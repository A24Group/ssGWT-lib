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

import java.util.Date;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
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
     * The format that the date is currently displayed in
     */
    String sDateFormat = "";

    /**
     * The format that the date value should be displayed in
     */
    String sDateDisplayTooltipFormat = "";

    /**
     * Instance of the template
     */
    private static Template template;
    
    /**
     * Template providing SafeHTML templates to build the widget
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  11 June 2013
     */
    interface Template extends SafeHtmlTemplates {

        @Template("<div title=\"{0}\" >{1}</div>")
        SafeHtml action(String title, String value);
    }
    
    /**
     * Class Constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 June 2013
     */
    public SSTextCell() {
        super();
        if (template == null) {
            template = GWT.create(Template.class);
        }
    }
    
    /**
     * Class Constructor
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  10 June 2013
     * 
     * @param sDateFormat - The format that the date is currently displayed in
     * @param sDateDisplayTooltip - The date format that you want to have the tooltip displayed
     */
    public SSTextCell(String sDateFormat, String sDateDisplayTooltip) {
        this();
        this.sDateFormat = sDateFormat;
        this.sDateDisplayTooltipFormat = sDateDisplayTooltip;
    }
    
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
        String tooltip = value;
        if (this.sDateDisplayTooltipFormat != null && this.sDateDisplayTooltipFormat != ""
            && this.sDateFormat != null && this.sDateFormat != "") {
            try {
                // Convert date from sDateFormat to sDateDisplayTooltipFormat
                Date date = DateTimeFormat.getFormat(this.sDateFormat).parse(value);
                tooltip = DateTimeFormat.getFormat(this.sDateDisplayTooltipFormat).format(date);
            } catch (Exception e){
                // Ignore exception, resulting in default tooltip
            }
        }
        sb.append(template.action(tooltip, value));
    }

}
