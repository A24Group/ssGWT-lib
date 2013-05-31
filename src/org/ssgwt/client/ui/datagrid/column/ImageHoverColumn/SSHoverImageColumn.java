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
package org.ssgwt.client.ui.datagrid.column.ImageHoverColumn;

import com.google.gwt.user.cellview.client.Column;

/**
 * The SSHoverImageColumn is a column that display an image also on hover of the cell display more information
 * in the from of a popup widget passed in by the constructor.
 *
 * @author Alec Erasmus
 * @since 31 May 2013
 *
 * @param T The type of the data represented by this column
 */
public abstract class SSHoverImageColumn<T> extends Column<T, T> {

    /**
     * Construct a new SSHoverImageColumn.
     *
     * @author Alec Erasmus
     * @since 31 May 2013
     *
     * @param imageUrl - The image to display in the cell
     */
    public SSHoverImageColumn(String imageUrl, AbstractImageColumnPopup<T> popup) {
      super(new SSHoverImageCell<T>(imageUrl, popup));
    }
}
