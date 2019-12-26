import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Twitter {

    private Map<Integer, Set<Integer>> userFolloweeMap;
    private Map<Integer, List<HomeTimeline>> userNewsFeed;
    private Integer dateTweetAdded;

    public Twitter() {
        userFolloweeMap = new HashMap<>();
        userNewsFeed = new HashMap<>();
        dateTweetAdded = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        dateTweetAdded = dateTweetAdded + 1;
        HomeTimeline tweet = new HomeTimeline(userId, tweetId, dateTweetAdded);
        // add tweet to user own timeline
        if(!userNewsFeed.containsKey(userId)){
            userNewsFeed.put(userId, new ArrayList<HomeTimeline>());
        }
        userNewsFeed.get(userId).add(tweet);

        // add the user tweet to all the follower's timeline for faster fetch
        if(null == userFolloweeMap.get(userId))
            return;
        for(Integer followerId : userFolloweeMap.get(userId)){
            if(!userNewsFeed.containsKey(followerId)){
                userNewsFeed.put(followerId , new ArrayList<HomeTimeline>());
            }
            userNewsFeed.get(followerId).add(tweet);
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        if(userNewsFeed.containsKey(userId)){
            List<HomeTimeline> tweets = userNewsFeed.get(userId);
            int size = Math.min(10, tweets.size());
            for(int i = tweets.size()-1; (i >=0 && size > 0); i--){
                result.add(tweets.get(i).tweetId);
                size--;
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
       // if follower and followee are same, do nothing
        if(followeeId == followerId)
            return;

        if(!userFolloweeMap.containsKey(followeeId)){
            userFolloweeMap.put(followeeId , new HashSet<Integer>());
        }
        // If already following do nothing
        if(userFolloweeMap.get(followeeId).contains(followerId)) return;

        userFolloweeMap.get(followeeId).add(followerId);
        if(!userNewsFeed.containsKey(followeeId)) return;
        // Add all the existing tweets tweeted by followeeId to the follower's timeline
        if(!userNewsFeed.containsKey(followerId)){
            userNewsFeed.put(followerId, new ArrayList<HomeTimeline>());
        }
        List<HomeTimeline> followerTimelineTweets = new ArrayList<>();
        for(HomeTimeline tweet : userNewsFeed.get(followeeId)){
            if(tweet.userId == followeeId)
                followerTimelineTweets.add(tweet);
        }
        userNewsFeed.get(followerId).addAll(followerTimelineTweets);
        // keep the tweets in the order of dateAdded in the follower timeline
        Collections.sort(userNewsFeed.get(followerId));
    }


    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(null != userFolloweeMap.get(followeeId) && userFolloweeMap.get(followeeId).contains(followerId)){
            userFolloweeMap.get(followeeId).remove(followerId);
            // remove the followee tweets from the follower home timeline cache.
            Iterator<HomeTimeline> userNewsFeedIterator = userNewsFeed.get(followerId).iterator();
            while(userNewsFeedIterator.hasNext()){
                HomeTimeline tweet = userNewsFeedIterator.next();
                if(followeeId == tweet.userId){
                    userNewsFeedIterator.remove();
                }
            }
        }
    }

    private static class HomeTimeline implements Comparable<HomeTimeline>{
        private Integer userId;
        private Integer tweetId;
        private Integer dateTweetAdded;

        public HomeTimeline(int userId, int tweetId, int dateTweetAdded){
            this.tweetId = tweetId;
            this.userId = userId;
            this.dateTweetAdded = dateTweetAdded;
        }

        @Override
        public int compareTo(HomeTimeline o) {
            return this.dateTweetAdded.compareTo(o.dateTweetAdded);
        }
    }
}

