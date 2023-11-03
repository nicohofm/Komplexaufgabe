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
            try{
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(data[4]);
                places[j][i] = new Car(new LicensePlate(data[0]), data[1], data[2], new Owner(data[3], date, data[4]));
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            i++;
            if(i == 20)
            {
                j++;
            }
        }
    }
    public void deleteCar(Car car){
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                if(car.getLicensePlate().getId().equals(places[j][i].getLicensePlate().getId()))
                {
                    places[j][i] = null;
                }
            }
        }
    }

    public Car getCar(int column, int row)
    {
        return places[column][row];
    }

}
