package MiniCompiler;

import java.util.List;

public class SemanticAnalysis {
    public static boolean performSemanticAnalysis(List<String> codeLines) {
        boolean isValid = true;
        for (String line : codeLines) {
            line = line.trim();

            if (line.startsWith("int ")) {
                if (!line.matches("^int\\s+\\w+\\s*=\\s*\\d+\\s*;$")) {
                    isValid = false; // int can only be assigned integers
                    break;
                }
            } else if (line.startsWith("float ") || line.startsWith("double ")) {
                if (!line.matches("^(float|double)\\s+\\w+\\s*=\\s*\\d+(\\.\\d+)?\\s*;$")) {
                    isValid = false; // float/double can only be assigned numbers (optionally with decimals)
                    break;
                }
            } else if (line.startsWith("String ")) {
                if (!line.matches("^String\\s+\\w+\\s*=\\s*\"[^\"]*\"\\s*;$")) {
                    isValid = false; // String can only be assigned quoted strings
                    break;
                }
            } else {
                isValid = false; // Invalid declaration
                break;
            }
        }
        return isValid;
    }
}
