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
public class GatewayInstance implements Serializable {
    /**
     * ID
     *
     * Corresponding to the database column p_gateway_instance.id
     */
    private Integer id;

    /**
     * 实例ID
     *
     * Corresponding to the database column p_gateway_instance.instance_id
     */
    private String instanceId;

    /**
     * 服务分组
     *
     * Corresponding to the database column p_gateway_instance.group
     */
    private String group;

    /**
     * 实例host IP
     *
     * Corresponding to the database column p_gateway_instance.host
     */
    private String host;

    /**
     * 实例主机名
     *
     * Corresponding to the database column p_gateway_instance.host_name
     */
    private String hostName;

    /**
     * 实例端口
     *
     * Corresponding to the database column p_gateway_instance.proxy_port
     */
    private Integer proxyPort;

    /**
     * 管理端口
     *
     * Corresponding to the database column p_gateway_instance.admin_port
     */
    private Integer adminPort;

    /**
     * 所属机房
     *
     * Corresponding to the database column p_gateway_instance.room
     */
    private String room;

    /**
     * 状态 1 正常
     *
     * Corresponding to the database column p_gateway_instance.status
     */
    private Byte status;

    /**
     * 启动时间
     *
     * Corresponding to the database column p_gateway_instance.start_time
     */
    private Date startTime;

    /**
     * 版本信息
     *
     * Corresponding to the database column p_gateway_instance.version
     */
    private String version;

    /**
     * 额外属性
     *
     * Corresponding to the database column p_gateway_instance.props
     */
    private String props;

    /**
     * 备注
     *
     * Corresponding to the database column p_gateway_instance.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_gateway_instance.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_gateway_instance.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_gateway_instance.is_del
     */
    private Boolean isDel;

    /**
     * Corresponding to the database table p_gateway_instance
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_gateway_instance.id
     *
     * @return the value of p_gateway_instance.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public GatewayInstance withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.id
     *
     * @param id the value for p_gateway_instance.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.instance_id
     *
     * @return the value of p_gateway_instance.instance_id
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     */
    public GatewayInstance withInstanceId(String instanceId) {
        this.setInstanceId(instanceId);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.instance_id
     *
     * @param instanceId the value for p_gateway_instance.instance_id
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.group
     *
     * @return the value of p_gateway_instance.group
     */
    public String getGroup() {
        return group;
    }

    /**
     */
    public GatewayInstance withGroup(String group) {
        this.setGroup(group);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.group
     *
     * @param group the value for p_gateway_instance.group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.host
     *
     * @return the value of p_gateway_instance.host
     */
    public String getHost() {
        return host;
    }

    /**
     */
    public GatewayInstance withHost(String host) {
        this.setHost(host);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.host
     *
     * @param host the value for p_gateway_instance.host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.host_name
     *
     * @return the value of p_gateway_instance.host_name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     */
    public GatewayInstance withHostName(String hostName) {
        this.setHostName(hostName);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.host_name
     *
     * @param hostName the value for p_gateway_instance.host_name
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.proxy_port
     *
     * @return the value of p_gateway_instance.proxy_port
     */
    public Integer getProxyPort() {
        return proxyPort;
    }

    /**
     */
    public GatewayInstance withProxyPort(Integer proxyPort) {
        this.setProxyPort(proxyPort);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.proxy_port
     *
     * @param proxyPort the value for p_gateway_instance.proxy_port
     */
    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.admin_port
     *
     * @return the value of p_gateway_instance.admin_port
     */
    public Integer getAdminPort() {
        return adminPort;
    }

    /**
     */
    public GatewayInstance withAdminPort(Integer adminPort) {
        this.setAdminPort(adminPort);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.admin_port
     *
     * @param adminPort the value for p_gateway_instance.admin_port
     */
    public void setAdminPort(Integer adminPort) {
        this.adminPort = adminPort;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.room
     *
     * @return the value of p_gateway_instance.room
     */
    public String getRoom() {
        return room;
    }

    /**
     */
    public GatewayInstance withRoom(String room) {
        this.setRoom(room);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.room
     *
     * @param room the value for p_gateway_instance.room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.status
     *
     * @return the value of p_gateway_instance.status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     */
    public GatewayInstance withStatus(Byte status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.status
     *
     * @param status the value for p_gateway_instance.status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.start_time
     *
     * @return the value of p_gateway_instance.start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     */
    public GatewayInstance withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.start_time
     *
     * @param startTime the value for p_gateway_instance.start_time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.version
     *
     * @return the value of p_gateway_instance.version
     */
    public String getVersion() {
        return version;
    }

    /**
     */
    public GatewayInstance withVersion(String version) {
        this.setVersion(version);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.version
     *
     * @param version the value for p_gateway_instance.version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.props
     *
     * @return the value of p_gateway_instance.props
     */
    public String getProps() {
        return props;
    }

    /**
     */
    public GatewayInstance withProps(String props) {
        this.setProps(props);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.props
     *
     * @param props the value for p_gateway_instance.props
     */
    public void setProps(String props) {
        this.props = props;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.remark
     *
     * @return the value of p_gateway_instance.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public GatewayInstance withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.remark
     *
     * @param remark the value for p_gateway_instance.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.insert_time
     *
     * @return the value of p_gateway_instance.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public GatewayInstance withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.insert_time
     *
     * @param insertTime the value for p_gateway_instance.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.update_time
     *
     * @return the value of p_gateway_instance.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public GatewayInstance withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.update_time
     *
     * @param updateTime the value for p_gateway_instance.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_gateway_instance.is_del
     *
     * @return the value of p_gateway_instance.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public GatewayInstance withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_instance.is_del
     *
     * @param isDel the value for p_gateway_instance.is_del
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
        sb.append(", group=").append(group);
        sb.append(", host=").append(host);
        sb.append(", hostName=").append(hostName);
        sb.append(", proxyPort=").append(proxyPort);
        sb.append(", adminPort=").append(adminPort);
        sb.append(", room=").append(room);
        sb.append(", status=").append(status);
        sb.append(", startTime=").append(startTime);
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
        GatewayInstance other = (GatewayInstance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
            && (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
            && (this.getHostName() == null ? other.getHostName() == null : this.getHostName().equals(other.getHostName()))
            && (this.getProxyPort() == null ? other.getProxyPort() == null : this.getProxyPort().equals(other.getProxyPort()))
            && (this.getAdminPort() == null ? other.getAdminPort() == null : this.getAdminPort().equals(other.getAdminPort()))
            && (this.getRoom() == null ? other.getRoom() == null : this.getRoom().equals(other.getRoom()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
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
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
        result = prime * result + ((getHostName() == null) ? 0 : getHostName().hashCode());
        result = prime * result + ((getProxyPort() == null) ? 0 : getProxyPort().hashCode());
        result = prime * result + ((getAdminPort() == null) ? 0 : getAdminPort().hashCode());
        result = prime * result + ((getRoom() == null) ? 0 : getRoom().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getProps() == null) ? 0 : getProps().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_gateway_instance
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        instanceId("instance_id", "instanceId", "VARCHAR", false),
        group("group", "group", "VARCHAR", true),
        host("host", "host", "VARCHAR", true),
        hostName("host_name", "hostName", "VARCHAR", false),
        proxyPort("proxy_port", "proxyPort", "INTEGER", false),
        adminPort("admin_port", "adminPort", "INTEGER", false),
        room("room", "room", "VARCHAR", false),
        status("status", "status", "TINYINT", true),
        startTime("start_time", "startTime", "TIMESTAMP", false),
        version("version", "version", "VARCHAR", false),
        props("props", "props", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false);

        /**
         * Corresponding to the database table p_gateway_instance
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_instance
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_instance
         */
        private final String column;

        /**
         * Corresponding to the database table p_gateway_instance
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_gateway_instance
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_gateway_instance
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