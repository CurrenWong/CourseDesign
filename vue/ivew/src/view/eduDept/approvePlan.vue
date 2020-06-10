<template>
<div>
  <div class="head">
    全国高校2020年本科招生计划
  </div>
  <el-row style="float: right">
    <el-button @click="toggleSelection()" type="primary">批量通过</el-button>
    <el-button @click="toggleSelection()" type="warning">批量驳回</el-button>
  </el-row>
  <el-table
    :header-cell-style="{background:'#d3d7d4'}"
    ref="multipleTable"
    :data="tableData"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      label="院校编号"
      width="120">
     <template slot-scope="scope">{{ scope.row.collegeNum }}</template>
    </el-table-column>
    <el-table-column
      property="collegeID"
      label="院校代码"
      width="120">
    </el-table-column>

    <el-table-column
      property="collegeName"
      label="院校名称">
    </el-table-column>

    <el-table-column
      property="majorID"
      label="专业（大类）代码">
    </el-table-column>

    <el-table-column
      property="majorName"
      label="专业（大类）名称">
    </el-table-column>

    <el-table-column
      property="class"
      label="涵盖专业（或方向）">
    </el-table-column>

    <el-table-column
      property="branch"
      label="科类">
    </el-table-column>

    <el-table-column
      property="number"
      label="招生人数">
    </el-table-column>

    <el-table-column
      property="city"
      label="省/直辖市"
      show-overflow-tooltip>
    </el-table-column>

    <el-table-column
      label="操作">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">通过</el-button>
        <el-button type="text" size="small">驳回</el-button>
      </template>
    </el-table-column>

  </el-table>


  <div style="margin-top: 20px">
    <el-button @click="toggleSelection()">清空选项</el-button>
  </div>
</div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          collegeNum: '1',
          collegeID: '01',
          collegeName: '上海理工大学',
         majorID :'01',
         majorName:'计算机类',
          class:'计算机科学与技术',
          branch:'理科',
          number:'20',
          city:'上海'
        },
          {
            collegeNum: '2',
            collegeID: '02',
            collegeName: '上海大学',
            majorID :'01',
            majorName:'计算机类',
            class:'计算机科学与技术',
            branch:'理科',
            number:'20',
            city:'上海'
          },

        ],
        multipleSelection: []
      }
    },

    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleClick(row) {
    console.log(row);
  }
    }
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
</style>
