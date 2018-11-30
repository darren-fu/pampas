package com.github.pampas.storage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatewayInstanceCondition {
    /**
     * Corresponding to the database table p_gateway_instance
     */
    protected String orderByClause;

    /**
     * Corresponding to the database table p_gateway_instance
     */
    protected boolean distinct;

    /**
     * Corresponding to the database table p_gateway_instance
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    /**
     */
    public GatewayInstanceCondition() {
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
    public GatewayInstanceCondition orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     */
    public GatewayInstanceCondition orderBy(String ... orderByClauses) {
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
     * Corresponding to the database table p_gateway_instance
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
        public Criteria andIdEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIdLessThanOrEqualToColumn(GatewayInstance.Column column) {
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

        public Criteria andInstanceIdIsNull() {
            addCriterion("instance_id is null");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIsNotNull() {
            addCriterion("instance_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstanceIdEqualTo(String value) {
            addCriterion("instance_id =", value, "instanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInstanceIdEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("instance_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotEqualTo(String value) {
            addCriterion("instance_id <>", value, "instanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInstanceIdNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("instance_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstanceIdGreaterThan(String value) {
            addCriterion("instance_id >", value, "instanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInstanceIdGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("instance_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("instance_id >=", value, "instanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInstanceIdGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("instance_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstanceIdLessThan(String value) {
            addCriterion("instance_id <", value, "instanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInstanceIdLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("instance_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("instance_id <=", value, "instanceId");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInstanceIdLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("instance_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInstanceIdLike(String value) {
            addCriterion("instance_id like", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotLike(String value) {
            addCriterion("instance_id not like", value, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdIn(List<String> values) {
            addCriterion("instance_id in", values, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotIn(List<String> values) {
            addCriterion("instance_id not in", values, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdBetween(String value1, String value2) {
            addCriterion("instance_id between", value1, value2, "instanceId");
            return (Criteria) this;
        }

        public Criteria andInstanceIdNotBetween(String value1, String value2) {
            addCriterion("instance_id not between", value1, value2, "instanceId");
            return (Criteria) this;
        }

        public Criteria andGroupIsNull() {
            addCriterion("`group` is null");
            return (Criteria) this;
        }

        public Criteria andGroupIsNotNull() {
            addCriterion("`group` is not null");
            return (Criteria) this;
        }

        public Criteria andGroupEqualTo(String value) {
            addCriterion("`group` =", value, "group");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGroupEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`group` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addCriterion("`group` <>", value, "group");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGroupNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`group` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addCriterion("`group` >", value, "group");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGroupGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`group` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addCriterion("`group` >=", value, "group");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGroupGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`group` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addCriterion("`group` <", value, "group");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGroupLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`group` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addCriterion("`group` <=", value, "group");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andGroupLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`group` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLike(String value) {
            addCriterion("`group` like", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotLike(String value) {
            addCriterion("`group` not like", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupIn(List<String> values) {
            addCriterion("`group` in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotIn(List<String> values) {
            addCriterion("`group` not in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupBetween(String value1, String value2) {
            addCriterion("`group` between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotBetween(String value1, String value2) {
            addCriterion("`group` not between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andServerNameIsNull() {
            addCriterion("`server_name` is null");
            return (Criteria) this;
        }

        public Criteria andServerNameIsNotNull() {
            addCriterion("`server_name` is not null");
            return (Criteria) this;
        }

        public Criteria andServerNameEqualTo(String value) {
            addCriterion("`server_name` =", value, "serverName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andServerNameEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`server_name` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andServerNameNotEqualTo(String value) {
            addCriterion("`server_name` <>", value, "serverName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andServerNameNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`server_name` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andServerNameGreaterThan(String value) {
            addCriterion("`server_name` >", value, "serverName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andServerNameGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`server_name` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andServerNameGreaterThanOrEqualTo(String value) {
            addCriterion("`server_name` >=", value, "serverName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andServerNameGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`server_name` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andServerNameLessThan(String value) {
            addCriterion("`server_name` <", value, "serverName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andServerNameLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`server_name` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andServerNameLessThanOrEqualTo(String value) {
            addCriterion("`server_name` <=", value, "serverName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andServerNameLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`server_name` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andServerNameLike(String value) {
            addCriterion("`server_name` like", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameNotLike(String value) {
            addCriterion("`server_name` not like", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameIn(List<String> values) {
            addCriterion("`server_name` in", values, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameNotIn(List<String> values) {
            addCriterion("`server_name` not in", values, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameBetween(String value1, String value2) {
            addCriterion("`server_name` between", value1, value2, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameNotBetween(String value1, String value2) {
            addCriterion("`server_name` not between", value1, value2, "serverName");
            return (Criteria) this;
        }

        public Criteria andHostIsNull() {
            addCriterion("`host` is null");
            return (Criteria) this;
        }

        public Criteria andHostIsNotNull() {
            addCriterion("`host` is not null");
            return (Criteria) this;
        }

        public Criteria andHostEqualTo(String value) {
            addCriterion("`host` =", value, "host");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`host` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostNotEqualTo(String value) {
            addCriterion("`host` <>", value, "host");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`host` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostGreaterThan(String value) {
            addCriterion("`host` >", value, "host");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`host` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostGreaterThanOrEqualTo(String value) {
            addCriterion("`host` >=", value, "host");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`host` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostLessThan(String value) {
            addCriterion("`host` <", value, "host");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`host` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostLessThanOrEqualTo(String value) {
            addCriterion("`host` <=", value, "host");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`host` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostLike(String value) {
            addCriterion("`host` like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotLike(String value) {
            addCriterion("`host` not like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostIn(List<String> values) {
            addCriterion("`host` in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotIn(List<String> values) {
            addCriterion("`host` not in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostBetween(String value1, String value2) {
            addCriterion("`host` between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotBetween(String value1, String value2) {
            addCriterion("`host` not between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andHostNameIsNull() {
            addCriterion("host_name is null");
            return (Criteria) this;
        }

        public Criteria andHostNameIsNotNull() {
            addCriterion("host_name is not null");
            return (Criteria) this;
        }

        public Criteria andHostNameEqualTo(String value) {
            addCriterion("host_name =", value, "hostName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostNameEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("host_name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostNameNotEqualTo(String value) {
            addCriterion("host_name <>", value, "hostName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostNameNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("host_name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostNameGreaterThan(String value) {
            addCriterion("host_name >", value, "hostName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostNameGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("host_name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostNameGreaterThanOrEqualTo(String value) {
            addCriterion("host_name >=", value, "hostName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostNameGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("host_name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostNameLessThan(String value) {
            addCriterion("host_name <", value, "hostName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostNameLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("host_name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostNameLessThanOrEqualTo(String value) {
            addCriterion("host_name <=", value, "hostName");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andHostNameLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("host_name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostNameLike(String value) {
            addCriterion("host_name like", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotLike(String value) {
            addCriterion("host_name not like", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameIn(List<String> values) {
            addCriterion("host_name in", values, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotIn(List<String> values) {
            addCriterion("host_name not in", values, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameBetween(String value1, String value2) {
            addCriterion("host_name between", value1, value2, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotBetween(String value1, String value2) {
            addCriterion("host_name not between", value1, value2, "hostName");
            return (Criteria) this;
        }

        public Criteria andProxyPortIsNull() {
            addCriterion("proxy_port is null");
            return (Criteria) this;
        }

        public Criteria andProxyPortIsNotNull() {
            addCriterion("proxy_port is not null");
            return (Criteria) this;
        }

        public Criteria andProxyPortEqualTo(Integer value) {
            addCriterion("proxy_port =", value, "proxyPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andProxyPortEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("proxy_port = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProxyPortNotEqualTo(Integer value) {
            addCriterion("proxy_port <>", value, "proxyPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andProxyPortNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("proxy_port <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProxyPortGreaterThan(Integer value) {
            addCriterion("proxy_port >", value, "proxyPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andProxyPortGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("proxy_port > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProxyPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("proxy_port >=", value, "proxyPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andProxyPortGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("proxy_port >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProxyPortLessThan(Integer value) {
            addCriterion("proxy_port <", value, "proxyPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andProxyPortLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("proxy_port < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProxyPortLessThanOrEqualTo(Integer value) {
            addCriterion("proxy_port <=", value, "proxyPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andProxyPortLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("proxy_port <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProxyPortIn(List<Integer> values) {
            addCriterion("proxy_port in", values, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortNotIn(List<Integer> values) {
            addCriterion("proxy_port not in", values, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortBetween(Integer value1, Integer value2) {
            addCriterion("proxy_port between", value1, value2, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andProxyPortNotBetween(Integer value1, Integer value2) {
            addCriterion("proxy_port not between", value1, value2, "proxyPort");
            return (Criteria) this;
        }

        public Criteria andAdminPortIsNull() {
            addCriterion("admin_port is null");
            return (Criteria) this;
        }

        public Criteria andAdminPortIsNotNull() {
            addCriterion("admin_port is not null");
            return (Criteria) this;
        }

        public Criteria andAdminPortEqualTo(Integer value) {
            addCriterion("admin_port =", value, "adminPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andAdminPortEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("admin_port = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdminPortNotEqualTo(Integer value) {
            addCriterion("admin_port <>", value, "adminPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andAdminPortNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("admin_port <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdminPortGreaterThan(Integer value) {
            addCriterion("admin_port >", value, "adminPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andAdminPortGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("admin_port > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdminPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_port >=", value, "adminPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andAdminPortGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("admin_port >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdminPortLessThan(Integer value) {
            addCriterion("admin_port <", value, "adminPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andAdminPortLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("admin_port < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdminPortLessThanOrEqualTo(Integer value) {
            addCriterion("admin_port <=", value, "adminPort");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andAdminPortLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("admin_port <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAdminPortIn(List<Integer> values) {
            addCriterion("admin_port in", values, "adminPort");
            return (Criteria) this;
        }

        public Criteria andAdminPortNotIn(List<Integer> values) {
            addCriterion("admin_port not in", values, "adminPort");
            return (Criteria) this;
        }

        public Criteria andAdminPortBetween(Integer value1, Integer value2) {
            addCriterion("admin_port between", value1, value2, "adminPort");
            return (Criteria) this;
        }

        public Criteria andAdminPortNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_port not between", value1, value2, "adminPort");
            return (Criteria) this;
        }

        public Criteria andRoomIsNull() {
            addCriterion("room is null");
            return (Criteria) this;
        }

        public Criteria andRoomIsNotNull() {
            addCriterion("room is not null");
            return (Criteria) this;
        }

        public Criteria andRoomEqualTo(String value) {
            addCriterion("room =", value, "room");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRoomEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("room = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRoomNotEqualTo(String value) {
            addCriterion("room <>", value, "room");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRoomNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("room <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThan(String value) {
            addCriterion("room >", value, "room");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRoomGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("room > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRoomGreaterThanOrEqualTo(String value) {
            addCriterion("room >=", value, "room");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRoomGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("room >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRoomLessThan(String value) {
            addCriterion("room <", value, "room");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRoomLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("room < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRoomLessThanOrEqualTo(String value) {
            addCriterion("room <=", value, "room");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRoomLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("room <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRoomLike(String value) {
            addCriterion("room like", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotLike(String value) {
            addCriterion("room not like", value, "room");
            return (Criteria) this;
        }

        public Criteria andRoomIn(List<String> values) {
            addCriterion("room in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotIn(List<String> values) {
            addCriterion("room not in", values, "room");
            return (Criteria) this;
        }

        public Criteria andRoomBetween(String value1, String value2) {
            addCriterion("room between", value1, value2, "room");
            return (Criteria) this;
        }

        public Criteria andRoomNotBetween(String value1, String value2) {
            addCriterion("room not between", value1, value2, "room");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`status` = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`status` <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`status` > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`status` >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`status` < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStatusLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("`status` <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStartTimeEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("start_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStartTimeNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("start_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStartTimeGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("start_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStartTimeGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("start_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStartTimeLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("start_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andStartTimeLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("start_time <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andVersionEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("version = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andVersionNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("version <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andVersionGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("version > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andVersionGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("version >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andVersionLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("version < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andVersionLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("version <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andPropsIsNull() {
            addCriterion("props is null");
            return (Criteria) this;
        }

        public Criteria andPropsIsNotNull() {
            addCriterion("props is not null");
            return (Criteria) this;
        }

        public Criteria andPropsEqualTo(String value) {
            addCriterion("props =", value, "props");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPropsEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("props = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPropsNotEqualTo(String value) {
            addCriterion("props <>", value, "props");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPropsNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("props <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPropsGreaterThan(String value) {
            addCriterion("props >", value, "props");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPropsGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("props > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPropsGreaterThanOrEqualTo(String value) {
            addCriterion("props >=", value, "props");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPropsGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("props >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPropsLessThan(String value) {
            addCriterion("props <", value, "props");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPropsLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("props < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPropsLessThanOrEqualTo(String value) {
            addCriterion("props <=", value, "props");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andPropsLessThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("props <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPropsLike(String value) {
            addCriterion("props like", value, "props");
            return (Criteria) this;
        }

        public Criteria andPropsNotLike(String value) {
            addCriterion("props not like", value, "props");
            return (Criteria) this;
        }

        public Criteria andPropsIn(List<String> values) {
            addCriterion("props in", values, "props");
            return (Criteria) this;
        }

        public Criteria andPropsNotIn(List<String> values) {
            addCriterion("props not in", values, "props");
            return (Criteria) this;
        }

        public Criteria andPropsBetween(String value1, String value2) {
            addCriterion("props between", value1, value2, "props");
            return (Criteria) this;
        }

        public Criteria andPropsNotBetween(String value1, String value2) {
            addCriterion("props not between", value1, value2, "props");
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
        public Criteria andRemarkEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("remark = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("remark <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("remark > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("remark >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("remark < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andRemarkLessThanOrEqualToColumn(GatewayInstance.Column column) {
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
        public Criteria andInsertTimeEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("insert_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("insert_time <>", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("insert_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("insert_time >", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("insert_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_time >=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("insert_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("insert_time <", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("insert_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("insert_time <=", value, "insertTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andInsertTimeLessThanOrEqualToColumn(GatewayInstance.Column column) {
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
        public Criteria andUpdateTimeEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("update_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("update_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("update_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("update_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("update_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andUpdateTimeLessThanOrEqualToColumn(GatewayInstance.Column column) {
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
        public Criteria andIsDelEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("is_del = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelNotEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("is_del <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("is_del > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelGreaterThanOrEqualToColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("is_del >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanColumn(GatewayInstance.Column column) {
            addCriterion(new StringBuilder("is_del < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        /**
         */
        public Criteria andIsDelLessThanOrEqualToColumn(GatewayInstance.Column column) {
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

        public Criteria andInstanceIdLikeInsensitive(String value) {
            addCriterion("upper(instance_id) like", value.toUpperCase(), "instanceId");
            return (Criteria) this;
        }

        public Criteria andGroupLikeInsensitive(String value) {
            addCriterion("upper(`group`) like", value.toUpperCase(), "group");
            return (Criteria) this;
        }

        public Criteria andServerNameLikeInsensitive(String value) {
            addCriterion("upper(`server_name`) like", value.toUpperCase(), "serverName");
            return (Criteria) this;
        }

        public Criteria andHostLikeInsensitive(String value) {
            addCriterion("upper(`host`) like", value.toUpperCase(), "host");
            return (Criteria) this;
        }

        public Criteria andHostNameLikeInsensitive(String value) {
            addCriterion("upper(host_name) like", value.toUpperCase(), "hostName");
            return (Criteria) this;
        }

        public Criteria andRoomLikeInsensitive(String value) {
            addCriterion("upper(room) like", value.toUpperCase(), "room");
            return (Criteria) this;
        }

        public Criteria andVersionLikeInsensitive(String value) {
            addCriterion("upper(version) like", value.toUpperCase(), "version");
            return (Criteria) this;
        }

        public Criteria andPropsLikeInsensitive(String value) {
            addCriterion("upper(props) like", value.toUpperCase(), "props");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    /**
     * Corresponding to the database table p_gateway_instance
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * Corresponding to the database table p_gateway_instance
         */
        private GatewayInstanceCondition example;

        /**
         */
        protected Criteria(GatewayInstanceCondition example) {
            super();
            this.example = example;
        }

        /**
         */
        public GatewayInstanceCondition example() {
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
     * Corresponding to the database table p_gateway_instance
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