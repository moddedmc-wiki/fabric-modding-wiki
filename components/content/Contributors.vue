<template>
  <ProseH1 id="contributors">
    Contributors
  </ProseH1>
  <ProseP>These amazing people contributed to the wiki:</ProseP>
  <List icon="-" v-for="contributor in contributors" :key="contributor">
      <ProseA :href="`https://github.com/${contributor}`" target="_blank">{{ contributor }}</ProseA>
  </List>
</template>

<script setup>
const contributors = ref(['JR1811', '0x3C50', 'Friendly-Banana', 'enjarai', 'mineblock11']);
</script>