package dev.mineblock11.fabric.referencemod.particle;

import dev.mineblock11.fabric.referencemod.MyMod;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType MY_PARTICLE = FabricParticleTypes.simple();
    public static void initialize() {
        LoggerUtil.devLogger("Initializing Particles");
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MyMod.MOD_ID, "my_particle"), MY_PARTICLE);
    }
}
