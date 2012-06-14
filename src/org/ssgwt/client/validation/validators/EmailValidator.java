package org.ssgwt.client.validation.validators;

import java.util.regex.Pattern;

/**
 * Validator to validate whether a string value is a valid
 * email address or not.
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 13 June 2012
 */
public class EmailValidator extends AbstractValidator implements ValidatorInterface<String> {
 
    /**
     * The name of the validator
     */
    private static final String VALIDATOR_NAME = "EmailValidator";
    
    /**
     * The regular expression patterns string to be used to validate the value.
     */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Determines whether the value passed in is a valid email address or not.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 13 June 2012
     * 
     * @return Whether the value is a valid email address or not.
     */
    public boolean isValid(String value) {
        return value.matches( EMAIL_PATTERN );
    }

    /**
     * Retrieves the name of the validator
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 13 June 2012
     * 
     * @return Validator name
     */
    @Override
    public String getName() {
        return EmailValidator.VALIDATOR_NAME;
    }
}