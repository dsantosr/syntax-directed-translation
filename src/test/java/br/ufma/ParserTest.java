package br.ufma;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    
    @Test
    public void testSimpleAddition() {
        String input = "8+5";
        String expected = "push 8\npush 5\nadd\n";
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            Parser parser = new Parser(input.getBytes());
            parser.parse();
            String actual = outContent.toString().replace("\r\n", "\n");
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testSimpleSubtraction() {
        String input = "8-5";
        String expected = "push 8\npush 5\nsub\n";
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            Parser parser = new Parser(input.getBytes());
            parser.parse();
            String actual = outContent.toString().replace("\r\n", "\n");
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testComplexExpression() {
        String input = "289-85+0+69";
        String expected = "push 289\npush 85\nsub\npush 0\nadd\npush 69\nadd\n";
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            Parser parser = new Parser(input.getBytes());
            parser.parse();
            String actual = outContent.toString().replace("\r\n", "\n");
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testComplexExpression2() {
        String input = "89+508-7+99";
        String expected = "push 89\npush 508\nadd\npush 7\nsub\npush 99\nadd\n";
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            Parser parser = new Parser(input.getBytes());
            parser.parse();
            String actual = outContent.toString().replace("\r\n", "\n");
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testExpressionWithWhitespace() {
        String input = "45  + 89   -       876";
        String expected = "push 45\npush 89\nadd\npush 876\nsub\n";
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            Parser parser = new Parser(input.getBytes());
            parser.parse();
            String actual = outContent.toString().replace("\r\n", "\n");
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testLetStatementWithAddition() {
        String input = "let a = 42 + 5 - 8;";
        String expected = "push 42\npush 5\nadd\npush 8\nsub\npop a\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Parser parser = new Parser(input.getBytes());
            parser.parse();
            String actual = outContent.toString().replace("\r\n", "\n");
            assertEquals(expected, actual);
        } finally {
            System.setOut(originalOut);
        }
    }
}