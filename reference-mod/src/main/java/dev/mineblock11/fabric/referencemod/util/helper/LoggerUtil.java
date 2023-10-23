package dev.mineblock11.fabric.referencemod.util.helper;

import dev.mineblock11.fabric.referencemod.MyMod;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;

/**
 * Helper class to make use of the {@link MyMod#LOGGER LOGGER} a bit easier.<br>
 * Constructor is not accessible and all methods are implemented statically.
 */
public class LoggerUtil {
    private LoggerUtil() {
    }

    /**
     * This method will print normal text to the console.
     * It uses the {@link MyMod#LOGGER LOGGER} and prints only,
     * if the instance has been launched in a developer environment.<br><br>
     * This method is an overload method of {@link #devLogger(String, boolean, Exception)}
     *
     * @param input String, which will be displayed in the console
     */
    public static void devLogger(String input) {
        devLogger(input, false, null);
    }

    /**
     * This method will print error text to the console.
     * It uses the {@link MyMod#LOGGER LOGGER} and prints only,
     * if the instance has been launched in a developer environment.<br><br>
     * This method is an overload method of {@link #devLogger(String, boolean, Exception)}
     *
     * @param input String, which will be displayed in the console
     */
    public static void devLogger(String input, Exception exception) {
        devLogger(input, true, exception);
    }

    /**
     * This method will print normal text or text with error highlighting to the console.
     * It uses the {@link MyMod#LOGGER LOGGER} and prints only,
     * if the instance has been launched in a developer environment.<br><br>
     *
     * @param input     String, which will be displayed in the console
     * @param isError   highlights text differently in the console
     * @param exception Throwable may be null, if not available
     */
    public static void devLogger(String input, boolean isError, @Nullable Exception exception) {
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;
        String outputText = "DEV - [" + input + "]";

        if (!isError) MyMod.LOGGER.info(outputText);
        else {
            if (exception == null) {
                MyMod.LOGGER.error(outputText);
                return;
            }

            MyMod.LOGGER.error(outputText, exception);
        }
    }
}
