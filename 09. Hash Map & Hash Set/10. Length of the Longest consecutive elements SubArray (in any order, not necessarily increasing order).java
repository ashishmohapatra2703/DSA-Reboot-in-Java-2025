/* 1. https://www.geeksforgeeks.org/dsa/length-largest-subarray-contiguous-elements-set-1/
 
Given an array of distinct integers, find length of the longest subarray 
which contains numbers that can be arranged in a continuous sequence. 

Input:  arr[] = {10, 12, 11};
Output: Length of the longest contiguous subarray is 3

Input:  arr[] = {14, 12, 11, 20};
Output: Length of the longest contiguous subarray is 2

Input:  arr[] = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45};
Output: Length of the longest contiguous subarray is 5    */

// Rule:- contiguous subarray =>  difference between maximum and minimum elements in subarray 
// = difference between last and first indexes of subarray (i-j+1)
// T.C =  O(n^2)




/* 2. https://www.geeksforgeeks.org/dsa/length-largest-subarray-contiguous-elements-set-2/

Given an array of integers (DUPLICATES ALLOWED), find length of the longest subarray 
which contains numbers that can be arranged in a continuous sequence. 

Input:  arr[] = {10, 12, 11};
Output: Length of the longest contiguous subarray is 3

Input:  arr[] = {10, 12, 12, 10, 10, 11, 10};
Output: Length of the longest contiguous subarray is 2     */

// Same as (1) + use of hashset to check duplicacy where this rule will be void