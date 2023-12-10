---
# https://vitepress.dev/reference/default-theme-home-page
layout: home

hero:
  name: "Fabric Modding Wiki"
  tagline: "An open source guide-book for creating mods using Fabric."
  actions:
    - theme: brand
      text: Getting Started
      link: /getting-started/
    - theme: alt
      text: Contribute
      link: /contributing

features:
  - title: Items
    details: "Discover how to register a simple item and how to texture, model and name it."
    link: /items/
  - title: Blocks
    details: "Learn how to register a simple block and block item, texture and model it."
    link: /blocks/
  - title: Data Generation
    details: "Find out how you can setup your project to utilize Fabric API's data generation helpers."
    link: /data-generation/
  - title: Events
    details: "Learn how to use Fabric API's event system."
    link: /events/
  - title: Rendering
    details: Learn the basics of rendering in Minecraft, both 3D and 2D.
    link: /rendering/
  - title: Sounds
    details: Learn how to utilize Minecraft's sound systems to add and play your own sounds.
    link: /sounds/
---

<style scoped>
.container {
  margin: auto;
  width: 100%;
  max-width: 1280px;
  padding: 0 24px;
  padding-top: 48px !important;
}

@media (min-width: 640px) {
  .container {
    padding: 0 48px;
  }
}

@media (min-width: 960px) {
  .container {
    width: 100%;
    padding: 0 64px;
  }
}
</style>

<script setup>
import {
  VPTeamMembers
} from 'vitepress/theme'

const memberData = [{
  name: "JR1811",
  twitter: "ShiroJR1811",
  mastodon: "@shirojr@mastodon.social",
  website: "https://jr1811.github.io"
}, {
  name: "0x3C50"
}, {
  name: "Friendly-Banana"
}, {
  name: "enjarai",
  website: "https://enjarai.dev",
  discord: "https://discord.gg/WcYsDDQtyR"
}, {
  name: "imb11",
  website: "https://imb11.dev/",
  discord: "https://discord.imb11.dev/"
}, {
  name: "Superkat32",
}, {
  name: "oliviathevampire",
  mastodon: "@oliviathevampire@mastodon.social"
}]

const members = memberData.map((data) => {
    const links = [];

    if(data.twitter) links.push({
      icon: "twitter",
      link: `https://twitter.com/${data.twitter}`,
      target: '_blank',
      rel: 'sponsored'
    });

    if(data.mastodon) {
      const mastodon_domain = data.mastodon.split("@")[1];
      const mastodon_username = "@" + data.mastodon.split("@")[0];

      links.push({
        icon: "mastodon",
        link: `https://${mastodon_domain}/${mastodon_username}`,
        target: '_blank',
      rel: 'sponsored'
      });
    }

    if(data.website) links.push({
      icon: {
        svg: `<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
  <circle cx="12" cy="12" r="10"></circle>
  <line x1="2" y1="12" x2="22" y2="12"></line>
  <path fill="none" d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path>
</svg>
`
      },
      link: data.website,
      target: '_blank',
      rel: 'sponsored'
    });

    if(data.discord) links.push({
      icon: "discord",
      link: data.discord,
      target: '_blank',
      rel: 'sponsored'
    });

    links.push({
      icon: "github",
      link: `https://github.com/${data.name}`,
      target: '_blank',
      rel: 'sponsored'
    });

    return {
      name: data.name,
      avatar: `https://github.com/${data.name}.png?size=120`,
      links: links,
      target: '_blank',
      rel: 'sponsored'
    }
});
</script>

<div class="vp-doc container">

::: info
These pages have been tested and verified to work with Minecraft `1.20.4`, if you encounter any issues, please report them on the [Discord](https://discord.gg/5tmestARuU) or [GitHub](https://github.com/moddedmc-wiki/fabric-modding-wiki).
:::

<br />

# Writers

These people have written the many guides and resources on this website, you should go and support them! If you want to contribute guides and resources, check out the [Contributing](/contributing) page.

<VPTeamMembers
    :members="members"
/>

</div>
