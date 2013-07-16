package org.ssgwt.client.ui.datagrid.filter;

import org.apache.catalina.deploy.FilterDef;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Test cases for the DateFilter component
 * 
 * @author Lodewyk Duminy <lodewyk.duminy@a24group.com
 * @since  15 Aug 2012
 */
public class DateFilterTest extends GWTTestCase {

    /**
     * Retrieves the module name
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com
     * @since  15 Aug 2012
     */
    @Override
    public String getModuleName() {
        return "org.ssgwt.ssGwt";
    }
    
    /**
     * Tests that the clearFields function changes the check box to an unchecked state,
     * that the filter list's selected index gets set to 0, and that the dateboxes get set to
     * null.
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  15 Aug 2012
     */
    public void testClearFields() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        dateFilter.getCheckBox().setValue(true);
        dateFilter.getFilterList().setSelectedIndex(5);
        Date date = new Date();
        dateFilter.getFromDateBox().setValue(date);
        dateFilter.getToDateBox().setValue(date);
        
        dateFilter.clearFields();
        
        assertFalse(
            "The value of the checkbox was expected to be false after the clearFields function was called",
            dateFilter.getCheckBox().getValue()
        );
        assertEquals(
            "The filter list's selected index was expected to be 0 after the clearFields function was called", 
            0,
            dateFilter.getFilterList().getSelectedIndex()
        );
        assertNull(
            "The date from datebox was expected to be empty after the clearFields function was called", 
            dateFilter.getFromDateBox().getValue()
        );
        assertNull(
            "The date to datebox was expected to be empty after the clearFields function was called", 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the updateFieldDate function works. This is tested by calling the setCriteria function,
     * which eventually calls the updateFieldData function.
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  15 Aug 2012
     */
    public void testUpdateFieldData() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        DateFilter.DateFilterCriteria filterCriteria = new DateFilter.DateFilterCriteria();
        
        Date from = new Date();
        from = getDateWithoutMilliseconds(from);
        
        Date to = new Date();
        to = getDateWithoutMilliseconds(to);
        
        from.setDate(from.getDate() - 2);
        
        filterCriteria.setFindEmptyEntriesOnly(true);
        filterCriteria.setFromDate(from);
        filterCriteria.setToDate(to);
        
        dateFilter.setCriteria(filterCriteria);
        
        assertTrue("Expected the checkbox to be checked", dateFilter.getCheckBox().getValue());
        assertEquals("Expected the from date to be set to 2 days ago", from, dateFilter.getFromDateBox().getValue());
        assertEquals("Expected the to date to be set to today", to, dateFilter.getToDateBox().getValue());
        
        Date newFrom = new Date();
        newFrom = getDateWithoutMilliseconds(newFrom);
        newFrom.setDate(newFrom.getDate() - 3);
        
        Date newTo = new Date();
        newTo = getDateWithoutMilliseconds(newTo);
        newTo.setDate(newTo.getDate() - 1);
        
        DateFilter.DateFilterCriteria filterCriteria2 = new DateFilter.DateFilterCriteria();
        filterCriteria2.setFindEmptyEntriesOnly(false);
        filterCriteria2.setFromDate(newFrom);
        filterCriteria2.setToDate(newTo);
        
        dateFilter.setCriteria(filterCriteria2);
        
        assertFalse("Expected the checkbox to not be checked", dateFilter.getCheckBox().getValue());
        assertEquals("Expected the from date to be set to 3 days ago", newFrom, dateFilter.getFromDateBox().getValue());
        assertEquals("Expected the to date to be set to yesterday", newTo, dateFilter.getToDateBox().getValue());
    }
    
    /**
     * Test that the setCriteriaObjectEmpty function sets the TextFilter Criteria to it's default state
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  15 Aug 2012
     */
    public void testSetCriteriaObjectEmpty() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date date = new Date();
        date = getDateWithoutMilliseconds(date);
        
        DateFilter.DateFilterCriteria filterCriteria = new DateFilter.DateFilterCriteria();
        filterCriteria.setFindEmptyEntriesOnly(true);
        filterCriteria.setFromDate(date);
        filterCriteria.setToDate(date);
        
        dateFilter.setCriteria(filterCriteria);
        
        dateFilter.setCriteriaObjectEmpty();
        
        assertFalse("The checkbox was not supposed to be checked", filterCriteria.isFindEmptyEntriesOnly());
        assertNull("The from date was expected to be empty", filterCriteria.getFromDate());
        assertNull("The to date was expected to be empty", filterCriteria.getToDate());
    }
    
    /**
     * Test that the updateCriteriaObject function updates the DateFilter Criteria with the date in the input fields
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  15 Aug 2012
     */
    public void testUpdateCriteriaObject() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date from = new Date();
        from = getDateWithoutMilliseconds(from);
        from.setDate(from.getDate() - 2);
        
        Date to = new Date();
        to = getDateWithoutMilliseconds(to);
        to.setDate(to.getDate() - 1);
        
        dateFilter.getCheckBox().setValue(true);
        dateFilter.getFromDateBox().setValue(from);
        dateFilter.getToDateBox().setValue(to);
        
        dateFilter.updateCriteriaObject();
        
        assertTrue(
            "The find empty entries only checkbox was expected to be checked", 
            dateFilter.getCriteria().isFindEmptyEntriesOnly()
        );
        assertEquals(
            "The from date was expected to be set to 2 days ago", 
            from, 
            dateFilter.getCriteria().getFromDate()
        );
        assertEquals(
            "The to date was expected to be set to 1 day ago", 
            to, 
            dateFilter.getCriteria().getToDate()
        );
    }
    
    /**
     * Tests that the checkFilterActive function returns the correct boolean values for different criteria sets.
     * Expected return values : criteria
     * Case 1 TRUE : { "fromDate":DateObject, "toDate":null, "findEmptyEntriesOnly":false }
     * Case 2 TRUE : { "fromDate":null, "toDate":DateObject, "findEmptyEntriesOnly":false }
     * Case 3 TRUE : { "fromDate":null, "toDate":null, "findEmptyEntriesOnly":true }
     * Case 4 FALSE : { "fromDate":null, "toDate":null, "findEmptyEntriesOnly":false }
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testCheckFilterActive() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        DateFilter.DateFilterCriteria filterCriteria = new DateFilter.DateFilterCriteria();
        
        // Case 1
        filterCriteria.setFindEmptyEntriesOnly(false);
        filterCriteria.setFromDate(new Date());
        filterCriteria.setToDate(null);
        dateFilter.setCriteria(filterCriteria);
        assertTrue(
            "checkFilterActive was excepted to return true if the fromDate is not empty", 
            dateFilter.checkFilterActive()
        );
        
        // Case 2
        filterCriteria.setFindEmptyEntriesOnly(false);
        filterCriteria.setFromDate(null);
        filterCriteria.setToDate(new Date());
        dateFilter.setCriteria(filterCriteria);
        assertTrue(
            "checkFilterActive was excepted to return true if the toDate is not empty", 
            dateFilter.checkFilterActive()
        );
        
        // Case 3
        filterCriteria.setFindEmptyEntriesOnly(true);
        filterCriteria.setFromDate(null);
        filterCriteria.setToDate(null);
        dateFilter.setCriteria(filterCriteria);
        assertTrue(
            "checkFilterActive was excepted to return true if the checkbox is checked", 
            dateFilter.checkFilterActive()
        );
        
        // Case 4
        filterCriteria.setFindEmptyEntriesOnly(false);
        filterCriteria.setFromDate(null);
        filterCriteria.setToDate(null);
        dateFilter.setCriteria(filterCriteria);
        assertFalse(
            "checkFilterActive was expected to return false with no values set", 
            dateFilter.checkFilterActive()
        );
    }
    
    /**
     * Test case to make sure that the to and from boxes get set to null when a custom date is being used.
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesCustom() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date();
        currentDate = getDateWithoutMilliseconds(currentDate);
        String range = "Customised date range";
        
        dateFilter.setDates(currentDate, range);
        
        assertNull(
            "The From date was expected to be null, because the range was custom", 
            dateFilter.getFromDateBox().getValue()
        );
        assertNull(
            "The To date was expected to be null, because the range was custom",
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the to and from date boxes get set to the current date when the selected filter is
     * "Today"
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesToday() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 1, 8);
        currentDate = getDateWithoutMilliseconds(currentDate);
        String range = "Today";
        
        dateFilter.setDates((Date)currentDate.clone(), range);
        
        assertEquals(
            "The From date was expected to be the same as the date passed in", 
            currentDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the same as the date passed in", 
            currentDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the to and from date boxes are set to the 7th of January when the input date
     * is the 8th of January and the filter is "Yesterday"
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesYesterday() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 1, 8);
        currentDate = getDateWithoutMilliseconds(currentDate);
        String range = "Yesterday";
        Date expectedDate = new Date(112, 1, 7);
        expectedDate = getDateWithoutMilliseconds(expectedDate);
        
        dateFilter.setDates((Date)currentDate.clone(), range);
        
        assertEquals(
            "The From date was expected to be 1 day earlier than the passed in date", 
            expectedDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be 1 day earlier than the passed in date", 
            expectedDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the function assignes the correct dates to the to and from date boxes.
     * For this testcase the range is "This week (Mon-Today)", with the input date being 8 Feb 2012.
     * We expected the range to be 6 Feb 2012 - 8 Feb 2012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesThisWeekMondayToToday() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 1, 8);
        currentDate = getDateWithoutMilliseconds(currentDate);
        
        Date fromDate = new Date(112, 1, 6);
        fromDate = getDateWithoutMilliseconds(fromDate);
        
        String range = "This week (Mon-Today)";
        
        dateFilter.setDates((Date)currentDate.clone(), range);
        
        assertEquals(
            "The From date was expected to be the 6th of February 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the date that was passed in", 
            currentDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the correct amount of days are included in the range when we want a
     * "Last 7 days" range.
     * Input: 8 Feb 2012
     * Expected output date range: 1 Feb 2012 - 8 Feb 2012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesLastSevenDays() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 1, 8);
        currentDate = getDateWithoutMilliseconds(currentDate);
        Date fromDate = new Date(112, 1, 1);
        fromDate = getDateWithoutMilliseconds(fromDate);
        String range = "Last 7 days";
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 1st of February 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the date that was passed in", 
            currentDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the function assigns the correct dates to the to and from boxes
     * input date: 16 August 2012
     * input range: "Last week (Mon-Sun)"
     * expected from value: 6 August 2012
     * expected to value: 12 August 1012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesLastWeekMondayToSunday() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 7, 16);
        currentDate = getDateWithoutMilliseconds(currentDate);
        Date fromDate = new Date(112, 7, 6);
        fromDate = getDateWithoutMilliseconds(fromDate);
        Date toDate = new Date(112, 7, 12);
        toDate = getDateWithoutMilliseconds(toDate);
        String range = "Last week (Mon-Sun)";
        
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 6th of August 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the 12th of August 2012", 
            toDate, 
            dateFilter.getToDateBox().getValue()
        );
        
    }
    
    /**
     * Tests that the function assigns the correct dates to the to and from boxes
     * input date: 15 August 2012
     * input range: "Last working week (Mon-Fri)"
     * expected from value: 6 August 2012
     * expected to value: 10 August 1012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesLastWorkingWeek() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 7, 15);
        currentDate = getDateWithoutMilliseconds(currentDate);
        Date fromDate = new Date(112, 7, 6);
        fromDate = getDateWithoutMilliseconds(fromDate);
        Date toDate = new Date(112, 7, 10);
        toDate = getDateWithoutMilliseconds(toDate);
        String range = "Last working week (Mon-Fri)";
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 6th of August 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the 10th of August 2012", 
            toDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the function assigns the correct dates to the to and from boxes
     * input date: 8 February 2012
     * input range: "Last 14 days"
     * expected from value: 25 January 2012
     * expected to value: 8 February 2012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesLastFourteenDays() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 1, 8);
        currentDate = getDateWithoutMilliseconds(currentDate);
        Date fromDate = new Date(112, 0, 25);
        fromDate = getDateWithoutMilliseconds(fromDate);
        String range = "Last 14 days";
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 25th of January 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the passed in date", 
            currentDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the function assigns the correct dates to the to and from boxes
     * input date: 16 August 2012
     * input range: "This month"
     * expected from value: 1 August 2012
     * expected to value: 16 August 2012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesThisMonth() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 7, 16);
        currentDate = getDateWithoutMilliseconds(currentDate);
        Date fromDate = new Date(112, 7, 1);
        fromDate = getDateWithoutMilliseconds(fromDate);
        String range = "This month";
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 1st of August 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the passed in date", 
            currentDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the function assigns the correct dates to the to and from boxes
     * input date: 8 February 2012
     * input range: "Last 30 days"
     * expected from value: 9 January 2012
     * expected to value: 8 February 2012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesLastThirtyDays() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 1, 8);
        Date fromDate = new Date(112, 0, 9);
        String range = "Last 30 days";
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 1st of January 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the 8th of February 2012", 
            currentDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the function assigns the correct dates to the to and from boxes
     * input date: 8 February 2012
     * input range: "Last month"
     * expected from value: 1 January 2012
     * expected to value: 31 January 2012
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesLastMonth() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 1, 8);
        currentDate = getDateWithoutMilliseconds(currentDate);
        Date fromDate = new Date(112, 0, 1);
        fromDate = getDateWithoutMilliseconds(fromDate);
        Date toDate = new Date(112, 0, 31);
        toDate = getDateWithoutMilliseconds(toDate);
        String range = "Last month";
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 1st of January 2012", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the 31st of January 2012", 
            toDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * Tests that the function assigns the correct dates to the to and from boxes
     * input date: 5 January 2012
     * input range: "Last month"
     * expected from value: 1 December 2011
     * expected to value: 31 December 2011
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     */
    public void testSetDatesLastMonth2() {
        DateFilter dateFilter = new DateFilter("someStyleName");
        
        Date currentDate = new Date(112, 0, 5);
        currentDate = getDateWithoutMilliseconds(currentDate);
        Date fromDate = new Date(111, 11, 1);
        fromDate = getDateWithoutMilliseconds(fromDate);
        Date toDate = new Date(111, 11, 31);
        toDate = getDateWithoutMilliseconds(toDate);
        String range = "Last month";
        
        dateFilter.setDates(currentDate, range);
        
        assertEquals(
            "The From date was expected to be the 1st of December 2011", 
            fromDate, 
            dateFilter.getFromDateBox().getValue()
        );
        assertEquals(
            "The To date was expected to be the 31st of December 2011", 
            toDate, 
            dateFilter.getToDateBox().getValue()
        );
    }
    
    /**
     * This function will return a date after having stripped off it's time
     * 
     * @param date The date that needs to be rid of it's milliseconds
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  16 Aug 2012
     * 
     * @return Date
     */
    private Date getDateWithoutMilliseconds(Date date) {
        Date clone = (Date)date.clone();
        clone.setHours(0);
        clone.setMinutes(0);
        clone.setSeconds(0);
        
        int iTimeStamp = (int) (clone.getTime() * .001);
        long newTimeStamp = (long)iTimeStamp * 1000;
        
        clone.setTime(newTimeStamp);
        
        return clone;
    }
}
