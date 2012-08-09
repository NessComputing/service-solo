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
                                             "ness.httpserver.class", SoloJetty8HttpServer.class.getName());
            }
        };

        server.startServer();
    }
}

