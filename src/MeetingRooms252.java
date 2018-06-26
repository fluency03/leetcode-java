/**
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all
 * meetings.
 * 
 * Example 1:
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * 
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: true
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class MeetingRooms252 {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));
        for (int i=1; i<intervals.length; i++) {
            if (intervals[i-1].end > intervals[i].start) return false;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/meeting-rooms/discuss/67780/Easy-JAVA-solution-beat-98/148985
     */
    public boolean canAttendMeetings2(Interval[] intervals) {
        int len=intervals.length;
        if(len==0){
            return true;
        }
        int[]begin=new int[len];
        int[]stop=new int[len];
        for(int i=0;i<len;i++){
            begin[i]=intervals[i].start;
            stop[i]=intervals[i].end;
        }
        Arrays.sort(begin);
        Arrays.sort(stop);
        int endT=0;
        for(int i=1;i<len;i++){
            if(begin[i]<stop[i-1]){
                return false;
            }
        }
        return true;
    }

}

