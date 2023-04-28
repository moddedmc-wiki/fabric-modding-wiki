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
        {{ article._dir.title }}
      </template>
      <template #description>
        {{ article._dir.description }}
        <br><br>
        <ButtonLink :href="`${version === latest ? '' : '/' + version}` + article._path.replace('/_dir', '')">
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
      latest: "1.19.4",
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
      const url = this.version === this.latest ? '/' : `/${this.version}/`;
      const article = await queryContent(url)
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
