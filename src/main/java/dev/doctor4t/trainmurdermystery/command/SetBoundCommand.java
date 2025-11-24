package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.cca.GameWorldComponent;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SetBoundCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("tmm:enableBounds")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                        .executes(context -> execute(context.getSource(), BoolArgumentType.getBool(context, "enabled"))))
        );
    }

    private static int execute(ServerCommandSource source, boolean enabled) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    GameWorldComponent gameWorldComponent = GameWorldComponent.KEY.get(source.getWorld());
                    gameWorldComponent.setBound(enabled);
                    
                    if (enabled) {
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.setbound.enabled")
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    } else {
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.setbound.disabled")
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    }
                }
        );
    }

}
