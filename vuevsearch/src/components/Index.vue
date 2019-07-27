<template>
    <el-row class="container">
        <el-col :span="24" class="header">
            <div class="title">
                <img src="/favicon.ico" style="width: 26px;height: 26px;"/>
                Vsearch
            </div>
        </el-col>
        <el-col :span="12" style="margin-left: 18%;margin-top: 20%;">
            <el-autocomplete style="width: 100%"
                             v-model="keyword"
                             :fetch-suggestions="querySearchAsync"
                             placeholder="请输入内容"
                             @select="handleSelect"
                             @keyup.enter.native="search"
                             clearable
            ></el-autocomplete>
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
                keyword: "",
                suggests: [],
                timeout: null
            }
        },
        methods: {
            search: function () {
                this.$router.push({path: '/search', query: {kw: this.keyword}})
            },
            querySearchAsync(queryString, cb) {
                let param = {
                    'suggest': this.keyword
                };
                this.postRequest('/search/suggest', param).then(res => {
                    var results = [];
                    this.suggests = res.data;
                    for (let index in this.suggests) {
                        let obj = {"value": this.suggests[index]};
                        results.push(obj)
                    }
                    clearTimeout(this.timeout);
                    this.timeout = setTimeout(() => {
                        cb(results);
                    }, 1000 * Math.random());
                });
            },
            createStateFilter(queryString) {
                return (state) => {
                    return (state.value.indexOf(queryString) === 0);
                };
            },
            handleSelect(item) {
                console.log(item);
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