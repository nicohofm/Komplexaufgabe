package Classes;
import Interfaces.IReadWriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteCSV implements IReadWriteFile{
    private List<String[]> csvData = new ArrayList<>();
    @Override
    public void readFile(String path) {
        try{
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while((line = bufferedReader.readLine()) != null)
            {
                csvData.add(line.split(";"));
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List<String[]> getCsvData(String path)
    {
        readFile(path);
        return csvData;
    }

    @Override
    public void writeFile(String path) {

    }
}
