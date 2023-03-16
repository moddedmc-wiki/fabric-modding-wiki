---
title: Data Generation
description: 
permalink: /datagen/
    - "JoostMSoftware"
layout: default
---

## Getting started with Data Generation

Data Generation is a of the Fabric API, it allows you to data generate basically anything as long there is a ``provider`` available for it.

Firstly we'll add a new gradle task which will take the code we write to generate data and spit out files Minecraft can read. Every time you modify the code that generates advancements (or anything else datagen can make like loot tables and such) you'll have to run the gradle task ``runDatagenClient``. We will do that later, but first open up your ``build.gradle`` file and add the following task:

```gradle
// ... (The rest of the file)
loom {
    runs {
        datagenClient {
            inherit client
            name "Data Generation"
            vmArg "-Dfabric-api.datagen"
            vmArg "-Dfabric-api.datagen.output-dir=${file("src/main/generated")}"
            vmArg "-Dfabric-api.datagen.modid=${modid}"
 
            runDir "build/datagen"
        }
    }
}
 
sourceSets {
    main {
        resources {
            srcDirs += [
                    'src/main/generated'
            ]
        }
    }
}
```

You'll see that it makes use of the variable ``${modid}``. That should be defined in the ``gradle.properties`` file or adjust as what follows below to manually add your ``modId`` to it like:

```gradle
// .. (The rest of the file)
vmArg "-Dfabric-api.datagen.modid=example_modid"
// .. (The rest of the file)`
```

A quick reminder if you use the IntelliJ IDE, make sure to press the load gradle changes icon (the blue elephant with a reload logo) somewhere in the top right to generate this task.

Next we'll define a new class in our project ``ExampleModDataGeneration`` which implements ``DataGeneratorEntrypoint``.

- The ``onInitializeDataGenerator`` function will be called whenever the gradle task we created earlier ``(runDatagenClient)`` is ran.

```java
package io.github.joostmsoftware.examplemod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
 
public class ExampleModDataGeneration implements DataGeneratorEntrypoint {
 
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
 
    }
}
```

Now we need to tell fabric about this entry point in our ``fabric.mod.json`` file:

```jsonc
{
  // ... (The rest of the file)
  "entrypoints": {
    "fabric-datagen": [
      "io.github.joostmsoftware.examplemod.ExampleModDataGeneration"
    ],
    "main": [
      "io.github.joostmsoftware.examplemod.DataGenExampleMod"
    ],
    "client": [
      "io.github.joostmsoftware.examplemod.DataGenExampleModClient"
    ]
  },
  // ... (The rest of the file)
}
```

Before continuing further let's see if what we have so far is working, or if we have any errors. Run the ``runDatagenClient`` task. You can have your IDE do that for you with the task name: ``Data Generation``, or just open the terminal in the root directory of your project and do:

```
./gradlew runDatagenClient
```

Read the output and make sure there are no errors.

- You can safely ignore: ``com.mojang.authlib.exceptions.InvalidCredentialsException: Status: 401`` if it comes up. That error happens because the debug version of Minecraft we're running doesn't try to authenticate our account.

If you do get an error, it's usually pretty explicit about what's missing or wrong, but if you can't seem to figure it out, you might want to head over to the fabric discord, and get some help there.

There should be a new folder in ``src/main`` called ``generated``. For now it'll be empty, but once we start generating data (like advancements), that is where it'll be saved.

## Adding Providers

These ``providers`` are currently implemented in the Fabric API and have a working and up to date tutorial.

- [Advancements Generation](/docs/data-gen/providers/advancements-generations)
