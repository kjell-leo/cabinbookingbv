<script setup>
  import {ref, onMounted } from 'vue'
  import axios from 'axios'
  import { useRoute } from 'vue-router'

  const cabin = ref(null)
  const bookedDates = ref([])
  const bookingRef = ref(null)
  const date = ref(null);
  const route = useRoute()
  const cabinId = route.params.cabinId
  const booking = ref({
    customerName: '',
    customerSurename: '',
    phoneNumber: '',
    email: '',
    dateFrom: '',
    dateTo: '',
    cabin: {
      id: cabinId
    }
  })

  const fetchCabin = () => {
    axios.get(`http://localhost:8080/api/cabin/${cabinId}`)
    .then(response => {
      cabin.value = response.data
      setBookedDates(response.data)
    })
    .catch(error => {
      console.error('Something went wrong fetching the cabin!', error)
    })
  } 

  const setBookedDates = (cabin) => {
    if(!cabin?.bookings) return;

    cabin.bookings.forEach(booking => {
      let currentDate = new Date(booking.dateFrom);
        const endDate = new Date(booking.dateTo);

        while (currentDate <= endDate) {
            bookedDates.value.push(new Date(currentDate)); // Clone date to avoid mutation
            currentDate.setDate(currentDate.getDate() + 1);
        }
    });
  }

  onMounted(fetchCabin)

  const createBooking = () => {
    booking.value.dateFrom = date.value[0]
    booking.value.dateTo = date.value[1]
    axios.post('http://localhost:8080/api/booking', booking.value)
    .then(response => {
      bookingRef.value = response.data.id
      fetchCabin()
      date.value = null
      booking.value = {
        customerName: '',
        customerSurename: '',
        phoneNumber: '',
        email: '',
        dateFrom: '',
        dateTo: '',
        cabin: {
          id: cabinId
        }
      }
    })
    .catch(error => {
      console.error('Something went wrong fetching the cabin!', error)
    })
  }
</script>

<template>
  <h1>CozyCabins</h1>

  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><RouterLink to="/">Home</RouterLink></li>
      <li class="breadcrumb-item active" aria-current="page">Booking</li>
    </ol>
  </nav>

  <div v-if="bookingRef" class="alert alert-success" role="alert">
    Booking confirmed. Your booking reference is: {{ bookingRef }}
  </div>

  <p>To make a booking, please fill out the contact form below with your details. This will help us process your request and confirm your booking as soon as possible.</p>
  
  <div class="col-sm-6 mb-4">
    <div class="card">
      <div v-if="cabin" class="card-body">
        <h5 class="card-title">{{ cabin.name }}</h5>
        <h6 class="card-subtitle mb-2 text-body-secondary">{{ cabin.location }}</h6>
        <p class="card-text">{{ cabin.description }}</p>
      </div>
    </div>
  </div>

  <div class="mb-3">
    <label for="inputDate" class="form-label">When are you planning to stay?</label>
    <VueDatePicker v-model="date" :enable-time-picker="false" range :disabled-dates="bookedDates" id="inputDate" />
  </div>

  <form v-if="date" @submit.prevent="createBooking">
    <div class="mb-3">
      <label for="inputName" class="form-label">Name</label>
      <input v-model="booking.customerName" type="text" class="form-control" id="inputName" placeholder="Enter name" required>
    </div>
    <div class="mb-3">
      <label for="inputSurename" class="form-label">Surename</label>
      <input v-model="booking.customerSurename" type="text" class="form-control" id="inputSurename" placeholder="Enter surename" required>
    </div>
    <div class="mb-3">
        <label for="inputPhone">Phone Number</label>
        <input v-model="booking.phoneNumber" type="tel" class="form-control" id="inputPhone" placeholder="Enter phone number" required>
    </div>
    <div class="mb-3">
        <label for="inputEmail">Email</label>
        <input v-model="booking.email" type="email" class="form-control" id="inputEmail" placeholder="Enter email" required>
    </div>

    <button type="submit" class="btn btn-primary">Book</button>
  </form>
</template>
