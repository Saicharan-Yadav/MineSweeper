// package mines;
// package MINE_SWEEPER;

import java.util.*;

class GenerateMatrix {
    int n;
    int mines;

    // constructor
    GenerateMatrix(int n, int mines) {
        this.n = n;
        this.mines = mines;
    }

    // done
    public boolean[][] genShowMatrix(int n) {
        boolean arr[][] = new boolean[n][n];
        return arr;
    }

    // done
    public int[][] genMatrix(int n1, int mines1) {
        int n = n1;
        int mines = mines1;

        int arr[][] = new int[n][n];

        Set<String> s = new HashSet<>(mines);
        Random rand = new Random();

        while (s.size() < mines) {
            int x = rand.nextInt(n);
            int y = rand.nextInt(n);
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

    // done
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

    // done
    public void validate(int n, int mines, int arr[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 9) {
                    IncreaseCountOfSurroundings(arr, i, j);
                }
            }
        }
    }

    // done
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

    // Revisit for styling
    public void printArray(int[][] arr, boolean[][] disparr, Set<String> pairArr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(disparr[i][j] ? arr[i][j] + " " : "* ");
            }
            System.out.println();
        }
    }

    // Done
    public void printArray(int[][] arr, boolean[][] disparr, Set<String> pairArr, int row, int col) {
        System.out.println(pairArr);
        Stack<String> st = new Stack<>();
        Stack<String> rep = new Stack<>();
        st.add(row + "," + col);
        if (arr[row][col] == 0) {

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

}
