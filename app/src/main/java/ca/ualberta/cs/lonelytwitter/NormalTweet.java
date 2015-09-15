package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yunita on 9/15/15.
 */
public class NormalTweet extends Tweet {

    public NormalTweet(String text, Date date) {
        super(text, date);
    }

    public NormalTweet(String text) {
        super(text);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }

}
