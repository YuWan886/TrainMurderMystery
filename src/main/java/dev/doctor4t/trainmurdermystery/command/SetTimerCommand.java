package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.cca.GameTimeComponent;
import dev.doctor4t.trainmurdermystery.game.GameConstants;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SetTimerCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("tmm:setTimer")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(
                                CommandManager.argument("minutes", IntegerArgumentType.integer(0, 240))
                                        .then(
                                                CommandManager.argument("seconds", IntegerArgumentType.integer(0, 59))
                                                        .executes(context -> setTimer(context.getSource(), IntegerArgumentType.getInteger(context, "minutes"), IntegerArgumentType.getInteger(context, "seconds")))
                                        )
                        )
        );
    }

    private static int setTimer(ServerCommandSource source, int minutes, int seconds) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    GameTimeComponent.KEY.get(source.getWorld()).setTime(GameConstants.getInTicks(minutes, seconds));
                    source.sendFeedback(
                        () -> Text.translatable("commands.tmm.settimer", minutes, seconds)
                            .styled(style -> style.withColor(0x00FF00)),
                        true
                    );
                }
        );
    }
}
