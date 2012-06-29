/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.ssgwt.client.ui.datagrid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.Range;

/**
 * A pager for controlling a {@link HasRows} that only supports simple page
 * navigation.
 * 
 * @author Michael Barnard
 * @since 29 June 2012
 */
public class SSPager extends AbstractPager {

    /**
     * A ClientBundle that provides images for this widget.
     */
    public static interface Resources extends ClientBundle {

        /**
         * The image used to skip ahead multiple pages.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_up.png")
        ImageResource simplePagerFastForward();

        /**
         * The disabled "fast forward" image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_disabled.png")
        ImageResource simplePagerFastForwardDisabled();

        /**
         * The pressed "fast forward" image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_down.png")
        ImageResource simplePagerFastForwardDown();

        /**
         * The hover "fast forward" image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_over.png")
        ImageResource simplePagerFastForwardOver();

        /**
         * The image used to go to the first page.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_End_up.png")
        ImageResource simplePagerFirstPage();

        /**
         * The disabled first page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_End_disabled.png")
        ImageResource simplePagerFirstPageDisabled();

        /**
         * The pressed first page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_End_down.png")
        ImageResource simplePagerFirstPageDown();

        /**
         * The hover first page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_End_over.png")
        ImageResource simplePagerFirstPageOver();

        /**
         * The image used to go to the last page.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_up.png")
        ImageResource simplePagerLastPage();

        /**
         * The disabled last page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_disabled.png")
        ImageResource simplePagerLastPageDisabled();

        /**
         * The pressed last page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_down.png")
        ImageResource simplePagerLastPageDown();

        /**
         * The hover last page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_End_over.png")
        ImageResource simplePagerLastPageOver();

        /**
         * The image used to go to the next page.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_up.png")
        ImageResource simplePagerNextPage();

        /**
         * The disabled next page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_disabled.png")
        ImageResource simplePagerNextPageDisabled();

        /**
         * The pressed next page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_down.png")
        ImageResource simplePagerNextPageDown();

        /**
         * The hover next page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_right_over.png")
        ImageResource simplePagerNextPageOver();

        /**
         * The image used to go to the previous page.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_up.png")
        ImageResource simplePagerPreviousPage();

        /**
         * The disabled previous page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_disabled.png")
        ImageResource simplePagerPreviousPageDisabled();

        /**
         * The pressed previous page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_down.png")
        ImageResource simplePagerPreviousPageDown();

        /**
         * The hover previous page image.
         */
        @ImageOptions(flipRtl = true)
        @Source("images/pagination_left_over.png")
        ImageResource simplePagerPreviousPageOver();

        /**
         * The styles used in this widget.
         */
        @Source("SimplePager.css")
        Style simplePagerStyle();
    }

    /**
     * Styles used by this widget.
     */
    public static interface Style extends CssResource {

        /**
         * Applied to buttons.
         */
        String button();

        /**
         * Applied to disabled buttons.
         */
        String disabledButton();

        /**
         * Applied to the details text.
         */
        String pageDetails();

        /**
         * Applied to the details text.
         */
        String framedLayout();
    }

    /**
     * The location of the text relative to the paging buttons.
     */
    public static enum TextLocation {
        CENTER, LEFT, RIGHT;
    }

    /**
     * An {@link Image} that acts as a button.
     */
    private static class ImageButton extends Image {
        /**
         * the disabled stat of the button
         */
        private boolean disabled;
        
        /**
         * the image to use on the element if it is disabled
         */
        private final ImageResource resDisabled;
        
        /**
         * The image to use on the element if it is enabled
         */
        private final ImageResource resEnabled;
        
        /**
         * The style that is used when the element is disabled
         */
        private final String styleDisabled;
        
        /**
         * The image to use on the element if it is hovered over
         */
        private final ImageResource resOver;
        
        /**
         * The image that is used when the element is pressed
         */
        private final ImageResource resDown;

        /**
         * Class Constructor
         * 
         * @param resEnabled - The enabled image
         * @param resDiabled - The disabled image
         * @param disabledStyle - The disabled style
         * @param resOver - The hover image
         * @param resDown - The pressed image
         */
        public ImageButton(ImageResource resEnabled, ImageResource resDiabled,
                String disabledStyle, ImageResource resOver,
                ImageResource resDown) {
            super(resEnabled);
            this.resEnabled = resEnabled;
            this.resDisabled = resDiabled;
            this.styleDisabled = disabledStyle;
            this.resOver = resOver;
            this.resDown = resDown;
            this.addMouseOverHandler(new MouseOverHandler() {

                /**
                 * Event triggered when mouse moves over the element.
                 * 
                 * @param The Mouse event
                 */
                @Override
                public void onMouseOver(MouseOverEvent event) {
                    if (!disabled) {
                        setResource(ImageButton.this.resOver);
                    }
                }
            });

            this.addMouseOutHandler(new MouseOutHandler() {

                /**
                 * Event triggered when mouse moves off the element.
                 * 
                 * @param The Mouse event
                 */
                @Override
                public void onMouseOut(MouseOutEvent event) {
                    if (!disabled) {
                        setResource(ImageButton.this.resEnabled);
                    }

                }
            });

            this.addMouseDownHandler(new MouseDownHandler() {

                /**
                 * Event triggered when mouse is pressed while over the element.
                 * 
                 * @param The Mouse event
                 */
                @Override
                public void onMouseDown(MouseDownEvent event) {
                    if (!disabled) {
                        setResource(ImageButton.this.resDown);
                    }
                }
            });

        }

        /**
         * Enabled state of the element
         * 
         * @return whether or not the element is disabled
         */
        public boolean isDisabled() {
            return disabled;
        }

        /**
         * Gets called on a browser event
         * 
         * @param The browser event
         */
        @Override
        public void onBrowserEvent(Event event) {
            // Ignore events if disabled.
            if (disabled) {
                return;
            }

            super.onBrowserEvent(event);
        }

        /**
         * Sets the elements enabled state
         * @param isDisabled - the enabled state that needs to be set on the element
         */
        public void setDisabled(boolean isDisabled) {
            if (this.disabled == isDisabled) {
                return;
            }

            this.disabled = isDisabled;
            if (disabled) {
                setResource(resDisabled);
                getElement().getParentElement().addClassName(styleDisabled);
            } else {
                setResource(resEnabled);
                getElement().getParentElement().removeClassName(styleDisabled);
            }
        }
    }

    /**
     * Default fast forward rows
     */
    private static int DEFAULT_FAST_FORWARD_ROWS = 1000;
    
    /**
     * The default resources
     */
    private static Resources DEFAULT_RESOURCES;

    /**
     * Gets the default resources
     * 
     * @return the default resources
     */
    private static Resources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(Resources.class);
        }
        return DEFAULT_RESOURCES;
    }

    /**
     * Fast forward button
     */
    private final ImageButton fastForward;

    /**
     * The fast forward rows count
     */
    private final int fastForwardRows;

    /**
     * The image for the first page element
     */
    private final ImageButton firstPage;

    /**
     * We use an {@link HTML} so we can embed the loading image.
     */
    private final HTML label = new HTML();
    
    /**
     * The label to display the total count
     */
    private final HTML labelOf = new HTML();

    /**
     * The last page images element
     */
    private final ImageButton lastPage;
    
    /**
     * The next page image element
     */
    private final ImageButton nextPage;
    
    /**
     * The previous page image element
     */
    private final ImageButton prevPage;

    /**
     * The {@link Resources} used by this widget.
     */
    private final Resources resources;

    /**
     * The {@link Style} used by this widget.
     */
    private final Style style;

    /**
     * Construct a {@link SimplePager} with the default text location.
     */
    public SSPager() {
        this(TextLocation.CENTER);
    }

    /**
     * Construct a {@link SimplePager} with the specified text location.
     * 
     * @param location
     *            the location of the text relative to the buttons
     */
    @UiConstructor
    // Hack for Google I/O demo
    public SSPager(TextLocation location) {
        this(location, getDefaultResources(), true, DEFAULT_FAST_FORWARD_ROWS,
                false);
    }

    /**
     * Construct a {@link SimplePager} with the specified resources.
     * 
     * @param location
     *            the location of the text relative to the buttons
     * @param resources
     *            the {@link Resources} to use
     * @param showFastForwardButton
     *            if true, show a fast-forward button that advances by a larger
     *            increment than a single page
     * @param fastForwardRows
     *            the number of rows to jump when fast forwarding
     * @param showLastPageButton
     *            if true, show a button to go the the last page
     */
    public SSPager(TextLocation location, Resources resources,
            boolean showFastForwardButton, final int fastForwardRows,
            boolean showLastPageButton) {
        this.resources = resources;
        this.fastForwardRows = fastForwardRows;
        this.style = resources.simplePagerStyle();
        this.style.ensureInjected();

        // Create the buttons.
        String disabledStyle = style.disabledButton();
        firstPage = new ImageButton(resources.simplePagerFirstPage(),
                resources.simplePagerFirstPageDisabled(), disabledStyle,
                resources.simplePagerFirstPageOver(),
                resources.simplePagerFirstPageDown());
        firstPage.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                firstPage();
            }
        });

        nextPage = new ImageButton(resources.simplePagerNextPage(),
                resources.simplePagerNextPageDisabled(), disabledStyle,
                resources.simplePagerNextPageOver(),
                resources.simplePagerNextPageDown());
        nextPage.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                nextPage();
            }
        });
        prevPage = new ImageButton(resources.simplePagerPreviousPage(),
                resources.simplePagerPreviousPageDisabled(), disabledStyle,
                resources.simplePagerPreviousPageOver(),
                resources.simplePagerPreviousPageDown());
        prevPage.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                previousPage();
            }
        });
        if (showLastPageButton) {
            lastPage = new ImageButton(resources.simplePagerLastPage(),
                    resources.simplePagerLastPageDisabled(), disabledStyle,
                    resources.simplePagerLastPageOver(),
                    resources.simplePagerLastPageDown());
            lastPage.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    lastPage();
                }
            });
        } else {
            lastPage = null;
        }
        if (showFastForwardButton) {
            fastForward = new ImageButton(resources.simplePagerFastForward(),
                    resources.simplePagerFastForwardDisabled(), disabledStyle,
                    resources.simplePagerFastForwardOver(),
                    resources.simplePagerFastForwardDown());
            fastForward.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    setPage(getPage() + getFastForwardPages());
                }
            });
        } else {
            fastForward = null;
        }

        // Construct the widget.
        HorizontalPanel layout = new HorizontalPanel();
        layout.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        initWidget(layout);
        if (location == TextLocation.LEFT) {
            layout.add(label);
        }
        layout.add(firstPage);
        layout.add(prevPage);
        if (location == TextLocation.CENTER) {
            layout.add(label);
        }
        layout.add(nextPage);
        if (showFastForwardButton) {
            layout.add(fastForward);
        }
        if (showLastPageButton) {
            layout.add(lastPage);
        }
        if (location == TextLocation.RIGHT) {
            layout.add(label);

        }
        layout.add(labelOf);
        label.getElement().addClassName(style.framedLayout());
        // Add style names to the cells.
        firstPage.getElement().getParentElement().addClassName(style.button());
        prevPage.getElement().getParentElement().addClassName(style.button());
        label.getElement().getParentElement().addClassName(style.pageDetails());
        labelOf.getElement().getParentElement()
                .addClassName(style.pageDetails());
        nextPage.getElement().getParentElement().addClassName(style.button());
        if (showFastForwardButton) {
            fastForward.getElement().getParentElement()
                    .addClassName(style.button());
        }
        if (showLastPageButton) {
            lastPage.getElement().getParentElement()
                    .addClassName(style.button());
        }

        // Disable the buttons by default.
        setDisplay(null);
    }

    /**
     * Gets the first page
     */
    @Override
    public void firstPage() {
        super.firstPage();
    }

    /**
     * Gets the current page
     */
    @Override
    public int getPage() {
        return super.getPage();
    }

    /**
     * Gets the total count of pages
     */
    @Override
    public int getPageCount() {
        return super.getPageCount();
    }

    /**
     * Check to see if there is a next page
     */
    @Override
    public boolean hasNextPage() {
        return super.hasNextPage();
    }

    /**
     * Checks to see if there is x amount of next pages
     * 
     * @param pages The x amount of pages
     */
    @Override
    public boolean hasNextPages(int pages) {
        return super.hasNextPages(pages);
    }

    /**
     * Checks if a specific  page exists
     */
    @Override
    public boolean hasPage(int index) {
        return super.hasPage(index);
    }

    /**
     * Check if there is a previous page
     */
    @Override
    public boolean hasPreviousPage() {
        return super.hasPreviousPage();
    }

    /**
     * Check if there is x amount of previous pages
     * 
     * @param pages - The amount of pages to check for
     */
    @Override
    public boolean hasPreviousPages(int pages) {
        return super.hasPreviousPages(pages);
    }

    /**
     * Go to the last page
     */
    @Override
    public void lastPage() {
        super.lastPage();
    }

    /**
     * Goes to the start of the last page
     */
    @Override
    public void lastPageStart() {
        super.lastPageStart();
    }

    /**
     * Goes to the next page
     */
    @Override
    public void nextPage() {
        super.nextPage();
    }

    /**
     * Goes to the previous page
     */
    @Override
    public void previousPage() {
        super.previousPage();
    }

    /**
     * Set the display
     * 
     * @param display The display to be set
     */
    @Override
    public void setDisplay(HasRows display) {
        // Enable or disable all buttons.
        boolean disableButtons = (display == null);
        setFastForwardDisabled(disableButtons);
        setNextPageButtonsDisabled(disableButtons);
        setPrevPageButtonsDisabled(disableButtons);
        super.setDisplay(display);
    }

    /**
     * Sets the page to a specific index
     * 
     * @param index - the page index
     */
    @Override
    public void setPage(int index) {
        super.setPage(index);
    }

    /**
     * Sets the page size
     * 
     * @param pageSize - the page size
     */
    @Override
    public void setPageSize(int pageSize) {
        super.setPageSize(pageSize);
    }

    /**
     * Sets the page start
     * 
     * @param index - The page start
     */
    @Override
    public void setPageStart(int index) {
        super.setPageStart(index);
    }

    /**
     * Let the page know that the table is loading. Call this method to clear
     * all data from the table and hide the current range when new data is being
     * loaded into the table.
     */
    public void startLoading() {
        getDisplay().setRowCount(0, true);
        label.setHTML("");
        labelOf.setHTML("");
    }

    /**
     * Get the text to display in the pager that reflects the state of the
     * pager.
     * 
     * @return the text
     */
    protected String createText() {
        // Default text is 1 based.
        NumberFormat formatter = NumberFormat.getFormat("#,###");
        HasRows display = getDisplay();
        Range range = display.getVisibleRange();
        int pageStart = range.getStart() + 1;
        int pageSize = range.getLength();
        int dataSize = display.getRowCount();
        int endIndex = Math.min(dataSize, pageStart + pageSize - 1);
        endIndex = Math.max(pageStart, endIndex);
        return formatter.format(pageStart) + "-" + formatter.format(endIndex);
    }

    /**
     * Get the text to display in the pager that reflects the state of the
     * pager.
     * 
     * @return the text
     */
    protected String createTextOf() {
        // Default text is 1 based.
        NumberFormat formatter = NumberFormat.getFormat("#,###");
        HasRows display = getDisplay();
        Range range = display.getVisibleRange();
        int pageStart = range.getStart() + 1;
        int pageSize = range.getLength();
        int dataSize = display.getRowCount();
        int endIndex = Math.min(dataSize, pageStart + pageSize - 1);
        endIndex = Math.max(pageStart, endIndex);
        boolean exact = display.isRowCountExact();
        return (exact ? " of " : " of over ") + formatter.format(dataSize);
    }

    /**
     * This method is called if the range count or the row count changes
     */
    @Override
    protected void onRangeOrRowCountChanged() {
        HasRows display = getDisplay();
        label.setText(createText());
        labelOf.setText(createTextOf());

        // Update the prev and first buttons.
        setPrevPageButtonsDisabled(!hasPreviousPage());

        // Update the next and last buttons.
        if (isRangeLimited() || !display.isRowCountExact()) {
            setNextPageButtonsDisabled(!hasNextPage());
            setFastForwardDisabled(!hasNextPages(getFastForwardPages()));
        }
    }

    /**
     * Check if the next button is disabled. Visible for testing.
     */
    boolean isNextButtonDisabled() {
        return nextPage.isDisabled();
    }

    /**
     * Check if the previous button is disabled. Visible for testing.
     */
    boolean isPreviousButtonDisabled() {
        return prevPage.isDisabled();
    }

    /**
     * Get the number of pages to fast forward based on the current page size.
     * 
     * @return the number of pages to fast forward
     */
    private int getFastForwardPages() {
        int pageSize = getPageSize();
        return pageSize > 0 ? fastForwardRows / pageSize : 0;
    }

    /**
     * Enable or disable the fast forward button.
     * 
     * @param disabled
     *            true to disable, false to enable
     */
    private void setFastForwardDisabled(boolean disabled) {
        if (fastForward == null) {
            return;
        }
        if (disabled) {
            fastForward.setResource(resources.simplePagerFastForwardDisabled());
            fastForward.getElement().getParentElement()
                    .addClassName(style.disabledButton());
        } else {
            fastForward.setResource(resources.simplePagerFastForward());
            fastForward.getElement().getParentElement()
                    .removeClassName(style.disabledButton());
        }
    }

    /**
     * Enable or disable the next page buttons.
     * 
     * @param disabled
     *            true to disable, false to enable
     */
    private void setNextPageButtonsDisabled(boolean disabled) {
        nextPage.setDisabled(disabled);
        if (lastPage != null) {
            lastPage.setDisabled(disabled);
        }
    }

    /**
     * Enable or disable the previous page buttons.
     * 
     * @param disabled
     *            true to disable, false to enable
     */
    private void setPrevPageButtonsDisabled(boolean disabled) {
        firstPage.setDisabled(disabled);
        prevPage.setDisabled(disabled);
    }
}