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
 * Age Validator.
 *
 * This class is used to validate a age
 *
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 26 July 2012
 */
public class AgeValidator extends AbstractValidator implements ValidatorInterface<SSDate> {

    /**
     * Name of the validator name.
     */
    private static final String VALIDATOR_NAME = "AgeValidator";

    /**
     * Default error message to use for validation
     */
    private static final String DEFAULT_VALIDATION_MESSAGE = "Invalid age entered";

    /**
     * The minimum age to validate for
     */
    public static final String CONFIG_MIN_AGE = "minAge";

    /**
     * The maximum age to validate for
     */
    public static final String CONFIG_MAX_AGE = "maxAge";

    /**
     * Validates the value passed in.
     *
     * @param value The value to validate
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 26 July 2012
     *
     * @return whether the value is valid or not
     */
    @Override
    public boolean isValid(SSDate value) {
        //check the configuration for validation to perform on value
        if (this.configuration.containsKey(AgeValidator.CONFIG_MIN_AGE)) {
            //get the current date and subtract the minimum age to get
            //the minimun date back into the past that is allowed
            SSDate minAgeDate = new SSDate();
            int year = minAgeDate.getYear();
            minAgeDate.setYear(year-(Integer)configuration.get(AgeValidator.CONFIG_MIN_AGE));
            minAgeDate.setHours(0);
            minAgeDate.setMinutes(0);
            minAgeDate.setSeconds(1);

            //check if the date passed in is after the
            //minimun date back into the past that is allowed
            if (value.after(minAgeDate)) {
                return false;
            }
        }

        if (this.configuration.containsKey(AgeValidator.CONFIG_MAX_AGE)) {
            //get the current date and subtract the maximum age to get
            //the maximum date back into the past that is allowed
            SSDate maxAgeDate = new SSDate();
            int year = maxAgeDate.getYear();
            maxAgeDate.setYear(year-(Integer)configuration.get(AgeValidator.CONFIG_MAX_AGE)-1);
            maxAgeDate.setHours(0);
            maxAgeDate.setMinutes(0);
            maxAgeDate.setSeconds(1);

            //check if the date passed in is before the
            //maximum date back into the past that is allowed
            if (value.before(maxAgeDate)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Retrieves the validator name.
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 26 July 2012
     *
     * @return validator name
     */
    @Override
    public String getName() {
        return AgeValidator.VALIDATOR_NAME;
    }

    /**
     * Returns default error message to use for validation
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 26 July 2012
     *
     * @return The default error message to use
     */
    @Override
    public String getDefaultValidationMessage() {
        return AgeValidator.DEFAULT_VALIDATION_MESSAGE;
    }

}
