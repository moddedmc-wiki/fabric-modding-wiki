<template>
  <div class="color-swatch" :style="`background-color: ${color}`" />
</template>

<script>
export default {
  props: ["color"],
};
</script>

<style>
td:has(.color-swatch) {
  display: flex;
  align-items: center;
  align-content: center;
}

.color-swatch {
  width: 100%;
  height: 100%;
  padding: 10%;
  margin: auto;
  margin-left: 10%;
  margin-right: 10%;
  height: 100%;
  border-radius: 0.25rem;
  border-color: grey;
  border-width: 1px;
}
</style>
