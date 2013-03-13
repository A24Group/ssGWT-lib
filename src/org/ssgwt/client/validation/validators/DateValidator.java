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
public class DateValidator extends AbstractValidator implements ValidatorInterface<Date> {

    /**
     * The date format to parse with.
     */
    public static final String DATE_FORMAT = "dd MMM yyyy";

    /**
     * Name of the validator name.
     */
    private static final String VALIDATOR_NAME = "DateValidator";
    
    /**
     * Default error message to use for validation
     */
    private static final String DEFAULT_VALIDATION_MESSAGE = "Invalid date entered";

    /**
     * The index for the configuration item to set a custom date format.
     */
    public static final String CONFIG_DATE_FORMAT = "dateFormat";
    
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
    public boolean isValid(Date value) {
        try {
            if ( value == null ) {
                return false;
            }
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
