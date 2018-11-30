package com.github.pampas.storage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 * 路由规则定义表
 *
 * @author 
 */
public class GatewayRouteRuleRel implements Serializable {
    /**
     * 主键
     *
     * Corresponding to the database column p_gateway_route_rule_rel.id
     */
    private Integer id;

    /**
     * 服务名称
     *
     * Corresponding to the database column p_gateway_route_rule_rel.gateway_instance_id
     */
    private String gatewayInstanceId;

    /**
     *
     * Corresponding to the database column p_gateway_route_rule_rel.route_rule_id
     */
    private Integer routeRuleId;

    /**
     * 状态 1 正常 0 停用
     *
     * Corresponding to the database column p_gateway_route_rule_rel.status
     */
    private Boolean status;

    /**
     * 备注
     *
     * Corresponding to the database column p_gateway_route_rule_rel.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_gateway_route_rule_rel.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_gateway_route_rule_rel.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_gateway_route_rule_rel.is_del
     */
    private Boolean isDel;

    /**
     * Corresponding to the database table p_gateway_route_rule_rel
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.id
     *
     * @return the value of p_gateway_route_rule_rel.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public GatewayRouteRuleRel withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.id
     *
     * @param id the value for p_gateway_route_rule_rel.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.gateway_instance_id
     *
     * @return the value of p_gateway_route_rule_rel.gateway_instance_id
     */
    public String getGatewayInstanceId() {
        return gatewayInstanceId;
    }

    /**
     */
    public GatewayRouteRuleRel withGatewayInstanceId(String gatewayInstanceId) {
        this.setGatewayInstanceId(gatewayInstanceId);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.gateway_instance_id
     *
     * @param gatewayInstanceId the value for p_gateway_route_rule_rel.gateway_instance_id
     */
    public void setGatewayInstanceId(String gatewayInstanceId) {
        this.gatewayInstanceId = gatewayInstanceId;
    }

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.route_rule_id
     *
     * @return the value of p_gateway_route_rule_rel.route_rule_id
     */
    public Integer getRouteRuleId() {
        return routeRuleId;
    }

    /**
     */
    public GatewayRouteRuleRel withRouteRuleId(Integer routeRuleId) {
        this.setRouteRuleId(routeRuleId);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.route_rule_id
     *
     * @param routeRuleId the value for p_gateway_route_rule_rel.route_rule_id
     */
    public void setRouteRuleId(Integer routeRuleId) {
        this.routeRuleId = routeRuleId;
    }

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.status
     *
     * @return the value of p_gateway_route_rule_rel.status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     */
    public GatewayRouteRuleRel withStatus(Boolean status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.status
     *
     * @param status the value for p_gateway_route_rule_rel.status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.remark
     *
     * @return the value of p_gateway_route_rule_rel.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public GatewayRouteRuleRel withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.remark
     *
     * @param remark the value for p_gateway_route_rule_rel.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.insert_time
     *
     * @return the value of p_gateway_route_rule_rel.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public GatewayRouteRuleRel withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.insert_time
     *
     * @param insertTime the value for p_gateway_route_rule_rel.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.update_time
     *
     * @return the value of p_gateway_route_rule_rel.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public GatewayRouteRuleRel withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.update_time
     *
     * @param updateTime the value for p_gateway_route_rule_rel.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_gateway_route_rule_rel.is_del
     *
     * @return the value of p_gateway_route_rule_rel.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public GatewayRouteRuleRel withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_route_rule_rel.is_del
     *
     * @param isDel the value for p_gateway_route_rule_rel.is_del
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gatewayInstanceId=").append(gatewayInstanceId);
        sb.append(", routeRuleId=").append(routeRuleId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDel=").append(isDel);
        sb.append("]");
        return sb.toString();
    }

    /**
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GatewayRouteRuleRel other = (GatewayRouteRuleRel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGatewayInstanceId() == null ? other.getGatewayInstanceId() == null : this.getGatewayInstanceId().equals(other.getGatewayInstanceId()))
            && (this.getRouteRuleId() == null ? other.getRouteRuleId() == null : this.getRouteRuleId().equals(other.getRouteRuleId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGatewayInstanceId() == null) ? 0 : getGatewayInstanceId().hashCode());
        result = prime * result + ((getRouteRuleId() == null) ? 0 : getRouteRuleId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_gateway_route_rule_rel
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        gatewayInstanceId("gateway_instance_id", "gatewayInstanceId", "VARCHAR", false),
        routeRuleId("route_rule_id", "routeRuleId", "INTEGER", false),
        status("status", "status", "BIT", true),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false);

        /**
         * Corresponding to the database table p_gateway_route_rule_rel
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_route_rule_rel
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_route_rule_rel
         */
        private final String column;

        /**
         * Corresponding to the database table p_gateway_route_rule_rel
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_gateway_route_rule_rel
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_gateway_route_rule_rel
         */
        private final String jdbcType;

        /**
         */
        public String value() {
            return this.column;
        }

        /**
         */
        public String getValue() {
            return this.column;
        }

        /**
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }
    }
}