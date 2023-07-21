package dev.mineblock11.fabric.referencemod;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType MY_PARTICLE = FabricParticleTypes.simple();
    public static void initialize() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MyMod.MOD_ID, "my_particle"), MY_PARTICLE);
    }
}
