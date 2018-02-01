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

package com.github.pampas.core.tracer;

import brave.Tracing;
import brave.opentracing.BraveTracer;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.Propagation;
import io.opentracing.Tracer;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by darrenfu on 18-1-23.
 *
 * @author: darrenfu
 * @date: 18-1-23
 */
public class ZipkinTraceFactory implements TracerFactory {
    @Override
    public Tracer getTracer() {
        Sender sender = OkHttpSender.create("http://192.168.20.131:9411/api/v2/spans");


        Reporter spanReporter = AsyncReporter.create(sender);

        // If you want to support baggage, indicate the fields you'd like to
        // whitelist, in this case "country-code" and "user-getId". On the wire,
        // they will be prefixed like "baggage-country-code"
        Propagation.Factory propagationFactory = ExtraFieldPropagation.newFactoryBuilder(B3Propagation.FACTORY)
                .addPrefixedFields("baggage-", Arrays.asList("country-code", "user-getId"))
                .build();
        Tracing braveTracing = Tracing.newBuilder()
                .localServiceName("gateway")
                .propagationFactory(propagationFactory)
                .spanReporter(spanReporter)
                .build();
        Tracer tracer = BraveTracer.create(braveTracing);

        return tracer;
    }

    public static void main(String[] args) {
        byte[] arr = new byte[10];
        Arrays.fill(arr, Byte.MAX_VALUE);
        try (Sender sender = OkHttpSender.create("http://192.168.20.131:9411/api/v2/spans")) {
            sender.sendSpans(Collections.singletonList(arr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
