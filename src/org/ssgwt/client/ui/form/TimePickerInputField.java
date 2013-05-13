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
package org.ssgwt.client.ui.form;

import java.util.Date;

import org.ssgwt.client.ui.form.spinner.Spinner.SpinnerResources;
import org.ssgwt.client.ui.form.spinner.TimePicker;
import org.ssgwt.client.ui.form.spinner.ValueSpinner.ValueSpinnerResources;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * A TimePicker input field for the DynamicForm
 *
 * @author Alec Erasmus<alec.erasmus@a24group.com>
 * @since 13 May 2013
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public abstract class TimePickerInputField<T> extends TimePicker implements HasValue<Date>, InputField<T, Date> {

    /**
     * The value from the object that should displayed on the input field
     */
    private boolean required = false;

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param date - the date providing the initial time to display
     * @param amPmFormat - the format to display AM/PM. Can be null to hide AM/PM field
     * @param hoursFormat - the format to display the hours. Can be null to hide hours field
     * @param minutesFormat - the format to display the minutes. Can be null to hide minutes field
     * @param secondsFormat - the format to display the seconds. Can be null to seconds field
     * @param styles - styles to be used by this TimePicker instance
     * @param images - images to be used by all nested Spinner widgets
     */
    public TimePickerInputField(
        Date date,
        DateTimeFormat amPmFormat,
        DateTimeFormat hoursFormat,
        DateTimeFormat minutesFormat,
        DateTimeFormat secondsFormat,
        ValueSpinnerResources styles,
        SpinnerResources images
    ) {
        super(date, amPmFormat, hoursFormat, minutesFormat, secondsFormat, styles, images);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param date the date providing the initial time to display
     * @param use24Hours if set to true the {@link TimePicker} will use 24h format
     */
    public TimePickerInputField(Date date, boolean use24Hours) {
        super(date, use24Hours);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param date - the date providing the initial time to display
     * @param amPmFormat - the format to display AM/PM. Can be null to hide AM/PM field
     * @param hoursFormat - the format to display the hours. Can be null to hide hours field
     * @param minutesFormat - the format to display the minutes. Can be null to hide minutes field
     * @param secondsFormat - the format to display the seconds. Can be null to seconds field
     */
    public TimePickerInputField(
        Date date,
        DateTimeFormat amPmFormat,
        DateTimeFormat hoursFormat,
        DateTimeFormat minutesFormat,
        DateTimeFormat secondsFormat
    ) {
        super(date, amPmFormat, hoursFormat, minutesFormat, secondsFormat);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param use24Hours - if set to true the {@link TimePicker} will use 24h format
     */
    public TimePickerInputField(boolean use24Hours) {
        super(use24Hours);
    }

    /**
     * Add a Handler on the change of the value
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param handler - The value change handler
     *
     * @return null
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return null;
    }

    /**
     * Gets this object's value.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @return the object's value
     */
    @Override
    public Date getValue() {
        return super.getDateTime();
    }

    /**
     * Sets this object's value. Fires
     * {@link com.google.gwt.event.logical.shared.ValueChangeEvent} when
     * fireEvents is true and the new value does not equal the existing value.
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value the object's new value
     */
    @Override
    public void setValue(Date value) {
        super.setDate(value);
    }

    /**
     * Sets this object's value. Fires
     * {@link com.google.gwt.event.logical.shared.ValueChangeEvent} when
     * fireEvents is true and the new value does not equal the existing value.
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value the object's new value
     * @param fireEvents fire events if true and value is new
     */
    @Override
    public void setValue(Date value, boolean fireEvents) {
        setValue(value);
    }

    /**
     * Retrieve the class type the input field returns
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
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
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param object - The object the value should be retrieved from
     *
     * @return The value that should be displayed on the field
     */
    @Override
    public abstract Date getValue(T object);

    /**
     * Sets the value from the input field on the object
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, Date value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
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
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
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
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }

    /**
     * Set as readOnly
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setEnabled(!readOnly);
    }

    /**
     * Retrieve the flag that indicates whether the field is read only
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  13 May 2013
     *
     * @return The flag that indicates whether the field is read only
     */
    @Override
    public boolean isReadOnly() {
        return !super.isEnabled();
    }
}
