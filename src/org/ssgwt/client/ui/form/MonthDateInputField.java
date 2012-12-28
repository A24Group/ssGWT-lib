package org.ssgwt.client.ui.form;

import java.util.Date;

import org.ssgwt.client.ui.datepicker.DateBox;
import org.ssgwt.client.ui.datepicker.MonthDateBox;

import com.google.gwt.user.client.ui.Widget;

public abstract class MonthDateInputField <T> extends MonthDateBox implements InputField<T, Date>{

	/**
     * The value from the object that should the displayed on the input field
     */
    private boolean required = false;
	
    /**
     * Retrieve the class type the input field returns
     * 
     * @return The class type the input field returns
     */
    @Override
    public Class<Date> getReturnType() {
        return Date.class;
    }

	@Override
	public abstract Date getValue(T object);

	@Override
	public abstract void setValue(T object, Date value);

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
    public Widget getInputFieldWidget() {
        return this;
    }

	@Override
	public void setReadOnly(boolean readOnly) {
	    super.getTextBox().setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() {
	    return super.getTextBox().isReadOnly();
	}
}
