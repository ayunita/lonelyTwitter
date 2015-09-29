package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by yunita on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testHoldsStuff(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }

    public void testHoldsManyThings(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        assertEquals(list.getCount(), 1);
        list.addTweet(new NormalTweet("test"));
        assertEquals(list.getCount(), 2);
    }

    public void testAddingRemovingTweets(){
        TweetList list = new TweetList();
        list.addTweet(new NormalTweet("1"));
        list.addTweet(new NormalTweet("2"));
        list.addTweet(new NormalTweet("3"));
        list.removeTweet(0);
        assertEquals(list.getCount(), 2);
        list.addTweet(new NormalTweet("3"));
        assertEquals(list.getCount(), 2);

    }

    public void testGettingTweetsInOrder(){
        TweetList list = new TweetList();
        list.addTweet(new NormalTweet("test 1"));
        list.addTweet(new NormalTweet("test 2"));
        list.addTweet(new NormalTweet("test 3"));
        list.getTweets();
    }

    public void testDuplicateTweets(){
        TweetList list = new TweetList();
        list.addTweet(new NormalTweet("test 1"));
        list.hasTweet(new NormalTweet("test 1"));
    }

}