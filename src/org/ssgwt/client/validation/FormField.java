package org.ssgwt.client.validation;

import java.util.HashMap;
import com.google.gwt.user.client.ui.Widget;

/**
 * Stores the form field values to be used 
 * to do validation on required fields
 * 
 * @author Michael Barnard
 * @since 22 June 2012
 */
public class FormField {
    
    /**
     * The reference name for the validation
     */
    public String validatorReferenceName;
    
    /**
     * The ui field the validation is done on 
     */
    public Widget uiField;
    
    /**
     * Validation configuration settings
     */
    public HashMap<String, String> config;
    
    /**
     * The locale key for the message
     */
    public String messageLocalKey;
    
    /**
     * The error style type
     */
    public String errorStyleName;
}