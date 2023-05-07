---
title: Creating Custom Particle
description: Learn how to add and use a new particle with the registry.
---

## Register a custom particle

To add a custom particle to your mod, you first need to register a ParticleType in your mod initializer class. 

```java
public class ExampleMod implements ModInitializer {

  public static final String MOD_ID = "mod_id";
  public static final DefaultParticleType MY_PARTICLE = FabricParticleTypes.simple();

  @Override
  public void onInitialize() {
    Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "my_particle"), MY_PARTICLE);
  }
}
```

The "my_particle" in lowercase letters is the json path for the particle's texture. You will be creating a new json file with that exact name later.

## Client-side registration

After you have registered the particle in the `ModInitializer` entrypoint, you will also need to register the particle in the `ModInitializerClient` entrypoint.

```java
public class ExampleModClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ExampleMod.MY_PARTICLE, EndRodParticle.Factory::new);
    }
}
```

In this example, we are registering our particle. We are then giving it some life with the end rod particle's factory. This means that the particle will behave exactly like the end rod particle would. You can replace the particle's factory with another particle's factory, or even your own!

## Creating a json file and adding textures

You will need to create 3 folders in your resources folder.

Let's begin with creating the folders necessary for the particle's texture(s). Add the new `resources/assets/<mod id here>/textures/particle` folders to your directory. Place the particle's textures that you want to use in the `particle` folder.

For this example, we will only be adding one texture, named "myparticletexture.png". It'll be a simple pixel art smiley face.

ADD SMILEY FACE HERE

Next, add the new `resources/assets/<mod id here>/particles` folder to your directory. Notice the "s" in "particles" for this folder's name. In this new `particles` folder, create a new json file named "my_particle.json" This is the lowercase "my_particle" from the inital registration.

In this file, you will need some code to help Minecraft know which texture(s) to put onto your particle.

```json
{
  "textures": [
    "<mod id here>:myparticletexture"
  ]
}
```

You can add more textures to animate the particle as well!

```json
{
  "textures": [
    "<mod id here>:myparticletexture1",
    "<mod id here>:myparticletexture2",
    "<mod id here>:myparticletexture3"
  ]
}
```

## Testing the new particle

Once you have completed the json file and saved your work, you are good to go! Load up minecraft and test everything out! You can see if everything has worked by typing the command `/particle <mod id here>:my_particle ~ ~1 ~`. You may need to go into third person to get a good look at it. You can also use a command block to summon the particle with the exact same command!
