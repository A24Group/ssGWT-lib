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

import java.util.ArrayList;
import java.util.List;

import org.ssgwt.client.i18n.DateTimeFormat;
import org.ssgwt.client.i18n.SSDate;
import org.ssgwt.client.ui.form.spinner.Spinner.SpinnerResources;
import org.ssgwt.client.ui.form.spinner.ValueSpinner.ValueSpinnerResources;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * {@link TimePicker} widget to enter the time part of a date using spinners
 */
public class TimePicker extends Composite implements FiresChangeEvents<SSDate> {

    private class TimeSpinner extends ValueSpinner {
        private final DateTimeFormat dateTimeFormat;

        public TimeSpinner(SSDate date, SSDate minDate, SSDate maxDate, DateTimeFormat dateTimeFormat, int step,
                boolean constrained, ValueSpinnerResources styles, SpinnerResources images,
                boolean readOnly) {
            super(date.getTime(), minDate.getTime(), maxDate.getTime(), 1, 99, constrained, styles, images);
            this.dateTimeFormat = dateTimeFormat;
            getSpinner().setMinStep(step);
            getSpinner().setMaxStep(step);
            // Refresh value after dateTimeFormat is set
            getSpinner().setValue(date.getTime(), true);
            getTextBox().setReadOnly(readOnly);
        }

        /**
         * Set the max allowed date
         *
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since 15 May 2013
         *
         * @param maxDate - The max allowed date
         */
        public void setMaxDate(SSDate maxDate) {
            setMaxValue(maxDate.getTime());
        }

        /**
         * Set the min allowed date
         *
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since 15 May 2013
         *
         * @param minDate - The min allowed date
         */
        public void setMinDate(SSDate minDate) {
            setMinValue(minDate.getTime());
        }

        @Override
        protected long parseValue(String value) {
            SSDate parsedDate = new SSDate(dateInMillis);
            dateTimeFormat.parse(value, 0, parsedDate);
            return parsedDate.getTime();
        }

        @Override
        protected String formatValue(long value) {
            dateInMillis = value;
            if (dateTimeFormat != null) {
                return dateTimeFormat.format(new SSDate(dateInMillis));
            }
            return "";
        }
    }

    private static final int SECOND_IN_MILLIS = 1000;
    private static final int MINUTE_IN_MILLIS = 60000;
    private static final int HOUR_IN_MILLIS = 3600000;
    private static final int HALF_DAY_IN_MS = 43200000;
    private static final int DAY_IN_MS = 86400000;

    private final List<TimeSpinner> timeSpinners = new ArrayList<TimeSpinner>();
    private List<ChangeHandler<SSDate>> changeHandlers;
    private long dateInMillis;
    private boolean enabled = true;
    private int minuteStep = 1;
    private int hourStep = 1;

    private final SpinnerListener listener = new SpinnerListener() {
        @Override
        public void onSpinning(long value) {
            if (changeHandlers != null) {
                for (ChangeHandler<SSDate> changeHandler : changeHandlers) {
                    changeHandler.onChange(
                        new ChangeEvent<SSDate>(
                            TimePicker.this,
                            new SSDate(dateInMillis),
                            new SSDate(value)
                        )
                    );
                }
            }
        }
    };

    /**
     * @param use24Hours
     *            if set to true the {@link TimePicker} will use 24h format
     */
    public TimePicker(boolean use24Hours) {
        this(new SSDate(), use24Hours);
    }

    /**
     * @param date
     *            the date providing the initial time to display
     * @param use24Hours
     *            if set to true the {@link TimePicker} will use 24h format
     */
    public TimePicker(SSDate date, boolean use24Hours) {
        this(
            date,
            new SSDate("January 1, 1970, 00:00:00 GMT"),
            new SSDate("January 1, 1970, 00:00:00 GMT"),
            false,
            use24Hours ? null : DateTimeFormat.getFormat("aa"),
            use24Hours ? DateTimeFormat.getFormat("HH") : DateTimeFormat.getFormat("hh"),
            DateTimeFormat.getFormat("mm"),
            DateTimeFormat.getFormat("ss"),
            1,
            1
        );
    }

    /**
     * @param date
     *            the date providing the initial time to display
     * @param amPmFormat
     *            the format to display AM/PM. Can be null to hide AM/PM field
     * @param hoursFormat
     *            the format to display the hours. Can be null to hide hours
     *            field
     * @param minutesFormat
     *            the format to display the minutes. Can be null to hide minutes
     *            field
     * @param secondsFormat
     *            the format to display the seconds. Can be null to seconds
     *            field
     */
    public TimePicker(SSDate date, SSDate min, SSDate max, boolean constrained, DateTimeFormat amPmFormat,
            DateTimeFormat hoursFormat, DateTimeFormat minutesFormat,
            DateTimeFormat secondsFormat, int minuteStep, int hourStep) {
        this(date, min, max, amPmFormat, hoursFormat, minutesFormat, secondsFormat, constrained, null,
                null, true, minuteStep, hourStep);
    }

    /**
     * @param date
     *            the date providing the initial time to display
     * @param min min value
     * @param max max value
     * @param amPmFormat
     *            the format to display AM/PM. Can be null to hide AM/PM field
     * @param hoursFormat
     *            the format to display the hours. Can be null to hide hours
     *            field
     * @param minutesFormat
     *            the format to display the minutes. Can be null to hide minutes
     *            field
     * @param secondsFormat
     *            the format to display the seconds. Can be null to seconds
     *            field
     * @param styles
     *            styles to be used by this TimePicker instance
     * @param images
     *            images to be used by all nested Spinner widgets
     *
     */
    public TimePicker(SSDate date, SSDate min, SSDate max, DateTimeFormat amPmFormat,
            DateTimeFormat hoursFormat, DateTimeFormat minutesFormat,
            DateTimeFormat secondsFormat, boolean constrained, ValueSpinnerResources styles,
            SpinnerResources images, boolean readOnly, int minuteStep, int hourStep) {
        this.dateInMillis = date.getTime();
        this.minuteStep = minuteStep;
        this.hourStep = hourStep;
        FlowPanel horizontalPanel = new FlowPanel();
        horizontalPanel.setStylePrimaryName("gwt-TimePicker");
        if (amPmFormat != null) {
            TimeSpinner amPmSpinner = new TimeSpinner(date, min, max, amPmFormat,
                    HALF_DAY_IN_MS, constrained, styles, images, readOnly);
            timeSpinners.add(amPmSpinner);
            horizontalPanel.add(amPmSpinner);
        }
        if (hoursFormat != null) {
            TimeSpinner hoursSpinner = new TimeSpinner(date, min, max, hoursFormat,
                    HOUR_IN_MILLIS * hourStep, constrained, styles, images, readOnly);
            if (timeSpinners.size() != 0) {
                Label separator = new Label(":");
                separator.setStylePrimaryName("gwt-TimePickerSeparator");
                horizontalPanel.add(separator);
            }
            timeSpinners.add(hoursSpinner);
            horizontalPanel.add(hoursSpinner);
        }
        if (minutesFormat != null) {
            TimeSpinner minutesSpinner = new TimeSpinner(date, min, max, minutesFormat,
                    MINUTE_IN_MILLIS * minuteStep, constrained, styles, images, readOnly);
            if (timeSpinners.size() != 0) {
                Label separator = new Label(":");
                separator.setStylePrimaryName("gwt-TimePickerSeparator");
                horizontalPanel.add(separator);
            }
            timeSpinners.add(minutesSpinner);
            horizontalPanel.add(minutesSpinner);
        }
        if (secondsFormat != null) {
            TimeSpinner secondsSpinner = new TimeSpinner(date, min, max, secondsFormat,
                    SECOND_IN_MILLIS, constrained, styles, images, readOnly);
            if (timeSpinners.size() != 0) {
                Label separator = new Label(":");
                separator.setStylePrimaryName("gwt-TimePickerSeparator");
                horizontalPanel.add(separator);
            }
            timeSpinners.add(secondsSpinner);
            horizontalPanel.add(secondsSpinner);
        }
        for (TimeSpinner timeSpinner : timeSpinners) {
            for (TimeSpinner nestedSpinner : timeSpinners) {
                if (nestedSpinner != timeSpinner) {
                    timeSpinner.getSpinner().addSpinnerListener(
                            nestedSpinner.getSpinnerListener());
                }
            }
            timeSpinner.getSpinner().addSpinnerListener(listener);
        }
        initWidget(horizontalPanel);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.gwt.widgetideas.client.event.FiresChangeEvents#addChangeHandler
     * (com.google.gwt.widgetideas.client.event.ChangeHandler)
     */
    @Override
    public void addChangeHandler(ChangeHandler<SSDate> changeHandler) {
        if (changeHandlers == null) {
            changeHandlers = new ArrayList<ChangeHandler<SSDate>>();
        }
        changeHandlers.add(changeHandler);
    }

    /**
     * @return the date specified by this {@link TimePicker}
     */
    public SSDate getDateTime() {
        return new SSDate(dateInMillis);
    }

    /**
     * @return Gets whether this widget is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the max date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param maxDate - The new max date
     */
    public void setMaxDate(SSDate maxDate) {
        for (TimeSpinner timeSpinner : timeSpinners) {
            timeSpinner.setMaxDate(maxDate);
        }
    }

    /**
     * Set the min date
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 15 May 2013
     *
     * @param minDate - The new min date
     */
    public void setMinDate(SSDate minDate) {
        for (TimeSpinner timeSpinner : timeSpinners) {
            timeSpinner.setMinDate(minDate);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.gwt.widgetideas.client.event.FiresChangeEvents#removeChangeHandler
     * (com.google.gwt.widgetideas.client.event.ChangeHandler)
     */
    @Override
    public void removeChangeHandler(ChangeHandler<SSDate> changeHandler) {
        if (changeHandlers != null) {
            changeHandlers.remove(changeHandler);
        }
    }

    /**
     * @param date
     *            the date to be set. Only the date part will be set, the time
     *            part will not be affected
     */
    public void setDate(SSDate date) {
        SSDate currentDate = new SSDate(dateInMillis);
        // Only change the date part, leave time part untouched
        date.setHours(currentDate.getHours());
        date.setMinutes(currentDate.getMinutes());
        date.setSeconds(currentDate.getSeconds());

        this.dateInMillis = date.getTime();
        for (TimeSpinner spinner : timeSpinners) {
            spinner.getSpinner().setValue(dateInMillis, false);
        }
    }

    /**
     * @param date
     *            the date to be set. Both date and time part will be set
     */
    public void setDateTime(SSDate date) {
        dateInMillis = date.getTime();
        for (TimeSpinner spinner : timeSpinners) {
            spinner.getSpinner().setValue(dateInMillis, true);
        }
    }

    /**
     * Sets whether this widget is enabled.
     *
     * @param enabled
     *            true to enable the widget, false to disable it
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        for (TimeSpinner spinner : timeSpinners) {
            spinner.setEnabled(enabled);
        }
    }
}