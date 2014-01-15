package ca.ualberta.cs.lonelytwitter;

public class ConceteTweetView implements TweetView {

	public String formatTweet(LonelyTweetModel lt) {
		return lt.getTimestamp().toString() + " | " + lt.getText();
	}

}
