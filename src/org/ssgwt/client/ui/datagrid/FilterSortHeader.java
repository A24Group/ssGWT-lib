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

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.client.Window;

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
    
    /**
     * Preview a browser event that may trigger a column sort event. Return true if the
     * {@link CellTable} should proceed with sorting the column. Subclasses can override this method
     * to disable column sort for some click events, or particular header/footer sections.
     * <p>
     * This method will be invoked even if the header's cell does not consume a click event.
     * </p>
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  12 Sept 2014
     * 
     * @param context the context of the header
     * @param parent the parent Element
     * @param event the native browser event
     * @return true if the {@link CellTable} should continue respond to the event (i.e., if this is
     *         a click event on a sortable column's header, fire {@link ColumnSortEvent}). False if
     *         the {@link CellTable} should stop respond to the event. 
     */
    @Override
    public boolean onPreviewColumnSortEvent(Context context, Element parent, NativeEvent event) {
        Element filterImageElement = getImageElement(parent);
        
        // If the filter image element is clicked, then we don't want to perform a column sort event.
        if (event.getEventTarget().equals(filterImageElement)) {
            if (BrowserEvents.CLICK.equals(event.getType())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Retrieves the element of the image that is being displayed in the Cell
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  12 Sept 2014
     * 
     * @param parent - The top level container of the Cell
     * 
     * @return The element object that represents the image in the Cell
     */
    protected Element getImageElement(Element parent) {
        if (parent.getFirstChildElement().getElementsByTagName("img").getItem(0).getAttribute("name").equals("filterIcon")) {
            return parent.getFirstChildElement().getElementsByTagName("img").getItem(0);
        }
        return parent.getFirstChildElement().getElementsByTagName("img").getItem(1);
    }
}
