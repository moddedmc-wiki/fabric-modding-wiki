---
layout: default
title: Matrices
description: Transforming rendered content using Matrices
permalink: /rendering/matrices
authors:
  - "0x3C50"
---

## What?
Matrices provide an easy way to transform rendered elements. They also allow content to be transformed into world space properly.

## Where?
Assuming you're coming from the [basics](/rendering/basics), you've already met them. In fact, they're everywhere, which is a good thing, since that means that we can transform about anything.

## The Minecraftian implementation
Minecraft has a wrapper around this entire mechanism, called the `MatrixStack`. It's a stack of matrices, which means that you can `push()` and `pop()` from it. These 2 methods will come in very handy later, since they essentially allow you to back up (`push()`) the current state, and then restore the correct backup later (`pop()`).

## Using a MatrixStack
When rendering anything, you usually have to pass in a MatrixStack from somewhere else. Since we also have access to the MatrixStack, we can make a backup of it (`push()`), transform it in any way we want, render with it, then restore it before passing it along to the next component (`pop()`).

Let's take our old example from [basics](/rendering/basics) for this example:
```java
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
```

We can already see a Matrix4f being made from our matrixStack we got from the event, so let's try modifying it.

We have 3 ways to modify a MatrixStack: `translate(x, y, z)`, `scale(x, y, z)` and `multiply(Quaternion)`. `multiply` is just a fancy word for "rotate the entire stack with the given quaternion". This is probably a good point to watch [3Blue1Brown - Visualizing quaternions [...] with stereographic projection](https://www.youtube.com/watch?v=d4EgbgTm0Bg).

## Scaling & Translating

Before anything, let's wrap our code in `matrixStack.push()` and `.pop()`:
```java
// ...
    matrixStack.push();
    Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
    // ...
    tessellator.draw();
    matrixStack.pop();
// ...
```

Then, let's try scaling the matrixStack on the x axis, to stretch the image out:
```java
// ...
    matrixStack.push();
    matrixStack.scale(2, 1, 1);
    // ...
```

Doing this results in the following render:
![](/docs/rendering/matrices/index_0.png)

If you're looking really close, you might've noticed that the spacing on the left looks a little... off?

You're correct, scaling all of the vertecies has the side effect that any precomputed padding is ruined. To fix this, we need to either
1. translate the Matrix to position the element correctly (positioning the element itself at 0, 0, 0), or
2. translate the Matrix to account for the added padding

let's look at option 2 for now.

Since we have doubled the scale on the x axis, we have added 20 additional pixels to the padding. To remove them, we just need to translate the matrix 20 px to the left: `translate(-20, 0, 0)`.

It's important to do this **before** scaling the matrix, since matrices will apply previous transformations to new transformations as well.

Adding the translate call before the scale call results in this result:
![](/docs/rendering/matrices/index_1.png)

Since that looks good, let's try to rotate it instead.

## Rotation

Assuming you know what a quaternion is, we can use them to rotate our drawn content. Lucky for us, minecraft has an utility to help with quaternions: `RotationAxis`.

We'll be using `RotationAxis.POSITIVE_Z` to rotate the image clockwise on the screen. Why `POSITIVE_Z`?
![](/docs/rendering/matrices/index_5.png)

By calling `multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45))`, we can effectively rotate the entire drawn content by 45 degrees clockwise, but something goes horribly wrong:
![](/docs/rendering/matrices/index_2.png)

Looking at the previous attempt, we can easily connect the dots here: We're rotating the element positioned at (20, 20) from the origin (0, 0), which will rotate the entire thing in a way that we don't want.

To fix this, we have to
1. translate the Matrix to shift the entire element into the right position
2. rotate it
3. translate the Matrix to shift the entire element onto (0, 0), where we want to rotate it from

Remember: previous transformations apply to the future ones, so the 3rd step is rotated from the 2nd, which is translated from the 1st

All in all, an example of this would look like this:
```java
matrixStack.translate(40, 40, 0); // shift the entire element +40 +40
matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45)); // rotate
matrixStack.translate(-40, -40, 0); // shift the entire element onto (0, 0) in the center
```

After doing this, we finally get a proper result:
![](/docs/rendering/matrices/index_3.png)

And since this is rendered each frame, we can easily animate it:
![](/docs/rendering/matrices/index_4.gif)

<sup>(The code used for this: `matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((System.currentTimeMillis() % 5000) / 5000f * 360f));`)</sup>

## Full example
```java
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
```