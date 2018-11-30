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
public class RouteRule implements Serializable {
    /**
     * 主键
     *
     * Corresponding to the database column p_route_rule.id
     */
    private Integer id;

    /**
     * 规则命名
     *
     * Corresponding to the database column p_route_rule.name
     */
    private String name;

    /**
     * 路由分组
     *
     * Corresponding to the database column p_route_rule.group
     */
    private String group;

    /**
     * 匹配的host头
     *
     * Corresponding to the database column p_route_rule.mapping_host
     */
    private String mappingHost;

    /**
     * 状态 1 正常 0 停用
     *
     * Corresponding to the database column p_route_rule.status
     */
    private Boolean status;

    /**
     * 备注
     *
     * Corresponding to the database column p_route_rule.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_route_rule.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_route_rule.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_route_rule.is_del
     */
    private Boolean isDel;

    /**
     * 路由规则详情
     *
     * Corresponding to the database column p_route_rule.content
     */
    private String content;

    /**
     * Corresponding to the database table p_route_rule
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_route_rule.id
     *
     * @return the value of p_route_rule.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public RouteRule withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.id
     *
     * @param id the value for p_route_rule.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_route_rule.name
     *
     * @return the value of p_route_rule.name
     */
    public String getName() {
        return name;
    }

    /**
     */
    public RouteRule withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.name
     *
     * @param name the value for p_route_rule.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the value of the database column p_route_rule.group
     *
     * @return the value of p_route_rule.group
     */
    public String getGroup() {
        return group;
    }

    /**
     */
    public RouteRule withGroup(String group) {
        this.setGroup(group);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.group
     *
     * @param group the value for p_route_rule.group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * This method returns the value of the database column p_route_rule.mapping_host
     *
     * @return the value of p_route_rule.mapping_host
     */
    public String getMappingHost() {
        return mappingHost;
    }

    /**
     */
    public RouteRule withMappingHost(String mappingHost) {
        this.setMappingHost(mappingHost);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.mapping_host
     *
     * @param mappingHost the value for p_route_rule.mapping_host
     */
    public void setMappingHost(String mappingHost) {
        this.mappingHost = mappingHost;
    }

    /**
     * This method returns the value of the database column p_route_rule.status
     *
     * @return the value of p_route_rule.status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     */
    public RouteRule withStatus(Boolean status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.status
     *
     * @param status the value for p_route_rule.status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method returns the value of the database column p_route_rule.remark
     *
     * @return the value of p_route_rule.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public RouteRule withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.remark
     *
     * @param remark the value for p_route_rule.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_route_rule.insert_time
     *
     * @return the value of p_route_rule.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public RouteRule withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.insert_time
     *
     * @param insertTime the value for p_route_rule.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_route_rule.update_time
     *
     * @return the value of p_route_rule.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public RouteRule withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.update_time
     *
     * @param updateTime the value for p_route_rule.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_route_rule.is_del
     *
     * @return the value of p_route_rule.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public RouteRule withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.is_del
     *
     * @param isDel the value for p_route_rule.is_del
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * This method returns the value of the database column p_route_rule.content
     *
     * @return the value of p_route_rule.content
     */
    public String getContent() {
        return content;
    }

    /**
     */
    public RouteRule withContent(String content) {
        this.setContent(content);
        return this;
    }

    /**
     * This method sets the value of the database column p_route_rule.content
     *
     * @param content the value for p_route_rule.content
     */
    public void setContent(String content) {
        this.content = content;
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
        sb.append(", group=").append(group);
        sb.append(", mappingHost=").append(mappingHost);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDel=").append(isDel);
        sb.append(", content=").append(content);
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
        RouteRule other = (RouteRule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGroup() == null ? other.getGroup() == null : this.getGroup().equals(other.getGroup()))
            && (this.getMappingHost() == null ? other.getMappingHost() == null : this.getMappingHost().equals(other.getMappingHost()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGroup() == null) ? 0 : getGroup().hashCode());
        result = prime * result + ((getMappingHost() == null) ? 0 : getMappingHost().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_route_rule
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        name("name", "name", "VARCHAR", true),
        group("group", "group", "VARCHAR", true),
        mappingHost("mapping_host", "mappingHost", "VARCHAR", false),
        status("status", "status", "BIT", true),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false),
        content("content", "content", "LONGVARCHAR", false);

        /**
         * Corresponding to the database table p_route_rule
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_route_rule
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_route_rule
         */
        private final String column;

        /**
         * Corresponding to the database table p_route_rule
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_route_rule
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_route_rule
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