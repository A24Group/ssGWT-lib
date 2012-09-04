package org.ssgwt.client.ui.menu;

import com.google.gwt.user.client.Command;

/**
 * The command that should be used for the items of the left menu
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since 04 Sep 2012
 */
public interface LeftMenuCommand extends Command {
    
    /**
     * Function to set whether the command should also execute the command of the top menu when executed
     * 
     * @param shouldExecute - Whether the object should be executed
     */
    public void setExecuteTopMenuCommand(boolean shouldExecute);
    
}
