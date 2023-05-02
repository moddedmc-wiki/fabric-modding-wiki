import { createResolver } from '@nuxt/kit'
const { resolve } = createResolver(import.meta.url)
export default defineNuxtConfig({
  extends: '@nuxt-themes/docus',
  css: [
    "@/assets/app.css"
  ],
  nitro: {
    preset: 'service-worker',
    prerender: {
      routes: ['/sitemap.xml'],
    }
  },
  content: {
    sources: {
      '1.19.3': {
        driver: 'fs',
        prefix: '/1.19.3',
        base: resolve('./archive/1.19.3'),
      }
    },
    documentDriven: true,
    highlight: {
      theme: {
        dark: 'github-dark',
        default: 'github-light'
      },
      preload: ['json', 'js', 'ts', 'html', 'css', 'vue', 'diff', 'shell', 'markdown', 'yaml', 'bash', 'ini']
    },
    navigation: {
      fields: ['icon', 'titleTemplate', 'header', 'main', 'aside', 'footer']
    }
  },
})
