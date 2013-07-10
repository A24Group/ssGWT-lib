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
package org.ssgwt.client.ui.form.spinner;

import org.ssgwt.client.ui.datagrid.SSDataGrid.Resources;
import org.ssgwt.client.ui.form.spinner.Spinner.SpinnerResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A spinner used for selecting hours
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  08 July 2013
 */
public class HourSpinner extends FlowPanel implements HasValue<Double>{

    /**
     * The style used for the spinner text box
     */
    protected final String TEXTBOX_STYLE = "textBox";

    /**
     * The style used for the arrows
     */
    protected final String ARROW_STYLE = "arrows";

    /**
     * A ClientBundle that provides images for this widget.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     */
    public interface ValueSpinnerResources extends ClientBundle {

        /**
         * Instance of the Resources
         */
        public static final Resources INSTANCE = GWT.create(Resources.class);

        /**
         * The styles used in this widget.
         */
        @Source("ValueSpinner.css")
        Style valueSpinnerStyle();
    }

    /**
     * Interface used for the styles
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     */
    public interface Style extends CssResource {
    }

    /**
     * The default resource used
     */
    private static ValueSpinnerResources defaultResources;

    /**
     * Default style name used
     */
    private static final String STYLENAME_DEFAULT = "gwt-ValueSpinner";

    /**
     * The spinner that contains the arrows
     */
    private Spinner spinner;

    /**
     * The text box that will display the value
     */
    private final TextBox valueBox = new TextBox();

    /**
     * The flow panel that contains the spinner and text box
     */
    private final FlowPanel spinnerContainer = new FlowPanel();

    /**
     * The label that is displayed next to the text box
     */
    private final Label valueBoxLabel;

    /**
     * Spinner listener that's used to update all the values if the spinner change
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     */
    private final SpinnerListener spinnerListener = new SpinnerListener() {

        /**
         * On the value change of the spinner
         *
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since  08 July 2013
         */
        @Override
        public void onSpinning(long value) {
            if (getSpinner() != null) {
                getSpinner().setValue(value, false);
            }
            valueBox.setText(formatValue(value));
        }
    };

    /**
     * The key press handler that handle the event of a key press.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     */
    private final KeyPressHandler keyPressHandler = new KeyPressHandler() {

        /**
         * Function that is called each time an a key is pressed
         *
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since  08 July 2013
         *
         * @param event - The KeyPressEvent fired
         */
        @Override
        public void onKeyPress(KeyPressEvent event) {
            int index = valueBox.getCursorPos();
            String previousText = valueBox.getText();
            String newText;
            if (valueBox.getSelectionLength() > 0) {
                newText = previousText.substring(
                    0,
                    valueBox.getCursorPos())
                    + event.getCharCode()
                    + previousText.substring(
                        valueBox.getCursorPos() + valueBox.getSelectionLength(),
                        previousText.length()
                    );
            } else {
                newText = previousText.substring(0, index)
                    + event.getCharCode()
                    + previousText.substring(index, previousText.length());
            }
            valueBox.cancelKey();
            try {
                long newValue = parseValue(newText);
                if (spinner.isConstrained() && (newValue > spinner.getMax() || newValue < spinner.getMin())) {
                    return;
                }
                spinner.setValue(newValue, true);
            } catch (Exception e) {
                // Die silently
            }
        }
    };

    /**
     * Set the max value
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
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
     * @since  08 July 2013
     *
     * @param min - The new min value
     */
    public void setMinValue(long min) {
        this.spinner.setMin(min);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - The initial value
     */
    public HourSpinner(long value) {
        this(value, 0, 0, 1, 99, false, null);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - The initial value
     * @param styles - The styles and images used by this widget
     * @param images - The images used by the spinner
     */
    public HourSpinner(
        long value,
        ValueSpinnerResources styles,
        SpinnerResources images
    ) {
        this(value, 0, 0, 1, 99, false, null, styles, images);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - The initial value
     * @param min - The min value
     * @param max - The max value
     */
    public HourSpinner(long value, double min, double max) {
        this(value, min, max, 1, 99, true, null);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - The initial value
     * @param min - The min value
     * @param max - The max value
     * @param minStep - The min value for stepping
     * @param maxStep - The max value for stepping
     * @param valueBoxLabelText - The text to display on the value box label
     */
    public HourSpinner(long value, double min, double max, int minStep, int maxStep, String valueBoxLabelText) {
        this(value, min, max, minStep, maxStep, true, valueBoxLabelText);
    }

    /**
     * Class constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - The initial value
     * @param min - The min value
     * @param max - The max value
     * @param minStep - The min value for stepping
     * @param maxStep - The max value for stepping
     * @param constrained - If set to false min and max value will not have any effect
     * @param valueBoxLabelText - The text to display on the value box label
     */
    public HourSpinner(
        long value,
        double min,
        double max,
        int minStep,
        int maxStep,
        boolean constrained,
        String valueBoxLabelText
    ) {
        this(value, min, max, minStep, maxStep, constrained, valueBoxLabelText, null);
    }

    /**
     * @param value - The initial value
     * @param min - The min value
     * @param max - The max value
     * @param minStep - The min value for stepping
     * @param maxStep - The max value for stepping
     * @param constrained - If set to false min and max value will not have any effect
     * @param valueBoxLabelText - The text to display on the value box label
     * @param resources - The styles and images used by this widget
     */
    public HourSpinner(
        long value,
        double min,
        double max,
        int minStep,
        int maxStep,
        boolean constrained,
        String valueBoxLabelText,
        ValueSpinnerResources resources
    ) {
        this(value, min, max, minStep, maxStep, constrained, valueBoxLabelText, resources, null);
    }

    /**
     * Class constructor
     *
     * @param value - The initial value
     * @param min - The minimum value
     * @param max - The maximum value
     * @param minStep - The minimum value for stepping
     * @param maxStep - The maximum value for stepping
     * @param constrained - If set to false min and max value will not have any effect
     * @param valueBoxLabelText - The text to display on the value box label
     * @param resources - The styles and images used by this widget
     * @param images - The images used by the spinner
     */
    public HourSpinner(
        long value,
        double min,
        double max,
        int minStep,
        int maxStep,
        boolean constrained,
        String valueBoxLabelText,
        ValueSpinnerResources resources,
        SpinnerResources images
    ) {
        super();
        setStylePrimaryName(STYLENAME_DEFAULT);
        long spinnerMin = (long) (min * 100);
        long spinnerMax = (long) (max * 100);
        if (images == null) {
            spinner = new Spinner(
                spinnerListener,
                value,
                spinnerMin,
                spinnerMax,
                minStep,
                maxStep,
                constrained
            );
        } else {
            spinner = new Spinner(
                spinnerListener,
                value,
                spinnerMin,
                spinnerMax,
                minStep,
                maxStep,
                constrained,
                images
            );
        }
        valueBox.setStyleName(TEXTBOX_STYLE);
        valueBox.addKeyPressHandler(keyPressHandler);
        spinnerContainer.add(valueBox);

        FlowPanel arrowsPanel = new FlowPanel();
        arrowsPanel.setStylePrimaryName(ARROW_STYLE);
        arrowsPanel.add(spinner.getIncrementArrow());
        arrowsPanel.add(spinner.getDecrementArrow());
        spinnerContainer.add(arrowsPanel);

        valueBoxLabel = new Label(valueBoxLabelText);
        add(spinnerContainer);
        add(valueBoxLabel);
    }

    /**
     * Getter for the spinner component.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @return the Spinner used by this widget
     */
    public Spinner getSpinner() {
        return spinner;
    }

    /**
     * Adds a style name to the spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param style - The style to add
     */
    @Override
    public void addStyleName(String style) {
        spinnerContainer.addStyleName(style);
    }

    /**
     * Removes a style name from the spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param style - The style to remove
     */
    @Override
    public void removeStyleName(String style) {
        spinnerContainer.removeStyleName(style);
    }

    /**
     * This function format the function into its correct display format
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - the value to format
     *
     * @return the formatted value
     */
    protected String formatValue(long value) {
        double doubleValue = Double.valueOf(value);
        doubleValue = doubleValue / 100;
        return String.valueOf(doubleValue);
    }

    /**
     * This function takes the value in the text box and parse it back to a long.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - the value to parse
     *
     * @return the parsed value.
     */
    protected long parseValue(String value) {
        double doubleValue = Double.parseDouble(value);
        doubleValue = doubleValue * 100;
        return Long.valueOf(String.valueOf(doubleValue));
    }

    /**
     * Getter for the SpinnerListener
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @return the SpinnerListener used to listen to the {@link Spinner} events
     */
    public SpinnerListener getSpinnerListener() {
        return spinnerListener;
    }

    /**
     * Getter for the text box
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @return the TextBox used by this widget
     */
    public TextBox getTextBox() {
        return valueBox;
    }

    /**
     * Getter for the text box label
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @return the label used by this widget
     */
    public Label getTextBoxLable() {
        return valueBoxLabel;
    }

    /**
     * Getter for the boolean whether the field is enabled
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @return whether this widget is enabled.
     */
    public boolean isEnabled() {
        return spinner.isEnabled();
    }

    /**
     * Sets whether this widget is enabled.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param enabled - true to enable the widget, false to disable it
     */
    public void setEnabled(boolean enabled) {
        spinner.setEnabled(enabled);
        valueBox.setEnabled(enabled);
    }

    /**
     * Sets whether this widget is enabled.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param enabled - true to enable the widget, false to disable it
     */
    public void setTextBoxReadOnly(boolean enabled) {
        valueBox.setReadOnly(enabled);
    }

    /**
     * Add the valueChangeHandler to the value spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param handler - The handler to add
     *
     * @return HandlerRegistration
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Double> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    /**
     * Get the value of the value spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @return the value on the spinner
     */
    @Override
    public Double getValue() {
        try {
            return Double.valueOf(this.valueBox.getValue());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Set the value of the spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - The value to set
     */
    @Override
    public void setValue(Double value) {
        spinner.setValue((long) (value * 100), true);
    }

    /**
     * Set the value of the spinner
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  08 July 2013
     *
     * @param value - The value to set
     * @param fireEvent - fires value changed event if set to true
     */
    @Override
    public void setValue(Double value, boolean fireEvents) {
        spinner.setValue((long) (value * 100), fireEvents);
    }

}
