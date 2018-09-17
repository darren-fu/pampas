package com.github.pampas.storage.entity;

import com.github.pampas.common.discover.ServerInstance;
import lombok.Data;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
@Entity(value = DBServiceAndInstances.ENTITY)
@Data
public class DBServiceAndInstances extends MongoData {

    public static final String ENTITY = "service_instance";

    private String serviceName;

    @Embedded
    private List<ServerInstance> instanceList;
}
