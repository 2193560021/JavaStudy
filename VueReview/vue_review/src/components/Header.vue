<script setup>
import { ArrowDown } from '@element-plus/icons-vue'
</script>

<template>
  <div style="height: 50px;line-height: 50px;display: flex;background: #41474d;">
    <div style="user-select: none;width: 600px; padding-left: 30px;font-weight: bolder; color: #7fabfa;font-size: 28px">
      <span style="cursor: pointer">
        羽你相约-羽毛球场地预约管理系统
      </span>
    </div>
    <div style="flex: 1">

    </div>
    <div style="width: 100px;padding-top: 15px;">
      <el-dropdown>
        <span class="el-dropdown-link" style="font-size: 18px">
          刘宇阳
          <el-icon class="el-icon--right">
            <arrow-down/>
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item divided>退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
.el-dropdown-link:hover{
  border: none;
}
</style>