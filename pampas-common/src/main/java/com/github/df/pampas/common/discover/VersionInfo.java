/*
 *
 *  *  Copyright 2009-2018.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.github.df.pampas.common.discover;

import com.github.df.pampas.common.tools.CommonTools;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式化后的版本信息
 * Created by darrenfu on 17-8-29.
 */
@Data
public class VersionInfo {

    /**
     * 空版本号
     */
    public static final VersionInfo EMPTY_VERSION = new VersionInfo();


    /**
     * 版本number reg
     */
    private static final Pattern VERSION_NUM_REG = Pattern.compile("^\\d+(\\.\\d)*");

    /**
     * 版本stage reg
     */
    private static final Pattern VERSION_STAGE_REG = Pattern.compile("[a-zA-z]+$");

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 原始版本信息 eg.1.2.0-RELEASE
     */
    private String originVersion;

    /**
     * 版本编号 eg.1.2.0 convert to 1.20  2.3.5 convert to 2.35
     */
    private BigDecimal versionNumber;

    /**
     * 版本阶段 SNAPSHOT, BETA, RC, RELEASE等等
     */
    private String versionStage;

    private VersionInfo() {
        this.id = UUID.randomUUID().toString();
    }


    /**
     * 创建版本信息
     *
     * @param originVersion 原始版本数据 1.0.0-RELEASE 2.0RELEASE
     * @return the versionInfo info
     */
    public static VersionInfo create(String originVersion) {
        return create(originVersion, originVersion);
    }


    /**
     * 创建版本信息
     *
     * @param id            the id
     * @param originVersion the origin versionInfo
     * @return the versionInfo info
     */
    public static VersionInfo create(String id, String originVersion) {
        VersionInfo versionInfo = new VersionInfo();
        versionInfo.setId(id);
        versionInfo.setOriginVersion(originVersion);
        // find versionInfo number
        Matcher numMatcher = VERSION_NUM_REG.matcher(originVersion);
        if (numMatcher.find()) {
            versionInfo.setVersionNumber(CommonTools.convertVersionNum(numMatcher.group()));
        }
        //find versionInfo stage
        Matcher stageMatcher = VERSION_STAGE_REG.matcher(originVersion);
        if (stageMatcher.find()) {
            versionInfo.setVersionStage(stageMatcher.group().toUpperCase());
        }
        return versionInfo;
    }


}
