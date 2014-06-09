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
package org.ssgwt.client.ui.component;

/**
 * Tip Bar Item Interface.
 * 
 * Represents a single tip bar item for the tip bar. All models that are to be used with the tip bar
 * components need to implement this interface.
 * 
 * author Dmitri De Klerk <dmitri.deklerk@a24group.com>
 * @since 6 June 2014
 */
public interface TipItemInterface {

    /**
     * Getter for the callback
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  9 June 2014
     * 
     * @return 
     */
    public TipBarCallback getCallBack();
    
    /**
     * Getter for label of the tip item
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  6 June 2014
     * 
     * @return The label of the tip item
     */
    public String getLabel();
    
    /**
     * Getter for the action label
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  6 June 2014
     * 
     * @return The action label
     */
    public String getActionLabel();
}