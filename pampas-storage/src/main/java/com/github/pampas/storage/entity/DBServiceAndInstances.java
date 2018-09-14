package com.github.pampas.storage.entity;

import com.github.pampas.common.discover.ServerInstance;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
@Entity(value = "service_instance")
@Data
public class DBServiceAndInstances {

    @Id
    private ObjectId id;

    @Version
    private Long version;

    private String serviceName;

    @Embedded
    private List<ServerInstance> instanceList;
}
