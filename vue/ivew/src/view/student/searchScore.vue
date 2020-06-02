<template>
  <div>
   <div>
     <header style="size: 1000px">2020普通高等学校招生全国统一考试成绩查询
     </header>
   </div>
    <div style="margin-top: 15px;width: 50%;height: 100px">
      <el-input placeholder="请输入准考证号" v-model="input1" class="input-with-select">
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
      <el-input placeholder="请输入姓名" v-model="input2" class="input-with-select">
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </div>

    <el-table
      :data="tableData"
      style="width: 50%">

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

