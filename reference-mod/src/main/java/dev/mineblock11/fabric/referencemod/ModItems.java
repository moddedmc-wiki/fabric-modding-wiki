package dev.mineblock11.fabric.referencemod;

import dev.mineblock11.fabric.referencemod.armor.GuiditeArmorMaterial;
import dev.mineblock11.fabric.referencemod.tools.GuiditeMaterial;
import dev.mineblock11.fabric.referencemod.tools.LightningStick;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {

    // Registers a blank item with the ID of "modid:poop"
    // I use FabricItemSettings - for the sake of it.
    // You can use Item.Settings if you wish.
    public static final Item POOP = register(
            new Item(
                    new FabricItemSettings().food(new FoodComponent.Builder()
                            .alwaysEdible()
                            .snack()
                            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 6 * 20, 1), 1.0f)
                            .build())
            ),
            "poop"
    );

    // Tools
    public static final Item GUIDITE_SWORD = register(new SwordItem(GuiditeMaterial.INSTANCE, 2, 0.5F, new FabricItemSettings()), "guidite_sword");
    public static final Item GUIDITE_PICKAXE = register(new PickaxeItem(GuiditeMaterial.INSTANCE, 2, 0.5F, new FabricItemSettings()), "guidite_pickaxe");
    public static final Item GUIDITE_AXE = register(new AxeItem(GuiditeMaterial.INSTANCE, 2, 0.5F, new FabricItemSettings()), "guidite_axe");
    public static final Item GUIDITE_SHOVEL = register(new ShovelItem(GuiditeMaterial.INSTANCE, 2, 0.5F, new FabricItemSettings()), "guidite_shovel");
    public static final Item GUIDITE_HOE = register(new HoeItem(GuiditeMaterial.INSTANCE, 2, 0.5F, new FabricItemSettings()), "guidite_hoe");

    // Interactive Items
    public static final LightningStick LIGHTNING_STICK = register(new LightningStick(new FabricItemSettings()), "lightning_stick");

    // Armor
    public static final ArmorItem GUIDITE_HELMET = register(new ArmorItem(GuiditeArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new Item.Settings()), "guidite_helmet");
    public static final ArmorItem GUIDITE_CHESTPLATE = register(new ArmorItem(GuiditeArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new Item.Settings()), "guidite_chestplate");
    public static final ArmorItem GUIDITE_LEGGINGS = register(new ArmorItem(GuiditeArmorMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new Item.Settings()), "guidite_leggings");
    public static final ArmorItem GUIDITE_BOOTS = register(new ArmorItem(GuiditeArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new Item.Settings()), "guidite_boots");

    public static final ItemGroup MY_MOD_ITEMGROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.GUIDITE_SWORD))
            .displayName(Text.translatable("itemGroup."))
            .build();


    // We can use generics to make it, so we don't need to
    // cast to an item when using this method.
    public static <T extends Item> T register(T item, String ID) {
        // Create the identifier for the item.
        Identifier itemID = new Identifier(MyMod.MOD_ID, ID);

        // Register the item.
        T registeredItem = Registry.register(Registries.ITEM, itemID, item);

        // Return the registered item!
        return registeredItem;
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(MyMod.MOD_ID, "item_group"), MY_MOD_ITEMGROUP);


        ItemGroupEvents
                // Register a "modify" event for the Ingredients item group.
                .modifyEntriesEvent(RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(MyMod.MOD_ID, "item_group")))
                // Add the item to the group when you get access to it.
                .register((itemGroup) ->
                        {
                            itemGroup.add(ModItems.GUIDITE_SWORD);
                            itemGroup.add(ModItems.GUIDITE_HOE);
                            itemGroup.add(ModItems.GUIDITE_PICKAXE);
                            itemGroup.add(ModItems.GUIDITE_SHOVEL);
                            itemGroup.add(ModItems.GUIDITE_AXE);
                            itemGroup.add(ModItems.POOP);
                            itemGroup.add(ModItems.GUIDITE_HELMET);
                            itemGroup.add(ModItems.GUIDITE_CHESTPLATE);
                            itemGroup.add(ModItems.GUIDITE_LEGGINGS);
                            itemGroup.add(ModItems.GUIDITE_BOOTS);

                            itemGroup.add(ModBlocks.CONDENSED_DIRT.asItem());
                            itemGroup.add(ModBlocks.CONDENSED_OAK_LOG.asItem());
                            itemGroup.add(ModBlocks.PRISMARINE_LAMP.asItem());
                        }
                );
    }
}
