package amata1219.bryionake.dsl.context;

import amata1219.bryionake.dsl.component.ParsedArgumentQueue;
import amata1219.bryionake.type.LabeledCommandContext;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Map;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BranchContext<S extends CommandSender> implements CommandContext<S> {

    private final Map<String, CommandContext<S>> contexts;
    private final Supplier<String> inadequateArgumentErrorMessage;

    public static <S extends CommandSender> BranchContext<S> define(Supplier<String> inadequateArgumentErrorMessage, LabeledCommandContext<S>... contexts) {
        return new BranchContext<>(
                Arrays.stream(contexts).collect(Collectors.toMap(c -> c.left, c -> c.right)),
                inadequateArgumentErrorMessage
        );
    }

    private BranchContext(Map<String, CommandContext<S>> contexts, Supplier<String> inadequateArgumentErrorMessage) {
        this.contexts = contexts;
        this.inadequateArgumentErrorMessage = inadequateArgumentErrorMessage;
    }

    @Override
    public void execute(S sender, Queue<String> args, ParsedArgumentQueue parsed) {
        if (args.isEmpty() || !contexts.containsKey(args.peek())) {
            sender.sendMessage(inadequateArgumentErrorMessage.get());
            return;
        }

        contexts.get(args.poll()).execute(sender, args, parsed);
    }

}
