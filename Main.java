package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String REGEX = "[, \\]\\[]";

    public static void main(String[] args) throws IOException {
        System.out.println("1. parametr (povinný) je celé kladné číslo N nebo cesta k zdrojovému souboru s čísly, 2. parametr (volitelný) určuje cestu k souboru do kterého se uloží výsledek)");
        System.out.println("Příklad 1:\njava -jar app.jar \ninput.txt output.txt");
        System.out.println("Příklad 2:\njava -jar app.jar \n1234567 output.txt");
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();
        // Přečte standardní vstup a případně rozdělí na argumenty.
        // inputs[0] = první argument, inputs[1] = druhý argument.
        String[] inputs = input.split("\\s+");
        String onlyOdd;
        String onlyEven;
        
        File file = new File(inputs[0]);
        // Pokud je první argument cesta k souboru..
        if (file.exists()) {
            inputs[0] = readFile(inputs);
            if (inputs[0].split("").length % 2 == 0) {
                onlyEven = isEven(inputs);
                writeFileEven(inputs, onlyEven);
            } else if (inputs[0].split("").length % 2 == 1) {
                onlyOdd = isOdd(inputs);
                writeFileOdd(inputs, onlyOdd);
            }
        // Pokud není musí to být celé číslo..
        } else {
            if (inputs[0].split("").length % 2 == 0) {
                onlyEven = isEven(inputs);
                writeFileEven(inputs, onlyEven);
            } else {
                onlyOdd = isOdd(inputs);
                writeFileOdd(inputs, onlyOdd);
            }
        }
    }
    
    public static String readFile(String[] input) throws IOException {
        File file = new File(input[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }
    
    public static String isEven(String[] nums) {
        List<Integer> even = new ArrayList<>();
        String[] numbers = nums[0].split("");

        for (int i = 0; i < numbers.length; i ++) {
            if (Integer.parseInt(numbers[i]) % 2 == 0) {
                even.add(Integer.parseInt(numbers[i]));
            }
        }
        return even.toString().replaceAll(REGEX, "");
    }
    
    public static String isOdd(String[] nums){
        List<Integer> odd = new ArrayList<>();
        String[] numbers = nums[0].split("");
        for (int i = 0; i < numbers.length; i++) {
            // Loop okolo čísel 1. argumentu. Pokud je číslo liché, přidá se do Listu odd.
            if (Integer.parseInt(numbers[i]) % 2 == 1) {
                odd.add(Integer.valueOf(numbers[i]));
            }
        }
        return odd.toString().replaceAll(REGEX, "");
    }

    static void writeFileOdd(String[] location, String onlyOdd) {
        // Pouze pokud existuje druhý argument se vytvoří soubor s touto lokací.
        if (location.length > 1 && location[1] != null) {
            try {
                File file = new File(location[1]);

                if (file.createNewFile()) {
                    FileWriter myWriter = new FileWriter(file);
                    myWriter.write(onlyOdd);
                    System.out.println("Soubor úspěšně vytvořen: " + file.getAbsolutePath());
                    System.out.println("Byla vyfiltrována následující lichá čísla: " + onlyOdd);
                    myWriter.close();
                } else {
                    System.out.println("Soubor již existuje");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Byla vyfiltrována následující lichá čísla: " + onlyOdd);
        }
    }

    static void writeFileEven(String[] location, String onlyEven) {
        if (location.length > 1 && location[1] != null) {
            try {
                File file = new File(location[1]);

                if (file.createNewFile()) {
                    FileWriter myWriter = new FileWriter(file);
                    myWriter.write(onlyEven);
                    System.out.println("Soubor úspěšně vytvořen: " + file.getAbsolutePath());
                    System.out.println("Byla vyfiltrována následující sudá čísla: " + onlyEven);

                    myWriter.close();
                } else {
                    System.out.println("Soubor již existuje");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Byla vyfiltrována následující sudá čísla: " + onlyEven);
            System.out.println(onlyEven);
        }
    }
}
