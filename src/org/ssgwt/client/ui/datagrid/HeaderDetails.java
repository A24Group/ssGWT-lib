package org.ssgwt.client.ui.datagrid;

import org.ssgwt.client.ui.datagrid.filter.AbstractHeaderFilter;

/**
 * Object holding info of the FilterSortHeader for the FilterSortCell
 * 
 * @author Johannes Gryffenberg
 * @since 29 June 2012
 */
public class HeaderDetails {

    /**
     * The label on the header
     */
    public String label;

    /**
     * The filter widget that should be displayed if the filte icon is clicked
     */
    public AbstractHeaderFilter filterWidget;
}
