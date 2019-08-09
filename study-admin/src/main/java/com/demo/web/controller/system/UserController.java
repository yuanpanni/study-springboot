package com.demo.web.controller.system;

import com.demo.system.pojo.SysUser;
import com.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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

    @ResponseBody
    @RequestMapping("/addScore")
    public String addRank(String uid, Integer score) {
        userService.rankAdd(uid, score);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/increScore")
    public String increScore(String uid, Integer score) {
        userService.increSocre(uid, score);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/rank")
    public Map<String, Long> rank(String uid) {
        Map<String, Long> map = new HashMap<>();
        map.put(uid, userService.rankNum(uid));
        return map;
    }

    @ResponseBody
    @RequestMapping("/score")
    public Long rankNum(String uid) {
        return userService.score(uid);
    }

    @ResponseBody
    @RequestMapping("/scoreByRange")
    public Set<ZSetOperations.TypedTuple<Object>> scoreByRange(Integer start, Integer end) {
        return userService.rankWithScore(start,end);
    }

    @ResponseBody
    @RequestMapping("/topByScore")
    public Set<ZSetOperations.TypedTuple<Object>> topByScore(Integer start, Integer end) {
        return userService.reverseZRankWithScore(start,end);
    }
}
