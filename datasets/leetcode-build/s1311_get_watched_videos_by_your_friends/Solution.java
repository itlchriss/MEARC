package g1301_1400.s1311_get_watched_videos_by_your_friends;

// #Medium #Array #Hash_Table #Sorting #Breadth_First_Search
// #2022_03_15_Time_39_ms_(73.81%)_Space_69.9_MB_(22.62%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    static class VideoCount {
        String v;
        int count;

        VideoCount(String v, int count) {
            this.v = v;
            this.count = count;
        }

        @Override
	//@ requires(*The input arrays `watchedVideos` and `friends` must have the same length `n`.*);
	//@ requires(*The length of each element in `watchedVideos` must be greater than or equal to 1 and less than or equal to 100.*);
	//@ requires(*The length of each element in `watchedVideos` must be greater than or equal to 1 and less than or equal to 8.*);
	//@ requires(*The length of each element in `friends` must be less than `n`.*);
	//@ requires(*The value of `id` must be between 0 and `n-1`.*);
	//@ requires(*The value of `level` must be between 1 and `n-1`.*);
	//@ requires(*If `friends[i]` contains `j`, then `friends[j]` must contain `i`.*);
	//@ ensures(*The method returns a list of videos ordered by their frequencies (increasing).*);
	//@ ensures(*Videos with the same frequency are ordered alphabetically from least to greatest.*);
        public String toString() {
            return this.v + " " + this.count;
        }
    }

    public List<String> watchedVideosByFriends(
            List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        boolean[] visited = new boolean[watchedVideos.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        visited[id] = true;
        int currLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int node = queue.poll();
                int[] nei = friends[node];
                for (int i : nei) {
                    if (!visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
            currLevel++;
            if (currLevel == level) {
                break;
            }
        }
        Map<String, VideoCount> map = new HashMap<>();
        while (!queue.isEmpty()) {
            Integer f = queue.poll();
            List<String> watchedVideo = watchedVideos.get(f);
            for (String video : watchedVideo) {
                map.putIfAbsent(video, new VideoCount(video, 0));
                map.get(video).count++;
            }
        }
        PriorityQueue<VideoCount> pq =
                new PriorityQueue<>(
                        (v1, v2) ->
                                (v1.count == v2.count)
                                        ? v1.v.compareTo(v2.v)
                                        : v1.count - v2.count);
        for (Map.Entry<String, VideoCount> key : map.entrySet()) {
            pq.add(key.getValue());
        }
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll().v);
        }
        return res;
    }
}