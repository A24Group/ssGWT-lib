package org.ssgwt.client.ui;

import org.ssgwt.client.ui.event.LoaderButtonClickEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * New image button that used generic components and doesn't use elements
 * It auto disables once clicked but has a constructor that allows this to be disabled
 * It also has a function to enable the button again
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
 * @since 14 December 2012
 */
public class LoaderImageButton extends FocusPanel implements ClickHandler {
    
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
     * Flag to indicate if the button should disable automatically when it is clicked
     */
    private boolean autoDisable = true;
    
    /**
     * Flag to indicate if the button is currently active
     */
    private boolean active = true;
    
    /**
     * The inner container of the button that will hold the active or disabled state containers
     */
    private FlowPanel innerContainer = new FlowPanel();
    
    /**
     * The active state container
     */
    private FlowPanel activeStateContainer = new FlowPanel();
    
    /**
     * The disabled state container
     */
    private FlowPanel disabledStateContainer = new FlowPanel();
    
    /**
     * The image that should be displayed with the text on the button
     */
    private Image image = null;
    
    /**
     * The active state label
     */
    private Label buttonTextLabel = new Label();
    
    /**
     * The loading state button image
     */
    private Image loadingImage = null;
    
    /**
     * The click handler attached to the button
     */
    private HandlerRegistration onClickHandler;
    
    /**
     * The loading text that should be displayed on the button
     */
    private String loadingText;
    
    /**
     * The label that displays the loading text
     */
    private Label loadingTextLabel = new Label();
    
    /**
     * The style to apply to the button's label
     */
    private String loadingTextStyle;

    /**
     * The style that will be added to the button when it is disabled
     */
    private String disabledStyle;
    
    /**
     * Class constructor creates a button with the image on the left side
     * 
     * @param label - The label of the button
     * @param imageResource - The image resource of the image the will be displayed on the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, ImageResource imageResource) {
        this(label, new Image(imageResource));
    }
    
    /**
     * Class constructor creates a button with the image on the left side
     * 
     * @param label - The label of the button
     * @param imageUrl - The URL of the image the will be displayed on the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, String imageUrl) {
        this(label, new Image(imageUrl));
    }
    
    /**
     * Class constructor creates a button with the image on the left side
     * 
     * @param label - The label of the button
     * @param image - The image that should be displayed on the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, Image image) {
        this(label, image, true);
    }
    
    /**
     * Class constructor creates a button with the image on the left side
     * 
     * @param label - The label of the button
     * @param image - The image that should be displayed on the button
     * @param autoDisable - Flag to indicate if the button should auto disabled when clicked
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, Image image, boolean autoDisable) {
        this(label, image, POSITION_LEFT, autoDisable);
    }
    
    /**
     * Class constructor creates a button with the side the image should display on specified
     * 
     * @param label - The label of the button
     * @param imageResource - The image resource of the image the will be displayed on the button
     * @param imagePosition - The position where the image should display
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, ImageResource imageResource, String imagePosition) {
        this(label, new Image(imageResource), imagePosition);
    }
    
    /**
     * Class constructor creates a button with the side the image should display on specified
     * 
     * @param label - The label of the button
     * @param imageUrl - The URL of the image the will be displayed on the button
     * @param imagePosition - The position where the image should display
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, String imageUrl, String imagePosition) {
        this(label, new Image(imageUrl), imagePosition);
    }
    
    /**
     * Class constructor creates a button with only a label
     * 
     * @param label - The label of the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label) {
        this(label, (Image)null);
    }
    
    /**
     * Class constructor creates a button with only a label
     * 
     * @param label - The label of the button
     * @param autoDisable - Flag to indicate if the button should auto disabled when clicked
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, boolean autoDisable) {
        this(label, (Image)null);
    }
    
    /**
     * Class constructor creates a button with the side the image should display on specified
     * 
     * @param label - The label of the button
     * @param image - The image that should be displayed on the button
     * @param imagePosition - The position where the image should display
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, Image image, String imagePosition) {
        this(label, image, imagePosition, true);
    }
    
    /**
     * Class constructor creates a button with the side the image should display on specified
     * 
     * @param label - The label of the button
     * @param image - The image that should be displayed on the button
     * @param imagePosition - The position where the image should display
     * @param autoDisable - Flag to indicate if the button should auto disabled when clicked
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public LoaderImageButton(String label, Image image, String imagePosition, boolean autoDisable) {
        this.imagePosition = imagePosition;
        this.autoDisable = autoDisable;
        this.getElement().getStyle().setProperty("display", "inline-block");
        this.getElement().getStyle().setProperty("textAlign", "center");
        this.add(innerContainer);
        innerContainer.getElement().getStyle().setProperty("cursor", "pointer");
        innerContainer.getElement().getStyle().setProperty("display", "table");
        innerContainer.setSize("100%", "100%");
        activeStateContainer.getElement().getStyle().setProperty("display", "table-cell");
        activeStateContainer.getElement().getStyle().setProperty("verticalAlign", "middle");
        activeStateContainer.setSize("100%", "100%");
        disabledStateContainer.getElement().getStyle().setProperty("display", "table-cell");
        disabledStateContainer.getElement().getStyle().setProperty("verticalAlign", "middle");
        disabledStateContainer.setSize("100%", "100%");
        buttonTextLabel.getElement().getStyle().setProperty("display", "inline");
        buttonTextLabel.getElement().getStyle().setProperty("paddingLeft", "3px");
        buttonTextLabel.getElement().getStyle().setProperty("paddingRight", "3px");
        loadingTextLabel.getElement().getStyle().setProperty("fontSize", "11px");
        loadingTextLabel.getElement().getStyle().setProperty("color", "#333333");
        loadingTextLabel.getElement().getStyle().setProperty("fontWeight", "normal");
        if (label != null) {
            setText(label);
        }
        if (image != null) {
            setImage(image);
        }
        updateButtonState();
        if (autoDisable) {
            onClickHandler = this.addClickHandler(this);
        }
    }
    
    /**
     * Updates that buttons display state to match the state of the active property
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    private void updateButtonState() {
        if (activeStateContainer.getParent() != null && activeStateContainer.getParent().equals(innerContainer)) {
            this.setSize(this.getOffsetWidth() + "px", this.getOffsetHeight() + "px");
        }
        innerContainer.clear();
        if (active) {
            if (this.disabledStyle != null) {
                this.removeStyleName(this.disabledStyle);
            }
            innerContainer.add(activeStateContainer);
        } else {
            if (this.disabledStyle != null) {
                this.addStyleName(this.disabledStyle);
            }
            addDisabledStateImageAndLabel();
            innerContainer.add(disabledStateContainer);
        }
    }
    
    /**
     * Adds the image and the label of the active state to the active state container
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    private void addActiveStateImageAndLabel() {
        activeStateContainer.clear();
        if (label != null) {
            buttonTextLabel.setText(label);
            activeStateContainer.add(buttonTextLabel);
        }
        if (image != null) {
            if (imagePosition.equals(POSITION_LEFT)) {
                activeStateContainer.insert(image, 0);
            } else if (imagePosition.equals(POSITION_RIGHT)) {
                activeStateContainer.add(image);
            }
        }
    }
    
    /**
     * Adds the image and the label of the disabled state to the disabled state container
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com> <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    private void addDisabledStateImageAndLabel() {
        disabledStateContainer.clear();
        if (loadingText != null) {
            loadingTextLabel.setText(loadingText);
            if (loadingTextStyle != null && loadingTextStyle.length() > 0) {
                loadingTextLabel.addStyleName(loadingTextStyle);
            }
            disabledStateContainer.add(loadingTextLabel);
        }
        if (loadingImage != null) {
            disabledStateContainer.insert(loadingImage, 0);
        }
    }
    
    /**
     * Adds a image to the button
     * 
     * @param image- The image the should be placed on the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public void setImage(Image image) {
        this.image = image;
        this.image.getElement().getStyle().setProperty("display", "inline");
        addActiveStateImageAndLabel();
    }
    
    /**
     * Changes the text on the button
     * 
     * @param label - The label that should be displayed on the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public void setText(String label) {
        this.label = label;
        addActiveStateImageAndLabel();
    }
    
    /**
     * Retrieve the label that is currently being displayed on the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     * 
     * @return the label that is currently being displayed on the button
     */
    public String getText() {
        return label;
    }
    
    /**
     * Handles the click events on the button to disabled the button when clicked
     * 
     * @param event - The click event
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    @Override
    public void onClick(ClickEvent event) {
        if (active) {
            active = !active;
            updateButtonState();
            if(!active) {
                LoaderButtonClickEvent.fire(this, event);
            }
        }
    }
    
    /**
     * Sets the loading image that will be used
     * 
     * @param loadingImage - The image the will be used in the disabled state
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public void setLoadingImage(Image loadingImage) {
        this.loadingImage = loadingImage;
    }
    
    /**
     * Sets the text that will be used in the disabled state of the button
     * 
     * @param loadingText - the text that will be used in the disabled state of the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }
    
    /**
     * Sets the style of the loading text
     * 
     * @param loadingTextStyle - the style that will used on the loading text
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 18 December 2012
     */
    public void setLoadingTextStyle(String loadingTextStyle) {
        this.loadingTextStyle = loadingTextStyle;
    }
    
    /**
     * Sets the style that will be used when the button is disabled
     * 
     * @param disabledStyle - the style that will be used when the button is disabled
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public void setDisabledStyle(String disabledStyle) {
        this.disabledStyle = disabledStyle;
    }
    
    /**
     * Change the button to enabled or disabled
     * 
     * @param enabled - Flag to indicate if the button should be disabled
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     */
    public void setEnabled(boolean enabled) {
        this.active = enabled;
        updateButtonState();
    }
    
    /**
     * Adds an event handler of the LoaderButtonClickEvent event to the button
     * 
     * @param handler - the event handler to handle the event
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@a24group.com>
     * @since 14 December 2012
     * 
     * @return - The event registration object
     */
    public HandlerRegistration addLoaderButtonClickHandler(LoaderButtonClickEvent.LoaderButtonClickHandler handler) {
        return this.addHandler(handler, LoaderButtonClickEvent.TYPE);
    }
}
