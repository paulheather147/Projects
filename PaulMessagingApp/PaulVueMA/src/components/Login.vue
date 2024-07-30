<template>
  <div>
    <h1>Login Page</h1>
    <input v-model="email" type="text" placeholder="Email" />
    <br />
    <input v-model="password" type="password" placeholder="Password" />
    <br />
    <button @click="loginUser">Log In</button>
  </div>
</template>

<script>
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";
import { app } from "../api/firebase";
import {setCurrentUser} from "@/userStore.js";
import { getFirestore, doc, getDoc } from "firebase/firestore";
export default {
  name: "Login",
  data() {
    return {
      displayName: "",
      email: "",
      password: "",
      uid: ""
    };
  },
  methods: {
  async loginUser() {
    try {
      const auth = getAuth(app);
      const userCredential = await signInWithEmailAndPassword(auth, this.email, this.password);
      const user = userCredential.user;

      // Fetch additional user details from Firestore
      const db = getFirestore(app);
      const userDoc = await getDoc(doc(db, "users", user.uid));

      if (userDoc.exists()) {
        const userData = userDoc.data();
        setCurrentUser({
          displayName: userData.displayName,
          email: user.email,
          uid: user.uid
        });
      } else {
        console.error("No such user document!");
      }

      this.$router.push('/chat');
    } catch (error) {
      console.error("Error logging in:", error.code, error.message);
    }
  }
}
};

</script>

<style scoped>
button {
  padding: 5px;
  margin: 10px;
}
div {
  border: dashed black 1px;
  padding: 20px;
  margin: 10px;
  display: inline-block;
}
</style>

