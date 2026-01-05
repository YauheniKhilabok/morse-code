import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class Solution {

    private static final Map<Character, String> CHAR_TO_CODE;
    private static final Map<String, Character> CODE_TO_CHAR;

    static {
        Map<Character, String> c2m = new HashMap<>();

        // A-Z
        c2m.put('A', ".-");
        c2m.put('B', "-...");
        c2m.put('C', "-.-.");
        c2m.put('D', "-..");
        c2m.put('E', ".");
        c2m.put('F', "..-.");
        c2m.put('G', "--.");
        c2m.put('H', "....");
        c2m.put('I', "..");
        c2m.put('J', ".---");
        c2m.put('K', "-.-");
        c2m.put('L', ".-..");
        c2m.put('M', "--");
        c2m.put('N', "-.");
        c2m.put('O', "---");
        c2m.put('P', ".--.");
        c2m.put('Q', "--.-");
        c2m.put('R', ".-.");
        c2m.put('S', "...");
        c2m.put('T', "-");
        c2m.put('U', "..-");
        c2m.put('V', "...-");
        c2m.put('W', ".--");
        c2m.put('X', "-..-");
        c2m.put('Y', "-.--");
        c2m.put('Z', "--..");

        // 0-9
        c2m.put('0', "-----");
        c2m.put('1', ".----");
        c2m.put('2', "..---");
        c2m.put('3', "...--");
        c2m.put('4', "....-");
        c2m.put('5', ".....");
        c2m.put('6', "-....");
        c2m.put('7', "--...");
        c2m.put('8', "---..");
        c2m.put('9', "----.");

        CHAR_TO_CODE = Collections.unmodifiableMap(c2m);

        Map<String, Character> m2c = new HashMap<>();
        for (Map.Entry<Character, String> e : CHAR_TO_CODE.entrySet()) {
            m2c.put(e.getValue(), e.getKey());
        }
        CODE_TO_CHAR = Collections.unmodifiableMap(m2c);
    }

    public static void main(String[] args) {
        String input = "CBA ABC";
        String encoded = encode(input);
        System.out.println("Encode result: " + encoded);

        String decoded = decode(encoded);
        System.out.println("Decode result: " + decoded);
    }

    public static String encode(String input) {
        if (input == null) {
            return null;
        }

        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return "";
        }

        StringBuilder out = new StringBuilder();
        String[] words = trimmed.split("\\s+");

        for (int w = 0; w < words.length; w++) {
            if (w > 0) {
                out.append("  ");
            }

            String word = words[w].toUpperCase(Locale.ROOT);
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                String morse = CHAR_TO_CODE.get(ch);
                if (morse == null) {
                    throw new IllegalArgumentException("Unsupported character: '" + ch + "'");
                }
                if (i > 0) {
                    out.append(' ');
                }
                out.append(morse);
            }
        }

        return out.toString();
    }

    public static String decode(String input) {
        if (input == null) {
            return null;
        }

        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return "";
        }

        String normalized = trimmed.replaceAll("\\s*/\\s*", "  ");

        String[] morseWords = normalized.split("\\s{2,}");
        StringBuilder out = new StringBuilder();

        for (int w = 0; w < morseWords.length; w++) {
            if (w > 0) {
                out.append(' ');
            }

            String word = morseWords[w].trim();
            if (word.isEmpty()) {
                continue;
            }

            String[] codes = word.split("\\s+");
            for (String code : codes) {
                Character ch = CODE_TO_CHAR.get(code);
                if (ch == null) {
                    throw new IllegalArgumentException("Unsupported morse code: '" + code + "'");
                }
                out.append(ch);
            }
        }

        return out.toString();
    }
}
