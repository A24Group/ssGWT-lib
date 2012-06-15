package org.ssgwt.client.validation.validators;

import java.util.HashMap;

/**
 * Interface describing a single validator.
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 12 June 2012
 */
public interface ValidatorInterface<T> {

    /**
     * Validates the value passed in.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @return Whether the value is valid or not
     */
    public boolean isValid(T value);

    /**
     * Returns the name of the validator.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @return The validator name
     */
    public String getName();

    /**
     * Sets the validators configuration if required.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @param config the configuration.
     * 
     * @return void
     */
    public void setConfiguration(HashMap<String, ?> config);
}