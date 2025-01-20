import { defineStore } from "pinia";
import axios from "axios";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    username: localStorage.getItem("username") || "",
    password: localStorage.getItem("password") || "",
    isAuthenticated: !!localStorage.getItem("username"),
  }),
  getters: {
    authHeader(state) {
      const credentials = `${this.username}:${state.password}`
      return `Basic ${btoa(credentials)}`
    },
  },
  actions: {
    async login(username, password) {
      try {
        const response = await axios.get("http://localhost:8080/api/admin", {
          auth: { username, password }
        });

        this.username = username;
        this.password = password;
        this.isAuthenticated = true;

        localStorage.setItem("username", username);
        localStorage.setItem("password", password);

        return response.data;
      } catch (error) {
        throw new Error("Invalid credentials");
      }
    },

    logout() {
      this.username = "";
      this.password = "";
      this.isAuthenticated = false;

      localStorage.removeItem("username");
      localStorage.removeItem("password");
    }
  }
});
