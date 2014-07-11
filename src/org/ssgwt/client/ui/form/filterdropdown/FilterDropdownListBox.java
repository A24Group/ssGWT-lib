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
package org.ssgwt.client.ui.form.filterdropdown;

import java.util.List;

import org.ssgwt.client.ui.form.filterdropdown.FilterDropdown.FilterDropdownResources;
import org.ssgwt.client.ui.form.filterdropdown.recorddisplays.FilterDropdownRecordWidget;
import org.ssgwt.client.ui.searchbox.SearchBox.SearchBoxResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * The drop down section of the filter dropdown list
 * 
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  10 Jul 2014
 *
 * @param <T> The type of the VO that will hold the data displayed by the search widget
 */
public class FilterDropdownListBox<ListType> extends PopupPanel {
    
    /**
     * Flag that indicated whether or not there is results to be dislpayed
     */
    private boolean hasResults = false;
    
    /**
     * The items that should be displayed in the drop down
     */
    private List<FilterDropdownRecordWidget<ListType>> resultDisplayItems = null;
    
    /**
     * The main panel that holds the result display items
     */
    private FlowPanel mainPanel = new FlowPanel();
    
    /**
     * This info label that holds the info messages displayed to the user
     */
    private Label infoLabel = new Label();
    
    /**
     * The currently selected item
     */
    private int selectedIndex = -1;
    
    /**
     * Holds an instance of the default resources
     */
    private static FilterDropdownResources DEFAULT_RESOURCES;
    
    /**
     * Holds an instance of resources
     */
    private FilterDropdownResources resources;
    
    /**
     * Class constructor
     * 
     * @param resources
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public FilterDropdownListBox(FilterDropdownResources resources) {
        super(true);
        this.setStyleName("");
        this.resources = resources;
        this.resources.filterDropdownStyle().ensureInjected();
        mainPanel.setStyleName(this.resources.filterDropdownStyle().dropDown());
        this.add(mainPanel);
        addInfoMessages();
        mainPanel.getElement().getStyle().setProperty("maxHeight", "250px");
        mainPanel.getElement().getStyle().setProperty("overflow", "auto");
    }
    
    /**
     * Adds the loading info message and the no results info message to the drop down
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public void addInfoMessages() {
        mainPanel.clear();
        if (!hasResults) {
            infoLabel.setText("~ No results found ~");
            infoLabel.setStyleName(this.resources.filterDropdownStyle().noResultText());
        }
        mainPanel.add(infoLabel);
    }
    
    /**
     * Selects the next item in the drop down and change the item to it's selected style
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @return The display text for the item
     */
    public String selectNextItem() {
        if (hasResults && selectedIndex < resultDisplayItems.size() - 1) {
            if (selectedIndex >= 0) {
                resultDisplayItems.get(selectedIndex).setSelectedState(false);
            }
            selectedIndex++;
            resultDisplayItems.get(selectedIndex).setSelectedState(true);
            resultDisplayItems.get(selectedIndex).getElement().scrollIntoView();
        }
        if (selectedIndex >= 0 && resultDisplayItems.size() > selectedIndex) {
            return resultDisplayItems.get(selectedIndex).getItemSelectionText();
        } else {
            return null;
        }
    }
    
    /**
     * Selects the previous item in the drop down and change the item to it's selected style
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @return The display text for the item
     */
    public String selectPreviousItem() {
        if (hasResults && selectedIndex >= 0) {
            resultDisplayItems.get(selectedIndex).setSelectedState(false);
            selectedIndex--;
            if (selectedIndex >= 0) {
                resultDisplayItems.get(selectedIndex).setSelectedState(true);
                resultDisplayItems.get(selectedIndex).getElement().scrollIntoView();
            }
        }
        if (selectedIndex >= 0 && resultDisplayItems.size() > selectedIndex) {
            return resultDisplayItems.get(selectedIndex).getItemSelectionText();
        } else {
            return null;
        }
    }
    
    /**
     * Create an instance on the default resources object if it the
     * DEFAULT_RESOURCES variable is null if not it just return the object in
     * the DEFAULT_RESOURCES variable
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @return the default resource object
     */
    private static FilterDropdownResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(SearchBoxResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    /**
     * Getter for the resources instance
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @return The resources
     */
    public FilterDropdownResources getResources() {
        return this.resources;
    }
    
    /**
     * Updates the items that should be displayed on the drop down for the user
     * 
     * @param resultDisplayItems
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public void setSelectableItems(List<FilterDropdownRecordWidget<ListType>> resultDisplayItems) {
        hasResults = !(resultDisplayItems.size() == 0);
        this.resultDisplayItems = resultDisplayItems;
        if (hasResults) {
            mainPanel.clear();
            int count = 0;
            for (final FilterDropdownRecordWidget<ListType> displayItem : this.resultDisplayItems) {
                if (count > 0) {
                    SimplePanel recordsSplitter = new SimplePanel();
                    recordsSplitter.setHeight("1px");
                    recordsSplitter.setStyleName("itemSplitter");
                    mainPanel.add(recordsSplitter);
                }
                mainPanel.add(displayItem);
                displayItem.addMouseOverHandler(new MouseOverHandler() {
                    
                    /**
                     * The event that fire on mouse over
                     * 
                     * @author Michael Barnard <michael.barnard@a24group.com>
                     * @since  10 Jul 2014
                     * 
                     * @param event - Mouse Over Event
                     */
                    @Override
                    public void onMouseOver(MouseOverEvent event) {
                        displayItem.setSelectedState(true);
                    }
                });
                displayItem.addMouseOutHandler(new MouseOutHandler() {
                    
                    /**
                     * The event that fire on mouse out
                     * 
                     * @author Michael Barnard <michael.barnard@a24group.com>
                     * @since  10 Jul 2014
                     * 
                     * @param event - Mouse out Event
                     */
                    @Override
                    public void onMouseOut(MouseOutEvent event) {
                        displayItem.setSelectedState(false);
                    }
                });
                count++;
            }
        } else {
            addInfoMessages();
        }
    }
    
    /**
     * Retrieves the currently selected item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @return The item that is currently selected
     */
    public FilterDropdownRecordWidget<ListType> getSelectedItem() {
        if (selectedIndex >= 0 && selectedIndex < resultDisplayItems.size()) {
            return resultDisplayItems.get(selectedIndex);
        } else {
            return null;
        }
    }
}
