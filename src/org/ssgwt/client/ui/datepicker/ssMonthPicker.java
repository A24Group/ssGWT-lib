package org.ssgwt.client.ui.datepicker;

import java.util.Date;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class ssMonthPicker extends Composite implements ClickHandler{
    
    private enum DefaultMonths {Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec}
    
    private final FlowPanel mainPanel;
    
    private final FlowPanel yearPanel = new FlowPanel();
    
    private final FlowPanel monthPanel = new FlowPanel();
    
    private static MonthDatePickerResources DEFAULT_RESOURCES;
    
    public HashMap<FocusPanel, String> monthList = new HashMap<FocusPanel, String>();
    
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
    public static final int DEFAULT_MINIMUM_YEAR = 1970; 

    /**
     * Default maximum year that will be displayed in the default date picker's
     * year combo box.
     */
    public static final int DEFAULT_MAXIMUM_YEAR = 2020;

    public ssMonthPicker() {
        this.resources = getDefaultResources();
        this.mainPanel= new FlowPanel();
        this.mainPanel.add(yearPanel);
        this.mainPanel.add(monthPanel);
        this.mainPanel.addStyleName(resources.monthDatePickerStyle().monthPicker());
        
        createYearDropDown();
        
        this.yearPanel.addStyleName(resources.monthDatePickerStyle().yearBlock());
        this.monthPanel.addStyleName(resources.monthDatePickerStyle().monthPicker());
        
        this.yearPanel.add(yearListBox);
        
        this.resources.monthDatePickerStyle().ensureInjected();
        initWidget(mainPanel);
        
        for (DefaultMonths month : DefaultMonths.values()) {
            createMonthBlock(month.toString());
        }
    }
    
    public void createMonthBlock(String name) {
        FocusPanel monthBlock = new FocusPanel();
        Label monthLabel = new Label(name);
        monthLabel.addStyleName(resources.monthDatePickerStyle().monthLabel());
        monthBlock.add(monthLabel);
        monthList.put(monthBlock, name);
        monthBlock.addStyleName(resources.monthDatePickerStyle().monthBlock());
        monthBlock.addClickHandler(this);
        
        monthPanel.add(monthBlock);
    }
    
    public void createYearDropDown() {
//        int x = 1730;
//        Date date = new Date();
//        int currerntYear = Integer.parseInt(DateTimeFormat.getFormat("yyyy").format(date));
//        while (x <= currerntYear) {
////            yearListBox.addItem(x + "", x + "");
//        }
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
     * Sets the {@link DatePicker}'s value.
     * 
     * @param newValue the new value for this date picker
     * @param fireEvents should events be fired.
     */
    public final void setValue(Date newValue, boolean fireEvents) {
//        Date oldValue = value;
//
//        if (oldValue != null) {
//            removeStyleFromDates(css().dayIsValue(), oldValue);
//        }
//
//        value = CalendarUtil.copyDate(newValue);
//        if (value != null) {
//            addStyleToDates(css().dayIsValue(), value);
//        }
//        if (fireEvents) {
//            DateChangeEvent.fireIfNotEqualDates(this, oldValue, newValue);
//        }
    }
    
    @Override
    public void onClick(ClickEvent event) {
        System.out.println(monthList.get(event.getSource()));
    }
}
