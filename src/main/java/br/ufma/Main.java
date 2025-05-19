package br.ufma;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Tradutor de Sentenças ===");
        System.out.println("Digite 'sair' para encerrar o programa");
        System.out.println("Digite uma sentença (statement -> printStatement | letStatement)");

        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("sair")) {
                running = false;
                continue;
            }

            if (input.isEmpty()) {
                System.out.println("Por favor, digite uma sentença válida.");
                continue;
            }

            try {
                System.out.println("\nTradução:");
                Parser parser = new Parser(input.getBytes());
                parser.parse();
                Interpretador i = new Interpretador (parser.output());
                i.run();
            } catch (Error e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        System.out.println("\nPrograma encerrado.");
        scanner.close();
    }
} 