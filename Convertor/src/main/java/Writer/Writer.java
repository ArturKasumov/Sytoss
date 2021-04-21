package Writer;

import Reader.Line;

import java.util.List;

public abstract class Writer {
    public abstract void write(List<Line> lines) throws Exception;
}
