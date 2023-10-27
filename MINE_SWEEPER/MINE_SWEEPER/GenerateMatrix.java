// package MINE_SWEEPER;

import java.util.*;

class GenerateMatrix {
    int n;
    int mines;

    GenerateMatrix(int n, int mines) {
        this.n = n;
        this.mines = mines;
    }

    public boolean[][] genShowMatrix(int n) {
        boolean arr[][] = new boolean[n][n];
        return arr;
    }

    public int[][] genMatrix(int n1, int mines1) {
        int n = n1;
        int mines = mines1;

        int arr[][] = new int[n][n];

        Set<String> s = new HashSet<>(mines);
        Random rand = new Random();

        while (s.size() < mines) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);
            String s1 = x + "," + y;
            s.add(s1);
        }

        for (String s1 : s) {
            String sarr[] = s1.split(",");
            int i = Integer.parseInt(sarr[0]);
            int j = Integer.parseInt(sarr[1]);
            arr[i][j] = 9;
        }

        return arr;
    }

    public void IncreaseCountOfSurroundings(int[][] arr, int i, int j) {
        int n = arr.length;

        int rows[] = { -1, 0, 1 };
        int cols[] = { -1, 0, 1 };
        for (int i1 = 0; i1 < 3; i1++) {
            for (int j1 = 0; j1 < 3; j1++) {
                if (i1 == 1 && j1 == 1)
                    continue;
                else {
                    if (i + rows[i1] >= 0 && i + rows[i1] < n && j + cols[j1] >= 0 && j + cols[j1] < n)
                        if (arr[i + rows[i1]][j + cols[j1]] != 9)
                            arr[i + rows[i1]][j + cols[j1]]++;
                }
            }
        }
    }

    public void validate(int n, int mines, int arr[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 9) {
                    IncreaseCountOfSurroundings(arr, i, j);
                }
            }
        }
    }

    public void printArray(int[][] arr, boolean[][] disparr, Set<String> pairArr, boolean flag, int row, int col) {
        if (arr[row][col] == 0) {

        }
        if (flag == true) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] == 9)
                        pairArr.add(i + "," + j);
                }
            }
        }
        for (String s : pairArr) {
            String sarr[] = s.split(",");
            int i = Integer.parseInt(sarr[0]);
            int j = Integer.parseInt(sarr[1]);
            disparr[i][j] = true;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(disparr[i][j] ? arr[i][j] + " " : "* ");
            }
            System.out.println();
        }

    }

    // public static void main(String[] args) {
    // int n = 9;
    // int mines = 20;

    // int arr[][] = genMatrix(n, mines);

    // validate(n, mines, arr);

    // }
}
