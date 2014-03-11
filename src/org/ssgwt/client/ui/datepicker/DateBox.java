/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
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
 * A text box that shows a {@link DatePicker} when the user focuses on it.
 *
 * Edited by Michael Barnard<michael.barnard@a24group.com>
 *
 * <h3>CSS Style Rules</h3>
 *
 * <dl>
 * <dt>.gwt-DateBox</dt>
 * <dd>default style name</dd>
 * <dt>.dateBoxPopup</dt>
 * <dd>Applied to the popup around the DatePicker</dd>
 * <dt>.dateBoxFormatError</dt>
 * <dd>Default style for when the date box has bad input. Applied by
 * {@link DateBox.DefaultFormat} when the text does not represent a date that
 * can be parsed</dd>
 * </dl>
 *
 * <p>
 * <h3>Example</h3>
 * {@example com.google.gwt.examples.DateBoxExample}
 * </p>
 */
public class DateBox extends Composite implements HasValue<SSDate>,
        IsEditor<LeafValueEditor<SSDate>> {
    /**
     * Default {@link DateBox.Format} class. The date is first parsed using the
     * {@link DateTimeFormat} supplied by the user, or
     * {@link DateTimeFormat#getMediumDateFormat()} by default.
     * <p>
     * If that fails, we then try to parse again using the default browser date
     * parsing.
     * </p>
     * If that fails, the <code>dateBoxFormatError</code> css style is applied to
     * the {@link DateBox}. The style will be removed when either a successful
     * {@link #parse(DateBox,String, boolean)} is called or
     * {@link #format(DateBox,Date)} is called.
     * <p>
     * Use a different {@link DateBox.Format} instance to change that behavior.
     * </p>
     */
    public static class DefaultFormat implements Format {

        private final DateTimeFormat dateTimeFormat;

        /**
         * Creates a new default format instance.
         */
        @SuppressWarnings("deprecation")
        public DefaultFormat() {
            dateTimeFormat = DateTimeFormat.getMediumDateTimeFormat();
        }

        /**
         * Creates a new default format instance.
         *
         * @param dateTimeFormat the {@link DateTimeFormat} to use with this
         *                    {@link Format}.
         */
        public DefaultFormat(DateTimeFormat dateTimeFormat) {
            this.dateTimeFormat = dateTimeFormat;
        }

        public String format(DateBox box, SSDate date) {
            if (date == null) {
                return "";
            } else {
                return dateTimeFormat.format(date);
            }
        }

        /**
         * Gets the date time format.
         *
         * @return the date time format
         */
        public DateTimeFormat getDateTimeFormat() {
            return dateTimeFormat;
        }

        @SuppressWarnings("deprecation")
        public SSDate parse(DateBox dateBox, String dateText, boolean reportError) {
            SSDate date = null;
            try {
                if (dateText.length() > 0) {
                    date = dateTimeFormat.parse(dateText);
                }
            } catch (IllegalArgumentException exception) {
                try {
                    date = new SSDate(dateText);
                } catch (IllegalArgumentException e) {
                    if (reportError) {
                        dateBox.addStyleName(DATE_BOX_FORMAT_ERROR);
                    }
                    return null;
                }
            }
            return date;
        }

        public void reset(DateBox dateBox, boolean abandon) {
            dateBox.removeStyleName(DATE_BOX_FORMAT_ERROR);
        }
    }

    /**
     * Implemented by a delegate to handle the parsing and formating of date
     * values. The default {@link Format} uses a new {@link DefaultFormat}
     * instance.
     */
    public interface Format {

        /**
         * Formats the provided date. Note, a null date is a possible input.
         *
         * @param dateBox the date box you are formatting
         * @param date the date to format
         * @return the formatted date as a string
         */
        String format(DateBox dateBox, SSDate date);

        /**
         * Parses the provided string as a date.
         *
         * @param dateBox the date box
         * @param text the string representing a date
         * @param reportError should the formatter indicate a parse error to the
         *                    user?
         * @return the date created, or null if there was a parse error
         */
        SSDate parse(DateBox dateBox, String text, boolean reportError);

        /**
         * If the format did any modifications to the date box's styling, reset them
         * now.
         *
         * @param abandon true when the current format is being replaced by another
         * @param dateBox the date box
         */
        void reset(DateBox dateBox, boolean abandon);
    }

    protected class DateBoxHandler implements ValueChangeHandler<SSDate>,
            FocusHandler, BlurHandler, ClickHandler, KeyDownHandler,
            CloseHandler<PopupPanel> {

        public void onBlur(BlurEvent event) {
            if (isDatePickerShowing() == false) {
                updateDateFromTextBox();
            }
        }

        public void onClick(ClickEvent event) {
            showDatePicker();
        }

        public void onClose(CloseEvent<PopupPanel> event) {
            // If we are not closing because we have picked a new value, make sure the
            // current value is updated.
            if (allowDPShow) {
                updateDateFromTextBox();
            }
        }

        public void onFocus(FocusEvent event) {
            if (allowDPShow && isDatePickerShowing() == false) {
                showDatePicker();
            }
        }

        public void onKeyDown(KeyDownEvent event) {
            switch (event.getNativeKeyCode()) {
                case KeyCodes.KEY_ENTER:
                case KeyCodes.KEY_TAB:
                    updateDateFromTextBox();
                    // Deliberate fall through
                case KeyCodes.KEY_ESCAPE:
                case KeyCodes.KEY_UP:
                    hideDatePicker();
                    break;
                case KeyCodes.KEY_DOWN:
                    showDatePicker();
                    break;
            }
        }

        public void onValueChange(ValueChangeEvent<SSDate> event) {
            setValue(parseDate(false), event.getValue(), true);
            hideDatePicker();
            preventDatePickerPopup();
            box.setFocus(true);
        }
    }

    /**
     * Default style name added when the date box has a format error.
     */
    private static final String DATE_BOX_FORMAT_ERROR = "dateBoxFormatError";

    /**
     * Default style name.
     */
    public static final String DEFAULT_STYLENAME = "gwt-DateBox";
    private static final DefaultFormat DEFAULT_FORMAT = GWT.create(DefaultFormat.class);
    private final PopupPanel popup;
    private final TextBox box = new TextBox();
    private final DatePicker picker;
    private LeafValueEditor<SSDate> editor;
    private Format format;
    private boolean allowDPShow = true;
    protected DateBoxHandler handler = new DateBoxHandler();

    /**
     * Create a date box with a new {@link DatePicker}.
     */
    public DateBox() {
        this(new DatePicker(), null, DEFAULT_FORMAT);
    }

    /**
     * Create a new date box.
     *
     * @param date the default date.
     * @param picker the picker to drop down from the date box
     * @param format to use to parse and format dates
     */
    public DateBox(DatePicker picker, SSDate date, Format format) {
        this(picker, date, format, true);
    }

    /**
     * Create a new date box.
     *
     * @param picker the picker to drop down from the date box
     * @param date the default date.
     * @param format to use to parse and format dates
     * @param addHandlers indicating whether a default handler should be added or not
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     */
    protected DateBox(DatePicker picker, SSDate date, Format format, boolean addHandlers) {
        this.picker = picker;
        this.popup = new PopupPanel(true);
        assert format != null : "You may not construct a date box with a null format";
        this.format = format;

        popup.addAutoHidePartner(box.getElement());
        popup.setWidget(picker);
        popup.setStyleName("dateBoxPopup");

        initWidget(box);
        setStyleName(DEFAULT_STYLENAME);

        if (addHandlers) {
            addHandlers(handler);
        }
        setValue(date);
    }

    /**
     * Adds a custom handler to the item
     *
     * @param handler to use in on the object
     */
    protected void addHandlers(DateBoxHandler handler) {
        picker.addValueChangeHandler(handler);
        box.addFocusHandler(handler);
        box.addBlurHandler(handler);
        box.addClickHandler(handler);
        box.addKeyDownHandler(handler);
        popup.addCloseHandler(handler);
    }

    public HandlerRegistration addValueChangeHandler(
            ValueChangeHandler<SSDate> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    /**
     * Returns a {@link TakesValueEditor} backed by the DateBox.
     */
    public LeafValueEditor<SSDate> asEditor() {
        if (editor == null) {
            editor = TakesValueEditor.of(this);
        }
        return editor;
    }

    /**
     * Gets the current cursor position in the date box.
     *
     * @return the cursor position
     *
     */
    public int getCursorPos() {
        return box.getCursorPos();
    }

    /**
     * Gets the date picker.
     *
     * @return the date picker
     */
    public DatePicker getDatePicker() {
        return picker;
    }

    /**
     * Gets the format instance used to control formatting and parsing of this
     * {@link DateBox}.
     *
     * @return the format
     */
    public Format getFormat() {
        return this.format;
    }

    /**
     * Gets the date box's position in the tab index.
     *
     * @return the date box's tab index
     */
    public int getTabIndex() {
        return box.getTabIndex();
    }

    /**
     * Get text box.
     *
     * @return the text box used to enter the formatted date
     */
    public TextBox getTextBox() {
        return box;
    }

    /**
     * Get the date displayed, or null if the text box is empty, or cannot be
     * interpreted.
     *
     * @return the current date value
     */
    public SSDate getValue() {
        return parseDate(true);
    }

    /**
     * Hide the date picker.
     */
    public void hideDatePicker() {
        popup.hide();
    }

    /**
     * Returns true if date picker is currently showing, false if not.
     */
    public boolean isDatePickerShowing() {
        return popup.isShowing();
    }

    /**
     * Sets the date box's 'access key'. This key is used (in conjunction with a
     * browser-specific modifier key) to automatically focus the widget.
     *
     * @param key the date box's access key
     */
    public void setAccessKey(char key) {
        box.setAccessKey(key);
    }

    /**
     * Sets whether the date box is enabled.
     *
     * @param enabled is the box enabled
     */
    public void setEnabled(boolean enabled) {
        box.setEnabled(enabled);
    }

    /**
     * Explicitly focus/unfocus this widget. Only one widget can have focus at a
     * time, and the widget that does will receive all keyboard events.
     *
     * @param focused whether this widget should take focus or release it
     */
    public void setFocus(boolean focused) {
        box.setFocus(focused);
    }

    /**
     * Sets the format used to control formatting and parsing of dates in this
     * {@link DateBox}. If this {@link DateBox} is not empty, the contents of date
     * box will be replaced with current contents in the new format.
     *
     * @param format the new date format
     */
    public void setFormat(Format format) {
        assert format != null : "A Date box may not have a null format";
        if (this.format != format) {
            SSDate date = getValue();

            // This call lets the formatter do whatever other clean up is required to
            // switch formatters.
            //
            this.format.reset(this, true);

            // Now update the format and show the current date using the new format.
            this.format = format;
            setValue(date);
        }
    }

    /**
     * Sets the date box's position in the tab index. If more than one widget has
     * the same tab index, each such widget will receive focus in an arbitrary
     * order. Setting the tab index to <code>-1</code> will cause this widget to
     * be removed from the tab order.
     *
     * @param index the date box's tab index
     */
    public void setTabIndex(int index) {
        box.setTabIndex(index);
    }

    /**
     * Set the date.
     */
    public void setValue(SSDate date) {
        setValue(date, false);
    }

    public void setValue(SSDate date, boolean fireEvents) {
        setValue(picker.getValue(), date, fireEvents);
    }

    /**
     * Shows the date picker.
     */
    public void showDatePicker() {
        showDatePicker(false);
    }

    /**
     * Shows the date picker and if the provided value for parseDate is true
     * it also parses the date currently found in the text box.
     *
     * @param parseDate Whether to parse the date or not.
     */
    public void showDatePicker(boolean parseDate) {
        if (parseDate) {
            SSDate current = parseDate(false);
            if (current == null) {
                current = new SSDate();
            }
            picker.setCurrentMonth(current);
        }
        popup.showRelativeTo(this);
    }

    private SSDate parseDate(boolean reportError) {
        if (reportError) {
            getFormat().reset(this, false);
        }
        String text = box.getText().trim();
        return getFormat().parse(this, text, reportError);
    }

    private void preventDatePickerPopup() {
        allowDPShow = false;
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                allowDPShow = true;
            }
        });
    }

    private void setValue(SSDate oldDate, SSDate date, boolean fireEvents) {
        SSDate pickerDate = date;
        if (date != null && this.getDatePicker().getMinimumDate().getTime() > date.getTime()) {
            System.out.println("It has been set");
            pickerDate = new SSDate(this.getDatePicker().getMinimumDate().getTime());
            pickerDate.setDate(this.getDatePicker().getMinimumDate().getDate() + 1);
        }
        if (pickerDate != null) {
            picker.setCurrentMonth(pickerDate);
        }
        picker.setValue(date, false);
        format.reset(this, false);
        box.setText(getFormat().format(this, date));

        if (fireEvents) {
            DateChangeEvent.fireIfNotEqualDates(this, oldDate, date);
        }
    }

    private void updateDateFromTextBox() {
        SSDate parsedDate = parseDate(true);
        if (parsedDate != null) {
            setValue(picker.getValue(), parsedDate, true);
        }
    }
}
