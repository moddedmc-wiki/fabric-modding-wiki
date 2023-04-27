<script setup>
import { ref } from 'vue'

const navigation = await fetchContentNavigation();

const articles = ref([]);

navigation[0].children.forEach(async page => {
    const article = await queryContent(navigation[0]._path.replace('/', '')).where({title: page.title, hide: undefined}).findOne();
    articles.value.push(article);
});

console.log(articles.value);
</script>

<template>
  <CardGrid>
    <template #title></template>
    <Card v-for="page in articles" v-bind:key="page.title">
      <template #title>
        {{ page.title }}
      </template>
      <template #description>
        {{ page.description }}
        <br /><br />
        <ButtonLink color="black" :href="page._path"> Read More → </ButtonLink>
      </template>
    </Card>
  </CardGrid>
</template>