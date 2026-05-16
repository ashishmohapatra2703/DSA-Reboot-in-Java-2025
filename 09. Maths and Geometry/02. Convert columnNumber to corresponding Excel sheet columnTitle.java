/* https://leetcode.com/problems/excel-sheet-column-title/description/
Given an integer columnNumber, 
return its corresponding column title as it appears in an Excel sheet.
For example:
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...

Input: columnNumber = 28
Output: "AB"  
Asked to me in uiPath Round-1 Interview on 4th May 2026, 
could not figure out to do columnNumber-- in case of Z char */

class Solution {
    //BY = 26^1 (2) + 26^0 (25)
    //AZ = 26^1 (1) + 26^0 (26)
    //CZ = 26^1 (3) + 26^0 (26) = 26 (4)
    //ZY = 26^1 (26) + 26^0 (25)
    public String convertToTitle(int columnNumber) {
        StringBuilder colNameStr = new StringBuilder();

        while(columnNumber != 0) {
            int rem = columnNumber % 26;
            if(rem == 0) {
                colNameStr.append('Z');
                columnNumber = columnNumber/26;
                columnNumber--;
            } else if(rem >= 1 && rem <=25) {
                colNameStr.append((char)(rem-1+'A'));
                columnNumber = columnNumber/26;
            }
        }
    
        return colNameStr.reverse().toString();
    }
}