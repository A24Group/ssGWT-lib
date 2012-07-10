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

import java.util.List;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Image;

/**
 * This class will hold details for a menu item. This can be in different places
 * 
 * @author Michael Barnard
 * @since 9 July 2012
 */
public class MenuItem {

    /**
     * The place name of the menu item
     */
    private String placeName;
    
    /**
     * The text to display on the menu item
     */
    private String label;
    
    /**
     * The order in which to load the menu items
     */
    private int iOrder;
    
    /**
     * whether this item should be defaultly selected
     */
    private boolean defaultSelected;
    
    /**
     * The command action to perform when the item is selected
     */
    private Command command;
    
    /**
     * The image to show when the button is selected
     */
    private String selectedImage;
    
    /**
     * The image to show when the button is not selected
     */
    private String unSelectedImage;
    
    /**
     * A list of sub menu items to use
     */
    private List<MenuItem> subMenus;
    
    /**
     * The class constructor
     * 
     * @param placeName - The place name of the menu item
     * @param label - The text to display on the menu item
     * @param iOrder - The order in which to load the menu items
     * @param defaultSelected - whether this item should be defaultly selected
     * @param command - The command action to perform when the item is selected
     */
    public MenuItem(String placeName, String label, int iOrder, boolean defaultSelected, Command command) {
        this(placeName, label, iOrder, defaultSelected, command, null, null, null);
    }
    
    /**
     * The class constructor
     *  
     * @param placeName - The place name of the menu item
     * @param label - The text to display on the menu item
     * @param iOrder - The order in which to load the menu items
     * @param defaultSelected - whether this item should be defaultly selected
     * @param command - The command action to perform when the item is selected
     * @param selectedImage - The image to show when the button is selected
     * @param unSelectedImage - The image to show when the button is not selected
     * @param subMenus - A list of sub menu items to use
     */
    public MenuItem(String placeName, String label, int iOrder, boolean defaultSelected, Command command, String selectedImage, String unSelectedImage, List<MenuItem> subMenus) {
        this.placeName = placeName;
        this.label = label;
        this.iOrder = iOrder;
        this.defaultSelected = defaultSelected;
        this.command = command;
        this.selectedImage = selectedImage;
        this.unSelectedImage = unSelectedImage;
        this.subMenus = subMenus;
    }
    
    /**
     * Getter for the placename of the menu item
     * 
     * @return the placename of the menu item
     */
    public String getPlaceName() {
        return this.placeName;
    }
    
    /**
     * Setter for the placename
     * 
     * @param placeName - The placename of the menu item
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
    /**
     * Getter for label of the menu item
     * 
     * @return The label of the menu item
     */
    public String getLabel() {
        return this.label;
    }
    
    /**
     * Setter got the label of the menu item
     *  
     * @param label - The label that will be displayed on the menu item
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Getter for the order of the current menu item
     * 
     * @return The order of the current menu item
     */
    public int getOrder() {
        return this.iOrder;
    }
    
    /**
     * Setter for the order that this button
     * 
     * @param iOrder - The order of this menu item
     */
    public void setOrder(int iOrder) {
        this.iOrder = iOrder;
    }
    
    /**
     * Getter for the default selected state of the menu item
     * 
     * @return Whether the menu item should be defaultly selected
     */
    public boolean isDefaultSelected() {
        return this.defaultSelected;
    }
    
    /**
     * Setter for the default selected state of the menu item
     * 
     * @param defaultSelected - The default selected state of the menu item
     */
    public void setDefaultSelected(boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }
    
    /**
     * Getter for the command action of the menu item
     * 
     * @return The command action for the menu item
     */
    public Command getCommand() {
        return this.command;
    }
    
    /**
     * Setter for the command action of the menu item
     * 
     * @param command - The command action of the menu item
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    /**
     * Getter for the selected image of the menu item
     * 
     * @return The image of the menu item when it is selected
     */
    public String getSelectedImage() {
        return this.selectedImage;
    }

    /**
     * Setter for the selected image of the menu item
     * 
     * @param selectedImage - The image of the menu item when it is selected
     */
    public void setSelectedImage(String selectedImage) {
        this.selectedImage = selectedImage;
    }
    
    /**
     * Getter for the unselected image of the menu item
     * 
     * @return The unselected image of the menu item
     */
    public String getUnSelectedImage() {
        return this.unSelectedImage;
    }
    
    /**
     * Setter for the not selected image of the menu item
     * 
     * @param unSelectedImage - The image of the menu item when it is not selected
     */
    public void setUnSelectedImage(String unSelectedImage) {
        this.unSelectedImage = unSelectedImage;
    }
    
    /**
     * Getter for the sub menu items of this menu item
     * 
     * @return The sub menu items of this menu item
     */
    public List<MenuItem> getSubMenus() {
        return this.subMenus;
    }
    
    /**
     * Setter for the sub menu items of this menu item
     * 
     * @param subMenu - The sub menu items of this menu item
     */
    public void setSubMenus(List<MenuItem> subMenus) {
        this.subMenus = subMenus;
    }
    
}
