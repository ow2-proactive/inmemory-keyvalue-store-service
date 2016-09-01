/*
 * ################################################################
 *
 * ProActive Parallel Suite(TM): The Java(TM) library for
 *    Parallel, Distributed, Multi-Core Computing for
 *    Enterprise Grids & Clouds
 *
 * Copyright (C) 1997-2016 INRIA/University of
 *                 Nice-Sophia Antipolis/ActiveEon
 * Contact: proactive@ow2.org or contact@activeeon.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; version 3 of
 * the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 *
 *  Initial developer(s):               The ProActive Team
 *                        http://proactive.inria.fr/team_members.htm
 *  Contributor(s):
 *
 * ################################################################
 * $$PROACTIVE_INITIAL_DEV$$
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
