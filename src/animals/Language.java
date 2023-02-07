package animals;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class Language {
    // Get language code.
    public static String getLanguageCode() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage();
    }

    // Get message for key.
    public static String getMessage(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        return bundle.getString(key.trim()).replaceAll("''", "'");
    }

    // Get message list for key.
    public static String[] getMessageList(String key) {
        String list = getMessage(key);
        return list.split("\f");
    }

    // Get random message from list.
    public static String getRandomMessage(String key) {
        String[] list = getMessageList(key);
        Random random = new Random();
        return list[random.nextInt(list.length)];
    }

    // Get pattern for key.
    public static String getPattern(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("patterns");
        return bundle.getString(key.trim());
    }
}
