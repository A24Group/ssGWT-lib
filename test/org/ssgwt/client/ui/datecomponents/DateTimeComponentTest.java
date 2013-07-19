package org.ssgwt.client.ui.datecomponents;

import org.ssgwt.client.ui.datecomponents.DateTimeComponent;
import org.ssgwt.client.ui.datepicker.DateBox;
import org.ssgwt.client.ui.datepicker.DateBox.DefaultFormat;
import org.ssgwt.client.ui.datepicker.SSDatePicker;
import org.ssgwt.client.ui.form.spinner.ChangeEvent;
import org.ssgwt.client.ui.form.spinner.ChangeHandler;
import org.ssgwt.client.ui.form.spinner.TimePicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Test cases for the DateTimeComponent component
 * 
 * @author Alec Erasmus <alec.erasmus@a24group.com
 * @since  16 May 2013
 */
public class DateTimeComponentTest extends GWTTestCase {
    
    /**
     * Retrieves the module name
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    @Override
    public String getModuleName() {
        return "org.ssgwt.ssGwt";
    }
    
    /**
     * Test that dateToMilliseconds return the correct number of milliseconds for a date
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testDateToMilliseconds() {
        long value = DateTimeComponent.dateToMilliseconds(4, 5, 60, 30);
        assertEquals("The value returned by the function was not as expected ", value, 367230000);
    }
    
    /**
     * Test that the correct values is set for a given date
     * Test the set start date
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testsetStartDate() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        dateTimeComponent.setStartDate(new Date(2013, 5, 15));
        assertEquals("The start Date was not as expected",  new Date(2013, 5, 15), dateTimeComponent.getStartDate());
        assertEquals("The end Date was not as expected", new Date(2013, 5, 15), dateTimeComponent.getEndDate());
    }
    
    /**
     * Test that the correct values is set for a given date
     * Test the set End date
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testsetEndDate() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        Date expectedDate = new Date(2013, 5, 16);
        expectedDate.setMinutes(30);
        
        dateTimeComponent.setEndDate(expectedDate);
        assertEquals("The start Date was not as expected", new Date(2013, 5, 15), dateTimeComponent.getStartDate());
        assertEquals("The end Date was not as expected", expectedDate, dateTimeComponent.getEndDate());
    }
    
    /**
     * Test that the getRestDate get the correct date
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testGetRestDate() {
        Date defaultSelectedDate = new Date(2014, 6, 15);
        defaultSelectedDate.setHours(12);
        defaultSelectedDate.setMinutes(30);
        
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            defaultSelectedDate, 
            0, 
            86400000
        );
        
        Date restedDate = dateTimeComponent.getRestDate(new Date(2013, 5, 15));
        
        Date expectedDate = new Date(2013, 5, 15);
        expectedDate.setHours(12);
        expectedDate.setMinutes(30);
        
        assertEquals("The reset Date was not as expected", expectedDate, restedDate);
    }
    
    /**
     * Test that the setEnabled set the isEnabled flage to true and that the 
     * isEnabled flag is true by default
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testSetEnabled() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        assertTrue("The isEnabled was expectde to be true by default", dateTimeComponent.isEnabled());
        dateTimeComponent.setEnabled(false);
        assertFalse("The isEnabled was expectde to be false", dateTimeComponent.isEnabled());
        dateTimeComponent.setEnabled(true);
        assertTrue("The isEnabled was expectde to be true", dateTimeComponent.isEnabled());
    }
    
    /**
     * Test that the getShiftTimeDiff return the correct date diff
     * Expect a diff of 0
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testGetShiftTimeDiffZero() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        Date startDate = new Date(2013, 5, 16);
        Date endDate = new Date(2013, 5, 16);
        String expected = "( 0h )";
        
        String result = dateTimeComponent.getShiftTimeDiff(startDate, endDate);
        assertEquals("The time diff return was not as expected", expected, result);
    }
    
    /**
     * Test that the getShiftTimeDiff return the correct date diff
     * Expect a diff of 24
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testGetShiftTimeDiff24() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        Date startDate = new Date(2013, 5, 16);
        Date endDate = new Date(2013, 5, 17);
        String expected = "( 24h )";
        
        String result = dateTimeComponent.getShiftTimeDiff(startDate, endDate);
        assertEquals("The time diff return was not as expected", expected, result);
    }
    
    /**
     * Test that the getShiftTimeDiff return the correct date diff
     * Expect a diff of err for reversed date
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testGetShiftTimeDiffReversed() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        Date startDate = new Date(2013, 5, 18);
        Date endDate = new Date(2013, 5, 17);
        String expected = "( -err )";
        
        String result = dateTimeComponent.getShiftTimeDiff(startDate, endDate);
        assertEquals("The time diff return was not as expected", expected, result);
    }
    
    /**
     * Test that the getShiftTimeDiff return the correct date diff
     * Expect a diff of 2.25
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testGetShiftTimeDiff() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        Date startDate = new Date(2013, 5, 18);
        Date endDate = new Date(2013, 5, 18);
        endDate.setHours(2);
        endDate.setMinutes(15);
        String expected = "( 2.25h )";
        
        String result = dateTimeComponent.getShiftTimeDiff(startDate, endDate);
        assertEquals("The time diff return was not as expected", expected, result);
    }
    
    /**
     * Test that the getShiftTimeDiff return the correct date diff
     * Expect a diff of 24 hours if the date is out wil one min
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com
     * @since  16 May 2013
     */
    public void testGetShiftTimeDiffMinOut() {
        DateTimeComponent dateTimeComponent = new DateTimeComponent(
            new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1),
            new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31),
            new Date(2013, 5, 15), 
            0, 
            86400000
        );
        
        Date startDate = new Date(2013, 5, 18);
        startDate.setHours(15);
        startDate.setMinutes(30);
        Date endDate = new Date(2013, 5, 19);
        endDate.setHours(15);
        endDate.setMinutes(29);
        String expected = "( 24h )";
        
        String result = dateTimeComponent.getShiftTimeDiff(startDate, endDate);
        assertEquals("The time diff return was not as expected", expected, result);
    }
}
