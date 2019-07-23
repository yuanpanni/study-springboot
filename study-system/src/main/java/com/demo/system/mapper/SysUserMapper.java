
package com.demo.system.mapper;

import com.demo.system.pojo.SysUser;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


/**
     * 根据条件分页查询用户对象
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */

    public List<SysUser> selectUserList(SysUser sysUser);
}
