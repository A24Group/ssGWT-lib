package org.ssgwt.client.ui.datagrid.column;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A text cell that will display text in a cell with a tooltip with the
 * same text value as the cell
 * 
 * @author Ruan Naude <nauderuan777@gmail.com>
 * @since 14 March 2013
 */
public class SSTextCell extends AbstractCell<String> {

    /**
     * This will create a label with text and a tooltip with the same text value
     * 
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 14 March 2013
     */
    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant("<div title=\"" + value + "\" >" + value + "</div>");
    }

}
