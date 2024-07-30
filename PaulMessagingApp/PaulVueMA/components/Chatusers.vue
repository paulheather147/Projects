<template>
  <div class="chatusers">
    <div class="userChatInfo">
      <span>Jane</span>
      <p>Hello</p>
    </div>
    <div class="userChatInfo">
      <span>Jane</span>
      <p>Hello</p>
    </div>
    <div class="userChatInfo">
      <span>Jane</span>
      <p>Hello</p>
    </div>
    <div class="userChatInfo">
      <span>Jane</span>
      <p>Hello</p>
    </div>
  </div>
</template>

<style scoped>
.userChatInfo{
  padding: 10px;
  display: flex;
  align-items: center;
  gap: 10px;
  color: white;
  cursor: pointer;
  border-bottom: 1px solid grey;
  &:hover{background-color: #943917}
  span{font-size: 18px; font-weight: 500}
  p{font-size: 10px; color: lightgrey}
}
</style>

<script setup>
</script>