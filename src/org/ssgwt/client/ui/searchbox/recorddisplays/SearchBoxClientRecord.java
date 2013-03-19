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
package org.ssgwt.client.ui.searchbox.recorddisplays;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the class for the organisation records to be displayed in the search box results
 *
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  19 March 2013
 */
public abstract class SearchBoxClientRecord<T> extends SearchBoxRecordWidget<T> {

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * The default resource to use for the SearchBoxClientRecord class
     */
    private static SearchBoxClientRecordResources DEFAULT_RESOURCES;

    /**
     * The resource to use for the LeftMenuItem class
     */
    private SearchBoxClientRecordResources resources;

    /**
     * The value object to be used on the search box component
     */
    private T itemVO;

    /**
     * The main container of the search box organisation record
     */
    @UiField
    FlowPanel searchBoxClientRecord;

    /**
     * The label for the organisation name
     */
    Label organisationLabel;

    /**
     * The label for the site name
     */
    Label siteLabel;

    /**
     * The image for the organisation avatar
     */
    Image avatarImage;

    /**
     * UiBinder interface for the composite
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     */
    interface Binder extends UiBinder<Widget, SearchBoxClientRecord<?>> {
    }

    /**
     * A ClientBundle that provides style for this widget.
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     */
    public interface SearchBoxClientRecordResources extends ClientBundle {

        /**
         * The style source to be used in this widget
         */
        @Source("SearchBoxClientRecord.css")
        Style searchBoxClientRecordStyle();

        /**
         * Icon used as the default organisation avatar
         */
        @Source("images/Default_client_icon.png")
        @ImageOptions(flipRtl = true)
        ImageResource defaultUserAvatar();
    }

    /**
     * The Css style recourse items to use in this widget
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     */
    public interface Style extends CssResource {

        /**
         * The style for the panel that contains
         * the whole organisation record
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  19 March 2013
         *
         * @return The name of the compiled style
         */
        String searchBoxClientRecord();

        /**
         * The style for the name of the organisation
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  19 March 2013
         *
         * @return The name of the compiled style
         */
        String organisationLabel();

        /**
         * The style for the name of the site
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  19 March 2013
         *
         * @return The name of the compiled style
         */
        String siteLabel();

        /**
         * The style for the organisation avatar image
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  19 March 2013
         *
         * @return The name of the compiled style
         */
        String avatarImage();

        /**
         * The style for the organisation record when it is selected
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  19 March 2013
         *
         * @return The name of the compiled style
         */
        String searchBoxClientRecordSelected();

        /**
         * The style for the name when the record is selected
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  19 March 2013
         *
         * @return The name of the compiled style
         */
        String organisationLabelSelected();

        /**
         * The style for the avatar when the record is selected
         *
         * @author Michael Barnard <michael.barnard@a24group.com>
         * @since  19 March 2013
         *
         * @return The name of the compiled style
         */
        String avatarImageSelected();
    }

    /**
     * This function will check of there is already a default resource to
     * use for the organisation record and if not create a default resource
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     *
     * @return The default resource
     */
    private static SearchBoxClientRecordResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(SearchBoxClientRecordResources.class);
        }
        return DEFAULT_RESOURCES;
    }

    /**
     * Class constructor for organisation record
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2012
     */
    public SearchBoxClientRecord() {
        this(getDefaultResources());
    }

    /**
     * Class constructor for organisation record with custom recource
     *
     * @param resources The resource to be used for the SearchBoxUserRecord
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     */
    public SearchBoxClientRecord(SearchBoxClientRecordResources resources) {
        super();
        this.resources = resources;
        this.resources.searchBoxClientRecordStyle().ensureInjected();
        this.add(uiBinder.createAndBindUi(this));
        //create the labels and image for the user record
        organisationLabel = new Label();
        siteLabel = new Label();
        avatarImage = new Image(resources.defaultUserAvatar());

        //add styls to the user record elements
        searchBoxClientRecord.setStyleName(resources.searchBoxClientRecordStyle().searchBoxClientRecord());
        avatarImage.setStyleName(resources.searchBoxClientRecordStyle().avatarImage());
        organisationLabel.setStyleName(resources.searchBoxClientRecordStyle().organisationLabel());
        siteLabel.setStyleName(resources.searchBoxClientRecordStyle().siteLabel());

        searchBoxClientRecord.add(avatarImage);
        searchBoxClientRecord.add(organisationLabel);
        searchBoxClientRecord.add(siteLabel);
    }

    /**
     * Sets the VO for the organisation record and details to be displayed by the record
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     */
    @Override
    public void setItemVO(T itemVO) {
        this.itemVO = itemVO;
        organisationLabel.setText(getOrganisationName(itemVO));
        if (getSiteName(itemVO) != null && !getSiteName(itemVO).trim().equals("")) {
            siteLabel.setText("- " + getSiteName(itemVO));
        }
        if (getOrgAvatarUrl(itemVO) != null && !getOrgAvatarUrl(itemVO).trim().equals("")) {
            avatarImage.setUrl(getOrgAvatarUrl(itemVO));
        }
    }

    /**
     * Gets the VO of the organisation record
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     *
     * @return The user record's VO
     */
    @Override
    public T getItemVO() {
        return this.itemVO;
    }

    /**
     * Returns the text to be returned when the record is selected
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     *
     * @return The record text to be displayed
     */
    @Override
    public String getItemSelectionText() {
        String itemString = getOrganisationName(itemVO);
        String itemAppend = getSiteName(itemVO);
        if (itemAppend != null && !itemAppend.trim().equals("")) {
            itemString += " - " + itemAppend;
        }

        return itemString;
    }

    /**
     * This will set the organisation record to the selected state by updating style accordingly
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     */
    @Override
    public void setSelectedState(boolean selected) {
        if (selected) {
            searchBoxClientRecord.addStyleName(resources.searchBoxClientRecordStyle().searchBoxClientRecordSelected());
            siteLabel.addStyleName(resources.searchBoxClientRecordStyle().organisationLabelSelected());
            avatarImage.addStyleName(resources.searchBoxClientRecordStyle().avatarImageSelected());
        } else {
            searchBoxClientRecord.removeStyleName(resources.searchBoxClientRecordStyle().searchBoxClientRecordSelected());
            siteLabel.removeStyleName(resources.searchBoxClientRecordStyle().organisationLabelSelected());
            avatarImage.removeStyleName(resources.searchBoxClientRecordStyle().avatarImageSelected());
        }
    }

    /**
     * Gets the username of the organisation to be displayed
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     *
     * @return The name of the organisation to be displayed
     */
    public abstract String getOrganisationName(T itemVO);

    /**
     * Gets the name of the site to be displayed
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     *
     * @return The name of the site to be displayed
     */
    public abstract String getSiteName(T itemVO);

    /**
     * Gets the url of the organisation avatar image to be displayed
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  19 March 2013
     *
     * @return The organisation avatar image to be displayed
     */
    public abstract String getOrgAvatarUrl(T itemVO);

}