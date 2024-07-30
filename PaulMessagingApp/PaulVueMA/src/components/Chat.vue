<template>
  <div class="chat" >
    <div class="container">
      <Sidebar class="sidebar"/>
      <Messageinfo class="messageinfo"/>
    </div>
  </div>
</template>
<script>
import Sidebar from "../../components/Sidebar.vue";
import Messageinfo from "../../components/Messageinfo.vue";
import { getCurrentUser } from '@/userStore';

export default {
  name: "Chat",
  components: {
    Messageinfo,
    Sidebar},
};

</script>

<style>
.chat{
  background-color: lightblue;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
.container{
  border: 1px solid white;
  border-radius: 10px;
  width: 65%;
  height: 80%;
  display: flex;
  overflow: hidden;

  .sidebar{
    flex: 1;

  }
  .messageinfo{
    flex:2;
  }

}


</style>