---
title: Item Interactivity
description: Learn how to create an item that uses built-in vanilla events.
order: 5
---

# Interactivity

Basic items can only go so far - eventually you will need an item that interacts with the world when it is used.

There are some key classes you must understand before taking a look at the vanilla item events.

## TypedActionResult

For items, the most common `TypedActionResult` you'll see is for `ItemStacks` - this class tells the game what to replace the item stack (or not to replace) after the event has occured.

If nothing has occured in the event, you should use the `TypedActionResult#pass(stack)` method where `stack` is the current item stack.

You can get the current item stack by getting the stack in the player's hand. Usually events that require a `TypedActionResult` pass the hand to the event method.

```java
TypedActionResult.pass(user.getStackInHand(hand))
```

If you pass the current stack - nothing will change, regardless of if you declare the event as failed, passed/ignored or successful.

If you want to delete the current stack, you should pass an empty one. The same can be said about decrementing, you fetch the current stack and decrement it by the amount you want:

```java
ItemStack heldStack = user.getStackInHand(hand);
heldStack.decrement(1);
TypedActionResult.success(heldStack);
```

## ActionResult

Similarly, an `ActionResult` tells the game the status of the event, whether it was passed/ignored, failed or successful.

## Overridable Events

Luckily, the Item class has many methods that can be overriden to add extra functionality to your items.

| Method          | Information                                             |
| --------------- | ------------------------------------------------------- |
| `postHit`       | Ran when the player hits an entity.                     |
| `postMine`      | Ran when the player mines a block.                      |
| `inventoryTick` | Ran every tick whilst the item is in an inventory.      |
| `onCraft`       | Ran when the item is crafted.                           |
| `useOnBlock`    | Ran when the player right clicks a block with the item. |
| `use`           | Ran when the player right clicks the item.              |

## The "use" event.

Let's say you want to make an item that summons a lightning bolt infront of the player - you would need to create a custom class.

```java
public class LightningStick extends Item {
    public LightningStick(Settings settings) {
        super(settings);
    }
}
```

The `use` event is probably the most useful out of them all - you can use this event to spawn our lightning bolt, you should spawn it 10 blocks in front of the players facing direction.

```java
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Ensure we don't spawn the lightning only on the client.
        // This is to prevent desync.
        if(world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        BlockPos frontOfPlayer = user.getBlockPos().offset(user.getHorizontalFacing(), 10);

        // Spawn the lightning bolt.
        LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.setPosition(frontOfPlayer.toCenterPos());
        world.spawnEntity(lightningBolt);

        // Nothing has changed to the item stack,
        // so we just return it how it was.
        return TypedActionResult.success(user.getStackInHand(hand));
    }
```

As usual, you should register your item, add a model and texture.

As you can see, the lightning bolt should spawn 10 blocks infront of you - the player.

![](./_assets/interactive_0.webp)