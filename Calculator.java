import java.util.Scanner;

import static java.lang.System.out;

/*
    Обязательное требование: не вводить числа в строковых типах('' || "")
    Результат иных действий в отклонении от требования приведет:
    1) Не корректному выражению
    2) К исключению, пример: (NumberFormatException.java:67)
 */

public class Calculator
{
    public static void main(String[] args)
    {
        Converter converter = new Converter();// создание класса имеющего методы конвертации в рим. числа и араб. числа


        // Запрос и нормализация
        Scanner scn = new Scanner(System.in);
        out.print("Введите выражение: ");
        String exp = scn.nextLine()
                .toUpperCase() // приводит к верхнему регистру
                .replaceAll(" ", "");// Заменяет все пробелы


        // Определяется арифметическое действие
        String operator = "";

        String[] actions = {"+", "-", "/", "*"};// массив доступных операторов
        // Поочередно ищет каждый из операторов в строке
        for (String action : actions)
        {
            // Если оператор быдл в выражении, то запомнить символ этого оператора (+ или - записать в отдельную переменную)
            if (exp.contains(action))
            {

                operator = action;
                break;
            }
        }
        // Проверка цикла действий


        // Строчка делится по найденному арифметическому знаку

        // Деление строки по символу разделителю
        // С помощью этого метода строчка делится на любой символ
        // В результате вернется массив разделенных строчек
        String[] data = exp.split("[-+*/]"); // строковое представление операндов.
        int[] operands = new int[data.length]; // цифровое представление операндов.
        for (int i = 0; i < data.length; i++)
        {
            operands[i] = converter.parseString(data[i]); // перегон из строки в цифру
        }


        boolean isRomanExpression = converter.isRoman(data[0]); // Определяется, "Римские" ли это числа или "Арабские"

        // если хотя бы одно из ограничений нарушается то сообщение о завершении работы
        if (operands[0] < 1 || operands[0] > 10 || operands[1] < 1 || operands[1] > 10)
        {
            out.println("Разрешен диапозон от одного до десяти включительно");
            return;
        }

        // если хотя бы одно из ограничений нарушается то сообщение о завершении работы
        if (converter.isRoman(data[0]) != converter.isRoman(data[1]))
        {
            out.println("Калькулятор не работает с числами разного формата");
            return;
        }

        // если хотя бы одно из ограничений нарушается то сообщение о завершении работы
        if (data.length != 2)
        {
            out.println("Поддерживается только два операнда");

            return;
        }

        // Выполнение арифметического действия с числами
        int result = switch (operator)
                {
                    case "+" -> operands[0] + operands[1];
                    case "-" -> operands[0] - operands[1];
                    case "*" -> operands[0] * operands[1];
                    default -> operands[0] / operands[1];
                };

        // Тернарный опертор - "тройственный"
        // = Условие? результат если да: результат если нет.

        String resultString = isRomanExpression ? converter.convertToRoman(result) : String.valueOf(result);
        out.print("Ваше желание исполнено: " + resultString);

    }
}

