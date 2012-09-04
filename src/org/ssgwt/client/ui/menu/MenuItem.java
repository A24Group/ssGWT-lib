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

import name.pehl.piriti.commons.client.Transient;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;

/**
 * This class will hold details for a menu item. This can be in different places
 * 
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since 9 July 2012
 */
public class MenuItem {
    
    /**
     * Json reader
     */
    public interface JsonModuleReader extends JsonReader<MenuItem> {}
    
    /**
     * Json writer
     */
    public interface JsonModuleWriter extends JsonWriter<MenuItem> {}
    
    /**
     * Json reader
     */
    public static final JsonModuleReader JSON_READER = GWT.create( JsonModuleReader.class );
    
    /**
     * Json writer
     */
    public static final JsonModuleWriter JSON_WRITER = GWT.create( JsonModuleWriter.class );
    
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
    private int order;
    
    /**
     * whether this item should be defaultly selected
     */
    private boolean defaultSelected;
    
    /**
     * The command action to perform when the item is selected
     */
    @Transient
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
     * The reference name for the menu item
     */
    private String referenceName;
    
    /**
     * The notification count for the menu item
     */
    private int notificationCount;
    
    /**
     * Name places that will also cause this left menu item to be selected
     */
    private List<String> referencedNamePlaces;
    

    /**
     * The class constructor
     */
    public MenuItem() {
        
    }
    
    /**
     * The class constructor
     * 
     * @param placeName - The place name of the menu item
     * @param label - The text to display on the menu item
     * @param iOrder - The order in which to load the menu items
     * @param defaultSelected - whether this item should be defaultly selected
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public MenuItem(String placeName, String label, int iOrder, boolean defaultSelected) {
        this(placeName, label, iOrder, defaultSelected, null, null, null);
    }
    
    /**
     * The class constructor
     *  
     * @param placeName - The place name of the menu item
     * @param label - The text to display on the menu item
     * @param iOrder - The order in which to load the menu items
     * @param defaultSelected - whether this item should be defaultly selected
     * @param selectedImage - The image to show when the button is selected
     * @param unSelectedImage - The image to show when the button is not selected
     * @param subMenus - A list of sub menu items to use
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     */
    public MenuItem(String placeName, String label, int iOrder, boolean defaultSelected,
            String selectedImage, String unSelectedImage, List<MenuItem> subMenus) {
        this(placeName, label, iOrder, defaultSelected, selectedImage, unSelectedImage, subMenus, null, 0);
    }
    
    /**
     * The class constructor
     *  
     * @param placeName - The place name of the menu item
     * @param label - The text to display on the menu item
     * @param iOrder - The order in which to load the menu items
     * @param defaultSelected - whether this item should be defaultly selected
     * @param selectedImage - The image to show when the button is selected
     * @param unSelectedImage - The image to show when the button is not selected
     * @param subMenus - A list of sub menu items to use
     * @param referenceName - The reference name for the menu item
     * @param notificationCount - The notification count
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     */
    public MenuItem(String placeName, String label, int iOrder, boolean defaultSelected,
            String selectedImage, String unSelectedImage, List<MenuItem> subMenus,
            String referenceName, int notificationCount) {
        this.placeName = placeName;
        this.label = label;
        this.order = iOrder;
        this.defaultSelected = defaultSelected;
        this.selectedImage = selectedImage;
        this.unSelectedImage = unSelectedImage;
        this.subMenus = subMenus;
        this.referenceName = referenceName;
        this.notificationCount = notificationCount;
    }
    
    /**
     * Getter for the placename of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
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
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
    /**
     * Getter for label of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
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
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Getter for the order of the current menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     * 
     * @return The order of the current menu item
     */
    public int getOrder() {
        return this.order;
    }
    
    /**
     * Setter for the order that this button
     * 
     * @param iOrder - The order of this menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setOrder(int iOrder) {
        this.order = iOrder;
    }
    
    /**
     * Getter for the default selected state of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
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
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setDefaultSelected(boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }
    
    /**
     * Getter for the command action of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
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
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    /**
     * Getter for the selected image of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
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
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setSelectedImage(String selectedImage) {
        this.selectedImage = selectedImage;
    }
    
    /**
     * Getter for the unselected image of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
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
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setUnSelectedImage(String unSelectedImage) {
        this.unSelectedImage = unSelectedImage;
    }
    
    /**
     * Getter for the sub menu items of this menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
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
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setSubMenus(List<MenuItem> subMenus) {
        this.subMenus = subMenus;
    }
    
    /**
     * Getter for the reference name for the menu item
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     * 
     * @return The reference name for the menu item
     */
    public String getReferenceName() {
        return this.referenceName;
    }
    
    /**
     * Setter for the reference name for the menu item
     *  
     * @param referenceName - The reference name for the menu item
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     */
    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }
    
    /**
     * Getter for the notification count
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     * 
     * @return The notification count
     */
    public int getNotificationCount() {
        return this.notificationCount;
    }
    
    /**
     * Setter for the notification count
     * 
     * @param notificationCount - The notification count
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 18 July 2012
     */
    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }
    
    /**
     * Retrieves the name places that will also cause this left menu item to be selected
     * 
     * @returnthe name places that will also cause this left menu item to be selected
     */
    public List<String> getReferencedNamePlaces() {
        return referencedNamePlaces;
    }
    
    /**
     * Sets the name places that will also cause this left menu item to be selected
     * 
     * @param referencedNamePlaces the name places that will also cause this left menu item to be selected
     */
    public void setReferencedNamePlaces(List<String> referencedNamePlaces) {
        this.referencedNamePlaces = referencedNamePlaces;
    }
}
