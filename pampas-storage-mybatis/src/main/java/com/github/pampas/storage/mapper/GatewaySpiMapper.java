package com.github.pampas.storage.mapper;

import com.github.pampas.storage.entity.GatewaySpi;
import com.github.pampas.storage.entity.GatewaySpiCondition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GatewaySpiMapper {
    /**
     */
    long countByExample(GatewaySpiCondition example);

    /**
     */
    int deleteByExample(GatewaySpiCondition example);

    /**
     */
    int deleteByPrimaryKey(Integer id);

    /**
     */
    int insert(GatewaySpi record);

    /**
     */
    int insertSelective(GatewaySpi record);

    /**
     */
    List<GatewaySpi> selectByExample(GatewaySpiCondition example);

    /**
     */
    GatewaySpi selectByPrimaryKey(Integer id);

    /**
     */
    int updateByExampleSelective(@Param("record") GatewaySpi record, @Param("example") GatewaySpiCondition example);

    /**
     */
    int updateByExample(@Param("record") GatewaySpi record, @Param("example") GatewaySpiCondition example);

    /**
     */
    int updateByPrimaryKeySelective(GatewaySpi record);

    /**
     */
    int updateByPrimaryKey(GatewaySpi record);
}