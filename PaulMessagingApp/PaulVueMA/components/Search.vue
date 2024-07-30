<template>
  <div class="search">
    <div class="searchbar">
      <input type="text" placeholder="Search for a user">
    </div>
    <div class="userChatInfo">
      <span>Jane</span>
    </div>
    <div/>
  </div>

</template>

<script>

</script>


<style scoped>
.search{
  border-bottom: 1px solid grey;
}
.searchbar{
  input{
    background-color: transparent;
    border: none;
    color: white;
    outline: none;
    &::placeholder{color: lightgray}
  }
}
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