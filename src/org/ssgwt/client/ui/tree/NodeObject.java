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
package org.ssgwt.client.ui.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * The node object.
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  31 Jan 2013
 */
public class NodeObject {

	/**
	 * All the sub nodes of the node
	 */
	public List<NodeObject> arrSubNodes = new ArrayList<NodeObject>();
	
	/**
	 * Boolean indicating if the item is selected
	 */
	public boolean selected;
	
	/**
	 * Function used to check if all the items is selected
	 * 
	 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
	 * @since  31 Jan 2013
	 * 
	 * @return true in no children is selected
	 */
	public boolean isNoChildrenSelected() {
		for (NodeObject child : arrSubNodes) {
			if (child.selected || !child.isNoChildrenSelected()) {
				return false;
			}
		}
		return true;
	}
}
