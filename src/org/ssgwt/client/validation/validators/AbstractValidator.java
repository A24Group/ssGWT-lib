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

package org.ssgwt.client.validation.validators;

import java.util.HashMap;

/**
 * Abstract validator allowing setting of configuration for the isValid()
 * method.
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 12 June 2012
 */
public class AbstractValidator {

    /**
     * Validation configuration settings. Will be populated if a validator has
     * some custom configuration or settings that needs to be taken into account
     * by the isValid method.
     */
    HashMap<String, ?> configuration;

    /**
     * Sets the validators configuration if required.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 12 June 2012
     * 
     * @param config The configuration.
     */
    public void setConfiguration(HashMap<String, ?> config) {
        this.configuration = config;
    }
}