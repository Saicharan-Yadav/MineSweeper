import java.util.*;

class ChoiceTaking {
    public void revealRemaining(int[][] arrClone, ArrayList<pair> pairArr, int row, int col, boolean flag) {
        Stack<pair> st = new Stack<>();

    }

    public void RevealPrevious(int[][] arrClone, ArrayList<pair> pairArr, int row, int col, boolean flag) {
        // flag true means reveal all mines
        // flase means reveal all the previous choices and current choices
        if (arrClone[row][col] == 0) {
            revealRemaining(arrClone, pairArr, row, col, flag);
        }

    }
}
