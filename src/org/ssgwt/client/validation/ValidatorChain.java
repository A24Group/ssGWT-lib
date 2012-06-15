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
