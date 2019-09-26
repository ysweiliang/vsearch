<template>
    <el-row class="container">
        <el-col :span="24" class="header">
            <span class="title">
                <img src="/favicon.ico" style="width: 26px;height: 26px;"/>
                Vsearch
                <el-button @click="dialogFormVisible = true"
                           v-if="this.$store.state.userInfo.name==='未登录'">登陆</el-button>
                <el-button @click="dialogFormRegisterVisible = true"
                           v-if="this.$store.state.userInfo.name==='未登录'">注册</el-button>
                <el-button
                        v-if="this.$store.state.userInfo.name!=='未登录'">{{this.$store.state.userInfo.name}}</el-button>
                <el-button v-if="this.$store.state.userInfo.name!=='未登录'" @click="loginOut">退出</el-button>

                <!--弹出登陆框-->
                <el-dialog title="用户登陆" :visible.sync="dialogFormVisible" @closed="handleLoginClose">
                  <el-form :model="form" :rules="rules" ref="ruleForm">
                    <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                      <el-input v-model="form.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                      <el-input v-model="form.password" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="login">确 定</el-button>
                  </div>
                </el-dialog>

                <!--弹出用户注册框-->
                <el-dialog title="用户注册" :visible.sync="dialogFormRegisterVisible" @closed="handleRegisterClose">
                  <el-form :model="registerForm" :rules="registerRules" ref="registerRuleForm">
                    <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                      <el-input v-model="registerForm.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                      <el-input v-model="registerForm.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
                      <el-input v-model="registerForm.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
                      <el-input v-model="registerForm.phone" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormRegisterVisible = false">取 消</el-button>
                    <el-button type="primary" @click="userRegister">确 定</el-button>
                  </div>
                </el-dialog>
            </span>
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
    import {isEmail, isPhone} from "../utils/validate";

    export default {
        name: "index",
        data: function () {
            return {
                dialogFormVisible: false,
                dialogFormRegisterVisible: false,
                keyword: "",
                suggests: [],
                timeout: null,
                form: {
                    name: "",
                    password: ""
                },
                formLabelWidth: '90px',
                rules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur'}
                    ]
                },
                registerForm: {
                    name: "",
                    password: "",
                    email: "",
                    phone: ""
                },
                registerRules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                        {validator: isEmail, trigger: 'blur'}
                    ],
                    phone: [
                        {required: true, message: '请输入手机号', trigger: 'blur'},
                        {type: 'string', min: 11, message: '手机号不允许小于11位', trigger: 'blur'},
                        {validator: isPhone, trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            /*搜索*/
            search: function () {
                this.$router.push({path: '/search', query: {kw: this.keyword}})
            },
            /*智能搜索提示框*/
            querySearchAsync(queryString, cb) {
                let param = {
                    'suggest': this.keyword
                };
                this.postRequest('/search/dropDown', param).then(res => {
                    let results = [];
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
            },
            /*登陆*/
            login: function () {
                this.$refs.ruleForm.validate((valid) => {
                    if (valid) {
                        let user = {
                            name: this.form.name,
                            password: this.form.password
                        };
                        var _this = this;
                        this.postRequest('/user/login', user).then(res => {
                            let userInfo = res.data;
                            if (userInfo.resultcode === 0) {
                                this.$message.error(userInfo.msg);
                            } else {
                                _this.$store.commit('login', userInfo);
                                this.dialogFormVisible = false
                            }
                        });
                    }
                });
            },
            /*关闭登陆窗口*/
            handleLoginClose() {
                this.$refs.ruleForm.resetFields();
                this.form = {
                    name: '',
                    password: ''
                }
            },
            /*注册用户*/
            userRegister() {
                this.$refs.registerRuleForm.validate((valid) => {
                    if (valid) {
                        let userRegister = {
                            name: this.registerForm.name,
                            password: this.registerForm.password,
                            phone: this.registerForm.phone,
                            email: this.registerForm.email
                        };
                        this.postRequest('/user/register', userRegister).then(res => {
                            let userInfo = res.data;
                            if (userInfo.resultcode === 0) {
                                this.$message.error(userInfo.msg);
                            } else {
                                this.dialogFormRegisterVisible = false
                            }
                        });
                    }
                });
            },
            handleRegisterClose() {
                this.$refs.registerRuleForm.resetFields();
                this.registerForm = {
                    name: "",
                    password: "",
                    phone: "",
                    email: ""
                }
            },
            /*登出*/
            loginOut() {
                var _this = this;
                _this.$store.commit('logout');
                window.location.reload()
            }
        },
        computed: {}
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