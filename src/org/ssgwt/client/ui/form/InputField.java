package org.ssgwt.client.ui.form;

import com.google.gwt.user.client.ui.Widget;

/**
 * The interface that should be extended by all input field that will be used on the DynamicForm
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since 12 July 2012
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 * @param <FieldValueType> The object type the field will return
 */
public interface InputField<T, FieldValueType> {
    
    /**
     * Retrieve the value from the object that should the displayed on the input field
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @return The value that should be displayed ob the field
     */
    public FieldValueType getValue(T object);
    
    /**
     * Sets the value from the input field on the object
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    public void setValue(T object, FieldValueType value);
    
    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * 
     * @return The flag that indicates whether the input field is required or not
     */
    public boolean isRequired();
    
    /**
     * Sets the flag that indicates whether the input field is required or not
     * 
     * @param required - The flag that indicates whether the input field is required or not
     */
    public void setRequired(boolean required);
    
    /**
     * Retrieve the input field as a widget
     * 
     * @return The input field as a widget
     */
    public Widget getWidget();
    
    /**
     * Set all the field as readOnly
     * 
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    public void setReadOnly(boolean readOnly);
    
    /**
     * Retrieve the flag that indicates whether the field is read only
     * 
     * @return The flag that indicates whether the field is read only
     */
    public boolean isReadOnly();
}
