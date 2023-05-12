<script setup lang="ts">
import Giscus from "@giscus/vue";
const { page } = useContent();
const { config } = useDocus();
</script>

<template>
  <div v-if="page" class="docs-page-bottom">
    <div v-if="config?.github?.edit" class="edit-link">
      <Icon name="uil:edit" />
      <EditOnLink v-slot="{ url }" :page="page">
        <ProseA :to="url">
          <span> Edit this page on GitHub </span>
        </ProseA>
      </EditOnLink>
    </div>

    <!-- Need to be supported by @nuxt/content -->
    <span v-if="page?.mtime"
      >Updated on
      <b>{{
        new Intl.DateTimeFormat("en-US").format(Date.parse(page.mtime))
      }}</b></span
    >
  </div>
  <Giscus
    id="comments"
    repo="mineblock11/fabric-community-wiki"
    repo-id="R_kgDOI8_VoA"
    category="Page Comments"
    category-id="DIC_kwDOI8_VoM4CWc8B"
    mapping="title"
    reactions-enabled="1"
    strict="1"
    emit-metadata="0"
    input-position="top"
    theme="transparent_dark"
    lang="en"
    loading="lazy"
  />
</template>

<style scoped lang="ts">
css({
  '.docs-page-bottom': {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    flexDirection: 'row',
    gap: '{space.4}',
    marginTop: '{space.8}',
    fontSize: '{fontSize.sm}',
    color: '{color.gray.500}',
    '@dark': {
      color: '{color.gray.400}'
    },
    '.edit-link': {
      flex: 1,
      display: 'flex',
      alignItems: 'center',
      gap: '{space.2}'
    }
  },
  '#comments': {
    marginTop: '{space.8}'
  },
  'iframe': {
    position: 'absolute',
    top: 0,
    left: 0,
    bottom: 0,
    right: 0,
    height: '100%',
    width: '100%'
  }
})
</style>
