package amata1219.bryionake.dsl.context;

import amata1219.bryionake.dsl.component.ParsedArgumentQueue;
import amata1219.bryionake.type.Either;
import amata1219.bryionake.type.Either.*;
import amata1219.bryionake.type.SafeCaster;
import org.bukkit.command.CommandSender;

import java.util.Queue;

public class CastingCommandSenderContext<S extends CommandSender, N extends S> implements CommandContext<S> {

    private final SafeCaster<S, N, String> caster;
    private final CommandContext<N> context;

    public CastingCommandSenderContext(SafeCaster<S, N, String> caster, CommandContext<N> context) {
        this.caster = caster;
        this.context = context;
    }

    @Override
    public void execute(S sender, Queue<String> unparsedArguments, ParsedArgumentQueue parsedArguments) {
        Either<String, N> result = caster.tryCast(sender);
        if (result instanceof Failure) {
            String errorMessage = ((Failure<String, N>) result).error;
            sender.sendMessage(errorMessage);
            return;
        }

        N castedSender = ((Success<String, N>) sender).value;
        context.execute(castedSender, unparsedArguments, parsedArguments);
    }

}
