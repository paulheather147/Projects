<template>

  <title>PaulMessagingApp</title>
  <div>
    <h1>This is the home page for my messaging app</h1>
    <button @click="$router.push('/login')">Log in</button>
    <button @click="$router.push('/signup')">Sign up</button><br>
  </div>

</template>

<script>
export default {
  name: 'Home'
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