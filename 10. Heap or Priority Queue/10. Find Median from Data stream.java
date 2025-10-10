/* https://leetcode.com/problems/find-median-from-data-stream/description/
The median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0  */

class MedianFinder {
    PriorityQueue<Integer> leftMaxH; //largest of the smaller-half at the peek
    PriorityQueue<Integer> rightMinH; //smallest of the larger-half at the peek

    public MedianFinder() {
        leftMaxH = new PriorityQueue<>(Collections.reverseOrder());
        rightMinH = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(leftMaxH.isEmpty() || num <= leftMaxH.peek()) {
            leftMaxH.add(num);
        } else if(rightMinH.isEmpty() || num >= rightMinH.peek() 
            || (leftMaxH.peek() <= num && num <= rightMinH.peek())) { // simply have else {put in right-half}
            rightMinH.add(num);
        }

        // balance the heap to be of format = (n+1 + n) or (n + n) in left + right
        if(rightMinH.size() - leftMaxH.size() == 1) {
            leftMaxH.add(rightMinH.poll()); // first balance left
        } else if(leftMaxH.size() - rightMinH.size() == 2) {
            rightMinH.add(leftMaxH.poll());
        } 
    }
    
    public double findMedian() {
        if(leftMaxH.size() == rightMinH.size()) {
            return (rightMinH.peek() + leftMaxH.peek()) / 2.0; // even no. of elements in n + n format
        } else {
            return leftMaxH.peek(); // odd no. of eleements in n+1 + n format
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */