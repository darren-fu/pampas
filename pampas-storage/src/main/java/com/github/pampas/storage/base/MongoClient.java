package com.github.pampas.storage.base;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
public class MongoClient {
    private static Datastore datastore;

    public static Datastore mongo() {

        if (datastore == null) {
            synchronized (MongoClient.class) {
                if (datastore == null) {
                    Morphia morphia = new Morphia();

                    // 告诉Morphia在哪里找到你的类
                    // 可以为不同的包或者类进行多次的调用
                    morphia.mapPackage("com.github.pampas.storage");
                    //创建datastore，并连接到指定数据库
                    //datastore有两个参数，第一个用来连接到MongoDB，第二个是数据库的名字。
                    datastore = morphia.createDatastore(new com.mongodb.MongoClient(), "morphia_example");
                    datastore.ensureIndexes();
                }
            }
        }

        return datastore;
    }

}
