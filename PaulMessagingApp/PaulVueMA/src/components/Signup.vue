<template>
  <div>
    <h1>Signup Page</h1>
    <input v-model="displayName" placeholder="Username">
    <br />
    <input v-model="email" type="email" placeholder="Email" />
    <br />
    <input v-model="password" type="password" placeholder="Password" />
    <br />
    <button @click="registerUser">Sign Up</button>
  </div>
</template>

<script>
import { getAuth, createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import { app } from "../api/firebase";
import { addUser, createUserChats } from "@/main.js";

export default {
  name: "Signup",
  data() {
    return {
      displayName: "",
      email: "",
      password: "",
      uid: ""
    };
  },
  methods: {
    registerUser() {
      const auth = getAuth(app);
      createUserWithEmailAndPassword(auth, this.email, this.password)
          .then(userCredential => {
            const user = userCredential.user;
            return updateProfile(user, {
              displayName: this.displayName
            });
          })
          .then(() => {
            const user = auth.currentUser;
            alert("Registered Successfully");
            addUser({
              displayName: user.displayName,
              email: user.email,
              uid: user.uid
            });
            console.log("DISPLAY NAME HERE: " + user.displayName);
            createUserChats(user);
            this.$router.push('/login');
          })
          .catch(error => {
            console.error("Error signing up:", error.code, error.message);
          });
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