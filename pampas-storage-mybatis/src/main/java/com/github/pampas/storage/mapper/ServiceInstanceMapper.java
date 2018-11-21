package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.ServiceInstance;
import com.github.pampas.storage.entity.ServiceInstanceCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ServiceInstanceMapper {
    /**
     */
    long countByExample(ServiceInstanceCondition example);

    /**
     */
    int deleteByExample(ServiceInstanceCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(ServiceInstance record);

    /**
     */
    int insertSelective(ServiceInstance record);

    /**
     */
    List<ServiceInstance> selectByExample(ServiceInstanceCondition example);

    /**
     */
    ServiceInstance selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") ServiceInstance record, @Param("example") ServiceInstanceCondition example);

    /**
     */
    int updateByExample(@Param("record") ServiceInstance record, @Param("example") ServiceInstanceCondition example);

    /**
     */
    int updateByPrimaryKeySelective(ServiceInstance record);

    /**
     */
    int updateByPrimaryKey(ServiceInstance record);
}