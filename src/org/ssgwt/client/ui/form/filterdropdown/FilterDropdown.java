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

import java.util.ArrayList;
import java.util.List;

import org.ssgwt.client.ui.form.InputField;
import org.ssgwt.client.ui.form.filterdropdown.recorddisplays.FilterDropdownRecordWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A filter search box that will perform live data filtering on a list of predefined values.
 *
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  10 Jul 2014
 *
 * @param <T> The type of the VO that will hold the dynamic form uses
 * @param <ListType> The type of the VO that will hold the data displayed by the search widget
 */
public abstract class FilterDropdown<T, ListType>
    extends
        Composite
    implements
        KeyUpHandler, ClickHandler, HasValue<String>, InputField<T, String> {

    /**
     * The minimum characters required for a filter to fire
     */
    private int minCharCount = 0;

    /**
     * The text box the user will type the search criteria in
     */
    @UiField
    TextBox textBox;
    
    /**
     * The container for the entire ui component
     */
    @UiField
    LayoutPanel container;
    
    /**
     * This is the flag used to specify if the component is read-only or not.
     */
    public boolean bReadOnly = false;
    
    /**
     * Whether the field is required or not
     */
    public boolean bRequired = false;

    /**
     * The object that was selected by the user
     */
    private ListType selectedObject = null;

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * Holds an instance of the default resources
     */
    private static FilterDropdownResources DEFAULT_RESOURCES;

    /**
     * Holds an instance of resources
     */
    private FilterDropdownResources resources;

    /**
     * The selected display item
     */
    private FilterDropdownRecordWidget<ListType> selectedDisplayItem;

    /**
     * The selected display item
     */
    private final ArrayList<FilterDropdownRecordWidget<ListType>> currentDisplayItems =
        new ArrayList<FilterDropdownRecordWidget<ListType>>();

    /**
     * The string that has previously been searched for
     */
    private String previousSearchString = null;

    /**
     * The drop down popup that will display the results
     */
    private FilterDropdownListBox<ListType> dropDownPopup = null;

    /**
     * UiBinder interface for the composite
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    interface Binder extends UiBinder<Widget, FilterDropdown<?, ?>> {
    }
    
    /**
     * Retrieve the value from the object that should the displayed on the input field
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @param object - The object the value should be retrieved from
     *
     * @return The value that should be displayed on the field
     */
    @Override
    public abstract String getValue(T object);

    /**
     * Sets the value from the input field on the object
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, String value);
    
    /**
     * Class constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public FilterDropdown() {
        this(getDefaultResources(), false);
    }
    
    /**
     * Class constructor
     * 
     * @param bRequired - Whether or not this filed is required
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public FilterDropdown(boolean bRequired) {
        this(getDefaultResources(), bRequired);
    }
    
    /**
     * Class constructor
     *
     * @param resources - The resources the search box will use
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public FilterDropdown(FilterDropdownResources resources) {
        this(resources, false);
    }
    
    /**
     * Class constructor
     *
     * @param resources - The resources the search box will use
     * @param bRequired - Whether or not this filed is required
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public FilterDropdown(FilterDropdownResources resources, boolean bRequired) {
        this.resources = resources;
        this.resources.filterDropdownStyle().ensureInjected();
        this.initWidget(uiBinder.createAndBindUi(this));
        textBox.addKeyUpHandler(this);
        textBox.addClickHandler(this);
        this.bRequired = bRequired;
    }

    /**
     * The resources interface for the text filter
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public interface FilterDropdownResources extends ClientBundle {

        /**
         * Retrieves an implementation of the Style interface generated using the specified css file
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return An implementation of the Style interface
         */
        @Source("FilterDropdown.css")
        Style filterDropdownStyle();
    }

    /**
     * The css resource for the text filter
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public interface Style extends CssResource {

        /**
         * The style for the text box
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return The name of the compiled style
         */
        String textBox();

        /**
         * The style drop down element
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return The name of the compiled style
         */
        String dropDown();

        /**
         * The style for the info text that is displayed on the drop down before data is loaded
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return The name of the compiled style
         */
        String infoText();

        /**
         * The style for the info text that is displayed on the drop down if there is no results
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return The name of the compiled style
         */
        String noResultText();
        
        /**
         * The style for the dropDownPopUpContainer with hidden overflow field.
         * 
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         * 
         * @return The name of the compiled style
         */
        String dropDownPopUpContainer();

    }

    /**
     * Create an instance on the default resources object if the
     * DEFAULT_RESOURCES variable is null, if not it just return the object in
     * the DEFAULT_RESOURCES variable
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @return the default resource object
     */
    private static FilterDropdownResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(FilterDropdownResources.class);
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
     * Sets the selected display item
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @param selectedDisplayItem - The display item the user clicked on in the drop down
     */
    public void setSelectedDisplayItem(FilterDropdownRecordWidget<ListType> selectedDisplayItem) {
        if (selectedDisplayItem != null) {
            this.selectedDisplayItem = selectedDisplayItem;
            this.selectedDisplayItem.setSelectedState(false);
            this.selectedObject = this.selectedDisplayItem.getItemValue();
            textBox.setText(this.selectedDisplayItem.getItemSelectionText());
            destroyDropDownPopup();
        }
    }

    /**
     * Sets minimum characters required for search service call to be made
     *
     * @param minCharCount The minimum characters required for search service call to be made
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public void setMinCharRequiredForSearch(int minCharCount) {
        this.minCharCount = minCharCount;
    }

    /**
     * The key up event handler
     *
     * @param event - The key up event that is being handled
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.isLeftArrow() || event.isRightArrow()) {
            // Capture these to prevent further execution
            // If the left or right arrow is pressed, we do not want any else if's to fire
        } else if (event.isDownArrow()) {
            if (dropDownPopup != null) {
                String itemText = dropDownPopup.selectNextItem();
                if (itemText != null) {
                    textBox.setText(itemText);
                }
            }
        } else if (event.isUpArrow()) {
            if (dropDownPopup != null) {
                String itemText = dropDownPopup.selectPreviousItem();
                if (itemText != null) {
                    textBox.setText(itemText);
                } else {
                    textBox.setText(previousSearchString);
                }
            }
        } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
            if (dropDownPopup != null) {
                setSelectedDisplayItem(dropDownPopup.getSelectedItem());
            }
        } else if (textBox.getText().length() >= this.minCharCount && !textBox.getText().equals(previousSearchString)) {
            selectedObject = null;
            previousSearchString = textBox.getText();
            createDropDownPopup();

            applyFilter();
            
        } else if (textBox.getText().length() < this.minCharCount) {
            selectedObject = null;
            previousSearchString = textBox.getText();
            destroyDropDownPopup();
        }
    }

    /**
     * This action is used to apply a new filter using the text in the textbox
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    private void applyFilter() {
        ArrayList<FilterDropdownRecordWidget<ListType>> tempListing =
            new ArrayList<FilterDropdownRecordWidget<ListType>>();
        
        for (FilterDropdownRecordWidget<ListType> item : currentDisplayItems) {
            if (item.compareToSearchCriteria(textBox.getText())) {
                tempListing.add(item);
            }
        }
        dropDownPopup.setSelectableItems(tempListing);
    }
    
    /**
     * Creates the drop down that displays the search results
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    private void createDropDownPopup() {
        if (dropDownPopup == null) {
            dropDownPopup = new FilterDropdownListBox<ListType>(resources);
            //setting popup dropdown style overflow to hidden
            dropDownPopup.setStyleName(this.resources.filterDropdownStyle().dropDownPopUpContainer());
            dropDownPopup.setPopupPosition(this.getAbsoluteLeft(), this.getAbsoluteTop() + this.getOffsetHeight());
            dropDownPopup.setWidth(this.getOffsetWidth() + Unit.PX.getType());
            dropDownPopup.show();
            dropDownPopup.addCloseHandler(new CloseHandler<PopupPanel>() {

                /**
                 * This will happen when the dropdown closes
                 * 
                 * @author Michael Barnard <michael.barnard@a24group.com>
                 * @since  11 Jul 2014
                 * 
                 * @param event - The event that triggered the close
                 */
                @Override
                public void onClose(CloseEvent<PopupPanel> event) {
                    dropDownPopup = null;
                }
            });
        }
    }

    /**
     * Destroys the drop down that displays the search results
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    private void destroyDropDownPopup() {
        if (dropDownPopup != null) {
            dropDownPopup.hide();
            dropDownPopup = null;
        }
    }

    /**
     * Set the data on the search box that was returned by the service call
     *
     * @param searchResults - The search results
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public void setData(List<ListType> searchResults) {
        currentDisplayItems.clear();
        for (ListType resultItem : searchResults) {
            FilterDropdownRecordWidget<ListType> tempDisplayItem = createDisplayWidgetInstance();
            tempDisplayItem.setItemValue(resultItem);
            tempDisplayItem.setParentSearchBox(this);
            currentDisplayItems.add(tempDisplayItem);
        }
        if (dropDownPopup != null) {
            dropDownPopup.setSelectableItems(currentDisplayItems);
        }
    }

    /**
     * Creates an instance of the widget that will be used to display records on the drop down
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @return an instance of the widget that will be used to display records on the drop down
     */
    public abstract FilterDropdownRecordWidget<ListType> createDisplayWidgetInstance();

    /**
     * Clears the selected item on the search box
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public void clearSelectedItem() {
        textBox.setText("");
        selectedObject = null;
    }

    /**
     * Check whether the input field is required or not
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @return Whether the input field is required
     */
    @Override
    public boolean isRequired() {
        return this.bRequired;
    }

    /**
     * This will set whether or not the field is required
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @param required - Whether the field is required or not
     */
    @Override
    public void setRequired(boolean required) {
        this.bRequired = required;
    }

    /**
     * Get this component as a widget
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @return This component as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this.asWidget();
    }

    /**
     * Set whether this field is read only
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @param readOnly Whether this field is read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        this.bReadOnly = readOnly;
    }

    /**
     * Get whether this field is read only
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @return Whether this field is read only
     */
    @Override
    public boolean isReadOnly() {
        return this.bReadOnly;
    }

    /**
     * Get the return type of this component
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @return String type
     */
    @Override
    public Class<String> getReturnType() {
        return String.class;
    }
    
    /**
     * Add a handler on the change of the data selected
     *
     * @param handler - The value change handler
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     *
     * @return null
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return null;
    }
    
    /**
     * This function will happen on click of the component
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     * 
     * @param event - The event that caused this click
     */
    @Override
    public void onClick(ClickEvent event) {
        if (textBox.getText().length() == 0) {
            createDropDownPopup();
            dropDownPopup.setSelectableItems(currentDisplayItems);
        } else {
            createDropDownPopup();
            applyFilter();
        }
    }
    
    /**
     * Gets this value of this component.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     *
     * @return the value of this component
     */
    @Override
    public String getValue() {
        return textBox.getText();
    }

    /**
     * Sets this object's value without firing any events. This should be
     * identical to calling setValue(value, false).
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     * <p>
     * Widgets must accept null as a valid value. By convention, setting a widget to
     * null clears value, calling getValue() on a cleared widget returns null. Widgets
     * that can not be cleared (e.g. {@link CheckBox}) must find another valid meaning
     * for null input.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     *
     * @param value the object's new value
     */
    @Override
    public void setValue(String value) {
        if (value == null) {
            textBox.setText("");
        } else {
            textBox.setText(value);
        }
    }

    /**
     * Sets this object's value. Fires
     * {@link com.google.gwt.event.logical.shared.ValueChangeEvent} when
     * fireEvents is true and the new value does not equal the existing value.
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 Jul 2014
     *
     * @param value the object's new value
     * @param fireEvents fire events if true and above param value has a new value
     */
    @Override
    public void setValue(String value, boolean fireEvents) {
        setValue(value);
    }

}
