package com.brandontate.androidwebviewselection;

public interface TextSelectionJavascriptInterfaceListener {

	/**
	 * Informs the listener that there was a javascript error.
	 * @param range
	 */
	public abstract void tsjiJSError(String error);
	
	
	/**
	 * The user has started dragging the selection handles.
	 */
	public abstract void tsjiStartSelectionMode();
	
	/**
	 * The user has stopped dragging the selection handles.
	 */
	public abstract void tsjiEndSelectionMode();
	
	/**
	 * Tells the listener to show the context menu for the given range and selected text.
	 * The bounds parameter contains a json string representing the selection bounds in the form 
	 * { 'left': leftPoint, 'top': topPoint, 'right': rightPoint, 'bottom': bottomPoint }
	 * @param range
	 * @param text
	 * @param handleBounds
	 * @param menuBounds
	 */
	public abstract void tsjiSelectionChanged(String range, String text, String handleBounds, String menuBounds);
	
	/**
	 * Sends the content width to the listener.  
	 * Necessary because Android web views don't allow you to get the content width.
	 * @param contentWidth
	 */
	public abstract void tsjiSetContentWidth(float contentWidth);
}
