package org.ssgwt.client.validation.validators;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Date Validator.
 * 
 * This class is used to validate date properties and values.
 * 
 * @todo Allow user to pass in date format via configuration
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 12 June 2012
 */
public class DateValidator extends AbstractValidator implements ValidatorInterface<String> {

    /**
     * The date format to parse with.
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Name of the validator name.
     */
    private static final String VALIDATOR_NAME = "DateValidator";

    /**
     * Validates the value passed in.
     * 
     * @param value
     *            The value to validate
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 13 June 2012
     * 
     * @return whether the value is valid or not
     */
    @Override
    public boolean isValid(String value) {
        try {
            Date date = DateTimeFormat.getFormat(DateValidator.DATE_FORMAT).parse(value);
        } catch (Exception exception) {
            return false;
        }

        return true;
    }

    /**
     * Retrieves the validator name.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 13 June 2012
     * 
     * @return validator name
     */
    @Override
    public String getName() {
        return DateValidator.VALIDATOR_NAME;
    }
}
