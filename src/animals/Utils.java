package animals;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    // Print greeting based on time of day.
    public static void printGreeting(LocalDateTime currentDateTime) {
        if (isDateTimeInRange(currentDateTime, "time.early.start", "time.early.end")) {
            System.out.println(Language.getMessage("greeting.early"));
        }
        if (isDateTimeInRange(currentDateTime, "time.morning.start", "time.morning.end")) {
            System.out.println(Language.getMessage("greeting.morning"));
        }
        if (isDateTimeInRange(currentDateTime, "time.afternoon.start", "time.afternoon.end")) {
            System.out.println(Language.getMessage("greeting.afternoon"));
        }
        if (isDateTimeInRange(currentDateTime, "time.evening.start", "time.evening.end")) {
            System.out.println(Language.getMessage("greeting.evening"));
        }
        if (isDateTimeInRange(currentDateTime, "time.night.start", "time.night.end")) {
            System.out.println(Language.getMessage("greeting.night"));
        }
    }

    // Check if datetime is within range of hours.
    private static boolean isDateTimeInRange(LocalDateTime dateTime, String startHourKey, String endHourKey) {
        int startHourInclusive = getHour(startHourKey);
        int endHourExclusive = getHour(endHourKey);
        LocalDateTime startDateTimeInclusive = dateTime.toLocalDate().atTime(startHourInclusive, 0);
        LocalDateTime endDateTimeExclusive = dateTime.toLocalDate().atTime(endHourExclusive, 0);
        if (endHourExclusive == 0) {
            endDateTimeExclusive = endDateTimeExclusive.plusDays(1);
        }
        return (dateTime.equals(startDateTimeInclusive) || (dateTime.isAfter(startDateTimeInclusive) && dateTime.isBefore(endDateTimeExclusive)));
    }

    // Get hour from resource file.
    private static int getHour(String key) {
        final Pattern hourPattern = Pattern.compile("^(\\d{2}):\\d{2}$");
        final String exceptionMessage = "Resource %s is configured with an invalid time format.\n";

        int hour;
        String time = Language.getMessage(key);
        Matcher hourMatcher = hourPattern.matcher(time);
        if (!hourMatcher.find()) {
            throw new IllegalArgumentException(String.format(exceptionMessage, key));
        }
        try {
            hour = Integer.parseInt(hourMatcher.group(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(exceptionMessage, key));
        }
        return hour;
    }

    // Enter yes/no response.
    public static boolean enterYesNoResponse(Scanner scanner, String prompt) {
        Boolean response;

        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            response = getTrueOrFalse(input);
            if (response != null) {
                break;
            }
            prompt = Language.getRandomMessage("ask.again");
        }

        return response;
    }

    // Determine if input is true or false.
    private static Boolean getTrueOrFalse(String input) {
        final Pattern positiveAnswerPattern = Pattern.compile(Language.getPattern("positiveAnswer.isCorrect"));
        final Pattern negativeAnswerPattern = Pattern.compile(Language.getPattern("negativeAnswer.isCorrect"));

        Boolean response = null;

        Matcher positiveAnswerMatcher = positiveAnswerPattern.matcher(input);
        if (positiveAnswerMatcher.find()) {
            response = true;
        }
        Matcher negativeAnswerMatcher = negativeAnswerPattern.matcher(input);
        if (negativeAnswerMatcher.find()) {
            response = false;
        }

        return response;
    }

    // Apply pattern replace.
    public static String applyPatternReplace(String prefix, String value) {
        String returnValue;

        value = value.toLowerCase();
        int number = 1;
        while (true) {
            String pattern = Language.getPattern(String.format("%s.%d.pattern", prefix, number));
            if (Pattern.matches(pattern, value)) {
                String replace = Language.getPattern(String.format("%s.%d.replace", prefix, number));
                returnValue = value.replaceFirst(pattern, replace);
                break;
            }
            number++;
        }

        return returnValue;
    }

    // Capitalize first word in string.
    public static String capitalize(String string) {
        if (string == null || string.length() == 0) {
            return string;
        } else {
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
    }
}
