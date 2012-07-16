package org.ssgwt.client.ui.form;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A TextBox input field for the DynamicForm
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since 12 July 2012
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public abstract class TextInputField<T> extends TextBox implements InputField<T, String> {

    /**
     * The value from the object that should the displayed on the input field
     */
    private boolean required = false;
    
    /**
     * Class Constructor
     */
    public TextInputField() {
        super();
    }
    
    /**
     * Class Constructor
     * 
     * @param required - The value from the object that should the displayed on the input field
     */
    public TextInputField(boolean required) {
        super();
        setRequired(required);
    }
    
    /**
     * Retrieve the value from the object that should the displayed on the input field
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @return The value that should be displayed ob the field
     */
    @Override
    public abstract String getValue(T object);

    /**
     * Sets the value from the input field on the object
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, String value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * 
     * @return The flag that indicates whether the input field is required or not
     */
    @Override
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets the flag that indicates whether the input field is required or not
     * 
     * @param required - The flag that indicates whether the input field is required or not
     */
    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Retrieve the input field as a widget
     * 
     * @return The input field as a widget
     */
    @Override
    public Widget getWidget() {
        return this;
    }
    
    /**
     * Set all the field as readOnly
     * 
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
    }
    
    /**
     * Retrieve the flag that indicates whether the field is read only
     * 
     * @return The flag that indicates whether the field is read only
     */
    @Override
    public boolean isReadOnly() {
        return super.isReadOnly();
    }
}
