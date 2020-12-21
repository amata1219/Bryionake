package amata1219.bryionake.constant;

import amata1219.bryionake.constant.Constants;
import amata1219.bryionake.type.SafeCaster;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SafeCasters {

    public static final SafeCaster<CommandSender, Player, String> casterToPlayer = new SafeCaster<>(() -> Constants.ERROR_MESSAGE_PREFIX + "ゲーム内から実行して下さい。");

}
