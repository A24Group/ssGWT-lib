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
 * The SSTextColumn to be able to get the column name from a event
 * 
 * @author Alec Erasmus
 * @since 14 Aug 2012
 */
public abstract class SSTextColumn<T> extends Column<T, String> implements SortableColumnWithName {
    
    /**
     * Construct a new TextColumn.
     */
    public SSTextColumn() {
      super(new SSTextCell());
    }
    
}
