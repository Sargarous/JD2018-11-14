package by.it.malishevskiy.jd01_06;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB2 {
    static String[] changes;

    public static void main(String[] args) {
        String text = Poem.text.replaceAll("\n+", " ");
        Pattern pattern = Pattern.compile("[А-ЯЁ](\\.\\.\\.|[^.!?])*[.!?]");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        changes = new String[count];
        matcher.reset();
        int i = 0;
        while (matcher.find()) changes[i++] = matcher.group().replaceAll("[^а-яА-ЯёЁ]+", " ").trim();
        sortStringArray(changes);
        for (String sen : changes) {
            System.out.println(sen);
        }
    }

    private static void sortStringArray(String[] sens){
        int[] lens = new int[sens.length];
        for (int i = 0; i < sens.length; i++) {
            lens[i] = sens[i].length();
        }
        String[] out = new String[sens.length];
        Arrays.sort(lens);
        for (String sen : sens) {
            for (int i = 0; i < lens.length; i++) {
                if (sen.length() == lens[i] && out[i] == null) {
                    out[i] = sen;
                    break;
                }
            }
        }
        System.arraycopy(out, 0, sens, 0, out.length);
    }

}

