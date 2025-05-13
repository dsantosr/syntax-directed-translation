package br.ufma;

public class Scanner {
    private byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
        this.current = 0;
    }

    public char peek() {
        if (current < input.length)
            return (char)input[current];
        return '\0';
    }

    public void match(char c) {
        if (c == peek()) {
            current++;
        } else {
            throw new Error("syntax error");
        }
    }
} 