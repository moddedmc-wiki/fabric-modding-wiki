---
title: Using Fabric API Events
description: Learn how to use Fabric API's event system.
navigation: false
---

::alert{type="warning"}
This content is for `1.19.3` and may not be supported.
::

# Using Fabric API Events

Fabric API contains **many** events that you can use instead of creating mixins - if everyone made the same mixin, to do the same task, it would get messy.

You can simply [search for events](https://github.com/search?q=repo%3AFabricMC%2Ffabric%20Events&type=code) on the Fabric API GitHub repository to find an event that suits your usage.

In this page, you'll utilize the `AttackEntityCallback` to send sound effects into the chat (POW! BANG! SNAP!) when the player attacks.

## Registering a listener.

Registering listeners are the easy part! Simply use the `register(listener)` method on the event and either pass a method reference or a lambda function.

```java
AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ...
});
```

## Utilizing event data.

Different events will provide different data - some even ask for data back, such as an integer or boolean.

In the case of our example, the event requires an `ActionResult`, the javadoc states the following `ActionResult` types can be used:

|Type|Affect|
|----|---------------|
|`ActionResult.SUCCESS`|Cancels all other listeners, cancels the attack as well.|
|`ActionResult.PASS`|Do not do anything else.|
|`ActionResult.FAIL`|Cancel all other listeners, but don't cancel the attack.|

Since we don't actually care why the player is attacking, we'll simply return a pass result.

```java
AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
    return ActionResult.PASS;
});
```

We also need to take note of the name of the player, and the name of the entity they are attacking:

```java
var playerName = player.getDisplayName().copy();
var entityName = entity.getDisplayName().copy();
```

Finally, we can send the sound effect text into the chat:

```java
MinecraftClient client = MinecraftClient.getInstance();
ChatHud chat = client.inGameHud.getChatHud();

chat.addMessage(Text.of("SNAP!!!!"));
chat.addMessage(playerName.append(" attacked ").append(entityName));
```

Some events may be client-only, or server-only - please be careful when using those events that you're not using server or client specific methods.

## Event Phases

Some events may have different phases - these phases are ran in a certain order depending on their **priority**.

If you want to run your listener last, you register it to a later phase. If you want to run it first, register it to a earlier phase.

Different phases might provide different data. For example, the final phase of the resource events do not let you modify the resource pack, because you are too late!

## Next Steps

This page is relatively short, mostly because it isn't a complex topic.

If you're interested in creating your own events - specifically for mod compatability or for library usage, you should see the [Creating Your Own Events](/events/creation) page.

- Why don't you make it so an actual sound plays when the player attacks something, instead of putting it in the chat?
- Can you use events to make it so the player drops a random item out of their inventory when they recieve damage?
- Try implement the example without events using mixins, see why it's so annoying and difficult!