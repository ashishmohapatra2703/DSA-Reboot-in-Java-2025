/* 1. Input: 5
Output:
* * * * *
* * * * *
* * * * *
* * * * *
* * * * *       */
class Solution {
    void printSquare(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

/* 2. Input: 5
Output:
* 
* * 
* * * 
* * * * 
* * * * *      */
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=i; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

/* 3. Input: 5
Output:
1
1 2 
1 2 3 
1 2 3 4 
1 2 3 4 5     */
class Solution {
    void printTriangle(int n) 
    {
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=i; j++)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}

/* 4. Input: 5
Output:
1
2 2 
3 3 3 
4 4 4 4 
5 5 5 5 5   */
class Solution {
    void printTriangle(int n) 
    {
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=i; j++)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

/* 5. Input: 5
Output:
1 
0 1 
1 0 1
0 1 0 1 
1 0 1 0 1  */
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=i; j++)
            {
                if((i+j) %2 == 0)
                    System.out.print("1 ");
                else if((i+j) %2 != 0)
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }
}

/* 6. Input: 5
Output:
1 
2  3 
4  5   6 
7  8   9 10 
11 12 13 14 15    */
class Solution {
    void printTriangle(int n) 
    {
        int num = 1;
        
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=i; j++)
            {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }
}

/* 7. Input: 5
Output:
* * * * *
* * * * 
* * * 
* *  
*           */
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; i+j<=n-1; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

/* 8. Input: 5
Output:
1 2 3 4 5
1 2 3 4
1 2 3 
1 2  
1    */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=1; i+j<=n; j++)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}

/* 9. Input: 5
Output:
    *
   ***  
  *****
 *******
*********       */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=(n-1)*2; j++)
            {
                if(i+j < n-1)
                    System.out.print(" ");
                
                else if(i+j >= n-1 && (j-n) <= (i-1))
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}

/* 10. Input: 5
Output:
*********
 *******
  *****
   ***
    *       */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=(n-1)*2; j++)
            {
                if(j < i)
                    System.out.print(" ");
                
                else if(j >= i &&  i+(j-n) < n-1)
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}

/* 11. Input: 5
Output:
* 
* * 
* * * 
* * * * 
* * * * *
* * * *
* * *
* *
*                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<=(n-1)*2; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(j <= i  && (i-n)+j < n-1)
                    System.out.print("* ");
            }
            System.out.println();
        }
    }
}

/* 12.  Input: 5
 Output: 
*        *
**      **
***    ***
****  ****
**********
****  ****
***    ***
**      **
*        *                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<=(n-1)*2; i++)
        {
            for(int j=0; j<=n*2-1 ; j++)  //columns = (n-1)*2 + 1 = n*2 - 1
            {
                if((j <= i  && (i-n)+j < n-1)  ||  (i+j >= n*2-1 && j > i))
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}

/* 13.  Input: 5
Output:
1                 1
1 2             2 1
1 2 3         3 2 1
1 2 3 4     4 3 2 1
1 2 3 4 5 5 4 3 2 1                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=2*n; j++)
            {
                if(j <= i)
                    System.out.print(j + " ");
                else if(i+(j-n) >= n+1)
                    System.out.print(2*n+1 -j + " ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
}

/* 14.  Input: 5
Output:
A
AB
ABC
ABCD
ABCDE                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=i; j++)
            {
                System.out.print((char)('A'+j));
            }
            System.out.println();
        }
    }
}

/* 15.  Input: 5
Output:
ABCDE
ABCD
ABC
AB
A                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; i+j<=n-1; j++)
            {
                System.out.print((char)('A'+j));
            }
            System.out.println();
        }
    }
}

/* 16.  Input: 5
Output:
A
BB
CCC
DDDD
EEEEE                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=i; j++)
            {
                System.out.print((char)('A'+i));
            }
            System.out.println();
        }
    }
}

/* 17.  Input: 6
Output:
     A
    ABA
   ABCBA
  ABCDCBA
 ABCDEDCBA
ABCDEFEDCBA                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            //left half and middle
            for(int j=0; j<n; j++)
            {
                if(i+j < n-1)
                    System.out.print(" ");
                if(i+j >= n-1)
                    System.out.print((char)('A'+i+j-(n-1)));
            }
            
            //right half
            int colTotal = (n-1)*2;
            for(int j=n; j<=colTotal; j++)
            {
                if((j-n) < i)
                    System.out.print((char)('A'+i+(colTotal-j)-(n-1)));
            }
            System.out.println();
        }
    }
}

/* 18.  Input: 5
Output:
E
E D
E D C
E D C B
E D C B A                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=i; j++)
            {
                System.out.print(((char)('A'+(n-1-j))) + " ");
            }
            System.out.println();
        }
    }
}

/* 19.  Input: 5
Output:
**********
****  ****
***    ***
**      **
*        *
*        *
**      **
***    ***
****  ****
**********                   */ 
class Solution {
    void printTriangle(int n) 
    {
        for(int i=0; i<2*n; i++)
        {
            for(int j=0; j<2*n; j++)
            {
                if(i+j <= n-1  ||                 // top left triangle
                    j <= (i-n) ||                 // bottom left triangle
                    (j-n) >= i ||                 // top right triangle
                    (i-n)+(j-n) >= n-1)           // bottom right triangle
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}

/* 20.  Input: 4
Output:
****
*  *
*  *
****                   */ 
class Solution {
    void printSquare(int n) 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(i == 0  ||                 // top horizontal
                    i == n-1 ||                 // bottom horizontal
                    j == 0 ||                 // left vertical
                    j == n-1)                 // right vertical
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}

/* 21.  Input: 4
Output:
4 4 4 4 4 4 4
4 3 3 3 3 3 4
4 3 2 2 2 3 4
4 3 2 1 2 3 4
4 3 2 2 2 3 4
4 3 3 3 3 3 4
4 4 4 4 4 4 4                   */
//Method-1  S.C = O(n²)
class Solution {
    void fillSqaureRing(int[][] sq, int ringStart, int ringEnd, int n)
    {
        for(int i=ringStart; i<=ringEnd; i++)
        {
            for(int j=ringStart; j<=ringEnd; j++)
            {
                if(i == ringStart  ||                 
                    i == ringEnd ||                 
                    j == ringStart ||                 
                    j == ringEnd)                 
                    sq[i][j] = n;
            }
        }
    }
    void printMatrix(int[][] sq, int size)
    {
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                System.out.print(sq[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    void printSquare(int n) 
    {
        int size = 2*n - 1;
        int[][] sq = new int[size][size];
        int ringStart = 0;
        int ringEnd = size -1;
        
        while(n > 0)
        {
            fillSqaureRing(sq, ringStart++, ringEnd--, n--);
        }
        
        printMatrix(sq, size);
    }
}
//Method-2   S.C = O(1)
class Solution {
    void printSquare(int n) 
    {
        int size = 2*n - 1;
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                int distanceFromLeft = j;
                int distanceFromTop = i;
                int distanceFromRight = size-1 - j;
                int distanceFromDown = size-1 - i;
                System.out.print(n - Math.min(Math.min(distanceFromLeft, distanceFromTop), Math.min(distanceFromRight, distanceFromDown)) + " ");
            }
            System.out.println();
        }
    }
}


/* 22.   Input: 6
Your Output: 
     *
    * *
   * * *
  * * * *
 * * * * *
* * * * * *
* * * * * *
 * * * * *
  * * * *
   * * *
    * *
     *                   */
class Solution {
    void printDiamond(int n) 
    {
        //upper half
        for(int i=0; i<n; i++)
        {
            boolean star = true;
            for(int j=0; j<=(n-1)*2; j++)
            {
                if(i+j < n-1)
                    System.out.print(" ");
                
                else if(i+j >= n-1 && (j-n) <= (i-1))
                {
                    if(star == true)
                        System.out.print("*");
                    else if(star == false)
                        System.out.print(" ");
                        
                    star = !star;
                }
            }
            System.out.println();
        }
        
        //lower half
        for(int i=0; i<n; i++)
        {
            boolean star = true;
            for(int j=0; j<=(n-1)*2; j++)
            {
                if(j < i)
                    System.out.print(" ");
                
                else if(j >= i &&  i+(j-n) < n-1)
                {
                    if(star == true)
                        System.out.print("*");
                    else if(star == false)
                        System.out.print(" ");
                        
                    star = !star;
                }
            }
            System.out.println();
        }
    }
}