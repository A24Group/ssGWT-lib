package org.ssgwt.client.ui;

/**
 * This interface is used to identify class types
 * 
 * @author Michael Barnard<michael.barnard@a24group.com>
 * @since 20 July 2012
 *
 * @param FieldValueType used to indicate the type of the field
 */
public interface AdvancedInputField<FieldValueType> {
    
    /**
     * Retrieve the class type the input field returns
     * 
     * @return The class type the input field returns
     */
    public Class<FieldValueType> getReturnType();
    
}
