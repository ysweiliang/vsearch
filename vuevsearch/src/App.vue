<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
    import TodoItem from './components/TodoItem.vue'

    export default {
        name: 'app',
        data() {
            return {
                msg: 'hello world',
                info: '',
                list: [],
            }
        },
        methods: {
            handleClick() {
                this.list.push(this.info)
                this.info = ''
            }
        },
        components: {
            TodoItem,
        }
    }
</script>

<style>

</style>
