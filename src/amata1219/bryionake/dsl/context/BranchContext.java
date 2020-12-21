package amata1219.bryionake.dsl.context;

import amata1219.bryionake.dsl.component.ParsedArgumentQueue;
import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.Queue;
import java.util.function.Supplier;

public class BranchContext<S extends CommandSender> implements ExecutionContext<S> {

    private final Map<String, ExecutionContext<S>> contexts;
    private final Supplier<String> argumentNotFoundErrorMessage;

    private BranchContext(Map<String, ExecutionContext<S>> contexts, Supplier<String> argumentNotFoundErrorMessage) {
        this.contexts = contexts;
        this.argumentNotFoundErrorMessage = argumentNotFoundErrorMessage;
    }

    @Override
    public void execute(S sender, Queue<String> unparsedArguments, ParsedArgumentQueue parsedArguments) {
        if (unparsedArguments.isEmpty() || !contexts.containsKey(unparsedArguments.peek())) {
            sender.sendMessage(argumentNotFoundErrorMessage.get());
            return;
        }

        contexts.get(unparsedArguments.poll()).execute(sender, unparsedArguments, parsedArguments);
    }

}
