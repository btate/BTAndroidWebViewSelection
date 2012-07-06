package com.brandontate.androidwebviewselection;

import android.content.Context;
import android.util.Log;

/**
 * This javascript interface allows the page to communicate that text has been selected by the user.
 * 
 * @author btate
 *
 */
public class TextSelectionJavascriptInterface {

	/** The TAG for logging. */
	private static final String TAG = "TextSelectionJavascriptInterface";
	
	/** The javascript interface name for adding to web view. */
	private final String interfaceName = "TextSelection";
	
	/** The webview to work with. */
	private TextSelectionJavascriptInterfaceListener listener;
	
	/** The context. */
	Context mContext;
	
	
	/**
	 * Constructor accepting context.
	 * @param c
	 */
	public TextSelectionJavascriptInterface(Context c){
		this.mContext = c;
	}
	
	/**
	 * Constructor accepting context and listener.
	 * @param c
	 * @param listener
	 */
	public TextSelectionJavascriptInterface(Context c, TextSelectionJavascriptInterfaceListener listener){
		this.mContext = c;
		this.listener = listener;
	}
	
	/**
	 * Handles javascript errors.
	 * @param error
	 */
	public void jsError(String error){
		if(this.listener != null){
			this.listener.tsjiJSError(error);
		}
	}
	
	/**
	 * Gets the interface name
	 * @return
	 */
	public String getInterfaceName(){
		return this.interfaceName;
	}
	
	/**
	 * Put the app in "selection mode".
	 */
	public void startSelectionMode(){
		
		if(this.listener != null)
			this.listener.tsjiStartSelectionMode();
		
	}
	
	/**
	 * Take the app out of "selection mode".
	 */
	public void endSelectionMode(){
		
		if(this.listener != null)
			this.listener.tsjiEndSelectionMode();
		
	}
	
	/**
	 * Show the context menu
	 * @param range
	 * @param text
	 * @param bounds
	 * @param showHighlight
	 * @param showUnHighlight
	 */
	public void selectionChanged(String range, String text, String handleBounds, String menuBounds){
		if(this.listener != null)
			this.listener.tsjiSelectionChanged(range, text, handleBounds, menuBounds);
		
	}
	
	public void setContentWidth(float contentWidth){
		if(this.listener != null)
			this.listener.tsjiSetContentWidth(contentWidth);
	}
	
}
