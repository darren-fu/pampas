package com.github.pampas.storage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatewayConfigCondition {
    /**
     * Corresponding to the database table p_gateway_config
     */
    protected String orderByClause;

    /**
     * Corresponding to the database table p_gateway_config
     */
    protected boolean distinct;

    /**
     * Corresponding to the database table p_gateway_config
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    /**
     */
    public GatewayConfigCondition() {
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
    public GatewayConfigCondition orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     */
    public GatewayConfigCondition orderBy(String ... orderByClauses) {
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
     * Corresponding to the database table p_gateway_config
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
        public Criteria andIdEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanOrEqualToColumn(GatewayConfig.Column column) {
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
        public Criteria andGatewayGroupEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_group = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupNotEqualTo(String value) {
            addCriterion("gateway_group <>", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_group <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupGreaterThan(String value) {
            addCriterion("gateway_group >", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_group > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_group >=", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_group >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupLessThan(String value) {
            addCriterion("gateway_group <", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_group < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayGroupLessThanOrEqualTo(String value) {
            addCriterion("gateway_group <=", value, "gatewayGroup");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayGroupLessThanOrEqualToColumn(GatewayConfig.Column column) {
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
        public Criteria andGatewayInstanceIdEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdNotEqualTo(String value) {
            addCriterion("gateway_instance_id <>", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdGreaterThan(String value) {
            addCriterion("gateway_instance_id >", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_instance_id >=", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLessThan(String value) {
            addCriterion("gateway_instance_id <", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("gateway_instance_id <=", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdLessThanOrEqualToColumn(GatewayConfig.Column column) {
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

        public Criteria andConfigSpiInterfaceIsNull() {
            addCriterion("config_spi_interface is null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceIsNotNull() {
            addCriterion("config_spi_interface is not null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceEqualTo(String value) {
            addCriterion("config_spi_interface =", value, "configSpiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceNotEqualTo(String value) {
            addCriterion("config_spi_interface <>", value, "configSpiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceGreaterThan(String value) {
            addCriterion("config_spi_interface >", value, "configSpiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceGreaterThanOrEqualTo(String value) {
            addCriterion("config_spi_interface >=", value, "configSpiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceLessThan(String value) {
            addCriterion("config_spi_interface <", value, "configSpiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceLessThanOrEqualTo(String value) {
            addCriterion("config_spi_interface <=", value, "configSpiInterface");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceLike(String value) {
            addCriterion("config_spi_interface like", value, "configSpiInterface");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceNotLike(String value) {
            addCriterion("config_spi_interface not like", value, "configSpiInterface");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceIn(List<String> values) {
            addCriterion("config_spi_interface in", values, "configSpiInterface");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceNotIn(List<String> values) {
            addCriterion("config_spi_interface not in", values, "configSpiInterface");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceBetween(String value1, String value2) {
            addCriterion("config_spi_interface between", value1, value2, "configSpiInterface");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceNotBetween(String value1, String value2) {
            addCriterion("config_spi_interface not between", value1, value2, "configSpiInterface");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescIsNull() {
            addCriterion("config_spi_interface_desc is null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescIsNotNull() {
            addCriterion("config_spi_interface_desc is not null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescEqualTo(String value) {
            addCriterion("config_spi_interface_desc =", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceDescEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface_desc = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescNotEqualTo(String value) {
            addCriterion("config_spi_interface_desc <>", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceDescNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface_desc <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescGreaterThan(String value) {
            addCriterion("config_spi_interface_desc >", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceDescGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface_desc > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescGreaterThanOrEqualTo(String value) {
            addCriterion("config_spi_interface_desc >=", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceDescGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface_desc >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescLessThan(String value) {
            addCriterion("config_spi_interface_desc <", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceDescLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface_desc < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescLessThanOrEqualTo(String value) {
            addCriterion("config_spi_interface_desc <=", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiInterfaceDescLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_interface_desc <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescLike(String value) {
            addCriterion("config_spi_interface_desc like", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescNotLike(String value) {
            addCriterion("config_spi_interface_desc not like", value, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescIn(List<String> values) {
            addCriterion("config_spi_interface_desc in", values, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescNotIn(List<String> values) {
            addCriterion("config_spi_interface_desc not in", values, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescBetween(String value1, String value2) {
            addCriterion("config_spi_interface_desc between", value1, value2, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescNotBetween(String value1, String value2) {
            addCriterion("config_spi_interface_desc not between", value1, value2, "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassIsNull() {
            addCriterion("config_spi_class is null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassIsNotNull() {
            addCriterion("config_spi_class is not null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassEqualTo(String value) {
            addCriterion("config_spi_class =", value, "configSpiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiClassEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_class = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassNotEqualTo(String value) {
            addCriterion("config_spi_class <>", value, "configSpiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiClassNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_class <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassGreaterThan(String value) {
            addCriterion("config_spi_class >", value, "configSpiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiClassGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_class > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassGreaterThanOrEqualTo(String value) {
            addCriterion("config_spi_class >=", value, "configSpiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiClassGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_class >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassLessThan(String value) {
            addCriterion("config_spi_class <", value, "configSpiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiClassLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_class < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassLessThanOrEqualTo(String value) {
            addCriterion("config_spi_class <=", value, "configSpiClass");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiClassLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_class <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassLike(String value) {
            addCriterion("config_spi_class like", value, "configSpiClass");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassNotLike(String value) {
            addCriterion("config_spi_class not like", value, "configSpiClass");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassIn(List<String> values) {
            addCriterion("config_spi_class in", values, "configSpiClass");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassNotIn(List<String> values) {
            addCriterion("config_spi_class not in", values, "configSpiClass");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassBetween(String value1, String value2) {
            addCriterion("config_spi_class between", value1, value2, "configSpiClass");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassNotBetween(String value1, String value2) {
            addCriterion("config_spi_class not between", value1, value2, "configSpiClass");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameIsNull() {
            addCriterion("config_spi_name is null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameIsNotNull() {
            addCriterion("config_spi_name is not null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameEqualTo(String value) {
            addCriterion("config_spi_name =", value, "configSpiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiNameEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameNotEqualTo(String value) {
            addCriterion("config_spi_name <>", value, "configSpiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiNameNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameGreaterThan(String value) {
            addCriterion("config_spi_name >", value, "configSpiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiNameGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameGreaterThanOrEqualTo(String value) {
            addCriterion("config_spi_name >=", value, "configSpiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiNameGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameLessThan(String value) {
            addCriterion("config_spi_name <", value, "configSpiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiNameLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameLessThanOrEqualTo(String value) {
            addCriterion("config_spi_name <=", value, "configSpiName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiNameLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameLike(String value) {
            addCriterion("config_spi_name like", value, "configSpiName");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameNotLike(String value) {
            addCriterion("config_spi_name not like", value, "configSpiName");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameIn(List<String> values) {
            addCriterion("config_spi_name in", values, "configSpiName");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameNotIn(List<String> values) {
            addCriterion("config_spi_name not in", values, "configSpiName");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameBetween(String value1, String value2) {
            addCriterion("config_spi_name between", value1, value2, "configSpiName");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameNotBetween(String value1, String value2) {
            addCriterion("config_spi_name not between", value1, value2, "configSpiName");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescIsNull() {
            addCriterion("config_spi_desc is null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescIsNotNull() {
            addCriterion("config_spi_desc is not null");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescEqualTo(String value) {
            addCriterion("config_spi_desc =", value, "configSpiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiDescEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_desc = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescNotEqualTo(String value) {
            addCriterion("config_spi_desc <>", value, "configSpiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiDescNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_desc <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescGreaterThan(String value) {
            addCriterion("config_spi_desc >", value, "configSpiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiDescGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_desc > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescGreaterThanOrEqualTo(String value) {
            addCriterion("config_spi_desc >=", value, "configSpiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiDescGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_desc >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescLessThan(String value) {
            addCriterion("config_spi_desc <", value, "configSpiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiDescLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_desc < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescLessThanOrEqualTo(String value) {
            addCriterion("config_spi_desc <=", value, "configSpiDesc");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andConfigSpiDescLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("config_spi_desc <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescLike(String value) {
            addCriterion("config_spi_desc like", value, "configSpiDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescNotLike(String value) {
            addCriterion("config_spi_desc not like", value, "configSpiDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescIn(List<String> values) {
            addCriterion("config_spi_desc in", values, "configSpiDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescNotIn(List<String> values) {
            addCriterion("config_spi_desc not in", values, "configSpiDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescBetween(String value1, String value2) {
            addCriterion("config_spi_desc between", value1, value2, "configSpiDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescNotBetween(String value1, String value2) {
            addCriterion("config_spi_desc not between", value1, value2, "configSpiDesc");
            return (Criteria) this;
        }

        public Criteria andKeyIsNull() {
            addCriterion("`key` is null");
            return (Criteria) this;
        }

        public Criteria andKeyIsNotNull() {
            addCriterion("`key` is not null");
            return (Criteria) this;
        }

        public Criteria andKeyEqualTo(String value) {
            addCriterion("`key` =", value, "key");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andKeyEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`key` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKeyNotEqualTo(String value) {
            addCriterion("`key` <>", value, "key");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andKeyNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`key` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThan(String value) {
            addCriterion("`key` >", value, "key");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andKeyGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`key` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThanOrEqualTo(String value) {
            addCriterion("`key` >=", value, "key");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andKeyGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`key` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKeyLessThan(String value) {
            addCriterion("`key` <", value, "key");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andKeyLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`key` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKeyLessThanOrEqualTo(String value) {
            addCriterion("`key` <=", value, "key");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andKeyLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`key` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKeyLike(String value) {
            addCriterion("`key` like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotLike(String value) {
            addCriterion("`key` not like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyIn(List<String> values) {
            addCriterion("`key` in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotIn(List<String> values) {
            addCriterion("`key` not in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyBetween(String value1, String value2) {
            addCriterion("`key` between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotBetween(String value1, String value2) {
            addCriterion("`key` not between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("`label` is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("`label` is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("`label` =", value, "label");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andLabelEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`label` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("`label` <>", value, "label");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andLabelNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`label` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("`label` >", value, "label");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andLabelGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`label` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("`label` >=", value, "label");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andLabelGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`label` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("`label` <", value, "label");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andLabelLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`label` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("`label` <=", value, "label");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andLabelLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`label` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("`label` like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("`label` not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("`label` in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("`label` not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("`label` between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("`label` not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("default_value is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("default_value is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("default_value =", value, "defaultValue");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andDefaultValueEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("default_value = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("default_value <>", value, "defaultValue");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andDefaultValueNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("default_value <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("default_value >", value, "defaultValue");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andDefaultValueGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("default_value > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("default_value >=", value, "defaultValue");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andDefaultValueGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("default_value >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("default_value <", value, "defaultValue");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andDefaultValueLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("default_value < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("default_value <=", value, "defaultValue");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andDefaultValueLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("default_value <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("default_value not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("default_value in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("default_value not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("default_value between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("default_value not between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andPlaceholderIsNull() {
            addCriterion("placeholder is null");
            return (Criteria) this;
        }

        public Criteria andPlaceholderIsNotNull() {
            addCriterion("placeholder is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceholderEqualTo(String value) {
            addCriterion("placeholder =", value, "placeholder");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPlaceholderEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("placeholder = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotEqualTo(String value) {
            addCriterion("placeholder <>", value, "placeholder");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPlaceholderNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("placeholder <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlaceholderGreaterThan(String value) {
            addCriterion("placeholder >", value, "placeholder");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPlaceholderGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("placeholder > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlaceholderGreaterThanOrEqualTo(String value) {
            addCriterion("placeholder >=", value, "placeholder");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPlaceholderGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("placeholder >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlaceholderLessThan(String value) {
            addCriterion("placeholder <", value, "placeholder");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPlaceholderLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("placeholder < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlaceholderLessThanOrEqualTo(String value) {
            addCriterion("placeholder <=", value, "placeholder");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPlaceholderLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("placeholder <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlaceholderLike(String value) {
            addCriterion("placeholder like", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotLike(String value) {
            addCriterion("placeholder not like", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderIn(List<String> values) {
            addCriterion("placeholder in", values, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotIn(List<String> values) {
            addCriterion("placeholder not in", values, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderBetween(String value1, String value2) {
            addCriterion("placeholder between", value1, value2, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotBetween(String value1, String value2) {
            addCriterion("placeholder not between", value1, value2, "placeholder");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("`value` is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("`value` is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(String value) {
            addCriterion("`value` =", value, "value");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andValueEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`value` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("`value` <>", value, "value");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andValueNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`value` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("`value` >", value, "value");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andValueGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`value` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("`value` >=", value, "value");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andValueGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`value` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("`value` <", value, "value");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andValueLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`value` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("`value` <=", value, "value");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andValueLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`value` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("`value` like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("`value` not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("`value` in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("`value` not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("`value` between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("`value` not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNull() {
            addCriterion("required is null");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNotNull() {
            addCriterion("required is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredEqualTo(Boolean value) {
            addCriterion("required =", value, "required");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRequiredEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("required = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRequiredNotEqualTo(Boolean value) {
            addCriterion("required <>", value, "required");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRequiredNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("required <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThan(Boolean value) {
            addCriterion("required >", value, "required");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRequiredGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("required > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("required >=", value, "required");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRequiredGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("required >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRequiredLessThan(Boolean value) {
            addCriterion("required <", value, "required");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRequiredLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("required < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRequiredLessThanOrEqualTo(Boolean value) {
            addCriterion("required <=", value, "required");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRequiredLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("required <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRequiredIn(List<Boolean> values) {
            addCriterion("required in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotIn(List<Boolean> values) {
            addCriterion("required not in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredBetween(Boolean value1, Boolean value2) {
            addCriterion("required between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("required not between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andMultiIsNull() {
            addCriterion("multi is null");
            return (Criteria) this;
        }

        public Criteria andMultiIsNotNull() {
            addCriterion("multi is not null");
            return (Criteria) this;
        }

        public Criteria andMultiEqualTo(Boolean value) {
            addCriterion("multi =", value, "multi");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andMultiEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("multi = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMultiNotEqualTo(Boolean value) {
            addCriterion("multi <>", value, "multi");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andMultiNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("multi <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMultiGreaterThan(Boolean value) {
            addCriterion("multi >", value, "multi");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andMultiGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("multi > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMultiGreaterThanOrEqualTo(Boolean value) {
            addCriterion("multi >=", value, "multi");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andMultiGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("multi >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMultiLessThan(Boolean value) {
            addCriterion("multi <", value, "multi");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andMultiLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("multi < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMultiLessThanOrEqualTo(Boolean value) {
            addCriterion("multi <=", value, "multi");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andMultiLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("multi <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMultiIn(List<Boolean> values) {
            addCriterion("multi in", values, "multi");
            return (Criteria) this;
        }

        public Criteria andMultiNotIn(List<Boolean> values) {
            addCriterion("multi not in", values, "multi");
            return (Criteria) this;
        }

        public Criteria andMultiBetween(Boolean value1, Boolean value2) {
            addCriterion("multi between", value1, value2, "multi");
            return (Criteria) this;
        }

        public Criteria andMultiNotBetween(Boolean value1, Boolean value2) {
            addCriterion("multi not between", value1, value2, "multi");
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
        public Criteria andStatusEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`status` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`status` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`status` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`status` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("`status` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanOrEqualToColumn(GatewayConfig.Column column) {
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

        public Criteria andPushIsNull() {
            addCriterion("push is null");
            return (Criteria) this;
        }

        public Criteria andPushIsNotNull() {
            addCriterion("push is not null");
            return (Criteria) this;
        }

        public Criteria andPushEqualTo(String value) {
            addCriterion("push =", value, "push");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPushEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("push = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPushNotEqualTo(String value) {
            addCriterion("push <>", value, "push");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPushNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("push <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPushGreaterThan(String value) {
            addCriterion("push >", value, "push");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPushGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("push > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPushGreaterThanOrEqualTo(String value) {
            addCriterion("push >=", value, "push");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPushGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("push >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPushLessThan(String value) {
            addCriterion("push <", value, "push");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPushLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("push < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPushLessThanOrEqualTo(String value) {
            addCriterion("push <=", value, "push");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPushLessThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("push <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPushLike(String value) {
            addCriterion("push like", value, "push");
            return (Criteria) this;
        }

        public Criteria andPushNotLike(String value) {
            addCriterion("push not like", value, "push");
            return (Criteria) this;
        }

        public Criteria andPushIn(List<String> values) {
            addCriterion("push in", values, "push");
            return (Criteria) this;
        }

        public Criteria andPushNotIn(List<String> values) {
            addCriterion("push not in", values, "push");
            return (Criteria) this;
        }

        public Criteria andPushBetween(String value1, String value2) {
            addCriterion("push between", value1, value2, "push");
            return (Criteria) this;
        }

        public Criteria andPushNotBetween(String value1, String value2) {
            addCriterion("push not between", value1, value2, "push");
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
        public Criteria andRemarkEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("remark = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("remark <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("remark > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("remark >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("remark < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanOrEqualToColumn(GatewayConfig.Column column) {
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
        public Criteria andInsertTimeEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("insert_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("insert_time <>", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("insert_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("insert_time >", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("insert_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_time >=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("insert_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("insert_time <", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("insert_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("insert_time <=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanOrEqualToColumn(GatewayConfig.Column column) {
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
        public Criteria andUpdateTimeEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("update_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("update_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("update_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("update_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("update_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanOrEqualToColumn(GatewayConfig.Column column) {
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
        public Criteria andIsDelEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("is_del = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelNotEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("is_del <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("is_del > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanOrEqualToColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("is_del >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanColumn(GatewayConfig.Column column) {
            addCriterion(new StringBuilder("is_del < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanOrEqualToColumn(GatewayConfig.Column column) {
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

        public Criteria andConfigSpiInterfaceLikeInsensitive(String value) {
            addCriterion("upper(config_spi_interface) like", value.toUpperCase(), "configSpiInterface");
            return (Criteria) this;
        }

        public Criteria andConfigSpiInterfaceDescLikeInsensitive(String value) {
            addCriterion("upper(config_spi_interface_desc) like", value.toUpperCase(), "configSpiInterfaceDesc");
            return (Criteria) this;
        }

        public Criteria andConfigSpiClassLikeInsensitive(String value) {
            addCriterion("upper(config_spi_class) like", value.toUpperCase(), "configSpiClass");
            return (Criteria) this;
        }

        public Criteria andConfigSpiNameLikeInsensitive(String value) {
            addCriterion("upper(config_spi_name) like", value.toUpperCase(), "configSpiName");
            return (Criteria) this;
        }

        public Criteria andConfigSpiDescLikeInsensitive(String value) {
            addCriterion("upper(config_spi_desc) like", value.toUpperCase(), "configSpiDesc");
            return (Criteria) this;
        }

        public Criteria andKeyLikeInsensitive(String value) {
            addCriterion("upper(`key`) like", value.toUpperCase(), "key");
            return (Criteria) this;
        }

        public Criteria andLabelLikeInsensitive(String value) {
            addCriterion("upper(`label`) like", value.toUpperCase(), "label");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLikeInsensitive(String value) {
            addCriterion("upper(default_value) like", value.toUpperCase(), "defaultValue");
            return (Criteria) this;
        }

        public Criteria andPlaceholderLikeInsensitive(String value) {
            addCriterion("upper(placeholder) like", value.toUpperCase(), "placeholder");
            return (Criteria) this;
        }

        public Criteria andValueLikeInsensitive(String value) {
            addCriterion("upper(`value`) like", value.toUpperCase(), "value");
            return (Criteria) this;
        }

        public Criteria andPushLikeInsensitive(String value) {
            addCriterion("upper(push) like", value.toUpperCase(), "push");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    /**
     * Corresponding to the database table p_gateway_config
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * Corresponding to the database table p_gateway_config
         */
        private GatewayConfigCondition example;

        /**
         */
        protected Criteria(GatewayConfigCondition example) {
            super();
            this.example = example;
        }

        /**
         */
        public GatewayConfigCondition example() {
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
     * Corresponding to the database table p_gateway_config
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