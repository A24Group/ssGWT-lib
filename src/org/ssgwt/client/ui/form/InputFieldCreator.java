package org.ssgwt.client.ui.form;

/**
 * Interface used to define a creator function for classes
 * 
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  26 November 2012
 */
public interface InputFieldCreator {
    
    /**
     * Create a class based on the class literal
     * 
     * @param classLiteral - The class literal
     * 
     * @return an instance of the class of the literal
     */
    public ComplexInput<?> createItem(Class<?> classLiteral);
    
}
