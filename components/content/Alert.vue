<template>
  <div :class="klass" role="alert">
    <p class="font-bold alert-title">{{ title }}</p>
    <p class="prose dark:prose-invert alert-content"><slot /></p>
  </div>
</template>

<script setup>
const { title, type } = defineProps({
  title: {
    type: String,
    default: "Note",
  },
  type: String,
});

const klass = ref("bg-stone-900 border-l-4 border-gray-500 p-4 rounded-md");

if (type === "info") {
  klass.value = "bg-stone-900 border-l-4 border-blue-500 p-4 rounded-md";
} else if (type === "warning") {
  klass.value = "bg-stone-900 border-l-4 border-orange-500 p-4 rounded-md";
} else if (type === "danger") {
  klass.value = "bg-stone-900 border-l-4 border-red-500 p-4 rounded-md";
}
</script>
