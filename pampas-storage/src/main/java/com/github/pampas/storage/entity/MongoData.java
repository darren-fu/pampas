package com.github.pampas.storage.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */

@Data
public abstract class MongoData<T> {

    @Id
    private ObjectId id;

    @Version
    private Long version;

    private T data;
}
