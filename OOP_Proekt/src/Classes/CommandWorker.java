package Classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CommandWorker {
    private String fileName;

    public void menu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Grammar> grammars = new ArrayList<>();
        GrammarWorker worker = new GrammarWorker(grammars);
        FileOperations operations = new FileOperations(grammars);
        Rules rules = new Rules(grammars);
        LogicalOperations logicalOperations = new LogicalOperations(grammars);

        int choice = 0;
        while (choice != 16) {
            System.out.println("\n" + "Please select a method. To exit enter 16");

            String[] methods = {"Add Grammar", "ID List", "Print Grammar", "Save Grammar in file", "Add Rule", "Remove Rule", "Union", "Concat", "Empty",
                    "Kleene operation", "Open", "Close", "Save", "Save as", "Help", "Exit"};
            for (int i = 0; i < methods.length; i++) {
                System.out.println(i + 1 + ": " + methods[i]);
            }

            choice = parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    if (fileName != null) {
                    worker.addGrammar();
                    }
                    else {
                        System.out.println("You must first must open a file!");
                    }
                }
                break;
                case 2: {
                    if (fileName != null) {
                        System.out.println("List of IDs for grammars");
                        for (int id : worker.list()) {
                            System.out.println(id);
                        }
                    }
                    else {
                        System.out.println("You must first must open a file!");
                    }
                }
                break;
                case 3: {
                    if (fileName != null) {
                        System.out.println("Prints all grammars");
                        for (int id : worker.list()) {
                            System.out.println(grammars.get(id).toString());
                        }
                    } else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 4: {
                    if (fileName != null) {
                        ShowGrammarMenu(worker);
                        int id = parseInt(scanner.nextLine());
                        System.out.println("Enter filename to save");
                        String filename = scanner.nextLine();
                        operations.saveGrammar(id, filename);
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 5: {
                    if (fileName != null) {
                    ShowGrammarMenu(worker);
                    int id = parseInt(scanner.nextLine());
                    System.out.println("Please enter new rule");
                    String rule = scanner.nextLine();
                    rules.addRule(id, rule);
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 6: {
                    if (fileName != null) {
                        ShowGrammarMenu(worker);
                        int id = parseInt(scanner.nextLine());
                        System.out.println("Please enter index of rule to remove");
                        int index = parseInt(scanner.nextLine());
                        rules.removeRule(id, index);
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 7: {
                    if (fileName != null) {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter IDs to union");
                    int id1 = parseInt(scanner.nextLine());
                    System.out.println("Please enter IDs to union");
                    int id2 = parseInt(scanner.nextLine());
                    logicalOperations.union(id1, id2);
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 8: {
                    if (fileName != null) {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter IDs to concat");
                    int id1 = parseInt(scanner.nextLine());
                    System.out.println("Please enter IDs to concat");
                    int id2 = parseInt(scanner.nextLine());
                    logicalOperations.concat(id1, id2);
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 9: {
                    if (fileName != null) {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter id to check if empty");
                    int id = parseInt(scanner.nextLine());
                    System.out.println(worker.empty(id));
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 10: {
                    if(fileName != null) {
                    ShowGrammarMenu(worker);
                    System.out.println("Please enter id to make Kleene operation");
                    int id = parseInt(scanner.nextLine());
                    int index = logicalOperations.iter(id);
                    System.out.println(index);
                    System.out.println(grammars.get(index).toString());
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 11: {
                    if (fileName == null) {
                        System.out.println("Enter filename to open");
                        String fileName = scanner.nextLine();
                        operations.open(fileName);
                        this.fileName = fileName;
                    }
                    else {
                        System.out.println("You have opened a file");
                    }
                }
                break;
                case 12: {
                    if (fileName != null ) {
                        operations.close(fileName);
                        fileName=null;
                    }
                    else {
                        System.out.println("You must first open a file!");
                        }
                }
                break;
                case 13: {
                    if (fileName != null) {
                        System.out.println("Enter filename to save ");
                        String filename = scanner.nextLine();
                        operations.save(filename);
                    } else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 14: {
                    if (fileName != null) {
                    System.out.println("Please enter file directory: ");
                    String filename = scanner.nextLine();
                    operations.saveas(filename);
                    }
                    else {
                        System.out.println("You must first open a file!");
                    }
                }
                break;
                case 15: {
                    operations.help();
                }
                break;
                case 16: {
                    operations.exit();
                }
                break;
            }
        }
    }

    public static void ShowGrammarMenu(GrammarWorker worker) {
        System.out.println("Please select a grammar");
        ArrayList<Grammar> grammars = worker.getGrammars();
        for (int id : worker.list()) {
            System.out.println(grammars.get(id).toString());
        }
    }
}
