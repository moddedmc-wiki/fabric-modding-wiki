<template>
  <DocsPageLayout>
    <slot />
  </DocsPageLayout>
</template>

<script setup>
const { page } = useContent();

const pageImageURL = `https://og.mineblock11.dev/fabric-community-wiki?title=${encodeURIComponent(
  page.value.title || "Fabric Community Wiki"
)}&description=${encodeURIComponent(
  page.value.description ||
    "An open source guide-book for creating mods using Fabric."
)}&path=${page.value._path || "/"}`;

useHead({
  meta: [
    { property: "og:image", content: pageImageURL },
    { property: "twitter:image", content: pageImageURL },
    { property: "twitter:title", content: page.value.title},
    { property: "twitter:description", content: page.value.description}
  ],
});

// export default {
//   head() {

//     return {
//       meta: [
//         {
//           property: "og:image",
//           content: ,
//         },
//       ],
//     };
//   },
// };
</script>
