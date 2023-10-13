package dev.mineblock11.fabric.referencemod.sound;

import dev.mineblock11.fabric.referencemod.MyMod;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    // ITEM_METAL_WHISTLE is the name of the custom sound event
    // and is called in the mod to use the custom sound
    public static SoundEvent ITEM_METAL_WHISTLE = registerSound("metal_whistle");

    // actual registration of all the custom SoundEvents
    static SoundEvent registerSound(String id) {
        SoundEvent sound = SoundEvent.of(new Identifier(MyMod.MOD_ID, id));
        return Registry.register(Registries.SOUND_EVENT, new Identifier(MyMod.MOD_ID, id), sound);
    }
    public static void initializeSounds() {
        LoggerUtil.devLogger("Initializing SoundEvents");
    }
}
