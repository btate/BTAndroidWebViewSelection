// Namespace
var android = {};
android.selection = {};
	
android.selection.selectionStartRange = null;
android.selection.selectionEndRange = null;


/** The last point touched by the user. { 'x': xPoint, 'y': yPoint } */
android.selection.lastTouchPoint = null;


/** 
 * Starts the touch and saves the given x and y coordinates as last touch point
 */
android.selection.startTouch = function(x, y){
	
	android.selection.lastTouchPoint = {'x': x, 'y': y};
	
};



/**
 *	Checks to see if there is a selection.
 *
 *	@return boolean
 */
android.selection.hasSelection = function(){
	return window.getSelection().toString().length > 0;
};


/**
 *	Clears the current selection.
 */
android.selection.clearSelection = function(){
	
	try{
		// if current selection clear it.
	   	var sel = window.getSelection();
	   	sel.removeAllRanges();
	}catch(err){
		window.TextSelection.jsError(err);
	}	
};


/**
 *	Handles the long touch action by selecting the last touched element.
 */
android.selection.longTouch = function() {

	try{
    
    	android.selection.clearSelection();
    	
	   	// if current selection clear it.
	   	var sel = window.getSelection();
	   	
	   	var oneWordCaret = document.caretRangeFromPoint(android.selection.lastTouchPoint.x, android.selection.lastTouchPoint.y);
	   	oneWordCaret.expand("word");
			
	   	
	   	sel.addRange(oneWordCaret);
	   	
	   	var temp = sel.getRangeAt(0);
	   	
	   	android.selection.saveSelectionStart();
	   	android.selection.saveSelectionEnd();
	   	
	   	
	   	// Show the context menu in app
	   	android.selection.selectionChanged();
	   	//android.selection.selectBetweenHandles();
	   	
	 }
	 catch(err){
	 	window.TextSelection.jsError(err);
	 }
   	
};

/**
 * Tells the app to show the context menu. 
 */
android.selection.selectionChanged = function(){

	try{
	
		var sel = window.getSelection();
		if(!sel){
			return;
		}
		
		var range = sel.getRangeAt(0);
		
		
		// Add spans to the selection to get page offsets
		var selectionEnd = $("<span id=\"selectionEnd\"/>");
	    var selectionStart = $("<span id=\"selectionStart\"/>");
	    
		
		var startRange = document.createRange();
    	startRange.setStart(range.startContainer, range.startOffset);
    	startRange.insertNode(selectionStart[0]);
		
		var endRange = document.createRange();
    	endRange.setStart(range.endContainer, range.endOffset);
    	endRange.insertNode(selectionEnd[0]);
	    
	   	
	   	// Create the bounds json object for the selection
	   	var handleBounds = "{'left': " + selectionStart.offset().left + ", ";
	   	handleBounds += "'top': " + selectionStart.offset().top + ", ";
	   	handleBounds += "'right': " + selectionEnd.offset().left + ", ";
	   	handleBounds += "'bottom': " + selectionEnd.offset().top + "}";
	   	
	   	
	   	// Pull the spans
	   	selectionStart.remove();
	   	selectionEnd.remove();
	   	
	   	// Reset range
	   	sel.removeAllRanges();
	   	sel.addRange(range);
	   	
	   	// Menu bounds
	   	var rect = range.getBoundingClientRect();
	   	
	   	var menuBounds = "{'left': " + rect.left + ", ";
	   	menuBounds += "'top': " + rect.top + ", ";
	   	menuBounds += "'right': " + rect.right + ", ";
	   	menuBounds += "'bottom': " + rect.bottom + "}";
	   	
	   	// Rangy
	   	var rangyRange = android.selection.getRange();
	   	
	   	// Text to send to the selection
	   	var text = window.getSelection().toString();
	   	
	   	// Set the content width
	   	window.TextSelection.setContentWidth(document.body.clientWidth);
	   	
	   	// Tell the interface that the selection changed
	   	window.TextSelection.selectionChanged(rangyRange, text, handleBounds, menuBounds);
	}
	catch(err){
		window.TextSelection.jsError(err);
	}
};



android.selection.getRange = function() {
    var serializedRangeSelected = rangy.serializeSelection();
    var serializerModule = rangy.modules.Serializer;
    if (serializedRangeSelected != '') {
        if (rangy.supported && serializerModule && serializerModule.supported) {
            var beginingCurly = serializedRangeSelected.indexOf("{");
            serializedRangeSelected = serializedRangeSelected.substring(0, beginingCurly);
            return serializedRangeSelected;
        }
    }
}



/** 
 * Returns the last touch point as a readable string.
 */
android.selection.lastTouchPointString = function(){
	if(android.selection.lastTouchPoint == null)
		return "undefined";
		
	return "{" + android.selection.lastTouchPoint.x + "," + android.selection.lastTouchPoint.y + "}";
};



android.selection.saveSelectionStart = function(){
	try{

		// Save the starting point of the selection
	   	var sel = window.getSelection();
		var range = sel.getRangeAt(0);
		
		var saveRange = document.createRange();
		
		saveRange.setStart(range.startContainer, range.startOffset);
		
		android.selection.selectionStartRange = saveRange;
	}catch(err){
		window.TextSelection.jsError(err);
	}

};

android.selection.saveSelectionEnd = function(){

	try{

		// Save the end point of the selection
	   	var sel = window.getSelection();
		var range = sel.getRangeAt(0);
		
		var saveRange = document.createRange();
		saveRange.setStart(range.endContainer, range.endOffset);
		
		android.selection.selectionEndRange = saveRange;
	}catch(err){
		window.TextSelection.jsError(err);
	}
	
};



/**
 * Sets the last caret position for the start handle.
 */
android.selection.setStartPos = function(x, y){
	
	try{
		android.selection.selectionStartRange = document.caretRangeFromPoint(x, y);
		
		android.selection.selectBetweenHandles();
	}catch(err){
		window.TextSelection.jsError(err);
	}

};

/**
 * Sets the last caret position for the end handle.
 */
android.selection.setEndPos = function(x, y){
	
	try{	
		android.selection.selectionEndRange = document.caretRangeFromPoint(x, y);
		
		android.selection.selectBetweenHandles();
	
	}catch(err){
		window.TextSelection.jsError(err);
	}

};

/**
 *	Selects all content between the two handles
 */
android.selection.selectBetweenHandles = function(){
	
	try{
		var startCaret = android.selection.selectionStartRange;
		var endCaret = android.selection.selectionEndRange;
		
		// If we have two carets, update the selection
		if (startCaret && endCaret) {
		
			// If end caret comes before start caret, need to flip
			if(startCaret.compareBoundaryPoints (Range.START_TO_END, endCaret) > 0){
				var temp = startCaret;
				startCaret = endCaret;
				endCaret = temp;
				
				android.selection.selectionStartRange = startCaret;
				android.selection.selectionEndRange = endCaret;
			}
			
			var range = document.createRange();
			range.setStart(startCaret.startContainer, startCaret.startOffset);
			range.setEnd(endCaret.startContainer, endCaret.startOffset);
			
			
			android.selection.clearSelection();
				
			var selection = window.getSelection();
			selection.addRange(range);
	
			
			
		}
		
		android.selection.selectionChanged();
   	}
   	catch(err){
   		window.TextSelection.jsError(err);
   	}
};



