package Interfaces;
import java.util.List;

public interface IReadWriteFile {
    void readFile(String path);
    void writeFile(List<List<String>>data, String path);
}
