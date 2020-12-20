package amata1219.bryionake.dsl.component;

import org.bukkit.command.CommandSender;

import java.util.Queue;

public class Command<S extends CommandSender> {

    public final S sender;
    public final Queue<String> args;
    public final ParsedArgumentQueue parsed;

    public Command(S sender, Queue<String> args, ParsedArgumentQueue parsed) {
        this.sender = sender;
        this.args = args;
        this.parsed = parsed;
    }

}
