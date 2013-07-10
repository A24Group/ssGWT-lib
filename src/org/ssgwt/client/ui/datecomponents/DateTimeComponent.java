package org.ssgwt.client.ui.datecomponents;

import java.util.Date;

import org.ssgwt.client.ui.datepicker.DateBox;
import org.ssgwt.client.ui.datepicker.DateBox.DefaultFormat;
import org.ssgwt.client.ui.datepicker.SSDatePicker;
import org.ssgwt.client.ui.form.spinner.ChangeEvent;
import org.ssgwt.client.ui.form.spinner.ChangeHandler;
import org.ssgwt.client.ui.form.spinner.TimePicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
    private final Date defaultSelectedDate;

    /**
     * The last end date
     */
    private Date lastEndDate;

    /**
     * The max date the the start date can be
     */
    private final Date maxDate;

    /**
     * The min date that the start date can be
     */
    private final Date minDate;

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
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 13 May 2013
     *
     * @param minDate - The minimum start date
     * @param maxDate - The maximum start date
     * @param selectedDate - The default selected date
     * @param minShiftTime - The minimum shift length in milliseconds
     * @param maxShiftTime - The maximum shift length in milliseconds
     */
    public DateTimeComponent(Date minDate, Date maxDate, Date selectedDate, long minShiftTime, long maxShiftTime) {
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

        startDateBox = new DateBox(startDatePicker, defaultSelectedDate, DEFAULT_FORMAT);
        startDateBox.getTextBox().setReadOnly(true);
        startDateBox.addValueChangeHandler(
            new ValueChangeHandler<Date>() {

                /**
                 * The function that is called on the value change of the start data box
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 14 May 2013
                 *
                 * @param event - The ValueChangeEvent that was fired
                 */
                @Override
                public void onValueChange(ValueChangeEvent<Date> event) {
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
            15,
            1
        );

        // Add change handler
        startTimePicker.addChangeHandler(
            new ChangeHandler<Date>() {

                /**
                 * The function that is called on the value change of the startTimePicker
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 15 May 2013
                 *
                 * @param event - The ChangeEvent that was fired
                 */
                @Override
                public void onChange(ChangeEvent<Date> event) {
                    onStartTimePickerValueChange(event.getNewValue());
                }
            }
        );

        endDatePicker = new SSDatePicker(getMinEndDate(getShiftMinDate()), getMaxEndDate(getShiftMaxDate()));
        endDatePicker.setStyleName(dtPickerSizeStyle);

        endDateBox = new DateBox(endDatePicker, getShiftMinDate(), DEFAULT_FORMAT);
        endDateBox.getTextBox().setReadOnly(true);
        endDateBox.addValueChangeHandler(
            new ValueChangeHandler<Date>() {

                /**
                 * The function that is called on the value change of the end data box
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 15 May 2013
                 *
                 * @param event - The ValueChangeEvent that was fired
                 */
                @Override
                public void onValueChange(ValueChangeEvent<Date> event) {
                    onEndDateBoxValueChange(event.getValue());
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
            15,
            1
        );
        this.lastEndDate = getShiftMinDate();
        endTimePicker.addChangeHandler(
            new ChangeHandler<Date>() {

                /**
                 * The function that is called on the value change of the endTimePicker
                 *
                 * @author Alec Erasmus <alec.erasmus@a24group.com>
                 * @since 15 May 2013
                 *
                 * @param event - The ChangeEvent that was fired
                 */
                @Override
                public void onChange(ChangeEvent<Date> event) {
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
    private void onStartTimePickerValueChange(Date date) {
        if (startDateManuallySet) {
            startDateManuallySet = false;
            endTimePicker.setMinDate(getShiftMinDate());
            endTimePicker.setMaxDate(getShiftMaxDate());
        } else {
            if (startDateTimeManuallySet) {
                startDateTimeManuallySet = false;
            } else {
                if (date.getMinutes() % 15 != 0) {
                    Date resetDate = getRestDate((Date) (date.clone()));
                    if ((date.getMinutes() % 15) - 15 == 1) {
                        date.setMinutes(date.getMinutes() - 1);
                        resetDate = date;
                    }
                    if ((date.getMinutes() % 15) - 15 == -1) {
                        date.setMinutes(date.getMinutes() + 1);
                        resetDate = date;
                    }
                    startTimePicker.setDateTime(resetDate);
                }
                if (date.getHours() % 24 == 0) {
                    System.out.println(date.getHours());
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
    private void onEndTimePickerValueChange(Date date) {
        if (endDateManuallySet) {
            endDateManuallySet = false;
        } else {
            if (endDateTimeManuallySet) {
                endDateTimeManuallySet = false;
            } else {
                if (date.getMinutes() % 15 != 0) {
                    Date resetDate = getRestDate((Date) (date.clone()));
                    if ((date.getMinutes() % 15) - 15 == 1) {
                        date.setMinutes(date.getMinutes() - 1);
                        resetDate = date;
                    }
                    if ((date.getMinutes() % 15) - 15 == -1) {
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
    private void onStartDateBoxValueChange(Date date) {
        startTimePicker.setDate(date);

        endDatePicker.setMinimumDate(getMinEndDate(getShiftMinDate()));
        endDatePicker.setMaximumDate(getMaxEndDate(getShiftMaxDate()));

        endDateBox.setValue(getShiftMinDate());
        endTimePicker.setDateTime(getShiftMinDate());

        endTimePicker.setMinDate(getShiftMinDate());
        endTimePicker.setMaxDate(getShiftMaxDate());
        totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
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
    private void onEndDateBoxValueChange(Date date) {
        endTimePicker.setDateTime(getShiftMinDate());
        endTimePicker.setDate(date);
        totalTime.setText(getShiftTimeDiff(startTimePicker.getDateTime(), endTimePicker.getDateTime()));
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
    public Date getRestDate(Date restDate) {
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
    private Date getShiftMaxDate() {
        return new Date(startTimePicker.getDateTime().getTime() + maxShiftTime);
    }

    /**
     * This function is used to get the min date the time picker is allowed to display for the end date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 14 May 2013
     *
     * @return the shift min date
     */
    private Date getShiftMinDate() {
        return new Date(startTimePicker.getDateTime().getTime() + minShiftTime);
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
    private Date getMaxEndDate(Date startDate) {
        Date dMax = startDate;
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
    private Date getMinEndDate(Date startDate) {
        Date dMin = startDate;
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
    public String getShiftTimeDiff(Date startDate, Date endDate) {
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
        return (int) (Math.ceil(((double) min) / 15) * 15);
    }

    /**
     * Get the start date selected
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 16 May 2013
     *
     * @return the selected start date
     */
    public Date getStartDate() {
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
    public Date getEndDate() {
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
    public void setStartDate(Date startDate) {
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
    public void setEndDate(Date endDate) {
        this.endDateManuallySet = true;
        this.endDateTimeManuallySet= true;
        this.lastEndDate = lastEndDate;
        this.endDateBox.setValue(endDate);
        this.endTimePicker.setDateTime(endDate);
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
