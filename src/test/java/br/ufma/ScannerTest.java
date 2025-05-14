package br.ufma;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ScannerTest {
    
    @Test
    public void testSimpleAddition() {
        String input = "8+5";
        String expected = "<NUMBER>8</NUMBER>\n<PLUS>+</PLUS>\n<NUMBER>5</NUMBER>\n<EOF>EOF</EOF>\n";
        
        Scanner scanner = new Scanner(input.getBytes());
        StringBuilder output = new StringBuilder();
        Token token;
        while ((token = scanner.nextToken()).type != TokenType.EOF) {
            output.append(token).append("\n");
        }
        output.append(token).append("\n"); // Add EOF token
        
        assertEquals(expected, output.toString());
    }

    @Test
    public void testSimpleSubtraction() {
        String input = "8-5";
        String expected = "<NUMBER>8</NUMBER>\n<MINUS>-</MINUS>\n<NUMBER>5</NUMBER>\n<EOF>EOF</EOF>\n";
        
        Scanner scanner = new Scanner(input.getBytes());
        StringBuilder output = new StringBuilder();
        Token token;
        while ((token = scanner.nextToken()).type != TokenType.EOF) {
            output.append(token).append("\n");
        }
        output.append(token).append("\n"); // Add EOF token
        
        assertEquals(expected, output.toString());
    }

    @Test
    public void testComplexExpression() {
        String input = "289-85+0+69";
        String expected = "<NUMBER>289</NUMBER>\n<MINUS>-</MINUS>\n<NUMBER>85</NUMBER>\n" +
                         "<PLUS>+</PLUS>\n<NUMBER>0</NUMBER>\n<PLUS>+</PLUS>\n<NUMBER>69</NUMBER>\n<EOF>EOF</EOF>\n";
        
        Scanner scanner = new Scanner(input.getBytes());
        StringBuilder output = new StringBuilder();
        Token token;
        while ((token = scanner.nextToken()).type != TokenType.EOF) {
            output.append(token).append("\n");
        }
        output.append(token).append("\n"); // Add EOF token
        
        assertEquals(expected, output.toString());
    }
}