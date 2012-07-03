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
package org.ssgwt.client.ui.datagrid.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Custom data grid event handler
 * 
 * @author Michael Barnard
 * @since 3 July 2012
 */
public interface ISelectAllEventHandler extends EventHandler {
    
    /**
     * Handler function for the DataEvent
     * 
     * @param event - The event that should be handled
     */
    void onSelectAllEvent(SelectAllEvent event);
}