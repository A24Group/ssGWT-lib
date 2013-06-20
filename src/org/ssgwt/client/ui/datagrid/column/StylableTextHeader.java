package org.ssgwt.client.ui.datagrid.column;

import com.google.gwt.user.cellview.client.Header;

public class StylableTextHeader extends Header<String> {

    /**
     * The string that will be used for the header text
     */
    private String text;

    /**
     * Construct a new TextHeader.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  20 June 2013
     *
     * @param text the header text as a String
     * @param styleName the style name that needs to be applied to the cell
     */
    public StylableTextHeader(String text, String styleName) {
        super(new SSTextCell(styleName));
        this.text = text;
    }

    /**
     * Return the header text.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  20 June 2013
     * 
     * @return the text for the header
     */
    @Override
    public String getValue() {
        return text;
    }
}