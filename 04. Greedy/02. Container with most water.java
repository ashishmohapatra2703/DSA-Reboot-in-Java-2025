/* You are given an integer array height of length n. There are n vertical lines 
drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, 
such that the container contains the most water.
Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49   */

// max area between 2 verical lines and X-axis
class Solution {
    public int maxArea(int[] height) 
    {
        int i = 0;
        int j = height.length-1;
        int maxArea = Integer.MIN_VALUE;

        while(i < j)
        {
            int ht = Math.min(height[i], height[j]);
            int wd = j-i;
            int area = ht * wd;
            maxArea = Math.max(area, maxArea);

            //now we are decreasing width -> greedily select the high height to maximize area
            if(height[i] <= height[j]) {
                i++;
            } else if (height[i] > height[j]) {
                j--;
            }
            // if height[i] == height[j] -> we can do either i++ or j--
        }
        return maxArea;
    }
}