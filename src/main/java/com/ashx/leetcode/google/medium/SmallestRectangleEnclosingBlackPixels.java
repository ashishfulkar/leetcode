package com.ashx.leetcode.google.medium;

public class SmallestRectangleEnclosingBlackPixels {
    public static void main(String[] args) {
        char[][] image = new char[][]{
                {'1'}};
        int minArea = minArea(image, 0,0);
        System.out.println(minArea);
    }

    public static int minArea(char[][] image, int x, int y) {
        int height = 0;
        for (int i = 0; i < image.length; i++) { // ROW
            boolean blackFound = false;
            for (int j = 0; j < image[i].length; j++) { // COL
                if (image[i][j] == '1') {
                    blackFound = true;
                    height++;
                    break;
                }
            }
            if (height != 0 && !blackFound) {
                break;
            }
        }
        int width = 0;
        for (int j = 0; j < image[0].length; j++) { // COL
            boolean blackFound = false;
            for (int i = 0; i < image.length; i++) { // ROW
                if (image[i][j] == '1') {
                    blackFound = true;
                    width++;
                    break;
                }
            }
            if (width != 0 && !blackFound) {
                break;
            }
        }
        return height* width;
    }
}
