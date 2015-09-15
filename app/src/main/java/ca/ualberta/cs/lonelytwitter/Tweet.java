package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by yunita on 9/15/15.
 */
public abstract class Tweet extends Object implements Tweetable {

    private String text;
    private Date date;

    public Tweet(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public Tweet(String text) {
        this.text = text;
        this.date = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text){
        try {
            if (this.text.length() <= 140) {
                this.text = text;
            } else {
                throw new IOException("Tweets can't be that long.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();

}
