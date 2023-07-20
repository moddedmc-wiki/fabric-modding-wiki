import { useRouter } from "next/router";
import { useConfig } from "nextra-theme-docs";

/** @type {import("nextra-theme-docs/.").DocsThemeConfig} */
export default {
  logo: <span>Fabric Community Wiki</span>,
  project: {
    link: "https://github.com/mineblock11/fabric-community-wiki",
  },
  chat: {
    link: "https://discord.gg/5tmestARuU",
  },
  docsRepositoryBase:
    "https://github.com/mineblock11/fabric-community-wiki/blob/latest/",
  head: (
    <>
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <link rel="icon" type="image/png" href="/fabric.png" />
    </>
  ),
  useNextSeoProps: () => {
    const { route } = useRouter();
    const { frontMatter } = useConfig();

    /** @type {import("next-seo").NextSeoProps} */
    let seoProps = {};

    if (route !== "/" || !frontMatter.title) {
      seoProps.title = `${frontMatter.title} - Fabric Community Wiki`;
    } else {
      seoProps.title = "Fabric Community Wiki";
    }

    seoProps.description =
      frontMatter.description ||
      "An open source guide-book for creating mods using Fabric.";

    seoProps.openGraph = {
      images: [
        {
          type: "image/png",
          alt: "Cover Image",
          url: `https://og.mineblock11.dev/fabric-community-wiki?title=${encodeURIComponent(
            frontMatter.title || "Fabric Community Wiki"
          )}&description=${encodeURIComponent(
            frontMatter.description ||
              "An open source guide-book for creating mods using Fabric."
          )}&path=${route || "/"}`,
        },
      ],
    };

    seoProps.twitter = {
      cardType: "summary_large_image",
    };

    return seoProps;
  },
  primaryHue: {
    dark: 24,
    light: 205,
  },
  sidebar: {
    toggleButton: true,
  },
  toc: {
    float: true,
    extraContent: () => {
      return <></>;
    },
  },
  footer: {
    text: (
      <span>
        Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International{" "}
        {new Date().getFullYear()} ©{" "}
        <a href="https://nextra.site" target="_blank">
          mineblock11
        </a>
      </span>
    ),
  },
};
