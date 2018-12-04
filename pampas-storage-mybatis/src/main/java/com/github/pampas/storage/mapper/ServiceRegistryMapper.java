package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.ServiceRegistry;
import com.github.pampas.storage.entity.ServiceRegistryCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ServiceRegistryMapper {
    /**
     */
    long countByExample(ServiceRegistryCondition example);

    /**
     */
    int deleteByExample(ServiceRegistryCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(ServiceRegistry record);

    /**
     */
    int insertSelective(ServiceRegistry record);

    /**
     */
    List<ServiceRegistry> selectByExample(ServiceRegistryCondition example);

    /**
     */
    ServiceRegistry selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") ServiceRegistry record, @Param("example") ServiceRegistryCondition example);

    /**
     */
    int updateByExample(@Param("record") ServiceRegistry record, @Param("example") ServiceRegistryCondition example);

    /**
     */
    int updateByPrimaryKeySelective(ServiceRegistry record);

    /**
     */
    int updateByPrimaryKey(ServiceRegistry record);
}