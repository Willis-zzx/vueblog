<template>
  <el-container
    class="container"
    v-loading="false"
    element-loading-text="拼命加载中"
    element-loading-background="rgba(0, 0, 0, 0.8)"
    element-loading-spinner="el-icon-loading"
  >
    <!-- 侧边栏 -->
    <div>
      <!-- 系统 Logo -->
      <el-aside class="header-logo" :width="isCollapse ? '64px' : '190px'">
        <div @click="$router.push({ name: 'Home' })">
          <a v-if="!isCollapse">Blog Admin</a>
          <a v-else>CMS</a>
        </div>
      </el-aside>
      <el-aside
        class="aside"
        :width="isCollapse ? '64px' : '190px'"
        :class="'icon-size-' + iconSize"
      >
        <el-scrollbar style="height: 100%; width: 100%">
          <!--
            default-active 表示当前选中的菜单，默认为 home。
            collapse 表示是否折叠菜单，仅 mode 为 vertical（默认）可用。
            collapseTransition 表示是否开启折叠动画，默认为 true。
            background-color 表示背景颜色。
            text-color 表示字体颜色。
        -->
          <!--菜单-->
          <el-menu
            :collapse="isCollapse"
            :collapse-transition="false"
            :default-active="$store.state.activePath"
            :default-openeds="defaultOpeneds"
            :router="true"
            :unique-opened="false"
            active-text-color="#409eff"
            background-color="#263238"
            text-color="#8a979e"
          >
            <!--          <el-menu-item index="/dashboard">
              <i class="iconfont ali-iconfont icon-dashboard"></i>
              <span>仪表盘</span>
            </el-menu-item> -->
            <!-- 一级菜单 -->
            <el-submenu
              v-for="item in menuList"
              :key="item.id"
              :index="item.id + ''"
            >
              <!-- 一级菜单的模板区域 -->
              <template slot="title">
                <i :class="iconsObj[item.id]" class="iconfont"></i>
                <span>{{ item.title }}</span>
              </template>
              <!-- 二级菜单 -->
              <el-menu-item
                v-for="subItem in item.children"
                :key="subItem.id"
                :index="subItem.path"
              >
                <template slot="title">
                  <i :class="iconsObj[subItem.id]"></i>
                  <span>{{ subItem.title }}</span>
                </template>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-scrollbar>
      </el-aside>
    </div>
    <!--
        direction="vertical"  用于垂直布局
    -->
    <el-container direction="vertical">
      <!-- 头部导航栏 -->
      <el-header height="50" style="background: #fff">
        <div class="header">
          <!-- 是否展开侧边栏 -->
          <div class="header-title" @click="foldOrOpen">
            <a class="el-icon-s-fold" v-if="!isCollapse" title="折叠侧边栏" />
            <a class="el-icon-s-unfold" v-else title="展开侧边栏" />
          </div>
          <!-- 设置、文档、用户设置等 -->
          <div class="header-menu">
            <el-menu mode="horizontal" class="header-menu-submenu">
              <!-- 用户设置 -->
              <el-submenu title="用户设置" index="6">
                <template slot="title">
                  <span class="header-span">
                    <img src="../assets/logo.png" :alt="userName" />
                    {{ userName }}
                  </span>
                </template>
                <el-menu-item index="6-1" @click="showPasswordBox">
                  <i class="el-icon-edit"></i>修改密码
                </el-menu-item>
                <el-menu-item index="6-2" @click="logout">
                  <i class="el-icon-close"></i>退出
                </el-menu-item>
              </el-submenu>
            </el-menu>
          </div>
        </div>
      </el-header>
      <!-- 内容 -->
      <el-main class="wrapper">
        <!--加 key 让组件被重用时 重新执行生命周期 否则在编辑文章页面路由到写文章页面时 数据被重用-->
        <router-view :key="$route.fullPath" />
      </el-main>
      <!-- 底部 -->
      <el-footer>
        <div class="my_footer">
          Copyright © {{ startYear }}-{{ endYear }} GoldSunny 版权所有
        </div>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      foldAside: true,
      // 保存当前选中的菜单
      menuActiveName: "home",
      // 用于拼接当前图标的 class 样式
      iconSize: "true",
      //是否折叠
      isCollapse: false,
      //默认打开的菜单
      defaultOpeneds: ["1", "2", "3", "4", "5"],
      user: {},
      menuList: [
        {
          id: 1,
          title: "博客管理",
          children: [
            {
              id: 11,
              title: "写文章",
              path: "/writeBlog",
            },
            // {
            //   id: 12,
            //   title: '写动态',
            //   path: '/moments/write'
            // },
            {
              id: 13,
              title: "文章管理",
              path: "/blogList",
            },
            // {
            //   id: 14,
            //   title: '动态管理',
            //   path: '/moments'
            // },
            {
              id: 15,
              title: "分类管理",
              path: "/type",
            },
            {
              id: 16,
              title: "标签管理",
              path: "/tags",
            },
            {
              id: 17,
              title: "评论管理",
              path: "/comments",
            },
          ],
        },
        {
          id: 2,
          title: "页面管理",
          children: [
            // {
            //   id: 21,
            //   title: '站点设置',
            //   path: '/siteSettings'
            // },
            {
              id: 22,
              title: "友链管理",
              path: "/friendList",
            },
            // {
            //   id: 23,
            //   title: '关于我',
            //   path: '/about'
            // }
          ],
        },
        {
          id: 3,
          title: "系统管理",
          children: [
            {
              id: 31,
              title: "用户管理",
              path: "/userManager",
            },
            // {
            //   id: 32,
            //   title: '定时任务',
            //   path: '/jobs'
            // }
          ],
        },
        {
          id: 4,
          title: "日志管理",
          children: [
            // {
            //   id: 41,
            //   title: '任务日志',
            //   path: '/jobs/logs'
            // },
            // {
            //   id: 42,
            //   title: '登录日志',
            //   path: '/loginLog'
            // },
            // {
            //   id: 43,
            //   title: '操作日志',
            //   path: '/operationLog'
            // },
            // {
            //   id: 44,
            //   title: '异常日志',
            //   path: '/exceptionLog'
            // },
            {
              id: 45,
              title: "访问日志",
              path: "/visitLog",
            },
          ],
        },
        {
          id: 5,
          title: "数据统计",
          children: [
            {
              id: 51,
              title: "访客统计",
              path: "/visitor",
            },
            // {
            //   id: 52,
            //   title: '受访页面',
            //   path: '/visitPage'
            // }
          ],
        },
      ],
      iconsObj: {
        1: "el-icon-menu",
        2: "el-icon-document-copy",
        3: "el-icon-s-tools",
        4: "el-icon-document",
        5: "el-icon-s-data",
        11: "el-icon-edit",
        12: "el-icon-edit",
        13: "el-icon-s-order",
        14: "el-icon-chat-dot-round",
        15: "el-icon-s-opportunity",
        16: "el-icon-paperclip",
        17: "el-icon-s-comment",
        21: "submenu ali-iconfont icon-bianjizhandian",
        22: "el-icon-share",
        23: "el-icon-tickets",
        31: "el-icon-user-solid",
        32: "el-icon-alarm-clock",
        41: "el-icon-alarm-clock",
        42: "el-icon-finished",
        43: "el-icon-document-checked",
        44: "el-icon-document-delete",
        45: "el-icon-data-line",
        51: "el-icon-s-marketing",
        52: "el-icon-view",
      },
      startYear: new Date().getFullYear(),
      endYear: new Date().getFullYear() + 1,
      userName: "zzx",
      breadList: [], // 路由集合
    };
  },
  created() {
    this.getUserInfo();
    this.getBreadcrumb();
  },
  watch: {
    $route() {
      this.getBreadcrumb();
    },
  },
  methods: {
    //获取缓存的用户名和头像
    getUserInfo() {
      if (this.$store.getters.getUser.username) {
        this.user.username = this.$store.getters.getUser.username;
        this.user.avatar = this.$store.getters.getUser.avatar;
      } else {
        this.$router.push("/login");
      }
    },
    isHome(route) {
      return route.name === "home";
    },
    getBreadcrumb() {
      let matched = this.$route.matched;
      //如果不是首页
      if (!this.isHome(matched[0])) {
        matched = [
          {
            path: "/home",
            meta: {
              title: "首页",
            },
          },
        ].concat(matched);
      }
      this.breadList = matched;
    },
    // 展开密码修改框
    showPasswordBox() {
      this.UpdatePasswordVisible = true;
      // this.$nextTick 表示数据渲染后，执行密码框初始化
      this.$nextTick(() => {
        this.$refs.updatePassowrd.init();
      });
    },
    //登出
    logout() {
      const _this = this;
      this.$axios.get("/logout").then((res) => {
        _this.$store.commit("REMOVE_INFO");
        _this.$router.push("/login");
      });
    },
    foldOrOpen(data) {
      this.isCollapse = !this.isCollapse;
    },
  },
};
</script>
<style>
.container {
  height: 100%;
}

.wrapper {
  height: calc(100vh - 100px);
}

.wrapper_con {
  padding-bottom: 0;
}

.aside {
  margin-bottom: 0;
  height: 100%;
  max-height: calc(100% - 50px);
  width: 100%;
  max-width: 200px;
  background-color: #263238;
  text-align: left;
  right: 0;
}

.header-logo {
  background-color: #17b3a3;
  text-align: center;
  height: 50px;
  line-height: 50px;
  width: 200px;
  font-size: 24px;
  color: #fff;
  font-weight: bold;
  margin-bottom: 0;
  cursor: pointer;
}

.el-submenu .el-menu-item {
  max-width: 200px !important;
}

.el-scrollbar__wrap {
  overflow-x: hidden !important;
}

.icon-size-false i {
  font-size: 30px !important;
}

.icon-size-true i {
  font-size: 18px !important;
}

.my_footer {
  background: #eee;
  color: #666;
  font-size: 14px;
  line-height: 36px;
}

.header {
  padding: 0 10px;
  display: flex;
  height: 50px;
  line-height: 50px;
  border-bottom: solid 1px #e6e6e6;
}

.header-title {
  height: 50px;
  width: 50px;
  float: left;
  font-size: 30px;
  cursor: pointer;
}

.header-menu {
  height: 50px;
  width: 100%;
  flex: 1;
  line-height: 50px;
  font-size: 30px;
}

.header-menu-submenu {
  float: right;
}

.header-submenu-a {
  text-decoration: none;
  color: #4cc4b8;
  font-weight: bold;
  font-size: 14px;
}

.header-submenu-a:hover {
  background-color: #2c3e50;
}

.el-menu--horizontal > .el-menu-item,
.el-menu--horizontal > .el-submenu .el-submenu__title {
  height: 50px !important;
  line-height: 50px !important;
  padding: 0 10px !important;
}

.el-menu--collapse .el-menu .el-submenu,
.el-menu--popup {
  min-width: auto !important;
}

.header-span img {
  width: 40px;
  height: 40px;
  line-height: 40px;
  margin: 5px 5px 10px 10px;
  border-radius: 50%;
}

.header-span {
  font-size: 20px;
  border-radius: 50%;
}
</style>