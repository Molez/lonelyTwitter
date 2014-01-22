package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

public class TweetListController {
	private static TweetListModel tweetListModel = null;

	public TweetListController() {
		super();
		if(tweetListModel == null)
			tweetListModel = new TweetListModel();
	}
	
	public void addTweet(String text){
		LonelyTweetModel lt = new NormalTweetModel(text);
		ArrayList<LonelyTweetModel>list = tweetListModel.getList();
		list.add(lt);
	}
	
	public ArrayList<LonelyTweetModel> getList(){
		return tweetListModel.getList();
	}
	
	public void setList(ArrayList<LonelyTweetModel> list){
		tweetListModel.setList(list);
	}
	
	public TweetListModel getTweetListModel(){
		return tweetListModel;
	}
	
	public void setTweetListModel(TweetListModel tlm){
		tweetListModel = tlm;
	}
}
