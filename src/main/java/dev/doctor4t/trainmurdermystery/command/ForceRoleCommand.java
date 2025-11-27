package dev.doctor4t.trainmurdermystery.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.doctor4t.trainmurdermystery.TMM;
import dev.doctor4t.trainmurdermystery.cca.ScoreboardRoleSelectorComponent;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ForceRoleCommand {
    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("tmm:forceRole").requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("killer").then(CommandManager.argument("players", EntityArgumentType.players())
                        .executes(context -> forceKiller(context.getSource(), EntityArgumentType.getPlayers(context, "players")))
                )).then(CommandManager.literal("vigilante").then(CommandManager.argument("players", EntityArgumentType.players())
                        .executes(context -> forceVigilante(context.getSource(), EntityArgumentType.getPlayers(context, "players")))
                ))
        );
    }

    private static int forceKiller(@NotNull ServerCommandSource source, @NotNull Collection<ServerPlayerEntity> players) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    ScoreboardRoleSelectorComponent component = ScoreboardRoleSelectorComponent.KEY.get(source.getServer().getScoreboard());
                    component.forcedKillers.clear();
                    for (var player : players) component.forcedKillers.add(player.getUuid());
                    
                    if (players.size() == 1) {
                        ServerPlayerEntity player = players.iterator().next();
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.forcerole.killer", player.getName().getString())
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    } else {
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.forcerole.killer.multiple", players.size())
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    }
                }
        );
    }

    private static int forceVigilante(@NotNull ServerCommandSource source, @NotNull Collection<ServerPlayerEntity> players) {
        return TMM.executeSupporterCommand(source,
                () -> {
                    ScoreboardRoleSelectorComponent component = ScoreboardRoleSelectorComponent.KEY.get(source.getServer().getScoreboard());
                    component.forcedVigilantes.clear();
                    for (var player : players) component.forcedVigilantes.add(player.getUuid());
                    
                    if (players.size() == 1) {
                        ServerPlayerEntity player = players.iterator().next();
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.forcerole.vigilante", player.getName().getString())
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    } else {
                        source.sendFeedback(
                            () -> Text.translatable("commands.tmm.forcerole.vigilante.multiple", players.size())
                                .styled(style -> style.withColor(0x00FF00)),
                            true
                        );
                    }
                }
        );
    }
}