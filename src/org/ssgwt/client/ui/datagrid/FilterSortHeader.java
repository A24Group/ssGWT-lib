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
package org.ssgwt.client.ui.datagrid;

import org.ssgwt.client.ui.datagrid.event.FilterChangeEvent;
import org.ssgwt.client.ui.datagrid.filter.AbstractHeaderFilter;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.Header;

/**
 * The header that is used to enable filtering on a table that uses the
 * FilterSortCell
 * 
 * @author Johannes Gryffenberg
 * @since 29 June 2012
 */
public class FilterSortHeader extends Header<HeaderDetails> {

    /**
     * Holds details of the header
     */
    private HeaderDetails headerDetails = new HeaderDetails();

    /**
     * Constructor taking the label that should be displayed on the header
     * 
     * @param label - The label for the header
     * @param filterWidget - The widget that should be displayed if the user clicks on the filter icon
     */
    public FilterSortHeader(String label, AbstractHeaderFilter filterWidget) {
        super(new FilterSortCell());
        headerDetails.label = label;
        headerDetails.filterWidget = filterWidget;
    }

    /**
     * Retrieves the header details
     */
    @Override
    public HeaderDetails getValue() {
        return headerDetails;
    }
    
    /**
     * Adds a event handler for the FilterChangeEvent
     * 
     * @param handler - The event handler
     * 
     * @return The handler registration object that will be used to remove the event handler
     */
    public HandlerRegistration addFilterChangeHandler(FilterChangeEvent.FilterChangeHandler handler) {
        return ((FilterSortCell)this.getCell()).addFilterChangeHandler(handler);
    }
    
}
