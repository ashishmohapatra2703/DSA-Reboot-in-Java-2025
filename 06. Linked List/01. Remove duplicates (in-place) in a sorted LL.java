/* https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
Given the head of a sorted linked list, delete all duplicates such that 
each element appears only once. Return the linked list sorted as well.

Input: head = [1,1,2,3,3]
Output: [1,2,3]  */

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;
            
        ListNode curr = head;
        while(curr.next != null)
        {
            if(curr.next != null && curr.next.val == curr.val)
            {
                curr.next = curr.next.next; //delete/remove the duplicate
            }
            else {
                curr = curr.next; //move only when no deletion
            }
        }

        return head;
    }
}