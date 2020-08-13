<template>
  <div class="vol-table">
    <el-table
      :header-cell-style="{background:'#d3d7d4'}"
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column
        prop="volID"
        label="志愿号"
      >
      </el-table-column>
      <el-table-column
        prop="collegeName"
        label="校院名称"
      >
      </el-table-column>
      <el-table-column
        prop="vol1"
        label="专业1">
      </el-table-column>
      <el-table-column
        prop="vol2"
        label="专业2">
      </el-table-column>
      <el-table-column
        prop="vol3"
        label="专业3">
      </el-table-column>
      <el-table-column
        prop="vol4"
        label="专业4">
      </el-table-column>
      <el-table-column
        prop="vol5"
        label="专业5">
      </el-table-column>
      <el-table-column
        prop="vol6"
        label="专业6"
        class="el-icon-price-tag"
      >
      </el-table-column>
      <el-table-column
        label="服从调剂"
        width="100">
        <RadioGroup>
          <Radio label="是">是</Radio>
          <Radio label="否">否</Radio>
        </RadioGroup>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          // "flightID": 121,
          "volID": '1',
          "collegeName": "100001-北京大学",
          "vol1": "导弹工程",
          "vol2": "导弹工程",
          "vol3": "导弹工程",
          "vol4": "导弹工程",
          "vol5": "导弹工程",
          "vol6": "导弹工程",
        },
          {"volID": '2' },
          {"volID": '3' },
          {"volID": '4' },
          {"volID": '5' },
          {"volID": '6' },
        ]
      }
    }
  }
</script>

<style scoped>

</style>
