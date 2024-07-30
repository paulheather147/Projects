<template>
  <div class="messagebox">
    <Message/>
    <Message/>

  </div>
</template>

<style scoped>
.messagebox{
  background-color: mediumpurple;
  padding: 10px;
  align-items: center;
  overflow-y: auto;
  height: calc(100% - 160px);
}
</style>

<script setup>
import Message from "./Message.vue";
</script>

