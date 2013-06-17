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
package org.ssgwt.client.ui.datagrid.column.ImageHoverColumn;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.client.SafeHtmlTemplates.Template;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

/**
 * A image cell that display a set image and on hover display a popup passed into it constructor
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  31 May 2013
 *
 * @param <T> - The type of data represented by the cell;
 */
public class SSHoverImageCell<T> extends AbstractCell<T> implements HasHandlers {

    /**
     * The popup the will be displayed on hover
     */
    private final AbstractImageColumnPopup<T> popup;

    /**
     * The handler manager used to handle events
     */
    private final HandlerManager handlerManager;

    /**
     * The image displayed in the cell
     */
    private Image image;

    /**
     * The parent element of the Cell
     */
    private Element parent;

    /**
     * The column the cell is displayed in.
     * The parent column.
     */
    private SSHoverImageColumn<T> column;

    /**
     * The mouse over event cont
     */
    private final static String MOUSE_OVER = "mouseover";

    /**
     * The mouse out event cont
     */
    private final static String MOUSE_OUT = "mouseout";

    /**
     * The name of the image tag in the cell
     */
    private final static String CELL_IMAGE_NAME = "cellImage";

    /**
     * Instance of the template
     */
    private static Template template;

    /**
     * The SSBooleanImageCell constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
     *
     * @param imageUrl - The url of the image to display in the cell
     * @param popup - The popup displayed on hover
     * @param column - The column this
     */
    public SSHoverImageCell(AbstractImageColumnPopup<T> popup) {
        super(MOUSE_OVER, MOUSE_OUT);
        if (template == null) {
            template = GWT.create(Template.class);
        }
        this.popup = popup;
        this.handlerManager = new HandlerManager(this);
    }

    /**
     * Template providing SafeHTML templates to build the widget
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
     */
    interface Template extends SafeHtmlTemplates {

        @Template("<div >")
        SafeHtml openContainerTag();

        @Template("</div>")
        SafeHtml closeContainerTag();
    }

    /**
     * Setter for the parent column
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
     *
     * @param column - The parent column
     */
    public void setParentColumn(SSHoverImageColumn<T> column) {
        this.column = column;
    }

    /**
     * This will create a image with the url passed in by the constructor and also on hover
     * of the image display a popup.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
     *
     * @param context -The context the cell is in
     * @param value - The value of the object that will be displayed in the popup
     * @param sb -The safe html builder.
     */
    @Override
    public void render(Context context, T value, SafeHtmlBuilder sb) {

        image = this.column.getImage(value);
        image.getElement().setAttribute("name", CELL_IMAGE_NAME);

        sb.append(template.openContainerTag());
        sb.appendHtmlConstant(image.toString());
        sb.append(template.closeContainerTag());
    }

    /**
     * Handle a browser event that took place within the cell. The default
     * implementation returns null.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
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
        T value,
        NativeEvent event,
        ValueUpdater<T> valueUpdater
    ) {
        // All the already browser event should still behave as it use to
        super.onBrowserEvent(context, parent, value, event, valueUpdater);

        this.parent = parent;
        // Get the image tag in the cell
        Element imageElement = getImageElement(parent);

        // If the event happened on the image tag
        if (event.getEventTarget().equals(imageElement)) {
            if (MOUSE_OVER.equals(event.getType())) { // The event is MOUSE_OVER
                // Display the popup
                displayPopup(value);
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
     * @since  31 May 2013
     *
     * @param data - The data that will be set in the popup
     */
    private void displayPopup(T data) {
        this.popup.center();
        this.popup.setData(data);
        this.popup.setPopupPosition(
            this.parent.getAbsoluteLeft(),
            (this.parent.getAbsoluteTop() + this.parent.getOffsetHeight() + 10)
        );
    }

    /**
     * Hides the popup
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
     */
    private void hidePopup() {
        this.popup.hide();
    }

    /**
     * Retrieves the element of the image that is being displayed in the Cell.
     * The cell being the parent;
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
     *
     * @param parent - The top level container of the Cell
     *
     * @return The element object that represents the image in the Cell
     */
    protected Element getImageElement(Element parent) {
        // A child image tag does exist for the cell with the name cellImage return it
        if (
            parent.getFirstChildElement()
                .getElementsByTagName("img")
                .getItem(0).getAttribute("name")
                .equals(CELL_IMAGE_NAME)
        ) {
            return parent.getFirstChildElement().getElementsByTagName("img").getItem(0);
        }
        // If a child image tag does not exist for the cell with the name cellImage return the first image tag
        return parent.getFirstChildElement().getElementsByTagName("img").getItem(1);
    }

    /**
     * This is used to fire an event
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  31 May 2013
     *
     * @param event - The event that needs to be fired
     */
    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

}
