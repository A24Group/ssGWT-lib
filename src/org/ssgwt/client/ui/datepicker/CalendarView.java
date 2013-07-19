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

package org.ssgwt.client.ui.datepicker;

import org.ssgwt.client.i18n.SSDate;

/**
 * The CalendarView is a calendar grid that represents the current view of a
 * {@link DatePicker}. Note, the calendar view only deals with the currently
 * visible dates and all state is flushed when the calendar view is refreshed.
 *
 */
public abstract class CalendarView extends DatePickerComponent {

    /**
     * Constructor.
     */
    public CalendarView() {
    }

    /**
     * Adds a style name to the cell of the supplied date. This style is only set
     * until the next time the {@link CalendarView} is refreshed.
     *
     * @param styleName style name to add
     * @param date date that will have the supplied style added
     */
    public abstract void addStyleToDate(String styleName, SSDate date);

    /**
     * Returns the first date that is currently shown by the calendar.
     *
     * @return the first date.
     */
    public abstract SSDate getFirstDate();

    /**
     * Returns the last date that is currently shown by the calendar.
     *
     * @return the last date.
     */
    public abstract SSDate getLastDate();

    /**
     * Is the cell representing the given date enabled?
     *
     * @param date the date
     * @return is the date enabled
     */
    public abstract boolean isDateEnabled(SSDate date);

    /**
     * Removes a visible style name from the cell of the supplied date.
     *
     * @param styleName style name to remove
     * @param date date that will have the supplied style added
     */
    public abstract void removeStyleFromDate(String styleName, SSDate date);

    /**
     * Enables or Disables a particular date. by default all valid dates are
     * enabled after a rendering event. Disabled dates cannot be selected.
     *
     * @param enabled true for enabled, false for disabled
     * @param date date to enable or disable
     */
    public abstract void setEnabledOnDate(boolean enabled, SSDate date);

    /**
     * Allows the calendar view to update the date picker's highlighted date.
     *
     * @param date the highlighted date
     */
    protected final void setHighlightedDate(SSDate date) {
        getDatePicker().setHighlightedDate(date);
    }
}