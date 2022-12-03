import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test
{
    public static void main(String[] args)
    {
        Map<String, String> testData = new HashMap<>()
        {{
            put("X+X", "XX");
            put("X+C", "CX");
        }};

        testData.forEach((input, expectedOutput) ->
        {
            String result;
            try
            {
                result = Main.calc(input);
            } catch (IllegalArgumentException exception)
            {
                result = exception.getMessage();
            }


            String details = "\ninput: \t\t" + input + "\nexpected: \t" + expectedOutput + "\nresult: \t" + result;

            if (Objects.equals(result, expectedOutput))
            {
                System.out.println(details);
            } else
            {
                System.err.println(details);
            }

        });


    }


}
