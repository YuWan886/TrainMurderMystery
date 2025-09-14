package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.ratatouille.util.registrar.SoundEventRegistrar;
import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

public interface TrainMurderMysterySounds {
    SoundEventRegistrar registrar = new SoundEventRegistrar(TrainMurderMystery.MOD_ID);

    BlockSoundGroup VENT_SHAFT = registrar.createBlockSoundGroup("block.vent_shaft", 1f, 1f);

    // Block special sounds
    SoundEvent BLOCK_CARGO_BOX_OPEN = registrar.create("block.cargo_box.open");
    SoundEvent BLOCK_CARGO_BOX_CLOSE = registrar.create("block.cargo_box.close");
    SoundEvent BLOCK_LIGHT_TOGGLE = registrar.create("block.light.toggle");
    SoundEvent BLOCK_PRIVACY_PANEL_TOGGLE = registrar.create("block.privacy_panel.toggle");
    SoundEvent BLOCK_SPACE_BUTTON_TOGGLE = registrar.create("block.space_button.toggle");
    SoundEvent BLOCK_BUTTON_TOGGLE_NO_POWER = registrar.create("block.button.toggle_no_power");
    SoundEvent BLOCK_DOOR_TOGGLE = registrar.create("block.door.toggle");
    SoundEvent BLOCK_SPRINKLER_RUN = registrar.create("block.sprinkler.run");
    SoundEvent BLOCK_DOOR_LOCKED = registrar.create("block.door.locked");

    // Items
    SoundEvent ITEM_KEY_DOOR = registrar.create("item.key.door");
    SoundEvent ITEM_LOCKPICK_DOOR = registrar.create("item.lockpick.door");
    SoundEvent ITEM_KNIFE_PREPARE = registrar.create("item.knife.prepare");
    SoundEvent ITEM_KNIFE_STAB = registrar.create("item.knife.stab");

    // Ambience
    SoundEvent AMBIENT_TRAIN_INSIDE = registrar.create("ambient.train.inside");
    SoundEvent AMBIENT_TRAIN_OUTSIDE = registrar.create("ambient.train.outside");

    static void initialize() {
        registrar.registerEntries();
    }
}
