/* https://leetcode.com/problems/add-strings/description/
Given two non-negative integers, num1 and num2 represented as string, 
return the sum of num1 and num2 as a string.
You must solve the problem without using any built-in library for handling large integers (such as BigInteger). 
You must also not convert the inputs to integers directly.

Input: num1 = "11", num2 = "123"
Output: "134"
Input: num1 = "456", num2 = "77"
Output: "533"
Input: num1 = "1", num2 = "9"
Output: "10"      */

class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sumof2Nums = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carryToLeft = 0;
        while(i >= 0 || j >= 0 || carryToLeft != 0) {
            int iVal = (i>=0) ? Character.getNumericValue(num1.charAt(i)) : 0;
            int jVal = (j>=0) ? Character.getNumericValue(num2.charAt(j)) : 0;

            int sumOfIAndJ = iVal + jVal + carryToLeft;
            if(sumOfIAndJ >= 10) {
                sumof2Nums.append(String.valueOf(sumOfIAndJ-10));
                carryToLeft = 1;
            } else if(sumOfIAndJ < 10) {
                sumof2Nums.append(String.valueOf(sumOfIAndJ));
                carryToLeft = 0;
            }
            
            i--;
            j--;
        }

        return sumof2Nums.reverse().toString();
    }
}