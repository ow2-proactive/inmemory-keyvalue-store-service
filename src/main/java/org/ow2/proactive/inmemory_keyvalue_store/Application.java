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
package org.ow2.proactive.inmemory_keyvalue_store;

import static springfox.documentation.builders.PathSelectors.regex;

import org.ow2.proactive.inmemory_keyvalue_store.controller.KeyValueStoreRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author ActiveEon Team
 */
@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
@PropertySource("classpath:application.properties")
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("springboot")
                                                      .apiInfo(apiInfo())
                                                      .select()
                                                      .apis(input -> input.getHandlerMethod()
                                                                          .getMethod()
                                                                          .getDeclaringClass()
                                                                          .isAssignableFrom(KeyValueStoreRestController.class))
                                                      .paths(regex("/.*"))
                                                      .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("In-Memory Key-Value Store")
                                   .contact(new Contact("ActiveEon",
                                                        "https://www.activeeon.com",
                                                        "contact@activeeon.com"))
                                   .licenseUrl("https://raw.githubusercontent.com/ow2-proactive/inmemory-keyvalue-store-service/master/LICENSE.txt")
                                   .version("1.0")
                                   .build();
    }

}
