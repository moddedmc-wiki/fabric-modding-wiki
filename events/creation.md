---
title: Creating Fabric API Events
description: Learn how to create your own events which allow compatability with other mods.
prev:
  text: "Using Fabric API Events"
  link: "/events/"
---

# Creating Fabric API Events

Fabric API exposes it's event classes so you can use them in your own mod.

This is useful if you want to allow other mods to add compatability with yours - or you want to create a library of events that are specific to your usage.

## Creating the functional interface.

A functional interface in Java is an interface that contains only a single abstract (unimplemented) method.

You'll use this functional interface to register event handlers and invoke the event.

In this example, we'll create an event that is called when the client player dies.

```java
@FunctionalInterface
public interface PlayerDiedCallback {
    void invoke(PlayerEntity player, Text deathMessage);
}
```

## Creating the event instance.

Next, you can use the `EventFactory` class provided by Fabric API to create an event that uses the `PlayerDiedCallback` class.

The `EventFactory.createArrayBacked` takes in a class reference to a functional interface, and a consumer which takes in all the registered listeners and the current values that are passed when the event is invoked.

We'll store the returned event in a field called `EVENT`

```java
public interface PlayerDiedCallback {
    Event<PlayerDiedCallback> EVENT = EventFactory.createArrayBacked(PlayerDiedCallback.class,
            (listeners) -> (player, deathMessage) -> {
                for (PlayerDiedCallback listener : listeners) {
                    // Invoke all event listeners with the provided player and death message.
                    listener.invoke(player, deathMessage);
                }
            });

    // ...
}
```

## Invoking the event via mixins

We'll hook into the `DeathScreen` class constructor to get the cause of death. We can get the player from the `MinecraftClient` instance.

```java
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
```

Remember to register your mixins in the right location! This mixin would be registered in the client section of `mixins.json`

## Wrapping It Up

You can now add listeners to this event - if you wanted to, you could even pass the death screen as a parameter of the callback if you want to be able to edit the screen from inside any event listeners.

In this example, I send `{Username}... you're an absolute idiot.` into the chat when the event is fired:

```java
PlayerDiedCallback.EVENT.register((player, deathMessage) -> {
    MinecraftClient client = MinecraftClient.getInstance();
    ChatHud chat = client.inGameHud.getChatHud();

    chat.addMessage(Text.literal(player.getEntityName() + "... you're an absolute idiot."));
});
```

![](./_assets/creating_events_0.png)