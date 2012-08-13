package org.ssgwt.client.ui.datagrid.filter;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Test cases for the TextFilter component
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since 13 Aug 2012
 */
public class TextFilterTest extends GWTTestCase {

    /**
     * Retrieves the module name
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 13 Aug 2012
     */
    @Override
    public String getModuleName() {
        return "org.ssgwt.ssGwt";
    }
    
    /**
     * Tests that the clearFields function changes the check box to an unchecked state and that the 
     * text in the text box is removed when the clearFields function is called
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 13 Aug 2012
     */
    public void testClearFields() {
        TextFilter textFilter = new TextFilter();
        
        textFilter.getCheckBox().setValue(true);
        textFilter.getTextBox().setText("This will be removed");
        
        textFilter.clearFields();
        
        assertFalse(
            "The value of the check box was expected to be false after the clearFields function was called",
            textFilter.getCheckBox().getValue()
        );
        assertEquals(
            "The text in the text box was expected to be an empty string after the clearFields function was called", 
            "", 
            textFilter.getTextBox().getValue()
        );
    }
    
    /**
     * Test that the fields are update with the data in the criteria object when the setCriteria function is called
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 13 Aug 2012
     */
    public void testSetCriteria() {
        TextFilter textFilter = new TextFilter();
        
        TextFilter.TextFilterCriteria textFilterCriteria = new TextFilter.TextFilterCriteria();
        textFilterCriteria.setCriteria("");
        textFilterCriteria.setFindEmptyEntriesOnly(true);
        
        textFilter.setCriteria(textFilterCriteria);
        
        assertEquals(
            "The text box was expected to hold no text", 
            "", 
            textFilter.getTextBox().getValue()
        );
        assertTrue("The check box was expected to be checked", textFilter.getCheckBox().getValue());
        assertEquals(
            "The getCriteria function was expected to return the same object that was passed into the setCriteria function", 
            textFilterCriteria, 
            textFilter.getCriteria()
        );
        
        TextFilter.TextFilterCriteria textFilterCriteria2 = new TextFilter.TextFilterCriteria();
        textFilterCriteria2.setCriteria("Criteria 2");
        textFilterCriteria2.setFindEmptyEntriesOnly(false);
        
        textFilter.setCriteria(textFilterCriteria2);
        
        assertEquals("The text box was expected to hold \"Criteria 2\"", "Criteria 2", textFilter.getTextBox().getValue());
        assertFalse("The check box was expected to be unchecked", textFilter.getCheckBox().getValue());
        assertEquals(
            "The getCriteria function was expected to return the same object that was passed into the setCriteria function", 
            textFilterCriteria2, 
            textFilter.getCriteria()
        );
    }
    
    /**
     * Test that the setCriteriaObjectEmpty function sets the TextFilter Criteria to it's default state
     * The object should look like this:
     * { "criteria" : "", "findEmptyEntriesOnly" : false }
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 13 Aug 2012
     */
    public void testSetCriteriaObjectEmpty() {
        TextFilter textFilter = new TextFilter();
        
        TextFilter.TextFilterCriteria textFilterCriteria = new TextFilter.TextFilterCriteria();
        textFilterCriteria.setCriteria("Bla bla bla");
        textFilterCriteria.setFindEmptyEntriesOnly(true);
        
        textFilter.setCriteria(textFilterCriteria);
        
        textFilter.setCriteriaObjectEmpty();
        
        assertEquals("The criteria was expected to be empty", "", textFilter.getCriteria().getCriteria());
        assertFalse("The find empty entries only was expected to be false", textFilter.getCriteria().isFindEmptyEntriesOnly());
    }
    
    /**
     * Test that the updateCriteriaObject function updates the TextFilter Criteria with the data in the input fields
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 13 Aug 2012
     */
    public void testUpdateCriteriaObject() {
        TextFilter textFilter = new TextFilter();
        
        textFilter.getTextBox().setValue("Bla bla bla");
        textFilter.getCheckBox().setValue(true);
        
        textFilter.updateCriteriaObject();
        
        assertEquals("The criteria was expected to be empty", "Bla bla bla", textFilter.getCriteria().getCriteria());
        assertTrue("The find empty entries only was expected to be true", textFilter.getCriteria().isFindEmptyEntriesOnly());
    }
    
    /**
     * Test that the checkFilterActive returns the correct boolean values for different criteria sets
     * 
     * List of expected return value against different criteria:
     * Case 1: Should return: TRUE for criteria: { "criteria" : "Some text here", "findEmptyEntriesOnly" : false }
     * Case 2: Should return: TRUE for criteria: { "criteria" : "", "findEmptyEntriesOnly" : true }
     * Case 3: Should return: FALSE for criteria: { "criteria" : "", "findEmptyEntriesOnly" : false }
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 13 Aug 2012
     */
    public void testCheckFilterActive() {
        TextFilter textFilter = new TextFilter();
        
        // Case 1
        TextFilter.TextFilterCriteria textFilterCriteria = new TextFilter.TextFilterCriteria();
        textFilterCriteria.setCriteria("Some text here");
        textFilterCriteria.setFindEmptyEntriesOnly(false);
        
        textFilter.setCriteria(textFilterCriteria);
        
        assertTrue(
            "The checkFilterActive function was expected to return true for the following criteria " +
            "object { \"criteria\" : \"Some text here\", \"findEmptyEntriesOnly\" : false }", 
            textFilter.checkFilterActive()
        );
        
        // Case 2
        textFilterCriteria.setCriteria("");
        textFilterCriteria.setFindEmptyEntriesOnly(true);
        
        textFilter.setCriteria(textFilterCriteria);
        
        assertTrue(
            "The checkFilterActive function was expected to return true for the following criteria " +
            "object { \"criteria\" : \"\", \"findEmptyEntriesOnly\" : true }", 
            textFilter.checkFilterActive()
        );
        
        // Case 3
        textFilterCriteria.setCriteria("");
        textFilterCriteria.setFindEmptyEntriesOnly(false);
        
        textFilter.setCriteria(textFilterCriteria);
        
        assertFalse(
            "The checkFilterActive function was expected to return false for the following criteria " +
            "object { \"criteria\" : \"\", \"findEmptyEntriesOnly\" : false }", 
            textFilter.checkFilterActive()
        );
    }
    
    /**
     * Tests that the text of the title label is update when the setTitle function is called
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 13 Aug 2012
     */
    public void testSetTitle() {
        TextFilter textFilter = new TextFilter();
        
        textFilter.setTitle("This is a title for the TextFilter");
        
        assertEquals(
            "The title label was expected to have \"This is a title for the TextFilter\" as text", 
            "This is a title for the TextFilter", 
            textFilter.getTitleLabel().getText()
        );
    }
    
}
