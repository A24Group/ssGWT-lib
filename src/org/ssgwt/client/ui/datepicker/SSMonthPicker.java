/**
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Class used to create a date picker on which only a month and year can be selected
 * 
 * @author Ruan Naude <nauderuan777@gmail.com>
 * @since 27 Dec 2012
 */
@SuppressWarnings("deprecation")
public class SSMonthPicker extends Composite {
    
    /**
     * The months to use on the month picker
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    private enum DefaultMonths {Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec}
    
    /**
     * The date selected on the month date picker
     */
    private Date selectedDate = null;
    
    /**
     * The current date
     */
    private Date currentDate = new Date();
    
    /**
     * The main panel of the month date picker
     */
    private final FlowPanel mainPanel = new FlowPanel();
    
    /**
     * The panel for the year input
     */
    private final FlowPanel yearPanel = new FlowPanel();
    
    /**
     * The pane for the months input
     */
    private final FlowPanel monthPanel = new FlowPanel();
    
    /**
     * The default resource to use on the month date picker
     */
    private static MonthDatePickerResources DEFAULT_RESOURCES;
    
    /**
     * The list to hold all the month inputs
     */
    private HashMap<Integer, FocusPanel> monthList = new HashMap<Integer, FocusPanel>();
    
    /**
     * The list to hold all the handler registrations of the month inputs
     */
    private HashMap<Integer, HandlerRegistration> monthHandlerRegistration = new HashMap<Integer, HandlerRegistration>();
    
    /**
     * The listbox for the years
     */
    private ListBox yearListBox = new ListBox();
    
    /**
     * The month date picker recourse to use
     */
    private MonthDatePickerResources resources;
    
    /**
     * Default minimum year that will be displayed in the default month date picker's
     * year dropdown box.
     */
    private int minimumYear = 1930;

    /**
     * Default maximum year that will be displayed in the default month date picker's
     * year dropdown box.
     */
    private int maximumYear = currentDate.getYear() + 1900;
    
    /**
     * Default minimum month that will be available in the default month date picker's
     * month combo box.
     */
    private int minimumMonth = 1;

    /**
     * Default maximum month that will be available in the default month date picker's
     * month combo box.
     */
    private int maximumMonth = currentDate.getMonth() + 1;

    /**
     * The popup panel the picker will be displayed in
     */
    private PopupPanel popupPanel;
    
    /**
     * A ClientBundle that provides style for this widget.
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public interface MonthDatePickerResources extends ClientBundle {
        
        /**
         * The style source to be used in this widget
         */
        @Source("MonthDatePicker.css")
        Style monthDatePickerStyle();
    }
    
    /**
     * The Css style recourse items to use in this widget
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public interface Style extends CssResource {

        /**
         * The style for each month block
         * 
         * @return The style for each month block
         */
        String monthBlock();
        
        /**
         * The style for the year block
         * 
         * @return The style for the year block
         */
        String yearBlock();
        
        /**
         * The style for each month label
         * 
         * @return The style for each month label
         */
        String monthLabel();
        
        /**
         * The style for the container with all the months
         * 
         * @return The style for the container with all the months
         */
        String monthPicker();
        
        /**
         * The style for main month date picker panel
         * 
         * @return The style for main month date picker panel
         */
        String mainPanel();
        
        /**
         * The style to use for a enabled month
         * 
         * @return The style to use for a enabled month
         */
        String monthEnabled();

        /**
         * The style to use for a disabled month
         * 
         * @return The style to use for a disabled month
         */
        String monthDisabled();

        /**
         * The style to use for a selected month
         * 
         * @return The style to use for a selected month
         */
        String monthSelected();
    }

    /**
     * Class constructor
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public SSMonthPicker() {
        this(null);
    }
    
    /**
     * Class constructor
     * 
     * @param popupPanel - The popup panel the month picker will be displayed in
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public SSMonthPicker(PopupPanel popupPanel) {
        this(popupPanel, getDefaultResources());
    }
    
    /**
     * Class constructor
     * 
     * @param popupPanel - The popup panel the month date picker will be displayed in
     * @param resources - The recource to use for the month date picker
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public SSMonthPicker(final PopupPanel popupPanel, MonthDatePickerResources resources) {
        this.popupPanel = popupPanel;
        this.resources = resources;
        this.resources.monthDatePickerStyle().ensureInjected();
        initWidget(mainPanel);
        
        //create the month inputs
        int counter = 1;
        for (DefaultMonths month : DefaultMonths.values()) {
            createMonthBlock(month.toString(), counter);
            counter++;
        }
        
        //create the year drop down box and add value change handler
        createYearDropDown();
        ChangeHandler changeHandler = new ChangeHandler() {
            
            /**
             * This will update the months that are available to selected
             * when the value changes
             * 
             * @param event - The event of the value change
             * 
             * @author Ruan Naude <nauderuan777@gmail.com>
             * @since 27 Dec 2012
             */
            @Override
            public void onChange(ChangeEvent event) {
                checkAvailableMonths();
            }
        };
        yearListBox.addChangeHandler(changeHandler);
        this.yearPanel.add(yearListBox);
        
        //add styles to the main panels
        this.yearPanel.addStyleName(resources.monthDatePickerStyle().yearBlock());
        this.monthPanel.addStyleName(resources.monthDatePickerStyle().monthPicker());
        this.mainPanel.addStyleName(resources.monthDatePickerStyle().mainPanel());
        
        //add year and month panels to the main panel
        this.mainPanel.add(yearPanel);
        this.mainPanel.add(monthPanel);
    }
    
    /**
     * This will create the year drop down box.
     * The years will be from the min to max set years.
     * If no min and max year was set then the default will be used (1930 - current year)
     * By default the selected year will be set to the current year.
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void createYearDropDown() {
        //check that the min year is not bigger that the max year
        if (minimumYear < maximumYear) {
            yearListBox.clear();
            int max = maximumYear;
            int counter = minimumYear;
            int currentYear = currentDate.getYear() + 1900;
            int index = 0;
            
            //loop for the min year to max year adding each year
            while(counter <= max) {
                yearListBox.addItem(""+counter);
                if (counter == currentYear) {
                    yearListBox.setSelectedIndex(index);
                }
                index++;
                counter++;
            }
            
            //check available months for the selected year
            checkAvailableMonths();
        }
    }
    
    /**
     * This function will create one of the months block for the month date picker
     * 
     * @param monthName - The name of the month
     * @param monthNumber - The number of the month
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void createMonthBlock(String monthName, int monthNumber) {
        //create block and label for month
        FocusPanel monthBlock = new FocusPanel();
        Label monthLabel = new Label(monthName);
        
        //add styles for the block and label for the month
        monthLabel.addStyleName(resources.monthDatePickerStyle().monthLabel());
        monthBlock.addStyleName(resources.monthDatePickerStyle().monthBlock());
        
        //add click handler to the month block
        addClickHandlerOnMonth(monthBlock, monthNumber);
        
        //add the month block to the month list
        monthList.put(monthNumber, monthBlock);
        
        //add the label to the block and the block to the month panel
        monthBlock.add(monthLabel);
        monthPanel.add(monthBlock);
    }
    
    /**
     * This function will add a click handler to a month block.
     * 
     * @param monthBlock - The panel the month label is in
     * @param monthNumber - The number of the month
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    private void addClickHandlerOnMonth(FocusPanel monthBlock, final int monthNumber) {
        ClickHandler handler = new ClickHandler() {
            
            /**
             * Will get the selected date when a month block is clicked
             * and close the popup if one is present
             * 
             * @param event - The click event
             * 
             * @author Ruan Naude <nauderuan777@gmail.com>
             * @since 27 Dec 2012
             */
            @Override
            public void onClick(ClickEvent event) {
                Date dateSelected = new Date(
                    Integer.parseInt(yearListBox.getItemText(yearListBox.getSelectedIndex())) - 1900,
                    monthNumber,
                    0
                );
                selectedDate = dateSelected;
                if (popupPanel != null) {
                    popupPanel.hide();
                }
            }
        };
        HandlerRegistration clickRegistration = monthBlock.addClickHandler(handler);
        monthHandlerRegistration.put(monthNumber, clickRegistration);
        monthBlock.addStyleName(resources.monthDatePickerStyle().monthEnabled());
    }
    
    /**
     * This will check all the months that is availible for the selected year
     * based on the min and max date.
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void checkAvailableMonths() {
        //remove all click handlers from the months
        for (Map.Entry<Integer, HandlerRegistration> entry : monthHandlerRegistration.entrySet()) {
            entry.getValue().removeHandler();
        }
        monthHandlerRegistration.clear();
        
        //remove all enabled, disabled or selected styles from the months
        for (final Map.Entry<Integer, FocusPanel> entry : monthList.entrySet()) {
            entry.getValue().removeStyleName(resources.monthDatePickerStyle().monthDisabled());
            entry.getValue().removeStyleName(resources.monthDatePickerStyle().monthEnabled());
            entry.getValue().removeStyleName(resources.monthDatePickerStyle().monthSelected());
        }
        
        //check if the year selected is the max or min year and apply styles and click handlers accordingly
        if (Integer.parseInt(yearListBox.getValue(yearListBox.getSelectedIndex())) == maximumYear) {
            for (final Map.Entry<Integer, FocusPanel> entry : monthList.entrySet()) {
                //if month smaller that max month set enabled style and click handler otherwise disabled style
                if (entry.getKey() <= maximumMonth) {
                    addClickHandlerOnMonth(entry.getValue(), entry.getKey());
                } else {
                    entry.getValue().addStyleName(resources.monthDatePickerStyle().monthDisabled());
                }
            }
        } else if (Integer.parseInt(yearListBox.getValue(yearListBox.getSelectedIndex())) == minimumYear) {
            for (final Map.Entry<Integer, FocusPanel> entry : monthList.entrySet()) {
                //if month bigger that min month set enabled style and click handler otherwise disabled style
                if (entry.getKey() >= minimumMonth) {
                    addClickHandlerOnMonth(entry.getValue(), entry.getKey());
                } else {
                    entry.getValue().addStyleName(resources.monthDatePickerStyle().monthDisabled());
                }
            }
        } else {
            //if not min or max year then set all months enabled
            for (final Map.Entry<Integer, FocusPanel> entry : monthList.entrySet()) {
                addClickHandlerOnMonth(entry.getValue(), entry.getKey());
            }
        }
        
        //check if the year is the selected year and set selected style on the selected month.
        if (selectedDate != null && 
                yearListBox.getValue(yearListBox.getSelectedIndex()).equals("" + (selectedDate.getYear() + 1900))) {
            monthList.get(selectedDate.getMonth() + 1).addStyleName(resources.monthDatePickerStyle().monthSelected());
        }
    }
    
    /**
     * This function will check of there is already a default resource to
     * use for the left menu item and if not create a default resource
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     * 
     * @return The default resource for the LeftMenuItem
     */
    private static MonthDatePickerResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(MonthDatePickerResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    /**
     * Sets the month date picker's selected value.
     * 
     * @param date - The new value for this date picker
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public final void setValue(Date date) {
        //set selected date as date passed in.
        selectedDate = date;
        if (date != null) {
            //set the year and month as selected
            String year = "" + (date.getYear() + 1900);
            for (int i = 0; i < yearListBox.getItemCount(); i++) {
                if (yearListBox.getItemText(i).equals(year)) {
                    yearListBox.setSelectedIndex(i);
                }
            }
            checkAvailableMonths();
        }
    }
    
    /**
     * This will set the maximum date the user is allowed to select
     * 
     * @param maxDate - The maximum date the user is allowed to select
     * 
     * @author Ruan Naude<nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void setMaxDate(Date maxDate) {
        maximumYear = maxDate.getYear() + 1900;
        maximumMonth = maxDate.getMonth() + 1;
        createYearDropDown();
    }
    
    /**
     * This will set the minimum date the user is allowed to select
     * 
     * @param minDate - The minimum date the user is allowed to select
     * 
     * @author Ruan Naude<nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void setMinDate(Date minDate) {
        minimumYear = minDate.getYear() + 1900;
        minimumMonth = minDate.getMonth() + 1;
        createYearDropDown();
    }
    
    /**
     * Gets the date the user selected on the month picker
     * 
     * @author Ruan Naude<nauderuan777@gmail.com>
     * @since 27 Dec 2012
     * 
     * @return The selected date
     */
    public Date getValue() {
        return selectedDate;
    }
}
