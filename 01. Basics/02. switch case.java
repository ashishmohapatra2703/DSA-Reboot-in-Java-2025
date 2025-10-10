import java.io.*;
import java.util.*;

public class GFG
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0)
        {
            int choice = Integer.parseInt(in.readLine());
            String a[] = in.readLine().trim().split("\\s+");
            List<Double> arr = new ArrayList<Double>();
            
            for(int i=0; i<choice; i++)
                arr.add(Double.parseDouble(a[i]));
                
            Solution ob = new Solution();
            
            if(choice == 1)
                System.out.println(ob. switchCase(choice, arr));
            else
                System.out.println((int)ob.switchCase(choice, arr));
        }
    }
}

class Solution
{
    static double switchCase(int choice, List<Double> arr)
    {
        double area = 0.0;
        switch(choice)
        {
            case 1:
                area = Math.PI*arr.get(0)*arr.get(0);
                break;
            case 2:
                area = arr.get(0)*arr.get(1);
                break;
        }
        return area;
    }
}

/*
i/p:
    2
    1
    3
    2
    3 4
o/p:
    28.274333882308138
    12
*/