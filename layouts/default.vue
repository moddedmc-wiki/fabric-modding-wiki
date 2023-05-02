<template>
  <DocsPageLayout>
    <slot />
  </DocsPageLayout>
</template>

<script setup>
const { page } = useContent();

console.log(page.value);

useHead({
    meta: [
        {property: "og:image", content: `https://og.mineblock11.dev/fabric-community-wiki?title=${encodeURIComponent(
            page.value.title || "Fabric Community Wiki"
          )}&description=${encodeURIComponent(page.value.description || "An open source guide-book for creating mods using Fabric.")}&path=${
            page.value._path || "/"
          }`}
    ]
})

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
