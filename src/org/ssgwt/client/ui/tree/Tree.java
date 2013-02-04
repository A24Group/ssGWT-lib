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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * The tree class.
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  31 Jan 2013
 */
public class Tree extends Composite {
    
    /**
     * The main panel that holds all the items
     */
    private FlowPanel mainPanel = new FlowPanel();
    
    /**
     * The data used to create the tree
     */
    private List<NodeObject> treeData;
    
    /**
     * Class constructor
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public Tree() {
        initWidget(mainPanel);
    }
    
    /**
     * Set the data the tree should display. Calling this function will redraw the tree
     * 
     * @param treeData - The data that will be used to draw the tree
     * @param viewState - Flag indicating if the tree is in view or edit state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setData(List<NodeObject> treeData, boolean viewState) {
        this.treeData = treeData;
        createNodes(viewState);
    }
    
    /**
     * Retrieves the data the that was used to draw the tree
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The tree data set using the setData function
     */
    public List<NodeObject> getData() {
        return treeData;
    }
    
    /**
     * Creates all the node top level nodes for the tree
     * 
     * @param viewState Flag to indicate if the tree should be in view or edit state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    private void createNodes(boolean viewState) {
        for (NodeObject nodeData : treeData) {
            TreeNode tempNode = createNode();
            tempNode.setNodeData(nodeData, viewState);
            tempNode.setParentTree(this);
            mainPanel.add(tempNode);
        }
    }
    
    /**
     * Creates an instance of a top level node
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The new instance of the top level node
     */
    public TreeNode createNode() {
        return new TreeNode();
    }
}
