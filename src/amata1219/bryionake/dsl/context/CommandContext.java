package amata1219.bryionake.dsl.context;

import amata1219.bryionake.dsl.component.ParsedArgumentQueue;
import amata1219.bryionake.dsl.parser.FailableParser;
import amata1219.bryionake.type.Either;
import amata1219.bryionake.type.Either.*;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Queue;
import java.util.function.Supplier;

public class CommandContext<S extends CommandSender> implements ExecutionContext<S> {

    private final ExecutionContext<S> context;
    private final ArrayList<FailableParser<?>> parsers;
    private final Supplier<String> argumentNotFoundErrorMessage;

    public CommandContext(ExecutionContext<S> context, ArrayList<FailableParser<?>> parsers, Supplier<String> argumentNotFoundErrorMessage) {
        this.context = context;
        this.parsers = parsers;
        this.argumentNotFoundErrorMessage = argumentNotFoundErrorMessage;
    }

    @Override
    public void execute(S sender, Queue<String> unparsedArguments, ParsedArgumentQueue parsedArguments) {
        if (unparsedArguments.isEmpty()) {
            sender.sendMessage(argumentNotFoundErrorMessage.get());
            return;
        }

        for (FailableParser<?> parser : parsers) {
            Either<String, ?> result = parser.parse(unparsedArguments.poll());
            if (result instanceof Failure) {
                String errorMessage = ((Failure<String, ?>) result).error;
                sender.sendMessage(errorMessage);
                return;
            }

            Object parsedArgument = ((Success<String, ?>) result).value;
            parsedArguments.offer(parsedArgument);
        }

        context.execute(sender, unparsedArguments, parsedArguments);
    }

}
