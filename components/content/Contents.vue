<template>
  <!-- This component is shown on the homepage. -->
  <div class="content">
    <ProseH2 id="jump-right-in">Jump right in!</ProseH2>
  </div>
  <CardGrid>
    <template #title></template>
    <Card v-for="page in contents" v-bind:key="page.title">
      <template #title>
        {{ page.title }}
      </template>
      <template #description>
        {{ page.excerpt }}
        <br /><br />
        <ButtonLink color="black" :href="page.url"> Read More → </ButtonLink>
      </template>
    </Card>
  </CardGrid>
</template>

<script>
export default {
  components: [],
  data: () => {
    return {
      contents: [
        {
          title: "Creating your first mod.",
          excerpt:
            "Get started using IntelliJ IDEA and the Minecraft Development plugin to create your first mod.",
          url: "/introduction/",
        },
        {
          title: "Adding Items",
          excerpt:
            "Learn how to register your own custom items; food, tools, armor and weapons.",
          url: "/items/",
        },
        {
          title: "Adding Blocks",
          excerpt:
            "Learn how to create and register your own custom blocks and block entities.",
          url: "/blocks/",
        },
        {
          title: "Adding Sounds",
          excerpt:
            "Learn how to use sounds and register your own custom SoundEvents.",
          url: "/sounds/",
        },
        {
          title: "Events",
          excerpt:
            "Use Fabric API's event system to encourage mod compatability and reduce the amount of mixins you write.",
          url: "/events/",
        },
        {
          title: "Rendering",
          excerpt:
            "Learn how to render objects in world-space and screen-space using Minecraft's rendering system and familiarize yourself with general techniques.",
          url: "/rendering/",
        },
        {
          title: "Miscelaneous",
          excerpt:
            "These pages aren't nessecarily in order - they explain how certain features of the game works.",
          url: "/misc/",
        },
      ],
    };
  },
};
</script>
