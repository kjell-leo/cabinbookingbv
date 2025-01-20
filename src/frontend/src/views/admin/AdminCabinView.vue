<script setup>
  import { useAuthStore } from "../../stores/auth";
  import {ref, onMounted } from 'vue'
  import axios from 'axios'
  import { useRoute, useRouter } from 'vue-router'

  const authStore = useAuthStore();
  const router = useRouter();
  const cabin = ref(null)
  const route = useRoute()
  const cabinId = route.params.cabinId

  onMounted(() => {
    axios.get(`http://localhost:8080/api/cabin/${cabinId}`)
    .then(response => {
      cabin.value = response.data
    })
    .catch(error => {
      console.error('Something went wrong fetching the cabin!', error)
    })
  })

  const deleteBooking = (bookingId, index) => {
    axios.delete(`http://localhost:8080/api/admin/booking/${bookingId}`, {
      auth: { username: "admin", password: "admin" }
    })
    .then(() => {
      cabin.value.bookings.splice(index, 1)
    })
    .catch(error => {
      console.error('Something went wrong fetching the cabin!', error)
    })
  }

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
      <li class="breadcrumb-item"><RouterLink to="/admin">Admin</RouterLink></li>
      <li class="breadcrumb-item active" aria-current="page">Cabin bookings</li>
    </ol>
  </nav>

  <div class="col-sm-6 mb-4">
    <div class="card">
      <div v-if="cabin" class="card-body">
        <h5 class="card-title">{{ cabin.name }}</h5>
        <h6 class="card-subtitle mb-2 text-body-secondary">{{ cabin.location }}</h6>
        <p class="card-text">{{ cabin.description }}</p>
      </div>
    </div>
  </div>
  <table class="table">
    <thead>
      <tr>
        <th scope="col">Reference</th>
        <th scope="col">Name</th>
        <th scope="col">Date From</th>
        <th scope="col">Date To</th>
        <th class="text-end" scope="col">Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(booking, index) in cabin?.bookings" :key="cabin?.bookings.id">
        <th scope="row">{{ booking.id }}</th>
        <td>{{ booking.customerName }}</td>
        <td>{{ booking.dateFrom }}</td>
        <td>{{ booking.dateTo }}</td>
        <td class="text-end">
          <RouterLink :to="'/admin/' + cabin.id + '/' + booking.id" class="btn btn-primary">More info</RouterLink>
          <Button @click="deleteBooking(booking.id, index)" class="btn btn-danger ms-2">Delete</Button>
        </td>
      </tr>
    </tbody>
  </table>
</template>
