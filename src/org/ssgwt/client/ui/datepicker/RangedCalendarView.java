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

import java.util.Date;

import org.ssgwt.client.ui.datepicker.RangedCalendarView.CellGrid.DateCell;

import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;

/**
 * Ranged calendar view for use by a date picker that wants to limit what dates
 * a user can click on.
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since  16 July 2012
 */
@SuppressWarnings(/* Date manipulation required */ { "deprecation", "rawtypes" })
public class RangedCalendarView extends CalendarView {

    /**
     * Cell grid used to display the cells on.
     */
    private CellGrid grid = new CellGrid();

    /**
     * The first day currently being displayed on the calendar.
     */
    private Date firstDisplayed;

    /**
     * The last day.
     */
    private Date lastDisplayed = new Date();

    /**
     * The minimum date allowed to be displayed on this view.
     */
    private Date minimumDate;

    /**
     * The maximum date allowed to be displayed on this view. 
     */
    private Date maximumDate;

    /**
     * Default Constructor
     * 
     * @param minimumDate The minimum date allowed on this view.
     * @param maximumDate The maximum date allowed on this view.
     */
    public RangedCalendarView(Date minimumDate, Date maximumDate) {
        this.minimumDate = minimumDate;
        this.maximumDate = maximumDate;
    }

    /**
     * Adds the provided style name to the cell that represents the provided date.
     * 
     * @param styleName The style name to add to the cell.
     * @param date The date for which we want to add the style.
     */
    public void addStyleToDate(String styleName, Date date) {
        //Make sure the cell is visible
        assert getDatePicker().isDateVisible(date) : "You tried to add style " + styleName + " to " 
                + date + ". The calendar is currently showing " + getFirstDate()
                + " to " + getLastDate();
        getCell(date).addStyleName(styleName);
    }

    /**
     * Getter for the first date being displayed on the view.
     * 
     * @return The first date being displayed.
     */
    public Date getFirstDate() {
        return this.firstDisplayed;
    }

    /**
     * Getter for the last date being displayed on the view.
     * 
     * @return The last date being displayed.
     */
    public Date getLastDate() {
        return this.lastDisplayed;
    }

    /**
     * Returns whether the cell representing the provided date is enabled.
     * 
     * @return Whether the date is enabled.
     */
    public boolean isDateEnabled(Date date) {
        return getCell(date).isEnabled();
    }

    /**
     * Refreshes the view.
     */
    public void refresh() {
        firstDisplayed = getModel().getCurrentFirstDayOfFirstWeek();
        if (firstDisplayed.getDate() == 1) {
            CalendarUtil.addDaysToDate(firstDisplayed, -7);
        }

        lastDisplayed.setTime(firstDisplayed.getTime());

        for (int i = 0 ; i < grid.getNumCells(); i++) {
            if (i != 0) {
                CalendarUtil.addDaysToDate(lastDisplayed, 1);
            }
            DateCell cell = (DateCell) grid.getCell(i);
            cell.update(lastDisplayed);
            
            if (lastDisplayed.getTime() >= minimumDate.getTime() && lastDisplayed.getTime() <= maximumDate.getTime()) {
                cell.setEnabled(true);
            } else {
                cell.setEnabled(false);
            }
            
            if (i >= (grid.getNumCells() - CalendarModel.DAYS_IN_WEEK)) {
                cell.addStyleName(css().day("LastRow"));
            }
            if (i % CalendarModel.DAYS_IN_WEEK == 0) {
                cell.addStyleName(css().day("FirstCol"));
            } else if (i % CalendarModel.DAYS_IN_WEEK == CalendarModel.DAYS_IN_WEEK - 1) {
                cell.addStyleName(css().day("LastCol"));
            }
        }
    }

    /**
     * Removes the provided style name from the cell representing the
     * provided date
     * 
     * @param styleName The style name to remove
     * @param date The date of the cell we want to remove it from.
     */
    public void removeStyleFromDate(String styleName, Date date) {
        getCell(date).removeStyleName(styleName);
    }

    /**
     * Sets whether the cell representing the provided date object should be
     * enabled or disabled.
     * 
     * @param enabled Whether the cell should be enabled.
     * @param date The date of the cell we want to enable/disable.
     */
    public void setEnabledOnDate(boolean enabled, Date date) {
        getCell(date).setEnabled(enabled);
    }

    /**
     * Sets up the view.
     */
    protected void setup() {
        //Preparation
        CellFormatter formatter = grid.getCellFormatter();
        int weekendStartColumn = -1;
        int weekendEndColumn = -1;

        //Set up the day labels.
        for (int i = 0 ; i < CalendarModel.DAYS_IN_WEEK; i++) {
            int shift = CalendarUtil.getStartingDayOfWeek();
            int dayIdx = i + shift;
            if (dayIdx >= CalendarModel.DAYS_IN_WEEK) {
                dayIdx -= CalendarModel.DAYS_IN_WEEK;
            }
            grid.setText(0, i, getModel().formatDayOfWeek(dayIdx));

            if (CalendarUtil.isWeekend(dayIdx)) {
                formatter.setStyleName(0, i, css().weekendLabel());
                if (weekendStartColumn == -1) {
                    weekendStartColumn = i;
                } else {
                    weekendEndColumn = i;
                }
            } else {
                formatter.setStyleName(0, i, css().weekdayLabel());
            }
        }
        
        // Set up the calendar grid.
        for (int row = 1; row <= CalendarModel.WEEKS_IN_MONTH; row++) {
            for (int column = 0; column < CalendarModel.DAYS_IN_WEEK; column++) {
                // set up formatter.
                Element e = formatter.getElement(row, column);
                grid.new DateCell(e, column == weekendStartColumn || column == weekendEndColumn);
            }
        }
        initWidget(grid);
        grid.setStyleName(css().days());
    }

    /**
     * Retrieves the date cell for a provided date.
     * 
     * @param date The date of the date cell we want.
     * @return The DateCell containing the provided date.
     */
    private DateCell getCell(Date date) {
        int index = CalendarUtil.getDaysBetween(firstDisplayed, date);
        assert (index >= 0);

        DateCell cell = (DateCell) grid.getCell(index);
        if (((Date) cell.getValue()).getDate() != date.getDate()) {
            throw new IllegalStateException(date + " cannot be associated with cell " + cell + " as it has date " + ((Date) cell.getValue()));
        }
        return cell;
    }
    
    /**
     * Cell grid.
     */
    // Javac bug requires that date be fully specified here.
    class CellGrid extends CellGridImpl<java.util.Date> {

        /**
         * Default constructor
         */
        public CellGrid() {
            resize(CalendarModel.WEEKS_IN_MONTH + 1, CalendarModel.DAYS_IN_WEEK);
        }

        /**
         * A cell representing a date.
         */
        class DateCell extends Cell {

            /**
             * The cell style.
             */
            private String cellStyle;

            /**
             * The date style.
             */
            private String dateStyle;

            /**
             * Constructor
             * 
             * @param td The table cell in which this will be.
             * @param isWeekend Whether the cell represents a day during the weekend.
             */
            public DateCell(Element td, boolean isWeekend) {
                super(td, new Date());
                //td.appendChild()
                cellStyle = css().day();
                if (isWeekend) {
                    cellStyle += " " + css().dayIsWeekend(); 
                } else {
                    cellStyle += " " + css().dayIsWeek();
                }
            }

            /**
             * Adds a style name to the date style.
             */
            public void addStyleName(String styleName) {
                if (dateStyle.indexOf(" " + styleName + " ") == -1) {
                    dateStyle += styleName + " ";
                }
                updateStyle();
            }

            /**
             * Checks whether the current value is in the current month.
             * 
             * @return Whether the current value is in the current month.
             */
            public boolean isFiller() { 
                return !getModel().isInCurrentMonth((Date) getValue());
            }

            /**
             * Called when a cell is highlighted.
             * 
             * @param highlighted Whether the cell is highlighted or not.
             */
            protected void onHighlighted(boolean highlighted) {
                setHighlightedDate((Date) getValue());
                updateStyle();
            }

            /**
             * Called when a cell is selected.
             * 
             * @param selected Whether the cell is selected or not.
             */
            protected void onSelected(boolean selected) {
                if (selected) {
                    getDatePicker().setValue((Date) getValue(), true);
                    if (isFiller()) {
                        getDatePicker().setCurrentMonth((Date) getValue());
                    }
                }
                updateStyle();
            }

            /**
             * Removes a style from the cell.
             * 
             * @param styleName The style name to remove.
             */
            public void removeStyleName(String styleName) {
                dateStyle = dateStyle.replace(" " + styleName + " ", " ");
                updateStyle();
            }

            /**
             * Updates the current date on the view.
             * 
             * @param current The current date.
             */
            public void update(Date current) {
                setEnabled(true);
                ((Date) getValue()).setTime(current.getTime());
                String value = getModel().formatDayOfMonth((Date) getValue());
                setText(value);
                dateStyle = cellStyle;
                if (isFiller()) {
                    dateStyle += " " + css().dayIsFiller(); 
                } else {
                    String extraStyle = getDatePicker().getStyleOfDate(current);
                    if (extraStyle != null) {
                        dateStyle += " " + extraStyle;
                    }
                }
                dateStyle += " ";
                updateStyle();
            }

            /**
             * Sets the inner text of the element.
             * 
             * @param value The new value
             */
            private void setText(String value) {
                Element divChild;
                if (getElement().getChild(0).getNodeType() == Node.ELEMENT_NODE) {
                    divChild = (Element) getElement().getChild(0);
                } else {
                    divChild = DOM.createDiv();
                    divChild.addClassName(css().wrap("DayValue"));
                    getElement().appendChild(divChild);
                }
                DOM.setInnerText(getElement(), "");
                DOM.setInnerText(divChild, value);
                DOM.appendChild(getElement(), divChild);
            }

            /**
             * Updates the style of the cell based on its' current state.
             */
            protected void updateStyle() {
                String accum = dateStyle;

                if (isHighlighted()) {
                    accum += " " + css().dayIsHighlighted();

                    if (isSelected()) {
                        accum += " " + css().dayIsValueAndHighlighted();
                    }
                }

                if (!isEnabled()) {
                    accum += " " + css().dayIsDisabled();
                }
                setStyleName(accum);
            }
            
        }

        /**
         * When the cell is selected
         */
        protected void onSelected(Cell lastSelected, Cell cell) {
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
    }
}
