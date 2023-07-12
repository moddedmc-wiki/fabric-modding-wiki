// https://v3.nuxtjs.org/api/configuration/nuxt.config
import tailwindTypography from '@tailwindcss/typography';
export default defineNuxtConfig({
  modules: ['@nuxt/content', '@nuxthq/ui'],
  tailwindcss: {
    config: {
      plugins: [tailwindTypography],
    }
  },
  content: {
    highlight: {
      theme: {
        dark: 'github-dark',
        light: 'github-light'
      }
    },
    documentDriven: true
  },
  ui: {
    icons: ['mdi', 'heroicons']
  },
})
