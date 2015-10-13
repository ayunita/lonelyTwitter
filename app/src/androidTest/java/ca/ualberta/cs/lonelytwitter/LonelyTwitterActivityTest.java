package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;
    private Tweet tweet;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    // UI test
    public void testEditATweet() {
        // starts lonelyTwitter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        // reset the app to known state
        activity.getTweets().clear();

        // user clicks on tweet they want to edit
        bodyText = activity.getBodyText();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

        saveButton = activity.getSaveButton();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

        // view is updated
        final ListView oldTweetsList = activity.getOldTweetsList();
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());

        // AcitivityMonitor called before activity started
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

        // Following was stolen from https://developer.android.com/training/activity-testing/activity-functional-testing.html

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000); // wait activity to startup in ms
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // assert that the tweet being shown on the edit screen is the tweet
        final EditText editTweet = receiverActivity.getEditTweet();
        final Button saveEditButton = receiverActivity.getSaveEditButton();
        assertEquals(tweet.getText(), "hamburgers");

        receiverActivity.runOnUiThread(new Runnable() {
            public void run() {
                // we clicked on
                editTweet.performClick();
                // edit the text of that tweet
                editTweet.setText("salad");
                saveEditButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished
        // save our edits
        tweet.setText(editTweet.getText().toString());

        // assert that out edits were  saved into the tweet correctly
        assertEquals(tweet.getText(), "salad");

        final ListView newTweetsList = activity.getOldTweetsList();
        Tweet editedTweet = (Tweet)newTweetsList.getItemAtPosition(0);
        // assert that out edits are shown on the screen to the user
        assertEquals("salad", editedTweet.getText());

        // back in the main activity
        receiverActivity.finish();

        // end of test: clear the data
        // end of test: make sure the edit activity is closed
        receiverActivity.finish();
    }

}