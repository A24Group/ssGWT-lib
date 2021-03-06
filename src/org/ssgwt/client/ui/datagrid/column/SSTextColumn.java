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

import org.ssgwt.client.ui.datagrid.column.ImageHoverColumn.AbstractImageColumnPopup;

import com.google.gwt.user.cellview.client.Column;

/**
 * The SSTextColumn to be able to get the column name from a event
 *
 * @author Alec Erasmus
 * @since 14 Aug 2012
 */
public abstract class SSTextColumn<T> extends Column<T, String> implements SortableColumnWithName {

    /**
     * The vo used in the row for the column
     */
    public T rowVo = null;
    
    /**
     * Construct a new TextColumn.
     */
    public SSTextColumn() {
        super(new SSTextCell());
    }

    /**
     * Construct a new TextColumn.
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 June 2013
     *
     * @param sDateFormat - The format that the date is currently displayed in
     * @param sDateDisplayTooltip - The date format that you want to have the tooltip displayed
     */
    public SSTextColumn(String sDateFormat, String sDateDisplayTooltip) {
        super(new SSTextCell(sDateFormat, sDateDisplayTooltip));
    }

    /**
     * Construct a new TextColumn.
     * With a custom tool tip
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     *
     * @param popup - The custom popup to display on hover
     */
    public SSTextColumn(AbstractImageColumnPopup<T> popup) {
        super(new SSTextCell<T>(popup));

        SSTextCell<T> textCell = (SSTextCell<T>) getCell();
        textCell.setParentColumn(this);
    }

    /**
     * This function return the data represented by the row.
     * If you use a custom popup fir the tool tip you need to override this function
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  17 June 2013
     *
     * @return the data in the row
     */
    public T getRowData() {
        return this.rowVo;
    }
    
    /**
     * Determine whether the popup should be shown or not.
     * 
     * This needs to be overriden if special logic is needed.
     * 
     * @param data The data from the row
     * 
     * @return Whether the popup should be shown or not
     */
    public boolean showPopup(T data) {
        return true;
    }
}
