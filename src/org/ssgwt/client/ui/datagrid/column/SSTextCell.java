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

import org.ssgwt.client.i18n.DateTimeFormat;
import org.ssgwt.client.i18n.SSDate;
import org.ssgwt.client.ui.datagrid.column.ImageHoverColumn.AbstractImageColumnPopup;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;

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
     * String used to store the styleName
     */
    String styleName = "";

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
     * The left position of the popup
     */
    int popupLeftPosition = 0;

    /**
     * The top position of the popup
     */
    int popupTopPosition = 0;

    /**
     * Template providing SafeHTML templates to build the widget
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 June 2013
     */
    interface Template extends SafeHtmlTemplates {
        @Template("<div class=\"{0}\" title=\"{1}\" >")
        SafeHtml openTag(String style, String title);

        @Template("</div>")
        SafeHtml closeTag();
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
     * @since  20 June 2013
     *
     * @param sCustomStyle The style used for customisation
     */
    public SSTextCell(String sCustomStyle) {
        super();
        this.setStyleName(sCustomStyle);
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
        String tooltip = value;
        if (this.sDateDisplayTooltipFormat != null && this.sDateDisplayTooltipFormat != ""
            && this.sDateFormat != null && this.sDateFormat != "") {
            try {
                // Convert date from sDateFormat to sDateDisplayTooltipFormat
                SSDate date = DateTimeFormat.getFormat(this.sDateFormat).parse(value);
                tooltip = date.formatOriginalTimezone(this.sDateDisplayTooltipFormat);
            } catch (Exception e){
                // Ignore exception, resulting in default tooltip
            }
        }

        sb.append(template.openTag(styleName, tooltip));
        sb.appendHtmlConstant(value.replace(" ", "&nbsp;"));
        sb.append(template.closeTag());
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
        Element imageElement = getLabelElement(parent);
        if (event.getEventTarget().equals(imageElement)) {
            if (MOUSE_OVER.equals(event.getType())) { // The event is MOUSE_OVER
                // Display the popup only if it is allowed.
                if (column.showPopup(column.getRowData())) {
                    displayPopup();
                }
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
     */
    private void displayPopup() {
        this.popup.center();
        this.popup.setData(column.getRowData());

        calculatePopupPosition();

        this.popup.setPopupPosition(
            popupLeftPosition,
            popupTopPosition
        );
    }

    /**
     * Calculate the position of the popup.
     *
     * @author Ryno Hartzer <ryno.hartzer@a24group.com>
     * @since  19 June 2013
     */
    private void calculatePopupPosition() {
        int windowHeight = Window.getClientHeight() - 10;
        int windowWidth = Window.getClientWidth() - 10;
        boolean topPointer = false;

        // Now we need to determine the x and y position of the pointer
        int xPointerPosition = this.popup.getOffsetWidth() / 6;
        // -1 is used as it should be shown 1px above/below the border line of the popup
        int yPointerPosition = this.popup.getOffsetHeight() - 1;
        int parentCenterXPosition = getLabelElement(this.parent).getAbsoluteLeft()
            + (getLabelElement(this.parent).getOffsetWidth() / 3);

        // By default show the popup upwards if possible
        if (this.popup.getOffsetHeight() + this.parent.getAbsoluteTop() + 10 <= windowHeight) {
            popupTopPosition = this.parent.getAbsoluteTop() - this.popup.getOffsetHeight() - 10;
            topPointer = false;
        } else {
            popupTopPosition = this.parent.getAbsoluteTop() + this.parent.getOffsetHeight() + 10;
            // -10 as we used +10 above. -2 is needed to get the pointer to show perfectly
            yPointerPosition = -12;
            topPointer = true;
        }
        // maxX will be the reference point plus the width of the popup.
        // Used to determine if we are 'out' of the screen horizontally
        int maxX = this.parent.getAbsoluteLeft() + this.popup.getOffsetWidth();

        // We cannot show the popup as it is out of the screen.
        if (maxX >= windowWidth) {
            // parentCenterXPosition is the middle x position of the parent image.
            // xPointerPosition is a 1/6 of the popup width. Multiply this by 5
            // so that we get 5/6.
            // Subtract 6 to align the center of the pointer with the center of the parent image
            popupLeftPosition = parentCenterXPosition - (xPointerPosition * 5) - 6;
            this.popup.setPointerPosition(xPointerPosition * 5, yPointerPosition, topPointer);
        } else {
            popupLeftPosition = parentCenterXPosition - (xPointerPosition) - 6;
            this.popup.setPointerPosition(xPointerPosition, yPointerPosition, topPointer);
        }
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

    /**
     * Used to set the style name for the cell
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  20 June 2013
     *
     * @param styleName The custom style for the internal div tag
     *
     * @return void
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

}
