package com.demo.web.controller.system;

import com.demo.system.pojo.SysUser;
import com.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/list")
    public List<SysUser> list(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        return list;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String,Object> update(SysUser user){
        Map<String,Object> map=new HashMap<String,Object>();
        if(user.getUserId()==null){
            map.put("status","failed");
            map.put("error","id is null");
            return map;
        }
        userService.updateUser(user);
        user=this.userService.selectUserById(user.getUserId());
        map.put("status","success");
        map.put("user",user);
        return map;
    }
}
