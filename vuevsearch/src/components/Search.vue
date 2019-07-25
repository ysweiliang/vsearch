<template>
    <el-row class="container">
        <el-col :span="24" class="header">
            <div class="title">
                <img src="/favicon.ico" style="width: 26px;height: 26px;cursor: pointer;" @click="returnIndex"/>
            </div>
        </el-col>
        <el-col :span="10" style="margin-left: 14%;">
            <el-input v-model="keyword" placeholder="请输入内容" @keyup.enter.native="search"></el-input>
        </el-col>
        <el-col :span="1">
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        </el-col>
        <el-col :span="10" style="margin-left: 14%;">
            <p>搜索结果：{{total}}</p>
        </el-col>
        <el-col :span="10" style="margin-left: 14%;">
            <div v-for="(searchDetailData) in searchList" :key="searchDetailData.id">
                <p v-html="searchDetailData.year" style="cursor: pointer;" @click="goDetail(searchDetailData)"></p>
                <p v-html="searchDetailData.title" style="margin-top: 2%;"></p>
            </div>
        </el-col>
        <el-col :span="4" style="margin-top: 5%;">
            <div>搜索热点<span style="margin-left: 35%;">换一换</span></div>
            <div>
                <p style="cursor: pointer;">11111</p>
                <p style="cursor: pointer;">11111</p>
                <p style="cursor: pointer;">11111</p>
                <p style="cursor: pointer;">11111</p>
                <p style="cursor: pointer;">11111</p>
            </div>
        </el-col>

        <el-col :span="10" style="margin-left: 14%;">
            <div>相关搜索</div>
            <div>
                <span style="cursor: pointer;">111112</span>
                <span style="margin-left: 15%;cursor: pointer;">222222</span>
                <span style="margin-left: 15%;cursor: pointer;">323333</span>
            </div>
            <div>
                <span style="cursor: pointer;">111132</span>
                <span style="margin-left: 15%;cursor: pointer;">233333</span>
                <span style="margin-left: 15%;cursor: pointer;">333333</span>
            </div>
        </el-col>
        <el-col :span="12" style="margin-left: 13%;margin-top: 2%;">
            <el-pagination
                    background
                    layout="prev, pager, next"
                    @current-change="changeHandle"
                    :current-page="pageNum"
                    :total="total">
            </el-pagination>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: 'Search',
        components: {},
        props: ['kw'],
        data() {
            return {
                keyword: '',
                pageNum: 1,
                pageSize: 5,
                total: null,
                searchList: []
            }
        },
        methods: {
            returnIndex: function () {
                this.$router.push({path: '/'})
            },
            search: function () {
                let param = {
                    'keyword': this.keyword,
                    'pageNum': this.pageNum,
                    'pageSize': this.pageSize
                };
                this.postRequest('/search', param).then(res => {
                    this.searchList = res.data.rows;
                    this.pageNum = res.data.pageNum;
                    this.pageSize = res.data.pageSize;
                    this.total = res.data.total;
                })
            },
            changeHandle: function (pageNum) {
                this.pageNum = pageNum;
                this.search(this.keyword);
            },
            goDetail: function (searchDetailData) {
                this.$router.push({
                    path: '/search/detail',
                    query: {searchDetailData: JSON.stringify(searchDetailData)}
                })
            }
        },
        created: function () {

        },
        mounted: function () {
            this.keyword = this.$route.query.kw;
            this.search();
        },
        watch: {
            keyword: function (val) {
                let query = this.$router.history.current.query;
                let path = this.$router.history.current.path;
                //对象的拷贝
                let newQuery = JSON.parse(JSON.stringify(query));
                newQuery.kw = val;
                this.$router.push({path, query: newQuery});
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