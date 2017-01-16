package br.com.unaerp.jdbc.persistence;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import javax.imageio.ImageIO;

class PersistenceConvert {
	public static Date LocalDateToSQLDate(LocalDate localDate) {
		return localDate == null ? null : Date.valueOf(localDate);
	}

	public static LocalDate SQLDateToLocalDate(Date data) {
		return data == null ? null : data.toLocalDate();
	}

	public static Timestamp LocalDateTimeToSQLDateTime(LocalDateTime locDateTime) {
		return locDateTime == null ? null : Timestamp.valueOf(locDateTime);
	}
	public static LocalDateTime SQLDateTimeToLocalDateTime(Timestamp sqlTimestamp) {
		return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime();
	}

	public static Date CalendarToSQLDate(Calendar c) {
		return c == null ? null : new Date(Calendar.getInstance().getTimeInMillis());
	}

	public static InputStream BufferedImageToInputStream(BufferedImage image) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static BufferedImage InputStreamToBufferedImage(byte[] image) {
		try {
			return ImageIO.read(new ByteArrayInputStream(image));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static LocalTime SQLDateTimeToLocalTime(Time sqlTimestamp) {
		return sqlTimestamp == null ? null : sqlTimestamp.toLocalTime();
	}
	
	public static String LocalTimeToSQLTime(LocalTime localTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Timestamp timestamp = null;
		/*
		try {
			timestamp = new Timestamp(sdf.parse(localTime.toString()).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		String tempo = "";
		try {
			tempo = sdf.format(new Timestamp(sdf.parse(localTime.toString()).getTime())).toString();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());;
		}
		
		return localTime == null ? null : tempo;//timestamp;
	}
}
