/**
 * Copyright (C) 2011 Ness Computing, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ness.solo;

import com.nesscomputing.config.Config;
import com.nesscomputing.httpserver.SoloJetty8HttpServer;

public class SoloMain
{
    public static void main(final String ... args)
    {
        final SoloServer server = new SoloServer() {
            @Override
            public Config getConfig()
            {
                return Config.getFixedConfig("ness.httpserver.service-uri", "http://localhost:8080/",
                                             "ness.httpserver.class", SoloJetty8HttpServer.class.getName(),
                                             "ness.job.ticker.class", MessageTickerJob.class.getName(),
                                             "ness.job.ticker.repeat", "1s",
                                             "ness.job.ticker.enabled", "true",
                                             "org.quartz.scheduler.instanceName", "TickingScheduler",
                                             "org.quartz.threadPool.threadCount", "3",
                                             "org.quartz.scheduler.interruptJobsOnShutdown", "true",
                                             "ness.quartz.start-delay", "5s",
                                             "ness.quart.shutdown-wait-for-jobs", "false",
                                             "ness.event.transport", "jms",
                                             "ness.event.jms.enabled", "true",
                                             "ness.event.jms.transmit-enabled", "true",
                                             "ness.event.jms.listen-enabled", "false",

                                             "ness.jms.enabled", "true",
                                             "ness.jms.lazy-transmitter-connect", "false",
                                             "ness.jms.connection-url", "failover:(tcp://localhost:21045?daemon=true)?maxReconnectAttempts=10",
                                             "ness.jms.listen-enabled", "false",
                                             "ness.jms.transmit-enabled", "true",

                                             "ness.jms.jms-event.enabled", "true",
                                             "ness.jms.jms-event.producer-queue-length", "2000",
                                             "ness.jms.jms-event.transmit-timeout", "100ms");
            }
        };

        server.startServer();
    }
}
