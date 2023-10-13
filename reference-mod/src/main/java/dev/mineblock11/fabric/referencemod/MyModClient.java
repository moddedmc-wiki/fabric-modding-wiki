package dev.mineblock11.fabric.referencemod;

import dev.mineblock11.fabric.referencemod.block.ModBlocks;
import dev.mineblock11.fabric.referencemod.event.ModEvents;
import dev.mineblock11.fabric.referencemod.particle.ModParticles;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class MyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEvents.registerClient();
        ModBlocks.initializeClientRendering();
        ModParticles.initializeClient();

        LoggerUtil.devLogger("All client classes have been initialized");

        // TODO: I'm just an example "TODO" comment. I am here to show off my functionality in IntelliJ!
    }
}
