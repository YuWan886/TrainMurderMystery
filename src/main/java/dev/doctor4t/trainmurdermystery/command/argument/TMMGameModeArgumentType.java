package dev.doctor4t.trainmurdermystery.command.argument;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.serialization.Codec;
import dev.doctor4t.trainmurdermystery.cca.GameWorldComponent;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.StringIdentifiable;

import java.util.Arrays;
import java.util.Locale;

public class TMMGameModeArgumentType extends EnumArgumentType<GameWorldComponent.GameMode> {
    private static final Codec<GameWorldComponent.GameMode> CODEC = StringIdentifiable.createCodec(
            TMMGameModeArgumentType::getValues, name -> name.toLowerCase(Locale.ROOT)
    );

    private static GameWorldComponent.GameMode[] getValues() {
        return Arrays.stream(GameWorldComponent.GameMode.values()).toArray(GameWorldComponent.GameMode[]::new);
    }

    private TMMGameModeArgumentType() {
        super(CODEC, TMMGameModeArgumentType::getValues);
    }

    public static TMMGameModeArgumentType gamemode() {
        return new TMMGameModeArgumentType();
    }

    public static GameWorldComponent.GameMode getGamemode(CommandContext<ServerCommandSource> context, String id) {
        return context.getArgument(id, GameWorldComponent.GameMode.class);
    }

    @Override
    protected String transformValueName(String name) {
        return name.toLowerCase(Locale.ROOT);
    }
}
