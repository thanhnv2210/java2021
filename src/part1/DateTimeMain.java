package part1;

import java.time.*;

public class DateTimeMain {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.printf("localDate:%s%n", localDate);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.printf("localDateTime:%s%n", localDateTime);
//        System.out.printf("localDateTime UTC:%s%n", localDateTime.atOffset(new ZoneOffset()));
        ZoneId zoneId = ZoneId.of("Asia/Singapore");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.printf("zonedDateTime:%s%n", zonedDateTime);
        ZoneOffset offset = ZoneOffset.of("+02:00");
        OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime, offset);
        System.out.printf("offSetByTwo:%s%n", offSetByTwo);
        System.out.printf("offSetByTwo.toLocalDate:%s%n", offSetByTwo.toLocalDateTime());

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        System.out.printf("zonedDateTimeNow:%s%n", zonedDateTimeNow);
//        System.out.printf("getAvailableZoneIds:%s%n", ZoneOffset.getAvailableZoneIds());
//        ZoneOffset.getAvailableZoneIds().forEach(System.out::println);
        ZonedDateTime zonedDateTimeSingapore = zonedDateTimeNow.withZoneSameInstant(ZoneId.of("Asia/Singapore"));
        System.out.printf("zonedDateTimeNowSingapore:%s%n", zonedDateTimeSingapore);
        System.out.printf("zonedDateTimeNowSingapore String:%s%n", zonedDateTimeSingapore.toLocalDateTime());
        //(1.Database,2.LocalDateTime,3.FromParse)UTC(OffsetDateTime.of(....))
        // ===> ZonedDateTime->withZoneSameInstant
        // ===> ZonedDateTime->toLocalDateTime
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime utc2 = OffsetDateTime.of(LocalDateTime.parse("2021-12-26T10:15:12"), ZoneOffset.UTC);
        System.out.printf("utc:%s%n", utc);
        System.out.printf("utc2:%s%n", utc2);
        ZonedDateTime zonedDateTimeSingapore2 = ZonedDateTime.from(utc2).withZoneSameInstant(ZoneId.of("Asia/Singapore"));
        System.out.printf("zonedDateTimeNowSingapore String:%s%n", zonedDateTimeSingapore2.toLocalDateTime());
    }
}
