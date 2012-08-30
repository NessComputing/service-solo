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

import com.google.inject.AbstractModule;
import com.google.inject.Module;

import com.nesscomputing.config.Config;
import com.nesscomputing.event.NessEventModule;
import com.nesscomputing.event.jms.JmsEventModule;
import com.nesscomputing.quartz.NessQuartzModule;
import com.nesscomputing.server.StandaloneServer;
import com.nesscomputing.server.templates.BasicGalaxyServerModule;

/**
 * This is the smallest REST server implementation that actually serves data.
 */
public class SoloServer extends StandaloneServer
{
    @Override
    public Module getMainModule(final Config config)
    {
        return new SoloModule(config);
    }

    @Override
    public String getServerType()
    {
        return "solo";
    }

    public static class SoloModule extends AbstractModule
    {
        private final Config config;

        SoloModule(final Config config)
        {
            this.config = config;
        }

        @Override
        public void configure()
        {
            install(new NessEventModule());
            install(new JmsEventModule(config));

            install (new BasicGalaxyServerModule(config));
            install (new NessQuartzModule(config));
        }
    }

}
