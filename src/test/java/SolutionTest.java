import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void encode_singleWord() {
        assertEquals(".- -... -.-.", Solution.encode("ABC"));
    }

    @Test
    void encode_multipleWords() {
        assertEquals("-.-. -... .-  .- -... -.-.", Solution.encode("CBA ABC"));
    }

    @Test
    void decode_singleWord() {
        assertEquals("ABC", Solution.decode(".- -... -.-."));
    }

    @Test
    void decode_multipleWords_doubleSpaceSeparator() {
        assertEquals("CAB ABC", Solution.decode("-.-. .- -...  .- -... -.-."));
    }

    @Test
    void decode_allowsSlashAsWordSeparator() {
        assertEquals("CAB ABC", Solution.decode("-.-. .- -... / .- -... -.-."));
    }

    @Test
    void roundTrip_preservesWordsAndLetters() {
        String input = "This is a sentence 123";
        String encoded = Solution.encode(input);
        String decoded = Solution.decode(encoded);
        assertEquals("THIS IS A SENTENCE 123", decoded);
    }

    @Test
    void encode_unsupportedCharacter_throws() {
        assertThrows(IllegalArgumentException.class, () -> Solution.encode("HELLO!"));
    }

    @Test
    void decode_unsupportedCode_throws() {
        assertThrows(IllegalArgumentException.class, () -> Solution.decode("... --- ... ......"));
    }

    @Test
    void encode_trimsAndNormalizesWhitespaceBetweenWords() {
        assertEquals(".- -... -.-.", Solution.encode("   ABC   "));
        assertEquals("-.-. -... .-  .- -... -.-.", Solution.encode("CBA     ABC"));
    }
}
