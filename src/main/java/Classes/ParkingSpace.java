package Classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ParkingSpace {
    private Car[][] places = new Car[50][20];
    //private ReadWriteCSV fileReader;

    public void fillParkingSpace(List<String[]> userdata)
    {
        int i = 0;
        int j = 0;
        for (String[] data: userdata)
        {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            places[j][i] = new Car(new LicensePlate(data[0]), data[1], data[2], new Owner(data[3], format.parse(data[4])));
        }
    }
    public void deleteCar(Car car){}

}
