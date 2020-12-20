package amata1219.bryionake.dsl.parser;

import amata1219.bryionake.type.Either;

import java.util.function.Function;
import java.util.function.Supplier;

import static amata1219.bryionake.type.Either.*;
import static org.bukkit.ChatColor.*;

public class Parsers {

    public static final FailableParser<String> str = Either::success;

    public static final FailableParser<Boolean> bool = define(Boolean::parseBoolean, () -> "bool値には true または false を指定して下さい。");

    private static <T> FailableParser<T> define(Function<String, T> parser, Supplier<String> errorMessage) {
        return arg -> {
            try {
                return success(parser.apply(arg));
            } catch (Exception e) {
                return failure("" + RED + BOLD + "Error! " + RESET + GRAY + "引数のパース処理に失敗しました。" + errorMessage.get());
            }
        };
    }

}
