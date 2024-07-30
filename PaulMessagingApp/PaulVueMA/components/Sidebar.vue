<template>
  <div class="sidebar">
    <div class="navbar">
      <Navbar/>
    </div>
    <div class="search">
    <Search/>
    </div>
    <div class="chats">
      <Chats/>
    </div>
  </div>
</template>

<style scoped>
.sidebar {
  display: flex;
  flex-direction: column;
  border-right: 1px solid green;
  background-color: orangered;
  height: 100%;
}
.navbar{
  width: 100%;
}
.search{
  width: 100%;
}


</style>
<script setup>
import Navbar from "./Navbar.vue";
import Search from "./Search.vue";
import Chats from "./Chatusers.vue";
</script>