package com.github.pampas.storage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatewaySpiCondition {
    /**
     * Corresponding to the database table p_gateway_spi
     */
    protected String orderByClause;

    /**
     * Corresponding to the database table p_gateway_spi
     */
    protected boolean distinct;

    /**
     * Corresponding to the database table p_gateway_spi
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    /**
     */
    public GatewaySpiCondition() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     */
    public GatewaySpiCondition orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     */
    public GatewaySpiCondition orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    /**
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setPageInfo(Integer currentPage, Integer pageSize) {
        if(pageSize<1) throw new IllegalArgumentException("页大小不能小于1！");
        this.limit=pageSize;
        if(currentPage<1) throw new IllegalArgumentException("页数不能小于1！");
        this.offset=(currentPage-1)*pageSize;
    }

    /**
     * Corresponding to the database table p_gateway_spi
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupIsNull() {
            addCriterion("gateway_group is null");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupIsNotNull() {
            addCriterion("gateway_group is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupEqualTo(String value) {
            addCriterion("gateway_group =", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_group = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupNotEqualTo(String value) {
            addCriterion("gateway_group <>", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_group <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupGreaterThan(String value) {
            addCriterion("gateway_group >", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_group > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_group >=", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_group >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupLessThan(String value) {
            addCriterion("gateway_group <", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_group < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupLessThanOrEqualTo(String value) {
            addCriterion("gateway_group <=", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_group <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupLike(String value) {
            addCriterion("gateway_group like", value, "gatewayGroup");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupNotLike(String value) {
            addCriterion("gateway_group not like", value, "gatewayGroup");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupIn(List<String> values) {
            addCriterion("gateway_group in", values, "gatewayGroup");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupNotIn(List<String> values) {
            addCriterion("gateway_group not in", values, "gatewayGroup");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupBetween(String value1, String value2) {
            addCriterion("gateway_group between", value1, value2, "gatewayGroup");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupNotBetween(String value1, String value2) {
            addCriterion("gateway_group not between", value1, value2, "gatewayGroup");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdIsNull() {
            addCriterion("gateway_instance_id is null");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdIsNotNull() {
            addCriterion("gateway_instance_id is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdEqualTo(String value) {
            addCriterion("gateway_instance_id =", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdNotEqualTo(String value) {
            addCriterion("gateway_instance_id <>", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdGreaterThan(String value) {
            addCriterion("gateway_instance_id >", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_instance_id >=", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLessThan(String value) {
            addCriterion("gateway_instance_id <", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("gateway_instance_id <=", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLike(String value) {
            addCriterion("gateway_instance_id like", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdNotLike(String value) {
            addCriterion("gateway_instance_id not like", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdIn(List<String> values) {
            addCriterion("gateway_instance_id in", values, "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdNotIn(List<String> values) {
            addCriterion("gateway_instance_id not in", values, "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdBetween(String value1, String value2) {
            addCriterion("gateway_instance_id between", value1, value2, "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdNotBetween(String value1, String value2) {
            addCriterion("gateway_instance_id not between", value1, value2, "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceIsNull() {
            addCriterion("spi_interface is null");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceIsNotNull() {
            addCriterion("spi_interface is not null");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceEqualTo(String value) {
            addCriterion("spi_interface =", value, "spiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiInterfaceEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_interface = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceNotEqualTo(String value) {
            addCriterion("spi_interface <>", value, "spiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiInterfaceNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_interface <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceGreaterThan(String value) {
            addCriterion("spi_interface >", value, "spiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiInterfaceGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_interface > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceGreaterThanOrEqualTo(String value) {
            addCriterion("spi_interface >=", value, "spiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiInterfaceGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_interface >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceLessThan(String value) {
            addCriterion("spi_interface <", value, "spiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiInterfaceLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_interface < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceLessThanOrEqualTo(String value) {
            addCriterion("spi_interface <=", value, "spiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiInterfaceLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_interface <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceLike(String value) {
            addCriterion("spi_interface like", value, "spiInterface");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceNotLike(String value) {
            addCriterion("spi_interface not like", value, "spiInterface");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceIn(List<String> values) {
            addCriterion("spi_interface in", values, "spiInterface");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceNotIn(List<String> values) {
            addCriterion("spi_interface not in", values, "spiInterface");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceBetween(String value1, String value2) {
            addCriterion("spi_interface between", value1, value2, "spiInterface");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceNotBetween(String value1, String value2) {
            addCriterion("spi_interface not between", value1, value2, "spiInterface");
            return (Criteria) this;
        }

        public Criteria andSpiClassIsNull() {
            addCriterion("spi_class is null");
            return (Criteria) this;
        }

        public Criteria andSpiClassIsNotNull() {
            addCriterion("spi_class is not null");
            return (Criteria) this;
        }

        public Criteria andSpiClassEqualTo(String value) {
            addCriterion("spi_class =", value, "spiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiClassEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_class = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiClassNotEqualTo(String value) {
            addCriterion("spi_class <>", value, "spiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiClassNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_class <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiClassGreaterThan(String value) {
            addCriterion("spi_class >", value, "spiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiClassGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_class > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiClassGreaterThanOrEqualTo(String value) {
            addCriterion("spi_class >=", value, "spiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiClassGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_class >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiClassLessThan(String value) {
            addCriterion("spi_class <", value, "spiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiClassLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_class < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiClassLessThanOrEqualTo(String value) {
            addCriterion("spi_class <=", value, "spiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiClassLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_class <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiClassLike(String value) {
            addCriterion("spi_class like", value, "spiClass");
            return (Criteria) this;
        }

        public Criteria andSpiClassNotLike(String value) {
            addCriterion("spi_class not like", value, "spiClass");
            return (Criteria) this;
        }

        public Criteria andSpiClassIn(List<String> values) {
            addCriterion("spi_class in", values, "spiClass");
            return (Criteria) this;
        }

        public Criteria andSpiClassNotIn(List<String> values) {
            addCriterion("spi_class not in", values, "spiClass");
            return (Criteria) this;
        }

        public Criteria andSpiClassBetween(String value1, String value2) {
            addCriterion("spi_class between", value1, value2, "spiClass");
            return (Criteria) this;
        }

        public Criteria andSpiClassNotBetween(String value1, String value2) {
            addCriterion("spi_class not between", value1, value2, "spiClass");
            return (Criteria) this;
        }

        public Criteria andSpiNameIsNull() {
            addCriterion("spi_name is null");
            return (Criteria) this;
        }

        public Criteria andSpiNameIsNotNull() {
            addCriterion("spi_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpiNameEqualTo(String value) {
            addCriterion("spi_name =", value, "spiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiNameEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiNameNotEqualTo(String value) {
            addCriterion("spi_name <>", value, "spiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiNameNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiNameGreaterThan(String value) {
            addCriterion("spi_name >", value, "spiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiNameGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiNameGreaterThanOrEqualTo(String value) {
            addCriterion("spi_name >=", value, "spiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiNameGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiNameLessThan(String value) {
            addCriterion("spi_name <", value, "spiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiNameLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiNameLessThanOrEqualTo(String value) {
            addCriterion("spi_name <=", value, "spiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiNameLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiNameLike(String value) {
            addCriterion("spi_name like", value, "spiName");
            return (Criteria) this;
        }

        public Criteria andSpiNameNotLike(String value) {
            addCriterion("spi_name not like", value, "spiName");
            return (Criteria) this;
        }

        public Criteria andSpiNameIn(List<String> values) {
            addCriterion("spi_name in", values, "spiName");
            return (Criteria) this;
        }

        public Criteria andSpiNameNotIn(List<String> values) {
            addCriterion("spi_name not in", values, "spiName");
            return (Criteria) this;
        }

        public Criteria andSpiNameBetween(String value1, String value2) {
            addCriterion("spi_name between", value1, value2, "spiName");
            return (Criteria) this;
        }

        public Criteria andSpiNameNotBetween(String value1, String value2) {
            addCriterion("spi_name not between", value1, value2, "spiName");
            return (Criteria) this;
        }

        public Criteria andSpiDescIsNull() {
            addCriterion("spi_desc is null");
            return (Criteria) this;
        }

        public Criteria andSpiDescIsNotNull() {
            addCriterion("spi_desc is not null");
            return (Criteria) this;
        }

        public Criteria andSpiDescEqualTo(String value) {
            addCriterion("spi_desc =", value, "spiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiDescEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_desc = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiDescNotEqualTo(String value) {
            addCriterion("spi_desc <>", value, "spiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiDescNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_desc <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiDescGreaterThan(String value) {
            addCriterion("spi_desc >", value, "spiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiDescGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_desc > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiDescGreaterThanOrEqualTo(String value) {
            addCriterion("spi_desc >=", value, "spiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiDescGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_desc >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiDescLessThan(String value) {
            addCriterion("spi_desc <", value, "spiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiDescLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_desc < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiDescLessThanOrEqualTo(String value) {
            addCriterion("spi_desc <=", value, "spiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andSpiDescLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("spi_desc <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSpiDescLike(String value) {
            addCriterion("spi_desc like", value, "spiDesc");
            return (Criteria) this;
        }

        public Criteria andSpiDescNotLike(String value) {
            addCriterion("spi_desc not like", value, "spiDesc");
            return (Criteria) this;
        }

        public Criteria andSpiDescIn(List<String> values) {
            addCriterion("spi_desc in", values, "spiDesc");
            return (Criteria) this;
        }

        public Criteria andSpiDescNotIn(List<String> values) {
            addCriterion("spi_desc not in", values, "spiDesc");
            return (Criteria) this;
        }

        public Criteria andSpiDescBetween(String value1, String value2) {
            addCriterion("spi_desc between", value1, value2, "spiDesc");
            return (Criteria) this;
        }

        public Criteria andSpiDescNotBetween(String value1, String value2) {
            addCriterion("spi_desc not between", value1, value2, "spiDesc");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("`status` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("`status` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("`status` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("`status` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("`status` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("`status` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("remark = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("remark <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("remark > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("remark >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("remark < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("remark <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNull() {
            addCriterion("insert_time is null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNotNull() {
            addCriterion("insert_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeEqualTo(Date value) {
            addCriterion("insert_time =", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("insert_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("insert_time <>", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("insert_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("insert_time >", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("insert_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_time >=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("insert_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("insert_time <", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("insert_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("insert_time <=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("insert_time <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeIn(List<Date> values) {
            addCriterion("insert_time in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotIn(List<Date> values) {
            addCriterion("insert_time not in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeBetween(Date value1, Date value2) {
            addCriterion("insert_time between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotBetween(Date value1, Date value2) {
            addCriterion("insert_time not between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("update_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("update_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("update_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("update_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("update_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("update_time <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Boolean value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("is_del = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelNotEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("is_del <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("is_del > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("is_del >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("is_del < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanOrEqualToColumn(GatewaySpi.Column column) {
            addCriterion(new StringBuilder("is_del <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Boolean> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Boolean> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andGatewayGroupLikeInsensitive(String value) {
            addCriterion("upper(gateway_group) like", value.toUpperCase(), "gatewayGroup");
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLikeInsensitive(String value) {
            addCriterion("upper(gateway_instance_id) like", value.toUpperCase(), "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andSpiInterfaceLikeInsensitive(String value) {
            addCriterion("upper(spi_interface) like", value.toUpperCase(), "spiInterface");
            return (Criteria) this;
        }

        public Criteria andSpiClassLikeInsensitive(String value) {
            addCriterion("upper(spi_class) like", value.toUpperCase(), "spiClass");
            return (Criteria) this;
        }

        public Criteria andSpiNameLikeInsensitive(String value) {
            addCriterion("upper(spi_name) like", value.toUpperCase(), "spiName");
            return (Criteria) this;
        }

        public Criteria andSpiDescLikeInsensitive(String value) {
            addCriterion("upper(spi_desc) like", value.toUpperCase(), "spiDesc");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    /**
     * Corresponding to the database table p_gateway_spi
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * Corresponding to the database table p_gateway_spi
         */
        private GatewaySpiCondition example;

        /**
         */
        protected Criteria(GatewaySpiCondition example) {
            super();
            this.example = example;
        }

        /**
         */
        public GatewaySpiCondition example() {
            return this.example;
        }

        /**
         */
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public interface ICriteriaAdd {
            /**
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * Corresponding to the database table p_gateway_spi
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}