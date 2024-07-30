import { createRouter, createWebHistory } from 'vue-router';
import Home from './components/Home.vue';
import Login from './components/Login.vue';
import Signup from './components/Signup.vue';
import Chat from "@/components/Chat.vue";

const routes = [
    { path: '/', component: Home },
    { path: '/login', component: Login },
    { path: '/signup', component: Signup },
    { path: '/chat', component: Chat },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;