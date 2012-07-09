package org.ssgwt.client.ui.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the class for the left menu item
 * 
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 09 July 2012
 */
public class LeftMenuItem extends Composite {
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * The default resource to use for the LeftMenuItem class
     */
    private static LeftMenuItemResources DEFAULT_RESOURCES;
    
    /**
     * The resource to use for the LeftMenuItem class
     */
    private LeftMenuItemResources resources;
    
    /**
     * The menu item that is currently being used
     */
    private MenuItem menuItem;
    
    /**
     * The container for the whole left menu item
     */
    @UiField
    LayoutPanel leftMenuItem;
    
    /**
     * The flow panel that contains
     * the unselected items of the menu item
     */
    @UiField
    FocusPanel notSelectedPanel;
    
    /**
     * The image to use for the unselected menu item state
     */
    @UiField(provided = true)
    Image notSelectedImage;
    
    /**
     * The unselected menu item label
     */
    @UiField
    Label notSelectedLabel;
    
    /**
     * The layout panel that contains
     * the selected menu item items
     */
    @UiField
    LayoutPanel selectedPanel;
    
    /**
     * The image to use for the selected menu item state
     */
    @UiField(provided = true)
    Image selectedImage;
    
    /**
     * The selected menu item label
     */
    @UiField
    Label selectedLabel;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    interface Binder extends UiBinder<Widget, LeftMenuItem> {
    }

    /**
     * A ClientBundle that provides style for this widget.
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public interface LeftMenuItemResources extends ClientBundle {
        
        /**
         * The style source to be used in this widget
         */
        @Source("LeftMenuItem.css")
        Style leftMenuItemStyle();
    }
    
    /**
     * The Css style recourse items to use in this widget
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public interface Style extends CssResource {
        
        /**
         * The style for the layout panel that contains
         * the whole left menu item
         * 
         * @return The name of the compiled style
         */
        String leftMenuItem();
        
        /**
         * The style for the flow panel that contains
         * the unselected items of the menu item
         * 
         * @return The name of the compiled style
         */
        String notSelectedPanel();
        
        /**
         * The style for the unselected menu item
         * image
         * 
         * @return The name of the compiled style
         */
        String notSelectedImage();
        
        /**
         * The style for the unselected menu item
         * label
         * 
         * @return The name of the compiled style
         */
        String notSelectedLabel();
        
        /**
         * The style for the layout panel that contains
         * the selected menu item items
         * 
         * @return The name of the compiled style
         */
        String selectedPanel();
        
        /**
         * The style for the selected menu item image
         * 
         * @return The name of the compiled style
         */
        String selectedImage();
        
        /**
         * The style for the selected menu item label
         * 
         * @return The name of the compiled style
         */
        String selectedLabel();
        
    }
    
    /**
     * Class constructor for just menu item
     * 
     * @param menuItem The menu item to create the left menu item details from
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public LeftMenuItem(MenuItem menuItem) {
        this(menuItem, getDefaultResources());
    }
    
    /**
     * Class constructor for menu item and resource
     * 
     * @param menuItem The menu item to create the left menu item details from
     * @param resources The recource to be used for the LeftMenuItem
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public LeftMenuItem(MenuItem menuItem, LeftMenuItemResources resources) {
        this.resources = resources;
        this.resources.leftMenuItemStyle().ensureInjected();
        
        //if the passed in menuItem is not empty then set styles
        //on elements and related details in menuItem on elements
        if (menuItem != null) {
            //get images from menuItem
            notSelectedImage = new Image(menuItem.getUnSelectedImage());
            selectedImage = new Image(menuItem.getSelectedImage());
            this.initWidget(uiBinder.createAndBindUi(this));
            
            leftMenuItem.setStyleName(resources.leftMenuItemStyle().leftMenuItem());
            notSelectedPanel.setStyleName(resources.leftMenuItemStyle().notSelectedPanel());
            
            //add click handler to notSelectedPanel to trigger the animation on the menu item
            notSelectedPanel.addMouseUpHandler(new MouseUpHandler() {
                
                /**
                 * Will handle the mouse up event on the panel
                 * 
                 * @param event The mouse event
                 */
                @Override
                public void onMouseUp(MouseUpEvent event) {
                    setSelected();
                }
            });
            
            notSelectedImage.setStyleName(resources.leftMenuItemStyle().notSelectedImage());
            notSelectedLabel.setStyleName(resources.leftMenuItemStyle().notSelectedLabel());
            notSelectedLabel.setText(menuItem.getLabel());
            
            selectedImage.setStyleName(resources.leftMenuItemStyle().selectedImage());
            selectedLabel.setStyleName(resources.leftMenuItemStyle().selectedLabel());
            selectedLabel.setText(menuItem.getLabel());
            selectedPanel.setStyleName(resources.leftMenuItemStyle().selectedPanel());
            leftMenuItem.forceLayout();
        }
    }
    
    /**
     * This function will check of there is already a default resource to
     * use for the left menu item and if not create a default resource
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     * 
     * @return The default resource for the LeftMenuItem
     */
    private static LeftMenuItemResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(LeftMenuItemResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    /**
     * This function will set the state of the left menu item
     * to the selected state
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public void setSelected() {
        //will create the slide animation from right to left
        leftMenuItem.setWidgetLeftRight(selectedPanel, 0, com.google.gwt.dom.client.Style.Unit.PX, 0, com.google.gwt.dom.client.Style.Unit.PX);
        leftMenuItem.animate(1000);
    }
    
    /**
     * This function will set the state of the left menu item
     * to the unselected state
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public void setUnselected() {
        //will create the slide animation from left to right
        leftMenuItem.setWidgetRightWidth(selectedPanel, 0, com.google.gwt.dom.client.Style.Unit.PX, 0, com.google.gwt.dom.client.Style.Unit.PX);
        leftMenuItem.animate(1000);
    }
}
