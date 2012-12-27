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

import java.util.Date;

import org.ssgwt.client.ui.datepicker.DateBox.DateBoxHandler;
import org.ssgwt.client.ui.datepicker.DateBox.Format;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MonthDateBox extends Composite implements HasValue<Date> {

	private static final String DEFAULT_DATE_FORMAT = "MMMM YYYY";

    private final TextBox box = new TextBox();

	private final SSMonthPicker picker;

	private final PopupPanel popup;
	
	private boolean allowDPShow = true;
	
	protected MonthDateBoxHandler handler = new MonthDateBoxHandler();
	
	private Format format;

    private String dateFormat;

	public MonthDateBox() {
		this.popup = new PopupPanel(true);
		this.picker = new SSMonthPicker(popup);

		popup.addAutoHidePartner(box.getElement());
		popup.setWidget(picker);
		
		addHandlers(handler);
		
		initWidget(box);
	}
	
	public void setMaxDate(Date maxDate) {
	    this.picker.setMaxDate(maxDate);
	}
	
	public void setMinDate(Date minDate) {
        this.picker.setMinDate(minDate);
    }

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Date> handler) {
		return null;
	}

	@Override
	public Date getValue() {
		return picker.getValue();
	}

	@Override
	public void setValue(Date date) {
	    picker.setValue(date);
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		// TODO Auto-generated method stub

	}

	protected class MonthDateBoxHandler implements ValueChangeHandler<Date>,
			FocusHandler, BlurHandler, ClickHandler, KeyDownHandler,
			CloseHandler<PopupPanel> {

		public void onBlur(BlurEvent event) {
			if (isDatePickerShowing() == false) {
			    updateDateTextBox();
			}
		}

		public void onClick(ClickEvent event) {
			showMonthPicker();
		}

		public void onClose(CloseEvent<PopupPanel> event) {
			// If we are not closing because we have picked a new value, make
			// current value is updated.
			if (allowDPShow) {
			    updateDateTextBox();
			}
		}

		public void onFocus(FocusEvent event) {
			if (allowDPShow && isDatePickerShowing() == false) {
				showMonthPicker();
			}
		}

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

		public void onValueChange(ValueChangeEvent<Date> event) {
//			setValue(parseDate(false), event.getValue(), true);
			hideMonthDatePicker();
			preventDatePickerPopup();
			box.setFocus(true);
		}
	}
	
    /**
     * Returns true if date picker is currently showing, false if not.
     */
    public boolean isDatePickerShowing() {
        return popup.isShowing();
    }
    
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    
    public String getDateFormat() {
        if(dateFormat == null || dateFormat.trim().equals("")){
            return DEFAULT_DATE_FORMAT;
        }
        return this.dateFormat;
    }
    
    /**
     * TODO
     */
    private void updateDateTextBox() {
        if (picker.getValue() != null) {
            box.setText(DateTimeFormat.getFormat(getDateFormat()).format(picker.getValue()));
        }
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
     * Shows the date picker.
     */
    public void showMonthPicker() {
    	showMonthPicker(false);
    }
    
    /**
     * Shows the date picker and if the provided value for parseDate is true
     * it also parses the date currently found in the text box.
     * 
     * @param parseDate Whether to parse the date or not.
     */
    public void showMonthPicker(boolean parseDate) {
        popup.showRelativeTo(this);
    }
    
    private void preventDatePickerPopup() {
        allowDPShow = false;
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                allowDPShow = true;
            }
        });
    }
    
    /**
     * Hide the date picker.
     */
    public void hideMonthDatePicker() {
        popup.hide();
    }
    
    /**
     * Adds a custom handler to the item
     * 
     * @param handler to use in on the object
     */
    protected void addHandlers(MonthDateBoxHandler handler) {
        box.addFocusHandler(handler);
        box.addBlurHandler(handler);
        box.addClickHandler(handler);
        box.addKeyDownHandler(handler);
        popup.addCloseHandler(handler);
    }
}
