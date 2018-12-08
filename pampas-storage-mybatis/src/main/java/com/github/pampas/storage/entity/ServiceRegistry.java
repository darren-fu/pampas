package com.github.pampas.storage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 * 注册中心
 *
 * @author 
 */
public class ServiceRegistry implements Serializable {
    /**
     * ID
     *
     * Corresponding to the database column p_service_registry.id
     */
    private Integer id;

    /**
     * 注册中心名称
     *
     * Corresponding to the database column p_service_registry.name
     */
    private String name;

    /**
     * 注册中心类型 consul、zk、etcd等
     *
     * Corresponding to the database column p_service_registry.type
     */
    private String type;

    /**
     * 模式 spring cloud, dubbo, grpc等等
     *
     * Corresponding to the database column p_service_registry.pattern
     */
    private String pattern;

    /**
     * 注册中心地址
     *
     * Corresponding to the database column p_service_registry.address
     */
    private String address;

    /**
     * 备注
     *
     * Corresponding to the database column p_service_registry.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_service_registry.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_service_registry.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_service_registry.is_del
     */
    private Boolean isDel;

    /**
     * Corresponding to the database table p_service_registry
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_service_registry.id
     *
     * @return the value of p_service_registry.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public ServiceRegistry withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.id
     *
     * @param id the value for p_service_registry.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_service_registry.name
     *
     * @return the value of p_service_registry.name
     */
    public String getName() {
        return name;
    }

    /**
     */
    public ServiceRegistry withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.name
     *
     * @param name the value for p_service_registry.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the value of the database column p_service_registry.type
     *
     * @return the value of p_service_registry.type
     */
    public String getType() {
        return type;
    }

    /**
     */
    public ServiceRegistry withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.type
     *
     * @param type the value for p_service_registry.type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns the value of the database column p_service_registry.pattern
     *
     * @return the value of p_service_registry.pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     */
    public ServiceRegistry withPattern(String pattern) {
        this.setPattern(pattern);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.pattern
     *
     * @param pattern the value for p_service_registry.pattern
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * This method returns the value of the database column p_service_registry.address
     *
     * @return the value of p_service_registry.address
     */
    public String getAddress() {
        return address;
    }

    /**
     */
    public ServiceRegistry withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.address
     *
     * @param address the value for p_service_registry.address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method returns the value of the database column p_service_registry.remark
     *
     * @return the value of p_service_registry.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public ServiceRegistry withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.remark
     *
     * @param remark the value for p_service_registry.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_service_registry.insert_time
     *
     * @return the value of p_service_registry.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public ServiceRegistry withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.insert_time
     *
     * @param insertTime the value for p_service_registry.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_service_registry.update_time
     *
     * @return the value of p_service_registry.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public ServiceRegistry withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.update_time
     *
     * @param updateTime the value for p_service_registry.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_service_registry.is_del
     *
     * @return the value of p_service_registry.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public ServiceRegistry withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_service_registry.is_del
     *
     * @param isDel the value for p_service_registry.is_del
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", pattern=").append(pattern);
        sb.append(", address=").append(address);
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
        ServiceRegistry other = (ServiceRegistry) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPattern() == null ? other.getPattern() == null : this.getPattern().equals(other.getPattern()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPattern() == null) ? 0 : getPattern().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_service_registry
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        name("name", "name", "VARCHAR", true),
        type("type", "type", "VARCHAR", true),
        pattern("pattern", "pattern", "VARCHAR", false),
        address("address", "address", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false);

        /**
         * Corresponding to the database table p_service_registry
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_service_registry
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_service_registry
         */
        private final String column;

        /**
         * Corresponding to the database table p_service_registry
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_service_registry
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_service_registry
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