package com.github.pampas.storage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 * 服务定义表
 *
 * @author 
 */
public class Service implements Serializable {
    /**
     * 主键
     *
     * Corresponding to the database column p_service.id
     */
    private Integer id;

    /**
     * 服务名称
     *
     * Corresponding to the database column p_service.service_name
     */
    private String serviceName;

    /**
     * 服务类型 RESTful, dubbo, grpc
     *
     * Corresponding to the database column p_service.type
     */
    private String type;

    /**
     * 协议 http dubbo grpc
     *
     * Corresponding to the database column p_service.protocol
     */
    private String protocol;

    /**
     * 注册中心
     *
     * Corresponding to the database column p_service.registry_id
     */
    private Integer registryId;

    /**
     * 服务分组
     *
     * Corresponding to the database column p_service.group
     */
    private String group;

    /**
     * 负载均衡器-spi_name
     *
     * Corresponding to the database column p_service.loadbalancer
     */
    private String loadbalancer;

    /**
     * 状态1 启用 0 停用
     *
     * Corresponding to the database column p_service.status
     */
    private Boolean status;

    /**
     * 备注
     *
     * Corresponding to the database column p_service.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_service.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_service.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_service.is_del
     */
    private Boolean isDel;

    /**
     * Corresponding to the database table p_service
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_service.id
     *
     * @return the value of p_service.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public Service withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.id
     *
     * @param id the value for p_service.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_service.service_name
     *
     * @return the value of p_service.service_name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     */
    public Service withServiceName(String serviceName) {
        this.setServiceName(serviceName);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.service_name
     *
     * @param serviceName the value for p_service.service_name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * This method returns the value of the database column p_service.type
     *
     * @return the value of p_service.type
     */
    public String getType() {
        return type;
    }

    /**
     */
    public Service withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.type
     *
     * @param type the value for p_service.type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns the value of the database column p_service.protocol
     *
     * @return the value of p_service.protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     */
    public Service withProtocol(String protocol) {
        this.setProtocol(protocol);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.protocol
     *
     * @param protocol the value for p_service.protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * This method returns the value of the database column p_service.registry_id
     *
     * @return the value of p_service.registry_id
     */
    public Integer getRegistryId() {
        return registryId;
    }

    /**
     */
    public Service withRegistryId(Integer registryId) {
        this.setRegistryId(registryId);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.registry_id
     *
     * @param registryId the value for p_service.registry_id
     */
    public void setRegistryId(Integer registryId) {
        this.registryId = registryId;
    }

    /**
     * This method returns the value of the database column p_service.group
     *
     * @return the value of p_service.group
     */
    public String getGroup() {
        return group;
    }

    /**
     */
    public Service withGroup(String group) {
        this.setGroup(group);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.group
     *
     * @param group the value for p_service.group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * This method returns the value of the database column p_service.loadbalancer
     *
     * @return the value of p_service.loadbalancer
     */
    public String getLoadbalancer() {
        return loadbalancer;
    }

    /**
     */
    public Service withLoadbalancer(String loadbalancer) {
        this.setLoadbalancer(loadbalancer);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.loadbalancer
     *
     * @param loadbalancer the value for p_service.loadbalancer
     */
    public void setLoadbalancer(String loadbalancer) {
        this.loadbalancer = loadbalancer;
    }

    /**
     * This method returns the value of the database column p_service.status
     *
     * @return the value of p_service.status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     */
    public Service withStatus(Boolean status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.status
     *
     * @param status the value for p_service.status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method returns the value of the database column p_service.remark
     *
     * @return the value of p_service.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public Service withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.remark
     *
     * @param remark the value for p_service.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_service.insert_time
     *
     * @return the value of p_service.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public Service withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.insert_time
     *
     * @param insertTime the value for p_service.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_service.update_time
     *
     * @return the value of p_service.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public Service withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.update_time
     *
     * @param updateTime the value for p_service.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_service.is_del
     *
     * @return the value of p_service.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public Service withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_service.is_del
     *
     * @param isDel the value for p_service.is_del
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
        sb.append(", serviceName=").append(serviceName);
        sb.append(", type=").append(type);
        sb.append(", protocol=").append(protocol);
        sb.append(", registryId=").append(registryId);
        sb.append(", group=").append(group);
        sb.append(", loadbalancer=").append(loadbalancer);
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
        Service other = (Service) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getServiceName() == null ? other.getServiceName() == null : this.getServiceName().equals(other.getServiceName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getProtocol() == null ? other.getProtocol() == null : this.getProtocol().equals(other.getProtocol()))
            && (this.getRegistryId() == null ? other.getRegistryId() == null : this.getRegistryId().equals(other.getRegistryId()))
            && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
            && (this.getLoadbalancer() == null ? other.getLoadbalancer() == null : this.getLoadbalancer().equals(other.getLoadbalancer()))
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
        result = prime * result + ((getServiceName() == null) ? 0 : getServiceName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getProtocol() == null) ? 0 : getProtocol().hashCode());
        result = prime * result + ((getRegistryId() == null) ? 0 : getRegistryId().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getLoadbalancer() == null) ? 0 : getLoadbalancer().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_service
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        serviceName("service_name", "serviceName", "VARCHAR", false),
        type("type", "type", "VARCHAR", true),
        protocol("protocol", "protocol", "VARCHAR", false),
        registryId("registry_id", "registryId", "INTEGER", false),
        group("group", "group", "VARCHAR", true),
        loadbalancer("loadbalancer", "loadbalancer", "VARCHAR", false),
        status("status", "status", "BIT", true),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false);

        /**
         * Corresponding to the database table p_service
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_service
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_service
         */
        private final String column;

        /**
         * Corresponding to the database table p_service
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_service
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_service
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