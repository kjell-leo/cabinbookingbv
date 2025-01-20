<script setup>
  import {ref, onMounted } from 'vue'
  import axios from 'axios'

  const cabins = ref([])

  onMounted(() =>{
    axios.get('http://localhost:8080/api/cabin')
    .then(response => {
      cabins.value = response.data
    })
    .catch(error =>{
      console.error('Something went wrong fetching cabins!', error)
    })
  })
</script>

<template>
  <h1>CozyCabins</h1>
  <img src="/cabin.jpg" class="img-fluid mb-3" alt="cabin">
  <p>Welcome to CozyCabins – Your Perfect Escape</p>
  <p>Discover charming cabins surrounded by nature, perfect for relaxation and adventure. Wake up to peaceful views, unwind by the fire, and enjoy the cozy comforts of a home away from home. Whether it’s a romantic retreat or a family getaway, your perfect stay awaits.</p>
  <p>Book your escape today!</p>
  <h2>Available cabins</h2>
  <div class="row">
    <div v-for="(cabin, index) in cabins" :key="cabin.id" class="col-sm-6 mb-4">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">{{ cabin.name }}</h5>
          <h6 class="card-subtitle mb-2 text-body-secondary">{{ cabin.location }}</h6>
          <p class="card-text">{{ cabin.description }}</p>
          <RouterLink :to="'/' + cabin.id" class="btn btn-primary">Book</RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>
