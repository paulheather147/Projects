<template>
  <div class="navbar">
    <span class="logo">Chat App</span>
    <div class="user">
      <span>{{currentUser.displayName}}</span>
    </div>
  </div>
</template>



<script >
import {getCurrentUser, userStore} from "@/userStore.js";

export default {
  setup() {
    const currentUser = getCurrentUser(() => userStore.currentUser);
    return {
      currentUser
    };
  }
};
</script>

<style scoped>
.navbar {
  display: flex;
  align-items: center;
  background-color: darkorange;
  height: 50px;
  padding: 10px;
  justify-content: space-between;
}
.logo{
  font-weight: bold;
  color: white;
}
.user{
  display: flex;
  gap: 10px;
  padding: 20px;
  color: white;

}

</style>