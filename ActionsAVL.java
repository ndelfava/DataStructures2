import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionsAVL {

    /**
     *  Represents a binary tree to store the knowledge base records.
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
     * Adds a new statement to the knowledge base, or updates an existing statement
     * if the new statement has higher confidence.
     *
     * @param term       The term associated with the statement.
     * @param statement  The statement to be added or updated.
     * @param confidence The confidence score associated with the statement.
     */
    public static void addStatement(String term, String statement, double confidence) {
        if (records.search(records.root, term) != null && confidence >= records.search(records.root, term).confidence) {
            records.search(records.root, term).statement = statement;
            records.search(records.root, term).confidence = confidence;
        }
        else if (records.search(records.root, term) != null && confidence < records.search(records.root, term).confidence){
            System.out.println("Term with higher confidence already in database.");
        }
        else {
            records.insert(term, statement, confidence);
            System.out.println("Added.");
        }
    }

    /**
     * Searches the knowledge base for a given term and prints the associated statement
     * and confidence level, if found.
     *
     * @param t The term to search for.
     */
    public static void searchByTerm(String t) {
        AVLNode n = records.search(records.root, t);
        if (n == null) {
            System.out.println("Nothing found.");
        } else {
            System.out.println("Statement found: " + n.statement + " (Confidence level: " + n.confidence + ")");
        }
    }

    /**
     * Searches the knowledge base for a given term and checks if the associated statement
     * matches the provided statement. If found, it prints a confirmation message and the confidence.
     *
     * @param term      The term to search for.
     * @param statement The statement to match against the term.
     */
    public static void termAndStatement(String term, String statement) {
        AVLNode n = records.search(records.root, term);
        if (n == null) {
            System.out.println("Nothing found.");
        } else {
            if (n.statement.toLowerCase().equals(statement)) {
                System.out.println("The statement was found and has a confidence score of " + n.confidence + ".");
            }
        }
    }

}
