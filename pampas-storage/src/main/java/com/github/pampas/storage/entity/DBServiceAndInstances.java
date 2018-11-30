package com.github.pampas.storage.entity;

import com.github.pampas.common.discover.ServerInstance;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
@Data
public class DBServiceAndInstances extends MongoData {

    public static final String ENTITY = "service_instance";

    private String serviceName;

    private List<ServerInstance> instanceList;
}
