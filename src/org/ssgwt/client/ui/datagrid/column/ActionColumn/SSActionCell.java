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
package org.ssgwt.client.ui.datagrid.column.ActionColumn;

import java.util.ArrayList;

import org.ssgwt.client.ui.datagrid.column.ActionColumn.Event.ActionClickEvent;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.client.SafeHtmlTemplates.Template;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A action cell that will display actions and fire a event if one of the actions is clicked
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since 03 April 2013
 */
public class SSActionCell<T> extends AbstractCell<T> implements HasHandlers {

    /**
     * The column the cell is displayed in.
     * The parent column.
     */
    private SSActionColumn<T> column;

    /**
     * The handler manager used to handle events
     */
    private final HandlerManager handlerManager;

    /**
     * List that contains the actions
     */
    private ArrayList<String> actionList;

    /**
     * The parent element of the Cell
     */
    private Element parent;

    /**
     * The action spacer size defaulted to 5px
     */
    private int spacerSize = 5;

    /**
     * Instance of the template
     */
    private static Template template;

    /**
     * The mouse up event cont
     */
    private final static String MOUSE_UP = "mouseup";

    /**
     * Template providing SafeHTML templates to build the widget
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
     */
    interface Template extends SafeHtmlTemplates {

        @Template(
            "<div class=\"actionCell\" name=\"{0}\" style=\"display: inline; margin-left: {1}px !important; cursor: pointer;\">"
            + "{0}"
            + "</div>"
        )
        SafeHtml action(String actionName, int spacerSize);

        @Template("<div class=\"actionCell\" name=\"{0}\" style=\"display: inline; cursor: pointer;\">{0}</div>")
        SafeHtml firstAction(String actionName);

        @Template("<div class=\"actionCellSeparator\" style=\"display: inline-block; margin-left: {0}px !important;\" >|</div>")
        SafeHtml actionSeparator(int spacerSize);
    }

    /**
     * The SSActionCell constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  18 July 2013
     *
     * @param spacerSize - The size to be used between the action items
     */
    public SSActionCell(int spacerSize) {
        super(MOUSE_UP);
        this.handlerManager = new HandlerManager(this);
        if (template == null) {
            template = GWT.create(Template.class);
        }
        this.spacerSize = spacerSize;
    }

    /**
     * The SSActionCell constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
     */
    public SSActionCell() {
        this(5);
    }

    /**
     * Setter for the parent column
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
     *
     * @param column - The parent column
     */
    public void setParentColumn(SSActionColumn<T> column) {
        this.column = column;
    }

    /**
     * This will create a image with the url passed in by the constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
     *
     * @param context -The context the cell is in
     * @param value - The value to be added in the cell but not the image thats going to be displayed
     * @param sb -The safe html builder.
     */
    @Override
    public void render(Context context, T value, SafeHtmlBuilder sb) {
        actionList = column.getActions(value);
        // Flag used to indicate the first item
        boolean firstItem = true;
        for (String action : actionList) {
            if (firstItem) {
                sb.append(template.firstAction(action));
                firstItem = false;
            } else {
                sb.append(template.actionSeparator(this.spacerSize));
                sb.append(template.action(action, this.spacerSize));
            }
        }
    }

    /**
     * Handle a browser event that took place within the cell. The default
     * implementation returns null.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
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
        if (MOUSE_UP.equals(event.getType())) { // The event is MOUSE_UP
            for (String action : actionList) {
                if (event.getEventTarget().equals(getElementByName(parent, action))) {
                    ActionClickEvent.fire(this, action, value);
                    break;
                }
            }
        }
    }

    /**
     * Retrieves the element of the action that is being displayed in the Cell by the name.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
     *
     * @param parent - The top level container of the Cell
     *
     * @return The element object that represents the action else null
     */
    protected Element getElementByName(Element parent, String elementName) {
        int numberOfNodes = parent.getChildCount();
        for (int i = 0; i < numberOfNodes; i++) {
            if (
                parent.getElementsByTagName("div")
                    .getItem(i).getAttribute("name")
                    .equals(elementName)
            ) {
                return parent.getElementsByTagName("div").getItem(i);
            }
        }
        return null;
    }

    /**
     * Adds a event handler for the ActionClickEvent
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
     *
     * @param handler - The event handler
     *
     * @return HandlerRegistration
     */
    public HandlerRegistration addActionClickHandler(ActionClickEvent.ActionClickHandler handler) {
        return handlerManager.addHandler(ActionClickEvent.TYPE, handler);
    }

    /**
     * This is used to fire an event
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 03 June 2013
     *
     * @param event - The event that needs to be fired
     */
    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

}
