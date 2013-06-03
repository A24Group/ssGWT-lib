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
package org.ssgwt.client.ui.datagrid.column.ActionColumn.Event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event that is dispatched when a user click an action on the action cell
 *
 * @author Alec Erasmus<alec.erasmus@a24group.com>
 * @since 3 June 2013
 */
public class ActionClickEvent extends GwtEvent<ActionClickEvent.ActionClickHandler> {

    /**
     * The handler type for the event
     */
    public static Type<ActionClickHandler> TYPE = new Type<ActionClickHandler>();

    /**
     * The action that was clicked
     */
    private final String action;

    /**
     * The row data
     */
    private final Object data;

    /**
     * The event handler interface for the event
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     */
    public interface ActionClickHandler extends EventHandler {

        /**
         * Handles the ActionClickEvent
         *
         * @author Alec Erasmus<alec.erasmus@a24group.com>
         * @since 3 June 2013
         *
         * @param event - The event that should be handled
         */
        void onActionClick(ActionClickEvent event);
    }

    /**
     * The event constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     *
     * @param action - The action that was clicked
     * @param data - The row data the action apply to
     */
    public ActionClickEvent(String action, Object data) {
        this.action = action;
        this.data = data;
    }

    /**
     * Dispatches the event
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     *
     * @param handler - The function that will handle the event
     */
    @Override
    protected void dispatch(ActionClickHandler handler) {
        handler.onActionClick(this);
    }

    /**
     * Getter to retrieve the event handler type
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     *
     * @return The event handler
     */
    @Override
    public Type<ActionClickHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter to retrieve the event handler type
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     *
     * @return The event handler
     */
    public static Type<ActionClickHandler> getType() {
        return TYPE;
    }

    /**
     * Getter for the action that was clicked
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     *
     * @return the action that was clicked
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Getter for the data the action apply to
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     *
     * @return the row data
     */
    public Object getData() {
        return this.data;
    }

    /**
     * Helper function to make it easier to dispatch an ActionClickEvent
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 3 June 2013
     *
     * @param source - The object that is dispatching the event
     */
    public static void fire(HasHandlers source, String action, Object data) {
        source.fireEvent(new ActionClickEvent(action, data));
    }
}
