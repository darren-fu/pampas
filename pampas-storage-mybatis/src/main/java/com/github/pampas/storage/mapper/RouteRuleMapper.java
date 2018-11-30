package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.RouteRule;
import com.github.pampas.storage.entity.RouteRuleCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RouteRuleMapper {
    /**
     */
    long countByExample(RouteRuleCondition example);

    /**
     */
    int deleteByExample(RouteRuleCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(RouteRule record);

    /**
     */
    int insertSelective(RouteRule record);

    /**
     */
    List<RouteRule> selectByExampleWithBLOBs(RouteRuleCondition example);

    /**
     */
    List<RouteRule> selectByExample(RouteRuleCondition example);

    /**
     */
    RouteRule selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") RouteRule record, @Param("example") RouteRuleCondition example);

    /**
     */
    int updateByExampleWithBLOBs(@Param("record") RouteRule record, @Param("example") RouteRuleCondition example);

    /**
     */
    int updateByExample(@Param("record") RouteRule record, @Param("example") RouteRuleCondition example);

    /**
     */
    int updateByPrimaryKeySelective(RouteRule record);

    /**
     */
    int updateByPrimaryKeyWithBLOBs(RouteRule record);

    /**
     */
    int updateByPrimaryKey(RouteRule record);
}