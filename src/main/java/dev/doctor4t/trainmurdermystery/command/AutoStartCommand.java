package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.cca.AutoStartComponent;
import dev.doctor4t.trainmurdermystery.game.GameConstants;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class AutoStartCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("tmm:autoStart")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(
                                CommandManager.argument("seconds", IntegerArgumentType.integer(0, 60))
                                        .executes(context -> setAutoStart(context.getSource(), IntegerArgumentType.getInteger(context, "seconds")))
                        )
        );
    }

    private static int setAutoStart(ServerCommandSource source, int seconds) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    AutoStartComponent.KEY.get(source.getWorld()).setStartTime(GameConstants.getInTicks(0, seconds));
                    if (seconds > 0) {
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.autostart.enabled", seconds)
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    } else {
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.autostart.disabled")
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    }
                }
        );
    }
}
