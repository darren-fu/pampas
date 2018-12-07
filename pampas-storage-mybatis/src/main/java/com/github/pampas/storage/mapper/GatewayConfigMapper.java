package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.GatewayConfig;
import com.github.pampas.storage.entity.GatewayConfigCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GatewayConfigMapper {
    /**
     */
    long countByExample(GatewayConfigCondition example);

    /**
     */
    int deleteByExample(GatewayConfigCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(GatewayConfig record);

    /**
     */
    int insertSelective(GatewayConfig record);

    /**
     */
    List<GatewayConfig> selectByExample(GatewayConfigCondition example);

    /**
     */
    GatewayConfig selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") GatewayConfig record, @Param("example") GatewayConfigCondition example);

    /**
     */
    int updateByExample(@Param("record") GatewayConfig record, @Param("example") GatewayConfigCondition example);

    /**
     */
    int updateByPrimaryKeySelective(GatewayConfig record);

    /**
     */
    int updateByPrimaryKey(GatewayConfig record);
}