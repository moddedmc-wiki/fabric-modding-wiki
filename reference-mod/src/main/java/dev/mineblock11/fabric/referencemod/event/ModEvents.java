package dev.mineblock11.fabric.referencemod.event;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.mineblock11.fabric.referencemod.MyMod;
import dev.mineblock11.fabric.referencemod.event.callback.PlayerDiedCallback;
import dev.mineblock11.fabric.referencemod.mixin.DeathScreenMixin;
import dev.mineblock11.fabric.referencemod.util.helper.LoggerUtil;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class ModEvents {

    public static void registerAllEvents() {
        LoggerUtil.devLogger("Initializing Events");

        attackedEntityEvent();
        playerDiedEvent();
        simpleHudRenderEvent();
        advancedHudRenderEvent();
        worldRenderEvent();
    }

    /**
     * <h2>Attacked Entity Event</h2>
     *  Event from <a href="https://fabric.moddedmc.wiki/events">Using Fabric API Events</a> page.<br>
     */
    private static void attackedEntityEvent() {
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

    /**
     * <h2>Creating a custom Event</h2>
     *  Event from <a href="https://fabric.moddedmc.wiki/events/creation">Creating Fabric API Events</a> page.<br>
     *  Also checkout {@linkplain PlayerDiedCallback} and {@linkplain DeathScreenMixin}
     */
    private static void playerDiedEvent() {
        PlayerDiedCallback.EVENT.register((player, deathMessage) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            ChatHud chat = client.inGameHud.getChatHud();
            chat.addMessage(Text.literal(player.getEntityName() + "... you're an absolute idiot."));
        });
    }

    /**
     * <h2>HUD Rendering 1</h2>
     *  Event from <a href="https://fabric.moddedmc.wiki/rendering">Introduction To Rendering</a> page.<br>
     */
    private static void simpleHudRenderEvent() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            Matrix4f positionMatrix = drawContext.getMatrices().peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
            buffer.vertex(positionMatrix, 20, 20, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
            buffer.vertex(positionMatrix, 20, 60, 0).color(1f, 0f, 0f, 1f).texture(0f, 1f).next();
            buffer.vertex(positionMatrix, 60, 60, 0).color(0f, 1f, 0f, 1f).texture(1f, 1f).next();
            buffer.vertex(positionMatrix, 60, 20, 0).color(0f, 0f, 1f, 1f).texture(1f, 0f).next();

            RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
            RenderSystem.setShaderTexture(0, new Identifier(MyMod.MOD_ID, "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            tessellator.draw();
        });
    }

    /**
     * <h2>HUD Rendering 2</h2>
     *  Event from <a href="https://fabric.moddedmc.wiki/rendering/matrices">Matrices</a> page.<br>
     */
    private static void advancedHudRenderEvent() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MatrixStack matrixStack = drawContext.getMatrices();
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
            RenderSystem.setShaderTexture(0, new Identifier(MyMod.MOD_ID, "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            tessellator.draw();
            matrixStack.pop();
        });
    }

    /**
     * <h2>World Rendering</h2>
     *  Event from <a href="https://fabric.moddedmc.wiki/rendering/world">Worldspace Rendering</a> page.<br>
     */
    private static void worldRenderEvent() {
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
            RenderSystem.setShaderTexture(0, new Identifier(MyMod.MOD_ID, "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            RenderSystem.disableCull();
            RenderSystem.depthFunc(GL11.GL_ALWAYS);

            tessellator.draw();

            RenderSystem.depthFunc(GL11.GL_LEQUAL);
            RenderSystem.enableCull();
        });
    }
}
