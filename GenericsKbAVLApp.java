import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GenericsKbAVLApp {


        static String action;

        /**
         * The main entry point of the application. It provides a menu-driven interface
         * for loading, adding, searching, and quitting interaction with the knowledge base.
         *
         * @param args Command-line arguments (not used in this program)
         * @throws FileNotFoundException If the specified knowledge base file is not found.
         */
        public static void main(String[] args) throws IOException {

            ActionsAVL.randomGen();

            final Scanner keyboard = new Scanner(System.in);

            System.out.println("Choose an action from the menu:\n" +
                    "1. Load a knowledge base from a file\n" +
                    "2. Load in a query base from a file\n" +
                    "3. Quit\n" +
                    "Enter your choice: ");

            action = keyboard.next();
            keyboard.nextLine();






            while (true) {
                while (true) {
                    try {
                        Integer.parseInt(action);
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                        System.out.println("Choose an action from the menu:\n" +
                                "1. Load a knowledge base from a file\n" +
                                "2. Load in a query base from a file\n" +
                                "3. Quit\n" +
                                "Enter your choice: ");
                        action = keyboard.next();
                        keyboard.nextLine();
                    }
                }


                switch (Integer.parseInt(action)) {
                    case 1:
                        System.out.println("Enter file name: ");
                        String filename = keyboard.nextLine();
                        ActionsAVL.loadKbAVL(filename);
                        break;
                    case 2:
                        System.out.println("Enter the file name: ");
                        String f = keyboard.nextLine();
                        ActionsAVL.queryAVL(f);
                        break;
                    case 3:
                        System.out.println(AVLTree.maxInsertCounter + " max insertion operations performed.");
                        System.out.println(AVLTree.minInsertCounter + " min insertion operations performed.");
                        System.out.println(AVLTree.sumInsert + " sum insertion operations performed.");
                        System.out.println(AVLTree.maxSearchCounter + " max search operations performed.");
                        System.out.println(AVLTree.minSearchCounter+ " min search operations performed.");
                        int sum = 0;
                        for (int i =0; i<AVLTree.counterList.size(); i++){
                            sum += AVLTree.counterList.get(i);
                        }
                        double average = sum/AVLTree.counterList.size();
                        System.out.println(average + " average search operations performed.");


                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter a number between 1-3 and try again: ");
                        break;
                }

                System.out.println("Choose an action from the menu:\n" +
                        "1. Load a knowledge base from a file\n" +
                        "2. Load in a query base from a file\n" +
                        "3. Quit\n" +
                        "Enter your choice: ");
                action = keyboard.next();
                keyboard.nextLine();
            }

        }
}
