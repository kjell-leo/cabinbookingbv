<script setup>
import { ref } from "vue";
import { useAuthStore } from "../stores/auth";
import { useRouter } from "vue-router";

const authStore = useAuthStore();
const router = useRouter();
const username = ref("");
const password = ref("");
const error = ref("");

const handleLogin = async () => {
  try {
    await authStore.login(username.value, password.value);
    router.push("/admin");
  } catch (err) {
    error.value = "Username or password is incorrect. Please try again.";
  }
};
</script>

<template>
  <div>
    <h1>CozyCabins</h1>
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><RouterLink to="/">Home</RouterLink></li>
        <li class="breadcrumb-item active" aria-current="page">Login</li>
      </ol>
    </nav>
    <div v-if="error" class="alert alert-danger" role="alert">
      {{ error }}
    </div>
    <form @submit.prevent="handleLogin">
      <div class="mb-3">
        <label for="inputUsername" class="form-label">Username</label>
        <input v-model="username" type="text" class="form-control" id="inputUsername" placeholder="Enter username" required>
      </div>
      <div class="mb-3">
        <label for="inputPassword" class="form-label">Password</label>
        <input v-model="password" type="password" class="form-control" id="inputPassword" placeholder="Enter password" required>
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
  </div>
</template>

  