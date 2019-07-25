<template>
    <el-row class="container">
        <el-col :span="24" class="header">
            <div class="title">
                <span @click="returnIndex" style="cursor: pointer;">
                    Vsearch
                </span>
            </div>
        </el-col>
        <el-col :span="12" style="margin-left: 18%;">
            <el-input v-model="keyword" placeholder="请输入内容"></el-input>
        </el-col>
        <el-col :span="1">
            <el-button type="primary" icon="el-icon-search" @click="search(keyword)">搜索</el-button>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: 'Home',
        components: {},
        props: ['kw'],
        data() {
            return {
                keyword: '',
                pageNum:1,
                pageSize:10
            }
        },
        methods: {
            returnIndex: function () {
                this.$router.push({path: '/'})
            },
            search: function (keyword) {
                let param = {
                    'keyword':keyword,
                    'pageNum':this.pageNum,
                    'pageSize':this.pageSize
                };
                this.postRequest('/search',param).then(res=> {
                    console.log(res,111111111111)
                })
            }
        },
        created: function () {
            this.keyword = this.$route.query.kw;
            this.search(this.keyword);
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