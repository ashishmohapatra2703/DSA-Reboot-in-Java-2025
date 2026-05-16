/* https://leetcode.com/problems/excel-sheet-column-number/ 
Given a string columnTitle that represents the column title as appears in an Excel sheet, 
return its corresponding column number.

For example:
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...

Input: columnTitle = "AB"
Output: 28 */

class Solution {
    //BY = 26^1 (2) + 26^0 (25)
    //AZ = 26^1 (1) + 26^0 (26)
    //CZ = 26^1 (3) + 26^0 (26)
    //ZY = 26^1 (26) + 26^0 (25)
    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int colNumber = 0;

        int factorToMultiplyToChar = 1; //starts from end with 26^0
        for(int i=n-1; i>=0; i--) {
            char charI = columnTitle.charAt(i); 
            colNumber += factorToMultiplyToChar * (charI-'A' +1); //auto type casting in charI - 'A' as a int
            factorToMultiplyToChar *= 26;
        }
        return colNumber;
    }
}