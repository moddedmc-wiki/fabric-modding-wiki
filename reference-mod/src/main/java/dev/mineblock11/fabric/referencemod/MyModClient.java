package dev.mineblock11.fabric.referencemod;

import dev.mineblock11.fabric.referencemod.particle.ModParticles;
import dev.mineblock11.fabric.referencemod.particle.custom.MyParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public class MyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.MY_PARTICLE, MyParticle.Factory::new);
    }
}
