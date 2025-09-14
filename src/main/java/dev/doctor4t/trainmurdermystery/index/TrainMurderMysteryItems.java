package dev.doctor4t.trainmurdermystery.index;

import dev.doctor4t.ratatouille.util.registrar.ItemRegistrar;
import dev.doctor4t.trainmurdermystery.TrainMurderMystery;
import dev.doctor4t.trainmurdermystery.item.KnifeItem;
import dev.doctor4t.trainmurdermystery.item.LockpickItem;
import dev.doctor4t.trainmurdermystery.item.RevolverItem;
import dev.doctor4t.trainmurdermystery.item.KeyItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public interface TrainMurderMysteryItems {
    ItemRegistrar registrar = new ItemRegistrar(TrainMurderMystery.MOD_ID);

    RegistryKey<ItemGroup> BUILDING_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, TrainMurderMystery.id("building"));
    RegistryKey<ItemGroup> DECORATION_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, TrainMurderMystery.id("decoration"));
    RegistryKey<ItemGroup> EQUIPMENT_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, TrainMurderMystery.id("equipment"));

    Item KEY = registrar.create("key", new KeyItem(new Item.Settings().maxCount(1)), EQUIPMENT_GROUP);
    Item LOCKPICK = registrar.create("lockpick", new LockpickItem(new Item.Settings().maxCount(1)), EQUIPMENT_GROUP);
    Item KNIFE = registrar.create("knife", new KnifeItem(new Item.Settings().maxCount(1)), EQUIPMENT_GROUP);
    Item REVOLVER = registrar.create("revolver", new RevolverItem(new Item.Settings().maxCount(1)), EQUIPMENT_GROUP);

    static void initialize() {
        registrar.registerEntries();

        Registry.register(Registries.ITEM_GROUP, BUILDING_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.trainmurdermystery.building"))
                .icon(() -> new ItemStack(TrainMurderMysteryBlocks.TARNISHED_GOLD_PILLAR))
                .build());
        Registry.register(Registries.ITEM_GROUP, DECORATION_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.trainmurdermystery.decoration"))
                .icon(() -> new ItemStack(TrainMurderMysteryBlocks.TARNISHED_GOLD_VENT_SHAFT))
                .build());
        Registry.register(Registries.ITEM_GROUP, EQUIPMENT_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.trainmurdermystery.equipment"))
                .icon(() -> new ItemStack(TrainMurderMysteryItems.KEY))
                .build());
    }
}
