// package MINE_SWEEPER;

import java.util.*;

public class Main {
    public static int[][] revertArr(int[][] mainArr, int arr[][]) {
        int n = arr.length;
        arr = Arrays.copyOf(mainArr, mainArr.length);
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.copyOf(mainArr[i], mainArr[i].length);
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean play = true;
        int n = 9;
        int mines = 10;
        GenerateMatrix gm = new GenerateMatrix(n, mines);

        int arr[][] = gm.genMatrix(n, mines);
        gm.validate(n, mines, arr);

        // Deep Cloning
        int arrClone[][] = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < n; i++) {
            arrClone[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        gm.print(arr);
        System.out.println();
        gm.print(arrClone);

        while (play) {
            System.out.println("enter row,col values seperately");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (arrClone[row][col] == 9) {
                System.out.println("Better Luck NextTime");

                play = false;

                System.out.println("Play Again Enter Command yes or no");
                String input = sc.next();
                if (input.charAt(0) != 'n' && input.charAt(0) != 'N') {
                    play = true;
                    // again to initial array
                    arrClone = revertArr(arr, arrClone);
                } else {
                    break;
                }
            }

        }
        System.out.println("exit");

    }
}
