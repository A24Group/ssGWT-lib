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

import com.google.gwt.user.client.ui.Widget;

/**
 * Class holding the configuration for a single column that should be displayed on a SSDataGrid
 * 
 * @author Johannes Gryffenberg
 * @since 27 June 2012
 */
public class SSDataGridColumn {
    
    /**
     * The header text for the column
     */
    private String headerText;
    
    /**
     * The width of the column
     */
    private String width;
    
    /**
     * The name of the field in the data provider the data should be retrieved from
     */
    private String dataField;
    
    /**
     * The name of the filter type that will be used on the column
     */
    private String filterType;
    
    /**
     * Setter for the header text for the column
     * 
     * @param headerText - The header text for the column
     */
    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }
    
    /**
     * Setter for the width of the column
     * 
     * @param width - The width of the column
     */
    public void setWidth(String width) {
        this.width = width;
    }
    
    /**
     * Setter for the name of the field in the data provider the data should be retrieved from
     * 
     * @param dataField - The name of the field in the data provider the data should be retrieved from
     */
    public void setDataField(String dataField) {
        this.dataField = dataField;
    }
    
    /**
     * Getter for the header text for the column
     * 
     * @return The header text for the column
     */
    public String getHeaderText() {
        return this.headerText;
    }
    
    /**
     * Getter for the width of the column
     * 
     * @return The width of the column
     */
    public String getWidth() {
        return this.width;
    }
    
    /**
     * Getter for the name of the field in the data provider the data should be retrieved from
     * 
     * @return The name of the field in the data provider the data should be retrieved from
     */
    public String getDataField() {
        return this.dataField;
    }
    
    /**
     * Setter for the filter type
     * 
     * @param filterType - The filter type that needs to be used
     */
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
    
    /**
     * Getter for the filter type that is being used
     * 
     * @return the filter type that is being used
     */
    public String getFilterType() {
        return this.filterType;
    }
}
