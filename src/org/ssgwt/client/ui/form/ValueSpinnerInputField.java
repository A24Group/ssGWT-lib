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

import org.ssgwt.client.ui.form.spinner.Spinner.SpinnerResources;
import org.ssgwt.client.ui.form.spinner.ValueSpinner;

import com.google.gwt.user.client.ui.Widget;

/**
 * A ValueSpinner input field for the DynamicForm
 *
 * @author Alec Erasmus<alec.erasmus@a24group.com>
 * @since 13 May 2013
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public abstract class ValueSpinnerInputField<T> extends ValueSpinner implements InputField<T, Long> {

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
     * @param value - initial value
     */
    public ValueSpinnerInputField(long value) {
        super(value);
        System.out.println("asdf");
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value initial value
     * @param styles the styles and images used by this widget
     * @param images the images used by the spinner
     */
    public ValueSpinnerInputField(
        long value,
        ValueSpinnerResources styles,
        SpinnerResources images
    ) {
        super(value, styles, images);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value - initial value
     * @param min - min value
     * @param max - max value
     */
    public ValueSpinnerInputField(long value, int min, int max) {
        super(value, min, max);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value - initial value
     * @param min - min value
     * @param max - max value
     * @param minStep - min value for stepping
     * @param maxStep - max value for stepping
     */
    public ValueSpinnerInputField(long value, int min, int max, int minStep, int maxStep) {
        super(value, min, max, minStep, maxStep);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value - initial value
     * @param min - min value
     * @param max - max value
     * @param minStep - min value for stepping
     * @param maxStep - max value for stepping
     * @param constrained - if set to false min and max value will not have any effect
     */
    public ValueSpinnerInputField(long value, int min, int max, int minStep, int maxStep, boolean constrained) {
        super(value, min, max, minStep, maxStep, constrained);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value - initial value
     * @param min - min value
     * @param max - max value
     * @param minStep - min value for stepping
     * @param maxStep - max value for stepping
     * @param constrained - if set to false min and max value will not have any effect
     * @param resources - the styles and images used by this widget
     */
    public ValueSpinnerInputField(
        long value,
        int min,
        int max,
        int minStep,
        int maxStep,
        boolean constrained,
        ValueSpinnerResources resources
    ) {
        super(value, min, max, minStep, maxStep, constrained, resources);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param value - initial value
     * @param min - min value
     * @param max - max value
     * @param minStep - min value for stepping
     * @param maxStep - max value for stepping
     * @param constrained - if set to false min and max value will not have any effect
     * @param resources - the styles and images used by this widget
     * @param images - the images used by the spinner
     */
    public ValueSpinnerInputField(
        long value,
        int min,
        int max,
        int minStep,
        int maxStep,
        boolean constrained,
        ValueSpinnerResources resources,
        SpinnerResources images
    ) {
        super(value, min, max, minStep, maxStep, constrained, resources, images);
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
    public Class<Long> getReturnType() {
        return Long.class;
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
    public abstract Long getValue(T object);

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
    public abstract void setValue(T object, Long value);

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
        return this.required;
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
