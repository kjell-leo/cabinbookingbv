import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from "../stores/auth";

import HomeView from '../views/HomeView.vue'
import CabinView from '../views/CabinView.vue'
import AdminHomeView from '../views/admin/AdminHomeView.vue'
import AdminCabinView from '../views/admin/AdminCabinView.vue'
import AdminBookingView from '../views/admin/AdminBookingView.vue'
import LoginView from "../views/LoginView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/:cabinId',
      name: 'cabin',
      component: CabinView,
    },
    {
      path: '/admin',
      name: 'adminhome',
      component: AdminHomeView,
      meta: { requiresAuth: true },
    },
    {
      path: '/admin/:cabinId',
      name: 'admincabin',
      component: AdminCabinView,
      meta: { requiresAuth: true },
    },
    {
      path: '/admin/:cabinId/:bookingId',
      name: 'adminbooking',
      component: AdminBookingView,
      meta: { requiresAuth: true },
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
  ],
})

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next("/login");
  } else {
    next();
  }
});


export default router
