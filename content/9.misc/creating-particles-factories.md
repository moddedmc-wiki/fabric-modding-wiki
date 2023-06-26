---
title: Creating Particle Factories
description: Learn how to enhance particles with custom particle factories.
---

# Creating Particle Factories
Custom particle factories allow for the already powerful particles to become super powerful. You can add small rotating leaves to a beautiful scene, or huge powerful explosions to an intense boss battle. Let's learn about them and make one!

## Creating The Factory's Class
You can think of a particle factory as the particle's code. In this example, we are going to make our smiley faced texture from the Creating Custom Particles tutorial fall slowly, spin, and change size, then stop all movement and fade away upon hitting the ground.  
To use a custom particle factory, you'll need to create a class to contain all of the code. If you plan on making multiple factories, or just want to keep your packages organized, then you will probably want to create a new package called `particles`(this package would be placed in the same package as your "ClientModInitializer" class). This package will contain all of your particle factories.  
Next up, create a new class with the name of your particle. For this example, we will be calling it `MyParticle.class`. We have a particle already named "myparticle", however, if you had a particle named "fireball", you could name the class "FireballParticle.class". 

## Adding Code
Your particle needs code! A lot of code. Let's begin by adding the constructor and tick methods. Your class needs to extend the `SpriteBillboardParticle` class, and needs to have the `@Environment(EnvType.CLIENT)` line above it. 

```java
@Environment(EnvType.CLIENT)
public class MyParticle extends SpriteBillboardParticle {

}
```

You then will need to create the methods required for the particle to function. Let's add the methods required to make the particle come to life.

```java
@Environment(EnvType.CLIENT)
public class MyParticle extends SpriteBillboardParticle {
  //Leave at least one line of space here for later
  
  MyParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
    super(world, x, y, z);
    //Particle's constructor method
    //Activated once as soon as the particle spawns
  }
  
  public void tick() {
    //Tick particle method
    //Activated every single tick the particle is alive
  }
}
```

*Depending on your IDE of choice, some code that isn't in the snippet above may be added. If so, then do not worry about it for now.*  
The code above is for some of the methods required to make your particle have motion. There are still more methods to come however. But first, let's add some code to the stuff we already have.

## Determining What Variables To Add
In the particle's constructor, there are many variables you can choose to edit to your liking. Here is a list of some of the most common variables you may want to edit.
- `age`: Typically the amount of ticks the particle has been in the world.
- `maxAge`: Typically the total amount of ticks the particle has in the world.
- `scale`: The size of the particle.
- `angle`: The angle of the particle.
- `alpha`: The opacity, or transparency of the particle.
- `collidesWithWorld`: A boolean which determines if the particle should collide with the world or not.  

*The following are typically determined by the method caller.*
- `x`: The "x" position of the particle.
- `y`: The "y" position of the particle.
- `z`: The "z" position of the particle.
- `velocityX`: The velocity of the particle in the "x" coordinate direction.
- `velocityY`: The velocity of the particle in the "y" coordinate direction.
- `velocityZ`: The velocity of the particle in the "z" coordinate direction.

Once you have figured out which variables you want to adjust, and which ones you want to leave as default, it is time to continue adding code!

## Adding Variables
Back to your particle's constructor, it is finally time to add some variables! As mentioned earlier, our goal is to make a particle which falls slowly, spins, and changes size, then stop all movement and fade away upon hitting the ground.  
This means we're going to want to have some variables to allow for this, like the `angle`, `alpha`, and `collidesWithWorld` boolean. But then there are also variables we'd just want in general, like the `age` and `scale`, as well as the variables related to positisioing.

```java
@Environment(EnvType.CLIENT)
public class MyParticle extends SpriteBillboardParticle {
  //Leave at least one line of space here for later

  MyParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
    super(world, x, y, z);
    this.maxAge = 200; //200 ticks = 10 seconds
    this.scale = 0.1f; //About a block big
    this.velocityX = velX; //The velX from the constructor parameters
    this.velocityY = -0.07f; //Allows the particle to slowly fall
    this.velocityZ = velZ;
    this.x = x; //The x from the constructor parameters
    this.y = y;
    this.z = z;
    this.collidesWithWorld = true;
    this.alpha = 1.0f; //Setting the alpha to 1.0f means there will be no opacity change until the alpha value is changed
  }

  public void tick() {
    //Tick particle method
    //Activated every single tick the particle is alive
  }
}
```

Now that's a lot of variables! You may change the variables to your liking. But, you may have noticed something... Why do we change the "y" velocity if it is handled by the method caller? Well, we do this to make sure that, even if the method caller happens to be a command block, our particle will still fall slowly. Doing this doesn't always help, but in this case, it helps a lot!

## Adding Code to The Tick Method
There are a lot of ways you could go about adding code to the tick method. We will be making it where our `age` variable is the amount of ticks the particle has been in the world, and our `maxAge` variable is the total amount of ticks the particle should be in the world before despawning.

```java
  public void tick() {
    this.prevPosX = this.x;
    this.prevPosY = this.y;
    this.prevPosZ = this.z;
    this.prevAngle = this.angle; //required for rotating the particle
    if(this.age++ >= this.maxAge || this.scale <= 0 || this.alpha <= 0>) { //Despawns the particle if the age has reached the max age, or if the scale is 0
      this.markDead(); //Despawns the particle
    } else {
      this.move(this.velocityX, this.velocityY, this.velocityZ);
    }
  }
```
We have started by adding what is required. A way to despawn the particle, and a way to move the particle. Let's continue from here!

```java
  public void tick() {
    this.prevPosX = this.x;
    this.prevPosY = this.y;
    this.prevPosZ = this.z;
    this.prevAngle = this.angle; //Required for rotating the particle
    if(this.age++ >= this.maxAge || this.scale <= 0 || this.alpha <= 0) { //Despawns the particle if the age has reached the max age, or if the scale is 0, or if the alpha is 0
      this.markDead(); //Despawns the particle
    } else {
      if(!this.onGround) { //If the particle isn't on the ground
        if(this.age >= this.maxAge / 3) {
          this.scale -= 0.02; //Slowly decreases the particle's size
        } else {
          this.scale += 0.02; //Slowly increases the particle's size
        }
        this.angle = this.prevAngle + 0.07f; //Slowly turns the particle
      } else {
        //Stops all velocity movement
        this.velocityX = 0;
        this.velocityZ = 0;

        this.alpha -= 0.05f; //Slowly fades away upon hitting the ground
      }
      this.move(this.velocityX, this.velocityY, this.velocityZ);
    }
  }
```
Woah, what's going on here? A lot, actually. However, let's look at things from one bit of code to the next. First, we are detecting if the particle is on the ground with the `if(!this.onGround)` statement. If the particle *isn't* on the ground, it will continue the statement. The statement continues by asking if the particle has progressed through a third of its age. If so, then it starts to shrink the particle, otherwise it'll continue to grow the particle. Also, if the particle *isn't* on the ground, it will continue to change its angle by adding the previous angle plus an additionial float.  
What if the particle is on the ground? If the particle is on the ground, the particle losses *most* velocity. It still has the velocityY going, because we never set it to 0. We don't need to. The reason why we set it to something in the first place is to let it hit the ground, and now that it is on the ground, and because the particle collides with the world, the particle stops the "y" velocity automatically!  
We also begin to reduce the particle's alpha, until it eventually despawns whenever the alpha hits 0, thanks to the `if(this.age++ >= this.maxAge || this.scale <= 0 || this.alpha <= 0)` statement from earlier.

## Textures
We are getting very close to completeing our beautiful new particle factory. But, what is the point of making a beautiful particle factory if we can't see its beautiful texture? That's where our next bits of code comes in. First, you need to add the following.
```java
@Environment(EnvType.CLIENT)
public class MyParticle extends SpriteBillboardParticle {
  private final SpriteProvider spriteProvider; //Add me!
  MyParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
    super(world, x, y, z);
    this.spriteProvider = spriteProvider; //Add me!
    ...
    this.setSpriteForAge(spriteProvider); //Add me!
  }
```
You need to add one extra line to the tick method next. Notice the one line of code with the comment, "Add me!".
```java
  public void tick() {
  ...
    if(this.age++ >= this.maxAge || this.scale <= 0 || this.alpha <= 0>) {
      this.markDead();
    } else {
      ...
      this.move(this.velocityX, this.velocityY, this.velocityZ);
      this.setSpriteForAge(this.spriteProvider); //Add me!
    }
  }
```
And lastly, the method regarding the texture's type.
```java
  @Override
  public ParticleTextureSheet getType() {
    return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT; //Allows for the texture to have some transparency 
  }
```
## The Factory
There is one last method we need to add before testing our particle. It is the method which will be called whenever registering a particle with our factory. Add the following code to the bottom of your particle class.
```java
  @Environment(EnvType.CLIENT)
  public static class Factory implements ParticleFactory<DefaultParticleType> {
      private final SpriteProvider spriteProvider;
      public Factory(SpriteProvider spriteProvider) { //The factory used in the client initializer
        this.spriteProvider = spriteProvider;
      }
      public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
        return new MyParticle(clientWorld, x, y, z, velX, velY, velZ, this.spriteProvider);
      }
  }
```
Once you are done adding that, your particle code is complete! **But wait!** Don't load up your game yet, as you still need to register your particle using this new particle factory.
## Using The New Factory
In your `ClientModInitializer` class, edit your particle register to something like the following.
```java
ParticleFactoryRegistry.getInstance().register(<mod id here>.MY_PARTICLE, MyParticle.Factory::new);
```
Now that your particle factory is being used in the particle registry, you may load up your game and test out the particle!
## The Most Beautiful Particle You Will Ever See
Try spawning the particle a block in the air, 5 blocks in the air, and 10 whole blocks in the air!  
A single block in the air.  
![](/misc/creating_particle_factories_1.png)  
5 blocks in the air.  
![](/misc/creating_particle_factories_2.png)  
10 whole blocks in the air!  
![](/misc/creating_particle_factories_3.png)  
![](/misc/creating_particle_factories_4.png)  

## Full Example
```java
@Environment(EnvType.CLIENT)
public class MyParticle extends SpriteBillboardParticle {
  private final SpriteProvider spriteProvider;
  MyParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
    super(world, x, y, z);
    this.spriteProvider = spriteProvider;
    this.maxAge = 200; //200 ticks = 10 seconds
    this.scale = 0.1f; //About a block big
    this.velocityX = velX; //The velX from the constructor parameters
    this.velocityY = -0.07f; //Allows the particle to slowly fall
    this.velocityZ = velZ;
    this.x = x; //The x from the constructor parameters
    this.y = y;
    this.z = z;
    this.collidesWithWorld = true;
    this.alpha = 1.0f; //Setting the alpha to 1.0f means there will be no opacity change until the alpha value is changed
    this.setSpriteForAge(spriteProvider);
  }

  public void tick() {
    this.prevPosX = this.x;
    this.prevPosY = this.y;
    this.prevPosZ = this.z;
    this.prevAngle = this.angle;
    if(this.age++ >= this.maxAge || this.scale <= 0 || this.alpha <= 0) { //Despawns the particle if the age has reached the max age, or if the scale is 0, or if the alpha is 0
      this.markDead(); //Despawns the particle
    } else {
      if(!this.onGround) { //If the particle isn't on the ground
        if(this.age >= this.maxAge / 3) {
          this.scale -= 0.02; //Slowly decreases the particle's size
        } else {
          this.scale += 0.02; //Slowly increases the particle's size
        }
        this.angle = this.prevAngle + 0.07f; //Slowly turns the particle
      } else {
        //Stops all velocity movement
        this.velocityX = 0;
        this.velocityZ = 0;

        this.alpha -= 0.05f; //Slowly fades away upon hitting the ground
      }
      this.move(this.velocityX, this.velocityY, this.velocityZ);
      this.setSpriteForAge(this.spriteProvider);
    }
  }

  @Override
  public ParticleTextureSheet getType() {
    return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT; //Allows for the texture to have some transparency
  }

  @Environment(EnvType.CLIENT)
  public static class Factory implements ParticleFactory<DefaultParticleType> {
      private final SpriteProvider spriteProvider;
      public Factory(SpriteProvider spriteProvider) { //The factory used in the client initializer
        this.spriteProvider = spriteProvider;
      }
      public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
        return new MyParticle(clientWorld, x, y, z, velX, velY, velZ, this.spriteProvider);
      }
  }
}
```