/* Given an integer numRows, return the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Input: numRows = 5
Output: 
[[1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]]
*/

//M-1
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();

        int num = 1;
        for (int i=0; i<numRows; i++) {
            Integer iCj = 1; //iC0 = 1
            List<Integer> row = new ArrayList<>();

            for (int j=0; j<=i; j++) {
                row.add(iCj);
                Integer iCjplus1 = iCj * (i-j) / (j+1); // binomial coefficient formula for next number
                iCj = iCjplus1;
            }
            pascalTriangle.add(row);
        }
        return pascalTriangle;
    }
}

//M-2 DP table
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();

        int num = 1;
        for (int i=0; i<numRows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j=0; j<=i; j++) {
                if (j==0 || j==i) {
                    // start and end elements of a row are always 1
                    row.add(1);
                } else {
                    // row[i][j] = row[i-1][j-1] + row[i-1][j]
                    int val = pascalTriangle.get(i-1).get(j-1) 
                            + pascalTriangle.get(i-1).get(j);
                    row.add(val);
                }
            }
            pascalTriangle.add(row);
        }
        return pascalTriangle;
    }
}
