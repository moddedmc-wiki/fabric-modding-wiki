package dev.mineblock11.fabric.referencemod.util.helper;

import dev.mineblock11.fabric.referencemod.MyMod;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.text.WordUtils;


/**
 * <h2>Text handling and translations</h2>
 * Methods from <a href="https://fabric.moddedmc.wiki/text-and-translations">Text handling and translations</a> and
 * <a href="https://fabric.moddedmc.wiki/data-generation/translations#programmatic-translation-example">Translation Provider</a> page.<br>
 */
@SuppressWarnings({"UnnecessaryLocalVariable", "UnusedReturnValue", "unused", "UnusedAssignment"})
public class TextUtil {
    private TextUtil() {
    }

    public static void initializeAllTextFunctions() {
        textLiterals();
        formattingText();
        generateHumanReadable(new Identifier(MyMod.MOD_ID, "big_blue_boat"));

        LoggerUtil.devLogger("Used all Text functions");
    }

    private static void textLiterals() {
        Text literal = Text.of("Hello, world!");
        MutableText mutable = Text.literal("Hello, world!");
        // Keep in mind that a MutableText can be used as a Text, making this valid:
        Text mutableAsText = mutable;
    }

    private static MutableText translatableText() {
        // check out the translation .json files in:
        // resources/assets/referencemod/lang

        Text translatable = Text.translatable("my_mod.text.hello");
        // Similarly to literals, translatable text can be easily made mutable.
        MutableText mutable = Text.translatable("my_mod.text.bye");

        return mutable;
    }

    private static String serializingTextToJson() {
        MutableText mutable = translatableText();

        String jsonString = Text.Serializer.toJson(mutable);
        return jsonString;
    }

    private static MutableText deserializingTextFromJson() {
        String jsonString = serializingTextToJson();

        MutableText result = Text.Serializer.fromJson(jsonString);
        return result;
    }

    private static void formattingText() {
        MutableText result = deserializingTextFromJson();
        result = Text.literal("Hello World!").formatted(Formatting.AQUA, Formatting.BOLD, Formatting.UNDERLINE);
    }

    public static String generateHumanReadable(Identifier identifier) {
        String identifier_path = identifier.getPath();
        String lowercase = identifier_path.replace("_", " ");
        String capitalized = WordUtils.capitalize(lowercase);
        return capitalized;
    }
}
