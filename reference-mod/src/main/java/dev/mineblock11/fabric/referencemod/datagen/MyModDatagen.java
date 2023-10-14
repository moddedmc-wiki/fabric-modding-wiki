package dev.mineblock11.fabric.referencemod.datagen;

import dev.mineblock11.fabric.referencemod.datagen.provider.*;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MyModDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        LoggerUtil.devLogger("Initializing Datagen");
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // ...

        pack.addProvider(EnglishTranslationProvider::new);
        pack.addProvider(MyBlockTagProvider::new);
        pack.addProvider(MyBlockLootTableProvider::new);
    }
}