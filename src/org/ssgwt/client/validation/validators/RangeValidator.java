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

import org.ssgwt.client.i18n.SSDate;

/**
 * Range Validator.
 *
 * This class is used to validate that a number is within a range
 *
 * @author Deon De Wet <deon.dewet@a24group.com>
 * @since  16 Sept 2020
 */
public class RangeValidator extends AbstractValidator implements ValidatorInterface<String> {

    /**
     * Name of the validator name.
     */
    private static final String VALIDATOR_NAME = "RangeValidator";

    /**
     * Default error message to use for validation
     */
    private static final String DEFAULT_VALIDATION_MESSAGE = "Number entered is not within the valid range";

    /**
     * The minimum value to validate for
     */
    public static final String CONFIG_MIN_VALUE = "minValue";

    /**
     * The maximum value to validate for
     */
    public static final String CONFIG_MAX_VALUE = "maxValue";

    /**
     * Validates the value passed in.
     *
     * @param value The value to validate
     *
     * @author Deon De Wet <deon.dewet@a24group.com>
     * @since  16 Sept 2020
     *
     * @return whether the value is valid or not
     */
    @Override
    public boolean isValid(String value) {
        //check the configuration for validation to perform on value
        if (this.configuration.containsKey(RangeValidator.CONFIG_MIN_VALUE)) {
            Float fValue = Float.parseFloat(value);
            Float fMinValue = Float.parseFloat(this.configuration.get(RangeValidator.CONFIG_MIN_VALUE).toString());

            if (fValue < fMinValue) {
                return false;
            }
        }

        if (this.configuration.containsKey(RangeValidator.CONFIG_MAX_VALUE)) {
            Float fValue = Float.parseFloat(value);
            Float fMaxValue = Float.parseFloat(this.configuration.get(RangeValidator.CONFIG_MAX_VALUE).toString());

            if (fValue > fMaxValue) {
                return false;
            }
        }

        return true;
    }

    /**
     * Retrieves the validator name.
     *
     * @author Deon De Wet <deon.dewet@a24group.com>
     * @since  16 Sept 2020
     *
     * @return validator name
     */
    @Override
    public String getName() {
        return RangeValidator.VALIDATOR_NAME;
    }

    /**
     * Returns default error message to use for validation
     *
     * @author Deon De Wet <deon.dewet@a24group.com>
     * @since  16 Sept 2020
     *
     * @return The default error message to use
     */
    @Override
    public String getDefaultValidationMessage() {
        return RangeValidator.DEFAULT_VALIDATION_MESSAGE;
    }

}
