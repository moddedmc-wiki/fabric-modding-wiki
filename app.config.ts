export default defineAppConfig({
  docus: {
    title: 'Fabric Community Wiki',
    description: 'An open source guidebook and wiki for creating Minecraft Mods using the Fabric Loader.',
    url: 'https://fabric.mineblock11.dev/',
    image: 'https://og.mineblock11.dev/fabric-community-wiki',
    socials: {
      discord: {
        label: 'Discord',
        icon: 'simple-icons:discord',
        href: 'https://discord.gg/UzHtJKqHny'
      },
      github: 'mineblock11/fabric-community-wiki'
    },
    github: {
      dir: 'content',
      edit: true,
      branch: 'docus',
      contributors: true,
      repo: 'fabric-community-wiki',
      owner: 'mineblock11',
    },
    aside: {
      level: 0,
      exclude: []
    },
    header: {
      fluid: true,
    },
    main: {
      padded: true,
      fluid: true
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
