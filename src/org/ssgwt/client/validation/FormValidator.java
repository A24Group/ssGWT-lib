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

import org.ssgwt.client.i18n.SSDate;
import org.ssgwt.client.ui.AdvancedInputField;
import org.ssgwt.client.validation.validators.AgeValidator;
import org.ssgwt.client.validation.validators.DateValidator;
import org.ssgwt.client.validation.validators.EmailValidator;
import org.ssgwt.client.validation.validators.StringRegexValidator;
import org.ssgwt.client.validation.validators.StringValidator;
import org.ssgwt.client.validation.validators.UsernameValidator;
import org.ssgwt.client.validation.validators.ValidatorInterface;
import org.ssgwt.client.validation.validators.RangeValidator;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * The class used to validate the fields
 *
 * @author Michael Barnard
 * @since 22 June 2012
 */
public class FormValidator {

    /**
     * The array list used to store all the form fields
     */
    private ArrayList<FormField> fields = new ArrayList<FormField>();

    /**
     * A hashmap used to store the validator interfaces
     */
    private HashMap<String, ValidatorInterface<?>> validatorInstances = new HashMap<String, ValidatorInterface<?>>();

    /**
     * Calls the addField(FormField formfield) method with a formfield object created from the parameters
     *
     * @param validatorReferenceName - The reference name for the validation
     * @param uiField - the ui field the validation is done on
     * @param config - Validation configuration settings
     * @param errorMessage - The error message to be displayed
     * @param errorStyleName - the error style type
     */
    public void addField(String validatorReferenceName, Widget uiField, HashMap<String, ?> config, String errorMessage, String errorStyleName) {
        FormField formField = new FormField();
        formField.validatorReferenceName = validatorReferenceName;
        formField.uiField = uiField;
        formField.config = config;
        formField.errorMessage = errorMessage;
        formField.errorStyleName = errorStyleName;
        this.addField(formField);
    }

    /**
     * Calls the addField(FormField formfield) method with a formfield object created from the parameters
     *
     * @param validatorReferenceName - The reference name for the validation
     * @param uiField - the ui field the validation is done on
     * @param config - Validation configuration settings
     * @param errorMessage - The error message to be displayed
     */
    public void addField(String validatorReferenceName, Widget uiField, HashMap<String, ?> config, String errorMessage) {
        this.addField(validatorReferenceName, uiField, config, errorMessage, null);
    }

    /**
     * Calls the addField(FormField formfield) method with a formfield object created from the parameters
     *
     * @param validatorReferenceName - The reference name for the validation
     * @param uiField - the ui field the validation is done on
     * @param config - Validation configuration settings
     */
    public void addField(String validatorReferenceName, Widget uiField, HashMap<String, ?> config) {
        this.addField(validatorReferenceName, uiField, config, null, null);
    }

    /**
     * Adds a field to the field array
     *
     * @param formfield - a formfield object used
     */
    public void addField(FormField formfield) {
        fields.add(formfield);
    }

    /**
     * Does the validation and sets styles accordingly
     *
     * @return String - The error string or null if the validation passes
     */
    public String doValidation() {
        //loops through the arraylist and remove all error styles if it is specified.
        int fieldSize = fields.size();
        for (int i = 0; i < fieldSize; i++) {
            if (fields.get(i).errorStyleName != null) {
                fields.get(i).uiField.removeStyleName(fields.get(i).errorStyleName.toString());
            }
        }
        //loops through the arraylist and retrieve their values from within the widget
        for (int i = 0; i < fieldSize; i++) {
            //gets the instance of the validator
            ValidatorInterface<?> validator = validatorFactory(fields.get(i).validatorReferenceName);
            //set the config hashmap on the validator
            validator.setConfiguration(fields.get(i).config);
            //check if the value is valid
            boolean valid = false;
            try {
                Class<?> type = ((AdvancedInputField)fields.get(i).uiField).getReturnType();
                if (String.class.equals(type)) {
                    valid = ((ValidatorInterface<String>)validator).isValid(((HasValue<String>)fields.get(i).uiField).getValue().trim());
                } else if (SSDate.class.equals(type)) {
                    valid = ((ValidatorInterface<SSDate>)validator).isValid(((HasValue<SSDate>)fields.get(i).uiField).getValue());
                }
            } catch (Exception e) {
                valid = ((ValidatorInterface<String>)validator).isValid(((HasValue<String>)fields.get(i).uiField).getValue());
            }
            if (!valid) {
                fields.get(i).uiField.getElement().scrollIntoView();
                //add error style
                if (fields.get(i).errorStyleName != null) {
                    fields.get(i).uiField.addStyleName(fields.get(i).errorStyleName.toString());
                }
                //return error (return generic if no key found)
                if (fields.get(i).errorMessage != null) {
                    return fields.get(i).errorMessage;
                } else {
                    return validator.getDefaultValidationMessage();
                }
            }
        }
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
        } else if (validatorReferenceName.equals(FormFieldConstants.VALIDATE_REGEX_REFERENCE)) {
            formValidationInstance = new StringRegexValidator();
        } else if (validatorReferenceName.equals(FormFieldConstants.VALIDATE_AGE_REFERENCE)) {
            formValidationInstance = new AgeValidator();
        } else if (validatorReferenceName.equals(FormFieldConstants.VALIDATE_MINMAX_REFERENCE)) {
            formValidationInstance = new RangeValidator();
        }

        //add validation class instance to array
        validatorInstances.put(validatorReferenceName, formValidationInstance);

        //return the validation class instance
        return formValidationInstance;
    }

}
