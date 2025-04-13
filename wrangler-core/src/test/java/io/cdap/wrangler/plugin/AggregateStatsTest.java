package io.cdap.wrangler.plugin;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.test.RecipeTestRunner;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AggregateStatsTest {

    @Test
    public void testAggregation() throws Exception {
        List<Row> rows = Arrays.asList(
            new Row().add("size", "10KB").add("time", "1s"),
            new Row().add("size", "1MB").add("time", "500ms")
        );

        String[] recipe = {
            "aggregate-stats :size :time total_size_mb total_time_sec"
        };

        List<Row> results = RecipeTestRunner.runRecipe(recipe, rows);

        Assert.assertEquals(1, results.size());

        double actualSize = (double) results.get(0).getValue("total_size_mb");
        double actualTime = (double) results.get(0).getValue("total_time_sec");

        Assert.assertEquals(1.009765625, actualSize, 0.001);
        Assert.assertEquals(1.5, actualTime, 0.001);
    }
}
