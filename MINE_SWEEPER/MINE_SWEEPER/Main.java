// package MINE_SWEEPER;

import java.util.*;

class pair {
    int row, col;

    pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    public static int[][] revertArr(int[][] mainArr, int arr[][]) {
        int n = arr.length;
        arr = Arrays.copyOf(mainArr, mainArr.length);
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.copyOf(mainArr[i], mainArr[i].length);
        }
        return arr;
    }

    public static boolean[][] revertArr(boolean[][] mainArr) {
        int n = mainArr.length;
        mainArr = new boolean[n][n];
        return mainArr;
    }

    public static void main(String[] args) {

        Set<String> pairArr = new HashSet<>();

        Scanner sc = new Scanner(System.in);

        boolean play = true;
        int n = 9;
        int mines = 10;

        GenerateMatrix gm = new GenerateMatrix(n, mines);

        // ChoiceTaking ct = new ChoiceTaking();

        int arr[][] = gm.genMatrix(n, mines);
        gm.validate(n, mines, arr);
        boolean disparr[][] = gm.genShowMatrix(mines);

        // Deep Cloning
        int arrClone[][] = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < n; i++) {
            arrClone[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        // gm.printArray(arr, disparr,pairArr,false,row,col);
        System.out.println();
        // gm.printArray(arrClone, disparr, pairArr, false, 0, 0);

        while (play) {

            System.out.println("enter row,col values seperately");
            int row = sc.nextInt();
            int col = sc.nextInt();
            // System.out.println(row + " " + col);

            String selection = row + "," + col;
            // Store The users input in set
            pairArr.add(selection);
            disparr[row][col] = true;

            if (arrClone[row][col] == 9) {

                gm.printall(arrClone, disparr, pairArr);

                System.out.println("Better Luck NextTime");

                play = false;

                System.out.println("Play Again Enter Command yes or no");
                String input = sc.next();
                if (input.charAt(0) != 'n' && input.charAt(0) != 'N') {
                    play = true;
                    // again to initial array
                    arrClone = revertArr(arr, arrClone);
                    disparr = revertArr(disparr);
                    pairArr.clear();
                } else {
                    break;
                }
            }

            else {
                gm.printArray(arrClone, disparr, pairArr, row, col);
            }

            if (pairArr.size() == n * n) {
                System.out.println("Hooray you Have Won");
                play = false;
                System.exit(0);
            }

        }
        System.out.println("exit");

    }
}
