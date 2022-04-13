<template>
  <div>
    <el-card shadow="hover">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input
            v-model="queryInfo.tagName"
            :clearable="true"
            placeholder="请输入标签名称"
            size="small"
            style="min-width: 95%"
            @clear="search"
            @keyup.native.enter="search"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="search"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="8">
          <!--添加-->
          <el-form>
            <el-button
              type="primary"
              size="small"
              icon="el-icon-plus"
              @click="addDialogVisible = true"
            >
              添加标签
            </el-button>
          </el-form>
        </el-col>
      </el-row>

      <el-table :data="tagList" style="width: 100%">
        <el-table-column label="序号" type="index" width="50"></el-table-column>
        <el-table-column label="标签名称" prop="tagName"></el-table-column>
        <el-table-column label="创建时间" prop="createTime"></el-table-column>
        <el-table-column label="修改时间" prop="updateTime"></el-table-column>
        <el-table-column label="操作" width="200">
          <template v-slot="scope">
            <el-button
              icon="el-icon-edit"
              size="mini"
              type="primary"
              @click="showEditDialog(scope.row)"
              >编辑
            </el-button>
            <el-popconfirm
              icon="el-icon-delete"
              iconColor="red"
              title="确定删除吗？"
              @confirm="deleteTagById(scope.row.id)"
            >
              <el-button
                slot="reference"
                icon="el-icon-delete"
                size="mini"
                type="danger"
                >删除
              </el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <!--分页-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.currentPage"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="queryInfo.pageSize"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        background
      >
      </el-pagination>

      <!--添加标签对话框-->
      <el-dialog
        title="添加标签"
        width="40%"
        :visible.sync="addDialogVisible"
        :close-on-click-modal="false"
        @close="addDialogClosed"
      >
        <el-form
          :model="addForm"
          :rules="formRules"
          ref="addFormRef"
          label-width="80px"
        >
          <el-form-item label="标签名称" prop="tagName">
            <el-input v-model="addForm.tagName"></el-input>
          </el-form-item>
        </el-form>
        <!--底部-->
        <span slot="footer">
          <el-button @click="addDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveTag">确定</el-button>
        </span>
      </el-dialog>

      <!--编辑标签对话框-->
      <el-dialog
        title="编辑标签"
        width="40%"
        :visible.sync="editDialogVisible"
        :close-on-click-modal="false"
        @close="editDialogClosed"
      >
        <el-form
          :model="editForm"
          :rules="formRules"
          ref="editFormRef"
          label-width="80px"
        >
          <el-form-item label="标签名称" prop="tagName">
            <el-input v-model="editForm.tagName"></el-input>
          </el-form-item>
        </el-form>
        <!--底部-->
        <span slot="footer">
          <el-button @click="editDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="editTag">确定</el-button>
        </span>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Tag",
  data() {
    return {
      tagList: [],
      queryInfo: {
        tagName: "",
        currentPage: 1,
        pageSize: 10,
      },
      total: 0,
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        tagName: "",
      },
      editForm: {
        tagName: "",
      },
      formRules: {
        typeName: [
          {
            required: true,
            message: "请输入标签名称",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    //查询标签列表
    getTagList() {
      const _this = this;
      this.$axios
        .get(
          "/tag/tagListByPage?pageNum=" +
            this.queryInfo.currentPage +
            "&pageSize=" +
            this.queryInfo.pageSize +
            "&tagName=" +
            this.queryInfo.tagName,
          {
            headers: {
              Authorization: localStorage.getItem("token"),
            },
          }
        )
        .then((res) => {
          _this.tagList = res.data.data.list;
          _this.total = res.data.data.total;
        });
    },
    showEditDialog(row) {
      this.editForm = row;
      this.editDialogVisible = true;
    },
    addDialogClosed() {
      this.addForm = {};
      this.addDialogVisible = false;
    },
    editDialogClosed() {
      this.editForm = {};
      this.editDialogVisible = false;
    },
    handleCurrentChange(newPage) {
      this.queryInfo.currentPage = newPage;
      this.getTagList();
    },
    handleSizeChange(newPageSize) {
      this.queryInfo.pageSize = newPageSize;
      this.getTagList();
    },
    saveTag() {
      const _this = this;
      _this.$axios.post("/tag/insertTag", _this.addForm).then((res) => {
        _this.$alert("操作成功", "提示", {
          confirmButtonText: "确定",
          callback: (action) => {
            _this.addDialogVisible = false;
            _this.addForm = {};
            _this.getTagList();
          },
        });
      });
      _this.$refs["addFormRef"].resetFields();
    },
    editTag() {
      const _this = this;
      _this.$axios.post("/tag/updateTag", this.editForm).then((res) => {
        _this.$alert("操作成功", "提示", {
          confirmButtonText: "确定",
          callback: (action) => {
            _this.editDialogVisible = false;
            _this.editForm = {};
            _this.getTagList();
          },
        });
      });
    },
    deleteTagById(id) {
      const _this = this;
      _this.$axios.get("/tag/deleteTag/" + id).then((res) => {
        _this.$alert("删除成功", "提示", {
          confirmButtonText: "确定",
          callback: (action) => {
            _this.getTagList();
          },
        });
      });
    },
    search() {
      const _this = this;
      this.getTagList();
    },
  },
  created() {
    this.getTagList();
  },
  mounted() {},
};
</script>