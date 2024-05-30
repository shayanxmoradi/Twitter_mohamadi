import entity.Tweet;
import entity.User;
import util.ApplicationContext;

import java.sql.SQLException;

public class TweeterApplication {
    public static void main(String[] args) throws SQLException {
//        User user = new User("fatemeh","22222");
////        System.out.println(user);
////        System.out.println("----------------------------");
//        user = ApplicationContext.getInstance().getUserService().save(user);
////        System.out.println(user);
//
//
//        Tweet tweet =new Tweet("hhhhhhhhhhhhhhhhhh",user);
//        System.out.println(tweet);
//        System.out.println("---------------");
//        tweet=ApplicationContext.getInstance().getTweetService().save(tweet);
//        System.out.println(tweet);

       // System.out.println(tweet.getUser().getId());
        ApplicationContext.getInstance().getUserService().login("fatemeh","22222");
      Tweet tweet1=  ApplicationContext.getInstance().getUserService().updateTweet("asdr",1);
        System.out.println(tweet1);
        ApplicationContext.getInstance().getUserService().deleteTweetById(2);
        Tweet tweet2=  ApplicationContext.getInstance().getUserService().findTweetById(1);
        System.out.println(tweet2);
    }
}
