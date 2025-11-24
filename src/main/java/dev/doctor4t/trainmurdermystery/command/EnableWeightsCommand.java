package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import dev.doctor4t.trainmurdermystery.cca.GameWorldComponent;
import dev.doctor4t.trainmurdermystery.cca.ScoreboardRoleSelectorComponent;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class EnableWeightsCommand {
    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("tmm:enableWeights")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean enabled = BoolArgumentType.getBool(context, "enabled");
                                    GameWorldComponent.KEY.get(context.getSource().getWorld()).setWeightsEnabled(enabled);
                                    ScoreboardRoleSelectorComponent.KEY.get(context.getSource()).reset();
                                    
                                    if (enabled) {
                                        context.getSource().sendFeedback(
                                            () -> Text.translatable("commands.tmm.enableweights.enabled")
                                                .styled(style -> style.withColor(0x00FF00)),
                                            true
                                        );
                                    } else {
                                        context.getSource().sendFeedback(
                                            () -> Text.translatable("commands.tmm.enableweights.disabled")
                                                .styled(style -> style.withColor(0x00FF00)),
                                            true
                                        );
                                    }
                                    return 1;
                                }))
        );
    }
}
