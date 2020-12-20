package amata1219.bryionake.type;

import amata1219.bryionake.dsl.context.CommandContext;
import org.bukkit.command.CommandSender;

public class LabeledCommandContext<S extends CommandSender> extends Pair<String, CommandContext<S>> {

    public LabeledCommandContext(String label, CommandContext<S> context) {
        super(label, context);
    }

}
