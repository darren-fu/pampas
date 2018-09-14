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

package com.github.pampas.storage.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.*;

/**
 * Created by darrenfu on 18-1-18.
 *
 * @author: darrenfu
 * @date: 18-1-18
 */
@Entity("employees")
@Indexes(
        @Index(value = "salary", fields = @Field("salary"))
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    private ObjectId id;
    private String name;
    @Reference
    private Employee manager;
    @Reference
    private List<Employee> directReports;
    @Property("wage")
    private Double salary;

    @Embedded
    private List<Info> infos = Arrays.asList(new Info(),new Info(),new Info());


    @Version
    private Long version;

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
        this.directReports = new ArrayList<>();
    }

    @Data
    public static class Info {

        private String addr = "address";
        private int age = 19;

        private Date date = new Date();
    }

}
