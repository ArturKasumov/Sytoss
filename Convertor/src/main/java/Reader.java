import java.io.File;
import java.util.List;

public abstract class Reader {
    protected List<Line> lines;

    abstract List<Line> read();
}
