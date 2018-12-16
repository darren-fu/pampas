package com.github.pampas.storage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created By MBG-GUI-EXTENSION https://github.com/spawpaw/mybatis-generator-gui-extension
 * Description:
 * 网关配置项
 *
 * @author 
 */
public class GatewayConfig implements Serializable {
    /**
     * ID
     *
     * Corresponding to the database column p_gateway_config.id
     */
    private Integer id;

    /**
     * 网关分组
     *
     * Corresponding to the database column p_gateway_config.gateway_group
     */
    private String gatewayGroup;

    /**
     * 网关ID
     *
     * Corresponding to the database column p_gateway_config.gateway_instance_id
     */
    private String gatewayInstanceId;

    /**
     *
     * Corresponding to the database column p_gateway_config.config_spi_interface
     */
    private String configSpiInterface;

    /**
     *
     * Corresponding to the database column p_gateway_config.config_spi_interface_desc
     */
    private String configSpiInterfaceDesc;

    /**
     *
     * Corresponding to the database column p_gateway_config.config_spi_class
     */
    private String configSpiClass;

    /**
     *
     * Corresponding to the database column p_gateway_config.config_spi_name
     */
    private String configSpiName;

    /**
     *
     * Corresponding to the database column p_gateway_config.config_spi_desc
     */
    private String configSpiDesc;

    /**
     * KEY
     *
     * Corresponding to the database column p_gateway_config.key
     */
    private String key;

    /**
     * LABEL
     *
     * Corresponding to the database column p_gateway_config.label
     */
    private String label;

    /**
     *
     * Corresponding to the database column p_gateway_config.default_value
     */
    private String defaultValue;

    /**
     *
     * Corresponding to the database column p_gateway_config.placeholder
     */
    private String placeholder;

    /**
     *
     * Corresponding to the database column p_gateway_config.value
     */
    private String value;

    /**
     * 是否必须
     *
     * Corresponding to the database column p_gateway_config.required
     */
    private Boolean required;

    /**
     * 多行
     *
     * Corresponding to the database column p_gateway_config.multi
     */
    private Boolean multi;

    /**
     *
     * Corresponding to the database column p_gateway_config.status
     */
    private Boolean status;

    /**
     * 推送
     *
     * Corresponding to the database column p_gateway_config.push
     */
    private String push;

    /**
     * 备注
     *
     * Corresponding to the database column p_gateway_config.remark
     */
    private String remark;

    /**
     * 创建时间
     *
     * Corresponding to the database column p_gateway_config.insert_time
     */
    private Date insertTime;

    /**
     * 更新时间
     *
     * Corresponding to the database column p_gateway_config.update_time
     */
    private Date updateTime;

    /**
     * 是否删除 0 否 1 是
     *
     * Corresponding to the database column p_gateway_config.is_del
     */
    private Boolean isDel;

    /**
     * Corresponding to the database table p_gateway_config
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column p_gateway_config.id
     *
     * @return the value of p_gateway_config.id
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public GatewayConfig withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.id
     *
     * @param id the value for p_gateway_config.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column p_gateway_config.gateway_group
     *
     * @return the value of p_gateway_config.gateway_group
     */
    public String getGatewayGroup() {
        return gatewayGroup;
    }

    /**
     */
    public GatewayConfig withGatewayGroup(String gatewayGroup) {
        this.setGatewayGroup(gatewayGroup);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.gateway_group
     *
     * @param gatewayGroup the value for p_gateway_config.gateway_group
     */
    public void setGatewayGroup(String gatewayGroup) {
        this.gatewayGroup = gatewayGroup;
    }

    /**
     * This method returns the value of the database column p_gateway_config.gateway_instance_id
     *
     * @return the value of p_gateway_config.gateway_instance_id
     */
    public String getGatewayInstanceId() {
        return gatewayInstanceId;
    }

    /**
     */
    public GatewayConfig withGatewayInstanceId(String gatewayInstanceId) {
        this.setGatewayInstanceId(gatewayInstanceId);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.gateway_instance_id
     *
     * @param gatewayInstanceId the value for p_gateway_config.gateway_instance_id
     */
    public void setGatewayInstanceId(String gatewayInstanceId) {
        this.gatewayInstanceId = gatewayInstanceId;
    }

    /**
     * This method returns the value of the database column p_gateway_config.config_spi_interface
     *
     * @return the value of p_gateway_config.config_spi_interface
     */
    public String getConfigSpiInterface() {
        return configSpiInterface;
    }

    /**
     */
    public GatewayConfig withConfigSpiInterface(String configSpiInterface) {
        this.setConfigSpiInterface(configSpiInterface);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.config_spi_interface
     *
     * @param configSpiInterface the value for p_gateway_config.config_spi_interface
     */
    public void setConfigSpiInterface(String configSpiInterface) {
        this.configSpiInterface = configSpiInterface;
    }

    /**
     * This method returns the value of the database column p_gateway_config.config_spi_interface_desc
     *
     * @return the value of p_gateway_config.config_spi_interface_desc
     */
    public String getConfigSpiInterfaceDesc() {
        return configSpiInterfaceDesc;
    }

    /**
     */
    public GatewayConfig withConfigSpiInterfaceDesc(String configSpiInterfaceDesc) {
        this.setConfigSpiInterfaceDesc(configSpiInterfaceDesc);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.config_spi_interface_desc
     *
     * @param configSpiInterfaceDesc the value for p_gateway_config.config_spi_interface_desc
     */
    public void setConfigSpiInterfaceDesc(String configSpiInterfaceDesc) {
        this.configSpiInterfaceDesc = configSpiInterfaceDesc;
    }

    /**
     * This method returns the value of the database column p_gateway_config.config_spi_class
     *
     * @return the value of p_gateway_config.config_spi_class
     */
    public String getConfigSpiClass() {
        return configSpiClass;
    }

    /**
     */
    public GatewayConfig withConfigSpiClass(String configSpiClass) {
        this.setConfigSpiClass(configSpiClass);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.config_spi_class
     *
     * @param configSpiClass the value for p_gateway_config.config_spi_class
     */
    public void setConfigSpiClass(String configSpiClass) {
        this.configSpiClass = configSpiClass;
    }

    /**
     * This method returns the value of the database column p_gateway_config.config_spi_name
     *
     * @return the value of p_gateway_config.config_spi_name
     */
    public String getConfigSpiName() {
        return configSpiName;
    }

    /**
     */
    public GatewayConfig withConfigSpiName(String configSpiName) {
        this.setConfigSpiName(configSpiName);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.config_spi_name
     *
     * @param configSpiName the value for p_gateway_config.config_spi_name
     */
    public void setConfigSpiName(String configSpiName) {
        this.configSpiName = configSpiName;
    }

    /**
     * This method returns the value of the database column p_gateway_config.config_spi_desc
     *
     * @return the value of p_gateway_config.config_spi_desc
     */
    public String getConfigSpiDesc() {
        return configSpiDesc;
    }

    /**
     */
    public GatewayConfig withConfigSpiDesc(String configSpiDesc) {
        this.setConfigSpiDesc(configSpiDesc);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.config_spi_desc
     *
     * @param configSpiDesc the value for p_gateway_config.config_spi_desc
     */
    public void setConfigSpiDesc(String configSpiDesc) {
        this.configSpiDesc = configSpiDesc;
    }

    /**
     * This method returns the value of the database column p_gateway_config.key
     *
     * @return the value of p_gateway_config.key
     */
    public String getKey() {
        return key;
    }

    /**
     */
    public GatewayConfig withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.key
     *
     * @param key the value for p_gateway_config.key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * This method returns the value of the database column p_gateway_config.label
     *
     * @return the value of p_gateway_config.label
     */
    public String getLabel() {
        return label;
    }

    /**
     */
    public GatewayConfig withLabel(String label) {
        this.setLabel(label);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.label
     *
     * @param label the value for p_gateway_config.label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * This method returns the value of the database column p_gateway_config.default_value
     *
     * @return the value of p_gateway_config.default_value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     */
    public GatewayConfig withDefaultValue(String defaultValue) {
        this.setDefaultValue(defaultValue);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.default_value
     *
     * @param defaultValue the value for p_gateway_config.default_value
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * This method returns the value of the database column p_gateway_config.placeholder
     *
     * @return the value of p_gateway_config.placeholder
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     */
    public GatewayConfig withPlaceholder(String placeholder) {
        this.setPlaceholder(placeholder);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.placeholder
     *
     * @param placeholder the value for p_gateway_config.placeholder
     */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    /**
     * This method returns the value of the database column p_gateway_config.value
     *
     * @return the value of p_gateway_config.value
     */
    public String getValue() {
        return value;
    }

    /**
     */
    public GatewayConfig withValue(String value) {
        this.setValue(value);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.value
     *
     * @param value the value for p_gateway_config.value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * This method returns the value of the database column p_gateway_config.required
     *
     * @return the value of p_gateway_config.required
     */
    public Boolean getRequired() {
        return required;
    }

    /**
     */
    public GatewayConfig withRequired(Boolean required) {
        this.setRequired(required);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.required
     *
     * @param required the value for p_gateway_config.required
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }

    /**
     * This method returns the value of the database column p_gateway_config.multi
     *
     * @return the value of p_gateway_config.multi
     */
    public Boolean getMulti() {
        return multi;
    }

    /**
     */
    public GatewayConfig withMulti(Boolean multi) {
        this.setMulti(multi);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.multi
     *
     * @param multi the value for p_gateway_config.multi
     */
    public void setMulti(Boolean multi) {
        this.multi = multi;
    }

    /**
     * This method returns the value of the database column p_gateway_config.status
     *
     * @return the value of p_gateway_config.status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     */
    public GatewayConfig withStatus(Boolean status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.status
     *
     * @param status the value for p_gateway_config.status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method returns the value of the database column p_gateway_config.push
     *
     * @return the value of p_gateway_config.push
     */
    public String getPush() {
        return push;
    }

    /**
     */
    public GatewayConfig withPush(String push) {
        this.setPush(push);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.push
     *
     * @param push the value for p_gateway_config.push
     */
    public void setPush(String push) {
        this.push = push;
    }

    /**
     * This method returns the value of the database column p_gateway_config.remark
     *
     * @return the value of p_gateway_config.remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     */
    public GatewayConfig withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.remark
     *
     * @param remark the value for p_gateway_config.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method returns the value of the database column p_gateway_config.insert_time
     *
     * @return the value of p_gateway_config.insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     */
    public GatewayConfig withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.insert_time
     *
     * @param insertTime the value for p_gateway_config.insert_time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method returns the value of the database column p_gateway_config.update_time
     *
     * @return the value of p_gateway_config.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public GatewayConfig withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.update_time
     *
     * @param updateTime the value for p_gateway_config.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method returns the value of the database column p_gateway_config.is_del
     *
     * @return the value of p_gateway_config.is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     */
    public GatewayConfig withIsDel(Boolean isDel) {
        this.setIsDel(isDel);
        return this;
    }

    /**
     * This method sets the value of the database column p_gateway_config.is_del
     *
     * @param isDel the value for p_gateway_config.is_del
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
        sb.append(", configSpiInterface=").append(configSpiInterface);
        sb.append(", configSpiInterfaceDesc=").append(configSpiInterfaceDesc);
        sb.append(", configSpiClass=").append(configSpiClass);
        sb.append(", configSpiName=").append(configSpiName);
        sb.append(", configSpiDesc=").append(configSpiDesc);
        sb.append(", key=").append(key);
        sb.append(", label=").append(label);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", placeholder=").append(placeholder);
        sb.append(", value=").append(value);
        sb.append(", required=").append(required);
        sb.append(", multi=").append(multi);
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
        GatewayConfig other = (GatewayConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGatewayGroup() == null ? other.getGatewayGroup() == null : this.getGatewayGroup().equals(other.getGatewayGroup()))
            && (this.getGatewayInstanceId() == null ? other.getGatewayInstanceId() == null : this.getGatewayInstanceId().equals(other.getGatewayInstanceId()))
            && (this.getConfigSpiInterface() == null ? other.getConfigSpiInterface() == null : this.getConfigSpiInterface().equals(other.getConfigSpiInterface()))
            && (this.getConfigSpiInterfaceDesc() == null ? other.getConfigSpiInterfaceDesc() == null : this.getConfigSpiInterfaceDesc().equals(other.getConfigSpiInterfaceDesc()))
            && (this.getConfigSpiClass() == null ? other.getConfigSpiClass() == null : this.getConfigSpiClass().equals(other.getConfigSpiClass()))
            && (this.getConfigSpiName() == null ? other.getConfigSpiName() == null : this.getConfigSpiName().equals(other.getConfigSpiName()))
            && (this.getConfigSpiDesc() == null ? other.getConfigSpiDesc() == null : this.getConfigSpiDesc().equals(other.getConfigSpiDesc()))
            && (this.getKey() == null ? other.getKey() == null : this.getKey().equals(other.getKey()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()))
            && (this.getDefaultValue() == null ? other.getDefaultValue() == null : this.getDefaultValue().equals(other.getDefaultValue()))
            && (this.getPlaceholder() == null ? other.getPlaceholder() == null : this.getPlaceholder().equals(other.getPlaceholder()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getRequired() == null ? other.getRequired() == null : this.getRequired().equals(other.getRequired()))
            && (this.getMulti() == null ? other.getMulti() == null : this.getMulti().equals(other.getMulti()))
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
        result = prime * result + ((getConfigSpiInterface() == null) ? 0 : getConfigSpiInterface().hashCode());
        result = prime * result + ((getConfigSpiInterfaceDesc() == null) ? 0 : getConfigSpiInterfaceDesc().hashCode());
        result = prime * result + ((getConfigSpiClass() == null) ? 0 : getConfigSpiClass().hashCode());
        result = prime * result + ((getConfigSpiName() == null) ? 0 : getConfigSpiName().hashCode());
        result = prime * result + ((getConfigSpiDesc() == null) ? 0 : getConfigSpiDesc().hashCode());
        result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        result = prime * result + ((getDefaultValue() == null) ? 0 : getDefaultValue().hashCode());
        result = prime * result + ((getPlaceholder() == null) ? 0 : getPlaceholder().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getRequired() == null) ? 0 : getRequired().hashCode());
        result = prime * result + ((getMulti() == null) ? 0 : getMulti().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPush() == null) ? 0 : getPush().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        return result;
    }

    /**
     * corresponding to the database table p_gateway_config
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        gatewayGroup("gateway_group", "gatewayGroup", "VARCHAR", false),
        gatewayInstanceId("gateway_instance_id", "gatewayInstanceId", "VARCHAR", false),
        configSpiInterface("config_spi_interface", "configSpiInterface", "VARCHAR", false),
        configSpiInterfaceDesc("config_spi_interface_desc", "configSpiInterfaceDesc", "VARCHAR", false),
        configSpiClass("config_spi_class", "configSpiClass", "VARCHAR", false),
        configSpiName("config_spi_name", "configSpiName", "VARCHAR", false),
        configSpiDesc("config_spi_desc", "configSpiDesc", "VARCHAR", false),
        key("key", "key", "VARCHAR", true),
        label("label", "label", "VARCHAR", true),
        defaultValue("default_value", "defaultValue", "VARCHAR", false),
        placeholder("placeholder", "placeholder", "VARCHAR", false),
        value("value", "value", "VARCHAR", true),
        required("required", "required", "BIT", false),
        multi("multi", "multi", "BIT", false),
        status("status", "status", "BIT", true),
        push("push", "push", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false),
        insertTime("insert_time", "insertTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        isDel("is_del", "isDel", "BIT", false);

        /**
         * Corresponding to the database table p_gateway_config
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_config
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * Corresponding to the database table p_gateway_config
         */
        private final String column;

        /**
         * Corresponding to the database table p_gateway_config
         */
        private final boolean isColumnNameDelimited;

        /**
         * Corresponding to the database table p_gateway_config
         */
        private final String javaProperty;

        /**
         * Corresponding to the database table p_gateway_config
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