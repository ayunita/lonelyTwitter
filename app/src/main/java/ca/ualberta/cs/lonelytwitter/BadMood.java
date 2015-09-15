package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yunita on 9/15/15.
 */
public class BadMood extends ABC {

    private Date date;

    public BadMood(Date date){
        this.date = date;
    }

    public BadMood(){
        Date date = new Date();
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String tweetMood() {
        return "SAD";
    }
}
