package pl.zini.zini;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskManager {

    static LinkedList<String> taskList = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadFile();
        while (true) {
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Please select an option:");
            System.out.println(ConsoleColors.GREEN_BRIGHT + "add");
            System.out.println(ConsoleColors.RED_BRIGHT + "remove");
            System.out.println(ConsoleColors.BLUE_BRIGHT + "list");
            System.out.println(ConsoleColors.RESET + "exit");
            System.out.print("$ ");
            String selection = scanner.nextLine();
            if (selection.equals("exit")) {
                writeFile(taskList);
                System.out.println(ConsoleColors.RED_BOLD + "Bye bye");
                break;
            }
            switch (selection) {
                case "add":
                    add();
                    break;
                case "remove":
                    list();
                    remove();
                    break;
                case "list":
                    list();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
        }
    }

    public static LinkedList<String> loadFile() {
        try {
            Scanner fileScanner = new Scanner(new File("tasks.csv"));
            while (fileScanner.hasNextLine()) {
                taskList.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(taskList);
        return taskList;
    }

    public static void writeFile(LinkedList<String> taskList) {
        try {
            FileWriter fileWriter = new FileWriter("tasks.csv");
            for (String s : taskList) {
                fileWriter.append(s).append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void add() {
        System.out.println("Please add task description");
        String taskDescription = scanner.nextLine();
        System.out.println("Please add task due date");
        String taskDueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String taskImportance = scanner.nextLine();
        taskList.add(taskDescription + ", " + taskDueDate + ", " + taskImportance);
    }

    public static void remove() {
        System.out.println("Please select number to remove");
        try {
            int positionToRemove = scanner.nextInt();
            System.out.println("Value was succesfully removed: " + taskList.get(positionToRemove-1));
            taskList.remove(positionToRemove-1);
        } catch (Exception e) {
            System.out.println("Please select a correct option.");
        }
        scanner.nextLine();
    }

    public static void list() {
        System.out.println("list");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i+1)+ ". " + taskList.get(i));
        }
    }
}