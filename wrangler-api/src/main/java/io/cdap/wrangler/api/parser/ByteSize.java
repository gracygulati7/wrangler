package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
    private final long bytes;

    public ByteSize(String value) {
        super(value);
        String unit = value.replaceAll("[0-9.]", "").toUpperCase();
        double num = Double.parseDouble(value.replaceAll("[^0-9.]", ""));
        switch (unit) {
            case "KB": bytes = (long)(num * 1024); break;
            case "MB": bytes = (long)(num * 1024 * 1024); break;
            case "GB": bytes = (long)(num * 1024 * 1024 * 1024); break;
            case "TB": bytes = (long)(num * 1024L * 1024L * 1024L * 1024L); break;
            default: bytes = (long) num; // 'B'
        }
    }

    public long getBytes() {
        return bytes;
    }
}
