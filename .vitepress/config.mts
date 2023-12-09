import { defineConfig } from 'vitepress'
import { generateSidebar } from 'vitepress-sidebar'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Fabric Modding Wiki",
  description: "An open source guide-book for creating mods using Fabric.",
  sitemap: {
    hostname: 'https://fabric.moddedmc.wiki/'
  },
  mpa: true,
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
      { text: 'Getting Started', link: '/getting-started/' },
      { text: 'Contribute', link: '/contributing' }
    ],

    sidebar: generateSidebar([
      {
        documentRootPath: '/',
        useTitleFromFileHeading: true,
        scanStartPath: "/getting-started/",
        resolvePath: "/getting-started/"
        sortMenusByFrontmatterOrder: true,
        hyphenToSpace: true,
        capitalizeEachWords: true,
        collapseDepth: 10,
        includeFolderIndexFile: true,
        useFolderLinkFromIndexFile: true,
        includeRootIndexFile: true,
        rootGroupText: 'Getting Started',
        rootGroupCollapsed: false
      }
    ]),

    socialLinks: [
      { icon: 'github', link: 'https://github.com/moddedmc-wiki/fabric-modding-wiki' }
    ]
  }
})
