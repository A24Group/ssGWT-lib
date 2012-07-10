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

import org.ssgwt.client.ui.menu.event.ILeftMenuItemSelectEventHandler;
import org.ssgwt.client.ui.menu.event.LeftMenuItemSelectEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * The SSDataGrid with a changeable action bar that dispatches events for
 * sorting, filtering, selection clicking of row and paging
 * 
 * @author Lodewyk Duminy
 * @since 09 July 2012
 */
public class LeftMenuBar extends Composite {

    /**
     * The resource to use for the LeftMenuItem class
     */
    private LeftMenuBarResources resources;
    
    /**
     * The default resource to use for the LeftMenuItem class
     */
    private static LeftMenuBarResources DEFAULT_RESOURCES;
    
    /**
     * The left menu bar container that will hold the menu items
     */
    @UiField
    FlowPanel leftMenuBarContainer;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Lodewyk Duminy
     * @since 09 July 2012
     */
    interface Binder extends UiBinder<Widget, LeftMenuBar> {
    }

    /**
     * A ClientBundle that provides images for this widget.
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since 09 July 2012
     */
    public interface LeftMenuBarResources extends ClientBundle {
        
        /**
         * The styles used in this widget.
         */
        @Source("LeftMenuBar.css")
        Style leftMenuBarStyle();
    }
    
    /**
     * The Css style recourse items to use in this widget
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since 09 July 2012
     */
    public interface Style extends CssResource {
        
        /**
         * The style for the layout panel that contains
         * the whole left menu bar
         * 
         * @return The name of the compiled style
         */
        String leftMenuBar();
    }
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * Instance of the currently selected menu item
     */
    private LeftMenuItem selectedItem;
    
    /**
     * Class constructor for just the left menu bar
     * 
     * @param menuItems List of menu items
     * 
     * @author Lodewyk Duminy
     * @since 09 July 2012
     */
    public LeftMenuBar(List<MenuItem> menuItems) {
        this(menuItems, getDefaultResources());
    }
    
    /**
     * Class Constructor for left menu bar and resources
     * 
     * @param menuItems List of menu items
     * @param resources Style resources
     * 
     * @author Lodewyk Duminy
     * @since 09 July 2012
     */
    public LeftMenuBar(List<MenuItem> menuItems, LeftMenuBarResources resources) {
    	this.resources = resources;
    	this.resources.leftMenuBarStyle().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));
        
        if (menuItems != null){
            List<MenuItem> sorted = new ArrayList<MenuItem>();
            int max = 0;
            for (int x = 0; x  < menuItems.size(); x++) {
                int current = menuItems.get(x).getOrder();
                if (current > max) {
                    max = current;
                }
            }
            for (int x = 0; x <= max; x++) {
                for (MenuItem menuItem : menuItems) {
                    int current = menuItem.getOrder();
                    if (current == x) {
                        sorted.add(menuItem);
                    }
                }
            }
            menuItems = sorted;
            boolean containsDefault = false;
            for (MenuItem menuItem : sorted) {
                containsDefault = containsDefault || menuItem.isDefaultSelected();
            }
            for (MenuItem menuItem : menuItems) {
                final LeftMenuItem item = new LeftMenuItem(menuItem);
                if (!containsDefault){
                    selectedItem = item;
                    item.setSelected();
                    
                    containsDefault = true;
                } else {
                    if (menuItem.isDefaultSelected()){
                        selectedItem = item;
                        item.setSelected();
                    } else {
                        item.setUnselected();
                    
                    }
                }
                
                item.addEventHandler(new ILeftMenuItemSelectEventHandler() {
                    
                    @Override
                    public void onLeftMenuItemSelectEvent(LeftMenuItemSelectEvent event) {
                        if (item != selectedItem) {
                            // Remove the selected style from the previously selected item
                            selectedItem.setUnselected();
                            // Give the newly selected item the selected style
                            item.setSelected();
                            selectedItem = item;
                        }
                    }
                });
                
                leftMenuBarContainer.add(item);
            }
        }
    }
    
    /**
     * This function will check of there is already a default resource to
     * use for the left menu item and if not create a default resource
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since 09 July 2012
     * 
     * @return The default resource for the LeftMenuBar
     */
    private static LeftMenuBarResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(LeftMenuBarResources.class);
        }
        return DEFAULT_RESOURCES;
    }
}