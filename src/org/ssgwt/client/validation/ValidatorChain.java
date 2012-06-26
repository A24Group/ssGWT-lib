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

package org.ssgwt.client.validation;

import java.util.ArrayList;
import java.util.Iterator;

import org.ssgwt.client.validation.validators.ValidatorInterface;

/**
 * Validator chain.
 * 
 * Allows a developer to chain together multiple validators to validate a single
 * value.
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 12 June 2012
 * 
 * @param <T>
 */
public class ValidatorChain<T> {

    /**
     * Array list containing all validators added to the validator chain.
     */
    private ArrayList<ValidatorInterface<T>> validators = new ArrayList<ValidatorInterface<T>>();

    /**
     * Adds a validator to the chain of validators.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @param validator The validator to add to the chain
     * 
     * @return void
     */
    public void addValidator(ValidatorInterface<T> validator) {
        this.validators.add(validator);
    }

    /**
     * Checks all validators in the chain and returns whether it is valid or
     * not.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @return Whether the value is valid or not
     */
    public <T> boolean isValid(T value) {
        // innocent until proven guilty
        boolean isValid = true;

        for (Iterator i = this.validators.iterator(); i.hasNext();) {
            ValidatorInterface<T> validator = (ValidatorInterface<T>) i.next();

            if (validator.isValid(value) == false) {
                isValid = false;
            }
        }
        return isValid;
    }
}
