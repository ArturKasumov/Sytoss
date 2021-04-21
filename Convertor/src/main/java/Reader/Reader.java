package Reader;

import java.util.List;

public abstract class Reader {
    protected List<Line> lines;

    public abstract List<Line> read() throws Exception;
}
