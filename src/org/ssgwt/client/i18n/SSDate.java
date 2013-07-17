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
package org.ssgwt.client.i18n;

import java.util.Date;

import com.google.gwt.i18n.client.TimeZone;

/**
 * The SSDate class that keeps the original timezone offset when created from a string using a DateTimeFormat class in
 * the ssGWT-lib.
 *
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  16 July 2016
 */
public class SSDate extends Date
{

    /**
     * Class constructor
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    public SSDate() {
        super();
    }

    /**
     * Class constructor
     *
     * @param date - the value of the argument to be created
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    public SSDate(long date) {
        super(date);
    }

    /**
     * Class constructor
     *
     * @param year - a year after 1900
     * @param month - a month between 0-11
     * @param date - day of the month between 1-31
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    public SSDate(
        int year,
        int month,
        int date
    ) {
        super(year, month, date);
    }

    /**
     * Class constructor
     *
     * @param year - a year after 1900
     * @param month - a month between 0-11
     * @param date - day of the month between 1-31
     * @param hrs - hours between 0-23
     * @param min - minutes between 0-59
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    public SSDate(
        int year,
        int month,
        int date,
        int hrs,
        int min
    ) {
        super(year, month, date, hrs, min);
    }

    /**
     * Class constructor
     *
     * @param year - a year after 1900
     * @param month - a month between 0-11
     * @param date - day of the month between 1-31
     * @param hrs - hours between 0-23
     * @param min - minutes between 0-59
     * @param sec - seconds between 0-59
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    public SSDate(
        int year,
        int month,
        int date,
        int hrs,
        int min,
        int sec
    ) {
        super(year, month, date, hrs, min, sec);
    }

    /**
     * Creates a date from a string according to the syntax accepted by parse().
     *
     * @param s - String date representation
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    public SSDate(String s) {
        super(s);
    }

    /**
     * The original time zone offset
     */
    private int originalTimeZoneOffset = Integer.MIN_VALUE;

    /**
     * Retrieve the original time zone offset
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The original time zone offset
     */
    public int getOriginalTimeZoneOffset() {
        return originalTimeZoneOffset;
    }

    /**
     * Sets the original time zone offset
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @param originalTimeZoneOffset - The original timezone offset for the date
     */
    public void setOriginalTimeZoneOffset(int originalTimeZoneOffset) {
        this.originalTimeZoneOffset = originalTimeZoneOffset;
    }

    /**
     * Applies the original timezone offset to the date and the converts it to a string
     *
     * @param format - The string date should be in
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The string date
     */
    public String formatOriginalTimezone(String format) {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(format);

        return dateTimeFormat.formatOrginalTimezone(this);
    }

    /**
     * Applies the browsers timezone offset to the date and the converts it to a string
     *
     * @param format - The string date should be in
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The string date
     */
    public String formatBrowserTimezone(String format) {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(format);

        return dateTimeFormat.formatBrowserTimezone(this);
    }

    /**
     * Applies the given timezone offset to the date and the converts it to a string.
     *
     * @param format - The string date should be in.
     * @param timezoneOffset - The timezone offset the date should be converted to.
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The string date
     */
    public String format(String format, int timezoneOffset) {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(format);
        TimeZone timeZone = TimeZone.createTimeZone(timezoneOffset);

        return dateTimeFormat.format(this, timeZone);
    }

    /**
     * Clones the date object and returns the new object.
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return copy of the object
     */
    public SSDate clone() {
        SSDate newDate = new SSDate(this.getYear(), this.getMonth(), this.getDate(), this.getHours(), this.getMinutes(), this.getSeconds());
        newDate.setOriginalTimeZoneOffset(this.getOriginalTimeZoneOffset());
        return newDate;
    }
}
