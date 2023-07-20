package dev.mineblock11.fabric.referencemod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.text.WordUtils;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class MyMod implements ModInitializer {
    public static final String MOD_ID = "referencemod";

    public static String generateHumanReadable(Identifier identifier) {
        String identifier_path = identifier.getPath();
        String lowercase = identifier_path.replace("_", " ");
        String capitalized = WordUtils.capitalize(lowercase);
        return capitalized;
    }

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModBlockEntities.registerBlockEntityTypes();
        ModItems.initialize();

        PlayerDiedCallback.EVENT.register((player, deathMessage) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            ChatHud chat = client.inGameHud.getChatHud();

            chat.addMessage(Text.literal(player.getEntityName() + "... you're an absolute idiot."));
        });

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
            RenderSystem.setShaderTexture(0, new Identifier("examplemod", "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            tessellator.draw();
        });

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

        Text translatable = Text.translatable("my_mod.text.hello");
        MutableText mutable = Text.translatable("my_mod.text.bye");

        String jsonString = Text.Serializer.toJson(mutable);
        MutableText result = Text.Serializer.fromJson(jsonString);

        result = Text.literal("Hello World!").formatted(Formatting.AQUA, Formatting.BOLD, Formatting.UNDERLINE);

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
