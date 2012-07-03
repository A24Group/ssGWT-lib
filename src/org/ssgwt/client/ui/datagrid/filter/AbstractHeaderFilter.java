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
package org.ssgwt.client.ui.datagrid.filter;

import org.ssgwt.client.ui.datagrid.FilterSortCell;

import com.google.gwt.user.client.ui.PopupPanel;

public abstract class AbstractHeaderFilter extends PopupPanel {

    /**
     * Flag indicating whether the filter is active or not
     */
    private boolean filterActive = false;

    /**
     * The header the filter is linked to
     */
    private FilterSortCell parentHeader = null;

    /**
     * Creates an empty popup panel. A child widget must be added to it before it
     * is shown.
     */
    public AbstractHeaderFilter() {
        super();
    }

    /**
     * Creates an empty popup panel, specifying its "auto-hide" property.
     *
     * @param autoHide <code>true</code> if the popup should be automatically
     *          hidden when the user clicks outside of it or the history token
     *          changes.
     */
    public AbstractHeaderFilter(boolean autoHide) {
        super(autoHide);
    }

    /**
     * Creates an empty popup panel, specifying its "auto-hide" and "modal"
     * properties.
     *
     * @param autoHide <code>true</code> if the popup should be automatically
     *          hidden when the user clicks outside of it or the history token
     *          changes.
     * @param modal <code>true</code> if keyboard or mouse events that do not
     *          target the PopupPanel or its children should be ignored
     */
    public AbstractHeaderFilter(boolean autoHide, boolean modal) {
        super(autoHide, modal);
    }

    /**
     * Setter the header the filter belongs to
     * 
     * @param parentHeader - The header the filter belongs to
     */
    public void setParentHeader(FilterSortCell parentHeader) {
        this.parentHeader = parentHeader;
    }

    /**
     * Getter that return whether the filter is active or not
     * 
     * @return Flag indicating whether the filter is active or not
     */
    public boolean isFilterActive() {
        return filterActive;
    }

}
