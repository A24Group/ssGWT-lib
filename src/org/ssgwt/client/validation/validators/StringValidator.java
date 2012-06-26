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
     * Default error message to use for validation
     */
    private static final String DEFAULT_VALIDATION_MESSAGE = "Invalid or no text entered";
    
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
    
    /**
     * Returns default error message to use for validation
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 26 June 2012
     * 
     * @return The default error message to use
     */
    public String getDefaultValidationMessage() {;
        return StringValidator.DEFAULT_VALIDATION_MESSAGE;
    }
}
