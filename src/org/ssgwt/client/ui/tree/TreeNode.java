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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is tree node class that to draw a node node and draw it's sub nodes
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  31 Jan 2013
 *
 * @param <NodeType> The type of the current nodes
 * @param <SubNodes> The type of the sub nodes that can be found on the top level nodes
 */
public abstract class TreeNode<NodeType extends NodeObject, SubNodes extends NodeObject> extends Composite {
    
    /**
     * The data object for the current node
     */
    private NodeType nodeData;
    
    /**
     * The mail panel of the node
     */
    @UiField
    FlowPanel mainPanel;
    
    /**
     * The text element of the node
     */
    @UiField
    Label textLabel;
    
    /**
     * The panel holding all the sub nodes
     */
    @UiField
    FlowPanel subNodePanel;
    
    /**
     * The image that is used as a button to expand and collapse a node
     */
    @UiField
    Image expandCollapseButton;
    
    /**
     * The container that holds the expand and collapse button
     */
    @UiField
    FlowPanel expandCollapseButtonContainer;
    
    /**
     * Instance of the parent tree
     */
    private Tree parent;
    
    /**
     * Flag to indicate if the node is a top level node
     */
    private boolean isTopLevelItem;
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 22 January 2013
     */
    interface Binder extends UiBinder<Widget, TreeNode> {
    }
    
    /**
     * Holds an instance of the default resources
     */
    private static TreeNodeResources DEFAULT_RESOURCES;
    
    /**
     * FIXME: Fix the comments
     */
    private TreeNodeResources resources;

    /**
     * FIXME: Fix the comments
     */
    private boolean viewState;

    /**
     * FIXME: Fix the comments
     */
    private boolean isExpanded = false;

    /**
     * FIXME: Fix the comments
     */
    private boolean isChildrenDrawn = false;
    
    /**
     * The resources interface for the tree node
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public interface TreeNodeResources extends ClientBundle {
        
        /**
         * Retrieves an implementation of the Style interface generated using the specified css file
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return An implementation of the Style interface
         */
        @Source("TreeNode.css")
        Style getTreeNodeStyles();
        
        /**
         * The expand image
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         */
        @Source("images/expand-icon.png")
        ImageResource expandIcon();

        /**
         * The collapse image
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         */
        @Source("images/minimise-Icon.png")
        ImageResource collapseIcon();

    }
    
    /**
     * The css resource for the text filter
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public interface Style extends CssResource {
    
        /**
         * The styling for the node
         * TODO: Update style comment to explain the use of the stlye
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeStyle();

        /**
         * The styling for the node
         * TODO: Update style comment to explain the use of the stlye
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeSelected();

        /**
         * The styling for the node
         * TODO: Update style comment to explain the use of the stlye
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String subNodeIndent();

        /**
         * The styling for the node
         * TODO: Update style comment to explain the use of the stlye
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeSpacingStyle();

        /**
         * The styling for the node
         * TODO: Update style comment to explain the use of the stlye
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String expandCollapseImage();

        /**
         * The styling for the node
         * TODO: Update style comment to explain the use of the stlye
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String selectedImage();

        /**
         * The styling for the node
         * TODO: Update style comment to explain the use of the stlye
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeUnselected();
    }
    
    /**
     * Class constructor
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public TreeNode() {
        this(getDefaultResources(), false);
    }
    
    /**
     * Class constructor
     * 
     * @param isTopLevelItem
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public TreeNode(boolean isTopLevelItem) {
        this(getDefaultResources(), isTopLevelItem);
    }
    
    /**
     * Class constructor
     * 
     * @param resources
     * @param isTopLevelItem
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public TreeNode(TreeNodeResources resources, boolean isTopLevelItem) {
        this.isTopLevelItem = isTopLevelItem;
        this.resources = resources;
        this.resources.getTreeNodeStyles().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    /**
     * Create an instance on the default resources object if it the
     * DEFAULT_RESOURCES variable is null if not it just return the object in
     * the DEFAULT_RESOURCES variable
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return the default resource object
     */
    private static TreeNodeResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(TreeNodeResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    /**
     * Getter for the resources instance
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The resources
     */
    public TreeNodeResources getResources() {
        return this.resources;
    }
    
    /**
     * FIXME: Fix the comments
     * 
     * @param nodeData
     * @param viewState
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setNodeData(NodeType nodeData, boolean viewState) {
        this.viewState = viewState;
        this.nodeData = nodeData;
        if (viewState && !isSelected() && nodeData.isNoChildrenSelected()) {
            this.setVisible(false);
            return;
        }
        updateSelectedState();
        textLabel.setText(getNodeDisplayText(nodeData));
        List<SubNodes> subNodesData = getSubNodesData(nodeData);
        if (subNodesData != null && subNodesData.size() > 0) {
            if (!viewState && !isSelected() && !nodeData.isNoChildrenSelected()) {
                expandNode();
            }
            updateExpandCollapseButtonState();
            expandCollapseButton.addClickHandler(new ClickHandler() {
                
                @Override
                public void onClick(ClickEvent event) {
                    if (isExpanded) {
                        collapseNode();
                    } else {
                        expandNode();
                    }
                    updateExpandCollapseButtonState();
                }
            });
        } else {
            expandCollapseButtonContainer.setVisible(false);
        }
    }
    
    /**
     * FIXME: Fix the comments
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void updateExpandCollapseButtonState() {
        if (this.isExpanded) {
            expandCollapseButton.setResource(resources.collapseIcon());
        } else {
            expandCollapseButton.setResource(resources.expandIcon());
        }
    }
    /**
     * FIXME: Fix the comments
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void updateSelectedState() {
        if (isSelected()) {
            textLabel.removeStyleName((resources.getTreeNodeStyles().nodeUnselected()));
            textLabel.addStyleName(resources.getTreeNodeStyles().nodeSelected());
        } else {
            textLabel.removeStyleName((resources.getTreeNodeStyles().nodeSelected()));
            textLabel.addStyleName(resources.getTreeNodeStyles().nodeUnselected());
        }
    }
    
    /**
     * FIXME: Fix the comments
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void expandNode() {
        if (!isChildrenDrawn) {
            this.isChildrenDrawn  = true;
            List<SubNodes> subNodesData = getSubNodesData(nodeData);
            for (SubNodes subNodeData : subNodesData) {
                TreeNode tempNode = createSubNode();
                tempNode.setNodeData(subNodeData, viewState);
                tempNode.setParentTree(parent);
                subNodePanel.add(tempNode);
            }
        } else {
            subNodePanel.setVisible(true);
        }
        this.isExpanded = true;
    }
    
    /**
     * FIXME: Fix the comments
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void collapseNode() {
        subNodePanel.setVisible(false);
        this.isExpanded = false;
    }
    
    /**
     * FIXME: Fix the comments
     * 
     * @param parent
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setParentTree(Tree parent) {
        this.parent = parent;
    }
    
    /**
     * FIXME: Fix the comments
     * 
     * @param nodeData
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return
     */
    public abstract String getNodeDisplayText(NodeType nodeData);
    
    /**
     * FIXME: Fix the comments
     * 
     * @param nodeData
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return
     */
    public abstract List<SubNodes> getSubNodesData(NodeType nodeData);
    
    /**
     * FIXME: Fix the comments
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return
     */
    public abstract TreeNode<SubNodes, ?> createSubNode();
    
    /**
     * FIXME: Fix the comments
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return
     */
    public boolean isSelected() {
        return nodeData.selected;
    }
    
    /**
     * FIXME: Fix the comments
     * 
     * @param selected
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setSelected(boolean selected) {
        nodeData.selected = selected;
    }
    
}
