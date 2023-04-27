export default defineAppConfig({
  docus: {
    title: 'Fabric Community Wiki',
    description: 'An open source guide-book for creating mods using Fabric.',
    image: '/logo.png',
    url: 'https://fabric.mineblock11.dev/',
    socials: {
      discord: {
        label: 'Discord',
        icon: 'simple-icons:discord',
        href: 'https://discord.gg/UzHtJKqHny'
      },
      github: 'mineblock11/fabric-community-wiki'
    },
    aside: {
      level: 0,
      exclude: []
    },
    header: {
      fluid: true,
    },
    footer: {
      credits: false,
      iconLinks: [
        {
          href: 'https://nuxt.com',
          icon: 'simple-icons:nuxtdotjs'
        }
      ]
    }
  }
})
