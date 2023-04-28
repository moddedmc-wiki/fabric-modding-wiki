<template>
  <!-- This component is shown on the homepage. -->
  <div class="content">
    <ProseH2 id="jump-right-in">
      Jump right in!
    </ProseH2>
    <ProseP>
      You're currently looking at content for <ProseCodeInline>{{ version }}</ProseCodeInline>  If you wish to view previous versions, <ProseA href="/archives">
        see this page here.
      </ProseA>
    </ProseP>
  </div>
  <CardGrid>
    <template #title />
    <Card v-for="article in orderedArticles" :key="article.title">
      <template #title>
        {{ article.title }}
      </template>
      <template #description>
        {{ article.description }}
        <br><br>
        <ButtonLink :href="article._path.replace('/_dir', '')">
          Read More →
        </ButtonLink>
      </template>
    </Card>
  </CardGrid>
</template>

<script>
import * as _ from 'lodash-es';


export default {
  props: ["version"],
  data: () => {
    return {
      articles: [],
    };
  },
  computed: {
    orderedArticles: function() {
      return _.orderBy(this.articles, 'index');
    }
  },
  async beforeMount() {
    const navigation = await fetchContentNavigation();

    navigation.forEach(async (page) => {
      const article = await queryContent("/" + this.version.replace("1.19.4", ''))
        .where({ _path: page._path })
        .findOne();
      if (!article.hide) {
        console.log(article)
        this.articles.push(article);
      }
    });
  },
};
</script>
