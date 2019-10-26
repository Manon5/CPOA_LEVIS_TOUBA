import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class dateUtil {
	private static final String Date_Pattern = "dd.MM.yyyy";
	
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Date_Pattern);
	
	public static String format(LocalDate date) {
		if(date == null){
			return null;
		}
		return DATE_FORMATTER.format(date);
	}
	
	
	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		}catch(DateTimeParseException e) {
			return null;
		}
	}
	
	
	public static boolean ValideDate(String dateString) {
		return dateUtil.parse(dateString) != null;
	}
}
