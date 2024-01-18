import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        System.out.println("------------------Welcome to Number Game Quizz------------------");
        System.out.println("There is one number you find between 0-100.");
        System.out.println("You have 5 attempts to guess.");
        System.out.println("You should guess the number in the minimum steps to find that number.");
        System.out.println("!!!Best of luck!!! ");
        System.out.println("-----------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int random = r.nextInt(100);
        // System.out.println(random);
        int count = 1;
        int i = 1;

        while (i <= 5) {
            try {
                System.out.println("Enter the number=");
                int input = sc.nextInt();

                if (input > random) {
                    System.out.println(input + " is more than the guess number");
                } else if (input < random) {
                    System.out.println(input + " is less than the guess number");
                } else {
                    System.out.println("Congratulations!! Your guess is correct. You took (" + count + " ) steps to guess");
                    break;
                }

                count++;
                i++;

                if (i > 5) {
                    System.out.println("Sorry!! You exceeded your limit. The number is =" + random);
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please start again");
                sc.nextLine();
            }
        }

        sc.close();
    }
}
