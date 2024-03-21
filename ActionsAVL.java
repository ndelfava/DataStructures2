import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class ActionsAVL {

    /**
     *  Represents an AVL tree to store the knowledge base records.
     */
    static AVLTree records;

    /**
     * Loads or updates the knowledge base from a tab-delimited text file.
     * If the knowledge base is empty, it adds all records. If not empty,
     * it updates existing terms with higher-confidence statements.
     *
     * @param filename The name of the file containing the knowledge base data.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void loadKbAVL(String filename) throws FileNotFoundException {
        File text = new File(filename);
        Scanner scnr = new Scanner(text);

        ArrayList<String> lines = new ArrayList<>();
        int numLines = 1;

        while (scnr.hasNextLine()) {
            lines.add(scnr.nextLine());
            numLines++;
        }

        if (records == null) {

            records = new AVLTree();

            for (int i = 0; i < lines.size(); i++) {
                String term = lines.get(i).split("\t")[0];
                String statement = lines.get(i).split("\t")[1];
                Double confidence = Double.valueOf(lines.get(i).split("\t")[2]);
                records.insert(term, statement, confidence);
            }
            System.out.println("Loadeddd");

        }
        else {
            for (int i = 0; i < lines.size(); i++) {
                String term = lines.get(i).split("\t")[0];
                String statement = lines.get(i).split("\t")[1];
                Double confidence = Double.valueOf(lines.get(i).split("\t")[2]);
                if ((records.search(records.root, term) != null) && confidence >= records.search(records.root,term).confidence)  {
                    records.search(records.root, term).statement = statement;
                    records.search(records.root, term).confidence = confidence;
                }
            }
            System.out.println("All items updated.");
        }

    }

    /**
     * Searches the knowledge base for a given term and prints the associated statement
     * and confidence level, if found.
     *
     * @param \t The term to search for.
     */
    public static void queryAVL(String filename) throws FileNotFoundException {
        File text = new File(filename);
        Scanner scnr = new Scanner(text);

        ArrayList<String> lines = new ArrayList<>();
        int numLines = 1;

        while (scnr.hasNextLine()) {
            lines.add(scnr.nextLine());
            numLines++;
        }

        for (int i = 0; i < lines.size(); i++) {
            String term = lines.get(i);
            if (records.search(records.root, term) != null) {
                System.out.println(term + ": " + records.search(records.root, term).statement + " (" + records.search(records.root, term).confidence + ")");
            }
            else {
                System.out.println("Term not found: " + term);
            }
        }
    }

    public static void randomGen() throws IOException {
        int[] n = new int[] {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};
        File text = new File("GenericsKB.txt");
        Scanner keyboard = new Scanner(text);

        ArrayList<String> lines = new ArrayList<>();
        int numLines = 1;
        while (keyboard.hasNextLine()) {
            lines.add(keyboard.nextLine());
            numLines++;
        }

        for (int i=0; i < 10; i++) {
            FileWriter myWriter = new FileWriter("test" + (i+1) + ".txt");
            for (int j=0; j < n[i]; j++) {
                int random = (int) (Math.random() * lines.size());
                myWriter.write(lines.get(random) + "\n");
            }
            myWriter.close();
        }




    }





}
