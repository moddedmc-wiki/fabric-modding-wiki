package mine.block.mymod.mixin;

import mine.block.mymod.PlayerDiedCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin {
    @Inject(method = "<init>", at = @At("TAIL"), cancellable = false)
    private void initDeathScreen(Text message, boolean isHardcore, CallbackInfo ci) {
        // Get the client player.
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        // Invoke the event for all listeners!
        PlayerDiedCallback.EVENT.invoker().invoke(player, message);
    }
}
