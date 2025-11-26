import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router';

import App from './App.vue'
import Mainview from './components/layouts/mainview.vue';
import FormVagas from './components/forms/formVagas.vue';
import Buscarvagas from './components/layouts/buscarvagas.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
    { path: '/', name: 'home', component: Mainview },
    { path: '/form-vagas/:msg', name: 'formVagas', component: FormVagas, props: true  },
    { path: '/buscar-vagas/:msg', name: 'buscarVagas', component: Buscarvagas, props: true  }
    ]
});


const app = createApp(App);
app.use(router);
app.mount('#app')
