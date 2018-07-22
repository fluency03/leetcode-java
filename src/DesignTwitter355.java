/**
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user and is able to see the 10 most recent tweets
 * in the user's news feed. Your design should support the following methods:
 * 
 * postTweet(userId, tweetId): Compose a new tweet.
 *
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's
 * news feed. Each item in the news feed must be posted by users who the user
 * followed or by the user herself. Tweets must be ordered from most recent to
 * least recent.
 *
 * follow(followerId, followeeId): Follower follows a followee.
 *
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * 
 * Example:
 * 
 * Twitter twitter = new Twitter();
 * 
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 * 
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 * 
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 * 
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 * 
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 * 
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 * 
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 */


public class DesignTwitter355 {
    class Twitter {
        private int time = 0;
        private Map<Integer, User> map = new HashMap<>();
        private static final Comparator<Tweet> comp = (t1, t2) -> Long.compare(t2.timestamp, t1.timestamp);
        
        /** Initialize your data structure here. */
        public Twitter() {
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            if (!map.containsKey(userId)) map.put(userId, new User(userId));
            map.get(userId).addTweet(tweetId, time++);
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed. Each
         * item in the news feed must be posted by users who the user followed
         * or by the user herself. Tweets must be ordered from most recent to
         * least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<Tweet> pq = new PriorityQueue<>(10, comp);
            if (!map.containsKey(userId)) return new ArrayList<Integer>();
            User me = map.get(userId);
            for (Integer uid: me.following) {
                pq.addAll(map.get(uid).tweets);
            }
            pq.addAll(me.tweets);
            List<Integer> ids = new ArrayList<>();
            int size = pq.size();
            for (int i=0; i<10 && i<size; i++) {
                ids.add(pq.poll().id);
            }
            return ids;
        }

        /**
         * Follower follows a followee. If the operation is invalid,
         * it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            if (!map.containsKey(followerId)) map.put(followerId, new User(followerId));
            if (!map.containsKey(followeeId)) map.put(followeeId, new User(followeeId));
            if (followerId == followeeId) return;
            map.get(followerId).follow(followeeId);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid,
         * it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            if (!map.containsKey(followerId)) map.put(followerId, new User(followerId));
            if (!map.containsKey(followeeId)) map.put(followeeId, new User(followeeId));
            if (followerId == followeeId) return;
            map.get(followerId).unfollow(followeeId);
        }

        class User {
            int id;
            LinkedList<Tweet> tweets = new LinkedList<>();
            Set<Integer> following = new HashSet<>();
            User(int i) {
                id = i;
            }

            public void addTweet(int i, int ts) {
                tweets.add(new Tweet(i, ts));
                while (tweets.size() > 10) {
                    tweets.removeFirst();
                }
            }

            public void follow(int userId) {
                following.add(userId);
            }

            public void unfollow(int userId) {
                if (following.contains(userId)) {
                    following.remove(userId);
                }
            }
        }

        class Tweet {
            int id;
            int timestamp;
            Tweet(int i, int ts) {
                id = i;
                timestamp = ts;
            }
        }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

}
