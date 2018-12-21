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
public class GatewaySpi implements Serializable {
    /**
     * 主键
     *
     * Corresponding to the database column p_gateway_spi.id
     */
    private Integer id;

    /**
     * 服务名称
     *
     * Corresponding to the database column p_gateway_spi.gateway_group
     */
    private String gatewayGroup;

    /**
     *
     * Corresponding to the database column p_gateway_spi.gateway_instance_id
     */
    private String gatewayInstanceId;

    /**
     *
     * Corresponding to the database column p_gateway_spi.spi_interface
     */
    private String spiInterface;

    /**
     *
     * Corresponding to the database column p_gateway_spi.spi_interface_desc
     */
    private String spiInterfaceDesc;

    /**
     *
     * Corresponding to the database column p_gateway_spi.spi_class
     */
    private String spiClass;

    /**
     *
     * Corresponding to the database column p_gateway_spi.spi_name
     */
    private String spiName;

    /**
     *
     * Corresponding to the database column p_gateway_spi.spi_desc
     */
    private String spiDesc;

    /**
     * 优先级越小越高
     *
     * Corresponding to the database column p_gateway_spi.order
     */
    private Integer order;

    /**
     * 状态 1 正常 0 停用
     *
     * Corresponding to the database column p_gateway_spi.status
     */
    private Boolean status;

    /**
     *
     * Corresponding to the database column p_gateway_spi.push
     */
    private String push;

    /**
     * 备注
     *
     * Corresponding to the database column p_gateway_spi.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_gateway_spi.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_gateway_spi.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_gateway_spi.is_del
     */
    private Boolean isDel;

    /**
     * Corresponding to the database table p_gateway_spi
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_gateway_spi.id
     *
     * @return the value of p_gateway_spi.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public GatewaySpi withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.id
     *
     * @param id the value for p_gateway_spi.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.gateway_group
     *
     * @return the value of p_gateway_spi.gateway_group
     */
    public String getGatewayGroup() {
        return gatewayGroup;
    }

    /**
     */
    public GatewaySpi withGatewayGroup(String gatewayGroup) {
        this.setGatewayGroup(gatewayGroup);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.gateway_group
     *
     * @param gatewayGroup the value for p_gateway_spi.gateway_group
     */
    public void setGatewayGroup(String gatewayGroup) {
        this.gatewayGroup = gatewayGroup;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.gateway_instance_id
     *
     * @return the value of p_gateway_spi.gateway_instance_id
     */
    public String getGatewayInstanceId() {
        return gatewayInstanceId;
    }

    /**
     */
    public GatewaySpi withGatewayInstanceId(String gatewayInstanceId) {
        this.setGatewayInstanceId(gatewayInstanceId);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.gateway_instance_id
     *
     * @param gatewayInstanceId the value for p_gateway_spi.gateway_instance_id
     */
    public void setGatewayInstanceId(String gatewayInstanceId) {
        this.gatewayInstanceId = gatewayInstanceId;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.spi_interface
     *
     * @return the value of p_gateway_spi.spi_interface
     */
    public String getSpiInterface() {
        return spiInterface;
    }

    /**
     */
    public GatewaySpi withSpiInterface(String spiInterface) {
        this.setSpiInterface(spiInterface);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.spi_interface
     *
     * @param spiInterface the value for p_gateway_spi.spi_interface
     */
    public void setSpiInterface(String spiInterface) {
        this.spiInterface = spiInterface;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.spi_interface_desc
     *
     * @return the value of p_gateway_spi.spi_interface_desc
     */
    public String getSpiInterfaceDesc() {
        return spiInterfaceDesc;
    }

    /**
     */
    public GatewaySpi withSpiInterfaceDesc(String spiInterfaceDesc) {
        this.setSpiInterfaceDesc(spiInterfaceDesc);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.spi_interface_desc
     *
     * @param spiInterfaceDesc the value for p_gateway_spi.spi_interface_desc
     */
    public void setSpiInterfaceDesc(String spiInterfaceDesc) {
        this.spiInterfaceDesc = spiInterfaceDesc;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.spi_class
     *
     * @return the value of p_gateway_spi.spi_class
     */
    public String getSpiClass() {
        return spiClass;
    }

    /**
     */
    public GatewaySpi withSpiClass(String spiClass) {
        this.setSpiClass(spiClass);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.spi_class
     *
     * @param spiClass the value for p_gateway_spi.spi_class
     */
    public void setSpiClass(String spiClass) {
        this.spiClass = spiClass;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.spi_name
     *
     * @return the value of p_gateway_spi.spi_name
     */
    public String getSpiName() {
        return spiName;
    }

    /**
     */
    public GatewaySpi withSpiName(String spiName) {
        this.setSpiName(spiName);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.spi_name
     *
     * @param spiName the value for p_gateway_spi.spi_name
     */
    public void setSpiName(String spiName) {
        this.spiName = spiName;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.spi_desc
     *
     * @return the value of p_gateway_spi.spi_desc
     */
    public String getSpiDesc() {
        return spiDesc;
    }

    /**
     */
    public GatewaySpi withSpiDesc(String spiDesc) {
        this.setSpiDesc(spiDesc);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.spi_desc
     *
     * @param spiDesc the value for p_gateway_spi.spi_desc
     */
    public void setSpiDesc(String spiDesc) {
        this.spiDesc = spiDesc;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.order
     *
     * @return the value of p_gateway_spi.order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     */
    public GatewaySpi withOrder(Integer order) {
        this.setOrder(order);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.order
     *
     * @param order the value for p_gateway_spi.order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.status
     *
     * @return the value of p_gateway_spi.status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     */
    public GatewaySpi withStatus(Boolean status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.status
     *
     * @param status the value for p_gateway_spi.status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.push
     *
     * @return the value of p_gateway_spi.push
     */
    public String getPush() {
        return push;
    }

    /**
     */
    public GatewaySpi withPush(String push) {
        this.setPush(push);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.push
     *
     * @param push the value for p_gateway_spi.push
     */
    public void setPush(String push) {
        this.push = push;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.remark
     *
     * @return the value of p_gateway_spi.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public GatewaySpi withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.remark
     *
     * @param remark the value for p_gateway_spi.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.insert_time
     *
     * @return the value of p_gateway_spi.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public GatewaySpi withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.insert_time
     *
     * @param insertTime the value for p_gateway_spi.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.update_time
     *
     * @return the value of p_gateway_spi.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public GatewaySpi withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.update_time
     *
     * @param updateTime the value for p_gateway_spi.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_gateway_spi.is_del
     *
     * @return the value of p_gateway_spi.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public GatewaySpi withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_spi.is_del
     *
     * @param isDel the value for p_gateway_spi.is_del
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
        sb.append(", gatewayGroup=").append(gatewayGroup);
        sb.append(", gatewayInstanceId=").append(gatewayInstanceId);
        sb.append(", spiInterface=").append(spiInterface);
        sb.append(", spiInterfaceDesc=").append(spiInterfaceDesc);
        sb.append(", spiClass=").append(spiClass);
        sb.append(", spiName=").append(spiName);
        sb.append(", spiDesc=").append(spiDesc);
        sb.append(", order=").append(order);
        sb.append(", status=").append(status);
        sb.append(", push=").append(push);
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
        GatewaySpi other = (GatewaySpi) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGatewayGroup() == null ? other.getGatewayGroup() == null : this.getGatewayGroup().equals(other.getGatewayGroup()))
            && (this.getGatewayInstanceId() == null ? other.getGatewayInstanceId() == null : this.getGatewayInstanceId().equals(other.getGatewayInstanceId()))
            && (this.getSpiInterface() == null ? other.getSpiInterface() == null : this.getSpiInterface().equals(other.getSpiInterface()))
            && (this.getSpiInterfaceDesc() == null ? other.getSpiInterfaceDesc() == null : this.getSpiInterfaceDesc().equals(other.getSpiInterfaceDesc()))
            && (this.getSpiClass() == null ? other.getSpiClass() == null : this.getSpiClass().equals(other.getSpiClass()))
            && (this.getSpiName() == null ? other.getSpiName() == null : this.getSpiName().equals(other.getSpiName()))
            && (this.getSpiDesc() == null ? other.getSpiDesc() == null : this.getSpiDesc().equals(other.getSpiDesc()))
            && (this.getOrder() == null ? other.getOrder() == null : this.getOrder().equals(other.getOrder()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPush() == null ? other.getPush() == null : this.getPush().equals(other.getPush()))
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
        result = prime * result + ((getGatewayGroup() == null) ? 0 : getGatewayGroup().hashCode());
        result = prime * result + ((getGatewayInstanceId() == null) ? 0 : getGatewayInstanceId().hashCode());
        result = prime * result + ((getSpiInterface() == null) ? 0 : getSpiInterface().hashCode());
        result = prime * result + ((getSpiInterfaceDesc() == null) ? 0 : getSpiInterfaceDesc().hashCode());
        result = prime * result + ((getSpiClass() == null) ? 0 : getSpiClass().hashCode());
        result = prime * result + ((getSpiName() == null) ? 0 : getSpiName().hashCode());
        result = prime * result + ((getSpiDesc() == null) ? 0 : getSpiDesc().hashCode());
        result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPush() == null) ? 0 : getPush().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_gateway_spi
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        gatewayGroup("gateway_group", "gatewayGroup", "VARCHAR", false),
        gatewayInstanceId("gateway_instance_id", "gatewayInstanceId", "VARCHAR", false),
        spiInterface("spi_interface", "spiInterface", "VARCHAR", false),
        spiInterfaceDesc("spi_interface_desc", "spiInterfaceDesc", "VARCHAR", false),
        spiClass("spi_class", "spiClass", "VARCHAR", false),
        spiName("spi_name", "spiName", "VARCHAR", false),
        spiDesc("spi_desc", "spiDesc", "VARCHAR", false),
        order("order", "order", "INTEGER", true),
        status("status", "status", "BIT", true),
        push("push", "push", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false);

        /**
         * Corresponding to the database table p_gateway_spi
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_spi
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_spi
         */
        private final String column;

        /**
         * Corresponding to the database table p_gateway_spi
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_gateway_spi
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_gateway_spi
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