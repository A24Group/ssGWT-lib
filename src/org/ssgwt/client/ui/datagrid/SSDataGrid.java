package org.ssgwt.client.ui.datagrid;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

/**
 * The SSDataGrid with a changeable action bar that dispatches events for
 * sorting, filtering, selection clicking of row and paging
 * 
 * @author Johannes Gryffenberg
 * @param <T>
 * @since 29 June 2012
 */
public class SSDataGrid<T> extends Composite implements RequiresResize {

    /**
     * UiBinder interface for the composite
     * 
     * @author Johannes Gryffenberg
     * @since 29 June 2012
     */
    interface Binder extends UiBinder<Widget, SSDataGrid> {
    }

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * The DataGrid that will be displayed on the screen
     */
    @UiField(provided = true)
    protected DataGrid<T> dataGrid = new DataGrid<T>();

    /**
     * The resource for the SimplePager
     */
    protected SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);

    /**
     * The pager that will handle the paging of the DataGrid
     */
    @UiField(provided = true)
    protected SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);

    /**
     * Class Constructor
     */
    public SSDataGrid() {
        this.initWidget(uiBinder.createAndBindUi(this));
        
        pager.setDisplay(dataGrid);
        pager.setPageSize(10);
        
        this.setData(null);
        
    }

    /**
     * Setter to set the data that should be displayed on the DataGrid
     * 
     * @param data - The data the should be displayed on the data grid
     */
    public void setData(List<T> data) {
        
    }

    /**
     * Getter to retrieve the data currently being displayed on the DataGrid
     * 
     * @return The data being displayed on the DataGrid
     */
    public List<T> getData() {
        return null;
    }
    
    /**
     * Ensures that the datagrid has a scrollbar when the browser is too small
     * to display all the rows.
     */
    @Override
    public void onResize() {
        dataGrid.setHeight((this.getOffsetHeight( ) - 40) + "px");
    }
}
