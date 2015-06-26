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
 * A spinner used for selecting a duration in minutes with a hour and minutes spinner
 *
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 15 June 2015
 */
public class DurationSpinner extends FlowPanel implements HasValue<Integer>{

    /**
     * The style used for the spinner text box
     */
    protected final String TEXTBOX_STYLE = "textBox";

    /**
     * The style used for the arrows
     */
    protected final String ARROW_STYLE = "arrows";
    
    /**
     * The default resource used
     */
    private static ValueSpinnerResources defaultResources;

    /**
     * Default style name used
     */
    private static final String STYLENAME_DEFAULT = "gwt-ValueSpinner";

    /**
     * The spinner that contains the arrows for hours
     */
    private Spinner hourSpinner;

    /**
     * The text box that will display the value of the hours
     */
    private final TextBox hourValueBox = new TextBox();

    /**
     * The flow panel that contains the spinner and text box for hours
     */
    private final FlowPanel hourSpinnerContainer = new FlowPanel();

    /**
     * The label that is displayed next to the text box for hours
     */
    private final Label hourValueBoxLabel;
    
    /**
     * The spinner that contains the arrows for the minutes
     */
    private Spinner minuteSpinner;

    /**
     * The text box that will display the value for the minutes
     */
    private final TextBox minuteValueBox = new TextBox();

    /**
     * The flow panel that contains the spinner and text box for the minutes
     */
    private final FlowPanel minuteSpinnerContainer = new FlowPanel();

    /**
     * The label that is displayed next to the text box for the minutes
     */
    private final Label minuteValueBoxLabel;
    
    /**
     * The minimum hours that is allowed
     */
    private int spinnerHourMinimum;

    /**
     * The maximum hours that is allowed
     */
    private int spinnerHourMaximum;

    /**
     * The minimum minutes that is allowed
     */
    private int spinnerMinuteMinimum;

    /**
     * The maximum minutes that is allowed
     */
    private int spinnerMinuteMaximum;

    /**
     * A ClientBundle that provides images for this widget.
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
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
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public interface Style extends CssResource {
    }

    /**
     * Spinner listener that's used to update all the values if the hour spinner change
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    private final SpinnerListener hourSpinnerListener = new SpinnerListener() {

        /**
         * On the value change of the hour spinner
         *
         * @author Ruan Naude <ruan.naude@a24group.com>
         * @since 15 June 2015
         *
         * @param value - The new value to set on the spinner
         */
        @Override
        public void onSpinning(long value) {
            if (getHourSpinner() != null) {
                getHourSpinner().setValue(value, false);
            }
            hourValueBox.setText(String.valueOf(value));
            
            try {
                int minutes = Integer.valueOf(minuteValueBox.getValue());
                setMinutesValue(minutes);
            } catch (NumberFormatException e) {
                //ignore this, means the field does not yet have value
            }
        }
    };
    
    /**
     * Spinner listener that's used to update all the values if the minute spinner change
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    private final SpinnerListener minuteSpinnerListener = new SpinnerListener() {

        /**
         * On the value change of the minutes spinner
         *
         * @author Ruan Naude <ruan.naude@a24group.com>
         * @since 15 June 2015
         *
         * @param value - The new value to set on the spinner
         */
        @Override
        public void onSpinning(long value) {
            setMinutesValue((int) value);
        }
    };

    /**
     * Class constructor
     * 
     * @param value - The initial value for the duration spinner
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinner(
        int value
    ) {
        this(value, null, null);
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
    public DurationSpinner(
        int value,
        ValueSpinnerResources resources,
        SpinnerResources images
    ) {
        this(
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
     * @param hourLabelText - The value of the text next to the hour spinner
     * @param minuteLabelText - The value of the text next to the minute spinner
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinner(
        int value,
        int minValue,
        int maxValue,
        String hourLabelText,
        String minuteLabelText
    ) {
        this(
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
     * @param hourLabelText - The value of the text next to the hour spinner
     * @param minuteLabelText - The value of the text next to the minute spinner
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinner(
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
        this(
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
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinner(
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
        this(
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
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    public DurationSpinner(
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
        this(
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
    public DurationSpinner(
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
        ValueSpinnerResources resources,
        SpinnerResources images
    ) {
        super();
        
        spinnerHourMinimum = minValue / 60;
        spinnerHourMaximum = maxValue / 60;
        spinnerMinuteMinimum = minValue - (spinnerHourMinimum * 60);
        spinnerMinuteMaximum = maxValue - (spinnerHourMaximum * 60);
        
        setStylePrimaryName(STYLENAME_DEFAULT);
        if (resources != null) {
            defaultResources = resources;
        }
        
        //divide the total minutes by 60 and cast to int hours will be whole e.g 8.6h will be 8h
        int hourValue = (int) (value / 60);
        //subtract the amount of minutes taken up by whole hours above to get remaining minutes.
        int minuteValue = (int) (value - (hourValue * 60));
        
        if (images == null) {
            hourSpinner = new Spinner(
                hourSpinnerListener,
                hourValue,
                spinnerHourMinimum,
                spinnerHourMaximum,
                hourMinStep,
                hourMaxStep,
                constrained
            );
            minuteSpinner = new Spinner(
                minuteSpinnerListener,
                minuteValue,
                0,
                59,
                minuteMinStep,
                minuteMaxStep,
                constrained
            );
        } else {
            hourSpinner = new Spinner(
                hourSpinnerListener,
                hourValue,
                spinnerHourMinimum,
                spinnerHourMaximum,
                hourMinStep,
                hourMaxStep,
                constrained,
                images
            );
            minuteSpinner = new Spinner(
                minuteSpinnerListener,
                minuteValue,
                0,
                59,
                minuteMinStep,
                minuteMaxStep,
                constrained,
                images
            );
        }
        //Setup the hour spinner
        hourValueBox.setStyleName(TEXTBOX_STYLE);
        hourSpinnerContainer.add(hourValueBox);

        FlowPanel arrowsPanel = new FlowPanel();
        arrowsPanel.setStylePrimaryName(ARROW_STYLE);
        arrowsPanel.add(hourSpinner.getIncrementArrow());
        arrowsPanel.add(hourSpinner.getDecrementArrow());
        hourSpinnerContainer.add(arrowsPanel);

        hourValueBoxLabel = new Label(hourLabelText);
        add(hourSpinnerContainer);
        add(hourValueBoxLabel);
        
        //Setup the minute spinner
        minuteValueBox.setStyleName(TEXTBOX_STYLE);
        minuteSpinnerContainer.add(minuteValueBox);

        FlowPanel minuteArrowsPanel = new FlowPanel();
        minuteArrowsPanel.setStylePrimaryName(ARROW_STYLE);
        minuteArrowsPanel.add(minuteSpinner.getIncrementArrow());
        minuteArrowsPanel.add(minuteSpinner.getDecrementArrow());
        minuteSpinnerContainer.add(minuteArrowsPanel);

        minuteValueBoxLabel = new Label(minuteLabelText);
        add(minuteSpinnerContainer);
        add(minuteValueBoxLabel);
    }
    
    /**
     * This function will set the correct value on the minutes spinner based on the
     * value of the hours and taking the max and min values into account.
     * 
     * @param newValue - The new value for the minutes spinner.
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     */
    private void setMinutesValue(int newValue) {
        try {
            int hours = Integer.valueOf(hourValueBox.getValue());
            if (hours == spinnerHourMinimum) {
                if (newValue <= spinnerMinuteMinimum) {
                    newValue = spinnerMinuteMinimum;
                }
            } else if (hours == spinnerHourMaximum) {
                if (newValue >= spinnerMinuteMaximum) {
                    newValue = spinnerMinuteMaximum;
                }
            }
        } catch (NumberFormatException e) {
            //ignore this, means the field does not yet have value
        }
        
        if (getMinuteSpinner() != null) {
            getMinuteSpinner().setValue(newValue, false);
        }
        if (newValue < 10) {
            minuteValueBox.setText("0" + newValue);
        } else {
            minuteValueBox.setText(String.valueOf(newValue));
        }
    }

    /**
     * Getter for the hour spinner component.
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return the hour Spinner used by this widget
     */
    public Spinner getHourSpinner() {
        return hourSpinner;
    }

    /**
     * Getter for the minute spinner component.
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return the minute Spinner used by this widget
     */
    public Spinner getMinuteSpinner() {
        return minuteSpinner;
    }
    
    /**
     * Getter for the hour text box label
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return the hour label used by this widget
     */
    public Label getHourTextBoxLabel() {
        return hourValueBoxLabel;
    }
    
    /**
     * Getter for the minute text box label
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return the minute label used by this widget
     */
    public Label getMinuteTextBoxLabel() {
        return minuteValueBoxLabel;
    }

    /**
     * Adds a style name to the spinners
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param style - The style to add
     */
    @Override
    public void addStyleName(String style) {
        hourSpinnerContainer.addStyleName(style);
        minuteSpinnerContainer.addStyleName(style);
    }

    /**
     * Removes a style name from the spinners
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param style - The style to remove
     */
    @Override
    public void removeStyleName(String style) {
        hourSpinnerContainer.removeStyleName(style);
        minuteSpinnerContainer.removeStyleName(style);
    }

    /**
     * Getter for the boolean whether the fields is enabled
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return whether this widget is enabled.
     */
    public boolean isEnabled() {
        return hourSpinner.isEnabled();
    }

    /**
     * Sets whether this widget is enabled.
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param enabled - true to enable the widget, false to disable it
     */
    public void setEnabled(boolean enabled) {
        hourSpinner.setEnabled(enabled);
        hourValueBox.setEnabled(enabled);
        minuteSpinner.setEnabled(enabled);
        minuteValueBox.setEnabled(enabled);
    }

    /**
     * Sets the text boxes to ready only or not.
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param enabled - true to set the text boxes to read only, false to enable it
     */
    public void setTextBoxReadOnly(boolean enabled) {
        hourValueBox.setReadOnly(enabled);
        minuteValueBox.setReadOnly(enabled);
    }

    /**
     * Add the valueChangeHandler to the value spinner
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param handler - The handler to add
     *
     * @return HandlerRegistration
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    /**
     * Get the total minutes value from the spinners
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @return The total minutes value from the spinners
     */
    @Override
    public Integer getValue() {
        try {
            int hours = Integer.valueOf(this.hourValueBox.getValue());
            int minutes = Integer.valueOf(this.minuteValueBox.getValue());
            return minutes + (hours * 60);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Set the value of the spinners
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param value - The value to set
     */
    @Override
    public void setValue(Integer value) {
      //divide the total minutes by 60 and cast to int hours will be whole e.g 8.6h will be 8h
        int hours = (int) (value / 60);
        //subtract the amount of minutes taken up by whole hours above to get remaining minutes.
        int minutes = (int) (value - (hours * 60));
        
        hourSpinner.setValue(hours, true);
        minuteSpinner.setValue(minutes, true);
    }

    /**
     * Set the value of the spinners
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 15 June 2015
     *
     * @param value - The value to set
     * @param fireEvent - fires value changed event if set to true
     */
    @Override
    public void setValue(Integer value, boolean fireEvents) {
        //divide the total minutes by 60 and cast to int hours will be whole e.g 8.6h will be 8h
        int hours = (int) (value / 60);
        //subtract the amount of minutes taken up by whole hours above to get remaining minutes.
        int minutes = (int) (value - (hours * 60));
        
        hourSpinner.setValue(hours, fireEvents);
        minuteSpinner.setValue(minutes, fireEvents);
    }
    
}
