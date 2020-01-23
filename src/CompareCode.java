import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompareCode {
    private String string1;
    private String string2;
    private float pointer;


    public void compare(String str1, String str2)
    {
        for(int i=0;i<str1.length();i++)
        {
            if(i>=str2.length()){
                pointer++;
            }
            else if(str1.charAt(i)==str2.charAt(i))
            {
                pointer--;
            }
            else pointer++;
        }

        System.out.println(pointer);

        if(pointer<0)
        {
            System.out.println("Same Code");
        }
        else
        {
            System.out.println("Different Code");
        }
        pointer=0;
    }


    public float checkPoint(String str1, String str2){

        for(int i=0;i<str1.length();i++)
        {
            if(i>=str2.length()){
                pointer--;
            }
            else if(str1.charAt(i)==str2.charAt(i))
            {
                pointer++;
            }
            else pointer--;
        }

        double comparisonRatio = (pointer/str1.length());
        pointer=0;
        BigDecimal bd = new BigDecimal(comparisonRatio).setScale(2, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
