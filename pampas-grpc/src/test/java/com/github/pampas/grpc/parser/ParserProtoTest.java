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

package com.github.pampas.grpc.parser;

import com.baidu.jprotobuf.com.squareup.protoparser.OptionElement;
import com.baidu.jprotobuf.com.squareup.protoparser.ProtoFile;
import com.baidu.jprotobuf.com.squareup.protoparser.ProtoParser;
import com.baidu.jprotobuf.com.squareup.protoparser.ServiceElement;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by darrenfu on 18-1-30.
 *
 * @author: darrenfu
 * @date: 18-1-30
 */
public class ParserProtoTest {

    @Test
    public void testParser() throws IOException {
        ClassLoader classLoader = ParserProtoTest.class.getClassLoader();
        URL resource = classLoader.getResource("test.proto");
        System.out.println(resource.getFile());
        File file = new File(resource.getFile());
        ProtoFile protoFile = ProtoParser.parseUtf8(file);
        for (ServiceElement serviceElement : protoFile.services()) {
            System.out.println("serviceElement:" + serviceElement.name());

            serviceElement.rpcs().stream().forEach(System.out::println);
            for (OptionElement optionElement : serviceElement.rpcs().get(0).options()) {
                System.out.println("optionElement:" + optionElement);
                System.out.println(optionElement.value());
                System.out.println(optionElement.value().getClass());
            }
        }
    }


}
