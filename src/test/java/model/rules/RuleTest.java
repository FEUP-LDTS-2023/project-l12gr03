package model.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.model.rules.Rule;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RuleTest {

    private Rule rule;

    @BeforeEach
    void setUp() throws IOException {
        this.rule = new Rule();
    }

    @Test
    void testScanRule() {
        assertDoesNotThrow(() -> rule.ScanRule());

        // Assuming the first line is not empty after scanning
        String firstLineAfterScan = rule.getLine(0);
        assertNotNull(firstLineAfterScan);
        assertFalse(firstLineAfterScan.trim().isEmpty());
    }

    @Test
    void testGetNumberLines() {
        // Assuming the actual file has at least one line
        assertTrue(rule.getNumberLines() > 0);
    }

    @Test
    void testGetLine() {
        // Assuming the actual file has at least one line
        String firstLine = rule.getLine(0);
        assertNotNull(firstLine);
        assertFalse(firstLine.trim().isEmpty());

        // Test accessing a specific line (e.g., line 10)
        String line10 = rule.getLine(9);
        assertNotNull(line10);
        assertFalse(line10.trim().isEmpty());
    }
}

