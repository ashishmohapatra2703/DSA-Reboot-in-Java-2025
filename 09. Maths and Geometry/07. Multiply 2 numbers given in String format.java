/* https://leetcode.com/problems/multiply-strings/ 
Given two non-negative integers num1 and num2 represented as strings, 
return the product of num1 and num2, also represented as a string.
Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Input: num1 = "2", num2 = "3"
Output: "6"
Input: num1 = "123", num2 = "456"
Output: "56088"      */

//M-1
class Solution {
    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();

        int maxLengthPossibleOfProductof2 = n1+n2; 
        int[] productOf2Nums = new int[n1+n2];
        
        for(int i=n1-1; i>=0; i--) {
            int carryToLeft = 0;
            int startIdxFromRight = i+n2; //for every i
            for(int j=n2-1; j>=0; j--) {
                int iVal = Character.getNumericValue(num1.charAt(i));
                int jVal = Character.getNumericValue(num2.charAt(j));

                int sum = (iVal*jVal) + productOf2Nums[startIdxFromRight] + carryToLeft;
                productOf2Nums[startIdxFromRight] = sum % 10;
                startIdxFromRight--;
                carryToLeft = sum / 10; 
            }
            productOf2Nums[startIdxFromRight] += carryToLeft; //if any non-zero carry still there, don't miss
        }

        StringBuilder productOf2NumsStr = new StringBuilder();
        for(int digit : productOf2Nums) {
            if(productOf2NumsStr.isEmpty() && digit == 0) //leading zero
                continue;
            else 
                productOf2NumsStr.append(digit);
        }
        return productOf2NumsStr.length()==0 ? "0" :productOf2NumsStr.toString();
    }
}

//M-2
class Solution {
    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();

        int maxLengthPossibleOfProductof2 = n1+n2; 
        int[] productOf2Nums = new int[n1+n2];
        for(int i=n1-1; i>=0; i--) {
            for(int j=n2-1; j>=0; j--) {
                int iVal = Character.getNumericValue(num1.charAt(i));
                int jVal = Character.getNumericValue(num2.charAt(j));

                int product = (iVal * jVal) + productOf2Nums[i+j+1];
                productOf2Nums[i+j+1] = product % 10;
                productOf2Nums[i+j] += product / 10; //productOf2Nums[i+j] = carryToLeft
            }
        }

        StringBuilder productOf2NumsStr = new StringBuilder();
        for(int digit : productOf2Nums) {
            if(productOf2NumsStr.isEmpty() && digit == 0) //leading zero
                continue;
            else 
                productOf2NumsStr.append(digit);
        }
        return productOf2NumsStr.length()==0 ? "0" :productOf2NumsStr.toString();
    }
}