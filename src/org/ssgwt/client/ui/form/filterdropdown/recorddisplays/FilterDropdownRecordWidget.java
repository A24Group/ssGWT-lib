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
package org.ssgwt.client.ui.form.filterdropdown.recorddisplays;

import org.ssgwt.client.ui.form.filterdropdown.FilterDropdown;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;

/**
 * Abstract class for all the filter drop down display items
 * 
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  10 Jul 2014
 *
 * @param <ListType> The type of object that the item works with
 */
public abstract class FilterDropdownRecordWidget<ListType> extends FocusPanel {
    
    /**
     * This will store an instance of the parent search box
     */
    private FilterDropdown<?, ListType> parent = null;
    
    /**
     * Sets the value of the item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @param itemValue - the value of the item
     */
    public abstract void setItemValue(ListType itemValue);
    
    /**
     * Retrieves the value of the item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @return The value of the item
     */
    public abstract ListType getItemValue();
    
    /**
     * Retrieves the text that will be displayed in the search box if an item is clicked
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @return the text that will be displayed in the search box if an item is clicked
     */
    public abstract String getItemSelectionText();
    
    /**
     * This method will be used to check if the current item matches the criteria that is entered in the search box
     * 
     * @param criteria - The criteria that was searched for
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @return Whether the item matches the criteria
     */
    public abstract boolean compareToSearchCriteria(String criteria);
    
    /**
     * Sets the item to a selected state or unselected state
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @param selected - Indicates if the item should be is selected state
     */
    public abstract void setSelectedState(boolean selected);
    
    /**
     * Class constructor
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     */
    public FilterDropdownRecordWidget() {
        this.addClickHandler(new ClickHandler() {
            
            /**
             * This will be called if the item is clicked on
             * 
             * @author Michael Barnard <michael.barnard@a24group.com>
             * @since  11 Jul 2014
             * 
             * @param event - The click event that caused this 
             */
            @Override
            public void onClick(ClickEvent event) {
                if (parent != null) {
                    parent.setSelectedDisplayItem(FilterDropdownRecordWidget.this);
                }
            }
        });
    }
    
    /**
     * Set the parent search box for the display item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @param parent - The parent FilterDropDown box
     */
    public void setParentSearchBox(FilterDropdown<?, ListType> parent) {
        this.parent = parent;
    }

}
