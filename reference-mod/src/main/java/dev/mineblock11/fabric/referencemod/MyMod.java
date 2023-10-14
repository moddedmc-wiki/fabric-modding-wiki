package dev.mineblock11.fabric.referencemod;

import dev.mineblock11.fabric.referencemod.block.ModBlockEntities;
import dev.mineblock11.fabric.referencemod.block.ModBlocks;
import dev.mineblock11.fabric.referencemod.event.ModEvents;
import dev.mineblock11.fabric.referencemod.item.ModItems;
import dev.mineblock11.fabric.referencemod.particle.ModParticles;
import dev.mineblock11.fabric.referencemod.sound.ModSounds;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import dev.mineblock11.fabric.referencemod.util.helper.TextUtil;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMod implements ModInitializer {
    public static final String MOD_ID = "referencemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModBlockEntities.registerBlockEntityTypes();
        ModItems.initialize();
        ModSounds.initializeSounds();
        ModEvents.registerAllEvents();
        ModParticles.initialize();
        TextUtil.initializeAllTextFunctions();

        LoggerUtil.devLogger("All classes have been initialized");
    }
}
