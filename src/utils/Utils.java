package utils;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class Utils {

	public static boolean isFieldValid(String text) {

		return text != null && text.trim().length() > 0;

	}
	
	public static Date asDate(LocalDateTime localDateTime) {
		Calendar calendar = Calendar.getInstance();
				calendar.set(localDateTime.getYear(),
				localDateTime.getMonth().getValue() - 1,
				localDateTime.getDayOfMonth(),
				localDateTime.getHour(),
				localDateTime.getMinute());
		return calendar.getTime();
	}

	public static void addNumberFilter (TextField field) {
        UnaryOperator<Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        
        field.setTextFormatter(new TextFormatter<>(filter));
		
	}
	
}
