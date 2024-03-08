package vn.phat.util;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
public class ConvertNameUtil {
    private ConvertNameUtil(){}

    private static final String UNDER_SCORE = "_";

    public static String snakeToCamel(String input){
        if(input == null) return null;
        String [] parts = input.split(UNDER_SCORE);
        if(parts.length <= 1) return input.toLowerCase();
        StringBuilder sb = new StringBuilder(parts[0].toLowerCase());
        for(int i = 1; i< parts.length; i++){
            String word = parts[i];
            word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            sb.append(word);
        }
        return sb.toString();
    }
}
