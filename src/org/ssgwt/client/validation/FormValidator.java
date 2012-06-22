package org.ssgwt.client.validation;

import java.util.ArrayList;
import java.util.HashMap;
import org.ssgwt.client.validation.validators.AbstractValidator;
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
     * Returns the correct instance of required validator
     * 
     * @param validatorReferenceName - the reference name to use in the factory
     * @return AbstractValidator - an instance of the AbstractValidator
     */
    private AbstractValidator validatorFactory(String validatorReferenceName) {
        //if refname in array
        //    return corresponding instance
        //else
        //create instance add to array
        //return
        return null;
    } 

}