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
     * The original time zone offset
     */
    private int timeZoneOffset = 0;

    /**
     * The time zone for the date
     */
    private TimeZone timeZone;

    /**
     * Class constructor
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    public SSDate() {
        super();
        this.timeZoneOffset = super.getTimezoneOffset();
        TimeZoneSettings tzs = TimeZoneSettings.getInstance();
        if (tzs.getCurrentTimeZone() != null) {
            setTimeZone(tzs.getCurrentTimeZone());
        } else {
            setTimezoneOffset(tzs.getCurrentTimeZoneOffset());
        }
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
        TimeZoneSettings tzs = TimeZoneSettings.getInstance();
        if (tzs.getCurrentTimeZone() != null) {
            this.timeZone = tzs.getCurrentTimeZone();
            this.timeZoneOffset = tzs.getCurrentTimeZone().getOffset(this);
        } else {
            this.timeZoneOffset = tzs.getCurrentTimeZoneOffset();
        }
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
        TimeZoneSettings tzs = TimeZoneSettings.getInstance();
        if (tzs.getCurrentTimeZone() != null) {
            this.timeZone = tzs.getCurrentTimeZone();
            this.timeZoneOffset = tzs.getCurrentTimeZone().getOffset(this);
        } else {
            this.timeZoneOffset = tzs.getCurrentTimeZoneOffset();
        }
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
        TimeZoneSettings tzs = TimeZoneSettings.getInstance();
        if (tzs.getCurrentTimeZone() != null) {
            this.timeZone = tzs.getCurrentTimeZone();
            this.timeZoneOffset = tzs.getCurrentTimeZone().getOffset(this);
        } else {
            this.timeZoneOffset = tzs.getCurrentTimeZoneOffset();
        }
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
        TimeZoneSettings tzs = TimeZoneSettings.getInstance();
        if (tzs.getCurrentTimeZone() != null) {
            this.timeZone = tzs.getCurrentTimeZone();
            this.timeZoneOffset = tzs.getCurrentTimeZone().getOffset(this);
        } else {
            this.timeZoneOffset = tzs.getCurrentTimeZoneOffset();
        }
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
        TimeZoneSettings tzs = TimeZoneSettings.getInstance();
        if (tzs.getCurrentTimeZone() != null) {
            this.timeZone = tzs.getCurrentTimeZone();
            this.timeZoneOffset = tzs.getCurrentTimeZone().getOffset(this);
        } else {
            this.timeZoneOffset = tzs.getCurrentTimeZoneOffset();
        }
    }

    /**
     * Sets the time using a time stamp. And ensures the time zone offset is correct
     *
     * @param time - The unix timestamp
     */
    public void setTime(long time) {
        super.setTime(time);
        if (this.timeZone != null) {
            this.timeZoneOffset = this.timeZone.getOffset(this);
        }
    }


    /**
     * Retrieves the time zone of the date
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The timezone of the date
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the time zone of the date
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @param timeZone - The time zone of the date
     */
    public void setTimeZone(TimeZone timeZone) {
        setTimezoneOffset(timeZone.getOffset(this));
        this.timeZone = timeZone;
    }

    /**
     * Retrieve the original time zone offset
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The original time zone offset
     */
    public int getTimezoneOffset() {
        return timeZoneOffset;
    }

    /**
     * Sets the original time zone offset
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @param timeZoneOffset - The original timezone offset for the date
     */
    public void setTimezoneOffset(int timeZoneOffset) {
        this.timeZone = null;
        this.setTime(this.getTime() + (this.timeZoneOffset - timeZoneOffset) * 60 * 1000);
        this.timeZoneOffset = timeZoneOffset;
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
    public String format(String format) {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(format);

        return dateTimeFormat.format(this);
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
        newDate.setTimezoneOffset(this.getTimezoneOffset());
        return newDate;
    }

    /**
     * Creates string representation of the date
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return String representation of the date
     */
    public String toString() {
        return DateTimeFormat.getFormat("dd MMMM yyyy HH:mm:ss ZZ").format(this, null);
    }
}
