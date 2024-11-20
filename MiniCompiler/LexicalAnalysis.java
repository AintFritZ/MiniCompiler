package MiniCompiler;

import java.util.regex.*;

public class LexicalAnalysis {

    public static boolean performLexicalAnalysis(String code) {
        String[] patterns = {
                "\\bint\\b",
                "\\bclass\\b",
                "\\bpublic\\b",
                "\\bprivate\\b",
                "[a-zA-Z_][a-zA-Z0-9_]*",
                "\\d+",
                "\\+",
                "-",
                "\\*",
                "/",
                ";",
                "\\{",
                "\\}",
                "\\(",
                "\\)"
        };

        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(code);
            while (m.find()) {
                System.out.println("Found token: " + m.group());
            }
        }

        return true;
    }
}
