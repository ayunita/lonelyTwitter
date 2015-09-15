package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yunita on 9/15/15.
 */
public class GoodMood extends ABC {

    private Date date;

    public GoodMood(Date date){
        this.date = date;
    }

    public GoodMood(){
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
        return "HAPPY";
    }
}
