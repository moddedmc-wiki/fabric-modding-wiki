package mine.block.mymod;

import com.mojang.blaze3d.systems.RenderSystem;
import jdk.jfr.EventFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class MyMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModItems.initialize();

        PlayerDiedCallback.EVENT.register((player, deathMessage) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            ChatHud chat = client.inGameHud.getChatHud();

            chat.addMessage(Text.literal(player.getEntityName() + "... you're an absolute idiot."));
        });

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
            buffer.vertex(positionMatrix, 20, 20, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
            buffer.vertex(positionMatrix, 20, 60, 0).color(1f, 0f, 0f, 1f).texture(0f, 1f).next();
            buffer.vertex(positionMatrix, 60, 60, 0).color(0f, 1f, 0f, 1f).texture(1f, 1f).next();
            buffer.vertex(positionMatrix, 60, 20, 0).color(0f, 0f, 1f, 1f).texture(1f, 0f).next();

            RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
            RenderSystem.setShaderTexture(0, new Identifier("examplemod", "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            tessellator.draw();
        });

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            matrixStack.push();

            matrixStack.translate(40, 40, 0);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((System.currentTimeMillis() % 5000) / 5000f * 360f));
            matrixStack.translate(-40, -40, 0);

            Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
            buffer.vertex(positionMatrix, 20, 20, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
            buffer.vertex(positionMatrix, 20, 60, 0).color(1f, 0f, 0f, 1f).texture(0f, 1f).next();
            buffer.vertex(positionMatrix, 60, 60, 0).color(0f, 1f, 0f, 1f).texture(1f, 1f).next();
            buffer.vertex(positionMatrix, 60, 20, 0).color(0f, 0f, 1f, 1f).texture(1f, 0f).next();

            RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
            RenderSystem.setShaderTexture(0, new Identifier("examplemod", "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            tessellator.draw();
            matrixStack.pop();
        });

        WorldRenderEvents.END.register(context -> {
            Camera camera = context.camera();

            Vec3d targetPosition = new Vec3d(0, 100, 0);
            Vec3d transformedPosition = targetPosition.subtract(camera.getPos());

            MatrixStack matrixStack = new MatrixStack();
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180.0F));
            matrixStack.translate(transformedPosition.x, transformedPosition.y, transformedPosition.z);

            Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
            buffer.vertex(positionMatrix, 0, 1, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
            buffer.vertex(positionMatrix, 0, 0, 0).color(1f, 0f, 0f, 1f).texture(0f, 1f).next();
            buffer.vertex(positionMatrix, 1, 0, 0).color(0f, 1f, 0f, 1f).texture(1f, 1f).next();
            buffer.vertex(positionMatrix, 1, 1, 0).color(0f, 0f, 1f, 1f).texture(1f, 0f).next();

            RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
            RenderSystem.setShaderTexture(0, new Identifier("examplemod", "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            RenderSystem.disableCull();
            RenderSystem.depthFunc(GL11.GL_ALWAYS);

            tessellator.draw();

            RenderSystem.depthFunc(GL11.GL_LEQUAL);
            RenderSystem.enableCull();
        });

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            var playerName = player.getDisplayName().copy();
            var entityName = entity.getDisplayName().copy();

            MinecraftClient client = MinecraftClient.getInstance();
            ChatHud chat = client.inGameHud.getChatHud();

            chat.addMessage(Text.of("SNAP!!!!"));
            chat.addMessage(playerName.append(" attacked ").append(entityName));

            return ActionResult.PASS;
        });
    }
}
