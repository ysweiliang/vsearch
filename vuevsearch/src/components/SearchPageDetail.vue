<template>
    <el-row class="container">
        <el-col :span="24" class="header">
            <!--<img src="/favicon.ico" style="width: 26px;height: 26px;cursor: pointer;" @click="returnSearch"/>-->
        </el-col>
        <el-col :span="24" style="margin-top: 5%;">
            <div>
                <p v-html="searchDetail.year" style="cursor: pointer;"></p>
                <p v-html="searchDetail.title" style="margin-top: 2%;"></p>
            </div>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "SearchPageDetail",
        components: {},
        data() {
            return {
                searchDetail: null,
                kw: null
            }
        },
        props: ['searchDetailData'],
        method: {
            // returnSearch: function () {
            //     this.$router.push({path: '/search', query: {kw: this.$route.query.kw}})
            // }
        },
        created: function () {
            this.searchDetail = JSON.parse(this.$route.query.searchDetailData);
            console.log(this.searchDetail)
        }
    }
</script>

<style scoped>

</style>