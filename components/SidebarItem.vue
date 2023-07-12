<template>
  <div
    v-if="info.index == 0 || info.children != null"
    :class="info.index == 0 ? 'pt-4' : ''"
  >
    <p class="uppercase text-sm font-bold pb-2">{{ info.title }}</p>
    <SidebarItem
      class="pl-4"
      v-if="info.children"
      v-for="child in info.children"
      :info="child"
    />
  </div>
  <div v-else>
    <UVerticalNavigation
      :ui="{
        active:
          'text-gray-900 dark:text-white before:bg-amber-100/50 dark:before:bg-amber-800/50',
        inactive:
          'text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-white hover:before:bg-gray-10 dark:hover:before:bg-amber-800/25',
      }"
      :links="links"
    />
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
