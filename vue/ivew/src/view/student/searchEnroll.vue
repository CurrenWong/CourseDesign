<template>
  <div>
    <div class="head">
      2020年高考录取查询结果
    </div>
    <enroll-result></enroll-result>
  </div>
</template>

<script>
  import enrollResult from './enrollResult'
  export default {
    name: 'searchEnroll',
    components:{
      enrollResult
    }
  }
</script>

<style scoped>
  .head{
    width: 40%;
    height: 100px;
    line-height: 50px;
    font-size: 30px;
    display: flex;
    margin: auto;
  }
</style>
