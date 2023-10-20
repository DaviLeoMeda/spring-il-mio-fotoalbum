
<template>
  <main>
    <h1>
      Photos
    </h1>
    <ul>
      <li v-for="picture in pictures" :key="picture.id">
        {picture.title}
      </li>
    </ul>
  </main>
</template>


<script setup>
import axios from 'axios';
import { onMounted } from 'vue';


const API_URL = "localhost:8080/api/v1.0"

const pictures = ref(null);


onMounted(() => {

  axios.get(API_URL + "/pictures")
    .then(res => {

      const data = res.data;
      pictures.value = data;
    })
    .catch(err => console.error(err));
});
</script>