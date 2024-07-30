// firebase.js
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";

const firebaseConfig = {
    apiKey: "AIzaSyCg82fi33RjgHBQn9vsz1IfQca1jf3vmTY",
    authDomain: "messagingapp-cdefe.firebaseapp.com",
    projectId: "messagingapp-cdefe",
    storageBucket: "messagingapp-cdefe.appspot.com",
    messagingSenderId: "618668618873",
    appId: "1:618668618873:web:9121ff2f94dcb9dc3b10a8",
    measurementId: "G-R9J34B08KG"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getFirestore();

export { app, auth, db };


