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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.Column;

/**
 * The SSActionColumn is a column that display actions and fire an event on click of one of the actions.
 *
 * @author Alec Erasmus
 * @since  03 June 2013
 */
public abstract class SSActionColumn<T> extends Column<T, T> {

    /**
     * Construct a new SSActionColumn.
     *
     * @author Alec Erasmus
     * @since  03 June 2013
     */
    public SSActionColumn() {
        this(5);
    }
    
    /**
     * Construct a new SSActionColumn.
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  18 July 2013
     * 
     * @param spacerSize - The space between the actions
     */
    public SSActionColumn(int spacerSize) {
        super(new SSActionCell<T>(spacerSize));

        SSActionCell<T> actionCell = (SSActionCell<T>) getCell();
        actionCell.setParentColumn(this);
    }

    /**
     * Get the actions to add in the cell
     *
     * @author Alec Erasmus
     * @since  03 June 2013
     *
     * @param data - The data in the row
     *
     * @return List of the actions
     */
    public abstract ArrayList<String> getActions(T data);

    /**
     * Adds a event handler for the ActionClickEvent
     *
     * @author Alec Erasmus
     * @since  03 June 2013
     *
     * @param handler - The event handler
     *
     * @return HandlerRegistration
     */
    public HandlerRegistration addActionClickHandler(ActionClickEvent.ActionClickHandler handler) {
        return ((SSActionCell<T>)this.getCell()).addActionClickHandler(handler);
    }
}
