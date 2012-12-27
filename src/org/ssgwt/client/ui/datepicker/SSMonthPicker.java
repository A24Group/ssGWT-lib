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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.james.mime4j.descriptor.MaximalBodyDescriptor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.sun.java.swing.plaf.windows.resources.windows;

public class SSMonthPicker extends Composite {
    
    private enum DefaultMonths {Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec}
    
    private Date selectedDate = new Date();
    
    private final FlowPanel mainPanel;
    
    private final FlowPanel yearPanel = new FlowPanel();
    
    private final FlowPanel monthPanel = new FlowPanel();
    
    private static MonthDatePickerResources DEFAULT_RESOURCES;
    
    public HashMap<Integer, FocusPanel> monthList = new HashMap<Integer, FocusPanel>();
    
    public HashMap<Integer, HandlerRegistration> monthHandlerRegistration = new HashMap<Integer, HandlerRegistration>();
    
    private ListBox yearListBox = new ListBox();
    
    /**
     */
    private MonthDatePickerResources resources;
    /**
     * A ClientBundle that provides style for this widget.
     * 
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
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public interface Style extends CssResource {

        String monthBlock();
        
        String yearBlock();
        
        String monthLabel();
        
        String monthPicker();
    }
    
    /**
     * Default minimum year that will be displayed in the default date picker's
     * year combo box.
     */
    private int minimumYear = 1970;

    /**
     * Default maximum year that will be displayed in the default date picker's
     * year combo box.
     */
    private int maximumYear = 2020;
    
    /**
     * Default minimum year that will be displayed in the default date picker's
     * year combo box.
     */
    private int minimumMonth = 1;

    /**
     * Default maximum year that will be displayed in the default date picker's
     * year combo box.
     */
    private int maximumMonth = 12;

    private PopupPanel popupPanel;

    public SSMonthPicker(final PopupPanel popupPanel) {
        this.popupPanel = popupPanel;
        this.resources = getDefaultResources();
        this.mainPanel= new FlowPanel();
        this.mainPanel.add(yearPanel);
        this.mainPanel.add(monthPanel);
        this.mainPanel.addStyleName(resources.monthDatePickerStyle().monthPicker());
        
        createYearDropDown();
        
        this.yearPanel.addStyleName(resources.monthDatePickerStyle().yearBlock());
        this.monthPanel.addStyleName(resources.monthDatePickerStyle().monthPicker());
        
        ChangeHandler changeHandler = new ChangeHandler() {
            
            @Override
            public void onChange(ChangeEvent event) {
                
                for (Map.Entry<Integer, HandlerRegistration> entry : monthHandlerRegistration.entrySet()) {
                    entry.getValue().removeHandler();
                }
                monthHandlerRegistration.clear();
                
                if (Integer.parseInt(yearListBox.getValue(yearListBox.getSelectedIndex())) == maximumYear) {
                    for (final Map.Entry<Integer, FocusPanel> entry : monthList.entrySet()) {
                        if (entry.getKey() <= maximumMonth) {
                            addClickHandlerOnMonth(entry.getValue(), entry.getKey());
                        }
                    }
                } else if (Integer.parseInt(yearListBox.getValue(yearListBox.getSelectedIndex())) == minimumYear) {
                    for (final Map.Entry<Integer, FocusPanel> entry : monthList.entrySet()) {
                        if (entry.getKey() >= minimumMonth) {
                            addClickHandlerOnMonth(entry.getValue(), entry.getKey());
                        }
                    }
                } else {
                    for (final Map.Entry<Integer, FocusPanel> entry : monthList.entrySet()) {
                        addClickHandlerOnMonth(entry.getValue(), entry.getKey());
                    }
                }
            }
        };
        yearListBox.addChangeHandler(changeHandler);
        this.yearPanel.add(yearListBox);
        
        this.resources.monthDatePickerStyle().ensureInjected();
        initWidget(mainPanel);
        
        int counter = 1;
        for (DefaultMonths month : DefaultMonths.values()) {
            createMonthBlock(month.toString(), counter);
            counter++;
        }
    }
    
    public void createMonthBlock(String name, int monthNumber) {
        FocusPanel monthBlock = new FocusPanel();
        Label monthLabel = new Label(name);
        
        monthLabel.addStyleName(resources.monthDatePickerStyle().monthLabel());
        monthBlock.add(monthLabel);
        
        monthBlock.addStyleName(resources.monthDatePickerStyle().monthBlock());
        
        addClickHandlerOnMonth(monthBlock, monthNumber);
        
        monthList.put(monthNumber, monthBlock);
        
        monthPanel.add(monthBlock);
    }
    
    private void addClickHandlerOnMonth(FocusPanel monthBlock, final int monthNumber) {
        ClickHandler handler = new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                Date dateSelected = new Date(
                    Integer.parseInt(yearListBox.getItemText(yearListBox.getSelectedIndex())) - 1900,
                    monthNumber,
                    0
                );
                selectedDate = dateSelected;
                popupPanel.hide();
            }
        };
        HandlerRegistration clickRegistration = monthBlock.addClickHandler(handler);
        monthHandlerRegistration.put(monthNumber, clickRegistration);
    }
    
    public void createYearDropDown() {
        if (minimumYear < maximumYear) {
            int max = maximumYear;
            int counter = minimumYear;
            Date date = new Date();
            int currentYear = date.getYear() + 1900;
            int index = 0;
            while(counter <= max) {
                yearListBox.addItem(""+counter);
                if (counter == currentYear) {
                    yearListBox.setSelectedIndex(index);
                }
                index++;
                counter++;
            }
        }
    }
    
    /**
     * This function will check of there is already a default resource to
     * use for the left menu item and if not create a default resource
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
     * Sets the {@link MonthPicker}'s value.
     * 
     * @param newValue the new value for this date picker
     */
    public final void setValue(Date date) {
        String year = "" + date.getYear() + 1900;
        for (int i = 0; i < yearListBox.getItemCount(); i++) {
            if (yearListBox.getItemText(i).equals(year)) {
                yearListBox.setSelectedIndex(i);
            }
        }
        Window.alert("month" + date.getMonth() + 1);
        //monthList.get(date.getMonth() + 1).setStyleName(SELECTED); 
    }
    
    public void setMaxDate(Date maxDate) {
        maximumYear = maxDate.getYear() + 1900;
        maximumMonth = maxDate.getMonth() + 1;
    }
    
    public void setMinDate(Date minDate) {
        minimumYear = minDate.getYear() + 1900;
        minimumMonth = minDate.getMonth() + 1;
    }
    
    public Date getValue() {
        return selectedDate;
    }
}
