package dev.mineblock11.fabric.referencemod.datagen;

import dev.mineblock11.fabric.referencemod.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.nio.file.Path;
import java.util.Optional;

public class EnglishTranslationProvider extends FabricLanguageProvider {
    protected EnglishTranslationProvider(FabricDataOutput dataOutput) {
        // Specifying the language code is optional in this case!
        // 'en_us' is always the default.
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModBlocks.PRISMARINE_LAMP, "Prismarine Lamp");

        try {
            Optional<Path> path = dataOutput.getModContainer().findPath("assets/mod_id/lang/en_us.unmerged.json");
            translationBuilder.add(path.get());
        } catch (Exception e) {
            LOGGER.info("Failed to merge language file: " + e);
        }
    }
}
