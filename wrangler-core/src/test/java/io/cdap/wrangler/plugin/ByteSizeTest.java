package io.cdap.wrangler.plugin;

import io.cdap.wrangler.api.parser.ByteSize;
import org.junit.Assert;
import org.junit.Test;

public class ByteSizeTest {
    @Test
    public void testByteSizeParsing() {
        Assert.assertEquals(10240, new ByteSize("10KB").getBytes());
        Assert.assertEquals(1572864, new ByteSize("1.5MB").getBytes());
        Assert.assertEquals(1073741824L, new ByteSize("1GB").getBytes());
    }
}
