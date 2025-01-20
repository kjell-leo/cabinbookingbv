<script setup>
  import { useAuthStore } from "../../stores/auth";
  import { useRouter } from "vue-router";
  import {ref, onMounted } from 'vue'
  import axios from 'axios'

  const authStore = useAuthStore();
  const router = useRouter();
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

  const logout = () => { 
    authStore.logout();
    router.push("/login");
  }
</script>

<template>
  <div class="d-flex justify-content-between align-items-center">
    <h1 class="align-self-center">CozyCabins Admin</h1>
    <Button @click="logout()" class="align-self-center btn btn-danger">Logout</Button>
  </div>

  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><RouterLink to="/">Home</RouterLink></li>
      <li class="breadcrumb-item active" aria-current="page">Admin</li>
    </ol>
  </nav>

  <h2>Cabins</h2>

  <table class="table">
    <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Location</th>
        <th scope="col">#Bookings</th>
        <th class="text-end" scope="col">Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(cabin, index) in cabins" :key="cabin.id">
        <th scope="row">{{ cabin.name }}</th>
        <td>{{ cabin.location }}</td>
        <td>{{ cabin.bookings.length }}</td>
        <td class="text-end">
          <RouterLink :to="'/admin/' + cabin.id" :aria-disabled="cabin.bookings.length <= 0" class="btn btn-primary" :class="cabin.bookings.length <= 0 ? 'disabled' : ''">See bookings</RouterLink>
        </td>
      </tr>
    </tbody>
  </table>
</template>
