package org.ssgwt.client.validation.validators;

/**
 * String Validator.
 * 
 * This class is used to validate string properties and values.
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 12 June 2012
 */
public class StringValidator extends AbstractValidator implements ValidatorInterface<String> {

    /**
     * Name of the validator name.
     */
    private static final String VALIDATOR_NAME = "StringValidator";

    /**
     * Constant representing the maximum length for the string.
     */
    public static final String OPTION_MAX_LENGTH = "max-length";

    /**
     * Constant representing the minimum length for the string.
     */
    public static final String OPTION_MIN_LENGTH = "min-length";

    /**
     * Validates the value passed in.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @return Whether valid string or not
     */
    @Override
    public boolean isValid(String value) {
        return (this.checkMaximumLength(value) && this
                .checkMinimumlength(value));
    }

    /**
     * Checks that the string adheres to the maximum string length.
     * 
     * @param value
     *            The value being checked.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @return Whether the value is valid or not.
     */
    private boolean checkMaximumLength(String value) {
        if (this.configuration.containsKey(StringValidator.OPTION_MAX_LENGTH)) {
            String maxLengthConfig = this.configuration.get(
                    StringValidator.OPTION_MAX_LENGTH).toString();
            int maxLength = Integer.parseInt(maxLengthConfig);

            if (value.length() > maxLength) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that the string adheres to the minimum string length.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @param value The value being checked.
     * 
     * @return Whether the value is valid or not.
     */
    private boolean checkMinimumlength(String value) {
        // check the minimum length
        if (this.configuration.containsKey(StringValidator.OPTION_MIN_LENGTH)) {
            String minlengthConfiguration = this.configuration.get(
                    StringValidator.OPTION_MIN_LENGTH).toString();
            int minlength = Integer.parseInt(minlengthConfiguration);

            if (value.length() < minlength) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the validator name.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     */
    @Override
    public String getName() {
        return StringValidator.VALIDATOR_NAME;
    }
}
