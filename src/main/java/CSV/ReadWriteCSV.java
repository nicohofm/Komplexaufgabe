package CSV;
import Interfaces.IReadWriteFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReadWriteCSV implements IReadWriteFile{
    private final List<String[]> csvData = new ArrayList<>();
    @Override
    public void readFile(String path) {
        try{
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while((line = bufferedReader.readLine()) != null)
            {
                csvData.add(line.split(";"));
            }
            csvData.remove(0);
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
    public void writeFile(List<List<String>>data, String path){
        File csvFile = new File(path);
        try
        {
            FileWriter fileWriter = new FileWriter(csvFile);
            for(List<String> value : data)
            {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < value.size(); i++) {
                    line.append(value.get(i));
                    if (i != value.size() - 1) {
                        line.append(',');
                    }
                }
                line.append("\n");
                fileWriter.write(line.toString());
            }
            fileWriter.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
