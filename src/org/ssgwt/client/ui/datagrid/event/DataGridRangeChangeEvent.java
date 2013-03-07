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
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.view.client.Range;

/**
 * Represents a range change event on the datagrid
 * 
 * @author Ruan Naude <nauderuan777@gmail.com>
 * @since 04 March 2013
 */
public class DataGridRangeChangeEvent extends GwtEvent<DataGridRangeChangeEvent.Handler> {

    /**
     * The handler type for the event
     */
    public static Type<Handler> TYPE = new Type<Handler>();
    
    /**
     * The new range on the datagrid
     */
    private final Range range;
    
    /**
     * Handler interface for {@link DataGridRangeChangeEvent} events.
     */
    public static interface Handler extends EventHandler {

        /**
         * Called when a {@link DataGridRangeChangeEvent} is fired.
         *
         * @param event the {@link DataGridRangeChangeEvent} that was fired
         * 
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 04 March 2013
         */
        void onRangeChange(DataGridRangeChangeEvent event);
    }

    /**
     * Fires a {@link DataGridRangeChangeEvent} on all registered handlers in the handler
     * manager.
     *
     * @param source the source of the handlers
     * @param range the new range
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 04 March 2013
     */
    public static void fire(HasHandlers source, Range range) {
        DataGridRangeChangeEvent event = new DataGridRangeChangeEvent(range);
        source.fireEvent(event);
    }

    /**
     * Gets the type associated with this event.
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 04 March 2013
     *
     * @return returns the handler type
     */
    public static Type<Handler> getType() {
        if (TYPE == null) {
            TYPE = new Type<Handler>();
        }
        return TYPE;
    }

    /**
     * Creates a {@link DataGridRangeChangeEvent}.
     *
     * @param range the new range
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 04 March 2013
     */
    protected DataGridRangeChangeEvent(Range range) {
        this.range = range;
    }

    /**
     * Gets the type associated with this event.
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 04 March 2013
     * 
     * @return returns the handler type
     */
    @Override
    public final Type<Handler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Gets the new range.
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 04 March 2013
     *
     * @return the new range
     */
    public Range getNewRange() {
        return range;
    }

    /**
     * Dispatches the event
     * 
     * @param handler - The function that will handle the event
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 04 March 2013
     */
    @Override
    protected void dispatch(Handler handler) {
        handler.onRangeChange(this);
    }
}
