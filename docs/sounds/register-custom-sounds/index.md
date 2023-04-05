---
layout: default
title: Implement your own sounds
description: Learn how to add and use a new sound with the registry.
permalink: /sounds/register-custom-sounds
authors:
    - "jr1811"
page_nav:
    prev:
        content: Using Your First Sound
        url: /sounds/using-sound
---



## Preparing the audio file

Your audio files need to be formatted in a specific way. Ogg is an open container format for Multimedia data, such as audio, and is used in case of Minecraft's sound files. To avoid problems with how Minecraft handles distance of sound the audio data needs to have only a single channel (Mono).

Many modern DAWs (Digital Audio Workstation software) can import and export using this file format. In the following example the free and open-source software "Audacity" will be used to bring the audio file into the right shape, however any other DAW should suffice as well.

![unprepared audio file in Audacity](/docs/sounds/register-custom-sounds/index_0.png)

In this example, a sound of a [whistle](https://freesound.org/people/strongbot/sounds/568995/) is imported into Audacity. It currently is saved as a .wav file and has two audio channels (Stereo). Edit the sound to your liking and make sure to delete one of the channels using the drop-down element at the top of the "track head".

![splitting Stereo track](/docs/sounds/register-custom-sounds/index_1.png)

![deleting one of the channels](/docs/sounds/register-custom-sounds/index_2.png)

When exporting or rendering the audio file, make sure to choose the OGG file format. Some DAWs like REAPER might support multiple OGG audio layer formats. In this case OGG Vorbis should work just fine.

![exporting as OGG file](/docs/sounds/register-custom-sounds/index_3.png)

Also keep in mind that audio files can increase the file size of your mod drastically. If needed, compress the audio when editing and exporting the file to keep the file size of your finished product to a minimum.

## Adding the audio file to the mod

Add the new `resources/assets/<mod id here>/sounds` directory for the sounds in your mod, and put the exported audio file `metal_whistle.ogg` in there.

Continue with creating the `resources/assets/<mod id here>/sounds.json` file if it doesn't exist yet and add your sound to the sound entries.

```json
{
  "metal_whistle": {
    "subtitle": "sound.mod_id.metal_whistle",
    "sounds": [
      "mod_id:metal_whistle"
    ]
  }
}
```

The subtitle entry provides more context for the player. The subtitle name is used in the language .json files in the `resources/assets/<mod id here>/lang` directory and will be displayed if the In-game subtitle setting is turned on and this custom sound is being played.

## Register the custom sound

To add the custom sound to the mod, register a SoundEvent in the class which implements the `ModInitializer` entrypoint.

```java
public class ExampleMod implements ModInitializer {		
    
    public static final String MOD_ID = "mod_id";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Registry.register(Registries.SOUND_EVENT, new Identifier(ExampleMod.MOD_ID, "metal_whistle"), SoundEvent.of(new Identifier(ExampleMod.MOD_ID, "metal-whistle")));
	}
}
```

## Cleaning up the mess

Depending on how many Registry entries there are, this can get messy quickly. To avoid that, add a new `ExampleModSounds` class in the `src/main/java/.../examplemodname/sound` folder.

Add two new methods. One, which registers all the sounds and one which is used to initialize this class in the first place. After that you can comfortably add new custom SoundEvents as needed.

```java
public class ExampleModSounds {
    // ITEM_METAL_WHISTLE is the name of the custom sound event
    // and is called in the mod to use the custom sound
    public static SoundEvent ITEM_METAL_WHISTLE = registerSound("metal_whistle");

    // actual registration of all the custom SoundEvents
    static SoundEvent registerSound(String id) {
        SoundEvent sound = SoundEvent.of(new Identifier(ExampleMod.MOD_ID, id));
        return Registry.register(Registries.SOUND_EVENT, new Identifier(ExampleMod.MOD_ID, id), sound);
    }
    
    // called in the ModInitializer implementing class
    // to initialize the ExampleModSounds class
    public static void initializeSounds() {        
        ExampleMod.LOGGER.info("Registering " + ExampleMod.MOD_ID + " Sounds");
    }
}
```

This way, the `ModInitializer` implementing entrypoint class needs to only implement one line to register all custom SoundEvents.

```java
public class ExampleMod implements ModInitializer {		
    
    public static final String MOD_ID = "mod_id";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ExampleModSounds.initializeSounds();
	}
}
```

## Using the custom SoundEvent

Use the public `ExampleModSounds` class to access the custom SoundEvent. Get back to the [first page](/sounds/using-sound) if you want to see how to implement sound in your mod.


## Next Steps

- Start easy with adding two more custom sounds
- Can you implement one of those for a [custom Block](/blocks/blockstates) and one for an [interactive Item](/items/interactive-items)?