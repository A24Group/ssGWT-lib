package org.ssgwt.client.ui.datagrid;

import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.DoStatement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.layout.client.Layout.Layer;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
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
     * Whether the data grid supports selecting multiple rows
     */
    private boolean multiSelect;
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * The DataGrid that will be displayed on the screen
     */
    @UiField(provided = true)
    protected DataGrid<T> dataGrid;
    
    /**
     * The DataGrid that will be displayed on the screen
     */
    @UiField
    protected FlowPanel actionBar;

    @UiField
    FlowPanel actionBarContainer;
    
    /**
     * The pager that will handle the paging of the DataGrid
     */
    @UiField(provided = true)
    protected SimplePager pager;

    /**
     * Class Constructor
     * 
     * @author Lodewyk Duminy
     * @since 29 June 2012
     */
    public SSDataGrid() {
        this((DataGrid.Resources)GWT.create(DataGrid.Resources.class), (SimplePager.Resources)GWT.create(SimplePager.Resources.class));
    }
    
    /**
     * Class Constructor
     * 
     * @author Lodewyk Duminy
     * @since 29 June 2012
     */
    public SSDataGrid(DataGrid.Resources dataGridResource, SimplePager.Resources pagerResource) {
        this.initWidget(uiBinder.createAndBindUi(this));
        dataGrid = new DataGrid<T>(10, dataGridResource);
        pager = new SimplePager(TextLocation.CENTER, pagerResource, false, 0, true);
        pager.setDisplay(dataGrid);
    }

    /**
     * Setter to set the data that should be displayed on the DataGrid
     * 
     * @param data - The data the should be displayed on the data grid
     * 
     * @author Lodewyk Duminy
     * @since 29 June 2012
     */
    public void setData(List<T> data) {
        
    }

    /**
     * Getter to retrieve the data currently being displayed on the DataGrid
     * 
     * @author Lodewyk Duminy
     * @since 29 June 2012
     * 
     * @return The data being displayed on the DataGrid
     */
    public List<T> getData() {
        return null;
    }
    
    /**
     * Ensures that the datagrid has a scrollbar when the browser is too small
     * to display all the rows.
     * 
     * @author Lodewyk Duminy
     * @since 29 June 2012
     */
    @Override
    public void onResize() {
        dataGrid.setHeight((this.getOffsetHeight() - 40) + "px");
    }
    
    /**
     * Adds a column to the end of the table.
     * 
     * @param col the column to be added
     */
    public void addColumn(Column<T, ?> col) {
        dataGrid.addColumn(col);
    }

    /**
     * Adds a column to the end of the table with an associated header.
     * 
     * @param col the column to be added
     * @param header the associated {@link Header}
     */
    public void addColumn(Column<T, ?> col, Header<?> header) {
        dataGrid.addColumn(col, header);
    }

    /**
     * Adds a column to the end of the table with an associated header and footer.
     * 
     * @param col the column to be added
     * @param header the associated {@link Header}
     * @param footer the associated footer (as a {@link Header} object)
     */
    public void addColumn(Column<T, ?> col, Header<?> header, Header<?> footer) {
        dataGrid.addColumn(col, header, footer);
    }

    /**
     * Adds a column to the end of the table with an associated String header.
     * 
     * @param col the column to be added
     * @param headerString the associated header text, as a String
     */
    public void addColumn(Column<T, ?> col, String headerString) {
        dataGrid.addColumn(col, headerString);
    }

    /**
     * Adds a column to the end of the table with an associated {@link SafeHtml}
     * header.
     * 
     * @param col the column to be added
     * @param headerHtml the associated header text, as safe HTML
     */
    public void addColumn(Column<T, ?> col, SafeHtml headerHtml) {
        dataGrid.addColumn(col, headerHtml);
    }

    /**
     * Adds a column to the end of the table with an associated String header and
     * footer.
     * 
     * @param col the column to be added
     * @param headerString the associated header text, as a String
     * @param footerString the associated footer text, as a String
     */
    public void addColumn(Column<T, ?> col, String headerString, String footerString) {
        dataGrid.addColumn(col, headerString, footerString);
    }

    /**
     * Adds a column to the end of the table with an associated {@link SafeHtml}
     * header and footer.
     * 
     * @param col the column to be added
     * @param headerHtml the associated header text, as safe HTML
     * @param footerHtml the associated footer text, as safe HTML
     */
    public void addColumn(Column<T, ?> col, SafeHtml headerHtml, SafeHtml footerHtml) {
        dataGrid.addColumn(col, headerHtml, footerHtml);
    }
    
    /**
     * Sets the amount of rows that the data grid will have. This
     * does not specify how many rows will be displayed.
     * 
     * @param count - The amount of rows that the data grid will have
     */
    public final void setRowCount(int count) {
        dataGrid.setRowCount(count);
    }

    /**
     * Sets the amount of rows that the data grid will have. This
     * does not specify how many rows will be displayed.
     * 
     * @param count - The amount of rows that the data grid will have
     * @param isExact - Whether or not the row measurement is exact
     */
    public void setRowCount(int size, boolean isExact) {
        dataGrid.setRowCount(size, isExact);
    }
    
    /**
     * Hides the action bar by setting it invisible
     */
    public void hideActionBar(){
        actionBar.setVisible(false);
    }
    
    /**
     * Hides the header by setting it invisible
     */
    public void hideHeader(){
        //TODO: Add functionality for reusability
    }
    
    /**
     * Adds a widget to the action bar
     * 
     * @param actionBarWidget - The widget that needs to be added to the action bar
     */
    public void setActionBarWidget(Widget actionBarWidget){
        this.actionBarContainer.add(actionBarWidget);
    }
    
    /**
     * Sets the size of the pager.
     * 
     * @param pageSize - The size of the pager
     */
    public void setPageSize(int pageSize) {
      pager.setPageSize(pageSize);
    }
    
    /**
     * 
     * @return
     */
    public boolean isMultiSelect(){
        return multiSelect;
    }
    
    /**
     * Set whether or not the grid should support multiple row select
     * 
     * @param multiSelect - Whether the data grid should support multiple row select
     */
    public void setMultiSelect(boolean multiSelect){
        this.multiSelect = multiSelect;
        //TODO: Waiting for some stuff from Ruan
    }
    
}
