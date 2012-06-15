package org.ssgwt.client.validation.validators;

import java.util.HashMap;

/**
 * Abstract validator allowing setting of configuration for the isValid()
 * method.
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 12 June 2012
 */
public class AbstractValidator {

    /**
     * Validation configuration settings. Will be populated if a validator has
     * some custom configuration or settings that needs to be taken into account
     * by the isValid method.
     */
    HashMap<String, ?> configuration;

    /**
     * Sets the validators configuration if required.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @param config The configuration.
     */
    public void setConfiguration(HashMap<String, ?> config) {
        this.configuration = config;
    }
}