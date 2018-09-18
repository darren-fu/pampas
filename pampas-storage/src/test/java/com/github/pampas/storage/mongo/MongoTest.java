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

package com.github.pampas.storage.mongo;

import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.storage.entity.DBServiceAndInstances;
import com.github.pampas.storage.entity.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by darrenfu on 18-1-18.
 *
 * @author: darrenfu
 * @date: 18-1-18
 */
public class MongoTest {


    private static Datastore datastore;

    @BeforeClass
    public static void init() {
        Morphia morphia = new Morphia();

        // 告诉Morphia在哪里找到你的类
        // 可以为不同的包或者类进行多次的调用
        morphia.mapPackage("com.github.pampas.persist.entity");

        //创建datastore，并连接到指定数据库
        //datastore有两个参数，第一个用来连接到MongoDB，第二个是数据库的名字。
        datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
        datastore.ensureIndexes();

    }

    @Test
    @Ignore
    public void storeFile() throws IOException {
        File file = new File("/home/darrenfu/IdeaProjects/pampas/pampas-grpc/df/open/grpc/hello/grpc-test-229014610914606914.jar");
        GridFS gridFS = new GridFS(datastore.getDB());

        GridFSInputFile gridFSInputFile = gridFS.createFile(file);
        if (gridFS.findOne(file.getName()) == null) {
            gridFSInputFile.setId(file.getName());
            gridFSInputFile.setMetaData(new BasicDBObject("version", "1.1.2"));
            gridFSInputFile.save();
        }

        GridFSDBFile fsdbFile = gridFS.findOne(file.getName());
        File newfile = new File("/home/darrenfu/IdeaProjects/pampas/pampas-grpc/df/open/grpc/hello/grpc-test-229014610914606914.new.jar");
        if (newfile.exists()) {
            newfile.delete();
        }
        newfile.createNewFile();
        newfile.setWritable(true);

        fsdbFile.writeTo(newfile);
        System.out.println("done : " + fsdbFile.getFilename());
    }


    @Test
    public void addServiceInstance() {

        DBServiceAndInstances serviceAndInstance = new DBServiceAndInstances();

        serviceAndInstance.setServiceName("TestService");
        serviceAndInstance.setInstanceList(Arrays.asList(
                ServerInstance.buildWithUri("TestService", "http://localhost:7001"),
                ServerInstance.buildWithUri("TestService", "http://localhost:7002")
        ));
        Key<DBServiceAndInstances> save = datastore.save(serviceAndInstance);

        System.out.println(save);
        serviceAndInstance.setId((ObjectId)save.getId());
        datastore.save(serviceAndInstance);
    }


    @Test
    public void add() {
        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        datastore.save(elmer);
        final Employee daffy = new Employee("Daffy Duck", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepé Le Pew", 25000.0);
        datastore.save(pepe);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);

        datastore.save(elmer);
    }

    @Test
    public void query() {

        final Query<Employee> query = datastore.createQuery(Employee.class)
                .field("salary").lessThanOrEq(30000)
                .filter("salary <=", 30000);
        final List<Employee> employees = query.asList();
        employees.forEach(System.out::println);
    }

    @Test
    public void update() {
        final Query<Employee> underPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary <=", 50000);

        final UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class)
                .inc("salary", 10000);
        final UpdateResults results = datastore.update(underPaidQuery, updateOperations);

        System.out.println("results:" + results);

    }

    @Test
    public void del() {
        final Query<Employee> overPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary >", 10000);
        datastore.delete(overPaidQuery);

    }
}
