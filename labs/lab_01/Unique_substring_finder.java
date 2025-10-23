public class Unique_substring_finder {
    //"aabccw"-> "abc

    public static String unique_substring_finder_func(String s) {
        int start = 0, end = 1;
        int diff = end - start;

        int startCurr = 0;
        int endCurr = 1;
        int diffCurr = endCurr - startCurr;
        while(endCurr != s.length()-1){

            int i = startCurr;
            while(s.charAt(i) != s.charAt(endCurr))
            {
                char c1 = s.charAt(i), c2 = s.charAt(endCurr);
                i++;
                if(endCurr == s.length()-1) {
                    endCurr++;
                    break;
                }
                endCurr++;
            }

            diffCurr = endCurr - startCurr;

            if(diff < diffCurr)
            {
                start = startCurr;
                end = endCurr;
                diff = diffCurr;
            }

            if(endCurr == s.length())
            {
                return s.substring(start,end);
            }

            if(endCurr != s.length()-1) {
                startCurr = endCurr;
                endCurr = startCurr + 1;
            }
        }

        return s.substring(start, end);
    }
}

public static void main(){
    println(Unique_substring_finder.unique_substring_finder_func("abcddqwertyuiioo"));
}


