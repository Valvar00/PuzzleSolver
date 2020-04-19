import javax.swing.*;
import java.io.*;

public class PuzzleSolver {
    private static String puzzleFile = "puzzle0";
    private static String wordsFile = "words0";
    private static String[][] puzzle = new String[3][4];
    private static String[] words = new String[6];
    private static String[][] splitWords = new String[6][4];
    private static int rows = 3;
    private static int cols = 4;
    private static int smallestWordLength = 100;

    public static void main(String[] args) throws Exception {
        ReadPuzzle();
        printPuzzle(puzzle,rows,cols);
        ReadWords();
        splitWords();
        findSmallest();
        Loop(0);
        System.out.println();
        printPuzzle(puzzle,rows,cols);
    }

    public static boolean Loop(int i1){
        if(i1!=2){
            for (String word: words) {
                if (word.length()==wordLengthRow(i1,0)){
                    insertWordRow(word,i1,0);
                }
                if(Loop(i1+1)==false){}else{
                    return true;
                }
            }
        }else{
            for (String word: words) {
                if (word.length()==wordLengthRow(i1,0)){
                    insertWordRow(word,i1,0);
                }
                if(puzzleFilled()){
                    return true;
                }
            }
        }
        return false;
    }

    public static void findSmallest(){
        for (String word: words) {
            if(word.length()<smallestWordLength){
                smallestWordLength=word.length();
            }
        }
    }

    private static boolean puzzleFilled(){
        for (int j = 0; j < cols; j++) {
            String check_word = new String();
            int col_len = wordLengthCol(0,j);
            if(col_len>=smallestWordLength){
                for (int i = 0; i < rows; i++) {
                    check_word+=puzzle[i][j];
                }
                int ch=0;
                for (String word: words) {
                    if(word.length()==check_word.length()){
                        if (word.equals(check_word)){
                            ch=1;
                            break;
                        }
                    }
                }
                if (ch == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void insertWordRow(String word,int i1,int j1){
        int j=0;
        for(String ch: word.split("(?!^)")) {
            puzzle[i1][j] = ch;
            j++;
        }
    }

    public static int wordLengthRow(int i1,int j1){
        int len = 0;
        for (int j = j1; j < cols; j++) {
            if (puzzle[i1][j].equals("#")){
                return len;
            }
            len++;
        }
        return len;
    }

    public static int wordLengthCol(int i1,int j1){
        int len = 0;
        for (int i = i1; i < rows; i++) {
            if (puzzle[i][j1].equals("#")){
                return len;
            }
            len++;
        }
        return len;
    }

    public static void printPuzzle(String[][] pzl,int i1, int j1){
        for (int i = 0; i < i1; i++) {
            for (int j = 0; j < j1; j++) {
                System.out.print(pzl[i][j]);
            }
            System.out.println();
        }
    }

    public static void ReadWords() throws Exception{
        File file = new File("D:\\university\\ItAI\\Puzzle_Solver_CSP\\Jolka\\"+wordsFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int i=0;
        while ((st = br.readLine()) != null){
            words[i] = st;
            i++;
        }
    }

    public static void splitWords(){
        int i=0;
        for (String word: words) {
            int j=0;
            for (String ch: word.split("(?!^)")){
                splitWords[i][j] = ch;
                j++;
            }
            i++;
        }
    }

    public static void insertWordCol(String word,int i1,int j1){
        int i=0;
        for(String ch: word.split("(?!^)")) {
            puzzle[i][j1] = ch;
            i++;
        }
    }

    public static void ReadPuzzle() throws IOException {
        File file = new File("D:\\university\\ItAI\\Puzzle_Solver_CSP\\Jolka\\"+puzzleFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int i = 0;
        while ((st = br.readLine()) != null){
            int j=0;
            for (String ch: st.split("(?!^)")) {
                puzzle[i][j] = ch;
                j++;
            }
            i++;
        }
    }
}
