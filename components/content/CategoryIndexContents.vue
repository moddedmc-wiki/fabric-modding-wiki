<script setup>
import { ref } from 'vue'

const { page } = useContent();

const navigation = await fetchContentNavigation();

const articles = ref([]);

const nav = navigation.filter(nav => nav.title === page.value.title)[0];

nav.children.forEach(async page => {
    const article = await queryContent(nav._path.replace('/', '')).where({title: page.title}).findOne();
    if(!article.hide) {
        articles.value.push(article);
    }
});
</script>

<template>
  <CardGrid>
    <template #title />
    <Card v-for="page in articles" :key="page.title">
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