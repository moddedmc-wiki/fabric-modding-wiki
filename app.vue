<template>
  <div>
    <NuxtLayout>
      <NuxtPage />
    </NuxtLayout>
  </div>
  <template v-if="!isMounted">
    <NuxtLink v-for="route in routes" :to="route" />
  </template>
</template>

<script lang="ts">
const useIsMounted = () => {
  const isMounted = ref(false);
  onMounted(() => {
    isMounted.value = true;
  });
  return isMounted;
};

export default defineNuxtComponent({
  setup() {
    const routes = useRouter().getRoutes();
    const isMounted = useIsMounted();
    return { routes, isMounted };
  },
});
</script>

<style>
.prose :where(code):not(:where([class~="not-prose"] *))::before,
.prose :where(code):not(:where([class~="not-prose"] *))::after {
  content: "" !important;
}

.prose {
  max-width: max-content !important;
}

.prose > p > img {
  margin-left: auto;
  margin-right: auto;
}

::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  background-color: transparent;
}

::-webkit-scrollbar {
  width: 4px;
  background-color: transparent;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #555;
}
</style>
