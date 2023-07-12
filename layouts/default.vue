<script setup>
import ColourToggle from "../components/ColourToggle.vue";
const { data: navigation } = await useAsyncData("navigation", () =>
  fetchContentNavigation()
);

const fixedLinks = [
  {
    label: "Fabric Community Wiki",
    avatar: {
      src: "/favicon.ico",
    },
    badge: "1.20.1",
  },
  {
    label: "GitHub",
    icon: "i-mdi-github",
    to: "https://github.com/mineblock11/fabric-community-wiki",
  },
];

const dynamicLinks = [];

function mapChildren(children, index) {
  if (!children) return null;
  return children.map((child) => {
    return {
      title: child.title,
      icon: child.icon,
      link: child._path,
      index: index + 1,
      children: mapChildren(child.children, index + 1),
    };
  });
}

if (navigation.value != null) {
  navigation.value.forEach(async (navItem) => {
    dynamicLinks.push({
      title: navItem.title,
      icon: navItem.icon,
      link: navItem._path,
      index: navItem.children ? 0 : -1,
      children: mapChildren(navItem.children, 0),
    });
  });
}

import {
  Disclosure,
  DisclosureButton,
  DisclosurePanel,
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
} from "@headlessui/vue";

const { page } = useContent();

if (page.value != null) {
  const pageImageURL = `https://og.mineblock11.dev/fabric-community-wiki?title=${encodeURIComponent(
    page.value.title || "Fabric Community Wiki"
  )}&description=${encodeURIComponent(
    page.value.description ||
      "An open source guide-book for creating mods using Fabric."
  )}&path=${page.value._path || "/"}`;

  useSeoMeta({
    ogImage: pageImageURL,
    twitterImage: pageImageURL,
    twitterTitle: page.value.title,
    title: page.value.title,
    description: page.value.description,
    twitterDescription: page.value.description,
    twitterCard: "summary_large_image",
  });
}
</script>

<style scoped>
aside {
  overflow-y: scroll;
  height: 100%;
}
</style>

<template>
  <div class="container mx-auto p-5">
    <div class="flex flex-row flex-wrap py-4">
      <Disclosure
        as="nav"
        class="w-full sm:w-1/3 md:w-1/4 px-2"
        v-slot="{ open }"
      >
        <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
          <div class="relative flex h-16 items-center justify-between">
            <div class="absolute inset-y-0 left-0 flex items-center sm:hidden">
              <!-- Mobile menu button-->
              <div class="flex flex-row gap-3">
                <DisclosureButton
                  class="inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white"
                >
                  <span class="sr-only">Open main menu</span>

                  <UIcon
                    name="i-heroicons-bars-4"
                    class="animated-menu-toggler"
                    :style="open ? `transform: rotate(90deg)` : ''"
                    aria-hidden="true"
                  />
                </DisclosureButton>
                <ColourToggle />
                <UButton
                  icon="i-mdi-github"
                  color="gray"
                  variant="ghost"
                  @click="
                    window.open(
                      'https://github.com/mineblock11/fabric-community-wiki',
                      '_blank',
                      'noreferrer'
                    )
                  "
                />
              </div>
            </div>
          </div>
        </div>

        <DisclosurePanel class="sm:hidden">
          <div class="space-y-1 px-2 pb-3 pt-2">
            <div class="sticky top-0 p-4 w-full">
              <SidebarItem v-for="link in dynamicLinks" :info="link" />
            </div>
          </div>
        </DisclosurePanel>

        <div class="max-sm:hidden">
          <div class="space-y-1 px-2 pb-3 pt-2">
            <div class="sticky top-0 p-4 w-full">
              <div class="grid-rows-1 grid-cols-3 py-2">
                <ColourToggle />
                <UButton
                  icon="i-mdi-github"
                  color="gray"
                  variant="ghost"
                  @click="
                    window.open(
                      'https://github.com/mineblock11/fabric-community-wiki',
                      '_blank',
                      'noreferrer'
                    )
                  "
                />
              </div>
              <SidebarItem v-for="link in dynamicLinks" :info="link" />
            </div>
          </div>
        </div>
      </Disclosure>
      <main role="main" class="w-full sm:w-2/3 md:w-3/4 pt-1 px-2">
        <UContainer><slot /></UContainer>
      </main>
    </div>
  </div>
</template>

<style>
.animated-menu-toggler {
  transition: all 250ms ease-in-out;
}
</style>
