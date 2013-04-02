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
    private final String trueImageUrl;

    /**
     * The url of the image being displayed if the boolean value is false
     */
    private final String falseImageUrl;

    /**
     * The SSBooleanImageCell constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 02 April 2013
     *
     * @param trueImageUrl - The image to display if the the boolean value is true
     * @param falseImageUrl - The image to display if the the boolean value is false
     */
    public SSBooleanImageCell(String trueImageUrl, String falseImageUrl) {
        super();
        this.trueImageUrl = trueImageUrl;
        this.falseImageUrl = falseImageUrl;
    }

    /**
     * This will create a image with the url passed in by the constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 02 April 2013
     *
     * @param context -The context the cell is in
     * @param value - The value to be added in the cell but not the image thats going to be displayed
     * @param sb -The safe html builder.
     */
    @Override
    public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
        String columnString;
        if (value == null) {
            value = false;
        }
        if (value) {
            if (trueImageUrl == null) {
                columnString = "<div title=\"" + value + "\" ></div>";
            } else {
                columnString = "<div title=\"" + value + "\" > <img src='" + trueImageUrl + "' /> </div>";
            }
        } else {
            if (falseImageUrl == null) {
                columnString = "<div title=\"" + value + "\" ></div>";
            } else {
                columnString = "<div title=\"" + value + "\" > <img src='" + falseImageUrl + "' /> </div>";
            }
        }
        sb.appendHtmlConstant(columnString);
    }

}
