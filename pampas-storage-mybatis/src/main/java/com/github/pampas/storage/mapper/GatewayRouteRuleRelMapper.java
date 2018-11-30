package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.GatewayRouteRuleRel;
import com.github.pampas.storage.entity.GatewayRouteRuleRelCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GatewayRouteRuleRelMapper {
    /**
     */
    long countByExample(GatewayRouteRuleRelCondition example);

    /**
     */
    int deleteByExample(GatewayRouteRuleRelCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(GatewayRouteRuleRel record);

    /**
     */
    int insertSelective(GatewayRouteRuleRel record);

    /**
     */
    List<GatewayRouteRuleRel> selectByExample(GatewayRouteRuleRelCondition example);

    /**
     */
    GatewayRouteRuleRel selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") GatewayRouteRuleRel record, @Param("example") GatewayRouteRuleRelCondition example);

    /**
     */
    int updateByExample(@Param("record") GatewayRouteRuleRel record, @Param("example") GatewayRouteRuleRelCondition example);

    /**
     */
    int updateByPrimaryKeySelective(GatewayRouteRuleRel record);

    /**
     */
    int updateByPrimaryKey(GatewayRouteRuleRel record);
}