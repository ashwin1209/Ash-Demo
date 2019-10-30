package com.target.myretail.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author Ashwin
 *
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        // scan the resources package for our resources
        packages("com.target.myretail");
    }
}