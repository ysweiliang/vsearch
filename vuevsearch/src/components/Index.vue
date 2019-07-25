<template>
    <el-row class="container">
        <el-col :span="24" class="header">
            <div class="title">Vsearch</div>
        </el-col>
        <el-col :span="12" style="margin-left: 18%;margin-top: 20%;">
            <el-input v-model="keyword" placeholder="请输入内容"></el-input>
        </el-col>
        <el-col :span="1" style="margin-top: 20%;">
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "index",
        data: function () {
            return {
                keyword: ""
            }
        },
        methods: {
            search: function () {
                this.$router.push({path: '/search', query: {kw: this.keyword}})
            }
        }
    }
</script>

<style scoped>

    .container {
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
        background-color: rgba(235, 235, 235, 0.08);
    }

    .header {
        background-color: #F8F8F8;
        height: 60px;
    }

    .title {
        color: #323232;
        font-size: 22px;
        display: flex;
        align-items: center;
        margin-left: 20px;
        height: 60px;
    }

    .userinfo {
        color: #fff;
        cursor: pointer;
    }

    .userinfoContainer {
        display: flex;
        align-items: center;
        justify-content: flex-end;
        height: 60px;
        margin-right: 20px;
    }
</style>