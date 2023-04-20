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
        System.out.println(findMinDifference(Arrays.asList("00:00","23:59","00:00")));
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
