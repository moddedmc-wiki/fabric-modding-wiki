<template>
  <CardGrid>
    <template #title />
    <Card v-for="page in orderedArticles" :key="page.title">
      <template #title>
        {{ page.title }}
      </template>
      <template #description>
        {{ page.description }}
        <br><br>
        <ButtonLink :href="page._path.replace('/_dir', '')">
          Read More →
        </ButtonLink>
      </template>
    </Card>
  </CardGrid>
</template>

<script>
import * as _ from "lodash-es";

export default {
  data: () => {
    return {
      articles: [],
    };
  },
  computed: {
    orderedArticles: function () {
      return _.orderBy(this.articles, "_id");
    },
  },
  async beforeMount() {
    const navigation = await fetchContentNavigation();
    const { page } = useContent();
    
    const path = page.value._path.split("/");

    path.splice(0, 1);

    const isArchive = path.length > 1;

    console.log(path)

    let nav;
    if(isArchive) {
      const v = path[0];
      nav = navigation.filter((nav) => nav._path === "/" + v)[0];
      nav = nav.children.filter((nav) => nav._path === page.value._path)[0];
    } else {
      nav = navigation.filter((nav) => nav._path === page.value._path)[0];
    }
    nav.children.forEach(async (page) => {
      const article = await queryContent(nav._path.replace("/", ""))
        .where({ _path: page._path })
        .findOne();
      if (!article.hide && !article.navigation) {
        this.articles.push(article);
      }
    });

    this.orderedArticles.forEach(article => {
      console.log(```
      ::card
      #title
      ${article.title}
      #description
      ${article.description}
      ::button-link{href=${article._path.replace('/_dir', '')}}
      Read More →
      ::
      ::
      ```)
    })
  },
};
</script>
