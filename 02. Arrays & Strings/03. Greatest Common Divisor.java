/*Given an integer array nums, 
return the greatest common divisor of the smallest number and largest number in nums.
The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

Input: nums = [2,5,6,9,10]
Output: 2
Explanation:
The smallest number in nums is 2.
The largest number in nums is 10.
The greatest common divisor of 2 and 10 is 2.   */

class Solution {
    public int findGCD(int[] nums) 
    {
        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < smallest)
                smallest = x;
            if (x >= largest)
                largest = x;
        }

        return gcd(largest, smallest);
    }

    private int gcd(int x, int y)   // gcd / hcf function (recursive)
    {
        return (y == 0) ? x : gcd(y, x % y);
    }
}