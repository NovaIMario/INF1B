
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to TIC TAC TOE");
        char[][] grid = new char[3][3];
        boolean noWinner = true;
        int turn = 0;
        while(noWinner&&turn<9){
            System.out.println("*****************");
            System.out.println("*   "+Arrays.toString(grid[0])+"   *");
            System.out.println("*   "+Arrays.toString(grid[1])+"   *");
            System.out.println("*   "+Arrays.toString(grid[2])+"   *");
            System.out.println("*****************");
            char player;
            if(turn % 2 == 0){
                player = 'X';
            }
            else{
                player = 'Y';
            }
            System.out.println("You are player "+player);
            System.out.println("*****************");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter x-coordinate:");
            int x = scanner.nextInt();
            System.out.println("Enter y-coordinate:");
            int y = scanner.nextInt();
            boolean notValid = x>=3 || y>=3 || grid[x][y] != 0;
            while(notValid){
                System.out.println("The previous coordinates are not valid.");
                System.out.println("Enter x-coordinate:");
                x = scanner.nextInt();
                System.out.println("Enter y-coordinate:");
                y = scanner.nextInt();
                notValid = x>=3 || y>=3 || grid[x][y] != 0;
            }
            grid[x][y] = player;
            noWinner = checkNoWinner(grid);
            turn ++;
        }

        System.out.println("*****************");
        System.out.println("*   "+Arrays.toString(grid[0])+"   *");
        System.out.println("*   "+Arrays.toString(grid[1])+"   *");
        System.out.println("*   "+Arrays.toString(grid[2])+"   *");
        System.out.println("*****************");
        System.out.println("Nobody won! Both are LOSERS!!!");
        System.out.println("*****************");
    }
    public static boolean checkNoWinner(char[][] grid){
        if(grid[0][0]!=0 && grid[0][0]==grid[1][1] && grid[1][1]==grid[2][2]){
            System.out.println(grid[0][0]+" wins!");
            return false;
        }
        else if(grid[0][0]!=0 && grid[0][2]==grid[1][1] && grid[1][1]==grid[2][0]){
            System.out.println(grid[0][2]+" wins!");
            return false;
        }
        else {
            for(int i = 0; i<3; i++){
                if(grid[i][0]!=0 && grid[i][0]==grid[i][1] && grid[i][1]==grid[i][2]){
                    System.out.println(grid[i][2]+" wins!");
                    return false;
                }
                else if(grid[0][i]!=0 && grid[0][i]==grid[1][i] && grid[1][i]==grid[2][i]){
                    System.out.println(grid[2][i]+" wins!");
                    return false;
                }
            }

            return true;
        }
    }
}
