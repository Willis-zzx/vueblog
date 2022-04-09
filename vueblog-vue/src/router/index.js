import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import BlogDetail from '../views/BlogDetail.vue'
import BlogEdit from '../views/BlogEdit.vue'
import Home from "../views/Home";
import Blog from "../views/Blog.vue"

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: {
            title: '登录'
        }
    }, 
    {
        path: '/',
        name: 'Index',

        // 懒加载
        component: () => import('../views/Index.vue'),
        redirect: 'Home',
        children: [{
                path: '',
                name: 'Home',
                component: Home,
                meta: {
                    title: '首页'
                }
            },
            {
                path: '/blog/:blogId',
                name: 'Blog',
                component: Blog,
                meta: {
                    title: '博客'
                }
            },
            /*{
                path: '/archives',
                name: 'Archives',
                component: Archives,
                meta: {
                    title: '归档'
                }
            },
            {
                path: '/about',
                name: 'About',
                component: About,
                meta: {
                    title: '关于我'
                }
            },
            {
                path: '/friends',
                name: 'Friends',
                component: Friends,
                meat: {
                    title: '友链'
                }
            },
            {
                path: '/category/:name',
                name: 'Category',
                component: Category,
                meta: {
                    title: '分类'
                }
            }, */


        ]

    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router