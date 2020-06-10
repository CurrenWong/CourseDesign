<template>
    <div>
      <div class="head">
        2020年高考招生工作进度管理
      </div>
      <div class="body">
        <h5>统招：
          <el-button  type="primary">统招开始</el-button>
          ---->
          <el-button  type="danger">统招结束</el-button>
        </h5>

        <h5>调招：
          <el-button  type="primary">调招开始</el-button>
          ---->
          <el-button  type="danger">调招结束</el-button></h5>

        <h5>补录：
          <el-button  type="primary">补录开始</el-button>
          ---->
          <el-button  type="danger">补录结束</el-button></h5>
      </div>

    </div>
</template>

<script>
  export default {
    name: 'planManage'
  }
</script>

<style scoped>
  .head{
    width: 50%;
    height: 50px;
    line-height: 50px;
    font-size: 30px;
    display: flex;
    margin: auto;
  }
  .body{
    line-height: 50px;
    font-size: 30px;
    margin-top: 100px;
    margin-left: 200px;
  }
</style>
