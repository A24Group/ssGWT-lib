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
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MonthDateBox extends Composite implements HasValue<Date> {

	private final TextBox box = new TextBox();

	private final ssMonthPicker picker;

	private final PopupPanel popup;
	
	private boolean allowDPShow = true;
	
	protected MonthDateBoxHandler handler = new MonthDateBoxHandler();
	
	private Format format;

	public MonthDateBox() {
		this.popup = new PopupPanel(true);
		this.picker = new ssMonthPicker();

		popup.addAutoHidePartner(box.getElement());
		popup.setWidget(picker);
		
		addHandlers(handler);
		
		initWidget(box);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Date> handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Date value) {
		// TODO Auto-generated method stub

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
				updateDateFromTextBox();
			}
		}

		public void onClick(ClickEvent event) {
			showMonthPicker();
		}

		public void onClose(CloseEvent<PopupPanel> event) {
			// If we are not closing because we have picked a new value, make
			// current value is updated.
			if (allowDPShow) {
				updateDateFromTextBox();
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
				updateDateFromTextBox();
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
    
    /**
     * TODO
     */
    private void updateDateFromTextBox() {
//        Date parsedDate = parseDate(true);
//        if (parsedDate != null) {
//            setValue(picker.getValue(), parsedDate, true);
//        }
    }
    
//    private Date parseDate(boolean reportError) {
//        if (reportError) {
//            getFormat().reset(this, false);
//        }
//        String text = box.getText().trim();
//        return getFormat().parse(this, text, reportError);
//    }
    
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
        if (parseDate) {
//            Date current = parseDate(false);
//            if (current == null) {
//                current = new Date();
//            }
//            picker.setCurrentMonth(current);
        }
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
