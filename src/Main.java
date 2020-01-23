import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String code1 = new String(Files.readAllBytes(Paths.get("C:\\Users\\Emerson Tiu\\Desktop\\Directory\\test_program1.cpp")));
        String code2 = new String(Files.readAllBytes(Paths.get("C:\\Users\\Emerson Tiu\\Desktop\\Directory\\test_program2.cpp")));

        code1=code1.replaceAll("\\s","");
        code2=code2.replaceAll("\\s","");

        System.out.println("CODE 1:"+code1);
        System.out.println("CODE 2:"+code2);

        CompareCode comp = new CompareCode();

        comp.compare(code1,code2);

        System.out.println(comp.checkPoint(code1,code2));

    }
}
