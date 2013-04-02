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
     *
     * @param context -The context the cell is in
     * @param value - The value to be added in the cell
     * @param sb -The safe html builder.
     */
    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (value == null) {
            value = "";
        }
        sb.appendHtmlConstant("<div title=\"" + value + "\" >" + value + "</div>");
    }

}
