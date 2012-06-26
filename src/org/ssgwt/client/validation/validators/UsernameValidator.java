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

import java.util.regex.Pattern;

/**
 * Validator to validate whether a string value is a valid
 * username or not. Currently accepts only alphanumeric values and
 * the (dot).
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 13 June 2012
 */
public class UsernameValidator extends AbstractValidator implements ValidatorInterface<String> {
 
    /**
     * The name of the validator
     */
    private static final String VALIDATOR_NAME = "UsernameValidator";
    
    /**
     * The regular expression patterns string to be used to validate the value.
     */
    private static final String USERNAME_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*";
    
    /**
     * Default error message to use for validation
     */
    private static final String DEFAULT_VALIDATION_MESSAGE = "Invalid username entered";

    /**
     * Determines whether the value passed in is a valid email address or not.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 13 June 2012
     * 
     * @return Whether the value is a valid email address or not.
     */
    public boolean isValid(String value) {
        return value.matches(USERNAME_PATTERN);
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
        return UsernameValidator.VALIDATOR_NAME;
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
        return UsernameValidator.DEFAULT_VALIDATION_MESSAGE;
    }
}