package dev.mineblock11.fabric.referencemod;

import dev.mineblock11.fabric.referencemod.block.ModBlocks;
import dev.mineblock11.fabric.referencemod.block.ModBlockEntities;
import dev.mineblock11.fabric.referencemod.event.ModEvents;
import dev.mineblock11.fabric.referencemod.item.ModItems;
import dev.mineblock11.fabric.referencemod.particle.ModParticles;
import dev.mineblock11.fabric.referencemod.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.text.WordUtils;
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

        Text translatable = Text.translatable("my_mod.text.hello");
        MutableText mutable = Text.translatable("my_mod.text.bye");

        String jsonString = Text.Serializer.toJson(mutable);
        MutableText result = Text.Serializer.fromJson(jsonString);

        result = Text.literal("Hello World!").formatted(Formatting.AQUA, Formatting.BOLD, Formatting.UNDERLINE);
    }

    public static String generateHumanReadable(Identifier identifier) {
        String identifier_path = identifier.getPath();
        String lowercase = identifier_path.replace("_", " ");
        String capitalized = WordUtils.capitalize(lowercase);
        return capitalized;
    }

    public static void devLogger(String input) {
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;
        LOGGER.info("DEV - [" + input + "]");
    }
}
