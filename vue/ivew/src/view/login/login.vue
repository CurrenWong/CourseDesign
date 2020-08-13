<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <label  >
            <!--            @click.stop="clickMe"-->
            <input type="checkbox" id="label1"  >学生
            <input type="checkbox" id="label2"  >高校
            <input type="checkbox" id="label3"  >教育局
            <input type="checkbox" id="label4"  >管理员
            <!--            v-model="ckeckVal"-->
          </label>
          <p class="login-tip">选择你的身份</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from '_c/login-form'
import { mapActions } from 'vuex'
export default {
  components: {
    LoginForm
  },
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo'
    ]),
    handleSubmit ({ userName, password }) {
      this.handleLogin({ userName, password }).then(res => {
        this.getUserInfo().then(res => {
          this.$router.push({
            name: this.$config.homeName
          })
        })
      })
    }
  }
}
</script>

<style>

</style>
