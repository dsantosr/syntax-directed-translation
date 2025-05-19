package br.ufma;

public class Parser {
    private Scanner scan;
    private Token currentToken;
    private StringBuilder output;

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
        output = new StringBuilder();
    }

    private void nextToken() {
        currentToken = scan.nextToken();
    }

    private void match(TokenType type) {
        if (currentToken.type == type) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    public void parse() {
        statements();
    }

    public String output() {
        return output.toString();
    }

    void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    void statement() {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    void printStatement() {
        match(TokenType.PRINT);
        expr();
        output.append("print\n");
        match(TokenType.SEMICOLON);
    }

    void expr() {
        term();
        oper();
    }

    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();
            output.append("add\n");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            output.append("sub\n");
            oper();
        }
    }

    void term() {
        if (currentToken.type == TokenType.NUMBER)
            number();
        else if (currentToken.type == TokenType.IDENT) {
            output.append("push ").append(currentToken.lexeme).append("\n");
            match(TokenType.IDENT);
        }
        else
            throw new Error("syntax error");
    }

    void number() {
        output.append("push ").append(currentToken.lexeme).append("\n");
        match(TokenType.NUMBER);
    }

    void letStatement() {
        match(TokenType.LET);
        var id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        output.append("pop ").append(id).append("\n");
        match(TokenType.SEMICOLON);
    }
} 