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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class will render a top menu based on a list 
 * of menu items that is passed in to it
 * 
 * @author Michael Barnard
 * @since 9 July 2012
 */
public class TopMenuBar extends Composite {
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * The default resources holder for the top menu
     */
    private static TopMenuResources DEFAULT_RESOURCES;
    
    /**
     * The holder for the custom resources
     */
    private TopMenuResources resources;
    
    /**
     * The holder for the selected menu item
     */
    private Button selectedItem = null;
    
    /**
     * A list of the menu item
     */
    private List<MenuItemInterface> menuItems;
    
    /**
     * The flow panel used for the menu item list
     */
    @UiField
    FlowPanel topMenu;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Michael Barnard
     * @since 9 July 2012
     */
    interface Binder extends UiBinder<Widget, TopMenuBar> {
    }

    /**
     * A ClientBundle that provides images for this widget.
     */
    public interface TopMenuResources extends ClientBundle {
        
        /**
         * The styles used in this widget.
         */
        @Source("TopMenuBar.css")
        Style topMenuStyle();
    }
    
    /**
     * The css resource standards that that should be followed
     * 
     * @author Michael Barnard
     * @since 9 July 2012
     */
    public interface Style extends CssResource {
        
        /**
         * The style for a non selected button
         *    
         * @return The name of the compiled style
         */
        String buttonStyle();
        
        /**
         * The fix for the broken bottom padding in firefox
         * 
         * @return The name of the compiled style
         */
        String bottomPadding();
        
        /**
         * The style for the selected button 
         *    
         * @return The name of the compiled style
         */
        String buttonSelectedStyle();
        
        /**
         * The style for the panel that contains all the menu items
         *    
         * @return The name of the compiled style
         */
        String containerStyle();
        
    }
    
    /**
     * The class constructor
     * 
     * @param topMenuList - The list of menu items that needs to be added to the menu bar
     */
    public TopMenuBar(List<MenuItemInterface> topMenuList) {
        this(topMenuList, getDefaultResources());
    }

    /**
     * The class constructor
     * 
     * @param menuItems - The list of menu items that needs to be added to the menu bar
     * @param resources - The resources that needs to be used on the menu bar
     */
    public TopMenuBar(List<MenuItemInterface> menuItems, TopMenuResources resources) {
        this.resources = resources;
        this.resources.topMenuStyle().ensureInjected();
        this.initWidget(uiBinder.createAndBindUi(this));
        this.setTopMenuBar(menuItems);
    }
    
    /**
     * Setter for the menu Items
     * 
     * @param menuItems - The list of menu items that needs to be added to the menu bar
     */
    public void setTopMenuBar(List<MenuItemInterface> menuItems) {
        setTopMenuBar(menuItems, false);
    }
    
    /**
     * Setter for the menu Items
     * 
     * @param menuItems - The list of menu items that needs to be added to the menu bar
     * @param runExecuteForDefault - Indicates that the command for the default selected item should be run
     */
    public void setTopMenuBar(List<MenuItemInterface> menuItems, boolean runExecuteForDefault) {
        topMenu.clear();
        if (menuItems != null) {
            List<MenuItemInterface> sorted = new ArrayList<MenuItemInterface>();
            int max = 0;
            for (int x = 0; x  < menuItems.size(); x++) {
                int current = menuItems.get(x).getOrder();
                if (current > max) {
                    max = current;
                }
            }
            for(int x = 0; x <= max; x++) {
                for (MenuItemInterface menuItem : menuItems) {
                    int current = menuItem.getOrder();
                    if (current == x) {
                        sorted.add(menuItem);
                    }
                }
            }
            menuItems = sorted;
            boolean containsDefault = false;
            for (MenuItemInterface menuItem : sorted) {
                containsDefault = containsDefault || menuItem.isDefaultSelected();
            }
            for (MenuItemInterface menuItem : menuItems) {
                final Button button = new Button();
                final MenuItemInterface currentMenuItem = menuItem;
                button.setText(currentMenuItem.getLabel());

                button.addMouseUpHandler(new MouseUpHandler() {
                    
                    /**
                     * Triggered when the mouse button is released
                     * while the pointer is over the menu item
                     * 
                     * @param event - The event that will be triggered
                     */
                    @Override
                    public void onMouseUp(MouseUpEvent event) {
                        selectedItem.setStyleName(TopMenuBar.this.resources.topMenuStyle().buttonStyle() + " " + TopMenuBar.this.resources.topMenuStyle().bottomPadding());
                        button.setStyleName(TopMenuBar.this.resources.topMenuStyle().buttonSelectedStyle() + " " + TopMenuBar.this.resources.topMenuStyle().bottomPadding());
                        selectedItem = button;
                        currentMenuItem.getCommand().execute();
                    }
                });
                if (!containsDefault) {
                    selectedItem = button;
                    button.setStyleName(resources.topMenuStyle().buttonSelectedStyle() + " " + TopMenuBar.this.resources.topMenuStyle().bottomPadding());
                    containsDefault = true;
                    if (runExecuteForDefault) {
                        currentMenuItem.getCommand().execute();
                    }
                } else {
                    if (menuItem.isDefaultSelected()) {
                        selectedItem = button;
                        button.setStyleName(resources.topMenuStyle().buttonSelectedStyle() + " " + TopMenuBar.this.resources.topMenuStyle().bottomPadding());
                        if (runExecuteForDefault) {
                            currentMenuItem.getCommand().execute();
                        }
                    } else {
                        button.setStyleName(resources.topMenuStyle().buttonStyle() + " " + TopMenuBar.this.resources.topMenuStyle().bottomPadding());
                    }
                }
                topMenu.setStyleName(resources.topMenuStyle().containerStyle());
                topMenu.add(button);
            }
        }
    }
    
    /**
     * Gets the default menu bar resources
     * 
     * @return The default resources that should be used for the menu bar
     */
    private static TopMenuResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(TopMenuResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
}
