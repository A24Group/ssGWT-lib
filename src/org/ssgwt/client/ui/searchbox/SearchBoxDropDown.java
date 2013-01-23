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

public class SearchBoxDropDown<T> extends PopupPanel {

	private boolean waitingForResults = true;
	
	private boolean hasResults = false;
	
	private List<SearchBoxRecordWidget<T>> resultDisplayItems = null;
	
	private FlowPanel mainPanel = new FlowPanel();
	
	private Label infoLabel = new Label();
	
	private int selectedIndex = -1;
	
	/**
     * Holds an instance of the default resources
     */
    private static SearchBoxResources DEFAULT_RESOURCES;
    
    /**
     * Holds an instance of resources
     */
    private SearchBoxResources resources;
	
	public SearchBoxDropDown(SearchBoxResources resources) {
		super(true);
		this.setStyleName("");
        this.resources = resources;
        this.resources.searchBoxStyle().ensureInjected();
        mainPanel.setStyleName(this.resources.searchBoxStyle().dropDown());
		this.add(mainPanel);
		addDefaultInfoItems();
	}
	
	public void addDefaultInfoItems() {
		mainPanel.clear();
		if (waitingForResults) {
			infoLabel.setStyleName(this.resources.searchBoxStyle().infoText());
		} else if (!hasResults) {
			infoLabel.setText("~ No results found ~");
			infoLabel.setStyleName(this.resources.searchBoxStyle().noResultText());
		}
		mainPanel.add(infoLabel);
	}
	
	public String selectNextItem() {
		if (hasResults && selectedIndex < resultDisplayItems.size() - 1) {
			if (selectedIndex >= 0) {
				resultDisplayItems.get(selectedIndex).setSelectedState(false);
			}
			selectedIndex++;
			resultDisplayItems.get(selectedIndex).setSelectedState(true);
		}
		if (selectedIndex >= 0) {
			return resultDisplayItems.get(selectedIndex).getItemSelectionText();
		} else {
			return null;
		}
	}
	
	public String selectPreviousItem() {
		if (hasResults && selectedIndex >= 0) {
			resultDisplayItems.get(selectedIndex).setSelectedState(false);
			selectedIndex--;
			if (selectedIndex >= 0) {
				resultDisplayItems.get(selectedIndex).setSelectedState(true);
			}
		}
		if (selectedIndex >= 0) {
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
	
	public void setSelectableItems(List<SearchBoxRecordWidget<T>> resultDisplayItems) {
		waitingForResults = false;
		hasResults = !(resultDisplayItems.size() == 0);
		this.resultDisplayItems = resultDisplayItems;
		if (hasResults) {
			mainPanel.clear();
			int count = 0;
			for (final SearchBoxRecordWidget<T> displayItem : this.resultDisplayItems) {
				mainPanel.add(displayItem);
				final int itemId = count;
				displayItem.addMouseOverHandler(new MouseOverHandler() {
					
					@Override
					public void onMouseOver(MouseOverEvent arg0) {
						if (selectedIndex >= 0) {
							SearchBoxDropDown.this.resultDisplayItems.get(selectedIndex).setSelectedState(false);
						}
						displayItem.setSelectedState(true);
						selectedIndex = itemId;
					}
				});
				displayItem.addMouseOutHandler(new MouseOutHandler() {
					
					@Override
					public void onMouseOut(MouseOutEvent arg0) {
						displayItem.setSelectedState(false);
					}
				});
				count++;
			}
		} else {
			addDefaultInfoItems();
		}
	}
	
	public SearchBoxRecordWidget<T> getSelectedItem() {
		if (selectedIndex >= 0 && selectedIndex < resultDisplayItems.size()) {
			return resultDisplayItems.get(selectedIndex);
		} else {
			return null;
		}
	}

}
