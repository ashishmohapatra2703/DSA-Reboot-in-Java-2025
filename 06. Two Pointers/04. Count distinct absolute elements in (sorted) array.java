/*Given a sorted array of integers, return the 
number of distinct absolute values among the elements of the array. 
The input can contain duplicates values. 

Input: [-3, -2, 0, 3, 4, 5]
Output: 5
There are 5 distinct absolute values among the elements of this array, i.e. (0, 2, 3, 4 and 5)

Input:  [-1, -1, -1, -1, 0, 1, 1, 1, 1]
Output: 2  */

//M-1 Time Complexity : O(n) , Auxiliary Space : O(n)
class Solution {
    int distinctCount(int[] arr, int n) 
    {
        Set<Integer> hashSet = new HashSet<>();
        
        for(int num: arr)
        {
            hashSet.add(Math.abs(num));
        }
        
        return hashSet.size();
    }
}

//M-2 Time Complexity : O(n) , Auxiliary Space : O(1)
class Solution {
    int distinctCount(int[] arr, int n) 
    {
        int countDistinct = 0;
        int i = 0;
        int j = n-1;
        
        while(i <= j)
        {
            int left = Math.abs(arr[i]);
            int right = Math.abs(arr[j]);
            
            while(i<=n-2 && arr[i] == arr[i+1])
                i++;
            while(j>=1 && arr[j-1] == arr[j])
                j--;
                
            if(left == right)
            {
                countDistinct++;
                i++;
                j--;
            }
            else if(left < right)
            {
                countDistinct++;
                j--;
            }
            else if(left > right)
            {
                countDistinct++;
                i++;
            }
        }
        
        return countDistinct;
    }
}
