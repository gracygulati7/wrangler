package io.cdap.wrangler.plugin;

import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.ExecutorContext;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.Column;
import io.cdap.wrangler.api.parser.Token;
import io.cdap.wrangler.api.parser.UsageDefinition;
import io.cdap.wrangler.api.parser.Text;
import io.cdap.wrangler.api.parser.String;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregateStats implements Directive {
    private String byteCol;
    private String timeCol;
    private String sizeTarget;
    private String timeTarget;

    private long totalBytes = 0;
    private long totalTime = 0;

    @Override
    public UsageDefinition define() {
        return UsageDefinition.builder("aggregate-stats")
            .define("byteCol", Column.class)
            .define("timeCol", Column.class)
            .define("sizeTarget", Column.class)
            .define("timeTarget", Column.class)
            .build();
    }

    @Override
    public void initialize(Arguments args) {
        this.byteCol = ((Column) args.value("byteCol")).value();
        this.timeCol = ((Column) args.value("timeCol")).value();
        this.sizeTarget = ((Column) args.value("sizeTarget")).value();
        this.timeTarget = ((Column) args.value("timeTarget")).value();
    }

    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext context) {
        for (Row row : rows) {
            String byteValue = row.getValue(byteCol).toString();
            String timeValue = row.getValue(timeCol).toString();

            ByteSize size = new ByteSize(byteValue);
            TimeDuration time = new TimeDuration(timeValue);

            totalBytes += size.getBytes();
            totalTime += time.getMilliseconds();
        }

        Row result = new Row();
        result.add(sizeTarget, totalBytes / (1024.0 * 1024.0));  // MB
        result.add(timeTarget, totalTime / 1000.0);              // seconds

        return Collections.singletonList(result);
    }

    @Override
    public void destroy() {
        // Nothing to clean up
    }
}
