<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/student/additionalRcd' }">本科一批</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/student/additionalScdRcd' }">本科二批</el-breadcrumb-item>
      <Breadcrumb-item></Breadcrumb-item>
    </el-breadcrumb>
    <div class="head">
      普通类本科一批志愿（补录）
    </div>
    <vol-table></vol-table>
  </div>
</template>

<script>
  import volTable from './volTable'
  export default {
    components:
      {
        volTable
      }
  }
</script>

<style scoped>
  .head{
    width: 40%;
    height: 50px;
    line-height: 50px;
    font-size: 30px;
    display: flex;
    margin: auto;
  }

</style>
