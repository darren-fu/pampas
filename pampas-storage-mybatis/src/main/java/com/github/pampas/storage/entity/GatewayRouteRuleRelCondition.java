package com.github.pampas.storage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatewayRouteRuleRelCondition {
    /**
     * Corresponding to the database table p_gateway_route_rule_rel
     */
    protected String orderByClause;

    /**
     * Corresponding to the database table p_gateway_route_rule_rel
     */
    protected boolean distinct;

    /**
     * Corresponding to the database table p_gateway_route_rule_rel
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    /**
     */
    public GatewayRouteRuleRelCondition() {
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
    public GatewayRouteRuleRelCondition orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     */
    public GatewayRouteRuleRelCondition orderBy(String ... orderByClauses) {
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
     * Corresponding to the database table p_gateway_route_rule_rel
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
        public Criteria andIdEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
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
        public Criteria andGatewayInstanceIdEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdNotEqualTo(String value) {
            addCriterion("gateway_instance_id <>", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdGreaterThan(String value) {
            addCriterion("gateway_instance_id >", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_instance_id >=", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLessThan(String value) {
            addCriterion("gateway_instance_id <", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("gateway_instance_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGatewayInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("gateway_instance_id <=", value, "gatewayInstanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGatewayInstanceIdLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
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

        public Criteria andRouteRuleIdIsNull() {
            addCriterion("route_rule_id is null");
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdIsNotNull() {
            addCriterion("route_rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdEqualTo(Integer value) {
            addCriterion("route_rule_id =", value, "routeRuleId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRouteRuleIdEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("route_rule_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdNotEqualTo(Integer value) {
            addCriterion("route_rule_id <>", value, "routeRuleId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRouteRuleIdNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("route_rule_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdGreaterThan(Integer value) {
            addCriterion("route_rule_id >", value, "routeRuleId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRouteRuleIdGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("route_rule_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("route_rule_id >=", value, "routeRuleId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRouteRuleIdGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("route_rule_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdLessThan(Integer value) {
            addCriterion("route_rule_id <", value, "routeRuleId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRouteRuleIdLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("route_rule_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("route_rule_id <=", value, "routeRuleId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRouteRuleIdLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("route_rule_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdIn(List<Integer> values) {
            addCriterion("route_rule_id in", values, "routeRuleId");
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdNotIn(List<Integer> values) {
            addCriterion("route_rule_id not in", values, "routeRuleId");
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("route_rule_id between", value1, value2, "routeRuleId");
            return (Criteria) this;
        }

        public Criteria andRouteRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("route_rule_id not between", value1, value2, "routeRuleId");
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
        public Criteria andStatusEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("`status` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("`status` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("`status` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("`status` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("`status` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
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
        public Criteria andRemarkEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("remark = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("remark <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("remark > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("remark >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("remark < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
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
        public Criteria andInsertTimeEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("insert_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("insert_time <>", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("insert_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("insert_time >", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("insert_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_time >=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("insert_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("insert_time <", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("insert_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("insert_time <=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
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
        public Criteria andUpdateTimeEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("update_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("update_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("update_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("update_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("update_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
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
        public Criteria andIsDelEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("is_del = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelNotEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("is_del <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("is_del > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("is_del >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanColumn(GatewayRouteRuleRel.Column column) {
            addCriterion(new StringBuilder("is_del < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanOrEqualToColumn(GatewayRouteRuleRel.Column column) {
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

        public Criteria andGatewayInstanceIdLikeInsensitive(String value) {
            addCriterion("upper(gateway_instance_id) like", value.toUpperCase(), "gatewayInstanceId");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    /**
     * Corresponding to the database table p_gateway_route_rule_rel
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * Corresponding to the database table p_gateway_route_rule_rel
         */
        private GatewayRouteRuleRelCondition example;

        /**
         */
        protected Criteria(GatewayRouteRuleRelCondition example) {
            super();
            this.example = example;
        }

        /**
         */
        public GatewayRouteRuleRelCondition example() {
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
     * Corresponding to the database table p_gateway_route_rule_rel
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