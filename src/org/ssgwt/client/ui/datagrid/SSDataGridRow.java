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
package org.ssgwt.client.ui.datagrid;

import java.util.List;

import org.ssgwt.client.ui.datagrid.SSDataGridHeader.Binder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Displays a single row on a SSDataGrid
 * 
 * @author Johannes Gryffenberg
 * @since 27 June 2012
 */
public class SSDataGridRow extends Composite {

    /**
     * Indication of whether multiple rows can be selected 
     */
    private boolean multipleSelect;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Johannes Gryffenberg
     * @since 27 June 2012
     */
    interface Binder extends UiBinder<Widget, SSDataGridRow> {}
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * Constructor
     */
    public SSDataGridRow() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    /**
     * Setter to set the column header for the columns
     * 
     * @param columns
     */
    public void setColumns( List<SSDataGridColumn> columns ) {
        
    }
    
    /**
     * Setter for the multi selectable option for the rows
     * 
     * @param isMultiSelect - whether or not multiple rows can be selected at a time
     */
    public void setMultiSelect(boolean isMultiSelect) {
        this.multipleSelect = isMultiSelect;
    }
    
    /**
     * Getter for the multi selectable state
     * 
     * @return Whether or not the rows can be multiply selected
     */
    public boolean isMultiSelect() {
        return this.multipleSelect;
    }
    
    /**
     * Setter to set the data that should be displayed on the data grid row
     * 
     * @param data - The object holding the data that should be displayed on the row
     */
    public void setRowData(Object data) {
        
    }
}
