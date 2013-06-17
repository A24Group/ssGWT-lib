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
package org.ssgwt.client.ui.datagrid.column;

import java.util.Date;

import org.ssgwt.client.ui.datagrid.column.ImageHoverColumn.AbstractImageColumnPopup;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.client.SafeHtmlTemplates.Template;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A text cell that will display text in a cell with a tooltip with the
 * same text value as the cell
 *
 * @author Ruan Naude <nauderuan777@gmail.com>
 * @since 14 March 2013
 */
public class SSTextCell<T> extends AbstractCell<String> implements HasHandlers {

    /**
     * The format that the date is currently displayed in
     */
    String sDateFormat = "";

    /**
     * The format that the date value should be displayed in
     */
    String sDateDisplayTooltipFormat = "";

    /**
     * The popup the will be displayed on hover
     */
    private AbstractImageColumnPopup<T> popup;

    /**
     * The handler manager used to handle events
     */
    private HandlerManager handlerManager;

    /**
     * The parent column
     */
    private SSTextColumn<T> column;

    /**
     * The parent element of the Cell
     */
    private Element parent;

    /**
     * The mouse over event cont
     */
    private final static String MOUSE_OVER = "mouseover";

    /**
     * The mouse out event cont
     */
    private final static String MOUSE_OUT = "mouseout";

    /**
     * Instance of the template
     */
    private static Template template;

    /**
     * Flag used to indicate the the cell have a custom tool tip
     */
    private boolean customToolTip = false;

    /**
     * Template providing SafeHTML templates to build the widget
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 June 2013
     */
    interface Template extends SafeHtmlTemplates {

        @Template("<div title=\"{0}\" >{1}</div>")
        SafeHtml action(String title, String value);

        @Template("<div >{0}</div>")
        SafeHtml action(String value);
    }

    /**
     * Class Constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 June 2013
     */
    public SSTextCell() {
        super();
        if (template == null) {
            template = GWT.create(Template.class);
        }
    }

    /**
     * Class Constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 June 2013
     *
     * @param sDateFormat - The format that the date is currently displayed in
     * @param sDateDisplayTooltip - The date format that you want to have the tooltip displayed
     */
    public SSTextCell(String sDateFormat, String sDateDisplayTooltip) {
        this();
        this.sDateFormat = sDateFormat;
        this.sDateDisplayTooltipFormat = sDateDisplayTooltip;
    }

    /**
     * Class Constructor for text cell with custom tool tip
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     *
     * @param popup - The custom popup to display on hover
     */
    public SSTextCell(AbstractImageColumnPopup<T> popup) {
        super(MOUSE_OVER, MOUSE_OUT);
        if (template == null) {
            template = GWT.create(Template.class);
        }
        this.popup = popup;
        this.handlerManager = new HandlerManager(this);
        this.customToolTip = true;
    }

    /**
     * Setter for the parent column
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  27 june 2013
     *
     * @param column - The parent column
     */
    public void setParentColumn(SSTextColumn<T> column) {
        this.column = column;
    }

    /**
     * This will create a label with text and a tooltip with the same text value
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 14 March 2013
     *
     * @param context -The context the cell is in
     * @param value - The value to be added in the cell
     * @param sb -The safe html builder.
     */
    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (value == null) {
            value = "";
        }
        if (!customToolTip) {
            String tooltip = value;
            if (this.sDateDisplayTooltipFormat != null && this.sDateDisplayTooltipFormat != ""
                && this.sDateFormat != null && this.sDateFormat != "") {
                try {
                    // Convert date from sDateFormat to sDateDisplayTooltipFormat
                    Date date = DateTimeFormat.getFormat(this.sDateFormat).parse(value);
                    tooltip = DateTimeFormat.getFormat(this.sDateDisplayTooltipFormat).format(date);
                } catch (Exception e){
                    // Ignore exception, resulting in default tooltip
                }
            }
            sb.append(template.action(tooltip, value));
        } else {
            sb.append(template.action(value));
        }
    }

    /**
     * Handle a browser event that took place within the cell. The default
     * implementation returns null.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     *
     * @param context - the {@link Context} of the cell
     * @param parent - the parent Element
     * @param value - the value associated with the cell
     * @param event - the native browser event
     * @param valueUpdater - a {@link ValueUpdater}, or null if not specified
     */
    @Override
    public void onBrowserEvent(
        Context context,
        Element parent,
        String value,
        NativeEvent event,
        ValueUpdater<String> valueUpdater
    ) {
        // All the already browser event should still behave as it use to
        super.onBrowserEvent(context, parent, value, event, valueUpdater);

        this.parent = parent;
        // Get the image tag in the cell
        Element imageElement = getLabelElement(parent);
        // If the event happened on the image tag
        if (event.getEventTarget().equals(imageElement)) {
            if (MOUSE_OVER.equals(event.getType())) { // The event is MOUSE_OVER
                // Display the popup
                displayPopup();
            } else if (MOUSE_OUT.equals(event.getType())) { // The event is MOUSE_OUT
                // Close the popup
                hidePopup();
            }
        }
    };

    /**
     * Displays the popup relative to the cell
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     *
     * @param data - The data that will be set in the popup
     */
    private void displayPopup() {
        this.popup.center();
        this.popup.setData(column.getRowData());
        this.popup.setPopupPosition(
            this.parent.getAbsoluteLeft(),
            (this.parent.getAbsoluteTop() + this.parent.getOffsetHeight() + 10)
        );
    }

    /**
     * Hides the popup
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     */
    private void hidePopup() {
        this.popup.hide();
    }

    /**
     * Retrieves the element of the label displayed in the cell
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     *
     * @param parent - The top level container of the Cell
     *
     * @return The element object that represents the label in the cell
     */
    protected Element getLabelElement(Element parent) {
        return parent.getFirstChildElement();
    }

    /**
     * This is used to fire an event
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     *
     * @param event - The event that needs to be fired
     */
    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

}
