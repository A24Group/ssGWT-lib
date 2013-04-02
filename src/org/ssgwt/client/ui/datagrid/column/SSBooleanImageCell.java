package org.ssgwt.client.ui.datagrid.column;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A boolean cell that will display a image in a cell
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since 02 April 2013
 */
public class SSBooleanImageCell extends AbstractCell<Boolean> {

    /**
     * The url of the image being displayed if the boolean value is true
     */
    private final String tImageUrl;

    /**
     * The url of the image being displayed if the boolean value is fa;se
     */
    private final String fImageUrl;

    /**
     * The SSBooleanImageCell constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 02 April 2013
     *
     * @param tImageUrl - The image to display if the the boolean value is true
     * @param fImageUrl - The image to display if the the boolean value is false
     */
    public SSBooleanImageCell(String tImageUrl, String fImageUrl) {
        super();
        this.tImageUrl = tImageUrl;
        this.fImageUrl = fImageUrl;
    }

    /**
     * This will create a image with with the url passed in by the constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 02 April 2013
     *
     * @param context -The context the cell is in
     * @param value - The value to be added in the cell but not the image thats going to be displayed
     * @param sb -The save html builder.
     */
    @Override
    public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
        String columnString;
        if (value == null) {
            value = false;
        }
        if (value) {
            if (tImageUrl == null) {
                columnString = "<div title=\"" + value + "\" ></div>";
            } else {
                columnString = "<div title=\"" + value + "\" > <img src='" + tImageUrl + "' /> </div>";
            }
        } else {
            if (fImageUrl == null) {
                columnString = "<div title=\"" + value + "\" ></div>";
            } else {
                columnString = "<div title=\"" + value + "\" > <img src='" + fImageUrl + "' /> </div>";
            }
        }
        sb.appendHtmlConstant(columnString);
    }

}
