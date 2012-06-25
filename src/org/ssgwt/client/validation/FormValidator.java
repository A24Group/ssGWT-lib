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
import java.util.HashMap;
import org.ssgwt.client.validation.validators.AbstractValidator;
import org.ssgwt.client.validation.validators.DateValidator;
import org.ssgwt.client.validation.validators.EmailValidator;
import org.ssgwt.client.validation.validators.StringValidator;
import org.ssgwt.client.validation.validators.UsernameValidator;
import org.ssgwt.client.validation.validators.ValidatorInterface;
import com.google.gwt.user.client.ui.Widget;

/**
 * The class used to validate the fields
 * 
 * @author Michael Barnard
 * @since 22 June 2012
 *
 */
public class FormValidator {
    
    /**
     * The array list used to store all the form fields
     */
    private ArrayList<FormField> fields;
    
    /**
     * A hashmap used to store the validator interfaces
     */
    private HashMap<String, ValidatorInterface<?>> validatorInstances;
    
    /**
     * Calls the addField(FormField formfield) method with a formfield object created from the parameters
     * 
     * @param validatorReferenceName - The reference name for the validation
     * @param uiField - the ui field the validation is done on
     * @param config - Validation configuration settings
     * @param messageLocaleKey - The locale key for the message
     * @param errorStyleName - the error style type
     */
    public void addField(String validatorReferenceName, Widget uiField, HashMap<String, String> config, String messageLocaleKey, String errorStyleName) {
        //Make an instance of the FormField with the params and call the other addField
    }
    
    /**
     * Calls the addField(FormField formfield) method with a formfield object created from the parameters
     * 
     * @param validatorReferenceName - The reference name for the validation
     * @param uiField - the ui field the validation is done on
     * @param config - Validation configuration settings
     * @param messageLocaleKey - The locale key for the message
     */
    public void addField(String validatorReferenceName, Widget uiField, HashMap<String, String> config, String messageLocaleKey) {
        //call the other(top) addField with some params as null
    }

    /**
     * Calls the addField(FormField formfield) method with a formfield object created from the parameters
     * 
     * @param validatorReferenceName - The reference name for the validation
     * @param uiField - the ui field the validation is done on
     * @param config - Validation configuration settings
     */
    public void addField(String validatorReferenceName, Widget uiField, HashMap<String, String> config) {
        //call the other(top) addField with some params as null
    }
    
    /**
     * Adds a field to the field array 
     * 
     * @param formfield - a formfield object used
     */
    public void addField(FormField formfield) {
        //add a field to the field array
    }
    
    /**
     * Does the validation and sets styles accordingly
     * 
     * @return String - The error string or null if the validation passes
     */
    public String doValidation(){
        //loops through the arraylist and remove all error styles if it is specified.
        //loops through the arraylist and retrieve their values from within the widget
        //gets the instance of the validator 
        //set the config hashmap on the validator
        //check if the value is valid
        //if error
        //    add error style 
        //    return error (return generic if no key found)
        return null;
    }
    
    /**
     * This is a factory function that will determine which instance of the
     * validation classes to create and return based on the reference passed
     * in
     * 
     * @param validatorReferenceName - the reference name to use in the factory
     * @return ValidatorInterface - an instance of the required validation class
     */
    private ValidatorInterface<?> validatorFactory(String validatorReferenceName) {
        //check if instance for validatorReferenceName is already in array and return if true
        if (validatorInstances.containsKey(validatorReferenceName)) {
            return validatorInstances.get(validatorReferenceName);
        }
        
        //create variable to hold the instance to return 
        ValidatorInterface<?> formValidationInstance = null;
        
        //check which instance of the validation classes to instantiate
        if (validatorReferenceName.equals(FormFieldConstants.VALIDATE_DATE_REFERENCE)) {
            formValidationInstance = new DateValidator();
        } else if (validatorReferenceName.equals(FormFieldConstants.VALIDATE_EMAIL_REFERENCE)) {
            formValidationInstance = new EmailValidator();
        } else if (validatorReferenceName.equals(FormFieldConstants.VALIDATE_STRING_REFERENCE)) {
            formValidationInstance = new StringValidator();
        } else if (validatorReferenceName.equals(FormFieldConstants.VALIDATE_USERNAME_REFERENCE)) {
            formValidationInstance = new UsernameValidator();
        }
        
        //add validation class instance to array
        validatorInstances.put(validatorReferenceName, formValidationInstance);
        
        //return the validation class instance
        return formValidationInstance;
    }

}