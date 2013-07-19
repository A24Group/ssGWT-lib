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

import org.ssgwt.client.i18n.SSDate;
import org.ssgwt.client.ui.datepicker.DateBox;
import org.ssgwt.client.ui.datepicker.SSDateBox;
import org.ssgwt.client.ui.datepicker.SSDatePicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.constants.DateTimeConstants;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * The date filter that can be used on the SSDataGrid with a FilterSortHeader
 *
 * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
 * @since  16 August 2012
 */
public class DateFilter extends AbstractHeaderFilter {

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * Holds an instance of the default resources
     */
    private static DateFilterResources DEFAULT_RESOURCES;

    /**
     * Holds an instance of resources
     */
    private DateFilterResources resources;

    /**
     * Holds a list of filters that will be used to populate the filter listbox
     */
    private String[] filters = {
        "Customised date range",
        "Today",
        "Yesterday",
        "This week (Mon-Today)",
        "Last 7 days",
        "Last week (Mon-Sun)",
        "Last working week (Mon-Fri)",
        "Last 14 days",
        "This month",
        "Last 30 days",
        "Last month"
    };

    /**
     * The filter container
     */
    @UiField
    FocusPanel filterContainer;

    /**
     * Holds the index of the filter listbox that will be used to remember the index
     * when the listbox gets disabled
     */
    private int filterIndex;

    /**
     * Used to remember the from date when the datebox gets disabled
     */
    private SSDate previousFromDate;

    /**
     * Used to remember the to date when the datebox gets disabled
     */
    private SSDate previousToDate;

    /**
     * The title label
     */
    @UiField
    Label titleLabel;

    /**
     * The check box on the filter popup
     */
    @UiField
    CheckBox checkBox;

    /**
     * The icon that clears a the filter criteria
     */
    @UiField
    Image removeFilterIcon;

    /**
     * The listbox that holds the list of preset filters
     */
    @UiField
    ListBox filterList;

    /**
     * The from label
     */
    @UiField
    Label fromLabel;

    /**
     * The to label
     */
    @UiField
    Label toLabel;

    /**
     * The datebox that will hold the from date
     */
    @UiField (provided = true)
    DateBox fromDateBox;

    /**
     * The datebox that will hold the to date
     */
    @UiField (provided = true)
    DateBox toDateBox;

    /**
     * The apply button
     */
    @UiField
    Button applyButton;

    /**
     * UiBinder interface for the composite
     *
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    interface Binder extends UiBinder<Widget, DateFilter> {
    }

    /**
     * The resources interface for the text filter
     *
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    public interface DateFilterResources extends Resources {

        /**
         * Retrieves an implementation of the Style interface generated using the specified css file
         *
         * @return An implementation of the Style interface
         */
        @Source("DateFilter.css")
        Style textFilterStyle();
    }

    /**
     * The css resource for the text filter
     *
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    public interface Style extends CssResource {

        /**
         * The style for the panel that contains all the elements on the text filter
         *
         * @return The name of the compiled style
         */
        String textFilterStyle();

        /**
         * The style for the speech bubble arrow border
         *
         * @return The name of the compiled style
         */
        String arrowBorderStyle();

        /**
         * The style for the speech bubble arrow
         *
         * @return The name of the compiled style
         */
        String arrowStyle();

        /**
         * The style for the title
         *
         * @return The name of the compiled style
         */
        String titleStyle();

        /**
         * The style the sets the position of the remove filter icon
         *
         * @return The name of the compiled style
         */
        String removeFilterIconStyle();

        /**
         * The style for the container that holds the checkbox
         *
         * @return The name of the compiled style
         */
        String checkBoxContainerStyle();

        /**
         * The style for the checkbox
         *
         * @return The name of the compiled style
         */
        String checkBoxStyle();

        /**
         * The style for the label of the checkbox
         *
         * @return The name of the compiled style
         */
        String checkBoxLabelStyle();

        /**
         * The style for the text box label
         *
         * @return The name of the compiled style
         */
        String textBoxLabelStyle();

        /**
         * The style for the container that holds the text box
         *
         * @return The name of the compiled style
         */
        String textBoxStyleContainer();

        /**
         * The style for the text box
         *
         * @return The name of the compiled style
         */
        String textBoxStyle();

        /**
         * The style for the container that holds the apply button
         *
         * @return The name of the compiled style
         */
        String applyButtonContainer();

        /**
         * The style for the apply button
         *
         * @return The name of the compiled style
         */
        String applyButton();

        /**
         * The style for the apply button when it is selected
         *
         * @return The name of the compiled style
         */
        String applyButtonDown();

        /**
         * The style for the date filter drop down list
         *
         * @return The name of the compiled style
         */
        String filterListStyle();

        /**
         * Style for the date labels
         *
         * @return The name of the compiled style
         */
        String dateLabelStyle();

        /**
         * Style for the date boxes
         *
         * @return The name of the compiled style
         */
        String dateBoxStyle();

        /**
         * Style for the panels containing the to and from SSDate boxes and labels
         *
         * @return The name of the compiled style
         */
        String floatLeft();

        /**
         * Style for the flow panel containing the parents of the to and from date boxes and labels
         *
         * @return The name of the compiled style
         */
        String dateBoxStyleContainer();
    }

    /**
     * The criteria object that will be used to represent the data enter by the user on the text filter
     *
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    public static class DateFilterCriteria extends Criteria {

        /**
         * Date holding the to date of the DateFilter
         */
        private SSDate toDate;

        /**
         * Retrieve the to date that indicates the end of the search range
         *
         * @return The to date that indicates the end of the search range
         */
        public SSDate getToDate() {
            return toDate;
        }

        /**
         * Sets the to date that indicates the end of the search range
         *
         * @param toDate - The new value for the to date
         */
        public void setToDate(SSDate toDate) {
            this.toDate = toDate;
        }

        /**
         * Date holding the from date of the DateFilter
         */
        private SSDate fromDate;

        /**
         * Retrieve the from date that indicates the start of the search range
         *
         * @return The from date that indicates the start of the search range
         */
        public SSDate getFromDate() {
            return fromDate;
        }

        /**
         * Sets the from date that indicates the start of the search range
         *
         * @param fromDate - The new value for the from date
         */
        public void setFromDate(SSDate fromDate) {
            this.fromDate = fromDate;
        }

        /**
         * Flag to indicate if the user is looking for empty entries only
         */
        private boolean findEmptyEntriesOnly;

        /**
         * Retrieve the flag that indicates if the user is looking for empty entries only
         *
         * @return The flag that indicates if the user is looking for empty entries only
         */
        public boolean isFindEmptyEntriesOnly() {
            return findEmptyEntriesOnly;
        }

        /**
         * Sets the flag that the user is looking for empty entries only or not
         *
         * @param findEmptyEntriesOnly - The new value for the flag value
         */
        public void setFindEmptyEntriesOnly(boolean findEmptyEntriesOnly) {
            this.findEmptyEntriesOnly = findEmptyEntriesOnly;
        }

    }

    /**
     * The default class constructor
     *
     * @param datePickerStyle - The style to apply to the datepickers
     */
    public DateFilter(String datePickerStyle) {
        this(datePickerStyle, getDefaultResources());
    }

    /**
     * Class constructor uses two dates to specify the start dates for both
     * the from date and the to date
     *
     * @param datePickerStyle - The style to apply to the datepickers
     * @param fromMinDate - The minimum date for the from datepicker
     * @param toMinDate - The minimum date for the to datepicker
     * @param fromMaxDate - The maximum date for the from datepicker
     * @param toMaxDate - The maximum date for the to datepicker
     */
    public DateFilter(String datePickerStyle, SSDate fromMinDate, SSDate toMinDate, SSDate fromMaxDate, SSDate toMaxDate) {
        this(datePickerStyle, getDefaultResources(), fromMinDate, toMinDate, fromMaxDate, toMaxDate);
    }

    /**
     * Class constructor that takes a custom resources class
     *
     * @param datePickerStyle - The style to apply to the datepickers
     * @param resources - The resources the text filter should use
     */
    public DateFilter(String datePickerStyle, DateFilterResources resources) {
        this(
            datePickerStyle,
            resources,
            new SSDate(70, 0, 1),
            new SSDate(70, 0, 1),
            new SSDate(120, 11, 31),
            new SSDate(120, 11, 31)
        );
    }

    /**
     * Class constructor that takes a custom resources class
     * and uses two dates to specify the start dates for both
     * the from date and the to date
     *
     * @param datePickerStyle - The style to apply to the datepickers
     * @param resources - The resources the text filter should use
     * @param fromMinDate - The minimum date for the from datepicker
     * @param toMinDate - The minimum date for the to datepicker
     * @param fromMaxDate - The maximum date for the from datepicker
     * @param toMaxDate - The maximum date for the to datepicker
     */
    public DateFilter(
        String datePickerStyle,
        DateFilterResources resources,
        SSDate fromMinDate,
        SSDate toMinDate,
        SSDate fromMaxDate,
        SSDate toMaxDate
    ) {
        super(true);
        this.resources = resources;
        this.resources.textFilterStyle().ensureInjected();
        this.setStyleName("");
        // Create datepickers, set their styles, and insert them into the dateboxes
        // To date
        SSDatePicker toDatePicker = new SSDatePicker();
        toDatePicker.setMinimumDate(toMinDate);
        toDatePicker.setMaximumDate(toMaxDate);
        toDatePicker.setStyleName(datePickerStyle);
        toDateBox = new DateBox(toDatePicker, null, SSDateBox.DEFAULT_FORMAT);
        toDateBox.setStyleName(getResources().textFilterStyle().dateBoxStyle());
        // From date
        SSDatePicker fromDatePicker = new SSDatePicker();
        fromDatePicker.setMinimumDate(fromMinDate);
        fromDatePicker.setMaximumDate(fromMaxDate);
        fromDatePicker.setStyleName(datePickerStyle);
        fromDateBox = new DateBox(fromDatePicker, null, SSDateBox.DEFAULT_FORMAT);
        fromDateBox.setStyleName(getResources().textFilterStyle().dateBoxStyle());

        this.setWidget(uiBinder.createAndBindUi(this));
        setCriteria(new DateFilterCriteria());
        addKeyEventHandlers();
        addRemoveIconEventHandlers();
        addApplyButtonEventHandlers();
        addCheckBoxEventHandlers();
        populateListBox();
        addListBoxEventHandlers();
        addDateBoxListeners();
    }

    /**
     * This will add event handlers for key events on the filter widget
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 May 2013
     */
    private void addKeyEventHandlers() {
        filterContainer.addKeyUpHandler(new KeyUpHandler() {

            /**
             * This will handle the key up events on the input
             *
             * @param event The key up event
             *
             * @author Ruan Naude <nauderuan777@gmail.com>
             * @since 27 May 2013
             */
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getSource() == filterContainer &&
                    event.getNativeKeyCode() == KeyCodes.KEY_ENTER
                ) {
                    applyButton.removeStyleName(resources.textFilterStyle().applyButtonDown());
                    closeFilterPopup(false);
                }
            }
        });
    }

    /**
     * This function will set the date boxes to certain dates, depending on what option was selected
     * from the filter list.
     *
     * @param currentDate date object containing the current date
     * @param range String describing what date range should be set
     *
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since  15 Aug 2012
     *
     * @return void
     */
    @SuppressWarnings("deprecation")
    protected void setDates(SSDate currentDate, String range) {
        SSDate fromDate = currentDate.clone();
        SSDate toDate = currentDate.clone();
        if (range.equals("Customised date range")) {
            toDateBox.setValue(null);
            fromDateBox.setValue(null);

        } else if (range.equals("Today")) {
            toDateBox.setValue(currentDate);
            fromDateBox.setValue(currentDate);

        } else if (range.equals("Yesterday")) {
            SSDate date = currentDate;
            date.setDate(date.getDate() - 1);

            toDateBox.setValue(date);
            fromDateBox.setValue(date);

        } else if (range.equals("This week (Mon-Today)")) {
            DateTimeConstants constants = LocaleInfo.getCurrentLocale().getDateTimeConstants();

            // THIS GETS -1 BECAUSE firstDayOfTheWeek() ADDS A DAY FOR SOME REASON.
            int firstDay = Integer.parseInt(constants.firstDayOfTheWeek()) -1;
            // Get the offset that will be used to calculate what SSDate the Monday is on.
            int offset = firstDay - fromDate.getDay();
            fromDate.setDate(fromDate.getDate() + offset);

            fromDateBox.setValue(fromDate);
            toDateBox.setValue(currentDate);

        } else if (range.equals("Last 7 days")) {
            fromDate.setDate(fromDate.getDate() - 7);

            toDateBox.setValue(toDate);
            fromDateBox.setValue(fromDate);

        } else if (range.equals("Last week (Mon-Sun)")) {
            DateTimeConstants constants = LocaleInfo.getCurrentLocale().getDateTimeConstants();
            // THIS GETS -1 BECAUSE firstDayOfTheWeek() ADDS A DAY FOR SOME REASON.
            int firstDay = Integer.parseInt(constants.firstDayOfTheWeek()) -1;
            // Get the offset that will be used to calculate what SSDate the Monday is on.
            int offset = (firstDay - fromDate.getDay()) - 7;
            fromDate.setDate(fromDate.getDate() + offset);

            toDate.setDate(fromDate.getDate() + 6);

            toDateBox.setValue(toDate);
            fromDateBox.setValue(fromDate);

        } else if (range.equals("Last working week (Mon-Fri)")) {
            DateTimeConstants constants = LocaleInfo.getCurrentLocale().getDateTimeConstants();
            // THIS GETS -1 BECAUSE firstDayOfTheWeek() ADDS A DAY FOR SOME REASON.
            int firstDay = Integer.parseInt(constants.firstDayOfTheWeek()) -1;
            // Get the offset that will be used to calculate what SSDate the Monday is on.
            int offset = (firstDay - fromDate.getDay()) - 7;
            fromDate.setDate(fromDate.getDate() + offset);

            toDate.setDate(fromDate.getDate() + 4);

            toDateBox.setValue(toDate);
            fromDateBox.setValue(fromDate);

        } else if (range.equals("Last 14 days")) {
            fromDate.setDate(fromDate.getDate() - 14);

            toDateBox.setValue(toDate);
            fromDateBox.setValue(fromDate);

        } else if (range.equals("This month")) {
            int offset = fromDate.getDate() - 1;
            fromDate.setDate(fromDate.getDate() - offset);

            toDateBox.setValue(currentDate);
            fromDateBox.setValue(fromDate);

        } else if (range.equals("Last 30 days")) {
            fromDate.setDate(fromDate.getDate() - 30);

            System.out.println(fromDate);

            toDateBox.setValue(toDate);
            fromDateBox.setValue(fromDate);

        } else if (range.equals("Last month")) {
            int year = toDate.getYear();
            int month = toDate.getMonth() - 1;

            // If the current month is january, this will make sure we get december of the previous year's info.
            if (month < 0) {
                --year;
                month = 11;
            }

            toDate.setDate(toDate.getDate() - toDate.getDate());

            fromDate = new SSDate(year, month, 1);

            toDateBox.setValue(toDate);
            fromDateBox.setValue(fromDate);
        }
    }

    /**
     * Sets the listbox to have it's first index item selected
     */
    private void resetListBox() {
        filterList.setSelectedIndex(0);
    }

    /**
     * Adds eventhandlers to the DateBoxes on the DateFilter
     */
    private void addDateBoxListeners() {
        toDateBox.addValueChangeHandler(new ValueChangeHandler<SSDate>() {

            /**
             * The function that will be called if the user changes the date in the to box
             *
             * @param even The vent that should be handled.
             */
            @Override
            public void onValueChange(ValueChangeEvent<SSDate> event) {
                resetListBox();
            }
        });

        fromDateBox.addValueChangeHandler(new ValueChangeHandler<SSDate>() {

            /**
             * The function that will be called if the user changes the date in the from box
             *
             * @param even The vent that should be handled.
             */
            @Override
            public void onValueChange(ValueChangeEvent<SSDate> event) {
                resetListBox();
            }
        });
    }

    /**
     * Adds eventhandlers to the listbox on the DateFilter
     */
    private void addListBoxEventHandlers() {
        filterList.addChangeHandler(new ChangeHandler() {

            /**
             * The function that will be called if the selected value on the listbox
             * changes
             *
             * @param even The vent that should be handled.
             */
            @Override
            public void onChange(ChangeEvent event) {
                String range = filterList.getItemText(filterList.getSelectedIndex());
                SSDate currentDate = new SSDate();
                currentDate.setHours(0);
                currentDate.setMinutes(0);
                currentDate.setSeconds(0);

                setDates(currentDate, range);
            }
        });
    }

    /**
     * Adds event handlers to the checkbox on the TextFilter
     */
    private void addCheckBoxEventHandlers() {
        this.checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

            /**
             * The function that will be called if the value on the check box changes
             *
             * @param event The event that should be handled
             */
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                if (event.getValue()) {

                    previousFromDate = fromDateBox.getValue();
                    previousToDate = toDateBox.getValue();
                    filterIndex = filterList.getSelectedIndex();

                    fromDateBox.setEnabled(false);
                    toDateBox.setEnabled(false);
                    filterList.setEnabled(false);

                    fromDateBox.setValue(null);
                    toDateBox.setValue(null);
                    filterList.setSelectedIndex(0);
                } else {
                    fromDateBox.setEnabled(true);
                    toDateBox.setEnabled(true);
                    filterList.setEnabled(true);

                    fromDateBox.setValue(previousFromDate);
                    toDateBox.setValue(previousToDate);
                    filterList.setSelectedIndex(filterIndex);
                }
            }
        });
    }

    /**
     * Populates the listbox.
     */
    private void populateListBox() {
        filterList.clear();
        // Populate the list box
        for (int i = 0; i < filters.length; i++) {
            filterList.addItem(filters[i]);
        }
    }

    /**
     * Function that adds the required events handlers to the apply button
     */
    private void addApplyButtonEventHandlers() {
        this.applyButton.addMouseDownHandler(new MouseDownHandler() {

            /**
             * The event that is call if the apply button fires a mouse down event
             *
             * @param event - The mouse down event that should be handled
             */
            @Override
            public void onMouseDown(MouseDownEvent event) {
                applyButton.addStyleName(resources.textFilterStyle().applyButtonDown());
            }
        });

        this.applyButton.addMouseUpHandler(new MouseUpHandler() {

            /**
             * The event that is call if the apply button fires a mouse up event
             *
             * @param event - The mouse up event that should be handled
             */
            @Override
            public void onMouseUp(MouseUpEvent event) {
                applyButton.removeStyleName(resources.textFilterStyle().applyButtonDown());
                closeFilterPopup(false);
            }
        });

        this.applyButton.addMouseOutHandler(new MouseOutHandler() {

            /**
             * The event that is call if the apply button fires a mouse out event
             *
             * @param event - The mouse out event that should be handled
             */
            @Override
            public void onMouseOut(MouseOutEvent event) {
                applyButton.removeStyleName(resources.textFilterStyle().applyButtonDown());
            }
        });
    }

    /**
     * Adds the required events handlers to the remove filter icon
     */
    private void addRemoveIconEventHandlers() {
        this.removeFilterIcon.addMouseOverHandler(new MouseOverHandler() {

            /**
             * The event that is call if the remove criteria icon fires a mouse over event
             *
             * @param event - The mouse over event that should be handled
             */
            @Override
            public void onMouseOver(MouseOverEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconOver());
            }
        });

        this.removeFilterIcon.addMouseOutHandler(new MouseOutHandler() {

            /**
             * The event that is call if the remove criteria icon fires a mouse out event
             *
             * @param event - The mouse out event that should be handled
             */
            @Override
            public void onMouseOut(MouseOutEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconUp());
            }
        });

        this.removeFilterIcon.addMouseDownHandler(new MouseDownHandler() {

            /**
             * The event that is call if the remove criteria icon fires a mouse down event
             *
             * @param event - The mouse down event that should be handled
             */
            @Override
            public void onMouseDown(MouseDownEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconDown());
            }
        });

        this.removeFilterIcon.addMouseUpHandler(new MouseUpHandler() {

            /**
             * The event that is call if the remove criteria icon fires a mouse up event
             *
             * @param event - The mouse up event that should be handled
             */
            @Override
            public void onMouseUp(MouseUpEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconOver());
                clearFields();
            }
        });
    }

    /**
     * Create an instance on the default resources object if it the
     * DEFAULT_RESOURCES variable is null if not it just return the object in
     * the DEFAULT_RESOURCES variable
     *
     * @return the default resource object
     */
    private static DateFilterResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(DateFilterResources.class);
        }
        return DEFAULT_RESOURCES;
    }

    /**
     * Sets the filter's title
     */
    @Override
    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }

    /**
     * Getter for the resources instance
     *
     * @return The resources
     */
    public DateFilterResources getResources() {
        return this.resources;
    }

    /**
     * Returns the criteria object
     *
     * @return DateFilterCriteria The criteria object
     */
    @Override
    public DateFilterCriteria getCriteria() {
        return (DateFilterCriteria)this.filterCirteria;
    }

    /**
     * Check if the filter has been activated
     *
     * @return boolean Whether or not the filter is active
     */
    @Override
    protected boolean checkFilterActive() {
        if (getCriteria().isFindEmptyEntriesOnly()) {
            return true;
        }
        if (getCriteria().getFromDate() != null) {
            return true;
        }
        if (getCriteria().getToDate() != null) {
            return true;
        }

        return false;
    }

    /**
     * Updates the info in the criteria object to match the info on the filter
     */
    @Override
    protected void updateCriteriaObject() {
        getCriteria().setFindEmptyEntriesOnly(checkBox.getValue());
        getCriteria().setFromDate(fromDateBox.getValue());
        getCriteria().setToDate(toDateBox.getValue());
    }

    /**
     * Clears all the data in the criteria object
     */
    @Override
    protected void setCriteriaObjectEmpty() {
        getCriteria().setFindEmptyEntriesOnly(false);
        getCriteria().setFromDate(null);
        getCriteria().setToDate(null);

    }/**
     * Retrieves the check box that is displayed on the TextFilter.
     * This function is protected as it is only used by test cases.
     *
     * @return instance of the check box that is displayed on the DateFilter
     */
    protected CheckBox getCheckBox() {
        return checkBox;
    }

    /**
     * Retrieves the title label that is displayed on the TextFilter.
     * This function is protected as it is only used by test cases.
     *
     * @return instance of the title label that is displayed on the DateFilter
     */
    protected Label getTitleLabel() {
        return titleLabel;
    }

    /**
     * Retrieves the filter listbox that is displayed on the DateFilter.
     * This function is protected as it is only used by test cases.
     *
     * @return instance of the filter listbox that is displayed on the DateFilter
     */
    protected ListBox getFilterList() {
        return filterList;
    }

    /**
     * Retrieves the from date box that is displayed on the DateFilter.
     * This function is protected as it is only used by test cases.
     *
     * @return instance of the from date box that is displayed on the DateFilter
     */
    protected DateBox getFromDateBox() {
        return fromDateBox;
    }

    /**
     * Retrieves the to date box that is displayed on the DateFilter.
     * This function is protected as it is only used by test cases.
     *
     * @return instance of the to date box that is displayed on the DateFilter
     */
    protected DateBox getToDateBox() {
        return toDateBox;
    }

    /**
     * Sets the values on the filter to the values in the criteria object
     */
    @Override
    protected void updateFieldData() {
        checkBox.setValue(getCriteria().isFindEmptyEntriesOnly());
        fromDateBox.setValue(getCriteria().getFromDate());
        toDateBox.setValue(getCriteria().getToDate());
        fromDateBox.setEnabled(!checkBox.getValue());
        toDateBox.setEnabled(!checkBox.getValue());
        filterList.setEnabled(!checkBox.getValue());
    }

    /**
     * Clears all the fields on the filter
     */
    @Override
    protected void clearFields() {
        checkBox.setValue(false);
        fromDateBox.setValue(null);
        toDateBox.setValue(null);
        filterList.setSelectedIndex(0);
        fromDateBox.setEnabled(true);
        toDateBox.setEnabled(true);
        filterList.setEnabled(true);
    }

    /**
     * Sets focus on the main input of the filter
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 24 May 2013
     */
    @Override
    public void setFocusOnMainInput() {
        filterList.setFocus(true);
    }
}
