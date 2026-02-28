package com.itmk.web.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.status.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.sys_admin.entity.PageParm;
import com.itmk.web.sys_admin.entity.SysAdmin;
import com.itmk.web.user.entity.LoginVo;
import com.itmk.web.user.entity.User;
import com.itmk.web.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hc/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public ResultVo register(@RequestBody User user) {
        //判断账户是否被占用
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername,user.getUsername());
        //查询用户
        User one = userService.getOne(query);
        if (one != null) {
            return ResultUtils.error("用户名被占用");
        }
        //密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        //存到数据库
        if (userService.saveOrUpdate(user)){
            return ResultUtils.success("注册成功!");
        }
        return ResultUtils.error("注册失败!");
    }
    //登录
    @PostMapping("/login")
    public ResultVo login(@RequestBody User user) {
        //构造查询条件
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getUsername,user.getUsername()).eq(User::getPassword,DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        User one = userService.getOne(query);
        if(one!=null){
            if (one.getStatus().equals("1")){
                return ResultUtils.error("您的账户被停用，请联系管理");
            }
            //返回成功的数据
            LoginVo vo=new LoginVo();
            vo.setName(one.getName());
            vo.setPhone(one.getPhone());
            vo.setId(one.getId());
            vo.setUsername(one.getUsername());
            vo.setCollege(one.getCollege());
            return ResultUtils.success("登录成功",vo);
        }
        return ResultUtils.error("用户名或密码错误！");
    }
    //列表
    @GetMapping("/getList")
    public ResultVo getList(PageParm parm){
        //构造查询条件
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getName()), User::getName, parm.getName());
        //构造分页
        IPage<User> page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        IPage<User> list = userService.page(page,query);
        return ResultUtils.success("查询成功",list);
    }
}
