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
package org.ssgwt.client.ui.tree;

import java.util.List;

/**
 * The node object.
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  31 Jan 2013
 */
public abstract class NodeObject {

    /**
     * Getter for the list of children nodes for this node
     * 
     * @author Ruan Naude<ruan.naude@a24Group.com>
     * @since  18 Feb 2013
     * 
     * @return The list of children nodes for this node
     */
    public abstract List<NodeObject> getChildren();
    
    /**
     * Function used to check if all the items is selected
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return true in no children is selected
     */
    public boolean isNoChildrenSelected() {
        for (NodeObject child : getChildren()) {
            if (child.isSelected() || !child.isNoChildrenSelected()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Retrieves the object's selected state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  01 Feb 2013
     * 
     * @return The object's selected state
     */
    public abstract boolean isSelected();
    
    /**
     * Set the object's selected state
     * 
     * @param selected The new selected state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  01 Feb 2013
     */
    public abstract void setSelected(boolean selected);
    
    /**
     * Determine whether the object is read only
     * 
     * @author Ruan Naude<ruan.naude@a24group.com>
     * @since  05 Feb 2013
     * 
     * @return Whether the object is read only
     */
    public abstract boolean isReadOnly();
    
    /**
     * Sets whether the object is read only
     * 
     * @param readOnly The read only state
     * 
     * @author Ruan Naude<ruan.naude@a24group.com>
     * @since  05 Feb 2013
     */
    public abstract void setReadOnly(boolean readOnly);
    
    /**
     * Updates the selected state of all the sub nodes
     * 
     * @param selected The new selected state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  01 Feb 2013
     */
    public void setAllChildrenSelectedState(boolean selected) {
        if (getChildren() != null) {
            for (NodeObject child : getChildren()) {
                child.setSelected(selected);
                child.setAllChildrenSelectedState(selected);
            }
        }
    }
    
    /**
     * Retrieves the text that should be displayed on the tree for the item
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  01 Feb 2013
     * 
     * @return The text that should be displayed on the tree for the item
     */
    public abstract String getNodeDisplayText();
}
