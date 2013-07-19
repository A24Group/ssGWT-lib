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
 * The time zone settings class
 *
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  16 July 2016
 */
public class TimeZoneSettings {

    /**
     * The instance of the TimeZoneSettings singleton
     */
    private static TimeZoneSettings instance;

    /**
     * The current application time zone
     */
    private TimeZone currentTimeZone = null;

    /**
     * The current time zone offset for the application
     */
    private int currentTimeZoneOffset = 0;

    /**
     * The class constructor
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     */
    private TimeZoneSettings() {
        this.currentTimeZoneOffset = new Date().getTimezoneOffset();
    }

    /**
     * Retrieves an instance of the TimeZoneSettings class
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return An instance of the TimeZoneSettings class
     */
    public static TimeZoneSettings getInstance() {
        if (TimeZoneSettings.instance == null) {
            TimeZoneSettings.instance = new TimeZoneSettings();
        }

        return TimeZoneSettings.instance;
    }

    /**
     * Retrieve the current time zone for the application
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The current time zone for the application
     */
    public TimeZone getCurrentTimeZone() {
        return currentTimeZone;
    }

    /**
     * Sets the current time zone for the application
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @param currentTimeZone - The current time zone for the application
     */
    public void setCurrentTimeZone(TimeZone currentTimeZone) {
        this.currentTimeZone = currentTimeZone;
    }

    /**
     * Retrieve the current time zone offset for the application
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @return The current time zone offset for the application
     */
    public int getCurrentTimeZoneOffset() {
        return currentTimeZoneOffset;
    }

    /**
     * Sets the current time zone offset for the application
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  16 July 2016
     *
     * @param currentTimeZoneOffset - The current time zone offset for the application
     */
    public void setCurrentTimeZoneOffset(int currentTimeZoneOffset) {
        this.currentTimeZoneOffset = currentTimeZoneOffset;
    }

}
