<template>
  <div class="message owner">
    <div class="messageContent">
      <p>hello</p>
    </div>
  </div>
</template>

<style scoped>
.message{
  display: flex;
  gap: 20px;
  &.owner{
    flex-direction: row-reverse;
    .messageContent {
      align-items: flex-end;
      p {
        background-color: dodgerblue;
        color: white;
        border-radius: 10px 0px 10px 10px;
      }
    }
  }
}
.messageContent {
  max-width: 80%;
  display: flex;
  flex-direction: column;
  gap: 10px;
  p{
    background-color: white;
    padding: 10px 20px;
    border-radius: 0px 10px 10px 10px;
  }


}
</style>
