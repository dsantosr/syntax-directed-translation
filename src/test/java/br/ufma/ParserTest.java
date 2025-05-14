package br.ufma;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    
    @Test
    public void testParseArithmeticExpression() {
        // Arrange
        String input = "8+5-7+9";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        // Act
        Parser parser = new Parser(input.getBytes());
        parser.parse();
        
        // Assert
        String expectedOutput = "push 8\n" +
                              "push 5\n" +
                              "add\n" +
                              "push 7\n" +
                              "sub\n" +
                              "push 9\n" +
                              "add\n";
        assertEquals(expectedOutput, outContent.toString());
        
        // Cleanup
        System.setOut(originalOut);
    }
} 