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

import org.ssgwt.client.ui.event.FocusImageClickEvent;
import org.ssgwt.client.ui.event.IFocusImageClickEventHandler;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;

/**
 * Focus image composite. This uses a FocusPanel to allow for images that can
 * gain focus and have different states. The composite should be provided with
 * a specific size and source. When the state changes it will use a different
 * of the resource as a background.
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since  16 July 2012
 */
public class FocusImage extends Composite implements MouseDownHandler, MouseUpHandler, MouseOutHandler, MouseOverHandler, HasHandlers {

    /**
     * Constant indicating the up state.
     */
    public static final String UP = "upState"; 

    /**
     * Constant indicating the over state.
     */
    public static final String OVER = "overState";

    /**
     * Constant indicating the down state.
     */
    public static final String DOWN = "downState";

    /**
     * Constant indicating the selected state.
     */
    public static final String SELECTED = "selectedState";
    
    /**
     * Constant indicating the disabled state.
     */
    public static final String DISABLED = "disabledState";

    /**
     * Focus panel used for the component.
     */
    private FocusPanel focusPanel;

    /**
     * Explicit width of the component.
     */
    private int width;

    /**
     * Explicit height of the component
     */
    private int height;

    /**
     * The url that should be used for displaying the image.
     */
    private String source;

    /**
     * Whether the component should act as a toggle button
     */
    private boolean isToggleButton;

    /**
     * Whether the component is currently toggled.
     */
    private boolean isToggled = false;

    /**
     * Whether the component should gain focus on mouse down and up.
     */
    private boolean isFocusable = false;
    
    /**
     * Whether the component should gain focus image are disabled.
     */
    private boolean isDisabled = false;
    
    /**
     * Whether we are going to use stylesheet's for the button states
     */
    private boolean bUseStyleNames = false;
    
    /**
     * Array that stores the style sheet names for the button states
     */
    private String[] styleNames = null;
    
    /**
     * Whether we are going to use a single style name for the button states
     */
    private boolean bUseSingleStyleName = false;
    
    /**
     * Stores the style sheet name for the button states
     */
    private String styleName = null;
    
    /**
     * Constructor for the FocusImage
     * 
     * @param width The explicit width of the image in pixels.
     * @param height The explicit height of the image in pixels.
     * @param source Url for the image to be used as the background.
     * 
     * @return {@link FocusImage} Instance
     */
    public FocusImage(int width, int height, String source) {
        this(width, height, source, false);
    }
    
    /**
     * Constructor for the FocusImage
     * 
     * When using this constructor the style name that will be used by the focus image is:
     * {styleName}-upState
     * {styleName}-overState
     * {styleName}-downState
     * {styleName}-selectedState
     * {styleName}-disabledState
     * 
     * @return {@link FocusImage} Instance
     */
    @UiConstructor
    public FocusImage() {
        this.bUseSingleStyleName = true;
        focusPanel = new FocusPanel();
        initWidget(focusPanel);
        focusPanel.addMouseOverHandler(this);
        focusPanel.addMouseOutHandler(this);
        focusPanel.addMouseDownHandler(this);
        focusPanel.addMouseUpHandler(this);
    }
    
    /**
     * Constructor for the FocusImage
     * 
     * @param styleNames Array of style names for the different states.
     * 
     * @return {@link FocusImage} Instance
     */
    public FocusImage(String[] stylesheets) {
        this(false, stylesheets);
    }
    
    /**
     * Constructor for the FocusImage
     * 
     * @param toggleButton Whether the FocusImage is selectable.
     * @param styleNames Array of style names for the different states.
     * 
     * @return {@link FocusImage} Instance
     */
    public FocusImage(boolean toggleButton, String[] styleNames) {
        if (styleNames.length != 0 || styleNames != null) {
            this.bUseStyleNames = true;
            this.styleNames = styleNames;
        }
        this.isToggleButton = toggleButton;

        focusPanel = new FocusPanel();
        initWidget(focusPanel);
        focusPanel.addMouseOverHandler(this);
        focusPanel.addMouseOutHandler(this);
        focusPanel.addMouseDownHandler(this);
        focusPanel.addMouseUpHandler(this);
        
        setState(UP);
    }

    /**
     * Constructor for the FocusImage
     * 
     * @param width The explicit width of the image in pixels.
     * @param height The explicit height of the image in pixels.
     * @param source Url for the image to be used as the background.
     * @param toggleButton Whether the FocusImage is selectable.
     * 
     * @return {@link FocusImage} Instance
     */
    public FocusImage(int width, int height, String source, boolean toggleButton) {
        this.source = source;
        this.width = width;
        this.height = height;
        this.isToggleButton = toggleButton;

        focusPanel = new FocusPanel();
        initWidget(focusPanel);
        focusPanel.setSize(width + Unit.PX.getType(), height + Unit.PX.getType());
        focusPanel.addMouseOverHandler(this);
        focusPanel.addMouseOutHandler(this);
        focusPanel.addMouseDownHandler(this);
        focusPanel.addMouseUpHandler(this);
        
        setState(UP);
    }

    /**
     * Sets the state of the component. If an invalid state is passed in, nothing will be changed.
     * 
     * @param newState The new state of the component.
     */
    public void setState(String newState) {
        if (this.bUseSingleStyleName) {
            focusPanel.setStyleName(this.styleName + "-" + newState);
        } else if (this.bUseStyleNames) {
            //use style sheet to set styles
            if (newState == UP) {
                focusPanel.setStyleName(this.styleNames[0]);
            } else if (newState == OVER) {
                focusPanel.setStyleName(this.styleNames[1]);
            } else if (newState == DOWN) {
                focusPanel.setStyleName(this.styleNames[2]);
            } else if (newState == SELECTED) {
                focusPanel.setStyleName(this.styleNames[3]);
            } else if (newState == DISABLED) {
                focusPanel.setStyleName(this.styleNames[4]);
            }
        } else {
            if (newState == UP) {
                focusPanel.getElement().getStyle().setProperty("background", "url(" + source + ") no-repeat 0px 0px");
            } else if (newState == OVER) {
                focusPanel.getElement().getStyle().setProperty("background", "url(" + source + ") no-repeat 0px " + (-1*height) + "px");
            } else if (newState == DOWN) {
                focusPanel.getElement().getStyle().setProperty("background", "url(" + source + ") no-repeat 0px " + (height * -2) + "px");
            } else if (newState == SELECTED) {
                focusPanel.getElement().getStyle().setProperty("background", "url(" + source + ") no-repeat 0px " + (height * -3) + "px");
            } else if (newState == DISABLED) {
                focusPanel.getElement().getStyle().setProperty("background", "url(" + source + ") no-repeat 0px " + (height * -4) + "px");
            }
        }
    }

    /**
     * Whether the FocusImage is toggled or not.
     * 
     * @return Whether the FocusImage is toggled or not.
     */
    public boolean isToggled() {
        return isToggled;
    }
    
    /**
     * Whether the FocusImage is disabled or not.
     * 
     * @return Whether the FocusImage is disabled or not.
     */
    public boolean isDisabled() {
        return isDisabled;
    }

    /**
     * Sets whether the toggle button is toggled or not.
     * 
     * @param isToggled Whether the FocusImage is toggled or not.
     */
    public void setToggled(boolean isToggled) {
        this.isToggled = isToggled;
        
        if (isToggled) {
            setState(SELECTED);
        } else {
            setState(UP);
        }
    }

    /**
     * Mouse Over implementation. This will be called when the user 
     * moves the mouse over the focusPanel.
     * 
     * @param event The event dispatched.
     */
    public void onMouseOver(MouseOverEvent event) {
        if (!isDisabled) {
            setState(OVER);
        }
        
    }

    /**
     * Mouse out implementation. This will be called when the user moves the
     * mouse out of the focusPanel.
     * 
     * @param event The event dispatched.
     */
    public void onMouseOut(MouseOutEvent event) {
        if (!isDisabled) {
            if (isToggled) {
                setState(SELECTED);
            } else {
                setState(UP);
            }
        }
    }

    /**
     * Mouse up implementation. This will be called when the user releases the
     * mouse button over the focusPanel.
     * 
     * @param event The event dispatched.
     */
    public void onMouseUp(MouseUpEvent event) {
        if (!isDisabled) {
            if (isToggled) {
                setState(SELECTED);
            } else {
                setState(OVER);
            }
            if (!isFocusable) {
                focusPanel.setFocus(false);
            }
        }
    }
    
    /**
     * Mouse disable implementation. This will be called when the focus image 
     * gets disabled.
     * 
     * @param disableState State whether button is disabled or not
     */
    public void setDisableState(boolean disableState) {
        this.isDisabled = disableState;
        if (isDisabled) {
            setState(DISABLED);
        } else {
            setState(UP);
        }
    }

    /**
     * Mouse down implementation. This will be called when the user clicks on the
     * focusPanel but before the click event is dispatched.
     * 
     * @param event The event dispatched.
     */
    public void onMouseDown(MouseDownEvent event) {
        if (!isDisabled) { 
            setState(DOWN);
            if (isToggleButton) {
                isToggled = !isToggled;
            }            
            if (!isFocusable) {
                focusPanel.setFocus(false);
            }
            fireEvent(new FocusImageClickEvent(this));
        }
    }
    
    /**
     * Register Handler 
     * 
     * @param handler The Event handler interface
     * 
     * @return handler
     */
    public HandlerRegistration addFocusImageClickEventHandler(IFocusImageClickEventHandler handler) {
        return this.addHandler(handler, FocusImageClickEvent.TYPE);
    }

    /**
     * Adds a mouse over handler and returns the {@link HandlerRegistration}.
     * 
     * @param mouseOverHandler The {@link MouseOverHandler} that will handle mouse over events.
     * 
     * @return The {@link HandlerRegistration} associated with the added event handler.
     */
    public HandlerRegistration addMouseOverHandler(MouseOverHandler mouseOverHandler) {
        return focusPanel.addMouseOverHandler(mouseOverHandler);
    }

    /**
     * Adds a mouse out handler and returns the {@link HandlerRegistration}.
     * 
     * @param mouseOutHandler The {@link MouseOutHandler} that will handle mouse out events.
     * 
     * @return The {@link HandlerRegistration} associated with the added event handler.
     */
    public HandlerRegistration addMouseOutHandler(MouseOutHandler mouseOutHandler) {
        return focusPanel.addMouseOutHandler(mouseOutHandler);
    }
    
    /**
     * Adds a key up handler and returns the {@link HandlerRegistration}.
     * 
     * @param keyUpHandler The {@link KeyUpHandler} that will handle key up events.
     * 
     * @return The {@link HandlerRegistration} associated with the added event handler.
     */
    public HandlerRegistration addKeyUpHandler (KeyUpHandler keyUpHandler) {
        return focusPanel.addKeyUpHandler(keyUpHandler);
    }

    /**
     * Getter for the explicit width
     * 
     * @return the explicit width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Getter for the explicit height
     * 
     * @return the explicit height
     */
    public int getHeight() {
        return this.height;
    }
    
    /**
     * Retrieves the style name that will be used by the focus image
     * 
     * @return the style name of the focus image 
     */
    public String getStyleName() {
        return styleName;
    }
    
    /**
     * Sets the style name that will be used by the focus image
     * 
     * @param styleName - The new that should be used by the focus image
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
        setState(UP);
    }

}
