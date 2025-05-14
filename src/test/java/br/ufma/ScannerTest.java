package br.ufma;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScannerTest {
    
    @Test
    public void testTokenization() {
        // Arrange
        String input = "4-8+6";
        Scanner scanner = new Scanner(input.getBytes());
        
        // Act & Assert
        assertEquals('4', scanner.nextToken());
        assertEquals('-', scanner.nextToken());
        assertEquals('8', scanner.nextToken());
        assertEquals('+', scanner.nextToken());
        assertEquals('6', scanner.nextToken());
    }
} 