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
package org.ssgwt.client.ui.datagrid;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The object type to be used when the multi select state wants to be used
 * 
 * @author Michael Barnard
 * @since 2 July 2012
 */
public abstract class AbstractMultiSelectObject {
    
    /**
     * Whether or not the object is selected
     */
    @JsonIgnore
    private boolean selected;
    
    /**
     * Sets whether the object is selected or not
     * 
     * @param selected - whether the object is selected
     */
    @JsonIgnore
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    /**
     * Get whether the object is selected or not
     * 
     * @return whether the object is selected
     */
    @JsonIgnore
    public boolean isSelected() {
        return this.selected;
    }
}
