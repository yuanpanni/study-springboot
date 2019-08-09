package com.demo.system.service.imp;

import com.demo.common.CacheUtils;
import com.demo.common.RedisService;
import com.demo.system.mapper.SysUserMapper;
import com.demo.system.pojo.SysUser;
import com.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private static final String CACHE_USER_LIST = "userList";
    private static final String RANKGNAME = "user_score";
    private static final String SALESCORE = "sale_score_rank:";

    //@Autowired
    private SysUserMapper userMapper;

    @Autowired
    private RedisService redisService;

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

    @Override
    public boolean rankAdd(String uid, Integer score) {
        return redisService.zAdd(RANKGNAME, uid, score);
    }

    @Override
    public Double increSocre(String uid, Integer score) {
        return redisService.incrementScore(RANKGNAME, uid, score);
    }

    @Override
    public Long rankNum(String uid) {
        return redisService.zRank(RANKGNAME,uid);
    }

    @Override
    public Long score(String uid) {
        return redisService.zSetScore(RANKGNAME,uid).longValue();
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> rankWithScore(Integer start, Integer end) {
        //return redisService.reverseZRankWithScore(RANKGNAME,start,end);

        return redisService.zRankWithScore(RANKGNAME,start,end);

    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> reverseZRankWithScore(Integer start, Integer end) {
        return redisService.reverseZRankWithRank(RANKGNAME,start,end);
    }


}
