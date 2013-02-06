## Tree

The Tree is used to display a tree structure.  The tree has 2 states VIEW and EDIT. 

In the VIEW state only items that are selected will be displayed. If a node is not selected but 
has children that is selected, the node will be displayed in the view state. These nodes will 
have an icon to indicate it is not selected.

In the EDIT state all nodes will be displayed. Selected nodes will be collapsed. Nodes that are 
not selected and has no children that is selected will also be collapsed. Nodes that are not 
selected but have children that is selected will be expanded.

There is also functionality to set the node to read only to not allow a user to change the selected
state of the node.

### Example

Level 1 value object
```Java
public class Level1 extends NodeObject {
	
	public String level1Name;

	public boolean selected;
	
	public boolean readOnly;
	
	/**
	 * Retrieves the object's selected state
	 * 
	 * @return The object's selected state
	 */
	@Override
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Set the object's selected state
	 * 
	 * @param selected The new selected state
	 */
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Retrieves the text that should be displayed on the tree for the item
	 * 
	 * @return The text that should be displayed on the tree for the item
	 */
	@Override
	public String getNodeDisplayText() {
		return level1Name;
	}

	/**
	 * Retrieves the object's read only state
	 * 
	 * @return The object's read only state
	 */
	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the object's read only state
	 * 
	 * @param readOnly The object's read only state to set
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
}
```

Level 2 value object
```Java
public class Level2 extends NodeObject {
	
	public String level2Name;

	public boolean selected;
	
	public boolean readOnly;
	
	/**
	 * Retrieves the object's selected state
	 * 
	 * @return The object's selected state
	 */
	@Override
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Set the object's selected state
	 * 
	 * @param selected The new selected state
	 */
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Retrieves the text that should be displayed on the tree for the item
	 * 
	 * @return The text that should be displayed on the tree for the item
	 */
	@Override
	public String getNodeDisplayText() {
		return level2Name;
	}

	/**
	 * Retrieves the object's read only state
	 * 
	 * @return The object's read only state
	 */
	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the object's read only state
	 * 
	 * @param readOnly The object's read only state to set
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
}
```

Level 3 value object
```Java
public class Level3 extends NodeObject {
	
	public String level3Name;
	
	public boolean selected;
	
	public boolean readOnly;
	
	/**
	 * Retrieves the object's selected state
	 * 
	 * @return The object's selected state
	 */
	@Override
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Set the object's selected state
	 * 
	 * @param selected The new selected state
	 */
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Retrieves the text that should be displayed on the tree for the item
	 * 
	 * @return The text that should be displayed on the tree for the item
	 */
	@Override
	public String getNodeDisplayText() {
		return level3Name;
	}

	/**
	 * Retrieves the object's read only state
	 * 
	 * @return The object's read only state
	 */
	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the object's read only state
	 * 
	 * @param readOnly The object's read only state to set
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
}
```

Creating the tree
```Java
// The list holding the data
ArrayList<Level1> data = new ArrayList<Level1>();

// Generates test data for the tree for this example
int numLevel1 = (int)(Math.random() * 5) + 1;
for (int i1 = 0; i1 < numLevel1; i1++) {
	Level1 objLevel1 = new Level1();
	objLevel1.level1Name = "Level1 " + i1;
	objLevel1.selected = ((int)(Math.random() * 2) == 0) ? false : true;
	int numLevel2 = (int)(Math.random() * 7);
	for (int i2 = 0; i2 < numLevel2; i2++) {
		Level2 objLevel2 = new Level2();
		objLevel2.level2Name = "Level1 " + i1 + " Level2 " + i2;
		objLevel2.selected = (((int)(Math.random() * 2) == 1) || objLevel1.selected) ? true : false;
		int numLevel3 = (int)(Math.random() * 11);
		for (int i3 = 0; i3 < numLevel3; i3++) {
			Level3 objLevel3 = new Level3();
			objLevel3.level3Name = "Level1 " + i1 + " Level2 " + i2 + " Level3 " + i3;
			objLevel3.selected = (((int)(Math.random() * 2) == 1) || objLevel2.selected) ? true : false;
			objLevel3.isReadOnly = ((int)(Math.random() * 2) == 0) ? false : true;
			objLevel2.arrTreeChildren.add(objLevel3);
		}
		objLevel1.arrTreeChildren.add(objLevel2);
	}
	data.add(objLevel1);
}

// Construct the tree
Tree tree = new Tree();

// Set the data call the setData function with the second parameter as true put the tree in view state
tree.setData((List<NodeObject>)(List<?>)data, false);
```
