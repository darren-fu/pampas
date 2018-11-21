package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.GatewayInstance;
import com.github.pampas.storage.entity.GatewayInstanceCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GatewayInstanceMapper {
    /**
     */
    long countByExample(GatewayInstanceCondition example);

    /**
     */
    int deleteByExample(GatewayInstanceCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(GatewayInstance record);

    /**
     */
    int insertSelective(GatewayInstance record);

    /**
     */
    List<GatewayInstance> selectByExample(GatewayInstanceCondition example);

    /**
     */
    GatewayInstance selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") GatewayInstance record, @Param("example") GatewayInstanceCondition example);

    /**
     */
    int updateByExample(@Param("record") GatewayInstance record, @Param("example") GatewayInstanceCondition example);

    /**
     */
    int updateByPrimaryKeySelective(GatewayInstance record);

    /**
     */
    int updateByPrimaryKey(GatewayInstance record);
}