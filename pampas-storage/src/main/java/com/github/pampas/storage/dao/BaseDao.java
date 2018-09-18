package com.github.pampas.storage.dao;

import com.github.pampas.storage.base.MongoClient;
import com.github.pampas.storage.entity.MongoData;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.InsertOptions;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.Date;
import java.util.List;

/**
 * Description: Mongo DAO基类
 * User: darrenfu
 * Date: 2018-09-18
 *
 * @param <T> the type parameter
 */
public abstract class BaseDao<T extends MongoData> {

    /**
     * Mongo datastore.
     *
     * @return the datastore
     */
    protected Datastore mongo() {
        return MongoClient.mongo();
    }

    /**
     * Query by example list.
     *
     * @param example the example
     * @return the list
     */
    public List<T> queryByExample(T example) {
        Query<T> ts = mongo().queryByExample(example);
        List<T> tsList = ts.asList();
        return tsList;
    }


    /**
     * Save key.
     *
     * @param t the t
     * @return the key
     */
    public Key<T> save(T t) {
        InsertOptions insertOptions = new InsertOptions();
        Date now = new Date();
        if (t.getCreateAt() == null) {
            t.setCreateAt(now);
        }
        t.setUpdateAt(now);
        return mongo().save(t, insertOptions);
    }

    /**
     * Update update results.
     *
     * @param query            the query
     * @param updateOperations the update operations
     * @return the update results
     */
    public UpdateResults update(Query<T> query, UpdateOperations<T> updateOperations) {
        updateOperations.set("updateAt", new Date());
        return mongo().update(query, updateOperations);
    }

    /**
     * Delete write result.
     *
     * @param query the query
     * @return the write result
     */
    public WriteResult delete(Query<T> query) {
        return mongo().delete(query);
    }
}
