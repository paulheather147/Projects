import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { collection, addDoc, doc, setDoc } from "firebase/firestore";
import { db } from "./api/firebase";

createApp(App).use(router).mount('#app');

export async function addUser(userData) {
    try {
        await setDoc(doc(db, "users", userData.uid), userData);
        console.log("Document written with ID: ", userData.uid);
    } catch (e) {
        console.error("Error adding document: ", e);
    }
}

export async function createUserChats(userData){
    try {
        await setDoc(doc(db, "userChats", userData.uid), {});
        console.log("userChats document created with ID: ", userData.uid);
    }catch (e) {
            console.error("Error adding document: ", e);
    }
}




