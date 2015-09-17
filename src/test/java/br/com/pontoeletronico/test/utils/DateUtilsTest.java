package br.com.pontoeletronico.test.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.pontoeletronico.utils.DateUtils;

public class DateUtilsTest {

	@Test
	public void toLocalDate() {
		Date data = new Date();
		Object obj = DateUtils.toLocalDate(new Date());
		Object objTest = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Assert.assertEquals(objTest, obj);
	}

	@Test
	public void toLocalTime() {
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
		Object obj = DateUtils.toLocalTime(new Date());
		Object objTest = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
		Assert.assertEquals(objTest, obj);
	}

	@Test
	public void toLocalDateTime() {
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
		Object obj = DateUtils.toLocalDateTime(new Date());
		Object objTest = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		Assert.assertEquals(objTest, obj);
	}

	@Test
	public void toDate1() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Object obj = DateUtils.toDate(localDateTime);
		Object objTest = Date.from(instant);
		Assert.assertEquals(objTest, obj);
	}

	@Test
	public void toDate2() {
		LocalDate localDate = LocalDate.now();
		Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Object objTest = Date.from(instant);
		Object obj = DateUtils.toDate(localDate);
		Assert.assertEquals(objTest, obj);
	}

	@Test
	public void toDate3() {
		LocalDate localDate = LocalDate.now();
		LocalDate date = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
		Instant instant = LocalTime.now().atDate(date).atZone(ZoneId.systemDefault()).toInstant();
		Object objTest = Date.from(instant);
		Object obj = DateUtils.toDate(LocalTime.now());
		Assert.assertEquals(objTest, obj);
	}

}
