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

package org.ssgwt.client.ui.datepicker;

import org.ssgwt.client.i18n.SSDate;

/**
 * This date picker uses a slightly different Month Selector and Calendar view
 * that has different styling than the defaults. It also supports setting of minimum
 * and maximum dates.
 *
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since  16 July 2012
 */
@SuppressWarnings(/* Date manipulation required */ { "deprecation" })
public class SSDatePicker extends DatePicker {

    /**
     * Default minimum year that will be displayed in the default date picker's
     * year combo box.
     */
    public static final int DEFAULT_MINIMUM_YEAR = 1970;

    /**
     * Default maximum year that will be displayed in the default date picker's
     * year combo box.
     */
    public static final int DEFAULT_MAXIMUM_YEAR = 2020;

    /**
     * Default constructor. This creates an instance that with the minimum and
     * maximum dates in the range specified by the default minimum and maximum year
     * constants.
     */
    public SSDatePicker() {
        //this(new DefaultMonthSelector2(DEFAULT_MINIMUM_YEAR, DEFAULT_MAXIMUM_YEAR), new DefaultCalendarView2(), new CalendarModel());
        this(new SSDate(DEFAULT_MINIMUM_YEAR - 1900, 0, 1), new SSDate(DEFAULT_MAXIMUM_YEAR - 1900, 11, 31));
    }

    /**
     * Constructor for specifying the minimum and maximum dates that are selectable
     * on this date picker.
     *
     * @param minimum The minimum date that can be chosen on this date picker.
     * @param maximum The maximum date that can be chosen on this date picker.
     */
    public SSDatePicker(SSDate minimum, SSDate maximum) {
        super(
            new ComboBoxMonthSelector(minimum, maximum),
            new RangedCalendarView(minimum, maximum),
            new CalendarModel()
       );
    }

    /**
     * Constructor for specifying the minimum and maximum dates that are selectable
     * on this date picker.
     *
     * @param minimum The minimum date that can be chosen on this date picker.
     * @param maximum The maximum date that can be chosen on this date picker.
     * @param prevMonthUrl The url of the image to be used for the previous month button.
     * @param nextMonthUrl The url of the image to be used for the next month button.
     */
    public SSDatePicker(SSDate minimum, SSDate maximum, String prevMonthUrl, String nextMonthUrl) {
        super(
            new ComboBoxMonthSelector(minimum, maximum, prevMonthUrl, nextMonthUrl),
            new RangedCalendarView(minimum, maximum),
            new CalendarModel()
       );
    }

    /**
     * Setter for the minimum date
     *
     * @param minimumDate The new minimum date
     */
    public void setMinimumDate(SSDate minimum) {
        ((ComboBoxMonthSelector) getMonthSelector()).setMinimumDate(minimum);
        ((RangedCalendarView) getView()).setMinimumDate(minimum);
        refreshAll();
    }

    /**
     * Setter for the maximum date
     *
     * @param minimumDate The new maximum date
     */
    public void setMaximumDate(SSDate maximum) {
        ((ComboBoxMonthSelector) getMonthSelector()).setMaximumDate(maximum);
        ((RangedCalendarView) getView()).setMaximumDate(maximum);
        refreshAll();
    }
}