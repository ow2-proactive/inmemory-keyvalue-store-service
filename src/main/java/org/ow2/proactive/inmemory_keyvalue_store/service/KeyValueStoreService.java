/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package org.ow2.proactive.inmemory_keyvalue_store.service;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.objectweb.proactive.core.ProActiveException;
import org.ow2.proactive.addons.inmemory_keyvalue_store.InMemoryKeyValueStore;
import org.ow2.proactive.addons.inmemory_keyvalue_store.InMemoryKeyValueStoreRunner;
import org.springframework.stereotype.Component;

import com.google.common.collect.Multimap;


/**
 * @author ActiveEon Team
 */
@Component
public class KeyValueStoreService {

    private String keyValueStoreUrl;

    // ProActive Active Object (remote object)
    private InMemoryKeyValueStore keyValueStoreStub;

    @PostConstruct
    public void initProActiveKeyValueStore() throws ProActiveException {
        InMemoryKeyValueStoreRunner inMemoryKeyValueStoreRunner = new InMemoryKeyValueStoreRunner();

        keyValueStoreStub = InMemoryKeyValueStore.newActive();
        keyValueStoreUrl = inMemoryKeyValueStoreRunner.exposeKeyValueStore(keyValueStoreStub);
    }

    public String getKeyValueStoreUrl() {
        return keyValueStoreUrl;
    }

    public Multimap<String, String> getChannels() {
        return keyValueStoreStub.getChannels();
    }

    public Map<String, Map<String, Serializable>> getData() {
        return keyValueStoreStub.getData();
    }

}
