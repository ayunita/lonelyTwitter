package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by yunita on 9/29/15.
 */
public class TweetList {

    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void addTweet(Tweet tweet){
        mostRecentTweet = tweet;
        if(tweets.size() == 0){
            tweets.add(tweet);
        } else {
            for(int i = 0; i < tweets.size(); i++){
                if(tweets.get(i).getText().equals(tweet.getText())){
                    throw new IllegalArgumentException();
                } else {
                    tweets.add(tweet);
                }
            }
        }
    }

    public ArrayList<Tweet> getTweets() {
        ArrayList<Tweet> tweetsInOrder = new ArrayList<Tweet>();
        for(int i = tweets.size()-1; i >= 0; i--){
            tweetsInOrder.add(tweets.get(i));
        }
        return tweetsInOrder;
    }

    public void removeTweet(int index){
        tweets.remove(index);
    }

    public Tweet getMostRecentTweet(){
        return mostRecentTweet;
    }

    public int getCount(){
        return tweets.size();
    }

    public boolean hasTweet(Tweet tweet){
        if(tweets.contains(tweet)){
            return true;
        } else {
            return false;
        }
    }
}
