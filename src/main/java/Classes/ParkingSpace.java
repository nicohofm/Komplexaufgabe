package Classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date convertedCurrentDate = format.parse(data[4]);
                String date=format.format(convertedCurrentDate);
                LocalDate localDate = LocalDate.parse(date);
                places[j][i] = new Car(new LicensePlate(data[0]), data[1], data[2], new Owner(data[3], localDate, data[5], data[6]));
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            i++;
            if(i == 20)
            {
                i=0;
                j++;
            }
        }
    }
    public void deleteCar(Car car){
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                if(places[j][i] != null) {
                    if (car.getLicensePlate().getId().equals(places[j][i].getLicensePlate().getId())) {
                        places[j][i] = null;
                    }
                }
            }
        }
    }

    public List<Car> getCarList()
    {
        List<Car> carList = new ArrayList<>();
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 50; j++)
            {
                carList.add(places[j][i]);
            }
        }
        return carList;
    }

    public Car getCar(int column, int row)
    {
        return places[column][row];
    }

}
