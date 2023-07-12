// https://v3.nuxtjs.org/api/configuration/nuxt.config
import tailwindTypography from '@tailwindcss/typography';
export default defineNuxtConfig({
  modules: ['@nuxt/content', '@nuxthq/ui'],
  tailwindcss: {
    config: {
      plugins: [tailwindTypography],
    }
  },
  // e
  content: {
    highlight: {
      theme: {
        dark: 'github-dark'
      }
    },
    documentDriven: true
  },
  ui: {
    icons: ['mdi']
  },
})
