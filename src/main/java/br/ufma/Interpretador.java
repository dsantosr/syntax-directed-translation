package br.ufma;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

class Command {
    public enum Type {
        ADD, 
        SUB,
        PUSH,
        POP,
        PRINT
    }

    public Command.Type type;
    public String arg = "";

    public Command(String[] command) {
        type = Command.Type.valueOf(command[0].toUpperCase());
        if (command.length > 1) {
            arg = command[1].trim();
        }
    }

    public String toString() {
        return type.name() + " " + arg;
    }
}

public class Interpretador {
    List<String[]> commands;
    Stack<Integer> stack = new Stack<>();
    Map<String,Integer> variables = new HashMap<>();

    Interpretador(String input) {
        commands = Arrays.stream(input.split("\n"))
            .map(String::trim)
            .filter(s -> !s.isEmpty() && !s.startsWith("//"))
            .map(s -> s.split("\\s+"))
            .collect(Collectors.toList());
    }

    public boolean hasMoreCommands() {
        return !commands.isEmpty();
    }

    public Command nextCommand() {
        return new Command(commands.remove(0));
    }

    private void printState(String operation) {
        System.out.println("\nOperação: " + operation);
        System.out.println("Pilha: " + stack);
        System.out.println("Variáveis: " + variables);
    }

    public void run() {
        System.out.println("\n=== Iniciando interpretação ===");
        while (hasMoreCommands()) {
            var command = nextCommand();
            switch (command.type) {
                case ADD:
                    var arg2 = stack.pop();
                    var arg1 = stack.pop();
                    stack.push(arg1 + arg2);
                    printState("ADD: " + arg1 + " + " + arg2 + " = " + (arg1 + arg2));
                    break;
                case SUB:
                    arg2 = stack.pop();
                    arg1 = stack.pop();
                    stack.push(arg1 - arg2);
                    printState("SUB: " + arg1 + " - " + arg2 + " = " + (arg1 - arg2));
                    break;
                case PUSH:
                    var value = variables.get(command.arg);
                    if (value != null) {
                        stack.push(value);
                        printState("PUSH variável: " + command.arg + " = " + value);
                    } else {
                        stack.push(Integer.parseInt(command.arg));
                        printState("PUSH número: " + command.arg);
                    }
                    break;
                case POP:
                    value = stack.pop();
                    variables.put(command.arg, value);
                    printState("POP: " + command.arg + " = " + value);
                    break;
                case PRINT:
                    var arg = stack.pop();
                    System.out.println("\n=== Resultado do PRINT ===");
                    System.out.println(arg);
                    printState("PRINT");
                    break;
            }
        }
        System.out.println("\n=== Interpretação finalizada ===");
    }
} 