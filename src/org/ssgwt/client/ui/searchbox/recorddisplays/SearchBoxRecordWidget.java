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
package org.ssgwt.client.ui.searchbox.recorddisplays;

import org.ssgwt.client.ui.searchbox.SearchBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract class for all the drop down display items for the search box
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  22 January 2013
 *
 * @param <T> The type of the VO that will hold the data displayed by the search widget
 */
public abstract class SearchBoxRecordWidget<T> extends FocusPanel {
    
    /**
     * The parent search box
     */
    private SearchBox<T> parent = null;
    
    /**
     * Sets the vo holding the data the display object will display
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @param itemVO - vo holding the data that should be displayed
     */
    public abstract void setItemVO(T itemVO);
    
    /**
     * Retrieves the vo holding the data the display object is displaying
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @param itemVO - vo holding the data the display object is displaying
     */
    public abstract T getItemVO();
    
    /**
     * Retrieves the text that will be displayed in the search box if an item is clicked
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @return the text that will be displayed in the search box if an item is clicked
     */
    public abstract String getItemSelectionText();
    
    /**
     * Sets the item to a selected state or unselected state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @param selected - Indicates if the it should be is selected state
     */
    public abstract void setSelectedState(boolean selected);
    
    /**
     * Class constructor
     */
    public SearchBoxRecordWidget() {
        this.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent arg0) {
                if (parent != null) {
                    parent.setSelectedDisplayItem(SearchBoxRecordWidget.this);
                }
            }
        });
    }
    
    /**
     * Set the parent search box for the display item
     * 
     * @param parent - The parent SearchBox
     */
    public void setParentSearchBox(SearchBox<T> parent) {
        this.parent = parent;
    }
}
