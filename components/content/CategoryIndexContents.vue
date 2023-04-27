<script setup>
import { ref } from 'vue'

const { page } = useContent();

const navigation = await fetchContentNavigation();

const articles = ref([]);

console.log(page.value)

const nav = navigation.filter(nav => nav.title === page.value.title)[0];

nav.children.forEach(async page => {
    const article = await queryContent(nav._path.replace('/', '')).where({title: page.title}).findOne();
    if(!article.hide) {
        articles.value.push(article);
    }
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