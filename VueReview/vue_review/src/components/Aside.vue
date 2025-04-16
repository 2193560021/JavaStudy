<script>
export default {
  name:"Aside",
  data(){
    return{
      path: this.$route.path
    }
  },
  created() {
    console.log(this.$route.path)
  }
}
</script>

<template>
  <div>
    <el-menu
        style="width: 250px;min-height: calc(100vh - 50px);"
        active-text-color="#538fff"
        background-color="#545c64"
        class="el-menu-vertical-demo asideText"
        default-active="2"
        text-color="#fff"
        router
        :default-active="path"
    >

      <el-menu-item index="/">
        <el-icon><icon-menu /></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-menu-item index="/court_order">
        <el-icon><document /></el-icon>
        <span>场地预约记录</span>
      </el-menu-item>
      <el-menu-item index="4">
        <el-icon><setting /></el-icon>
        <span>场地信息管理</span>
      </el-menu-item>
      <el-menu-item index="5">
        <el-icon><setting /></el-icon>
        <span>商品信息管理</span>
      </el-menu-item>
      <el-menu-item index="6">
        <el-icon><setting /></el-icon>
        <span>商品购买管理</span>
      </el-menu-item>
      <el-menu-item index="7">
        <el-icon><setting /></el-icon>
        <span>文章内容管理</span>
      </el-menu-item>
      <el-menu-item index="8">
        <el-icon><setting /></el-icon>
        <span>用户信息管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped>
.asideText span{
  font-size: 18px;
}
</style>