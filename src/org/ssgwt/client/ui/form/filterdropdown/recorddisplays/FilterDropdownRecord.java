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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the filter dropdown record item that will be used to display a single string item as a result
 *
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  10 Jul 2014
 * 
 * @param <ListType> The type that is used by the specified list items.
 */
public abstract class FilterDropdownRecord<ListType> extends FilterDropdownRecordWidget<ListType> {

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * The default resource to use for the FilterDropdownRecord class
     */
    private static FilterDropdownRecordResources DEFAULT_RESOURCES;

    /**
     * The resource to use for the FilterDropdownRecord class
     */
    private FilterDropdownRecordResources resources;

    /**
     * The object that represents the current value in the component
     */
    private ListType objCurrentValue;

    /**
     * The main container of the record item
     */
    @UiField
    FlowPanel resultRecord;

    /**
     * The label for displaying the text
     */
    Label textLabel;

    /**
     * UiBinder interface for the composite
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    interface Binder extends UiBinder<Widget, FilterDropdownRecord<?>> {
    }

    /**
     * Class Constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public interface FilterDropdownRecordResources extends ClientBundle {

        /**
         * The style source to be used in this widget
         */
        @Source("FilterDropdownRecord.css")
        Style filterDropdownRecordStyle();
    }

    /**
     * The Css style recourse items to use in this widget
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public interface Style extends CssResource {

        /**
         * The style for the panel that contains the whole search result record.
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return The name of the compiled style
         */
        String searchResultRecord();
        
        /**
         * The style for the text label.
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return The name of the compiled style
         */
        String textLabel();

        /**
         * The style for a selected record.
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  10 Jul 2014
         *
         * @return The name of the compiled style
         */
        String searchResultRecordSelected();
    }

    /**
     * This function will check if there is already a default resource to
     * use for the filter dropdown and if not, will create a default resource
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @return The default resource
     */
    private static FilterDropdownRecordResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(FilterDropdownRecordResources.class);
        }
        return DEFAULT_RESOURCES;
    }

    /**
     * Class constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public FilterDropdownRecord() {
        this(getDefaultResources());
    }

    /**
     * Class constructor for user record with custom recource
     *
     * @param resources The resource to be used for the SearchBoxUserRecord
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    public FilterDropdownRecord(FilterDropdownRecordResources resources) {
        super();
        this.resources = resources;
        this.resources.filterDropdownRecordStyle().ensureInjected();
        this.add(uiBinder.createAndBindUi(this));
        //create the labels and image for the user record
        textLabel = new Label();

        //add style to the record elements
        textLabel.setStyleName(resources.filterDropdownRecordStyle().textLabel());

        resultRecord.add(textLabel);
    }

    /**
     * Sets the Value for the record item
     *
     * @param itemValue - The new value for the item
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    @Override
    public void setItemValue(ListType itemValue) {
        this.objCurrentValue = itemValue;
        // Set the value on the item
        textLabel.setText((String)itemValue);
    }

    /**
     * Gets the Value for the record item
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @return The user record's VO
     */
    @Override
    public ListType getItemValue() {
        return this.objCurrentValue;
    }

    /**
     * Returns the text for when the record is selected
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     *
     * @return The record text to be displayed
     */
    @Override
    public String getItemSelectionText() {
        return (String)this.getItemValue();
    }

    /**
     * This will set the selected state by updating style accordingly
     *
     * @param selected - Whether the item is selected or not
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     */
    @Override
    public void setSelectedState(boolean selected) {
        if (selected) {
            resultRecord.addStyleName(resources.filterDropdownRecordStyle().searchResultRecordSelected());
        } else {
            resultRecord.removeStyleName(resources.filterDropdownRecordStyle().searchResultRecordSelected());
        }
    }
    
    /**
     * This function will be used to compare this item to the search criteria
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 Jul 2014
     * 
     * @param sCriteria - The criteria entered in the search box
     * 
     * @return true if it matches the criteria, false otherwise
     */
    @Override
    public boolean compareToSearchCriteria(String sCriteria) {
        String sItemValue = (String)this.getItemValue();
        return sItemValue.startsWith(sCriteria);
    }

}