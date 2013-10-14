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
package org.ssgwt.client.ui.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is used to create a generic popup
 * which can be used to display a widget as its content
 * 
 * @author Ruan Naude <naudeuran777@gmail.com>
 * @since 15 July 2013
 *
 */
public class GenericPopup extends PopupPanel {
    
    /**
     * The default resource to use for the GenericPopup class
     */
    private static GenericPopupResource DEFAULT_RESOURCE;
    
    /**
     * The style property name for margin-left
     */
    private static String STYLE_PROPERTY_MARGIN_LEFT = "marginLeft";
    
    /**
     * The style property name for margin-top
     */
    private static String STYLE_PROPERTY_MARGIN_TOP = "marginTop";
    
    /**
     * The style property name for top
     */
    private static String STYLE_PROPERTY_TOP = "top";
    
    /**
     * The style property name for left
     */
    private static String STYLE_PROPERTY_LEFT = "left";
    
    /**
     * The application name for Microsoft Internet Explorer
     */
    private static String APPLICATION_NAME_IE = "Microsoft Internet Explorer";
    
    /**
     * The resource to use for the GenericPopup class
     */
    private GenericPopupResource resource;
    
    /**
     * Main flow panel of the popup
     */
    private FlowPanel mainFlowPanel = new FlowPanel();
    
    /**
     * Content panel of the popup
     */
    private FlowPanel popupContent = new FlowPanel();
    
    /**
     * Loader flow panel of the popup
     */
    private FlowPanel loaderFlowPanel = new FlowPanel();
    
    /**
     * The loader state image
     */
    Image loaderImage;
    
    /**
     * Inner "white" arrow
     * 
     * Inner will be shown in the outer to hide the gray line
     */
    private FlowPanel innerArrow = new FlowPanel();
    
    /**
     * Outer "gray" arrow
     */
    private FlowPanel outerArrow = new FlowPanel();
    
    /**
     * Save the left position of the popup
     */
    private int popupLeftPosition = 0;
    
    /**
     * Save the top position of the popup
     */
    private int popupTopPosition = 0;
    
    /**
     * The widget the popup is attached to
     */
    private Widget attachToWidget;

    /**
     * The popup content widget
     */
    private IGenericPopupContentWidget popupContentWidget;

    /**
     * The flag to indicate whether to use arrow on the popup
     */
    private boolean useArrow = true;

    /**
     * The x axis center position of the parent
     */
    private int parentCenterXPosition;

    /**
     * Wherher to close the popup on mouse out
     */
    private boolean closeOnMouseOut;

    /**
     * Whether the popup is in loading state
     */
    private boolean loadingState = true;
    
    /**
     * The timer for the mouse out event
     */
    Timer mouseOutTimer = null;

    /**
     * A ClientBundle that provides style for this widget.
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public interface GenericPopupResource extends ClientBundle {
        
        /**
         * The style source to be used in this widget
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         */
        @Source("GenericPopup.css")
        Style genericPopupStyle();
        
        /**
         * Image used for the popup loading state
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         */
        @Source("images/loaderImage.gif")
        @ImageOptions(flipRtl = true)
        ImageResource loaderImage();
    }
    
    /**
     * The Css style recourse items to use in this widget
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public interface Style extends CssResource {
        
        /**
         * The style for the popup main panel
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String hoverPopupContainer();
        
        /**
         * The style for the inner arrow panel
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String hoverPopupInnerArrow();
        
        /**
         * The style for the popup loader image
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String popupLoaderImage();
        
        /**
         * The style for the popup outer arrow panel
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String hoverPopupOuterArrow();
        
        /**
         * The style for a gray border at the top
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String borderColorGreyTop();
        
        /**
         * The style for a gray border at the bottom
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String borderColorGreyBottom();
        
        /**
         * The style for a white border at the top
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String borderColorWhiteTop();
        
        /**
         * The style for a white border at the bottom
         * 
         * @author Ruan Naude <naudeuran777@gmail.com>
         * @since 15 July 2013
         * 
         * @return The name of the compiled style
         */
        String borderColorWhiteBottom();
        
    }
    
    /**
     * This function will check if there is already a default resource to
     * use for the generic popup and if not one will be created
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     * 
     * @return The default resource for the LeftMenuItem
     */
    private static GenericPopupResource getDefaultResources() {
        if (DEFAULT_RESOURCE == null) {
            DEFAULT_RESOURCE = GWT.create(GenericPopupResource.class);
        }
        return DEFAULT_RESOURCE;
    }
    
    /**
     * Class constructor sets the widget to display in popup
     * 
     * @param popupContentWidget - The widget to display in the popup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public GenericPopup(IGenericPopupContentWidget popupContentWidget) {
        this(popupContentWidget, false, false, false, getDefaultResources());
    }
    
    /**
     * Class constructor sets the widget to display in popup
     * 
     * @param popupContentWidget - The widget to display in the popup
     * @param closeOnMouseOut - Whether to close the popup on mouse out
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public GenericPopup(IGenericPopupContentWidget popupContentWidget, boolean closeOnMouseOut) {
        this(popupContentWidget, false, closeOnMouseOut, false, getDefaultResources());
    }
    
    /**
     * Class constructor sets the widget to display in popup
     * 
     * @param popupContentWidget - The widget to display in the popup
     * @param closeOnMouseOut - Whether to close the popup on mouse out
     * @param useArrow - Whether to use a arrow on the popup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public GenericPopup(IGenericPopupContentWidget popupContentWidget, boolean closeOnMouseOut, boolean useArrow) {
        this(popupContentWidget, false, closeOnMouseOut, useArrow, getDefaultResources());
    }
    
    /**
     * Class constructor sets the widget to display in popup
     * 
     * @param popupContentWidget - The widget to display in the popup
     * @param closeOnMouseOut - Whether to close the popup on mouse out
     * @param resource The resource to be used for the GenericPopup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public GenericPopup(IGenericPopupContentWidget popupContentWidget, boolean closeOnMouseOut, GenericPopupResource resource) {
        this(popupContentWidget, false, closeOnMouseOut, false, getDefaultResources());
    }
    
    /**
     * Class constructor sets the widget to display in popup
     * 
     * @param popupContentWidget - The widget to display in the popup
     * @param closeOnMouseOut - Whether to close the popup on mouse out
     * @param lockBackground Whether the background should be locked so clicking is disabled
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  02 September 2013
     */
    public GenericPopup(IGenericPopupContentWidget popupContentWidget, boolean lockBackground, boolean closeOnMouseOut, boolean useArrow) {
        this(popupContentWidget, lockBackground, closeOnMouseOut, useArrow, getDefaultResources());
    }
    
    /**
     * Class constructor sets the widget to display in popup
     * 
     * @param popupContentWidget - The widget to display in the popup
     * @param lockBackground - Whether to lock the background
     * @param closeOnMouseOut - Whether to close the popup on mouse out
     * @param useArrow - Whether to use a arrow on the popup
     * @param resource The resource to be used for the GenericPopup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public GenericPopup(
        IGenericPopupContentWidget popupContentWidget, boolean lockBackground, final boolean closeOnMouseOut, boolean useArrow, GenericPopupResource resource
    ) {
        super(!lockBackground);
        setGlassEnabled(lockBackground);
        setGlassStyleName("generalPopupOpacity");
        
        this.resource = resource;
        this.closeOnMouseOut = closeOnMouseOut;
        this.useArrow = useArrow;
        this.popupContentWidget = popupContentWidget;
        popupContentWidget.setParent(this);
        this.resource.genericPopupStyle().ensureInjected();
        this.setWidget(mainFlowPanel);
        
        //add the loader and content panels
        loaderImage = new Image(resource.loaderImage());
        loaderFlowPanel.add(loaderImage);
        loaderFlowPanel.setVisible(false);
        popupContent.add(popupContentWidget.asWidget());
        mainFlowPanel.add(popupContent);
        mainFlowPanel.add(loaderFlowPanel);
        
        //set the styles on the popup
        innerArrow.addStyleName(resource.genericPopupStyle().hoverPopupInnerArrow());
        outerArrow.addStyleName(resource.genericPopupStyle().hoverPopupOuterArrow());
        this.setStyleName(resource.genericPopupStyle().hoverPopupContainer());
        loaderImage.addStyleName(resource.genericPopupStyle().popupLoaderImage());
        
        //This will listen to the browser resize event and update the popup location
        Window.addResizeHandler(new ResizeHandler() {
            
            /**
             * Will handle the logic on the resize of the browser
             * 
             * @author Ruan Naude <naudeuran777@gmail.com>
             * @since 15 July 2013
             */
            @Override
            public void onResize(ResizeEvent event) {
                if (isShowing()) {
                    calculatePopupPosition();
                }
            }
        });
        
        //add mouse out handler to the popup
        this.addDomHandler(new MouseOutHandler() {
            
            /**
             * Handles what happens when the mouse leaves the popup
             * 
             * @author Ruan Naude <naudeuran777@gmail.com>
             * @since 15 July 2013
             */
            @Override
            public void onMouseOut(MouseOutEvent event) {
                handelMouseOutEvent();
            }
        }, MouseOutEvent.getType());
        
        //add mouse over handler to the popup
        this.addDomHandler(new MouseOverHandler() {
            
            /**
             * Handles what happens when the mouse enters the popup
             * 
             * @author Ruan Naude <naudeuran777@gmail.com>
             * @since 15 July 2013
             */
            @Override
            public void onMouseOver(MouseOverEvent event) {
                //clear timer if there was one
                if (mouseOutTimer != null) {
                    mouseOutTimer.cancel();
                    mouseOutTimer = null;
                }
            }
        }, MouseOverEvent.getType());

    }
    
    /**
     * This will handle what happens when the mouse leaves the popup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    private void handelMouseOutEvent() {
        //clear timer if there was one
        if (mouseOutTimer != null) {
            mouseOutTimer.cancel();
            mouseOutTimer = null;
        }
        
        //add timer if closeOnMouseOut is true
        if (closeOnMouseOut) {
            mouseOutTimer = new Timer() {
                
                /**
                 * Handles what happens when the timer runs
                 * 
                 * @author Ruan Naude <naudeuran777@gmail.com>
                 * @since 15 July 2013
                 */
                @Override
                public void run() {
                    hide();
                }
            };
            mouseOutTimer.schedule(500);
        }
    }
    
    /**
     * Getter for the popup content widget
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     * 
     * @return The popup content widget
     */
    public IGenericPopupContentWidget getPopupContentWidget() {
        return this.popupContentWidget;
    }
    
    /**
     * Displays the popup in the center of the screen
     * This is due to not passing in a attachToWidget
     *
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public void displayPopup() {
        displayPopup(null, this.closeOnMouseOut, false);
    }
    
    /**
     * Displays the popup relative to the passed in attachToWidget
     * widget
     *
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     *
     * @param attachToWidget - The widget the popup is attached to
     */
    public void displayPopup(Widget attachToWidget) {
        displayPopup(attachToWidget, this.closeOnMouseOut, this.useArrow);
    }
    
    /**
     * Displays the popup relative to the passed in attachToWidget
     * widget
     *
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     *
     * @param attachToWidget - The widget the popup is attached to
     * @param closeOnMouseOut - Whether to close the popup on mouse out
     */
    public void displayPopup(Widget attachToWidget, boolean closeOnMouseOut) {
        displayPopup(attachToWidget, closeOnMouseOut, this.useArrow);
    }
    
    /**
     * Displays the popup relative to the passed in attachToWidget
     * widget if arrow is true otherwise centered
     *
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     * 
     * @param attachToWidget - The widget the popup is attached to
     * @param closeOnMouseOut - Whether to close the popup on mouse out
     * @param useArrow - Whether to use a arrow on the popup
     */
    public void displayPopup(Widget attachToWidget, boolean closeOnMouseOut, boolean useArrow) {
        this.useArrow = useArrow;
        this.attachToWidget = attachToWidget;
        this.closeOnMouseOut = closeOnMouseOut;
        
        Timer timer = new Timer() {
            
            /**
             * This method will be called when a timer fires 
             * allowing time for the resource to be loaded and
             * then calculate Popup Position
             * 
             * @author Ruan Naude <naudeuran777@gmail.com>
             * @since 15 July 2013
             */
            @Override
            public void run() {
                calculatePopupPosition();
            }
        };
        timer.schedule(100);
        
    }
    
    /**
     * This function will load the popup data and
     * determine the popup position
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public void calculatePopupPosition() {
        //displays the popup in the center of the screen
        this.center();

        //set the height of the loader panel according to the 
        //size of the content
        setLoaderStateSize();
        
        //calculate popup position if popup should display relative
        //to widget with an arrow
        if (useArrow) {
            mainFlowPanel.add(outerArrow);
            mainFlowPanel.add(innerArrow);
            setPopupPosition();
        }
    }
    
    /**
     * This function will set the loader state size and 
     * put the loader image in the center of the popup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    private void setLoaderStateSize() {
        //set the size of the loader panel based on the popup content
        if (loadingState) {
            popupContent.setVisible(true);
            loaderFlowPanel.setHeight(popupContent.getOffsetHeight() + Unit.PX.getType());
            loaderFlowPanel.setWidth(popupContent.getOffsetWidth() + Unit.PX.getType());
            loaderFlowPanel.setVisible(true);
            popupContent.setVisible(false);
        } else {
            loaderFlowPanel.setHeight(popupContent.getOffsetHeight() + Unit.PX.getType());
            loaderFlowPanel.setWidth(popupContent.getOffsetWidth() + Unit.PX.getType());
        }
        
        //if the loader panel is to small for the loader image then
        //make the loader panel the size of the loader image
        if (loaderFlowPanel.getOffsetWidth() < loaderImage.getOffsetWidth()) {
            loaderFlowPanel.setWidth(loaderImage.getOffsetWidth() + Unit.PX.getType());
        }
        if (loaderFlowPanel.getOffsetHeight() < loaderImage.getOffsetHeight()) {
            loaderFlowPanel.setHeight(loaderImage.getOffsetWidth() + Unit.PX.getType());
        }
        
        //put the loader image in the center of the loader panel
        int xPosition = (loaderFlowPanel.getOffsetWidth() / 2) - (loaderImage.getOffsetWidth() / 2);
        int yPosition = (loaderFlowPanel.getOffsetHeight() / 2) - (loaderImage.getOffsetHeight() / 2);
        
        loaderImage.getElement().getStyle().setProperty(STYLE_PROPERTY_MARGIN_LEFT, xPosition + Unit.PX.getType());
        loaderImage.getElement().getStyle().setProperty(STYLE_PROPERTY_MARGIN_TOP, yPosition + Unit.PX.getType());
    }
    
    /**
     * Calculate the position of the popup.
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    private void setPopupPosition() {
        int windowWidth = Window.getClientWidth() - 10;
        boolean topPointer = false;
        
        // Now we need to determine the x and y position of the pointer
        int xPointerPosition = this.getOffsetWidth() / 6;
        // -1 is used as it should be shown 1px above/below the border line of the popup
        int yPointerPosition = this.getOffsetHeight() - 1;
        parentCenterXPosition = this.attachToWidget.getAbsoluteLeft()
            + (this.attachToWidget.getOffsetWidth() / 2);
        
        // By default show the popup upwards if possible
        if (this.getOffsetHeight() + 10 <= this.attachToWidget.getAbsoluteTop()) {
            popupTopPosition = this.attachToWidget.getAbsoluteTop() - this.getOffsetHeight() - 10;
            topPointer = false;
        } else {
            popupTopPosition = this.attachToWidget.getAbsoluteTop() + this.attachToWidget.getOffsetHeight() + 10;
            yPointerPosition = -12;
            topPointer = true;
        }
        
        // maxX will be the reference point plus the width of the popup.
        // Used to determine if we are 'out' of the screen horizontally
        int maxX = this.attachToWidget.getAbsoluteLeft() + this.getOffsetWidth();
        
        // We cannot show the popup as it is out of the screen.
        if (maxX >= windowWidth) {
            // parentCenterXPosition is the middle x position of the parent image.
            // xPointerPosition is a 1/6 of the popup width. Multiply this by 5
            // so that we get 5/6.
            // Subtract 6 to align the center of the pointer with the center of the parent image
            popupLeftPosition = parentCenterXPosition - (xPointerPosition * 5) - 6;
            this.setPointerPosition(xPointerPosition * 5, yPointerPosition, topPointer);
        } else {
            popupLeftPosition = parentCenterXPosition - (xPointerPosition) - 6;
            this.setPointerPosition(xPointerPosition, yPointerPosition, topPointer);
        }
        
        this.setPopupPosition(
            popupLeftPosition,
            popupTopPosition
        );
    }
    
    /**
     * Set the pointer position (outer and inner) by setting the property on the element
     * 
     * @param xPosition The x position in pixels
     * @param yPosition The y position in pixels
     * @param topPointer Whether the pointer should be at the top or bottom of popup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    private void setPointerPosition(int xPosition, int yPosition, boolean topPointer) {
        //check if the popup exceeds the window boundaries and adjust accordingly
        if (popupLeftPosition < 1) {
            popupLeftPosition = 0;
            if (parentCenterXPosition <= 10) {
                xPosition = 10;
            } else if (xPosition > this.getOffsetWidth()) {
                xPosition = this.getOffsetWidth() - 10;
            } else {
                xPosition = parentCenterXPosition;
            }
        }
        
        if (topPointer) {
            outerArrow.removeStyleName(resource.genericPopupStyle().borderColorGreyBottom());
            outerArrow.addStyleName(resource.genericPopupStyle().borderColorGreyTop());
        } else {
            outerArrow.removeStyleName(resource.genericPopupStyle().borderColorGreyTop());
            outerArrow.addStyleName(resource.genericPopupStyle().borderColorGreyBottom());
        }
        
        // For IE we need an additional 1 px on y position
        if (Window.Navigator.getAppName().equals(APPLICATION_NAME_IE)) {
            if (yPosition < 0) {
                yPosition++;
            } else {
                yPosition--;
            }
        }
        
        outerArrow.getElement().getStyle().setProperty(STYLE_PROPERTY_TOP, yPosition + Unit.PX.getType());
        outerArrow.getElement().getStyle().setProperty(STYLE_PROPERTY_LEFT, xPosition + Unit.PX.getType());
        
        if (yPosition < 0) {
            yPosition++;
        } else {
            yPosition--;
        }
        
        if (topPointer) {
            innerArrow.removeStyleName(resource.genericPopupStyle().borderColorWhiteBottom());
            innerArrow.addStyleName(resource.genericPopupStyle().borderColorWhiteTop());
        } else {
            innerArrow.removeStyleName(resource.genericPopupStyle().borderColorWhiteTop());
            innerArrow.addStyleName(resource.genericPopupStyle().borderColorWhiteBottom());
        }
        
        innerArrow.getElement().getStyle().setProperty(STYLE_PROPERTY_TOP, yPosition + Unit.PX.getType());
        innerArrow.getElement().getStyle().setProperty(STYLE_PROPERTY_LEFT, xPosition + Unit.PX.getType());
    }
    
    /**
     * This function will set the popup into a loading
     * state to indicate that data is being loaded for popup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     * 
     * @param loading - Whether to set popup into loading state
     */
    public void setLoadingState(boolean loading) {
        // Set the popup in the center of the screen.
        // only if it does not attach onto a widget
        if (useArrow == false) {
            this.center();
        }
        
        this.loadingState = loading;
        // Calculate the size of the loader.
        setLoaderStateSize();
        loaderFlowPanel.setVisible(loading);
        popupContent.setVisible(!loading);
        
        // The popup size is determined by the content in it, so we need
        // to re-center the popup as it's size might have changed.
        // This will only be centered if the popup has NO arrow
        if (useArrow == false) {
            this.center();
        }
    }
    
    /** 
     * Hides the popup
     *
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    public void hidePopup() {
        this.hide();
    }
}
