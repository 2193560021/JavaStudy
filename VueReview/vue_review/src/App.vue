<template>
  <div >

    <Header/> <!--主体-->
    <div style="display: flex">
      <Aside/> <!--侧边栏-->

      <!-- 内容区域 -->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<style>


</style>
<script>

import Header from "@/components/Header.vue";
import Aside from "@/components/Aside.vue";

export default {
  name: "Layout",
  components:{
    Aside,
    Header
  }
}

</script>