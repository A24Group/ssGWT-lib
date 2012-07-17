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

package org.ssgwt.client.ui.event;

import com.google.gwt.event.shared.GwtEvent;
/**
 * Event dispatched by the focus image if it is clicked
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since  16 July 2012
 */
public class FocusImageClickEvent extends GwtEvent<IFocusImageClickEventHandler> {
    
    /**
     * Holds the event type
     */
    public static Type<IFocusImageClickEventHandler> TYPE = new Type<IFocusImageClickEventHandler>();
    
    /**
     * The data that will be transfered with the event
     */
    private Object data;
    
    /**
     * Class constructor
     * 
     * @param data The data that should be transfered with the event
     */
    public FocusImageClickEvent(Object data) {
        this.data = data;
    }
    
    /**
     * getter for data object
     * 
     * @return data object
     */
    public Object getData() {
        return data;
    }
    
    /**
     * setter for data object
     * 
     * @param data object
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * retrieves the custom event type
     * 
     * @return custom event type
     */
    public com.google.gwt.event.shared.GwtEvent.Type<IFocusImageClickEventHandler> getAssociatedType() {
        return TYPE;
    }
    
    /**
     * dispatch the event
     * 
     * @param handler - instance of the event handler
     */
    protected void dispatch(IFocusImageClickEventHandler handler) {
        handler.onFocusImageClickEvent(this);
    }
    
}