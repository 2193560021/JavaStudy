<template>
  <div class="about">
    <h1>This is an about page</h1>
    <el-table :data="tableData" stripe border style="width: 98%;margin: 10px">
      <el-table-column prop="date" label="Date" />
      <el-table-column prop="name" label="Name"/>
      <el-table-column prop="address" label="Address" />
    </el-table>
  </div>
</template>

<script>
export default {
  name:"CourtOrder",
  components:{

  },
  data(){
    return{
      tableData:[
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ]
    }
  }
}
</script>
