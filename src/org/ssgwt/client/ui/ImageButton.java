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
package org.ssgwt.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is a new Button component that allows a button to have an image on it
 * either to the left or the right of the Button's label.
 * 
 * @author Johannes Gryffenberg
 * @since 05 December 2012
 */
public class ImageButton extends Button {
    
    /**
     * The label text of the button
     */
    private String label;
    
    /**
     * Position constant for the image when is should display to the left of the text
     */
    public static final String POSITION_LEFT = "left";
    
    /**
     * Position constant for the image when is should display to the right of the text
     */
    public static final String POSITION_RIGHT = "right";
    
    /**
     * The current position of the button
     */
    private String imagePosition;
    
    /**
     * The element that displays the text
     */
    private Element textElement;
    
    /**
     * The element that displayed the image on the button
     */
    private Element imageElement;
    
    /**
     * Class constructor creates a button with the image on the left side
     * 
     * @param label - The label of the button
     * @param imageResource - The image resource of the image the will be displayed on the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public ImageButton(String label, ImageResource imageResource) {
        this(label, new Image(imageResource));
    }
    
    /**
     * Class constructor creates a button with the image on the left side
     * 
     * @param label - The label of the button
     * @param imageUrl - The URL of the image the will be displayed on the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public ImageButton(String label, String imageUrl) {
        this(label, new Image(imageUrl));
    }
    
    /**
     * Class constructor creates a button with the image on the left side
     * 
     * @param label - The label of the button
     * @param image - The image that should be displayed on the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public ImageButton(String label, Image image) {
        this(label, image, POSITION_LEFT);
    }
    
    /**
     * Class constructor creates a button with the side the image should display on specified
     * 
     * @param label - The label of the button
     * @param imageResource - The image resource of the image the will be displayed on the button
     * @param imagePosition - The position where the image should display
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public ImageButton(String label, ImageResource imageResource, String imagePosition) {
        this(label, new Image(imageResource), imagePosition);
    }
    
    /**
     * Class constructor creates a button with the side the image should display on specified
     * 
     * @param label - The label of the button
     * @param imageUrl - The URL of the image the will be displayed on the button
     * @param imagePosition - The position where the image should display
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public ImageButton(String label, String imageUrl, String imagePosition) {
        this(label, new Image(imageUrl), imagePosition);
    }
    
    /**
     * Class constructor creates a button with only a label
     * 
     * @param label - The label of the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public ImageButton(String label) {
        this(label, (Image)null);
    }
    
    /**
     * Class constructor creates a button with the side the image should display on specified
     * 
     * @param label - The label of the button
     * @param image - The image that should be displayed on the button
     * @param imagePosition - The position where the image should display
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public ImageButton(String label, Image image, String imagePosition) {
        createInnerDiv();
        this.imagePosition = imagePosition;
        setText(label);
        if (image != null) {
            setImage(image);
        }
    }
    
    /**
     * Creates and adds a div inside the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    private void createInnerDiv() {
        getElement().setAttribute("style", "padding: 0px;");
        Element innerElement = DOM.createElement("div");
        innerElement.setAttribute("style", "width: 100%; height: 100%; border-width: 1px; border-color: #FFF;");
        innerElement.setAttribute("class", "innerDiv");
        DOM.appendChild(getElement(), innerElement);
    }
    
    /**
     * Retrieves the div inside the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     * 
     * @return the div inside the button
     */
    public Element getInnerElement() {
        return DOM.getFirstChild(getElement());
    }
    
    /**
     * Adds a image to the button
     * 
     * @param image- The image the should be placed on the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    public void setImage(Image image) {
        if (imageElement != null) {
            DOM.removeChild(getInnerElement(), imageElement);
        }
        String definedStyles = image.getElement().getAttribute("style");
        imageElement = image.getElement();
        imageElement.setAttribute("style", definedStyles + "; vertical-align:middle;");
        
        if (POSITION_LEFT.equals(imagePosition)) {
            DOM.insertBefore(getInnerElement(), image.getElement(), textElement);
        } else if (POSITION_RIGHT.equals(imagePosition)) {
            DOM.appendChild(getInnerElement(), image.getElement());
        }
    }
    
    /**
     * Changes the text on the button
     * 
     * @param label - The label that should be displayed on the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     */
    @Override
    public void setText(String label) {
        if (textElement != null) {
            DOM.removeChild(getInnerElement(), textElement);
        }
        this.label = label;
        textElement = DOM.createElement("span");
        textElement.setInnerText(label);
        textElement.setAttribute("style", "padding-left:3px; padding-right:3px; vertical-align:middle;");
        
        if (DOM.getChildCount(getInnerElement()) == 0) {
            DOM.appendChild(getInnerElement(), textElement);
        } else {
            if (POSITION_RIGHT.equals(imagePosition)) {
                DOM.insertBefore(getInnerElement(), textElement, imageElement);
            } else if (POSITION_LEFT.equals(imagePosition)) {
                DOM.appendChild(getInnerElement(), textElement);
            }
        }
    }
    
    /**
     * Retrieve the label that is currently being displayed on the button
     * 
     * @author Johannes Gryffenberg
     * @since 05 December 2012
     * 
     * @return the label that is currently being displayed on the button
     */
    @Override
    public String getText() {
        return label;
    }
}