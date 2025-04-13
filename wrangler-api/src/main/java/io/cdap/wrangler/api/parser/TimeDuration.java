package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
    private final long millis;

    public TimeDuration(String value) {
        super(value);
        String unit = value.replaceAll("[0-9.]", "").toLowerCase();
        double num = Double.parseDouble(value.replaceAll("[^0-9.]", ""));
        switch (unit) {
            case "s":
            case "sec": millis = (long)(num * 1000); break;
            case "m":
            case "min": millis = (long)(num * 60 * 1000); break;
            case "h": millis = (long)(num * 60 * 60 * 1000); break;
            case "d": millis = (long)(num * 24 * 60 * 60 * 1000); break;
            default: millis = (long) num; // assume ms
        }
    }

    public long getMilliseconds() {
        return millis;
    }
}
