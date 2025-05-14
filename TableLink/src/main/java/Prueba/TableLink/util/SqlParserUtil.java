package Prueba.TableLink.util;

import java.util.*;
import java.util.regex.*;

public class SqlParserUtil {
    public static List<Map<String, String>> parseInsertStatements(String sqlContent) {
    List<Map<String, String>> result = new ArrayList<>();

    // Match INSERT INTO tabla (col1, col2) VALUES (...), (...);
    Pattern pattern = Pattern.compile(
        "INSERT INTO [`\"]?(\\w+)[`\"]? \\(([^)]+)\\) VALUES\\s*(.+?);",
        Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Matcher matcher = pattern.matcher(sqlContent);

    while (matcher.find()) {
        String[] columns = matcher.group(2).split(",");
        String valuesGroup = matcher.group(3).trim();

        // Separar los grupos de valores: (1, 'Juan'), (2, 'Ana')
        Matcher valueMatcher = Pattern.compile("\\(([^)]+)\\)").matcher(valuesGroup);
        while (valueMatcher.find()) {
            String[] values = valueMatcher.group(1).split(",(?=(?:[^']*'[^']*')*[^']*$)"); // No cortar dentro de strings
            if (columns.length != values.length) continue;

            Map<String, String> row = new LinkedHashMap<>();
            for (int i = 0; i < columns.length; i++) {
                row.put(columns[i].trim(), values[i].trim().replaceAll("^'|'$", "")); // quitar comillas
            }
            result.add(row);
        }
    }

    return result;
    }
}


