package MiniCompiler;

import java.util.List;

public class SemanticAnalysis {
    public static boolean performSemanticAnalysis(List<String> codeLines) {
        boolean isValid = true;
        for (String line : codeLines) {
            if (!line.trim().matches("^(int|float|double|String)\\s+\\w+\\s*=.*;$")) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
