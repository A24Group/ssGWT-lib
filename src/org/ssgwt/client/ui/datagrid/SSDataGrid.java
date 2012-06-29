package org.ssgwt.client.ui.datagrid;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * The SSDataGrid with a changeable action bar that dispatches events for
 * sorting, filtering, selection clicking of row and paging
 * 
 * @author Johannes Gryffenberg
 * @since 29 June 2012
 */
public class SSDataGrid<T> extends Composite {

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
    private DataGrid<T> dataGrid = new DataGrid<T>();

    /**
     * The resource for the SimplePager
     */
    private SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);

    /**
     * The pager that will handle the paging of the DataGrid
     */
    private SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);

    /**
     * Class Constructor
     */
    public SSDataGrid() {

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

}
