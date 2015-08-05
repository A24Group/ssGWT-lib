/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.ssgwt.client.ui.form.spinner;

import org.ssgwt.client.ui.datagrid.SSDataGrid.Resources;
import org.ssgwt.client.ui.form.spinner.Spinner.SpinnerResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A {@link ValueSpinner} is a combination of a {@link TextBox} and a
 * {@link Spinner} to allow spinning <h3>CSS Style Rules</h3>
 * <ul class='css'>
 * <li>.gwt-ValueSpinner { primary style }</li>
 * <li>.gwt-ValueSpinner .textBox { the textbox }</li>
 * <li>.gwt-ValueSpinner .arrows { the spinner arrows }</li>
 * </ul>
 */
public class ValueSpinner extends FlowPanel implements HasValue<Long>{

    /**
     * A ClientBundle that provides images for this widget.
     */
    public interface ValueSpinnerResources extends ClientBundle {

        public static final Resources INSTANCE = GWT.create(Resources.class);

        /**
         * The styles used in this widget.
         */
        @Source("ValueSpinner.css")
        Style valueSpinnerStyle();
    }

    public interface Style extends CssResource {
    }

    private static ValueSpinnerResources defaultResources;

    private static final String STYLENAME_DEFAULT = "gwt-ValueSpinner";

    private Spinner spinner;
    private final TextBox valueBox = new TextBox();
    
    /**
     * This stores the default multiplier used by the value spinner for calculations
     */
    public long multiplier = 1;
    
    /**
     * This will store the old value on the spinner
     */
    private long oldValue;

    private final SpinnerListener spinnerListener = new SpinnerListener() {
        @Override
        public void onSpinning(long value) {

            if (getSpinner() != null) {
                getSpinner().setValue(value, false);
            }
            String updateValue = formatValue(value);
            // Set the new value for the valueBox
            valueBox.setText(updateValue);
            if (updateValue != null && !updateValue.equals("")) {
                // Replace old value with a new one
                oldValue = Long.parseLong(updateValue);
            }
        }
    };

    /**
     * This is a blur handler for the numeric field
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  03 August 2015
     */
    private final BlurHandler blurHandler = new BlurHandler() {

        /**
         * This method will fire as soon as the spinner loses focus
         * 
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  27 July 2015
         * 
         * @param event - The blur event for the focus lost
         */
        @Override
        public void onBlur(BlurEvent event) {
            // Get the value from the text box
            String value = valueBox.getValue();
            if (!value.equals(formatValue(getSpinner().getValue()))){
                try {
                    // Convert text to number
                    long numericNew = Long.parseLong(value);
                    
                    // Calculate the difference between the typed value and the old value
                    long difference = numericNew - oldValue;
                    long timeDiff = difference * multiplier;
                    long newValue = spinner.getValue() + timeDiff;
                    
                    // Get a formatted version of the value typed in
                    long formattedNewValue = Long.parseLong(formatValue(newValue));
                    // Compare the limited and non limited values
                    if (numericNew != formattedNewValue) {
                        // Shift the time
                        long shifting = (numericNew - Long.parseLong(formatValue(newValue)));
                        // minus one to conform to 0 start
                        shifting -= 1;
                        difference = shifting - oldValue;
                        timeDiff = difference * multiplier;
                        // Recalculate the value
                        newValue = spinner.getValue() + timeDiff;
                    }
                    if (getSpinner().isConstrained()) {
                        // Get spinner constraints
                        long maxValue = getSpinner().getMax();
                        long minValue = getSpinner().getMin();
                        // Check that the spinner conforms to the constraints
                        if (newValue < minValue) {
                            newValue = minValue;
                        }
                        if (newValue > maxValue) {
                            newValue = maxValue;
                        }
                    }
    
                    // Set the spinner value
                    if (getSpinner() != null) {
                        getSpinner().setValue(newValue, false);
                    }
                    String updateValue = formatValue(newValue);
                    //Perform normal update
                    valueBox.setText(updateValue);
                    if (updateValue != null && !updateValue.equals("")) {
                        oldValue = Long.parseLong(updateValue);
                    }
                    // Fire update so that recalculations can happen
                    getSpinner().fireOnValueChanged();
                } catch (Exception e) {
                    // If any exception happens, we revert back to the original value that was typed in
                    valueBox.setText(formatValue(getSpinner().getValue()));
                }
            }
        }
    };
    
    /**
     * Set the max value
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param max - The new max value
     */
    public void setMaxValue(long max) {
        this.spinner.setMax(max);
    }

    /**
     * Set the min value
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param min - The new min value
     */
    public void setMinValue(long min) {
        this.spinner.setMin(min);
    }

    /**
     * @param value
     *            initial value
     */
    public ValueSpinner(long value) {
        this(value, 0, 0, 1, 99, false);
    }

    /**
     * @param value
     *            initial value
     * @param styles
     *            the styles and images used by this widget
     * @param images
     *            the images used by the spinner
     */
    public ValueSpinner(long value, ValueSpinnerResources styles,
            SpinnerResources images) {
        this(value, 0, 0, 1, 99, false, styles, images);
    }

    /**
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     */
    public ValueSpinner(long value, int min, int max) {
        this(value, min, max, 1, 99, true);
    }

    /**
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     * @param minStep
     *            min value for stepping
     * @param maxStep
     *            max value for stepping
     */
    public ValueSpinner(long value, int min, int max, int minStep, int maxStep) {
        this(value, min, max, minStep, maxStep, true);
    }

    /**
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     * @param minStep
     *            min value for stepping
     * @param maxStep
     *            max value for stepping
     * @param constrained
     *            if set to false min and max value will not have any effect
     */
    public ValueSpinner(long value, int min, int max, int minStep, int maxStep,
            boolean constrained) {
        this(value, min, max, minStep, maxStep, constrained, null);
    }

    /**
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     * @param minStep
     *            min value for stepping
     * @param maxStep
     *            max value for stepping
     * @param constrained
     *            if set to false min and max value will not have any effect
     * @param resources
     *            the styles and images used by this widget
     */
    public ValueSpinner(long value, int min, int max, int minStep, int maxStep,
            boolean constrained, ValueSpinnerResources resources) {
        this(value, min, max, minStep, maxStep, constrained, resources, null);
    }

    /**
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     * @param minStep
     *            min value for stepping
     * @param maxStep
     *            max value for stepping
     * @param constrained
     *            if set to false min and max value will not have any effect
     * @param resources
     *            the styles and images used by this widget
     * @param images
     *            the images used by the spinner
     */
    public ValueSpinner(long value, long min, long max, int minStep,
            int maxStep, boolean constrained, ValueSpinnerResources resources,
            SpinnerResources images) {
        super();
        setStylePrimaryName(STYLENAME_DEFAULT);
        if (images == null) {
            spinner = new Spinner(spinnerListener, value, min, max, minStep,
                    maxStep, constrained);
        } else {
            spinner = new Spinner(spinnerListener, value, min, max, minStep,
                    maxStep, constrained, images);
        }
        setValue(spinner.getValue());
        valueBox.setStyleName("textBox");
        valueBox.addBlurHandler(blurHandler);
        add(valueBox);
        FlowPanel arrowsPanel = new FlowPanel();
        arrowsPanel.setStylePrimaryName("arrows");
        arrowsPanel.add(spinner.getIncrementArrow());
        arrowsPanel.add(spinner.getDecrementArrow());
        add(arrowsPanel);
    }

    /**
     * @return the Spinner used by this widget
     */
    public Spinner getSpinner() {
        return spinner;
    }

    /**
     * @return the SpinnerListener used to listen to the {@link Spinner} events
     */
    public SpinnerListener getSpinnerListener() {
        return spinnerListener;
    }

    /**
     * @return the TextBox used by this widget
     */
    public TextBox getTextBox() {
        return valueBox;
    }

    /**
     * @return whether this widget is enabled.
     */
    public boolean isEnabled() {
        return spinner.isEnabled();
    }

    /**
     * Sets whether this widget is enabled.
     *
     * @param enabled
     *            true to enable the widget, false to disable it
     */
    public void setEnabled(boolean enabled) {
        spinner.setEnabled(enabled);
        valueBox.setEnabled(enabled);
    }

    /**
     * @param value
     *            the value to format
     * @return the formatted value
     */
    protected String formatValue(long value) {
        return String.valueOf(value);
    }

    /**
     * @param value - the value to parse
     *
     * @return the parsed value
     */
    protected long parseValue(String value) {
        return Long.valueOf(value);
    }

    /**
     * Add the valueChangeHandler to the value spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param handler - The handler to add
     *
     * @return HandlerRegistration
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Long> handler) {
         return addHandler(handler, ValueChangeEvent.getType());
    }

    /**
     * Get the value of the value spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @return the value on the spinner
     */
    @Override
    public Long getValue() {
        return spinner.getValue();
    }

    /**
     * Set the value of the spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param value - The value to set
     */
    @Override
    public void setValue(Long value) {
        spinner.setValue(value, true);
    }

    /**
     * Set the value of the spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param value - The value to set
     * @param fireEvent - fires value changed event if set to true
     */
    @Override
    public void setValue(Long value, boolean fireEvents) {
        spinner.setValue(value, fireEvents);
    }

}