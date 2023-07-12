<template>
  <div class="flex flex-wrap gap-2">
    <div v-for="user in contributors">
      <UTooltip :text="user">
        <a :href="`https://github.com/${user}`">
          <UAvatar size="xl" :src="`https://github.com/${user}.png`"></UAvatar
        ></a>
      </UTooltip>
    </div>
  </div>
</template>

<script setup>
const contributors = ref([
  "JR1811",
  "0x3C50",
  "Friendly-Banana",
  "enjarai",
  "mineblock11",
  "Superkat32",
]);
</script>
