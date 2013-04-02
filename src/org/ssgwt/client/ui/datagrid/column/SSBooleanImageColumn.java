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
package org.ssgwt.client.ui.datagrid.column;

import com.google.gwt.user.cellview.client.Column;

/**
 * The SSBooleanImageColumn is a column that display an image based on the boolean value.
 *
 * @author Alec Erasmus
 * @since 02 April 2013
 */
public abstract class SSBooleanImageColumn<T> extends Column<T, Boolean> implements SortableColumnWithName {

    /**
     * Construct a new BooleanImageColumn.
     *
     * @author Alec Erasmus
     * @since 02 April 2013
     *
     * @param tImageUrl - The image to display if the the boolean value is true
     * @param fImageUrl - The image to display if the the boolean value is false
     */
    public SSBooleanImageColumn(String tImageUrl, String fImageUrl) {
      super(new SSBooleanImageCell(tImageUrl, fImageUrl));
    }
}
