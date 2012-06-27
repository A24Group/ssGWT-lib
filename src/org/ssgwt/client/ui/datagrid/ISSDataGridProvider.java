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
package org.ssgwt.client.ui.datagrid;

/**
 * Interface that is needs to be implemented by any Object that will be used as data provider for a data grid
 * 
 * @author Johannes Gryffenberg
 * @since 27 June 2012
 */
public interface ISSDataGridProvider {
	
	/**
	 * Retrieves the value of a property using the property name
	 * 
	 * @param propertyName - The name of the property
	 * 
	 * @return The string value of the property
	 */
	public String getValueForKey( String propertyName );
	
}
