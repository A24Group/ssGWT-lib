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

import java.util.ArrayList;
import java.util.Date;

import org.ssgwt.client.ui.FocusImage;
import org.ssgwt.client.ui.event.FocusImageClickEvent;
import org.ssgwt.client.ui.event.IFocusImageClickEventHandler;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;

/**
 * Month selector with combo boxes for indicating/selecting the month and year.
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since  16 July 2012
 */
@SuppressWarnings(/* Date manipulation required */ { "deprecation" })
public class ComboBoxMonthSelector extends MonthSelector {

    /**
     * Focus image to be used for the back button.
     */
    private FocusImage backMonth;

    /**
     * Focus image to be used for the next button.
     */
    private FocusImage addMonth;

    /**
     * List box for containing the months.
     */
    private ListBox monthListBox;

    /**
     * List box for containing the years.
     */
    private ListBox yearListBox;

    /**
     * Grid to be used for laying out the header.
     */
    private Grid grid;

    /**
     * The minimum date that can be selected.
     */
    private Date minimumDate;

    /**
     * The maximum date that can be selected.
     */
    private Date maximumDate;

    /**
     * The offset value of the months combo box, this allows us to easily determine
     * the index of a month in a year when we do not have all months of the year
     * in the combo box.
     */
    private int monthOffset = 0;

    /**
     * The URL of the previous month's icon
     */
    private String previousMonthUrl;

    /**
     * The URL of the next month's icon
     */
    private String nextMonthUrl;

    /**
     * Default constructor
     * 
     * @param minimumDate The minimum date that can be chosen on this month selector.
     * @param maximumDate The maximum date that can be chosen on this month selector.
     */
    public ComboBoxMonthSelector(Date minimumDate, Date maximumDate) {
        this(minimumDate, maximumDate, "images/datepicker/prev_month.png", "images/datepicker/next_month.png");
    }

    /**
     * Constructor with focus image urls.
     * 
     * @param minimumDate The minimum date that can be chosen on this month selector.
     * @param maximumDate The maximum date that can be chosen on this month selector.
     * @param prevMonthUrl The url of the previous month image string.
     * @param nextMonthUrl The url of the next month image.
     */
    public ComboBoxMonthSelector(Date minimumDate, Date maximumDate, String prevMonthUrl, String nextMonthUrl) {
        this.minimumDate = minimumDate;
        this.maximumDate = maximumDate;
        this.previousMonthUrl = prevMonthUrl;
        this.nextMonthUrl = nextMonthUrl;
    }

    /**
     * Refreshes the month selector to make sure it displays the information
     * for the currently selected month.
     */
    protected void refresh() {
        populateMonthListBox();
        setListBoxesValues();
    }

    /**
     * Sets up the component.
     */
    protected void setup() {
        monthListBox = new ListBox();
        yearListBox = new ListBox();

        // populate the list boxes
        populateYearListBox();
        
        monthListBox.setStyleName("divNavMonths");

        backMonth = new FocusImage(19, 18, previousMonthUrl);
        backMonth.addFocusImageClickEventHandler(new IFocusImageClickEventHandler() {
            public void onFocusImageClickEvent(FocusImageClickEvent event) {
                Date currentMonth = getModel().getCurrentMonth();
                //Only allow going back if we're not already on the minimum.
                if (currentMonth.getYear() > minimumDate.getYear() || currentMonth.getMonth() > minimumDate.getMonth()) {
                    addMonths(-1);
                }
            }
        });

        addMonth = new FocusImage(19, 18, nextMonthUrl);
        addMonth.addFocusImageClickEventHandler(new IFocusImageClickEventHandler() {
            public void onFocusImageClickEvent(FocusImageClickEvent event) {
                Date currentMonth = getModel().getCurrentMonth();
                if (currentMonth.getYear() < maximumDate.getYear() || currentMonth.getMonth() < maximumDate.getMonth()) {
                    addMonths(+1);
                }
            }
        });
        
        // Add a handler to handle drop box events
        monthListBox.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                addMonths(monthListBox.getSelectedIndex() + monthOffset - getModel().getCurrentMonth().getMonth());
            }
        });

        // Add a handler to handle drop box events
        yearListBox.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                //Get a copy of the current month, we do not want the reference as when we do calculations on it we don't want it to update.
                Date currentMonth = (Date) getModel().getCurrentMonth().clone();
                Date targetMonth = (Date) currentMonth.clone();
                //Set the target date to the newly selected year.
                targetMonth.setYear(Integer.parseInt(yearListBox.getItemText(yearListBox.getSelectedIndex())) - 1900);

                //Work out where the target date is in relation to the minimum and maximum.
                int daysMin = CalendarUtil.getDaysBetween(minimumDate, targetMonth);
                int daysMax = CalendarUtil.getDaysBetween(targetMonth, maximumDate);
                //If the target is before or after the minimum/maximum date we make it the minimum or maximum date.
                if (daysMin < 0 && targetMonth.getMonth() != minimumDate.getMonth()) {
                    CalendarUtil.addDaysToDate(targetMonth, -1 * daysMin);
                } else if (daysMax < 0 && targetMonth.getMonth() != maximumDate.getMonth()) {
                    CalendarUtil.addDaysToDate(targetMonth, daysMax);
                }

                //Now work out which direction we need to move
                int iDiff = 0;
                int iDirection = 0;
                if (targetMonth.getTime() < currentMonth.getTime()) {
                    iDirection = -1;
                } else if (targetMonth.getTime() > currentMonth.getTime()) {
                    iDirection = 1;
                }

                //move in the determined direction
                while (currentMonth.getYear() != targetMonth.getYear() || currentMonth.getMonth() != targetMonth.getMonth()) {
                    CalendarUtil.addMonthsToDate(currentMonth, iDirection * 1);
                    iDiff += iDirection;
                }

                //Add the months
                addMonths(iDiff);
            }
        });

        // Set up grid.
        // grid = new Grid(3, 6);
        grid = new Grid(1, 4);
        grid.setWidget(0, 0, backMonth);
        grid.setWidget(0, 3, addMonth);
        grid.setWidget(0, 1, monthListBox);
        grid.setWidget(0, 2, yearListBox);
        grid.setStyleName("tblNav");
        LayoutPanel navPanel = new LayoutPanel();
        navPanel.add(grid);
        navPanel.setStyleName("comboBoxMonthSelector");

        CellFormatter formatter = grid.getCellFormatter();
        formatter.setStyleName(0, 0, "tdNavLeft");
        formatter.setStyleName(0, 1, "tdNavMiddle");
        formatter.setStyleName(0, 3, "tdNavRight");

        initWidget(navPanel);
    }
    
    /**
     * Populates the year list box based on the minimum and maximum dates allowed
     * for this month selector.
     */
    protected void populateYearListBox() {
        int iMin = minimumDate.getYear() + 1900;
        int iMax = new Date(maximumDate.getTime()).getYear() + 1900;
        
        for (; iMin <= iMax; iMin++) {
            yearListBox.addItem("" + iMin);
        }
    }
    
    /**
     * Populates the month list box based on thre minimum and maximum dates allowed
     * for this month selector.
     */
    protected void populateMonthListBox() {
        Date currentMonth = getModel().getCurrentMonth();
        String[] usableMonths = getMonthsForYearInPeriod(currentMonth, minimumDate, maximumDate);
        monthListBox.clear();
        for (String month : usableMonths) {
            monthListBox.addItem(month);
        }
        monthOffset = getMonthsOffset(currentMonth, minimumDate, maximumDate);
    }
    
    /**
     * Retrieves a string array of month names that fall into the provided date period
     * for a specific year.
     * 
     * @param year The date object indicating the year for which we want the names.
     * @param minimum The minimum date
     * @param maximum The maximum date
     * 
     * @return String array of month names.
     */
    private String[] getMonthsForYearInPeriod(Date year, Date minimum, Date maximum) {
        String[] monthNames = LocaleInfo.getCurrentLocale().getDateTimeFormatInfo().monthsShort();
        ArrayList<String> months = new ArrayList<String>();
        Date minDate = (Date) minimum.clone();
        while (minDate.getTime() <= maximum.getTime()) {
            if (minDate.getYear() == year.getYear()) {
                months.add(monthNames[ minDate.getMonth() ]);
            }
            CalendarUtil.addMonthsToDate(minDate, 1);
        }
            
        return months.toArray(new String[]{});
    }
    
    /**
     * Retrieves the offset for the selected year for the given minimnum and
     * maximum dates.
     * 
     * @param year The year for which we want the offset.
     * @param minimum The minimum date.
     * @param maximum The maximum date.
     * 
     * @return The amount of months that we're offset by.
     */
    private int getMonthsOffset(Date year, Date minimum, Date maximum) {
        if (year.getYear() != minimum.getYear()) {
            return 0;
        } else {
            return minimum.getMonth();
        }
    }

    /**
     * Sets the correct selected indexes for the month and year combo boxes.
     */
    protected void setListBoxesValues() {
        Date currentSelectedDate = getModel().getCurrentMonth();

        try {
            yearListBox.setSelectedIndex(Math.abs(minimumDate.getYear() - currentSelectedDate.getYear()));
            if (yearListBox.getSelectedIndex() == 0) {
                monthListBox.setSelectedIndex(currentSelectedDate.getMonth() - minimumDate.getMonth());
            } else {
                monthListBox.setSelectedIndex(currentSelectedDate.getMonth());
            }
        } catch (Exception e) {
            //Catching exception for when the year list box throws an exception when attempting to set the selected index before it's ready.
        }
    }

    /**
     * Getter for the current Minimum date.
     * 
     * @return The minimum date
     */
    public Date getMinimumDate() {
        return minimumDate;
    }

    /**
     * Setter for the minimum date
     * 
     * @param minimumDate The new minimum date
     */
    public void setMinimumDate(Date minimumDate) {
        this.minimumDate = minimumDate;
        yearListBox.clear();
        populateYearListBox();
    }

    /**
     * Getter for the current maximum date.
     * 
     * @return The maximum date
     */
    public Date getMaximumDate() {
        return maximumDate;
    }

    /**
     * Setter for the maximum date
     * 
     * @param minimumDate The new maximum date
     */
    public void setMaximumDate(Date maximumDate) {
        this.maximumDate = maximumDate;
        yearListBox.clear();
        populateYearListBox();
    }
}
