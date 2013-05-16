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

import org.ssgwt.client.ui.datecomponents.AbstractStartEndDateVo;
import org.ssgwt.client.ui.datecomponents.DateTimeComponent;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * Shift input is a field that can be added to the dynamic form.
 * Allow a used to select a start date, time and end date, time.
 * Display the number of hours diff between the two dates.
 *
 * Note this field return a Vo(object with the 2 dates on it)
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since 13 May 2013
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public abstract class ShiftInputField<T>
    extends
        DateTimeComponent
    implements
        HasValue<AbstractStartEndDateVo>,
        InputField<T, AbstractStartEndDateVo> {

    /**
     * The object that is used to map the start date and end date
     */
    AbstractStartEndDateVo abstractStartEndDateVo = new AbstractStartEndDateVo();

    /**
     * Flag used to indicate if the field is required
     */
    private boolean required = false;

    /**
     * Class constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @param minDate - The minimum start date
     * @param maxDate - The maximum start date
     * @param selectedDate - The default selected date
     * @param minShiftTime - The minimum shift length in milliseconds
     * @param maxShiftTime - The maximum shift length in milliseconds
     */
    public ShiftInputField(Date minDate, Date maxDate, Date selectedDate, long minShiftTime, long maxShiftTime) {
        super(minDate, maxDate, selectedDate, minShiftTime, maxShiftTime);
    }

    /**
     * Add a ValueChangeHandler to this class
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @param handler - The handler to be added
     *
     * @return null - This is not implemented for this field
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<AbstractStartEndDateVo> handler) {
        return null;
    }

    /**
     * Get the values set on in the start date and end date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @return a instance of the AbstractStartEndDateVo with the start date and end date set on it
     */
    @Override
    public AbstractStartEndDateVo getValue() {
        abstractStartEndDateVo.dtStart = this.getStartDate();
        abstractStartEndDateVo.dtEnd = this.getEndDate();
        return abstractStartEndDateVo;
    }

    /**
     * Sets this object's value without firing any events. This should be
     * identical to calling setValue(value, false).
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     * <p>
     * Widgets must accept null as a valid value. By convention, setting a widget to
     * null clears value, calling getValue() on a cleared widget returns null. Widgets
     * that can not be cleared (e.g. {@link CheckBox}) must find another valid meaning
     * for null input.
     *
     * @param value the object's new value
     */
    @Override
    public void setValue(AbstractStartEndDateVo value) {
        this.setStartDate(value.dtStart);
        this.setEndDate(value.dtEnd);
    }

    /**
     * Sets this object's value. Fires
     * {@link com.google.gwt.event.logical.shared.ValueChangeEvent} when
     * fireEvents is true and the new value does not equal the existing value.
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     *
     * @param value the object's new value
     * @param fireEvents fire events if true and value is new
     */
    @Override
    public void setValue(AbstractStartEndDateVo value, boolean fireEvents) {
        this.setStartDate(value.dtStart);
        this.setEndDate(value.dtEnd);
    }

    /**
     * Retrieve the class type the input field returns
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  16 May 2013
     *
     * @return The class type the input field returns
     */
    @Override
    public Class<AbstractStartEndDateVo> getReturnType() {
        return AbstractStartEndDateVo.class;
    }

    /**
     * Retrieve the value from the object that should be displayed on the input field
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  16 May 2013
     *
     * @param object - The object the value should be retrieved from
     *
     * @return The value that should be displayed on the field
     */
    @Override
    public abstract AbstractStartEndDateVo getValue(T object);

    /**
     * Sets the value from the input field on the object
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  16 May 2013
     *
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, AbstractStartEndDateVo value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * Cannot be applied to this field
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  16 May 2013
     *
     * @return The flag that indicates whether the input field is required or not
     */
    @Override
    public boolean isRequired() {
        return this.required;
    }

    /**
     * Sets the flag that indicates whether the input field is required or not
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  16 May 2013
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
     * @since  16 May 2013
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
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  16 May 2013
     *
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setEnabled(!readOnly);
    }

    /**
     * Retrieve the flag that indicates whether the field is read only/enabled.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  16 May 2013
     *
     * @return The flag that indicates whether the field is read only/enabeld
     */
    @Override
    public boolean isReadOnly() {
        return super.isEnabled();
    }

}
