<template>
  <div class="messageinfo">
    <div class="messageinfoinfo">
      <span id="nameTag">Jane</span>
    </div>
      <Messagebox/>

    <div class="typeinput">
      <Typeinput/>
    </div>
  </div>
</template>

<style scoped>
  .messageinfo{
    background-color: purple;
  }
  .messageinfoinfo{
    height: 50px;
    background-color: rebeccapurple;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 10px;
    color: lightgray;
    span{font-size: 18px; color: white}
  }

  .typeinput{
    align-items: center;
  }
</style>


<script setup>
import Messagebox from "./Messagebox.vue";
import Typeinput from "./Typeinput.vue";
</script>
