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
package com.github.pampas.common.tracer;

import io.opentracing.NoopTracerFactory;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * trace factory
 * from motan
 *
 * @author darrenfu
 */
public interface TracerFactory {

    TracerFactory DEFAULT = new DefaultTracerFactory();

    /**
     * get a Tracer implementation. this method may called every request, consider whether singleton
     * pattern is needed
     *
     * @return
     */
    Tracer getTracer();

    public static void main(String[] args) {
        Tracer tracer = DEFAULT.getTracer();
        System.out.println(tracer);

    }

    class DefaultTracerFactory implements TracerFactory {
        static final Logger log = LoggerFactory.getLogger(DefaultTracerFactory.class);

        private static Tracer tracer = NoopTracerFactory.create();

        static {
            loadDefaultTracer();
        }

        /**
         * load SPI Tracer and set default only if one tracer is found.
         */
        private static void loadDefaultTracer() {
            try {
                Iterator<Tracer> implementations = ServiceLoader.load(Tracer.class, Tracer.class.getClassLoader()).iterator();
                if (implementations.hasNext()) {
                    Tracer firstTracer = implementations.next();
                    if (!implementations.hasNext()) {
                        // only one tracer is found.
                        tracer = firstTracer;
                        log.info("io.opentracing.Tracer load in DefaultTracerFactory, " + tracer.getClass().getSimpleName()
                                + " is used as default tracer.");
                    } else {
                        log.info("io.opentracing.Tracer load in DefaultTracerFactory, NoopTracer is used as default tracer since more than one tracer is found.");
                    }

                }
            } catch (Exception e) {
                log.warn("DefaultTracerFactory load Tracer fail.", e);
            }
        }


        @Override
        public Tracer getTracer() {
            return tracer;
        }

    }
}
