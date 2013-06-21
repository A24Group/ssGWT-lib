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

import com.google.gwt.user.cellview.client.Header;

/**
 * This is the header that is used when a custom style needs to be applied to it
 * The style will only apply to the inner most cell of the header
 * 
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  20 June 2013
 */
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