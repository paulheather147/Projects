<template>
  <div class="typeinput">
    <input type="text" placeholder="Type here...">
    <button>Send</button>
  </div>
</template>

<style scoped>
.typeinput{
  height: 50px;
  background-color: white;
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  input {
    width: 100%;
    border: none;
    outline: none;
    font-size: 18px;

    &::placeholder {
      color: rebeccapurple;
    }
  }
  button{
    border: none;
    padding: 10px 15px;
    color: white;
    background-color: rebeccapurple;
    cursor: pointer;
  }
}

</style>
