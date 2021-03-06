package org.ssgwt.client.ui.datecomponents;

import org.ssgwt.client.i18n.DateTimeFormat;
import org.ssgwt.client.i18n.SSDate;
import org.ssgwt.client.ui.datepicker.DateBox;
import org.ssgwt.client.ui.datepicker.DateBox.DefaultFormat;
import org.ssgwt.client.ui.datepicker.SSDatePicker;
import org.ssgwt.client.ui.form.spinner.ChangeEvent;
import org.ssgwt.client.ui.form.spinner.ChangeHandler;
import org.ssgwt.client.ui.form.spinner.TimePicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The date time component allows you to select a start date and end date and
 * display you the diff between the two dates
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since 13 May 2013
 */
public class DateTimeComponent extends Composite {

    /**
     * Constant for the opening total time string
     */
    private static final String TOTAL_TIME_FORMAT_START = "( ";
    
    /**
     * Constant for the hour format in the total time string
     */
    private static final String TOTAL_TIME_FORMAT_HOURS = "H";
    
    /**
     * Constant for the minute format in the total time string
     */
    private static final String TOTAL_TIME_FORMAT_MINUTES = "Min";
    
    /**
     * Constant for the closing total time string
     */
    private static final String TOTAL_TIME_FORMAT_END = " )";

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * This label is used for the header
     */
    @UiField
    Label headerLabel;

    /**
     * This label is used to display the total time
     */
    @UiField
    Label totalTime;

    /**
     * This is the main panel.
     */
    @UiField
    FlowPanel mainPanel;

    /**
     * This flow panel that contains the start date fields
     */
    @UiField
    FlowPanel startDatePanel;

    /**
     * This flow panel that contains the end date fields
     */
    @UiField
    FlowPanel endDatePanel;

    /**
     * The is used for the start date field
     */
    @UiField(provided=true)
    DateBox startDateBox;

    /**
     * The is used for the end date field
     */
    @UiField(provided=true)
    DateBox endDateBox;

    /**
     * The time picker for the start time
     */
    TimePicker startTimePicker;

    /**
     * The time picker for the end time
     */
    protected TimePicker endTimePicker;

    /**
     * Panel that will contain the total time of the diff between the start date and end date
     */
    @UiField
    FlowPanel totalTimeDiffContainer;

    /**
     * The is the panel that display the left border
     */
    @UiField
    FlowPanel totalTimeLeftContainer;

    /**
     * The is the panel that display the right border
     */
    @UiField
    FlowPanel totalTimeRightContainer;

    /**
     * Panel used as spacing between the left and right panels
     */
    @UiField
    FlowPanel totalTimeSpacing;

    /**
     * The panel that contains the start date components
     */
    @UiField
    FlowPanel startDateLabelPanel;

    /**
     * The panel that contains the end date components
     */
    @UiField
    FlowPanel endDateLabelPanel;

    /**
     * The flow panel that contains the start time labels
     */
    @UiField
    protected FlowPanel startTimeLabelPanel;

    /**
     * The start date label
     */
    @UiField
    Label startDateLabel;

    /**
     * The start date assignment label
     */
    @UiField
    Label startDateAssignmentLabel;

    /**
     * The end date label
     */
    @UiField
    Label endDateLabel;

    /**
     * The end date assignment label
     */
    @UiField
    Label endDateAssignmentLabel;

    /**
     * The start time label
     */
    @UiField
    Label startTimeLabel;

    /**
     * The start time assignment label
     */
    @UiField
    Label startTimeAssignmentLabel;

    /**
     * The end time label
     */
    @UiField
    Label endTimeLabel;

    /**
     * The end time assignment label
     */
    @UiField
    Label endTimeAssignmentLabel;

    /**
     * The default display format of the date picker
     */
    DefaultFormat DEFAULT_FORMAT = new DefaultFormat(DateTimeFormat.getFormat("dd MMMM yyyy"));

    /**
     * The date is selected by default
     */
    private final SSDate defaultSelectedDate;
    
    /**
     * The date that was previously selected by the component
     */
    private SSDate lastUsedDate;
    
    /**
     * The end date that was previously selected by the component
     */
    private SSDate lastUsedEndDate;

    /**
     * The last end date
     */
    private SSDate lastEndDate;

    /**
     * The max date the the start date can be
     */
    private final SSDate maxDate;

    /**
     * The min date that the start date can be
     */
    private final SSDate minDate;

    /**
     * The max length of the shift in milliseconds
     */
    private final long maxShiftTime;

    /**
     * The min length of the shift in milliseconds
     */
    private final long minShiftTime;

    /**
     * The date picker used select the start date
     */
    private final SSDatePicker startDatePicker;

    /**
     * The date picker use to select the end date
     */
    private final SSDatePicker endDatePicker;

    /**
     * The style applied to the date picker
     */
    private final String dtPickerSizeStyle = "dtPickerSize";

    /**
     * The time picker style
     */
    private final String timePickerStyle = "gwt-timePickerStyle";

    /**
     * Indicate that this component is enabled
     */
    private boolean enabled = true;

    /**
     * The flag used when the start date was manually set
     */
    private boolean startDateManuallySet = false;

    /**
     * The flag used when the start time was manually set
     */
    private boolean startDateTimeManuallySet = false;

    /**
     * The flag used when the end date was manually set
     */
    private boolean endDateManuallySet = false;

    /**
     * The flag used when the end time was manually set
     */
    private boolean endDateTimeManuallySet = false;

    /**
     * The previous set end date
     */
    private SSDate previousEndDate = null;

    /**
     * Will hold the value in which to increase the hours for every spin
     */
    private int hoursStep = 1;
    
    /**
     * Will hold the value in which to increase the minutes for every spin
     */
    private int minutesStep = 1;
    
    /**
     * UiBinder interface for the composite
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 13 May 2013
     */
    interface Binder extends UiBinder<Widget, DateTimeComponent> {
    }

    /**
     * This function return the number of days, hours, minutes and seconds as milliseconds
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @param day - The number of days
     * @param hour - The number of hours
     * @param min - The number of minutes
     * @param sec - The number of seconds
     *
     * @return the days, hours, min and sec as milliseconds
     */
    public static long dateToMilliseconds(int day, int hour, int min, int sec) {
        return (day * 24 * 60 * 60 * 1000) + (hour * 60 * 60 * 1000) + (min * 60 * 1000) + (sec * 1000);
    }

    /**
     * Class constructor
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 12 June 2015
     *
     * @param minDate - The minimum start date
     * @param maxDate - The maximum start date
     * @param selectedDate - The default selected date
     * @param minShiftTime - The minimum shift length in milliseconds
     * @param maxShiftTime - The maximum shift length in milliseconds
     */
    public DateTimeComponent(
        SSDate minDate,
        SSDate maxDate,
        SSDate selectedDate,
        long minShiftTime,
        long maxShiftTime
    ) {
        this(minDate, maxDate, selectedDate, minShiftTime, maxShiftTime, 1, 15);
    }
    
    /**
     * Class constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param minDate - The minimum start date
     * @param maxDate - The maximum start date
     * @param selectedDate - The default selected date
     * @param minShiftTime - The minimum shift length in milliseconds
     * @param maxShiftTime - The maximum shift length in milliseconds
     * @param hoursStep - The step to increase the hours with
     * @param minutesStep - The step to increase the minutes with
     */
    public DateTimeComponent(
        SSDate minDate,
        SSDate maxDate,
        SSDate selectedDate,
        long minShiftTime,
        long maxShiftTime,
        int hoursStep,
        int minutesStep
    ) {
        this.hoursStep = hoursStep;
        this.minutesStep = minutesStep;
        
        // This is the max date allowed for the start date
        this.maxDate = maxDate;
        this.maxDate.setSeconds(0);
        this.maxDate.setMinutes(roundUpTime(this.maxDate.getMinutes())); // round up the min

        this.minDate = minDate;
        this.minDate.setSeconds(0);
        this.minDate.setMinutes(roundUpTime(this.minDate.getMinutes()));

        this.defaultSelectedDate = selectedDate;

        this.defaultSelectedDate.setSeconds(0);
        this.defaultSelectedDate.setMinutes(roundUpTime(defaultSelectedDate.getMinutes()));
        
        this.maxShiftTime = maxShiftTime;
        this.minShiftTime = minShiftTime;

        // The date picker for the start date
        startDatePicker = new SSDatePicker(this.minDate, this.maxDate);
        startDatePicker.setStyleName(dtPickerSizeStyle);
        
        this.lastUsedDate = this.defaultSelectedDate;
        
        startDateBox = new DateBox(startDatePicker, defaultSelectedDate, DEFAULT_FORMAT) {
            /**
             * This function is overridden so that we can set the last used date as well
             * 
             * @author Michael Barnard <michael.barnard@a24group.com>
             * @since  22 July 2015
             * 
             * @param date - The date that should be set in the date picker
             * @param fireEvents - Whether or not to fire events when the value is set
             * 
             * @return void
             */
            @Override
            public void setValue(SSDate date, boolean fireEvents) {
                super.setValue(date, fireEvents);
                lastUsedDate = date;
            }
        };
        startDateBox.getTextBox().setReadOnly(true);
        startDateBox.addValueChangeHandler(
            new ValueChangeHandler<SSDate>() {

                /**
                 * The function that is called on the value change of the start data box
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 14 May 2013
                 *
                 * @param event - The ValueChangeEvent that was fired
                 */
                @Override
                public void onValueChange(ValueChangeEvent<SSDate> event) {
                    onStartDateBoxValueChange(event.getValue());
                }
            }
        );

        // Time picker used for the shift
        startTimePicker = new TimePicker(
            defaultSelectedDate,
            minDate,
            maxDate,
            true,
            null,
            DateTimeFormat.getFormat("HH"),
            DateTimeFormat.getFormat("mm"),
            null,
            minutesStep,
            hoursStep
        );

        // Add change handler
        startTimePicker.addChangeHandler(
            new ChangeHandler<SSDate>() {

                /**
                 * The function that is called on the value change of the startTimePicker
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 15 May 2013
                 *
                 * @param event - The ChangeEvent that was fired
                 */
                @Override
                public void onChange(ChangeEvent<SSDate> event) {
                    onStartTimePickerValueChange(event.getNewValue());
                }
            }
        );

        endDatePicker = new SSDatePicker(getMinEndDate(getShiftMinDate()), getMaxEndDate(getShiftMaxDate()));
        endDatePicker.setStyleName(dtPickerSizeStyle);
        
        this.lastUsedEndDate = getShiftMinDate();
        
        endDateBox = new DateBox(endDatePicker, getShiftMinDate(), DEFAULT_FORMAT) {
            /**
             * This function is overridden so that we can set the last used date as well
             * 
             * @author Michael Barnard <michael.barnard@a24group.com>
             * @since  22 July 2015
             * 
             * @param date - The date that should be set in the date picker
             * @param fireEvents - Whether or not to fire events when the value is set
             * 
             * @return void
             */
            @Override
            public void setValue(SSDate date, boolean fireEvents) {
                super.setValue(date, fireEvents);
                lastUsedEndDate = date;
            }
        };
        endDateBox.getTextBox().setReadOnly(true);
        endDateBox.addValueChangeHandler(
            new ValueChangeHandler<SSDate>() {

                /**
                 * The function that is called on the value change of the end data box
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 15 May 2013
                 *
                 * @param event - The ValueChangeEvent that was fired
                 */
                @Override
                public void onValueChange(ValueChangeEvent<SSDate> event) {
                    if (previousEndDate.getTime() - event.getValue().getTime() != 0) {
                        previousEndDate = (SSDate) event.getValue().clone();
                        onEndDateBoxValueChange(event.getValue());
                    }
                }
            }
        );

        // The end date time picker
        endTimePicker = new TimePicker(
            getShiftMinDate(),
            getShiftMinDate(),
            getShiftMaxDate(),
            true,
            null,
            DateTimeFormat.getFormat("HH"),
            DateTimeFormat.getFormat("mm"),
            null,
            minutesStep,
            hoursStep
        );
        this.lastEndDate = getShiftMinDate();
        endTimePicker.addChangeHandler(
            new ChangeHandler<SSDate>() {

                /**
                 * The function that is called on the value change of the endTimePicker
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 15 May 2013
                 *
                 * @param event - The ChangeEvent that was fired
                 */
                @Override
                public void onChange(ChangeEvent<SSDate> event) {
                    onEndTimePickerValueChange(event.getNewValue());
                }
            }
        );

        this.initWidget(uiBinder.createAndBindUi(this));

        startDatePanel.add(startTimePicker);
        endDatePanel.add(endTimePicker);

        startTimePicker.addStyleName(timePickerStyle);
        endTimePicker.addStyleName(timePickerStyle);

        totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
    }

    /**
     * The function that is called on the value change of the startTimePicker
     * Reset the start time picker date(not time) if the time picker have made a loop.
     * Set the new max and end date, selectable by the end date date picker.
     * Reset the end date fields to the same as the start date fields
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param date - The new date selected
     */
    private void onStartTimePickerValueChange(SSDate date) {
        if (startDateManuallySet) {
            startDateManuallySet = false;
            endTimePicker.setMinDate(getShiftMinDate());
            endTimePicker.setMaxDate(getShiftMaxDate());
        } else {
            if (startDateTimeManuallySet) {
                startDateTimeManuallySet = false;
            } else {
                if (date.getMinutes() % minutesStep != 0) {
                    SSDate resetDate = getRestDate((SSDate) (date.clone()));
                    if ((date.getMinutes() % minutesStep) - minutesStep == 1) {
                        date.setMinutes(date.getMinutes() - 1);
                        resetDate = date;
                    }
                    if ((date.getMinutes() % minutesStep) - minutesStep == -1) {
                        date.setMinutes(date.getMinutes() + 1);
                        resetDate = date;
                    }
                    startTimePicker.setDateTime(resetDate);
                }
                if (date.getHours() % 24 == 0) {
                    startTimePicker.setDate(startDateBox.getValue());
                }
                if (startTimePicker.getDateTime().getDay() != startDateBox.getValue().getDay()) {
                    startTimePicker.setDate(startDateBox.getValue());
                }
                endTimePicker.setMinDate(getShiftMinDate());
                endTimePicker.setMaxDate(getShiftMaxDate());
                endTimePicker.setDateTime(getShiftMinDate());
                endDateBox.setValue(startDateBox.getValue());
                onEndDateBoxValueChange(startDateBox.getValue());
            }
        }
        totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
    }

    /**
     * The function that is called on the value change of the endTimePicker.
     * Reset the end date if a loop was made
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param date - The new date selected
     */
    private void onEndTimePickerValueChange(SSDate date) {
        if (endDateManuallySet) {
            endDateManuallySet = false;
        } else {
            if (endDateTimeManuallySet) {
                endDateTimeManuallySet = false;
            } else {
                if (date.getMinutes() % minutesStep != 0) {
                    SSDate resetDate = getRestDate((SSDate) (date.clone()));
                    if ((date.getMinutes() % minutesStep) - minutesStep == 1) {
                        date.setMinutes(date.getMinutes() - 1);
                        resetDate = date;
                    }
                    if ((date.getMinutes() % minutesStep) - minutesStep == -1) {
                        date.setMinutes(date.getMinutes() + 1);
                        resetDate = date;
                    }
                    endTimePicker.setDateTime(resetDate);
                }
                if (date.getHours() % 24 == 0) {
                    if (
                        (startDateBox.getValue().getDay() == endDateBox.getValue().getDay())
                    ) {
                        if (getShiftMinDate().getTime() == endTimePicker.getDateTime().getTime()) {
                            totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
                            return;
                        }
                        if (endTimePicker.getDateTime().getMinutes() % 60 != 0) {
                            totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
                        }
                        endTimePicker.setDateTime(getShiftMinDate());
                        totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
                    } else {
                        if (getShiftMaxDate().getTime() == endTimePicker.getDateTime().getTime()) {
                            totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
                            return;
                        }
                        if (endTimePicker.getDateTime().getMinutes() % 60 != 0) {
                            totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
                            return;
                        }
                    }
                }
                if (
                    endDateBox.getValue().getDay() != endTimePicker.getDateTime().getDay() &&
                    startDateBox.getValue().getDay() == endDateBox.getValue().getDay()
                ) {
                    endTimePicker.setDateTime(getShiftMinDate());
                    totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
                    return;
                }
                if (endDateBox.getValue().getDay() != endTimePicker.getDateTime().getDay()) {
                    endTimePicker.setDateTime(getShiftMaxDate());
                }
            }
        }
        totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
    }

    /**
     * The function that is called on the value change of the start date box
     * Reset the end date field to to the start date
     * Set the date of the start date time picker the same as the start date box
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @param date - The new date selected
     */
    private void onStartDateBoxValueChange(SSDate date) {

        // Get the previous date without time
        SSDate previousDate = this.lastUsedDate;
        previousDate.resetTime();

        // Get the new date without time
        SSDate currentDate = date;
        currentDate.resetTime();
        
        // Execute only if the dates are different
        if (!currentDate.equals(previousDate)) {
            this.lastUsedDate = date;
            startTimePicker.setDate(date);

            endDatePicker.setMinimumDate(getMinEndDate(getShiftMinDate()));
            endDatePicker.setMaximumDate(getMaxEndDate(getShiftMaxDate()));

            endDateBox.setValue(getShiftMinDate());
            endTimePicker.setDateTime(getShiftMinDate());

            endTimePicker.setMinDate(getShiftMinDate());
            endTimePicker.setMaxDate(getShiftMaxDate());
            totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
        }
    }

    /**
     * The function that is called on the value change of the end date box
     * Set the end time picker date the same as the end date box
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @param date - The new date selected
     */
    private void onEndDateBoxValueChange(SSDate date) {
        // Get the previous date without time
        SSDate previousDate = this.lastUsedEndDate;
        previousDate.resetTime();

        // Get the new date without time
        SSDate currentDate = date;
        currentDate.resetTime();

        // Execute only if the dates are different
        if (!currentDate.equals(previousDate)) {
            this.lastUsedEndDate = date;
            endTimePicker.setDateTime(getShiftMinDate());
            endTimePicker.setDate(date);
            totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
        }
    }

    /**
     * Set the text of the header
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @param text - The text to set in the header
     */
    public void setHeader(String text) {
        headerLabel.setText(text);
    }

    /**
     * This reset the date if something went wrong
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @param restDate - The date the is used
     *
     * @return the date with the reset time
     */
    public SSDate getRestDate(SSDate restDate) {
        restDate.setHours(this.defaultSelectedDate.getHours());
        restDate.setMinutes(this.defaultSelectedDate.getMinutes());
        restDate.setSeconds(00);
        return restDate;
    }

    /**
     * This function is used to get the max date the time picker is allowed to display for the end date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @return the shift max date
     */
    private SSDate getShiftMaxDate() {
        return new SSDate(startTimePicker.getDateTime().getTime() + maxShiftTime);
    }

    /**
     * This function is used to get the min date the time picker is allowed to display for the end date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @return the shift min date
     */
    private SSDate getShiftMinDate() {
        return new SSDate(startTimePicker.getDateTime().getTime() + minShiftTime);
    }

    /**
     * This function is used to get the max date the date picker is allowed to display for the end date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @param startDate - The start date the max date must be calculated from
     *
     * @return the max end date
     */
    private SSDate getMaxEndDate(SSDate startDate) {
        SSDate dMax = startDate;
        dMax.setHours(23);
        dMax.setMinutes(45);
        dMax.setSeconds(00);
        return dMax;
    }

    /**
     * This function is used to get the min date the date picker is allowed to display for the end date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @param startDate - The start date the min date must be calculated from
     *
     * @return the min end date
     */
    private SSDate getMinEndDate(SSDate startDate) {
        SSDate dMin = startDate;
        dMin.setHours(0);
        dMin.setMinutes(0);
        dMin.setSeconds(0);
        return dMin;
    }

    /**
     * Get the total number min and hours of a shift.
     * Note the max date time is 24 hours.
     * If the the diff is 0 is seen as a 24h shift
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param startDate - The shift start date
     * @param endDate - The shift end date
     *
     * @return the shift is format "(H.M)"
     */
    public String getShiftTimeDiff(SSDate startDate, SSDate endDate) {
        if (minutesStep == 15) {
            boolean reversed = false;
            if (endDate.getTime() < startDate.getTime() - 1000) {
                reversed = true;
            }
            int shiftTotalHours = (int) (((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60)));
            int shiftTotalMin = (int) (((endDate.getTime() - startDate.getTime()) / (1000 * 60)) % 60);
            if (shiftTotalMin == 59) {
                shiftTotalMin = 0;
                shiftTotalHours++;
            } else if (shiftTotalMin % 15 == 14) {
                shiftTotalMin++;
            }
            int totalMinAndHours = (shiftTotalHours * 60) + shiftTotalMin;
    
            if (totalMinAndHours >= 1441 || totalMinAndHours <= 1439) {
                if (endDate.getTime() - startDate.getTime() > 86399000) {
                    shiftTotalHours = 24;
                    totalMinAndHours = 1440;
                    shiftTotalMin = 0;
                }
            }
    
            //check if date difference are a varient of 0.25
            if (((15 - (shiftTotalMin % 15) < 1)) || (totalMinAndHours > 1440) || reversed) {
                return "( -err )";
            }
    
            if (shiftTotalMin == 0) {
                return "( " + shiftTotalHours + "h )";
            }
            String displayedMinutes = String.valueOf((int)((Math.ceil(shiftTotalMin) / 60) * 100)).replaceAll("0", "");
            return "( " + shiftTotalHours + "." + displayedMinutes + "h )";
        } else {
            /**
             * Due to the nature of the events being thrown in this component, the
             * getShiftTimeDiff ignores the values passed in to this method(unless the minutes 
             * step is 15) and instead retrieves it from the components itself. This way, it is 
             * guaranteed to always have the latest values, and avoid being thrown off by a dalayed
             * event containing old date values.
             */
            // Get start date object
            startDate = startDatePicker.getValue();
            // Set the time to the start time picker
            startDate.setDirectTime(
                startTimePicker.getDateTime().getHours(),
                startTimePicker.getDateTime().getMinutes(),
                startTimePicker.getDateTime().getSeconds()
            );
            // Get end date object
            endDate = endDatePicker.getValue();
            // Set the time to the end time picker
            endDate.setDirectTime(
                endTimePicker.getDateTime().getHours(),
                endTimePicker.getDateTime().getMinutes(),
                endTimePicker.getDateTime().getSeconds()
            );
            //getTime is in milliseconds, so divide by 100 for seconds and then 60 for minutes
            float shiftTotalMinutes = (endDate.getTime() - startDate.getTime()) / 1000 / 60;
            
            //divide the total minutes by 60 and cast to int to get amount of hours e.g 8.3h will be 8h
            int hours = (int) (shiftTotalMinutes / 60);
            //subtract the amount of minutes taken up by whole hours above to get remaining minutes.
            int min = (int) (shiftTotalMinutes - (hours * 60));
            
            String totalTimeString = TOTAL_TIME_FORMAT_START + hours + TOTAL_TIME_FORMAT_HOURS;
            if (min < 10) {
                totalTimeString += " " + 0;
            } else {
                totalTimeString += " ";
            }
            totalTimeString += min + TOTAL_TIME_FORMAT_MINUTES + TOTAL_TIME_FORMAT_END;
            
            return totalTimeString;
        }
    }

    /**
     * Function that is used to round the date up min to the nearest 15 min.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @param min - The min as int
     *
     * @return passed in min rounded up to the nearest 15 min
     */
    private int roundUpTime(int min) {
        return (int) (Math.ceil(((double) min) / minutesStep) * minutesStep);
    }

    /**
     * Get the start date selected
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @return the selected start date
     */
    public SSDate getStartDate() {
        return this.startTimePicker.getDateTime();
    }

    /**
     * Get the end date selected
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @return the selected end date
     */
    public SSDate getEndDate() {
        return this.endTimePicker.getDateTime();
    }

    /**
     * Set the start date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @param startDate - The start date.
     */
    public void setStartDate(SSDate startDate) {
        startDate.setMinutes(roundUpTime(startDate.getMinutes()));
        startDate.setSeconds(0);
        this.startDateManuallySet = true;
        this.startDateTimeManuallySet= true;
        this.startDateBox.setValue(startDate);
        this.startTimePicker.setDateTime(startDate);
    }

    /**
     * Set the end date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @param endDate - The end date.
     */
    public void setEndDate(SSDate endDate) {
        endDate.setMinutes(roundUpTime(endDate.getMinutes()));
        endDate.setSeconds(0);
        endDatePicker.setMinimumDate(getMinEndDate(getShiftMinDate()));
        endDatePicker.setMaximumDate(getMaxEndDate(getShiftMaxDate()));
        this.endDateManuallySet = true;
        this.endDateTimeManuallySet= true;
        this.lastEndDate = lastEndDate;
        this.endDateBox.setValue(endDate);
        this.endTimePicker.setDateTime(endDate);
        previousEndDate = (SSDate) this.endDateBox.getValue().clone();
    }

    /**
     * Sets whether the date box is enabled.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @param enabled is the box enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
        startDateBox.setEnabled(enabled);
        endDateBox.setEnabled(enabled);

        startTimePicker.setEnabled(enabled);
        endTimePicker.setEnabled(enabled);
    }

    /**
     * Retrieve the flag that indicates whether the field is enabled.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @return The flag that indicates whether the field is enabeld
     */
    public boolean isEnabled() {
        return this.enabled;
    }
}
