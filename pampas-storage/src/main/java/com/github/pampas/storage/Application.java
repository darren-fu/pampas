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

package com.github.pampas.storage;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * Created by darrenfu on 18-1-18.
 *
 * @author: darrenfu
 * @date: 18-1-18
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        System.out.println(Application.class.getPackage().getName());
//        ConfigurableEnvironment environment =
        applicationContext.scan(Application.class.getPackage().getName());
        applicationContext.refresh();
        Test test = applicationContext.getBean(Test.class);
        test.say();
        System.out.println("val:" + test.getVal());
        System.out.println("----------------------------------");
    }


    @Component
    public static class Test {

        @Value("${abc}")
        private Integer val;

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public String say() {
            System.out.println("我是Test...");
            return "OK";
        }

    }

}
