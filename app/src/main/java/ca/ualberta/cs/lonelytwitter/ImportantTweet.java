package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yunita on 9/15/15.
 */
public class ImportantTweet extends Tweet{

    public ImportantTweet(String text, Date date) {
        super(text, date);
    }

    public ImportantTweet(String text) {
        super(text);
    }

    public Boolean isImportant(){
        return Boolean.TRUE;
    }

    @Override
    public String getText(){
        return "important!: " + super.getText();
    }

}
