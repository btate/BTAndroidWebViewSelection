package com.brandontate.androidwebviewselection;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.accessibility.AccessibilityEvent;

import java.util.Locale;


/**
 * BT Android WebView Selection
 *
 * @author Brandon Tate
 */
public class TTSService extends AccessibilityService implements OnInitListener {

    /** Logging tag. */
    private static final String TAG = "Accessibility";

    /** Text to speech. */
    public static TextToSpeech mtts;

    @Override
    public void onServiceConnected(){

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();

        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED | AccessibilityEvent.TYPE_VIEW_FOCUSED;

        info.packageNames = new String[] { "gov.cdc.topical.framework" };

        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;

        info.notificationTimeout = 100;

        this.setServiceInfo(info);


        mtts = new TextToSpeech(this,this);
        mtts.setLanguage(Locale.getDefault());

    }

    /* (non-Javadoc)
     * @see android.accessibilityservice.TTSService#onAccessibilityEvent(android.view.accessibility.AccessibilityEvent)
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // TODO Auto-generated method stub

        // Ignore the clicked events.
        if(event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED)
            return;


        // Using custom events throughout the app with the TYPE_VIEW_FOCUSED event to
        // Make sure I have control over the speech.
        if(mtts != null && event.getContentDescription() != null){
            mtts.speak((String) event.getContentDescription(), TextToSpeech.QUEUE_FLUSH,null);
        }



    }

    @Override
    public void onDestroy(){
        // Kill the text to speech service
        if(mtts!=null){
            mtts.stop();
            mtts = null;
        }
    }

    /* (non-Javadoc)
     * @see android.accessibilityservice.TTSService#onInterrupt()
     */
    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub

    }


}
