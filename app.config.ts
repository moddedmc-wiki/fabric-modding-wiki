export default defineAppConfig({
  docus: {
    title: 'Fabric Community Wiki',
    description: 'An open source guide-book for creating mods using Fabric.',
    url: 'https://fabric.mineblock11.dev/',
    image: 'https://repository-images.githubusercontent.com/600823200/20ceea11-87a3-48ff-b34b-9458c718c62d',
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
