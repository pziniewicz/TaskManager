package pl.zini.zini;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskManager {

    static final String[] OPTIONS = {"add", "remove", "list"};
    static LinkedList<String> taskList = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadFile();
        while (true) {
            showMenu();
            String selection = scanner.nextLine();
            if (!selection.equals("exit")) {
                switch (selection) {
                    case "add" -> addTask();
                    case "remove" -> removeTask();
                    case "list" -> listPrint();
                }
            } else {
                writeFile(taskList);
                System.out.println(ConsoleColors.RED_BOLD + "Bye bye");
                break;
            }
        }
    }

    public static void showMenu() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Please select an option:");
        for (String option : OPTIONS) {
            System.out.println(ConsoleColors.RESET + option);
        }
    }

    public static void loadFile() {
        try {
            Scanner fileScanner = new Scanner(new File("tasks.csv"));
            while (fileScanner.hasNextLine()) {
                taskList.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeFile(LinkedList<String> taskList) {
        try (FileWriter fileWriter = new FileWriter("tasks.csv")) {
            for (String s : taskList) {
                fileWriter.append(s).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTask() {
        System.out.println("Please add task description");
        String taskDescription = scanner.nextLine();
        System.out.println("Please add task due date");
        String taskDueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String taskImportance = scanner.nextLine();
        taskList.add(taskDescription + ", " + taskDueDate + ", " + taskImportance);
    }

    public static void removeTask() {
        while (true) {
            System.out.println("Please select number to remove");
            try {
                int positionToRemove = scanner.nextInt();
                if (positionToRemove > 0) {
                    taskList.remove(positionToRemove - 1);
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Value was successfully removed: ");
        scanner.nextLine();
    }

    public static void listPrint() {
        System.out.println("list");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
}