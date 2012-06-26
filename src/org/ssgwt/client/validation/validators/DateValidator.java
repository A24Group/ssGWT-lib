/**
 * Copyright 2012 A24Group
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

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
     * Default error message to use for validation
     */
    private static final String DEFAULT_VALIDATION_MESSAGE = "Invalid date entered";
    
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
    
    /**
     * Returns default error message to use for validation
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 26 June 2012
     * 
     * @return The default error message to use
     */
    @Override
    public String getDefaultValidationMessage() {;
        return DateValidator.DEFAULT_VALIDATION_MESSAGE;
    }

}
