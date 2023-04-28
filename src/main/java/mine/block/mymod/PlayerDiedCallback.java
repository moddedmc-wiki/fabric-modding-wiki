package mine.block.mymod;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

@FunctionalInterface
public interface PlayerDiedCallback {
    Event<PlayerDiedCallback> EVENT = EventFactory.createArrayBacked(PlayerDiedCallback.class,
            (listeners) -> (player, deathMessage) -> {
                for (PlayerDiedCallback listener : listeners) {
                    // Invoke all event listeners with the provided player and death message.
                    listener.invoke(player, deathMessage);
                }
            });

    void invoke(ClientPlayerEntity player, Text deathMessage);
}
