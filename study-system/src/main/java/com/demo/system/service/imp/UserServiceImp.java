package com.demo.system.service.imp;

import com.demo.common.CacheUtils;
import com.demo.system.mapper.SysUserMapper;
import com.demo.system.pojo.SysUser;
import com.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    public static final String CACHE_USER_LIST = "userList";

    //@Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        //测试缓存
        List<SysUser> list=(List<SysUser>)CacheUtils.get(CACHE_USER_LIST);
        if(list==null){
            System.out.println("get data from db ...");
            SysUser user_t=new SysUser(1,1,"ypn","ypn");
            list=new ArrayList<SysUser>();
            list.add(user_t);
            //list=userMapper.selectUserList(user);
            CacheUtils.put(CACHE_USER_LIST,list);
        }
        return list;
    }

    @Override
    public SysUser selectUserByLoginName(String userName) {
        return null;
    }

    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public SysUser selectUserByEmail(String email) {
        return null;
    }

    @Override
    public SysUser selectUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(String ids) throws Exception {
        return 0;
    }

    @Override
    public int insertUser(SysUser user) {
        return 0;
    }

    @Override
    public int updateUser(SysUser user) {
        int i=userMapper.updateByPrimaryKeySelective(user);
        //更新缓存
        CacheUtils.remove(CACHE_USER_LIST);
        return i;
    }

    @Override
    public int updateUserInfo(SysUser user) {
        return 0;
    }

    @Override
    public int resetUserPwd(SysUser user) {
        return 0;
    }

    @Override
    public String checkLoginNameUnique(String loginName) {
        return null;
    }

    @Override
    public String checkPhoneUnique(SysUser user) {
        return null;
    }

    @Override
    public String checkEmailUnique(SysUser user) {
        return null;
    }

    @Override
    public String selectUserRoleGroup(Long userId) {
        return null;
    }

    @Override
    public String selectUserPostGroup(Long userId) {
        return null;
    }

    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        return null;
    }

    @Override
    public int changeStatus(SysUser user) {
        return 0;
    }
}
