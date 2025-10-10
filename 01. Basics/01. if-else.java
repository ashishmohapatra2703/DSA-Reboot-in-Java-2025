import java.util.*;
import java.io.*;

public class GFG 
{ 
    public static void main(String args[]) throws IOException 
    { 
        Scanner scn = new Scanner(System.in); 
        int t = scn.nextInt(); 
        while(t-- > 0) 
        { 
            int n = scn.nextInt(); 
            int m = scn.nextInt(); 
            
            Solution ob = new Solution(); 
            String ans = ob.compareNM(n,m); 
            System.out.println(ans);
        }
    }
} 

class Solution
{
    String compareNM(int n,int m){
        if(n < m)
            return "lesser";
        else if(n > m)
            return "greater";
        else
            return "equal";
    }
}
