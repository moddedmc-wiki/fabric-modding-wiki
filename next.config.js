const withNextra = require("nextra")({
  theme: "nextra-theme-docs",
  latex: true,
  themeConfig: "./theme.config.jsx",
});

module.exports = withNextra({
  images: {
    unoptimized: true,
  },
});
