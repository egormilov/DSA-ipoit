package by.it.group410972.damarad.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {

    int[][] memo;

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length();
        int m = two.length();
        memo = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                memo[i][j] = -1;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return levDist(one, two, n, m);
    }

    int levDist(String one, String two, int i, int j) {
        // Если одна строка пустая, нужно вставить все символы другой строки
        if (i == 0) return j;
        if (j == 0) return i;

        if (memo[i][j] != -1) return memo[i][j];

        if (one.charAt(i - 1) == two.charAt(j - 1)) {
            // Последние символы совпадают - двигаемся дальше без операции
            memo[i][j] = levDist(one, two, i - 1, j - 1);
        } else {
            // Рассматриваем все операции и выбираем минимальную
            int insert = levDist(one, two, i, j - 1) + 1;
            int delete = levDist(one, two, i - 1, j) + 1;
            int replace = levDist(one, two, i - 1, j - 1) + 1;

            memo[i][j] = Math.min(insert, Math.min(delete, replace));
        }

        return memo[i][j];
    }
    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
