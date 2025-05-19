package br.ufma;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    
    @Test
    public void testSimpleAddition() {
        String input = "8+5";
        String expected = "push 8\npush 5\nadd\n";
        
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        assertEquals(expected, parser.output());
    }

    @Test
    public void testSimpleSubtraction() {
        String input = "8-5";
        String expected = "push 8\npush 5\nsub\n";
        
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        assertEquals(expected, parser.output());
    }

    @Test
    public void testComplexExpression() {
        String input = "289-85+0+69";
        String expected = "push 289\npush 85\nsub\npush 0\nadd\npush 69\nadd\n";
        
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        assertEquals(expected, parser.output());
    }

    @Test
    public void testComplexExpression2() {
        String input = "89+508-7+99";
        String expected = "push 89\npush 508\nadd\npush 7\nsub\npush 99\nadd\n";
        
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        assertEquals(expected, parser.output());
    }

    @Test
    public void testExpressionWithWhitespace() {
        String input = "45  + 89   -       876";
        String expected = "push 45\npush 89\nadd\npush 876\nsub\n";
        
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        assertEquals(expected, parser.output());
    }

    @Test
    public void testLetStatement() {
        String input = "let a = 42 + 5 - 8;";
        String expected = "push 42\npush 5\nadd\npush 8\nsub\npop a\n";
        
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        assertEquals(expected, parser.output());
    }

    @Test
    public void testMultipleStatements() {
        String input = "let a = 42 + 5 - 8;\n" +
                      "let b = 56 + 8;\n" +
                      "print a + b + 6;";
        
        String expected = "push 42\npush 5\nadd\npush 8\nsub\npop a\n" +
                         "push 56\npush 8\nadd\npop b\n" +
                         "push a\npush b\nadd\npush 6\nadd\nprint\n";
        
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        assertEquals(expected, parser.output());
    }
}