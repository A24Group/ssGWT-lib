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
package org.ssgwt.client.ui.datagrid.filter;

import org.ssgwt.client.ui.datagrid.FilterSortCell;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * The abstract filter that all filter that work with the FilterSortHeader should extend
 * 
 * @author Johannes Gryffenberg
 * @since 5 July 2012
 */
public abstract class AbstractHeaderFilter extends PopupPanel {

    /**
     * Flag indicating whether the filter is active or not
     */
    private boolean filterActive = false;

    /**
     * Object holding the filter criteria
     */
    protected Criteria filterCirteria;
    
    /**
     * The header the filter is linked to
     */
    private FilterSortCell parentHeader = null;

    /**
     * The default resources for filter poups
     * 
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    public interface Resources extends ClientBundle {
        
        /**
         * Image for the remove filter icon's up state
         * 
         * @return The image resource to be used with a image
         */
        @Source("images/Hover_Delete_Applied_filter_up.png")
        ImageResource removeFilterIconUp();
        
        /**
         * Image for the remove filter icon's over state
         * 
         * @return The image resource to be used with a image
         */
        @Source("images/Hover_Delete_Applied_filter_hover.png")
        ImageResource removeFilterIconOver();
        
        /**
         * Image for the remove filter icon's down state
         * 
         * @return The image resource to be used with a image
         */
        @Source("images/Hover_Delete_Applied_filter_down.png")
        ImageResource removeFilterIconDown();
        
    }
    
    /**
     * The criteria object that will be extended by the criteria objects of each filter
     * 
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    class Criteria {
        
    }
    
    /**
     * Creates an empty popup panel. A child widget must be added to it before it
     * is shown.
     */
    public AbstractHeaderFilter() {
        super();
    }

    /**
     * Creates an empty popup panel, specifying its "auto-hide" property.
     *
     * @param autoHide <code>true</code> if the popup should be automatically
     *          hidden when the user clicks outside of it or the history token
     *          changes.
     */
    public AbstractHeaderFilter(boolean autoHide) {
        super(autoHide);
    }

    /**
     * Creates an empty popup panel, specifying its "auto-hide" and "modal"
     * properties.
     *
     * @param autoHide <code>true</code> if the popup should be automatically
     *          hidden when the user clicks outside of it or the history token
     *          changes.
     * @param modal <code>true</code> if keyboard or mouse events that do not
     *          target the PopupPanel or its children should be ignored
     */
    public AbstractHeaderFilter(boolean autoHide, boolean modal) {
        super(autoHide, modal);
    }

    /**
     * Setter the header the filter belongs to
     * 
     * @param parentHeader - The header the filter belongs to
     */
    public void setParentHeader(FilterSortCell parentHeader) {
        this.parentHeader = parentHeader;
    }

    /**
     * Set the filter's state and then updates the header with the state of the filter
     * 
     * @param filterActive - Flag to indicate if the filter is active
     */
    protected void setFilterActive(boolean filterActive) {
        this.filterActive = filterActive;
        this.parentHeader.setFilterActive(filterActive);
    }
    
    /**
     * Function to check if the filter is active
     * 
     * @return 
     */
    public boolean isFilterActive() {
        return this.filterActive;
    }
    
    /**
     * Sets the title of the Filter popup
     * 
     * @param title - The title that should be displayed on the popup
     */
    public abstract void setTitle(String title);
    
    /**
     * Retrieves the criteria of the filter
     * 
     * @return The criteria of the obejct
     */
    public abstract Criteria getCriteria();
    
    /**
     * Function that check if the filter is active by checking the value on the criteria object
     * 
     * @return Flag to indicate if the filter should be active based on the data in the criteria object
     */
    protected abstract boolean checkFilterActive();
    
    /**
     * Function to update the criteria with data in the fields
     */
    protected abstract void updateCriteriaObject();
    
    /**
     * Function that sets the criteria object to a state where the filter will be inactive
     */
    protected abstract void setCriteriaObjectEmpty();
    
    /**
     * Sets the criteria object for the filter
     * 
     * @param filterCirteria - The criteria for the filter
     */
    public void setCriteria(Criteria filterCirteria) {
        this.filterCirteria = filterCirteria;
        updateFieldData();
    }
    
    /**
     * Function used to update the input fields
     */
    protected abstract void updateFieldData();
    
    /**
     * The function that is called to close the filter popup
     * 
     * @param clearFilter - Flag to indicate if the user wants to clear the filter or use the data added to the fields
     */
    public void closeFilterPopup(boolean clearFilter) {
        if (!clearFilter) {
            updateCriteriaObject();
            setFilterActive(checkFilterActive());
        } else {
            setCriteriaObjectEmpty();
            updateFieldData();
            setFilterActive(false);
        }
        this.hide();
    }
    
    /**
     * Updates the fields to their previous state before the page the popup is hidden
     */
    public void hide(boolean autoClosed) {
        updateFieldData();
        super.hide(autoClosed);
        
    }
    
    /**
     * Clear all the ui fields to their default states
     */
    protected abstract void clearFields();
}
