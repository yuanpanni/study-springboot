
package com.demo.system.mapper;

import com.demo.system.pojo.SysJob;
import org.apache.ibatis.annotations.Param;

public interface SysJobMapper {
    int deleteByPrimaryKey(@Param("jobId") Integer jobId, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    int insert(SysJob record);

    int insertSelective(SysJob record);

    SysJob selectByPrimaryKey(@Param("jobId") Integer jobId, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    int updateByPrimaryKeySelective(SysJob record);

    int updateByPrimaryKey(SysJob record);
}
