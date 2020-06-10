<template>
  <div>
   <div class="head">
    2020普通高等学校招生全国统一考试成绩查询
   </div>
    <div class="searchbar">
      <el-input placeholder="请输入准考证号" v-model="input1" class="input-with-select" style="line-height:50px;width:400px">
      </el-input>
      <el-input placeholder="请输入姓名" v-model="input2" class="input-with-select" style="width:400px;">
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </div>

    <el-table
      :data="tableData"
      class="searchTable">
      <el-table-column
        prop="item"
         width="180">
      </el-table-column>

      <el-table-column
        prop="result"
      width="180">
      </el-table-column>
    </el-table>

  </div>
</template>


<script>
  export default {
    data () {
      return {
        input2: '',
        input1: '',
        select: '',
        tableData: [
          { item: "准考证号", result: "34728823" },
          { item: "姓名", result: "王浩" },
          { item: "年级排名", result: "1" },
          { item: "全省排名", result: "1" },
          { item: "高考总分", result: "750" },
          { item: "语文", result: "150" },
          { item: "数学", result: "150" },
          { item: "外语", result: "150" },
          { item: "综合", result: "300" }
        ]
      }
    },
   }
</script>


<style>
.head{
  width: 50%;
  padding-left: 20px;
  height: 50px;
  line-height: 50px;
  font-size: 20px;
  display: flex;
  padding-right: 50px;
  margin: auto;
}
  .searchbar{
    margin: auto;
    width: 50%;
    height: 100px;
  }
  .searchTable{
    width: 50%;
    margin: auto;
    padding-left: 100px;
    line-height: 50px;
    font-size: 20px;
    color: #657180;
    background-color: #DCDCDC;
  }
</style>
