<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
  .mh-50 {
    max-height: 50vh;
    overflow: hidden;
    overflow-y: auto;
  }
</style>
