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
package org.ow2.proactive.inmemory_keyvalue_store.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.ow2.proactive.inmemory_keyvalue_store.service.KeyValueStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;


/**
 * @author ActiveEon Team
 */
@RestController
@RequestMapping(value = "/")
public class KeyValueStoreRestController {

    @Autowired
    private KeyValueStoreService keyValueStoreService;

    @RequestMapping(value = "/channels", method = RequestMethod.GET)
    public Map<String, Collection<String>> listChannels() {
        ImmutableMap.Builder<String, Collection<String>> builder = ImmutableMap.builder();

        Multimap<String, String> channels = keyValueStoreService.getChannels();

        channels.keySet().forEach(k -> {
            builder.put(k, channels.get(k));
        });

        return builder.build();
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Map<String, Map<String, Serializable>> listData() {
        return keyValueStoreService.getData();
    }

    @RequestMapping(value = "/proactive", method = RequestMethod.GET)
    public String getKeyValueStoreProActiveUrl() {
        return keyValueStoreService.getKeyValueStoreUrl();
    }

}