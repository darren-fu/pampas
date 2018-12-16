package com.github.pampas.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-06
 */
@Spi(scope = Scope.SINGLETON, desc = "可定义配置")
public interface DefinableConfig extends VersionConfig {


    List<PropDefine> propDefines();

    default ConfigLevelEnum configLevel() {
        return ConfigLevelEnum.GROUP;
    }

    @Data
    class PropDefine {

        public PropDefine(String key, String label) {
            this(ConfigLevelEnum.GROUP, key, label, null);
        }

        public PropDefine(String key, String label, String placeholder) {
            this(ConfigLevelEnum.GROUP, key, label, placeholder);
        }

        public PropDefine(ConfigLevelEnum level, String key, String label, String placeholder) {
            this.level = level;
            this.key = key;
            this.label = label;
            this.placeholder = placeholder;
        }


        private ConfigLevelEnum level;

        private String key;

        private String label;

        private String placeholder;

        private boolean required = true;

        private boolean multi = false;
    }

    enum ConfigLevelEnum {
        GROUP("group"), INSTANCE("instance");

        private String value;

        ConfigLevelEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static ConfigLevelEnum getEnum(String value) {
            for (ConfigLevelEnum anEnum : values()) {
                if (anEnum.getValue().equals(value)) {
                    return anEnum;
                }
            }
            throw new IllegalArgumentException("不合法的数据:" + value);
        }


    }
}
