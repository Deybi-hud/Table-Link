package Prueba.TableLink.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SqlParserUtil {

    public static List<Map<String, String>> parseInsertStatements(String sqlContent) {
    List<Map<String, String>> result = new ArrayList<>();
 
        Pattern pattern = Pattern.compile(
        "INSERT INTO [`\"]?(\\w+)[`\"]? \\(([^)]+)\\) VALUES\\s*(.+?);",
        Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(sqlContent);

        while (matcher.find()) {

            String[] columns = matcher.group(2).split(",");
            String valuesGroup = matcher.group(3).trim();

            Matcher valueMatcher = Pattern.compile("\\(([^)]+)\\)").matcher(valuesGroup);

            while (valueMatcher.find()) {

                String[] values = valueMatcher.group(1).split(",(?=(?:[^']*'[^']*')*[^']*$)"); 
                if (columns.length != values.length) continue;

                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < columns.length; i++) {
                    row.put(columns[i].trim(), values[i].trim().replaceAll("^'|'$", "")); 
                }
                result.add(row);
                
            }
        }

        return result;
    }
}


