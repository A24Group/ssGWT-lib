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
     * Whether or not null will pass the minimum length validation
     */
    private boolean bAllowNullToPassMinimumLenth = false;
    
    /**
     * Whether or not null will pass the maximum length validation
     */
    private boolean bAllowNullToPassMaximumLenth = false;
    
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

            if (value == null) {
                return bAllowNullToPassMaximumLenth;
            }
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

            if (value == null) {
                return this.bAllowNullToPassMinimumLenth;
            } 
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
    @Override
    public String getDefaultValidationMessage() {;
        return StringValidator.DEFAULT_VALIDATION_MESSAGE;
    }
    
    /**
     * Used to set if null is allowed to pass the minimum validation
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 06 March 2013
     * 
     * @param bAllowNullToPassMinimumLenth - Whether to allow null to pass the minimum length validation
     */
    public void setAllowNullToPassMinimumLenth(boolean bAllowNullToPassMinimumLenth) {
        this.bAllowNullToPassMinimumLenth = bAllowNullToPassMinimumLenth;
    }
    
    /**
     * Used to set if null is allowed to pass the maximum validation
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 06 March 2013
     * 
     * @param bAllowNullToPassMaximumLenth - Whether to allow null to pass the maximum length validation
     */
    public void setAllowNullToPassMaximumLenth(boolean bAllowNullToPassMaximumLenth) {
        this.bAllowNullToPassMaximumLenth = bAllowNullToPassMaximumLenth;
    }
    
    /**
     * Used to set if null is allowed to pass the validation
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 06 March 2013
     * 
     * @param bAllowNullToPassLenth - Whether to allow null to pass the length validation
     */
    public void setAllowNullToPassLength(boolean bAllowNullToPassLenth) {
        this.bAllowNullToPassMinimumLenth = bAllowNullToPassLenth;
        this.bAllowNullToPassMaximumLenth = bAllowNullToPassLenth;
    }
    
    /**
     * Used to check if null is allowed to pass the minimum length validation
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 06 March 2013
     * 
     * @return Whether null is allowed to pass the minimum length validation
     */
    public boolean isNullAllowedToPassMinimumLenth() {
        return this.bAllowNullToPassMinimumLenth;
    }
    
    /**
     * Used to check if null is allowed to pass the maximum length validation
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 06 March 2013
     * 
     * @return Whether null is allowed to pass the maximum length validation
     */
    public boolean isNullAllowedToPassMaximumLenth() {
        return this.bAllowNullToPassMaximumLenth;
    }
}
