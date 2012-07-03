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

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.cellview.client.Column;

/**
 * Custom event used when a sort action is performed
 * 
 * @author Michael Barnard
 * @since 3 July 2012
 */
public class SelectAllEvent extends GwtEvent<ISelectAllEventHandler> {

    /**
     * Holds the event type
     */
    public static Type<ISelectAllEventHandler> TYPE = new Type<ISelectAllEventHandler>();

    /**
     * Class constructor
     * 
     * @param column The Column data that should be transfered with the event
     * @param ascending The ascending data that should be transfered with the event
     */
    public SelectAllEvent() {
    }

    /**
     * Retrieves the custom event type
     * 
     * @return custom event type
     */
    public com.google.gwt.event.shared.GwtEvent.Type<ISelectAllEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Dispatch the event
     * 
     * @param handler - instance of the event handler
     */
    protected void dispatch(ISelectAllEventHandler handler) {
        handler.onSelectAllEvent(this);
    }

}