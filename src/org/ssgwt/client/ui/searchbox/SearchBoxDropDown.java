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
package org.ssgwt.client.ui.searchbox;

import java.util.List;

import org.ssgwt.client.ui.searchbox.SearchBox.SearchBoxResources;
import org.ssgwt.client.ui.searchbox.recorddisplays.SearchBoxRecordWidget;

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
 * The drop down of the search box that holds the results
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  22 January 2013
 *
 * @param <T> The type of the VO that will hold the data displayed by the search widget
 */
public class SearchBoxDropDown<T> extends PopupPanel {
    
    /**
     * Flag that indicates if the page is waiting for results
     */
    private boolean waitingForResults = true;
    
    /**
     * Flag that indicated whether or not there is results to be dislpayed
     */
    private boolean hasResults = false;
    
    /**
     * The items that should be displayed in the drop down
     */
    private List<SearchBoxRecordWidget<T>> resultDisplayItems = null;
    
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
    private static SearchBoxResources DEFAULT_RESOURCES;
    
    /**
     * Holds an instance of resources
     */
    private SearchBoxResources resources;
    
    /**
     * Class constructor
     * 
     * @param resources
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public SearchBoxDropDown(SearchBoxResources resources) {
        super(true);
        this.setStyleName("");
        this.resources = resources;
        this.resources.searchBoxStyle().ensureInjected();
        mainPanel.setStyleName(this.resources.searchBoxStyle().dropDown());
        this.add(mainPanel);
        addInfoMessages();
        mainPanel.getElement().getStyle().setProperty("maxHeight", "250px");
        mainPanel.getElement().getStyle().setProperty("overflow", "auto");
    }
    
    /**
     * Adds the loading info message and the no results info message to the drop down
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public void addInfoMessages() {
        mainPanel.clear();
        if (waitingForResults) {
            infoLabel.setStyleName(this.resources.searchBoxStyle().infoText());
        } else if (!hasResults) {
            infoLabel.setText("~ No results found ~");
            infoLabel.setStyleName(this.resources.searchBoxStyle().noResultText());
        }
        mainPanel.add(infoLabel);
    }
    
    /**
     * Selects the next item in the drop down and change the item to it's selected style
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
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
        if (selectedIndex >= 0) {
            return resultDisplayItems.get(selectedIndex).getItemSelectionText();
        } else {
            return null;
        }
    }
    
    /**
     * Selects the previous item in the drop down and change the item to it's selected style
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @author Saurabh Chawla <saurabh.chawla@a24group.com>
     * @since  22 January 2013
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
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @return the default resource object
     */
    private static SearchBoxResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(SearchBoxResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    /**
     * Sets the string the user is currently searching for
     * 
     * @param currentSearchString - The current string the user is searching for
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public void setCurrentSearchString(String currentSearchString) {
        infoLabel.setText("Searching for \"" + currentSearchString + "\"");
    }
    
    /**
     * Getter for the resources instance
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @return The resources
     */
    public SearchBoxResources getResources() {
        return this.resources;
    }
    
    /**
     * Updates the items that should be displayed on the drop down for the user
     * 
     * @param resultDisplayItems
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public void setSelectableItems(List<SearchBoxRecordWidget<T>> resultDisplayItems) {
        waitingForResults = false;
        hasResults = !(resultDisplayItems.size() == 0);
        this.resultDisplayItems = resultDisplayItems;
        if (hasResults) {
            mainPanel.clear();
            int count = 0;
            for (final SearchBoxRecordWidget<T> displayItem : this.resultDisplayItems) {
                if (count > 0) {
                    SimplePanel recordsSplitter = new SimplePanel();
                    recordsSplitter.setHeight("1px");
                    recordsSplitter.setStyleName("itemSplitter");
                    mainPanel.add(recordsSplitter);
                }
                mainPanel.add(displayItem);
                final int itemId = count;
                displayItem.addMouseOverHandler(new MouseOverHandler() {
                    
                    /**
                     * The event that fire on mouse over
                     * 
                     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
                     * @since  22 January 2013
                     * 
                     * @param event - Mouse Over Event
                     */
                    @Override
                    public void onMouseOver(MouseOverEvent event) {
                        if (selectedIndex >= 0) {
                            SearchBoxDropDown.this.resultDisplayItems.get(selectedIndex).setSelectedState(false);
                        }
                        displayItem.setSelectedState(true);
                        selectedIndex = itemId;
                    }
                });
                displayItem.addMouseOutHandler(new MouseOutHandler() {
                    
                    /**
                     * The event that fire on mouse out
                     * 
                     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
                     * @since  22 January 2013
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
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @return The item that is currently selected
     */
    public SearchBoxRecordWidget<T> getSelectedItem() {
        if (selectedIndex >= 0 && selectedIndex < resultDisplayItems.size()) {
            return resultDisplayItems.get(selectedIndex);
        } else {
            return null;
        }
    }
}
