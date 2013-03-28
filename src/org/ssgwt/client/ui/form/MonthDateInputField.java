package org.ssgwt.client.ui.form;

import java.util.Date;

import org.ssgwt.client.ui.datepicker.MonthDateBox;

import com.google.gwt.user.client.ui.Widget;

/**
 * A MonthDateInputField input field for the DynamicForm
 * 
 * @author Ruan Naude<ruan.naude@gmail.com>
 * @since 28 Dec 2012
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public abstract class MonthDateInputField <T> extends MonthDateBox implements InputField<T, Date>{

    /**
     * Whether the field is rewuired or not
     */
    private boolean required = false;

    /**
     * Retrieve the class type the input field returns
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
     * 
     * @return The class type the input field returns
     */
    @Override
    public Class<Date> getReturnType() {
        return Date.class;
    }

    /**
     * Retrieve the value from the object that should the displayed on the input field
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
     * 
     * @return The value that should be displayed ob the field
     */
    @Override
    public abstract Date getValue(T object);

    /**
     * Sets the value from the input field on the object
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
     */
    @Override
    public abstract void setValue(T object, Date value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
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
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
     */
    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Retrieve the input field as a widget
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
     * 
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }

    /**
     * Set the field as readOnly
     * 
     * @param readOnly - Flag to indicate whether the field should be read only
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.getTextBox().setReadOnly(readOnly);
    }
    
    /**
     * Retrieve the flag that indicates whether the field is read only
     * 
     * @author Ruan Naude<ruan.naude@gmail.com>
     * @since 28 Dec 2012
     * 
     * @return The flag that indicates whether the field is read only
     */
    @Override
    public boolean isReadOnly() {
        return super.getTextBox().isReadOnly();
    }
    
    /**
     * Enable or disable the text box used and clear
     * the month text box.
     * 
     * @author Ryno Hartzer <ryno.hartzer@a24group.com>
     * @since  28 December 2012
     */
    public void setEnabledAndClearTextBox(boolean enabled) {
        super.getTextBox().setEnabled(enabled);
        if (!enabled) {
            super.getTextBox().setText("");
        }
    }
}
