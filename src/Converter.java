import java.util.HashMap;

public class Converter
{
    // Создание карт для конвертации римских и арабских чисел

    String[][] romDigit = {{"I", "V", "X"}, {"X", "L", "C"}, {"C", "D", "M"}};// Заполнение массива с ее объявлением

    HashMap<Character, Integer> romanKeyMap = new HashMap<>(); // хранит много пар ключ-значение

    public Converter()
    {

        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);

    }


    public boolean isRoman(String number)
    {
        // Этот метод принимает строчку и проверяет ее наличие в key map

        /* for (char h : number.toCharArray())
        {
            if (!romanKeyMap.containsKey(h))
                return false;

        }
        return !number.isBlank();
        */
        return number.matches("^[IVXLC]+$");// проверка совпадения строки от начала^ до конца$
        // символ в квадратных скобках любой символ из перчисленных
        // + обозначает от одного и до бесконечнности

    }

    // Конвертирование в рим цифры.
    public String convertToRoman(int arabianNumber)
    {
//        примечания к старому коду который не используется в данный момент.
//        String roman = "";
//        // Строчка сохранения результирующего римского числа
//        int arabianKey;
//        do
//        {
//            arabianKey = arabianKeyMap.floorKey(number);
//            // Метод floorKey ищет наиб. близкий ключ к введенному чилсу
//            roman += arabianKeyMap.get(arabianKey);
//            // Конвертация в арабское число через arabianKeyMap
//            number -= arabianKey;
//        } while (number != 0);
//        return roman;     i
        if (arabianNumber < 1)
        {
            throw new IllegalArgumentException( "Вы желаете странного");
        }
        String result = ""; // копилка значений  
        char[] razr = String.valueOf(arabianNumber).toCharArray(); // Число разбитые на цифры
        for (var r = 0; r < razr.length; r++)
        {
            String I = String.valueOf(romDigit[r][0]);  // содержится символ для единиц для данного разряда 
            String V = String.valueOf(romDigit[r][1]); // содержится символ для пятерок для данного разряда
            String X = String.valueOf(romDigit[r][2]); // содержится символ для десяток для данного разряда
            result = switch (razr[razr.length - r - 1])
                    {
                        case '1' -> I;
                        case '2' -> I + I;
                        case '3' -> I + I + I;
                        case '4' -> I + V;
                        case '5' -> V;
                        case '6' -> V + I;
                        case '7' -> V + I + I;
                        case '8' -> V + I + I + I;
                        case '9' -> I + X;
                        default -> "";
                    } + result;// к результату добавить очередной разряд слева,рим число наращивается слева result справа
        }
        return result;


    }

    /**
     * Конвертация римских в арабские числа
     *
     * @param romanNumber число в римской нотации
     * @return - целое число
     */

    public int convertToArabian(String romanNumber)
    {
        // Защита от некоректных знач.
        if (romanNumber.isBlank())
        {
            return 0;
        }

        int end = romanNumber.length() - 1; //  хранит последний индекс по которому можно обратиться
        char[] roman = romanNumber.toCharArray(); // делит римское число на составляющие символы
        int[] arab = new int[romanNumber.length()]; // цифровые аналоги для римских символов
        for (int i = 0; i < roman.length; i++)
        {
            arab[i] = romanKeyMap.get(roman[i]);// заполнение цифровых аналогов
        }


        int result = arab[end]; // берем в качетстве начального значения для результата
        for (int i = end - 1; i >= 0; i--)
        {
            // цикл считывания справа налево по строке
            // самое крайнее правое уже учтено в результате, поэтому end - 1
            if (arab[i] < arab[i + 1]) // Обнаружение ситуации с четыре и девять
            {
                result -= arab[i];
            } else
            {
                result += arab[i];
            }


        }

        return result;

    }

    public int parseString(String value)
    {
        if (value.isBlank())
            return 0;
        return isRoman(value) ? convertToArabian(value) : Integer.parseInt(value);
        // Превращение строкового числа в int
        // поддерживает любую нотацию.


    }

}
