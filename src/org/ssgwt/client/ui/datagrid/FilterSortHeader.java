package org.ssgwt.client.ui.datagrid;

import org.ssgwt.client.ui.datagrid.filter.AbstractHeaderFilter;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.user.cellview.client.Header;

/**
 * The header that is used to enable filtering on a table that uses the
 * FilterSortCell
 * 
 * @author Johannes Gryffenberg
 * @since 29 June 2012
 */
public class FilterSortHeader extends Header<HeaderDetails> {

    /**
     * Holds details of the header
     */
    private static HeaderDetails headerDetails = new HeaderDetails();

    /**
     * Constructor taking the label that should be displayed on the header
     * 
     * @param label - The label for the header
     */
    public FilterSortHeader(String label) {
        this(label, null);
    }

    /**
     * Constructor taking the label that should be displayed on the header
     * 
     * @param label - The label for the header
     * @param filterWidget - The widget that should be displayed if the user clicks on the filter icon
     */
    public FilterSortHeader(String label, AbstractHeaderFilter filterWidget) {
        super(new FilterSortCell());
        headerDetails.label = label;
        headerDetails.filterWidget = filterWidget;
    }

    /**
     * Retrieves the header details
     */
    @Override
    public HeaderDetails getValue() {
        return headerDetails;
    }

}
