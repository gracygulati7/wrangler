package io.cdap.wrangler.plugin;

import io.cdap.wrangler.api.parser.TimeDuration;
import org.junit.Assert;
import org.junit.Test;

public class TimeDurationTest {
    @Test
    public void testTimeParsing() {
        Assert.assertEquals(5000, new TimeDuration("5s").getMilliseconds());
        Assert.assertEquals(120000, new TimeDuration("2min").getMilliseconds());
        Assert.assertEquals(3600000, new TimeDuration("1h").getMilliseconds());
    }
}
