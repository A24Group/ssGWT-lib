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
package org.ssgwt.client.ui.menu.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Custom event used when a sort action is performed
 * 
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 10 July 2012
 */
public class LeftMenuItemSelectEvent extends GwtEvent<ILeftMenuItemSelectEventHandler> {

    /**
     * Holds the event type
     */
    public static Type<ILeftMenuItemSelectEventHandler> TYPE = new Type<ILeftMenuItemSelectEventHandler>();

    /**
     * Class constructor
     * 
     * @param column The Column data that should be transfered with the event
     * @param ascending The ascending data that should be transfered with the event
     */
    public LeftMenuItemSelectEvent() {
    }

    /**
     * Retrieves the custom event type
     * 
     * @return custom event type
     */
    public com.google.gwt.event.shared.GwtEvent.Type<ILeftMenuItemSelectEventHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Dispatch the event
     * 
     * @param handler - instance of the event handler
     */
    protected void dispatch(ILeftMenuItemSelectEventHandler handler) {
        handler.onLeftMenuItemSelectEvent(this);
    }
}