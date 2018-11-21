package com.github.pampas.storage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 * 服务实例
 *
 * @author 
 */
public class ServiceInstance implements Serializable {
    /**
     * ID
     *
     * Corresponding to the database column p_service_instance.id
     */
    private Integer id;

    /**
     * 实例ID
     *
     * Corresponding to the database column p_service_instance.instance_id
     */
    private String instanceId;

    /**
     * 服务ID
     *
     * Corresponding to the database column p_service_instance.service_id
     */
    private Integer serviceId;

    /**
     * 服务名称
     *
     * Corresponding to the database column p_service_instance.service_name
     */
    private String serviceName;

    /**
     * 实例分组
     *
     * Corresponding to the database column p_service_instance.group
     */
    private String group;

    /**
     * 协议
     *
     * Corresponding to the database column p_service_instance.protocol
     */
    private String protocol;

    /**
     * 实例host
     *
     * Corresponding to the database column p_service_instance.host
     */
    private String host;

    /**
     * 主机名
     *
     * Corresponding to the database column p_service_instance.host_name
     */
    private String hostName;

    /**
     * 实例端口
     *
     * Corresponding to the database column p_service_instance.port
     */
    private Integer port;

    /**
     * 所属机房
     *
     * Corresponding to the database column p_service_instance.room
     */
    private String room;

    /**
     * 状态 1 正常 0 失效
     *
     * Corresponding to the database column p_service_instance.status
     */
    private Byte status;

    /**
     * 启动时间
     *
     * Corresponding to the database column p_service_instance.start_time
     */
    private Date startTime;

    /**
     * 热机时间
     *
     * Corresponding to the database column p_service_instance.warmup_seconds
     */
    private Integer warmupSeconds;

    /**
     * 权重
     *
     * Corresponding to the database column p_service_instance.weight
     */
    private Integer weight;

    /**
     * 版本信息
     *
     * Corresponding to the database column p_service_instance.version
     */
    private String version;

    /**
     * 额外属性
     *
     * Corresponding to the database column p_service_instance.props
     */
    private String props;

    /**
     * 备注
     *
     * Corresponding to the database column p_service_instance.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_service_instance.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_service_instance.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_service_instance.is_del
     */
    private Boolean isDel;

    /**
     * Corresponding to the database table p_service_instance
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_service_instance.id
     *
     * @return the value of p_service_instance.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public ServiceInstance withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.id
     *
     * @param id the value for p_service_instance.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_service_instance.instance_id
     *
     * @return the value of p_service_instance.instance_id
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     */
    public ServiceInstance withInstanceId(String instanceId) {
        this.setInstanceId(instanceId);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.instance_id
     *
     * @param instanceId the value for p_service_instance.instance_id
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * This method returns the value of the database column p_service_instance.service_id
     *
     * @return the value of p_service_instance.service_id
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     */
    public ServiceInstance withServiceId(Integer serviceId) {
        this.setServiceId(serviceId);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.service_id
     *
     * @param serviceId the value for p_service_instance.service_id
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * This method returns the value of the database column p_service_instance.service_name
     *
     * @return the value of p_service_instance.service_name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     */
    public ServiceInstance withServiceName(String serviceName) {
        this.setServiceName(serviceName);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.service_name
     *
     * @param serviceName the value for p_service_instance.service_name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * This method returns the value of the database column p_service_instance.group
     *
     * @return the value of p_service_instance.group
     */
    public String getGroup() {
        return group;
    }

    /**
     */
    public ServiceInstance withGroup(String group) {
        this.setGroup(group);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.group
     *
     * @param group the value for p_service_instance.group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * This method returns the value of the database column p_service_instance.protocol
     *
     * @return the value of p_service_instance.protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     */
    public ServiceInstance withProtocol(String protocol) {
        this.setProtocol(protocol);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.protocol
     *
     * @param protocol the value for p_service_instance.protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * This method returns the value of the database column p_service_instance.host
     *
     * @return the value of p_service_instance.host
     */
    public String getHost() {
        return host;
    }

    /**
     */
    public ServiceInstance withHost(String host) {
        this.setHost(host);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.host
     *
     * @param host the value for p_service_instance.host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * This method returns the value of the database column p_service_instance.host_name
     *
     * @return the value of p_service_instance.host_name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     */
    public ServiceInstance withHostName(String hostName) {
        this.setHostName(hostName);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.host_name
     *
     * @param hostName the value for p_service_instance.host_name
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * This method returns the value of the database column p_service_instance.port
     *
     * @return the value of p_service_instance.port
     */
    public Integer getPort() {
        return port;
    }

    /**
     */
    public ServiceInstance withPort(Integer port) {
        this.setPort(port);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.port
     *
     * @param port the value for p_service_instance.port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * This method returns the value of the database column p_service_instance.room
     *
     * @return the value of p_service_instance.room
     */
    public String getRoom() {
        return room;
    }

    /**
     */
    public ServiceInstance withRoom(String room) {
        this.setRoom(room);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.room
     *
     * @param room the value for p_service_instance.room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * This method returns the value of the database column p_service_instance.status
     *
     * @return the value of p_service_instance.status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     */
    public ServiceInstance withStatus(Byte status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.status
     *
     * @param status the value for p_service_instance.status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method returns the value of the database column p_service_instance.start_time
     *
     * @return the value of p_service_instance.start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     */
    public ServiceInstance withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.start_time
     *
     * @param startTime the value for p_service_instance.start_time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method returns the value of the database column p_service_instance.warmup_seconds
     *
     * @return the value of p_service_instance.warmup_seconds
     */
    public Integer getWarmupSeconds() {
        return warmupSeconds;
    }

    /**
     */
    public ServiceInstance withWarmupSeconds(Integer warmupSeconds) {
        this.setWarmupSeconds(warmupSeconds);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.warmup_seconds
     *
     * @param warmupSeconds the value for p_service_instance.warmup_seconds
     */
    public void setWarmupSeconds(Integer warmupSeconds) {
        this.warmupSeconds = warmupSeconds;
    }

    /**
     * This method returns the value of the database column p_service_instance.weight
     *
     * @return the value of p_service_instance.weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     */
    public ServiceInstance withWeight(Integer weight) {
        this.setWeight(weight);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.weight
     *
     * @param weight the value for p_service_instance.weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * This method returns the value of the database column p_service_instance.version
     *
     * @return the value of p_service_instance.version
     */
    public String getVersion() {
        return version;
    }

    /**
     */
    public ServiceInstance withVersion(String version) {
        this.setVersion(version);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.version
     *
     * @param version the value for p_service_instance.version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * This method returns the value of the database column p_service_instance.props
     *
     * @return the value of p_service_instance.props
     */
    public String getProps() {
        return props;
    }

    /**
     */
    public ServiceInstance withProps(String props) {
        this.setProps(props);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.props
     *
     * @param props the value for p_service_instance.props
     */
    public void setProps(String props) {
        this.props = props;
    }

    /**
     * This method returns the value of the database column p_service_instance.remark
     *
     * @return the value of p_service_instance.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public ServiceInstance withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.remark
     *
     * @param remark the value for p_service_instance.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_service_instance.insert_time
     *
     * @return the value of p_service_instance.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public ServiceInstance withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.insert_time
     *
     * @param insertTime the value for p_service_instance.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_service_instance.update_time
     *
     * @return the value of p_service_instance.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public ServiceInstance withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.update_time
     *
     * @param updateTime the value for p_service_instance.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_service_instance.is_del
     *
     * @return the value of p_service_instance.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public ServiceInstance withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_instance.is_del
     *
     * @param isDel the value for p_service_instance.is_del
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
        sb.append(", instanceId=").append(instanceId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", serviceName=").append(serviceName);
        sb.append(", group=").append(group);
        sb.append(", protocol=").append(protocol);
        sb.append(", host=").append(host);
        sb.append(", hostName=").append(hostName);
        sb.append(", port=").append(port);
        sb.append(", room=").append(room);
        sb.append(", status=").append(status);
        sb.append(", startTime=").append(startTime);
        sb.append(", warmupSeconds=").append(warmupSeconds);
        sb.append(", weight=").append(weight);
        sb.append(", version=").append(version);
        sb.append(", props=").append(props);
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
        ServiceInstance other = (ServiceInstance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getServiceId() == null ? other.getServiceId() == null : this.getServiceId().equals(other.getServiceId()))
            && (this.getServiceName() == null ? other.getServiceName() == null : this.getServiceName().equals(other.getServiceName()))
            && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
            && (this.getProtocol() == null ? other.getProtocol() == null : this.getProtocol().equals(other.getProtocol()))
            && (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
            && (this.getHostName() == null ? other.getHostName() == null : this.getHostName().equals(other.getHostName()))
            && (this.getPort() == null ? other.getPort() == null : this.getPort().equals(other.getPort()))
            && (this.getRoom() == null ? other.getRoom() == null : this.getRoom().equals(other.getRoom()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getWarmupSeconds() == null ? other.getWarmupSeconds() == null : this.getWarmupSeconds().equals(other.getWarmupSeconds()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getProps() == null ? other.getProps() == null : this.getProps().equals(other.getProps()))
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
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        result = prime * result + ((getServiceId() == null) ? 0 : getServiceId().hashCode());
        result = prime * result + ((getServiceName() == null) ? 0 : getServiceName().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getProtocol() == null) ? 0 : getProtocol().hashCode());
        result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
        result = prime * result + ((getHostName() == null) ? 0 : getHostName().hashCode());
        result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
        result = prime * result + ((getRoom() == null) ? 0 : getRoom().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getWarmupSeconds() == null) ? 0 : getWarmupSeconds().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getProps() == null) ? 0 : getProps().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_service_instance
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        instanceId("instance_id", "instanceId", "VARCHAR", false),
        serviceId("service_id", "serviceId", "INTEGER", false),
        serviceName("service_name", "serviceName", "VARCHAR", false),
        group("group", "group", "VARCHAR", true),
        protocol("protocol", "protocol", "VARCHAR", false),
        host("host", "host", "VARCHAR", true),
        hostName("host_name", "hostName", "VARCHAR", false),
        port("port", "port", "INTEGER", false),
        room("room", "room", "VARCHAR", false),
        status("status", "status", "TINYINT", true),
        startTime("start_time", "startTime", "TIMESTAMP", false),
        warmupSeconds("warmup_seconds", "warmupSeconds", "INTEGER", false),
        weight("weight", "weight", "INTEGER", false),
        version("version", "version", "VARCHAR", false),
        props("props", "props", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false);

        /**
         * Corresponding to the database table p_service_instance
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_service_instance
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_service_instance
         */
        private final String column;

        /**
         * Corresponding to the database table p_service_instance
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_service_instance
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_service_instance
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