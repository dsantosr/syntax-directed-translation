package br.ufma;

public class Parser {
    private Scanner scanner;

    public Parser(byte[] input) {
        this.scanner = new Scanner(input);
    }

    public void parse() {
        expr();
    }

    private void expr() {
        digit();
        oper();
    }

    private void digit() {
        if (Character.isDigit(scanner.peek())) {
            System.out.println("push " + scanner.peek());
            scanner.match(scanner.peek());
        } else {
            throw new Error("syntax error");
        }
    }

    private void oper() {
        if (scanner.peek() == '+') {
            scanner.match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (scanner.peek() == '-') {
            scanner.match('-');
            digit();
            System.out.println("sub");
            oper();
        }
    }
} 