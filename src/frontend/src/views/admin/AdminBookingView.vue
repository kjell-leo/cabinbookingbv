<script setup>
  import { useAuthStore } from "../../stores/auth";
  import {ref, onMounted } from 'vue'
  import axios from 'axios'
  import { useRoute, useRouter } from 'vue-router'

  const authStore = useAuthStore();
  const router = useRouter();
  const booking = ref(null)
  const route = useRoute()
  const bookingId = route.params.bookingId
  const cabinId = route.params.cabinId

  onMounted(() => {
    axios.get(`http://localhost:8080/api/admin/booking/${bookingId}`, {
      auth: { username: "admin", password: "admin" }
    })
    .then(response => {
      booking.value = response.data
    })
    .catch(error => {
      console.error('Something went wrong fetching the cabin!', error)
    })
  })

  const deleteBooking = (bookingId) => {
    axios.delete(`http://localhost:8080/api/admin/booking/${bookingId}`, {
      auth: { username: "admin", password: "admin" }
    })
    .then(() => {
      console.log()
      router.push(`/admin/${cabinId}`);
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
      <li class="breadcrumb-item"><RouterLink :to="`/admin/${cabinId}`">Cabin bookings</RouterLink></li>
      <li class="breadcrumb-item active" aria-current="page">Booking</li>
    </ol>
  </nav>

  <div class="col-sm-6 mb-4">
    <div class="card">
      <div v-if="booking" class="card-body">
        <p class="card-text">Reference: {{ booking.id }}</p>
        <p class="card-text">Name: {{ booking.customerName }}</p>
        <p class="card-text">Surename: {{ booking.customerSurename }}</p>
        <p class="card-text">Phone Number: {{ booking.phoneNumber }}</p>
        <p class="card-text">Email: {{ booking.email }}</p>
        <p class="card-text">Date From: {{ booking.dateFrom }}</p>
        <p class="card-text">Date To: {{ booking.dateTo }}</p>
        <Button @click="deleteBooking(booking.id)" class="btn btn-danger">Delete</Button>
      </div>
    </div>
  </div>

  
</template>
