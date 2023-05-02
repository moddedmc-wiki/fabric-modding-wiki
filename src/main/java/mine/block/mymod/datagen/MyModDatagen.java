package mine.block.mymod.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MyModDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
            final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

            // ...

            pack.addProvider(EnglishTranslationProvider::new);
    }
}