/* Input: n = 2
Output: 20 18 16 14 12 10 8 6 4 2
*/

class Solution
{
    public void printTable(int n)
    {
        int multiplier = 10;
        while(multiplier > 0)
        {
            System.out.print(n*multiplier-- + " ");
        }
        System.out.println();
    }
}