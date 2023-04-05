---
layout: default
title: Using Your First Sound
description: Learn how to use an already registered sound.
permalink: /sounds/using-sound
authors:
    - "jr1811"
page_nav:
    prev:
        content: Contents
        url: /sounds/
    next:
        content: Register custom SoundEvents
        url: /sounds/register-custom-sounds
---

Sounds can be added in, for example, your custom item or block class, to enhance the user experience of your mod.

Minecraft has a big selection of sounds which you can choose from. Check out the `SoundEvents` class to see all, by the base game already registered, sounds.

## Using sounds in your mod

Make sure to execute the `playSound()` method on the logical server side when using sounds!

In this example, the `useOnEntity()` and `useOnBlock()` method for a custom [interactive Item](/items/interactive-items) is used to play a "placing copper block" and a pillager sound.

```java
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // As stated above, don't use the playSound() mothod on the client side.
        if (!entity.getWorld().isClient()) {
            // Play the sound with LivingEntity.
            entity.playSound(SoundEvents.ENTITY_PILLAGER_AMBIENT, 2f, 0.7f);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

```

The `playSound()` method is used with the `LivingEntity` object. Only the SoundEvent, the volume and the pitch need to be specified. You can also use the `playSound()`  method from an Instance of `World` where you can get access to more parameters.

```java
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Tipp of the day: Check out "Guard Clauses" to keep your code clean.
        if (!context.getWorld().isClient()) {
            // Play the sound with World.
            context.getWorld().playSound(null, context.getBlockPos(), 
                    SoundEvents.BLOCK_COPPER_PLACE, SoundCategory.PLAYERS, 
                    1f, 1f);
        }        
        return super.useOnBlock(context);
    }
```

### SoundEvent and SoundCategory

The SoundEvent defines which sound will be played. You can also [register your own SoundEvents](/sounds/register-custom-sounds) to include your own sound.

Minecraft has several audio sliders in the In-game settings. The SoundCategory is used to determine, which slider will adjust your sound's volume.

### Volume and Pitch

The volume parameter can be a bit misleading. In the range of `0.0f - 1.0f` the actual volume of the sound can be changed. If the number gets bigger than that, the volume of `1.0f` will be used and only the distance, in which your sound can be heard, gets adjusted. The block distance can be roughly calculated by `volume * 16`. The default value is `1.0f`.

The pitch parameter increases or decreases the pitch value and also changes the duration of the sound. In the range of `(0.5f - 1.0f)` the pitch and the speed gets decreased, while bigger numbers will increase the pitch and the speed. Numbers below `0.5f` will stay at the pitch value of `0.5f`. The default value is `1.0f`.

## Next Steps

- Already familiar with [Mixins](/introduction/mixins)? Try to lower the pitch of the Bell Block in the `BellBlock` class and increase the sound's distance too.