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
import java.util.Date;
import java.util.List;

import org.ssgwt.client.ui.form.spinner.Spinner.SpinnerResources;
import org.ssgwt.client.ui.form.spinner.ValueSpinner.ValueSpinnerResources;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * {@link TimePicker} widget to enter the time part of a date using spinners
 */
public class TimePicker extends Composite implements FiresChangeEvents<Date> {
  private class TimeSpinner extends ValueSpinner {
    private final DateTimeFormat dateTimeFormat;

    public TimeSpinner(Date date, DateTimeFormat dateTimeFormat, int step, ValueSpinnerResources styles, SpinnerResources images) {
      super(date.getTime(), styles, images);
      this.dateTimeFormat = dateTimeFormat;
      getSpinner().setMinStep(step);
      getSpinner().setMaxStep(step);
      // Refresh value after dateTimeFormat is set
      getSpinner().setValue(date.getTime(), true);
    }

    @Override
    protected long parseValue(String value) {
      Date parsedDate = new Date(dateInMillis);
      dateTimeFormat.parse(value, 0, parsedDate);
      return parsedDate.getTime();
    }

    @Override
    protected String formatValue(long value) {
      dateInMillis = value;
      if (dateTimeFormat != null) {
        return dateTimeFormat.format(new Date(dateInMillis));
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
  private List<ChangeHandler<Date>> changeHandlers;
  private long dateInMillis;
  private boolean enabled = true;

  private final SpinnerListener listener = new SpinnerListener() {
    @Override
    public void onSpinning(long value) {
      if (changeHandlers != null) {
        for (ChangeHandler<Date> changeHandler : changeHandlers) {
          changeHandler.onChange(new ChangeEvent<Date>(TimePicker.this,
              new Date(value), new Date(dateInMillis)));
        }
      }
    }
  };

  /**
   * @param use24Hours if set to true the {@link TimePicker} will use 24h format
   */
  public TimePicker(boolean use24Hours) {
    this(new Date(), use24Hours);
  }

  /**
   * @param date the date providing the initial time to display
   * @param use24Hours if set to true the {@link TimePicker} will use 24h format
   */
  public TimePicker(Date date, boolean use24Hours) {
    this(date, use24Hours ? null : DateTimeFormat.getFormat("aa"), use24Hours
        ? DateTimeFormat.getFormat("HH") : DateTimeFormat.getFormat("hh"),
        DateTimeFormat.getFormat("mm"), DateTimeFormat.getFormat("ss"));
  }
  /**
   * @param date the date providing the initial time to display
   * @param amPmFormat the format to display AM/PM. Can be null to hide AM/PM
   *          field
   * @param hoursFormat the format to display the hours. Can be null to hide
   *          hours field
   * @param minutesFormat the format to display the minutes. Can be null to hide
   *          minutes field
   * @param secondsFormat the format to display the seconds. Can be null to
   *          seconds field
   */
  public TimePicker(Date date, DateTimeFormat amPmFormat,
      DateTimeFormat hoursFormat, DateTimeFormat minutesFormat,
      DateTimeFormat secondsFormat) {
      this(date, amPmFormat, hoursFormat, minutesFormat, secondsFormat, null, null);
  }


  /**
   * @param date the date providing the initial time to display
   * @param amPmFormat the format to display AM/PM. Can be null to hide AM/PM
   *          field
   * @param hoursFormat the format to display the hours. Can be null to hide
   *          hours field
   * @param minutesFormat the format to display the minutes. Can be null to hide
   *          minutes field
   * @param secondsFormat the format to display the seconds. Can be null to
   *          seconds field
   * @param styles styles to be used by this TimePicker instance
   * @param images images to be used by all nested Spinner widgets
   *
   */
  public TimePicker(Date date, DateTimeFormat amPmFormat,
      DateTimeFormat hoursFormat, DateTimeFormat minutesFormat,
      DateTimeFormat secondsFormat, ValueSpinnerResources styles, SpinnerResources images) {
    this.dateInMillis = date.getTime();
    FlowPanel horizontalPanel = new FlowPanel();
    horizontalPanel.setStylePrimaryName("gwt-TimePicker");
    if (amPmFormat != null) {
      TimeSpinner amPmSpinner = new TimeSpinner(date, amPmFormat,
          HALF_DAY_IN_MS, styles, images);
      timeSpinners.add(amPmSpinner);
      horizontalPanel.add(amPmSpinner);
    }
    if (hoursFormat != null) {
      TimeSpinner hoursSpinner = new TimeSpinner(date, hoursFormat,
          HOUR_IN_MILLIS, styles, images);
      if (timeSpinners.size() != 0) {
          Label separator = new Label(":");
          separator.setStylePrimaryName("gwt-TimePickerSeparator");
          horizontalPanel.add(separator);
      }
      timeSpinners.add(hoursSpinner);
      horizontalPanel.add(hoursSpinner);
    }
    if (minutesFormat != null) {
      TimeSpinner minutesSpinner = new TimeSpinner(date, minutesFormat,
          MINUTE_IN_MILLIS, styles, images);
      if (timeSpinners.size() != 0) {
          Label separator = new Label(":");
          separator.setStylePrimaryName("gwt-TimePickerSeparator");
          horizontalPanel.add(separator);
      }
      timeSpinners.add(minutesSpinner);
      horizontalPanel.add(minutesSpinner);
    }
    if (secondsFormat != null) {
      TimeSpinner secondsSpinner = new TimeSpinner(date, secondsFormat,
          SECOND_IN_MILLIS, styles, images);
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
   * @see com.google.gwt.widgetideas.client.event.FiresChangeEvents#addChangeHandler(com.google.gwt.widgetideas.client.event.ChangeHandler)
   */
  @Override
public void addChangeHandler(ChangeHandler<Date> changeHandler) {
    if (changeHandlers == null) {
      changeHandlers = new ArrayList<ChangeHandler<Date>>();
    }
    changeHandlers.add(changeHandler);
  }

  /**
   * @return the date specified by this {@link TimePicker}
   */
  public Date getDateTime() {
    return new Date(dateInMillis);
  }

  /**
   * @return Gets whether this widget is enabled
   */
  public boolean isEnabled() {
    return enabled;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.google.gwt.widgetideas.client.event.FiresChangeEvents#removeChangeHandler(com.google.gwt.widgetideas.client.event.ChangeHandler)
   */
  @Override
public void removeChangeHandler(ChangeHandler<Date> changeHandler) {
    if (changeHandlers != null) {
      changeHandlers.remove(changeHandler);
    }
  }

  /**
   * @param date the date to be set. Only the date part will be set, the time
   *          part will not be affected
   */
  public void setDate(Date date) {
    // Only change the date part, leave time part untouched
    dateInMillis = (long) ((Math.floor(date.getTime() / DAY_IN_MS) + 1) * DAY_IN_MS)
        + dateInMillis % DAY_IN_MS;
    for (TimeSpinner spinner : timeSpinners) {
      spinner.getSpinner().setValue(dateInMillis, false);
    }
  }

  /**
   * @param date the date to be set. Both date and time part will be set
   */
  public void setDateTime(Date date) {
    dateInMillis = date.getTime();
    for (TimeSpinner spinner : timeSpinners) {
      spinner.getSpinner().setValue(dateInMillis, true);
    }
  }

  /**
   * Sets whether this widget is enabled.
   *
   * @param enabled true to enable the widget, false to disable it
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    for (TimeSpinner spinner : timeSpinners) {
      spinner.setEnabled(enabled);
    }
  }
}