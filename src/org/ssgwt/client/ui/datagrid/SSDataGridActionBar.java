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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Action Bar that will be placed below the SSDataGrid
 * 
 * @author Johannes Gryffenberg
 * @since 27 June 2012
 */
public class SSDataGridActionBar extends Composite {
    
    /**
     * The limit to be placed on the rows
     */
    private int rowLimit;
    
    /**
     * The actual amount of rows that will be displayed
     */
    private int rows;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Johannes Gryffenberg
     * @since 27 June 2012
     */
    interface Binder extends UiBinder<Widget, SSDataGridActionBar> {}
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * Constructor
     */
    public SSDataGridActionBar(){
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    /**
     * Sets the widget that should be displayed in the action bar
     * 
     * @param widget - The widget that should be displayed in the action bar
     */
    public void setActionBarWidget(Widget widget) {
        
    }
    
    /**
     * Setter for the row limit
     * 
     * @param rowLimit - the amount of rows needed
     */
    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }
    
    /**
     * Getter for the row limit
     * 
     * @return The row limit 
     */
    public int getRowLimit() {
        return this.rowLimit;
    }
    
    /**
     * Setter for the number of rows
     * 
     * @param rows - The number of rows
     */
    public void setNumerOfRows(int rows) {
        this.rows = rows;
    }
    
    /**
     * Getter for the number of rows
     * 
     * @return The number of rows
     */
    public int getNumberOfRows(){
        return this.rows;
    }
}
