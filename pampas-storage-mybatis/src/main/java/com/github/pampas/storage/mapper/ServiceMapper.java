package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.Service;
import com.github.pampas.storage.entity.ServiceCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ServiceMapper {
    /**
     */
    long countByExample(ServiceCondition example);

    /**
     */
    int deleteByExample(ServiceCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(Service record);

    /**
     */
    int insertSelective(Service record);

    /**
     */
    List<Service> selectByExample(ServiceCondition example);

    /**
     */
    Service selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") Service record, @Param("example") ServiceCondition example);

    /**
     */
    int updateByExample(@Param("record") Service record, @Param("example") ServiceCondition example);

    /**
     */
    int updateByPrimaryKeySelective(Service record);

    /**
     */
    int updateByPrimaryKey(Service record);
}