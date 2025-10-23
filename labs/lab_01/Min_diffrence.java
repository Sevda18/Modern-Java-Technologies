

import java.util.Arrays;

import static java.lang.Math.abs;

public class Min_diffrence {
    public static int min_diffrence_finder(int[] tasks){

       int left = 0, right = 0;
       boolean isLeftMore = false;
       Arrays.sort(tasks);

       for(int i = tasks.length-1; i >= 0; i--)
       {
           if(isLeftMore)
           {
               right+=tasks[i];
           }
           else{
               left += tasks[i];
           }

           isLeftMore = (left > right);
       }

        return abs(left - right);
    }
}
