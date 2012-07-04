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

import org.ssgwt.client.ui.datagrid.event.IHelpEventHandler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.Header;

/**
 * The header that is used for the help feature.
 * 
 * @author Ruan Naude
 * @since 4 July 2012
 */
public class HelpHeader extends Header<String> {

    /**
     * The cell that needs to be used as the header
     */
    HelpCell HelpCell;
    
    /**
     * Constructor
     */
    public HelpHeader() {
        super(new HelpCell());
        HelpCell = ( HelpCell )getCell( );
    }
    
    /**
     * Retrieves the header details
     */
    @Override
    public String getValue() {
        return null;
    }
    
    /**
     * Adds a handler to the handler manager
     * 
     * @param handler - The handler to be added to the handle manager
     * @return The handle registration 
     */
    public HandlerRegistration addEventHandler(
            IHelpEventHandler handler) {
        return HelpCell.addEventHandler(handler);
    }

}
