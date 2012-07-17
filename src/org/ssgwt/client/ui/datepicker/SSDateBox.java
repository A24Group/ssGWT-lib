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

package org.ssgwt.client.ui.datepicker;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.LayoutPanel;
import java.util.Date;

import org.ssgwt.client.ui.FocusImage;
import org.ssgwt.client.ui.datepicker.DateBox.Format;
import org.ssgwt.client.ui.event.FocusImageClickEvent;
import org.ssgwt.client.ui.event.IFocusImageClickEventHandler;

/**
 * Datebox component with a FocusImage icon.
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since  16 July 2012
 */
public class SSDateBox extends Composite implements HasName, HasValue<Date>, IsEditor<LeafValueEditor<Date>>, IFocusImageClickEventHandler, HasValueChangeHandlers<Date> {

    /**
     * Default date format
     */
    public static final DateBox.DefaultFormat DEFAULT_FORMAT = new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd MMM yyyy"));
    
    /**
     * Layout panel for laying out the date box and focus image on the composite.
     */
    private LayoutPanel layoutPanel = new LayoutPanel();

    /**
     * Focus image used to display the icon with.
     */
    private FocusImage image;

    /**
     * Wrapped date box that contains the logic for displaying the popup and datepicker.
     */
    private DateBox box;

    /**
     * Name of the form field
     */
    private String name;

    /**
     * Constructor used when not specifying an icon image.
     * 
     * @param picker The datepicker to use.
     * @param date The date that is selected by default.
     * @param format The date format that should be displayed in the date box.
     */
    public SSDateBox(DatePicker picker, Date date, Format format) {
        this(picker, date, format, new FocusImage(23, 22, "images/datepicker/datebox_icon.png"));
    }
    
    /**
     * Constructor to use when sending in a custom focus image
     * 
     * @param picker The datepicker to use.
     * @param date The date that is selected by default.
     * @param format The date format that should be displayed in the date box.
     * @param img The Focus image to use as an icon.
     */
    public SSDateBox(DatePicker picker, Date date, Format format, FocusImage img) {
        this(picker, date, format, img, 0);
    }
    
    /**
     * Constructor to use when sending in a custom focus image, and setting the 
     * space between the calendar image and input box
     * 
     * @param picker The datepicker to use.
     * @param date The date that is selected by default.
     * @param format The date format that should be displayed in the date box.
     * @param img The Focus image to use as an icon.
     * @param space Space between calendar image and input box
     */ 
    public SSDateBox(DatePicker picker, Date date, Format format, FocusImage img, int space) {
        box = new DateBox(picker, date, format);
        image = img;

        layoutPanel.add(box);
        layoutPanel.add(image);
        layoutPanel.setWidgetTopBottom(box, 0, Unit.PX, 0, Unit.PX);
        layoutPanel.setWidgetLeftRight(box, 0, Unit.PX, space + 2 + (image.getWidth()), Unit.PX);
        layoutPanel.setWidgetRightWidth(image, 0, Unit.PX, 2 + (image.getWidth()), Unit.PX);
        layoutPanel.setWidgetTopBottom(image, 3, Unit.PX, 2, Unit.PX);
        image.addFocusImageClickEventHandler(this);

        initWidget(layoutPanel);
    }
    
    /**
     * Adds the provided value change handler to the date box.
     * 
     * @param handler The ValueChangeHandler implementation that will handle date changes.
     */
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return box.addValueChangeHandler(handler);
    }

    /**
     * Returns the date box as a editor.
     * 
     * @return the date box as a editor.
     */
    public LeafValueEditor<Date> asEditor() {
        return box.asEditor();
    }

    /**
     * Retrieves the currently selected date from the date box.
     * 
     * @return The selected date.
     */
    public Date getValue() {
        return box.getValue();
    }

    /**
     * Sets the selected date.
     * 
     * @param value The new date.
     */
    public void setValue(Date value) {
        box.setValue(value);
    }

    /**
     * Returns the string value of the date box.
     * 
     * @return String date value
     */
    public String getStringValue() {
        return box.getFormat().format(box, box.getValue());
    }

    /**
     * Sets the selected date.
     * 
     * @param value The new date.
     * @param fireEvents Whether to dispatch events when changing the date.
     */
    public void setValue(Date value, boolean fireEvents) {
        box.setValue(value, fireEvents);
    }

    /**
     * Handler function for when the user clicks on the icon.
     * This pops up the date picker.
     * 
     * @param event The event to be handled.
     */
    public void onFocusImageClickEvent(FocusImageClickEvent event) {
        box.showDatePicker(false);
    }

    /**
     * Sets the name that this item should have on a form.
     * 
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
        this.box.getElement().setAttribute("name", name);
    }

    /**
     * Returns the current name used on the form.
     * 
     * @return Current name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the date box used by this instance.
     * 
     * @return The date box.
     */
    public DateBox getDateBox() {
        return box;
    }
}
