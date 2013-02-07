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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is tree node class that draws a single node and draws it's sub nodes when the expand button is clicked
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  31 Jan 2013
 */
public class TreeNode extends Composite {
    
    /**
     * The data object for the current node
     */
    private NodeObject nodeData;
    
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
     * The container that holds the check box
     */
    @UiField
    FlowPanel checkBoxContainer;
    
    /**
     * The container that holds the read only check box images
     */
    @UiField
    FlowPanel readOnlyCheckBoxContainer;
    
    /**
     * The check box that is displayed in edit state
     */
    @UiField
    CheckBox checkBox;
    
    /**
     * The read only check box image that is displayed in edit state
     */
    @UiField
    Image readOnlyImage;
    
    /**
     * The image that is display next to sub nodes. This displays top level nodes if the tree 
     * is in view state and the node is not selected
     */
    @UiField
    Image selectedUnselectedImage;
    
    /**
     * The container that holds the selected and unselected indicator image
     */
    @UiField
    FlowPanel selectedUnselectedImageContainer;
    
    /**
     * Instance of the parent tree
     */
    private Tree parent;
    
    /**
     * The parent node of the current node this will be null if the current node is a top level node
     */
    private TreeNode parentNode = null;
    
    /**
     * Instances of the sub node TreeNodes that is display when the node is expanded
     */
    private List<TreeNode> subNodeDisplayItems = new ArrayList<TreeNode>();
    
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
     * Instance of the resource class the component is using to display it's images
     */
    private TreeNodeResources resources;

    /**
     * Flag to indicate whether the tree is in a view or edit state
     */
    private boolean viewState;

    /**
     * Flag to indicate whether a node is expanded or not
     */
    private boolean isExpanded = false;

    /**
     * Flag to indicate whether the instances for the node items has been created
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
         * 
         * @return The image resource
         */
        @Source("images/expand-icon.png")
        ImageResource expandIcon();

        /**
         * The collapse image
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The image resource
         */
        @Source("images/minimise-Icon.png")
        ImageResource collapseIcon();
        
        /**
         * The image that is shown on root nodes in the view state if they are not selected
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  01 Feb 2013
         * 
         * @return The image resource
         */
        @Source("images/No-access-Icon.png")
        ImageResource viewStateTopNodeNotSelected();
        
        /**
         * The image that is shown next to sub nodes that that are selected
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  01 Feb 2013
         * 
         * @return The image resource
         */
        @Source("images/orange-branch.png")
        ImageResource branchSelected();
        
        /**
         * The image that is shown next to sub nodes that that are not selected
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  01 Feb 2013
         * 
         * @return The image resource
         */
        @Source("images/grey-branch.png")
        ImageResource branchNotSelected();
        
        /**
         * The image that is shown next to sub nodes that are selected
         * but not editable
         * 
         * @author Ruan Naude <ruan.naude@gmail.com>
         * @since  01 Feb 2013
         * 
         * @return The image resource
         */
        @Source("images/disabled_Checked.png")
        ImageResource disabledCheckedIcon();
        
        /**
         * The image that is shown next to sub nodes that are unselected
         * and not editable
         * 
         * @author Ruan Naude <ruan.naude@gmail.com>
         * @since  01 Feb 2013
         * 
         * @return The image resource
         */
        @Source("images/disabled.png")
        ImageResource disabledUncheckedIcon();
    }
    
    /**
     * The css resource for the TreeNode
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public interface Style extends CssResource {
    
        /**
         * The styling for the node text
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeTextStyle();

        /**
         * The color styling for the node text when the node is selected
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeSelected();

        /**
         * The style that applies the padding on the sub nodes of the node
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String subNodeIndent();

        /**
         * The style that adds the spacing the the node
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeSpacingStyle();

        /**
         * The style that adds spacing for the expand and collapse icon
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String expandCollapseImage();

        /**
         * The style that adds spacing for the selected icon
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String selectedImage();

        /**
         * The color styling for the node text when the node is not selected
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String nodeUnselected();
        
        /**
         * The extra padding that is applied to leaf node
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String leafNodeStyle();
        
        /**
         * The style that adds spacing for the check box in the edit state
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String checkBoxContainerStyle();
        
        /**
         * The style to remove the margin and the label of the checkbox
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  31 Jan 2013
         * 
         * @return The style compiled name
         */
        public String checkBox();
    }
    
    /**
     * Class constructor
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public TreeNode() {
        this(getDefaultResources());
    }
    
    /**
     * Class constructor
     * 
     * @param resources The resources class that provides the styles and images for the TreeNode
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public TreeNode(TreeNodeResources resources) {
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
     * Sets the data for the node and updates the state to match the data of the item
     * 
     * @param nodeData The node's data
     * @param viewState Flag that indicates whether the tree is in view or edit state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setNodeData(NodeObject nodeData, boolean viewState) {
        this.nodeData = nodeData;
        this.viewState = viewState;
        /*
         * This will only affect root nodes. This checks if a root has any children that are selected
         * if the node is not selected. If the node has no children that are selected this root node it hidden.
         */
        if (viewState && !isSelected() && nodeData.isNoChildrenSelected()) {
            this.setVisible(false);
            return;
        }
        /*
         * Update the selected image of the current node
         */
        updateNodeImage();
        
        // Adds value change handler to the check box if the Tree is in edit state and sets the check box to visible.
        if (!this.viewState) {
            if (!isReadOnly()) {
                this.checkBoxContainer.setVisible(true);
                this.readOnlyCheckBoxContainer.setVisible(false);
                this.checkBox.setValue(isSelected());
                this.checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    
                    /**
                     * The on value change handler function that is call when an item is selected or deselected
                     * 
                     * @param event The event being handled
                     */
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        // Update the node to it's new selection state
                        setSelected(event.getValue());
                    }
                });
            } else {
                this.checkBoxContainer.setVisible(false);
                this.readOnlyCheckBoxContainer.setVisible(true);
                if (isSelected()) {
                    this.readOnlyImage.setResource(resources.disabledCheckedIcon());
                } else {
                    this.readOnlyImage.setResource(resources.disabledUncheckedIcon());
                }
            }
        }
        
        // Updates the text style for the node
        updateSelectedState();
        
        // Set the text of the node
        textLabel.setText(nodeData.getNodeDisplayText());
        
        // Retrieves the sub nodes of the node
        List<NodeObject> subNodesData = getSubNodesData();
        
        // Check if the node has children
        if (subNodesData != null && subNodesData.size() > 0) {
            // Node has children
            
            /*
             * Expand the sub nodes if the node is not selected and node has children that is selected.
             * 
             * If the node is selected the or has no children that is selected the node is not expanded.
             */
            if (!isSelected() && !nodeData.isNoChildrenSelected()) {
                expandNode();
            }
            
            // Updates the expand button to the correct state
            updateExpandCollapseButtonState();
            
            // Add a click handler to the expand and collapse button
            expandCollapseButton.addClickHandler(new ClickHandler() {
                
                /**
                 * The on click event handler that expands and collapse the node
                 * 
                 * @param event The event being handled
                 */
                @Override
                public void onClick(ClickEvent event) {
                    if (isExpanded) {
                        collapseNode();
                    } else {
                        expandNode();
                    }
                }
            });
        } else {
            // If the node doesn't have children. Remove the expand button.
            expandCollapseButtonContainer.setVisible(false);
            mainPanel.setStyleName(resources.getTreeNodeStyles().leafNodeStyle());
        }
    }
    
    /**
     * Updates the node's expand and collapse button
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
     * Updates the node's branch image to match the selected state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  01 Feb 2013
     */
    public void updateNodeImage() {
        if (parentNode == null) {
            if (viewState) {
                if (!isSelected()) {
                    selectedUnselectedImage.setResource(resources.viewStateTopNodeNotSelected());
                    selectedUnselectedImageContainer.setVisible(true);
                }
            }
        } else {
            if (isSelected()) {
                selectedUnselectedImage.setResource(resources.branchSelected());
            } else {
                selectedUnselectedImage.setResource(resources.branchNotSelected());
            }
            selectedUnselectedImageContainer.setVisible(true);
        }
    }
    
    /**
     * Updates the node's text style to match the selected style
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
     * Expands the node
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void expandNode() {
        List<NodeObject> subNodesData = getSubNodesData();
        if (subNodesData != null && subNodesData.size() > 0) {
            if (!isChildrenDrawn ) {
                this.isChildrenDrawn  = true;
                for (NodeObject subNodeData : subNodesData) {
                    TreeNode tempNode = createSubNode();
                    tempNode.setParentNode(this);
                    tempNode.setNodeData(subNodeData, viewState);
                    tempNode.setParentTree(parent);
                    subNodeDisplayItems.add(tempNode);
                    subNodePanel.add(tempNode);
                }
            } else {
                subNodePanel.setVisible(true);
            }
            this.isExpanded = true;
            updateExpandCollapseButtonState();
        }
    }
    
    /**
     * Collapses the node
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void collapseNode() {
        subNodePanel.setVisible(false);
        this.isExpanded = false;
        updateExpandCollapseButtonState();
    }
    
    /**
     * Sets the node's parent tree
     * 
     * @param parent The parent tree
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setParentTree(Tree parent) {
        this.parent = parent;
    }
    
    /**
     * Retrieves the data for the node's children
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The data for the children
     */
    public List<NodeObject> getSubNodesData() {
        return nodeData.arrTreeChildren;
    }
    
    /**
     * Creates an instance of a sub node
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return An instance of a sub node
     */
    public TreeNode createSubNode() {
    	return new TreeNode();
    }
    
    /**
     * Retrieves the node's current selected state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     * 
     * @return The node's current selected state
     */
    public boolean isSelected() {
        return nodeData.isSelected();
    }
    
    /**
     * Determine whether the object is read only
     * 
     * @author Ruan Naude<ruan.naude@a24group.com>
     * @since  05 Feb 2013
     * 
     * @return Whether the object is read only
     */
    public boolean isReadOnly() {
        return nodeData.isReadOnly();
    }
    
    /**
     * Sets the node's selected state
     * 
     * @param selected The node's selected state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setSelected(boolean selected) {
        nodeData.setSelected(selected);
        checkBox.setValue(selected, false);
        updateSelectedState();
        updateNodeImage();
        if (selected) {
            nodeData.setAllChildrenSelectedState(selected);
            for (TreeNode subNode: subNodeDisplayItems) {
                subNode.setSelected(selected);
            }
        } else {
            expandNode();
            if (this.parentNode != null) {
                this.parentNode.setSelected(selected);
            }
        }
    }
    
    /**
     * Sets the parent nodes
     * 
     * @param parentNode The parent nodes
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  31 Jan 2013
     */
    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }
}
