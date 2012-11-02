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
package org.ssgwt.client.ui.menu;

import org.ssgwt.client.ui.menu.event.ILeftMenuItemSelectEventHandler;
import org.ssgwt.client.ui.menu.event.LeftMenuItemSelectEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
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
    private MenuItemInterface menuItem;
    
    /**
     * Boolean to determine whether the menu item
     * can be click at the moment
     */
    private boolean isClickable = true;
    
    /**
     * Boolean to determine whether the menu item
     * is busy animating
     */
    private boolean isAnimating = false;
    
    /**
     * Boolean to determine whether a que to
     * make the menu item unselected has been made
     */
    private boolean isUnselectQued = false;
    
    /**
     * The handler manager used to handle events
     */
    private HandlerManager handlerManager;
    
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
    FlowPanel notSelectedFlowPanel;
    
    /**
     * The focus panel that contains
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
     * The container for the non selected menu item count
     */
    @UiField
    FlowPanel notSelectedNotificationContainer;
    
    /**
     * The label for the non selected menu item count
     */
    @UiField
    Label notSelectedNotificationCount;
    
    /**
     * The container for the selected menu item label and notification count
     */
    @UiField
    FlowPanel selectedLabelContainer;
    
    /**
     * The container for the selected menu item count
     */
    @UiField
    FlowPanel selectedNotificationContainer;
    
    /**
     * The label for the selected menu item count
     */
    @UiField
    Label selectedNotificationCount;
    
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
         * The up state style for the flow panel that contains
         * the unselected items of the menu item
         * 
         * @return The name of the compiled style
         */
        String notSelectedPanelUpState();
        
        /**
         * The over state style for the flow panel that contains
         * the unselected items of the menu item
         * 
         * @return The name of the compiled style
         */
        String notSelectedPanelOverState();
        
        /**
         * The down state style for the flow panel that contains
         * the unselected items of the menu item
         * 
         * @return The name of the compiled style
         */
        String notSelectedPanelDownState();
        
        /**
         * The out state style for the flow panel that contains
         * the unselected items of the menu item
         * 
         * @return The name of the compiled style
         */
        String notSelectedPanelOutState();
        
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
        
        /**
         * The style for the container for the non selected menu item count
         */
        String notSelectedNotificationContainer();
        
        /**
         * The style for the label for the non selected menu item count
         */
        String notSelectedNotificationCount();
        
        /**
         * The style for the container for the selected menu item label and notification count
         */
        String selectedLabelContainer();
        
        /**
         * The style for the container for the selected menu item count
         */
        String selectedNotificationContainer();
        
        /**
         * The style for the label for the selected menu item count
         */
        String selectedNotificationCount();
    }
    
    /**
     * Class constructor for just menu item
     * 
     * @param menuItem The menu item to create the left menu item details from
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public LeftMenuItem(MenuItemInterface menuItem) {
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
    public LeftMenuItem(MenuItemInterface menuItem, LeftMenuItemResources resources) {
        this.resources = resources;
        this.resources.leftMenuItemStyle().ensureInjected();
        handlerManager = new HandlerManager(this);
        
        //if the passed in menuItem is not empty then set styles
        //on elements and related details in menuItem on elements
        if (menuItem != null) {
            this.menuItem = menuItem;
            //get images from menuItem
            notSelectedImage = new Image(menuItem.getUnSelectedImage());
            selectedImage = new Image(menuItem.getSelectedImage());
            this.initWidget(uiBinder.createAndBindUi(this));
            
            leftMenuItem.setStyleName(resources.leftMenuItemStyle().leftMenuItem());
            notSelectedPanel.setStyleName(resources.leftMenuItemStyle().notSelectedPanelOutState());
            
            //add click handler to notSelectedPanel to trigger the animation on the menu item
            //and add the mouse up, out, down and over state
            notSelectedPanel.addMouseUpHandler(new MouseUpHandler() {
                
                /**
                 * Will handle the mouse up event on the panel
                 * 
                 * @param event The mouse event
                 */
                @Override
                public void onMouseUp(MouseUpEvent event) {
                    notSelectedPanel.setStyleName(LeftMenuItem.this.resources.leftMenuItemStyle().notSelectedPanelUpState());
                    if (isClickable) {
                        isClickable = false;
                        setSelected(true);
                        fireEvent(new LeftMenuItemSelectEvent());
                    }
                }
            });
            
            notSelectedPanel.addMouseDownHandler(new MouseDownHandler() {
                
                /**
                 * Will handle the mouse down event on the panel
                 * 
                 * @param event The mouse event
                 */
                @Override
                public void onMouseDown(MouseDownEvent event) {
                    notSelectedPanel.setStyleName(LeftMenuItem.this.resources.leftMenuItemStyle().notSelectedPanelDownState());
                }
            });
            
            notSelectedPanel.addMouseOverHandler(new MouseOverHandler() {
                
                /**
                 * Will handle the mouse over event on the panel
                 * 
                 * @param event The mouse event
                 */
                @Override
                public void onMouseOver(MouseOverEvent event) {
                    notSelectedPanel.setStyleName(LeftMenuItem.this.resources.leftMenuItemStyle().notSelectedPanelOverState());
                }
            });
            
            notSelectedPanel.addMouseOutHandler(new MouseOutHandler() {
                
                /**
                 * Will handle the mouse out event on the panel
                 * 
                 * @param event The mouse event
                 */
                @Override
                public void onMouseOut(MouseOutEvent event) {
                    notSelectedPanel.setStyleName(LeftMenuItem.this.resources.leftMenuItemStyle().notSelectedPanelOutState());
                }
            });
            
            notSelectedImage.setStyleName(resources.leftMenuItemStyle().notSelectedImage());
            notSelectedLabel.setStyleName(resources.leftMenuItemStyle().notSelectedLabel());
            notSelectedLabel.setText(menuItem.getLabel());
            notSelectedNotificationContainer.setStyleName(resources.leftMenuItemStyle().notSelectedNotificationContainer());
            notSelectedNotificationCount.setStyleName(resources.leftMenuItemStyle().notSelectedNotificationCount());
            
            selectedImage.setStyleName(resources.leftMenuItemStyle().selectedImage());
            selectedLabel.setStyleName(resources.leftMenuItemStyle().selectedLabel());
            selectedLabel.setText(menuItem.getLabel());
            selectedPanel.setStyleName(resources.leftMenuItemStyle().selectedPanel());
            selectedLabelContainer.setStyleName(resources.leftMenuItemStyle().selectedLabelContainer());
            selectedNotificationContainer.setStyleName(resources.leftMenuItemStyle().selectedNotificationContainer());
            selectedNotificationCount.setStyleName(resources.leftMenuItemStyle().selectedNotificationCount());
            
            removeNotificationCount();
            
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
        setSelected(false);
    }
    
    /**
     * This function will set the state of the left menu item
     * to the selected state
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     * 
     * @param executeTopMenuDefault Indicates whether the top menu's command should be executed
     */
    public void setSelected(boolean executeTopMenuDefault) {
        //will create the slide animation from right to left
        if (!isAnimating) {
            ((LeftMenuCommand)LeftMenuItem.this.menuItem.getCommand()).setExecuteTopMenuCommand(executeTopMenuDefault);
            LeftMenuItem.this.menuItem.getCommand().execute();
            isAnimating = true;
            leftMenuItem.setWidgetLeftRight(selectedPanel, 0, Unit.PX, 0, Unit.PX);
            leftMenuItem.setWidgetLeftWidth(notSelectedFlowPanel, -100, Unit.PCT, 100, Unit.PCT);
            leftMenuItem.animate(600);
            
            Timer timer = new Timer() {
                public void run() {
                    isAnimating = false;
                    if (isUnselectQued) {
                        isUnselectQued = false;
                        setUnselected();
                    }
                }
            };
            timer.schedule(680);
        }
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
        if (isAnimating) {
            isUnselectQued = true;
        } else {
            isAnimating = true;
            leftMenuItem.setWidgetRightWidth(selectedPanel, 0, Unit.PX, 0, Unit.PX);
            leftMenuItem.setWidgetLeftWidth(notSelectedFlowPanel, 0, Unit.PX, 100, Unit.PCT);
            leftMenuItem.animate(600);
            
            Timer timer = new Timer() {
                public void run() {
                    isClickable = true;
                    isAnimating = false;
                }
            };
            timer.schedule(600);
        }
    }
    
    /**
     * This function will set the notification count for the
     * selected and unselected state of the menu item
     * 
     * @param count The number to display as the notification count
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     */
    public void setNotificationCount(int count) {
        if (count != 0) {
            selectedNotificationCount.setText("" + count);
            notSelectedNotificationCount.setText("" + count);
            selectedNotificationContainer.setVisible(true);
            notSelectedNotificationContainer.setVisible(true);
        }
    }
    
    /**
     * This function will remove and hide the notification count for the
     * selected and unselected state of the menu item
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     */
    public void removeNotificationCount() {
        selectedNotificationCount.setText("");
        notSelectedNotificationCount.setText("");
        selectedNotificationContainer.setVisible(false);
        notSelectedNotificationContainer.setVisible(false);
    }
    
    /**
     * This is used to fire an event
     * 
     * @param event - The event that needs to be fired
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    /**
     * Adds a handler to the handler manager
     * 
     * @param handler - The handler to be added to the handle manager
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     * 
     * @return The handle registration
     */
    public HandlerRegistration addEventHandler(
            ILeftMenuItemSelectEventHandler handler) {
        return handlerManager.addHandler(LeftMenuItemSelectEvent.TYPE, handler);
    }
}