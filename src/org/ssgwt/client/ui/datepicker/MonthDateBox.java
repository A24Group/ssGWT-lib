/**
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.ssgwt.client.ui.datepicker;

import org.ssgwt.client.i18n.DateTimeFormat;
import org.ssgwt.client.i18n.SSDate;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A text box that shows a month picker when the user focuses on it.
 *
 * @author Ruan Naude <nauderuan777@gmail.com>
 * @since 27 Dec 2012
 */
@SuppressWarnings("deprecation")
public class MonthDateBox extends Composite implements HasValue<SSDate> {

    /**
     * This is the default date format that will be used to display the date
     * in the text box if no date format is set
     */
    private static final String DEFAULT_DATE_FORMAT = "MMMM yyyy";

    /**
     * Variable to store date format for custom date format
     */
    private String dateFormat;

    /**
     * This is the text box for the display of the selected date
     */
    private final TextBox box = new TextBox();

    /**
     * The month picker to be displayed in the popup
     */
    private final SSMonthPicker monthPicker;

    /**
     * The popup panel to display the month picker in
     */
    private final PopupPanel popup;

    /**
     * Flag to determine if the month picker can be shown
     */
    private boolean allowMPShow = true;

    /**
     * The handlers for the month date box
     */
    protected MonthDateBoxHandler handler = new MonthDateBoxHandler();

    /**
     * Class constructor
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public MonthDateBox() {
        //Create the popup panel and month picker
        this.popup = new PopupPanel(true);
        this.monthPicker = new SSMonthPicker(popup);

        //add popup to box and content to popup
        popup.addAutoHidePartner(box.getElement());
        popup.setWidget(monthPicker);
        addHandlers(handler);

        //clear any styles on the popup
        popup.setStyleName("");
        initWidget(box);
    }

    /**
     * This will set the maximum date the user is allowed to select
     *
     * @param maxDate - The maximum date the user is allowed to select
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void setMaxDate(SSDate maxDate) {
        this.monthPicker.setMaxDate(maxDate);
    }

    /**
     * This will set the minimum date the user is allowed to select
     *
     * @param minDate - The minimum date the user is allowed to select
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void setMinDate(SSDate minDate) {
        this.monthPicker.setMinDate(minDate);
    }

    /**
     * Adds a {@link ValueChangeEvent} handler.
     *
     * @param handler the handler
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     *
     * @return the registration for the event
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<SSDate> handler) {
        return null;
    }

    /**
     * Gets the date the user selected from the popup
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     *
     * @return The selected date
     */
    @Override
    public SSDate getValue() {
        return monthPicker.getValue();
    }

    /**
     * Sets the selected date on the popup
     *
     * @param date - The date to be selected
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    @Override
    public void setValue(SSDate date) {
        monthPicker.setValue(date);
        if (date != null) {
            updateDateTextBox();
        }
    }

    /**
     * Sets the selected date on the popup with events
     *
     * @param date - The date to be selected
     * @param fireEvents - Fire events if true
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    @Override
    public void setValue(SSDate date, boolean fireEvents) {
    }

    /**
     * This class will handle different events on the month date box
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    protected class MonthDateBoxHandler implements ValueChangeHandler<SSDate>,
        FocusHandler, BlurHandler, ClickHandler, KeyDownHandler,
        CloseHandler<PopupPanel> {

        /**
         * This will handle what happens on blur for the month date box
         *
         * @param event - The event that triggered the blur
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 27 Dec 2012
         */
        public void onBlur(BlurEvent event) {
            if (isDatePickerShowing() == false) {
                updateDateTextBox();
            }
        }

        /**
         * This will handle what happens on click for the month date box
         *
         * @param event - The event that triggered the click
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 27 Dec 2012
         */
        public void onClick(ClickEvent event) {
            showMonthPicker();
        }

        /**
         * This will handle what happens on close for the month date box
         *
         * @param event - The event that triggered the close
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 27 Dec 2012
         */
        public void onClose(CloseEvent<PopupPanel> event) {
            if (allowMPShow) {
                updateDateTextBox();
            }
        }

        /**
         * This will handle what happens on focus for the month date box
         *
         * @param event - The event that triggered the focus
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 27 Dec 2012
         */
        public void onFocus(FocusEvent event) {
            if (allowMPShow && isDatePickerShowing() == false) {
                showMonthPicker();
            }
        }

        /**
         * This will handle what happens on key events for the month date box
         *
         * @param event - The key that triggered the event
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 27 Dec 2012
         */
        public void onKeyDown(KeyDownEvent event) {
            switch (event.getNativeKeyCode()) {
            case KeyCodes.KEY_ENTER:
            case KeyCodes.KEY_TAB:
                updateDateTextBox();
                // Deliberate fall through
            case KeyCodes.KEY_ESCAPE:
            case KeyCodes.KEY_UP:
                hideMonthDatePicker();
                break;
            case KeyCodes.KEY_DOWN:
                showMonthPicker();
                break;
            }
        }

        /**
         * This will handle what happens on value change for the month date box
         *
         * @param event - The value change that triggered the event
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 27 Dec 2012
         */
        public void onValueChange(ValueChangeEvent<SSDate> event) {
            hideMonthDatePicker();
            preventDatePickerPopup();
            box.setFocus(true);
        }
    }

    /**
     * Returns true if date picker is currently showing, false if not.
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     *
     * @return True if date picker is currently showing, false if not.
     */
    public boolean isDatePickerShowing() {
        return popup.isShowing();
    }

    /**
     * This function will set the date format to user on the month date box
     *
     * @param dateFormat - The format to use on the month date box
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * This function will get the date format to user on the month date box
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     *
     * @return The date format to user on the month date box
     */
    public String getDateFormat() {
        //return default date format if no custom date format is set
        if (dateFormat == null || dateFormat.trim().equals("")) {
            return DEFAULT_DATE_FORMAT;
        }
        return this.dateFormat;
    }

    /**
     * This function will update the value on the month date box with the
     * value selected on the month picker popup
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    private void updateDateTextBox() {
        if (monthPicker.getValue() != null) {
            box.setText(DateTimeFormat.getFormat(getDateFormat()).format(monthPicker.getValue()));
        }
    }

    /**
     * Shows the month picker.
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void showMonthPicker() {
        // We need to remove the selected style
        // from the previously selected month box.
        if (box.getText().equals("")) {
            this.removeSelectedStyle();
            // Set the value to null or the style will be applied again
            monthPicker.setValue(null);
        } else {
            monthPicker.setValue(monthPicker.getValue());
        }
        popup.showRelativeTo(this);
    }

    /**
     * Prevents the popup from showing
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    private void preventDatePickerPopup() {
        allowMPShow = false;
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                allowMPShow = true;
            }
        });
    }

    /**
     * Hide the date picker.
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    public void hideMonthDatePicker() {
        popup.hide();
    }

    /**
     * Adds a custom handler to the item
     *
     * @param handler to use in on the object
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     */
    protected void addHandlers(MonthDateBoxHandler handler) {
        box.addFocusHandler(handler);
        box.addBlurHandler(handler);
        box.addClickHandler(handler);
        box.addKeyDownHandler(handler);
        popup.addCloseHandler(handler);
    }

    /**
     * Getter for the text box used as the month date box
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 27 Dec 2012
     *
     * @return The text box used as the month date box
     */
    public TextBox getTextBox() {
        return this.box;
    }

    /**
     * Remove the selected style from the month box
     *
     * @author Ryno Hartzer <ryno.hartzer@a24group.com>
     * @since  31 December 2012
     */
    public void removeSelectedStyle() {
        monthPicker.removeStateStyles();
    }
}
