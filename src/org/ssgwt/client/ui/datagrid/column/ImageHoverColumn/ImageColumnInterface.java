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

/**
 * This interface is used for for the a image column popup
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since 31 May 2013
 *
 * @param <T> - The type of object used in the interface
 */
public interface ImageColumnInterface<T> {

    /**
     * The function that will be used to set the data on the popup
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since 31 May 2013
     *
     * @param data - The data to set in the popup
     */
    public void setData(T data);

}
