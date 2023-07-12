<template>
  <div v-if="info.index == 0" class="pt-4">
    <p class="uppercase text-sm font-bold pb-2">{{ info.title }}</p>
    <SidebarItem
      class="pl-1"
      v-if="info.children"
      v-for="child in info.children"
      :info="child"
    />
  </div>
  <div v-else>
    <UVerticalNavigation :links="links" />
  </div>
</template>

<script setup lang="ts">
interface PropInfo {
  title: String;
  children?: PropInfo[];
  icon?: String;
  link: String;
  index: number;
}

const props = defineProps<{ info: PropInfo }>();

const { info } = props;

const links = [
  {
    label: info.title,
    to: info.link,
  },
];
</script>
