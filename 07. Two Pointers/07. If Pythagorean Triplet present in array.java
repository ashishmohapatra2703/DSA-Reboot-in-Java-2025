/* https://www.geeksforgeeks.org/problems/pythagorean-triplet3018/1
Given an array arr[], return true if there is a triplet (a, b, c) from the array 
(where a, b, and c are on different indexes) that satisfies a2 + b2 = c2, otherwise return false.

Input: arr[] = [3, 2, 4, 6, 5]
Output: true
Explanation: a=3, b=4, and c=5 forms a pythagorean triplet. */

//M-1 Similar to 3Sum but TLE because of O(n^2)
class Solution {
    boolean pythagoreanTriplet(int[] arr) {
        arr = Arrays.stream(arr)
            .boxed()
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
        int n = arr.length;

        for(int i=0; i<n; i++)
        {
            int hypSq = arr[i]*arr[i]; //for each num1, apply 2-pointer
            
            int low = i+1;
            int high = n-1;
            while(low < high)
            {
                int baseSq = arr[low]*arr[low];
                int perpSq = arr[high]*arr[high];
            
                if(baseSq+perpSq == hypSq) {
                    return true;
                }
                else if(baseSq+perpSq < hypSq)
                    high--;
                else if(baseSq+perpSq > hypSq)
                    low++;
            }
        }

        return false;
    }
}

//M-2 O(n^2) but More optimized => Passed
class GfG {
    static boolean pythagoreanTriplet(int[] arr) { 
        int n = arr.length;
        HashSet<Integer> st = new HashSet<>();
        for (int i = 0; i < n; i++)
            st.add(arr[i]);

        // Iterate through all possible values of a and b
        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = i + 1; j < n; j++) 
            {    
                int a = arr[i];
                int b = arr[j];
                int c = (int)Math.sqrt(a * a + b * b);
            
                // First, verify if c^2 is a perfect square (indicating a 
                // valid c) and check if this c exists in the set.
                if (c*c == a*a + b*b && st.contains(c))
                    return true;
            }
        }
        return false;
    }
}

//M-3 O(max(arr)^2) Time and O(max(arr)) Space
class Solution {
    boolean pythagoreanTriplet(int[] arr) {
        HashSet<Integer> set = new HashSet<>(); //to check element presence in array in O(1) time
        for(int ele:arr)
            set.add(ele);
            
        int maxEle = 0;
        for (int ele:arr) 
            maxEle = Math.max(maxEle, ele);

        // Iterate through all possible values (a, b) starting from 1,2,3,...
        // within the range of maximum element and present in the input 
        for(int a=1; a<=maxEle; a++) 
        {
            if(!set.contains(a))
                continue;
            for(int b=1; b<=maxEle; b++) 
            {    
                if(!set.contains(b))
                    continue;
                int c = (int)Math.sqrt(a*a + b*b);
            
                // c^2 is a perfect square + c exists in the set + c is within maxEle
                if (c*c == a*a + b*b && c <= maxEle && set.contains(c))
                    return true;
            }
        }
        return false;
    }
}