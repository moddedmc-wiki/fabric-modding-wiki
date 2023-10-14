package dev.mineblock11.fabric.referencemod.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class MyParticle extends SpriteBillboardParticle {
  //The sprite provider which is used to determine the particle's texture
  private final SpriteProvider spriteProvider;
 
  MyParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
    super(world, x, y, z);
    this.spriteProvider = spriteProvider; //Sets the sprite provider from above to the sprite provider in the constructor method
    this.maxAge = 200; //20 ticks = 1 second
    this.scale = 0.1f;
    this.velocityX = velX; //The velX from the constructor parameters
    this.velocityY = -0.07f; //Allows the particle to slowly fall
    this.velocityZ = velZ;
    this.x = x; //The x from the constructor parameters
    this.y = y;
    this.z = z;
    this.collidesWithWorld = true;
    this.alpha = 1.0f; //Setting the alpha to 1.0f means there will be no opacity change until the alpha value is changed
    this.setSpriteForAge(spriteProvider); //Required
  }
 
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
      this.setSpriteForAge(this.spriteProvider); //Animates the particle if needed
      this.move(this.velocityX, this.velocityY, this.velocityZ);
    }
  }
 
  @Override
  public ParticleTextureSheet getType() {
    return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT; //Allows for the texture to have some transparency 
  }

  public static class Factory implements ParticleFactory<DefaultParticleType> {
    //The factory used in a particle's registry
    private final SpriteProvider spriteProvider;
    public Factory(SpriteProvider spriteProvider) {
      this.spriteProvider = spriteProvider;
    }
    public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
      return new MyParticle(clientWorld, x, y, z, velX, velY, velZ, this.spriteProvider);
    }
  }
}