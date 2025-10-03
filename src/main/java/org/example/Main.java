package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
    }

    //1. Средний элемент
    public static void middleElement() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strNums = reader.readLine().split(" ");

        int[] intNums = new int[strNums.length];

        for (int i = 0; i < strNums.length; i++) {
            intNums[i] = Integer.parseInt(strNums[i]);
        }
        Arrays.sort(intNums);
        writer.write(String.valueOf(intNums[1]));

        reader.close();
        writer.close();
    }

    //2. Самый дешевый путь
    public static void cheapestWay() throws IOException {
        int n, m;
        int[][] matrix;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] strNums = line.split(" ");

            n = Integer.parseInt(strNums[0]);
            m = Integer.parseInt(strNums[1]);

            matrix = new int[n][m];

            for (int j = 0; j < n; j++) {
                line = reader.readLine();
                strNums = line.split(" ");
                for (int i = 0; i < m; i++) {
                    matrix[j][i] = Integer.parseInt(strNums[i]);
                }
            }
        }

        for (int i = 1; i < n; i++) {
            matrix[i][0] += matrix[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            matrix[0][i] += matrix[0][i - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i][j - 1]);
            }
        }

        System.out.println(matrix[n-1][m-1]);
    }

    //3. Вывести маршрут максимальной стоимости
    public static void maxCostRoad() throws IOException {
        int n, m;
        int[][] matrix;
        String[][] path;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] strNums = line.split(" ");

            n = Integer.parseInt(strNums[0]);
            m = Integer.parseInt(strNums[1]);

            matrix = new int[n][m];
            path = new String[n][m];

            for (int j = 0; j < n; j++) {
                line = reader.readLine();
                strNums = line.split(" ");
                for (int i = 0; i < m; i++) {
                    matrix[j][i] = Integer.parseInt(strNums[i]);
                }
            }
        }
        path[0][0] = "";

        for (int i = 1; i < n; i++) {
            matrix[i][0] += matrix[i - 1][0];
            path[i][0] = path[i - 1][0] + "D ";
        }

        for (int i = 1; i < m; i++) {
            matrix[0][i] += matrix[0][i - 1];
            path[0][i] = path[0][i - 1] + "R ";
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i - 1][j] > matrix[i][j - 1]) {
                    matrix[i][j] += matrix[i - 1][j];
                    path[i][j] = path[i - 1][j] + "D ";
                } else {
                    matrix[i][j] += matrix[i][j - 1];
                    path[i][j] = path[i][j - 1] + "R ";
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            writer.write(matrix[n-1][m-1] + "\n" + path[n-1][m-1]);
        }
    }

    //4. Ход конём
    public static void numberOfKnightSteps() throws IOException {
        int row, col;
        int[][] matrix;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] strNums = line.split(" ");

            row = Integer.parseInt(strNums[0]);
            col = Integer.parseInt(strNums[1]);
        }
        matrix = new int[row + 1][col + 1];
        matrix[1][1] = 1;
        matrix[0][1] = 0;
        matrix[1][0] = 0;

        for (int i = 2; i <= row; i++) {
            for (int j = 2; j <= col; j++) {
                matrix[i][j] = matrix[i - 2][j - 1] + matrix[i - 1][j - 2];
            }
        }
        System.out.println(matrix[row][col]);
    }

    //155. Уникальные элементы
    public static void uniqueElements() throws IOException {
        Map<String, Integer> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            reader.readLine();
            String[] strNums = reader.readLine().split(" ");
            for (String strNum : strNums) {
                map.put(strNum, map.getOrDefault(strNum, 0) + 1);
            }
        }
        System.out.println(map.values().stream()
                .filter(value -> value == 1)
                .count()
        );
    }
}