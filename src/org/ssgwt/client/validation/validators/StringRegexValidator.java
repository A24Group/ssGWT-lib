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
 * String regex validator class.
 * 
 * This class is used to validate values with certain regex patterns
 * 
 * @author Ryno Hartzer <ryno.hartzer@a24group.com>
 * @since 02 July 2012
 */
public class StringRegexValidator extends AbstractValidator implements
        ValidatorInterface<String> {

    /**
     * Name of the validator name.
     */
    private static final String VALIDATOR_NAME = "StringRegexValidator";
    
    /**
     * Config key to retrieve from the hash map
     */
    public static final String VALIDATOR_REGEX_NAME = "RegexPattern";
    
    /**
     * Default error message to use for validation
     */
    private static final String DEFAULT_VALIDATION_MESSAGE = "Invalid characters were found";
    
    /**
     * This regular expression is used to make sure that
     * some special characters can be part of a name.
     * We allow a hyphen or space in the middle of the name.
     * Some accent and gravis symbols are also allowed.
     */
    public static final String REGEX_NAME_PATTERN = "^[A-Za-zàáâäãåèéêëìíîïòóôöõøùúûüÿýñçčšžÀÁÂÄÃÅÈÉÊËÌÍÎÏÒÓÔÖÕØÙÚÛÜŸÝÑßÇŒÆČŠŽ∂ð]+([A-Z a-zàáâäãåèéêëìíîïòóôöõøùúûüÿýñçčšžÀÁÂÄÃÅÈÉÊËÌÍÎÏÒÓÔÖÕØÙÚÛÜŸÝÑßÇŒÆČŠŽ∂ð\\-]*[A-Za-zàáâäãåèéêëìíîïòóôöõøùúûüÿýñçčšžÀÁÂÄÃÅÈÉÊËÌÍÎÏÒÓÔÖÕØÙÚÛÜŸÝÑßÇŒÆČŠŽ∂ð]){0,}$";
    
    /**
     * This regular expression is used to make sure that
     * some special characters and numbers can be part of a name.
     * We allow a hyphen or space in the middle of the name.
     * Some accent and gravis symbols are also allowed.
     */
    public static final String REGEX_NAME_PATTERN_NUMBERS = "^[0-9A-Za-zàáâäãåèéêëìíîïòóôöõøùúûüÿýñçčšžÀÁÂÄÃÅÈÉÊËÌÍÎÏÒÓÔÖÕØÙÚÛÜŸÝÑßÇŒÆČŠŽ∂ð&]+([0-9A-Z a-zàáâäãåèéêëìíîïòóôöõøùúûüÿýñçčšžÀÁÂÄÃÅÈÉÊËÌÍÎÏÒÓÔÖÕØÙÚÛÜŸÝÑßÇŒÆČŠŽ∂ð\\'-]*[0-9A-Za-zàáâäãåèéêëìíîïòóôöõøùúûüÿýñçčšžÀÁÂÄÃÅÈÉÊËÌÍÎÏÒÓÔÖÕØÙÚÛÜŸÝÑßÇŒÆČŠŽ∂ð&]){0,}$";
   
    /**
     * The regular expression patterns string to be used to validate an email address.
     */
    public static final String REGEX_EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /**
     * The regular expression pattern string to be used to validate alpha numeric values.
     */
    public static final String REGEX_ALPHA_NUMERIC_PATTERN = "^[a-zA-Z0-9]*$";

    /**
     * The regular expression patterns string to be used to validate an Telephopne numbers.
     */
    public static final String REGEX_TELEPHONE_NUMBER_PATTERN = "(^[\\+][0-9]{0,15}$)|(^0{1}[0-9]{0,14}$)";
    
    /**
     * Validates the value passed in with the set regular expression.
     * 
     * Regular expression pattern is set via the config hash map
     * 
     * @param sValue The value to validate
     * 
     * @author Ryno Hartzer <ryno.hartzer@a24group.com>
     * @since 12 June 2012
     * 
     * @return Whether the value matched the pattern or not
     */
    @Override
    public boolean isValid(String sValue) {
        String sRegExpression = this.configuration.get(VALIDATOR_REGEX_NAME).toString();
        return this.validateRegularExpressionPattern(sValue, sRegExpression);
    }

    /**
     * Validate that a given value matches the given regex pattern
     * 
     * @param sValue The value to validate
     * @param sRegExpression The regular expression pattern to validate
     * 
     * @author Ryno Hartzer <ryno.hartzer@a24group.com>
     * @since  02 July 2012
     * 
     * @return Whether the value matched the regex pattern or not
     */
    public boolean validateRegularExpressionPattern(String sValue, String sRegExpression) {
        return sValue.matches(sRegExpression);
    }

    /**
     * Retrieves the validator name.
     * 
     * @author Ryno Hartzer <ryno.hartzer@a24group.com>
     * @since 02 July 2012
     * 
     * @return The validator name
     */
    @Override
    public String getName() {
        return StringRegexValidator.VALIDATOR_NAME;
    }

    /**
     * Returns default error message to use for validation
     * 
     * @author Ryno Hartzer <ryno.hartzer@a24group.com>
     * @since  02 July 2012
     * 
     * @return The default error message to use
     */
    @Override
    public String getDefaultValidationMessage() {
        return StringRegexValidator.DEFAULT_VALIDATION_MESSAGE;
    }
}
