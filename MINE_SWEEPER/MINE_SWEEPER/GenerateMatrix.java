// package MINE_SWEEPER;

import java.util.*;

import javax.print.DocFlavor.STRING;

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

    public void printall(int[][] arr, boolean[][] disparr, Set<String> pairArr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 9) {
                    pairArr.add(i + "," + j);
                    disparr[i][j] = true;
                }
            }
        }
        printArray(arr, disparr, pairArr);
    }

    public void printArray(int[][] arr, boolean[][] disparr, Set<String> pairArr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(disparr[i][j] ? arr[i][j] + " " : "* ");
            }
            System.out.println();
        }
    }

    public void printArray(int[][] arr, boolean[][] disparr, Set<String> pairArr, int row, int col) {
        Stack<String> st = new Stack<>();
        Stack<String> rep = new Stack<>();
        st.add(row + "," + col);
        if (arr[row][col] == 0) {
            System.out.println("0 encountered");
            while (!st.isEmpty()) {
                String curr = st.pop();
                rep.add(curr);
                String sarr[] = curr.split(",");
                int i = Integer.parseInt(sarr[0]);
                int j = Integer.parseInt(sarr[1]);

                int n = arr.length;
                int rows[] = { -1, 0, 1 };
                int cols[] = { -1, 0, 1 };
                for (int i1 = 0; i1 < 3; i1++) {
                    for (int j1 = 0; j1 < 3; j1++) {
                        if (i1 == 1 && j1 == 1)
                            continue;
                        else {
                            if (i + rows[i1] >= 0 && i + rows[i1] < n && j + cols[j1] >= 0 && j + cols[j1] < n) {
                                int drow = i + rows[i1];
                                int dcol = j + cols[j1];
                                String dummy = drow + "," + dcol;

                                System.out.println("dummy called" + dummy);
                                if (arr[drow][dcol] == 0) {
                                    if (!rep.contains(dummy)) {
                                        System.out.println("true");
                                        st.add(dummy);
                                    }

                                }
                                pairArr.add(dummy);
                            }

                        }
                    }
                }
            }

        }
        // make the elements from pairArr to make disparr==true
        for (String s : pairArr) {
            String sarr[] = s.split(",");
            int srow = Integer.parseInt(sarr[0]);
            int scol = Integer.parseInt(sarr[1]);
            disparr[srow][scol] = true;
        }
        // print where disparr is true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(disparr[i][j] ? arr[i][j] + " " : "* ");
            }
            System.out.println();
        }

    }
    /**
     * public void printArray(int[][] arr, boolean[][] disparr, Set<String> pairArr,
     * boolean flag, int row, int col) {
     * Stack<String> st = new Stack<>();
     * st.push(row + "," + col);
     * if (arr[row][col] == 0) {
     * while (!st.isEmpty()) {
     * String stop = st.pop();
     * String sarr[] = stop.split(",");
     * // System.out.println(sarr);
     * System.out.println("**************************************");
     * for (int i = 0; i < arr.length; i++) {
     * for (int j = 0; j < arr[i].length; j++) {
     * System.out.print(arr[i][j] + " ");
     * }
     * System.out.println(); // Move to the next line after each row
     * }
     * System.out.println("**************************************");
     * 
     * int srow = Integer.parseInt(sarr[0]);
     * int scol = Integer.parseInt(sarr[1]);
     * 
     * int rows[] = { -1, 0, 1 };
     * int cols[] = { -1, 0, 1 };
     * for (int i1 = 0; i1 < 3; i1++) {
     * for (int j1 = 0; j1 < 3; j1++) {
     * if (i1 == 1 && j1 == 1)
     * continue;
     * else {
     * if (srow + rows[i1] >= 0 && srow + rows[i1] < n && scol + cols[j1] >= 0
     * && scol + cols[j1] < n) {
     * String dummy = srow + "" + rows[i1] + "," + scol + "" + cols[j1];
     * if (arr[srow + rows[i1]][scol + cols[j1]] == 9)
     * continue;
     * if (arr[srow + rows[i1]][scol + cols[j1]] == 0) {
     * st.push(dummy);
     * }
     * pairArr.add(dummy);
     * disparr[srow + rows[i1]][scol + cols[j1]] = true;
     * }
     * }
     * }
     * }
     * 
     * }
     * }
     * if (flag == true) {
     * for (int i = 0; i < arr.length; i++) {
     * for (int j = 0; j < arr.length; j++) {
     * if (arr[i][j] == 9)
     * pairArr.add(i + "," + j);
     * disparr[i][j] = true;
     * }
     * }
     * }
     * for (String s : pairArr) {
     * String sarr[] = s.split(",");
     * int i = Integer.parseInt(sarr[0]);
     * int j = Integer.parseInt(sarr[1]);
     * disparr[i][j] = true;
     * }
     * for (int i = 0; i < arr.length; i++) {
     * for (int j = 0; j < arr[i].length; j++) {
     * System.out.print(disparr[i][j] ? arr[i][j] + " " : "* ");
     * }
     * System.out.println();
     * }
     * 
     * }
     */

    /**
     * public static void main(String[] args) {
     * int n = 9;
     * int mines = 20;
     * 
     * int arr[][] = genMatrix(n, mines);
     * 
     * validate(n, mines, arr);
     * 
     * }
     */
}
