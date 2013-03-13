package org.ssgwt.client.ui.datagrid.event;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is dispatched when a user changes a filter
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since 9 July 2012
 */
public class DataGridRowSelectionChangedEvent<T> extends GwtEvent<DataGridRowSelectionChangedEvent.DataGridRowSelectionChangedHandler> {

    /**
     * The handler type for the event
     */
    public static Type<DataGridRowSelectionChangedHandler> TYPE = new Type<DataGridRowSelectionChangedHandler>();
    
    /**
     * The data for the rows that was selected or deselected
     */
    private List<T> changedRows;

    /**
     * The event handler interface for the event
     * 
     * @author Johannes Gryffenberg
     * @since 9 July 2012
     */
    public interface DataGridRowSelectionChangedHandler extends EventHandler {
        
        /**
         * Handles the DataGridRowSelectionChangedEvent
         * 
         * @param event - The event that should be handled
         */
        void onDataGridRowSelectionChanged(DataGridRowSelectionChangedEvent event);
    }

    /**
     * The event constructor
     * 
     * @param changedRows - The data for the rows that was selected or deselected
     */
    public DataGridRowSelectionChangedEvent(List<T> changedRows) {
        this.changedRows = changedRows;
    }

    /**
     * Getter to retrieve the data of the rows that was selected or deselected
     * 
     * @return The data of the rows that was selected or deselected
     */
    public List<T> getChangedRows() {
        return changedRows;
    }

    /**
     * Dispatches the event
     * 
     * @param handler - The function that will handle the event
     */
    @Override
    protected void dispatch(DataGridRowSelectionChangedHandler handler) {
        handler.onDataGridRowSelectionChanged(this);
    }

    /**
     * Getter to retrieve the event handler type
     * 
     * @return The event handler
     */
    @Override
    public Type<DataGridRowSelectionChangedHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter to retrieve the event handler type
     * 
     * @return The event handler
     */
    public static Type<DataGridRowSelectionChangedHandler> getType() {
        return TYPE;
    }
}
