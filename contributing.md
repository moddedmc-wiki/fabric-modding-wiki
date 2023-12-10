---
title: Contributing
---

# Contributing

If you wish to contribute to the wiki, you are more than welcome!

## Content Guidelines

1. **Future-proof Content**: All contributed content should be easily updatable to accommodate future versions of Minecraft. Avoid using version-specific features or APIs that might become outdated quickly.

2. **Clear and Cohesive Writing**: Ensure that the content is easy to understand and follows a logical flow. While it doesn't have to be strictly step-by-step, it should present information in a coherent manner, allowing readers to follow along smoothly.

3. **Latest Minecraft Version**: All content should be based on the latest version of Minecraft. This helps maintain relevance and provides accurate information to users.

4. **Fabric API Usage**: If your contribution relies on the Fabric API, explicitly mention it in your content. You can do this either briefly, like "This page utilizes Fabric API's Y module," or directly state, "This page uses Fabric API."

## Contributing Process

1. **Fork the Repository**: To contribute, start by forking the Fabric Modding Wiki repository on GitHub. This will create a copy of the project in your GitHub account.

2. **Make Changes**: Create a new branch in your forked repository and make the necessary changes to the content. Ensure that your changes adhere to the contribution guidelines mentioned above.

3. **Test Your Changes**: If your contribution involves code snippets, test it thoroughly to confirm that it works as intended in the latest version of Minecraft.

4. **Create a Pull Request (PR)**: Once you are satisfied with your changes, open a Pull Request from your branch to the main repository's `master` branch. Provide a clear and concise description of your changes in the PR to help reviewers understand your contribution better.

5. **Review Process**: Your PR will be reviewed by the community members or the maintainers of the Fabric Modding Wiki. Be prepared to make further changes based on their feedback or suggestions.

6. **Merge and Publication**: If your PR meets the contribution guidelines and aligns with the objectives of the Fabric Modding Wiki, it will be approved and merged into the main repository.

If you have any questions or need further assistance, don't hesitate to reach out to us on GitHub or via Discord.

## Markdown Extension Examples

This section demonstrates some of the built-in markdown extensions provided by VitePress.

### Syntax Highlighting

VitePress provides Syntax Highlighting powered by [Shikiji](https://github.com/antfu/shikiji), with additional features like line-highlighting:

**Input**

````md
```js{4}
export default {
  data () {
    return {
      msg: 'Highlighted!'
    }
  }
}
```
````

**Output**

```js{4}
export default {
  data () {
    return {
      msg: 'Highlighted!'
    }
  }
}
```

### Custom Containers

**Input**

```md
::: info
This is an info box.
:::

::: tip
This is a tip.
:::

::: warning
This is a warning.
:::

::: danger
This is a dangerous warning.
:::

::: details
This is a details block.
:::
```

**Output**

::: info
This is an info box.
:::

::: tip
This is a tip.
:::

::: warning
This is a warning.
:::

::: danger
This is a dangerous warning.
:::

::: details
This is a details block.
:::

### More

Check out the documentation for the [full list of markdown extensions](https://vitepress.dev/guide/markdown).