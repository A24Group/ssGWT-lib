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

import org.ssgwt.client.ui.form.spinner.DurationSpinner;
import org.ssgwt.client.ui.form.spinner.Spinner.SpinnerResources;

import com.google.gwt.user.client.ui.Widget;

/**
 * A duration spinner input field for the DynamicForm
 *
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 15 June 2015
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public abstract class DurationSpinnerInputField<T> extends DurationSpinner implements InputField<T, Integer> {

    /**
     * Whether this field is required or not
     */
    private boolean required = false;

    /**
     * Class constructor
     * 
     * @param value - The initial value for the duration spinner
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinnerInputField(
        int value
    ) {
        super(value, null, null);
    }

    /**
     * Class constructor
     * 
     * @param value - The initial value for the duration spinner
     * @param resources - The styles and images used by this widget
     * @param images - The images used by the spinners
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinnerInputField(
        int value,
        ValueSpinnerResources resources,
        SpinnerResources images
    ) {
        super(
            value,
            0,
            1440,
            1,
            99,
            1,
            99,
            false,
            null,
            null,
            resources,
            images
        );
    }

    /**
     * Class constructor
     * 
     * @param value - The initial value for the duration spinner
     * @param minValue - The minimum value the duration is allowed to be
     * @param maxValue - The maximum value the duration is allowed to be
     * @param hourMinStep - The minimum value the hour is allowed to be stepped through
     * @param hourMaxStep - The maximum value the hour is allowed to be stepped through
     * @param minuteMinStep - The minimum value the minute is allowed to be stepped through
     * @param minuteMaxStep - The maximum value the minute is allowed to be stepped through
     * @param constrained - If set to false minimum and maximum value for will not have any effect on spinners
     * @param hourLabelText - The value of the text next to the hour spinner
     * @param minuteLabelText - The value of the text next to the minute spinner
     * @param resources - The styles and images used by this widget
     * @param images - The images used by the spinners
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinnerInputField(
        int value,
        int minValue,
        int maxValue,
        String hourLabelText,
        String minuteLabelText
    ) {
        super(
            value,
            minValue,
            maxValue,
            1,
            99,
            1,
            99,
            hourLabelText,
            minuteLabelText
        );
    }

    /**
     * Class constructor
     * 
     * @param value - The initial value for the duration spinner
     * @param minValue - The minimum value the duration is allowed to be
     * @param maxValue - The maximum value the duration is allowed to be
     * @param hourMinStep - The minimum value the hour is allowed to be stepped through
     * @param hourMaxStep - The maximum value the hour is allowed to be stepped through
     * @param minuteMinStep - The minimum value the minute is allowed to be stepped through
     * @param minuteMaxStep - The maximum value the minute is allowed to be stepped through
     * @param constrained - If set to false minimum and maximum value for will not have any effect on spinners
     * @param hourLabelText - The value of the text next to the hour spinner
     * @param minuteLabelText - The value of the text next to the minute spinner
     * @param resources - The styles and images used by this widget
     * @param images - The images used by the spinners
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinnerInputField(
        int value,
        int minValue,
        int maxValue,
        int hourMinStep,
        int hourMaxStep,
        int minuteMinStep,
        int minuteMaxStep,
        String hourLabelText,
        String minuteLabelText
    ) {
        super(
            value,
            minValue,
            maxValue,
            hourMinStep,
            hourMaxStep,
            minuteMinStep,
            minuteMaxStep,
            true,
            hourLabelText,
            minuteLabelText
        );
    }

    /**
     * Class constructor
     * 
     * @param value - The initial value for the duration spinner
     * @param minValue - The minimum value the duration is allowed to be
     * @param maxValue - The maximum value the duration is allowed to be
     * @param hourMinStep - The minimum value the hour is allowed to be stepped through
     * @param hourMaxStep - The maximum value the hour is allowed to be stepped through
     * @param minuteMinStep - The minimum value the minute is allowed to be stepped through
     * @param minuteMaxStep - The maximum value the minute is allowed to be stepped through
     * @param constrained - If set to false minimum and maximum value for will not have any effect on spinners
     * @param hourLabelText - The value of the text next to the hour spinner
     * @param minuteLabelText - The value of the text next to the minute spinner
     * @param resources - The styles and images used by this widget
     * @param images - The images used by the spinners
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinnerInputField(
        int value,
        int minValue,
        int maxValue,
        int hourMinStep,
        int hourMaxStep,
        int minuteMinStep,
        int minuteMaxStep,
        boolean constrained,
        String hourLabelText,
        String minuteLabelText
    ) {
        super(
            value,
            minValue,
            maxValue,
            hourMinStep,
            hourMaxStep,
            minuteMinStep,
            minuteMaxStep,
            constrained,
            hourLabelText,
            minuteLabelText,
            null
        );
    }

    /**
     * Class constructor
     * 
     * @param value - The initial value for the duration spinner
     * @param minValue - The minimum value the duration is allowed to be
     * @param maxValue - The maximum value the duration is allowed to be
     * @param hourMinStep - The minimum value the hour is allowed to be stepped through
     * @param hourMaxStep - The maximum value the hour is allowed to be stepped through
     * @param minuteMinStep - The minimum value the minute is allowed to be stepped through
     * @param minuteMaxStep - The maximum value the minute is allowed to be stepped through
     * @param constrained - If set to false minimum and maximum value for will not have any effect on spinners
     * @param hourLabelText - The value of the text next to the hour spinner
     * @param minuteLabelText - The value of the text next to the minute spinner
     * @param resources - The styles and images used by this widget
     * @param images - The images used by the spinners
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinnerInputField(
        int value,
        int minValue,
        int maxValue,
        int hourMinStep,
        int hourMaxStep,
        int minuteMinStep,
        int minuteMaxStep,
        boolean constrained,
        String hourLabelText,
        String minuteLabelText,
        ValueSpinnerResources resources
    ) {
        super(
            value,
            minValue,
            maxValue,
            hourMinStep,
            hourMaxStep,
            minuteMinStep,
            minuteMaxStep,
            constrained,
            hourLabelText,
            minuteLabelText,
            resources,
            null
        );
    }


    /**
     * Retrieve the class type the input field returns
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return The class type the input field returns
     */
    @Override
    public Class<Integer> getReturnType() {
        return Integer.class;
    }

    /**
     * Retrieve the value from the object that should be displayed on the input field
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param object - The object the value should be retrieved from
     *
     * @return The value that should be displayed on the field
     */
    @Override
    public abstract Integer getValue(T object);

    /**
     * Sets the value from the input field on the object
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, Integer value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
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
     * @since 08 July 2013
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
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
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
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setEnabled(!readOnly);
    }

    /**
     * Set the text box as ready only
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setTextBoxReadOnly(boolean readOnly) {
        super.setTextBoxReadOnly(readOnly);
    }

    /**
     * Retrieve the flag that indicates whether the field is read only
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return The flag that indicates whether the field is read only
     */
    @Override
    public boolean isReadOnly() {
        return !super.isEnabled();
    }
}
