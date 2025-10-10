//M-1
class Reverse
{
    public static String reverseWord(String str)
    {
        char[] reverse = str.toCharArray(); //String is immutable in java
        int i = 0;
        int j = str.length() - 1;
        
        while(i <= j)
        {
            swap(reverse, i, j);
            i++;
            j--;
        } 
        return new String(reverse);
    }

    static void swap(char[] reverse, int i, int j)
    {
        char temp = reverse[i];
        reverse[i] = reverse[j];
        reverse[j] = temp;
    }
}


//M-2 
class Reverse
{
    public static String reverseWord(String str)
    {
        String rev = "";
        for(int i = str.length()-1; i >= 0; i--)
        {
            rev += str.charAt(i);
        }
        return rev;
    }
}

