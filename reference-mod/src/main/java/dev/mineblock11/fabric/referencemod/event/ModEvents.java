package dev.mineblock11.fabric.referencemod.event;

import dev.mineblock11.fabric.referencemod.event.callback.PlayerDiedCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;

public class ModEvents {

    public static void register() {
        PlayerDiedCallback.EVENT.register((player, deathMessage) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            ChatHud chat = client.inGameHud.getChatHud();

            chat.addMessage(Text.literal(player.getEntityName() + "... you're an absolute idiot."));
        });


    }
}
