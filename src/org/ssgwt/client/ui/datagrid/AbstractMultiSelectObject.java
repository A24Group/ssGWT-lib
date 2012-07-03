package org.ssgwt.client.ui.datagrid;

/**
 * The object type to be used when the multi select state wants to be used
 * 
 * @author Michael Barnard
 * @since 2 July 2012
 */
public abstract class AbstractMultiSelectObject {
    
    /**
     * Whether or not the object is selected
     */
    private boolean selected;
    
    /**
     * Sets whether the object is selected or not
     * 
     * @param selected - whether the object is selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    /**
     * Get whether the object is selected or not
     * 
     * @return whether the object is selected
     */
    public boolean isSelected() {
        return this.selected;
    }
}
