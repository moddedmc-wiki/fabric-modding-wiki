<template>
  <!-- This component is shown on the homepage. -->
  <div class="content">
    <ProseH2 id="jump-right-in">
      Jump right in!
    </ProseH2>
  </div>
  <CardGrid>
    <template #title />
    <Card v-for="article in articles" :key="article.title">
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

<script setup>
const navigation = await fetchContentNavigation();

const articles = ref([]);

navigation.forEach(async page => {
    const article = await queryContent('/').where({title: page.title}).findOne();
    if(!article.hide) {
        articles.value.push(article);
    }
});
</script>
