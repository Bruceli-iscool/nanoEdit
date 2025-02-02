package dev.desktop;
import java.io.*;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        System.out.println("NanoEdit v0.1");
        System.out.println("An inconvenient toy editor for playing around with.");
        System.out.println("-h for help. -l for license");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("cmd>");
            String in = scanner.nextLine();
            if (in.replaceAll("\\s+", "").equals("-q")) {
                break;
            } else {
                interpret(in);
            }
        }
    }

    static void interpret(String cmd) {
        cmd = cmd.replaceAll("\\s+", "");
        switch (cmd) {
            case "-h" -> doc.help();
            case "-l" ->
                    System.out.println("nanoEdit is licensed under the BSD-3 Clause License\nNO WARRANTY OF ANY KIND IS PROVIDED!");
            case "-i" -> edit();
        }
    }

    static void edit() {
        System.out.println("Entering editing mode....");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter File Path> ");
        String path = scanner.nextLine();
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("nanoEdit-File does not exist! Creating new file.");
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("nanoEdit-File not readable! Starting with empty file!");
        }
        System.out.print(content);
        System.out.print("edit> ");
        while (true) {
            String in = scanner.nextLine();
            if (in.replaceAll("\\s+", "").equals("-qws")) {
                System.out.println("Exiting edit mode...");
                break;
            } else if (in.replaceAll("\\s+", "").equals("-qs")) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(content.toString());
                    System.out.println("nanoEdit-Saved File!");
                    break;
                } catch (IOException e) {
                    System.out.println("nanoEdit-Error Saving File!");
                    break;
                }
            }else {
                content.append(in).append("\n");
            }
            }

        }
    }
