package amata1219.bryionake.dsl;

import amata1219.bryionake.dsl.argument.ParsedArgumentQueue;
import amata1219.bryionake.dsl.context.BranchContext;
import amata1219.bryionake.dsl.context.CommandContext;
import amata1219.bryionake.dsl.context.ExecutionContext;
import amata1219.bryionake.dsl.parser.FailableParser;
import amata1219.bryionake.adt.Pair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

public interface CommandExecutor extends org.bukkit.command.CommandExecutor {

    @Override
    default boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        executor().execute(sender, new ArrayDeque<>(Arrays.asList(args)), new ParsedArgumentQueue());
        return true;
    }

    default <S extends CommandSender> ExecutionContext<S> define(Supplier<String> argumentNotFoundErrorMessage, CommandContext<S> context, FailableParser<?>... parsers) {
        return new ExecutionContext<>(argumentNotFoundErrorMessage, (ArrayList<FailableParser<?>>) Arrays.asList(parsers), context);
    }

    default <S extends CommandSender> BranchContext<S> define(Supplier<String> argumentNotFoundErrorMessage, Pair<String, CommandContext<S>>... branches) {
        HashMap<String, CommandContext<S>> contexts = new HashMap<>();
        for (Pair<String, CommandContext<S>> branch : branches) contexts.put(branch.left, branch.right);
        return new BranchContext<>(argumentNotFoundErrorMessage, contexts);
    }

    default <S extends CommandSender> Pair<String, CommandContext<S>> bind(String label, CommandContext<S> context) {
        return new Pair<>(label, context);
    }

    CommandContext<? extends CommandSender> executor();

}