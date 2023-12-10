import { defineConfig } from 'vitepress'

const allCategoriesSection = [
  {
    text: "Guides",
    items: [
      { text: "Getting Started", link: "/getting-started/" },
      { text: "Items", link: "/items/" },
      { text: "Blocks", link: "/blocks/" },
      { text: "Sounds", link: "/sounds/" },
      { text: "Data Generation", link: "/data-generation/" },
      { text: "Events", link: "/events/" },
      { text: "Rendering", link: "/rendering/" }
    ]
  }
]

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Fabric Modding Wiki",
  description: "An open source guide-book for creating mods using Fabric.",
  sitemap: {
    hostname: 'https://fabric.moddedmc.wiki/'
  },
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
      { text: 'Getting Started', link: '/getting-started/' },
      { text: 'Contribute', link: '/contributing' }
    ],

    sidebar: {
      // index.md, creating-project.md, launching-the-game.md, launching-servers.md, access-wideners.md, mixins.md
      '/getting-started/': [
        ...allCategoriesSection,
        {
          text: 'Getting Started',
          items: [
            { text: 'Introduction', link: '/getting-started/' },
            { text: 'Creating a Project', link: '/getting-started/creating-project' },
            { text: 'Launching the Game', link: '/getting-started/launching-the-game' },
            { text: 'Launching Servers', link: '/getting-started/launching-servers' },
            { text: 'Access Wideners', link: '/getting-started/access-wideners' },
            { text: 'Mixins', link: '/getting-started/mixins' }
          ]
        }
      ],
      // index.md, using.md
      '/sounds/': [
        ...allCategoriesSection,
        {
          text: "Sounds",
          items: [
            { text: "Creating Custom Sounds", link: "/sounds/" },
            { text: "Playing SoundEvents", link: "/sounds/using" }
          ]
        }
      ],
      // index.md (Creating Your First Item), food.md, tools.md, armor.md, item-groups.md, interactivity.md
      '/items/': [
        ...allCategoriesSection,
        {
          text: 'Items',
          items: [
            { text: 'Creating Your First Item', link: '/items/' },
            { text: 'Food', link: '/items/food' },
            { text: 'Tools and Weapons', link: '/items/tools' },
            { text: 'Armor', link: '/items/armor' },
            { text: 'Custom Item Groups', link: '/items/item-groups' },
            { text: 'Item Interactivity', link: '/items/interactivity' }
          ]
        }
      ],
      // index.md, blockstates.md, block-entities.md
      '/blocks/': [
        ...allCategoriesSection,
        {
          text: 'Blocks',
          items: [
            { text: 'Creating Your First Block', link: '/blocks/' },
            { text: 'Blockstates', link: '/blocks/blockstates' },
            { text: 'Block Entities', link: '/blocks/block-entities' }
          ]
        }
      ],

      // index.md, loot-tables.md, tags.md, translations.md
      '/data-generation/': [
        ...allCategoriesSection,
        {
          text: "Data Generation",
          items: [
            { text: 'Data Generation Setup', link: '/data-generation/' },
            { text: 'Loot Table Provider', link: '/data-generation/loot-tables' },
            { text: 'Tag Provider', link: '/data-generation/tags' },
            { text: 'Translation Provider', link: '/data-generation/translations' }
          ]
        }
      ],

      // index.md, creation.md
      '/events/': [
        ...allCategoriesSection,
        {
          text: 'Events',
          items: [
            { text: 'Using Custom Events', link: '/events/' },
            { text: 'Creating Custom Events', link: '/events/creation' }
          ]
        }
      ],

      '/rendering/': [
        ...allCategoriesSection,
        {
          text: 'Rendering',
          items: [
            { text: 'Introduction To Rendering', link: '/rendering/' },
            { text: 'Matrices', link: '/rendering/matrices' },
            { text: "Worldspace Rendering", link: '/rendering/world' },
            {
              text: "Particles",
              items: [
                { text: "Creating Custom Particles", link: '/rendering/particles/' },
                { text: "Creating Particle Factories", link: '/rendering/particles/factories' }
              ]
            }
          ]
        }
      ],
    },

    footer: {
      message: 'Not affiliated with Mojang Studios or the Fabric Project.',
      copyright: 'Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International 2023 © Calum H. (IMB11)'
    },

    socialLinks: [
      { icon: 'github', link: 'https://github.com/moddedmc-wiki/fabric-modding-wiki' },
      { icon: 'discord', link: 'https://discord.gg/5tmestARuU' }
    ]
  }
})
