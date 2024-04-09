package com.ashx.leetcode.google.medium;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/minimum-time-difference/">Leetcode</a>
 */
public class MinimumTimeDifference {
    public static void main(String[] args) {
        //        System.out.println(findMinDifference(Arrays.asList("23:59", "00:00")));
        System.out.println(findMinDifferencePerf(Arrays.asList("00:00", "23:59", "00:00")));
    }

    public static int findMinDifferencePerf(List<String> timePoints) {
        var minutes = new boolean[24 * 60 + 1];
        int first = Integer.MAX_VALUE;
        int last = Integer.MIN_VALUE;
        for (var s : timePoints) {
            int minute = 0;
            minute += s.charAt(4) - '0';
            minute += (s.charAt(3) - '0') * 10;
            minute += (s.charAt(1) - '0') * 60;
            minute += (s.charAt(0) - '0') * 600;
            if (minutes[minute]) {
                return 0;
            }
            minutes[minute] = true;
            first = Math.min(first, minute);
            last = Math.max(last, minute);
        }
        int ans = first + 24 * 60 - last;
        int prev = first;
        for (int i = first + 1; i <= last; i++) {
            if (!minutes[i]) {
                continue;
            }
            ans = Math.min(ans, i - prev);
            prev = i;
        }
        return ans;
    }

    public static int findMinDifference(List<String> timePoints) {
        int res = 0;
        timePoints.sort(String.CASE_INSENSITIVE_ORDER);
        int n = timePoints.size();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date dt1 = sdf.parse(timePoints.get(0));
            Date dt2 = sdf.parse(timePoints.get(1));
            res = (int) Math.abs(dt2.getTime() - dt1.getTime()) / (60 * 1000);
            if (res > 720) {
                res = 1440 - res;
            }
        } catch (Exception e) {
        }
        for (int i = 1; i < n - 1; i++) {
            try {
                Date dt1 = sdf.parse(timePoints.get(i));
                Date dt2 = sdf.parse(timePoints.get(i + 1));
                int diff = (int) Math.abs(dt2.getTime() - dt1.getTime()) / (60 * 1000);
                if (diff > 720) {
                    diff = 1440 - diff;
                }
                res = Math.min(res, diff);
            } catch (Exception e) {
            }
        }

        if (n > 2) {
            int diff = 0;
            try {
                Date dt1 = sdf.parse(timePoints.get(n - 1));
                Date dt2 = sdf.parse(timePoints.get(0));
                diff = (int) Math.abs(dt2.getTime() - dt1.getTime()) / (60 * 1000);
                if (diff > 720) {
                    diff = 1440 - diff;
                }
            } catch (Exception e) {
            }

            res = Math.min(res, diff);
        }

        return res;
    }
}
