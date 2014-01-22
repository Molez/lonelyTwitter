package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

public class TweetListModel {
	private ArrayList<LonelyTweetModel> list;
	
	public TweetListModel(){
		list = new ArrayList<LonelyTweetModel>();
	}

	public ArrayList<LonelyTweetModel> getList() {
		return list;
	}

	public void setList(ArrayList<LonelyTweetModel> list) {
		if (list!=null){
			this.list = list;
		}
	}
	
}
