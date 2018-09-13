/**
 * You are given several logs that each log contains a unique id and timestamp.
 * Timestamp is a string that has the following format:
 * Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59.
 * All domains are zero-padded decimal numbers.
 * 
 * Design a log storage system to implement the following functions:
 * 
 * void Put(int id, string timestamp): Given a log's unique id and timestamp,
 * store the log in your storage system.
 * 
 * int[] Retrieve(String start, String end, String granularity): Return the
 * id of logs whose timestamps are within the range from start to end. Start
 * and end all have the same format as timestamp. However, granularity means
 * the time level for consideration. For example,
 * start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59",
 * granularity = "Day", it means that we need to find the logs within the range
 * from Jan. 1st 2017 to Jan. 2nd 2017.
 * 
 * Example 1:
 * put(1, "2017:01:01:23:59:59");
 * put(2, "2017:01:01:22:59:59");
 * put(3, "2016:01:01:00:00:00");
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
 * // return [1,2,3], because you need to return all logs within 2016 and 2017.
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour");
 * // return [1,2], because you need to return all logs start from
 * 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
 * 
 * Note:
 * There will be at most 300 operations of Put or Retrieve.
 * Year ranges from [2000,2017]. Hour ranges from [00,23].
 * Output for Retrieve has no order required.
 */

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
  

public class DesignLogStorageSystem635 {
    class LogSystem {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss");
        private static final int K = 6;
        private TreeMap<String, Integer> treeMap;
        
        public LogSystem() {
            Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);
            treeMap = new TreeMap<>(comp);
        }
        
        public void put(int id, String timestamp) {
            treeMap.put(timestamp, id);
        }
        
        public List<Integer> retrieve(String s, String e, String gra) {
            String start = roundLeft(s, gra);
            String end = roundRight(e, gra);
            Map<String, Integer> subMap = treeMap.subMap(start, end);
            List<Integer> res = new ArrayList<>();
            for (Integer id: subMap.values()) {
                res.add(id);
            }
            return res;
        }
        
        private String roundLeft(String timestamp, String gra) {
            TimeUnit unit = TimeUnit.valueOf(gra);
            return roundLeft(timestamp, unit);
        }
        
        private String roundLeft(String timestamp, TimeUnit unit) {
            String[] times = timestamp.split(":");
            for (int k=K-1; k>unit.pos; k--) {
                times[k] = "00";
            }
            return String.join(":", times);
        }
        
        private String plus(String timestamp, TimeUnit unit) {
            LocalDateTime newTime = LocalDateTime.parse(timestamp, formatter).plus(1, ChronoUnit.valueOf(unit.chrono));
            return formatter.format(newTime);
        }
        
        private String roundRight(String timestamp, String gra) {
            TimeUnit unit = TimeUnit.valueOf(gra);
            String newTs = plus(timestamp, unit);
            return roundLeft(newTs, unit);
        }
    }

    enum TimeUnit {
        Year(0, "YEARS"),
        Month(1, "MONTHS"),
        Day(2, "DAYS"),
        Hour(3, "HOURS"),
        Minute(4, "MINUTES"),
        Second(5, "SECONDS");

        int pos;
        String chrono;
        TimeUnit(int position, String chrono) {
            this.pos = position;
            this.chrono = chrono;
        }
    }


    /**
     * https://leetcode.com/problems/design-log-storage-system/discuss/105008/Concise-Java-Solution
     */
    class LogSystem2 {
        List<String[]> timestamps = new LinkedList<>();
        List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
        int[] indices = new int[]{4,7,10,13,16,19};
        
        public void put(int id, String timestamp) { timestamps.add(new String[]{Integer.toString(id), timestamp}); }
    
        public List<Integer> retrieve(String s, String e, String gra) {
            List<Integer> res = new LinkedList<>();
            int idx = indices[units.indexOf(gra)];
            for (String[] timestamp : timestamps) {
                if (timestamp[1].substring(0, idx).compareTo(s.substring(0, idx)) >= 0 &&
                    timestamp[1].substring(0, idx).compareTo(e.substring(0, idx)) <= 0) res.add(Integer.parseInt(timestamp[0]));
            }
            return res;
        }
    }



/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */

}
