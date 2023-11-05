import Classes.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkSpeedDefinitions {
private FineEngine fineEngine;
private Car car;
private boolean result;

@Given ("^I have a fineEngine and a car$")
    public void I_have_a_fineEngine(){
     fineEngine = new FineEngine(new MobileNetworkModule(),new CentralUnit());
    LicensePlate licensePlate = new LicensePlate("XXXXXXX");
    Owner owner = new Owner("Leona Watts", LocalDate.parse("1956-12-20") ,"ECCEACEAAEFAFEE","0151 9610354287");
     car = new Car(licensePlate,"10w896vv39", "Tesla", owner);
}

@When("^I ask it to compare (\\d+) with (\\d+)$")
    public void I_ask_compare(int speed, int carspeed){
    this.car.setSpeed(carspeed);
    result = fineEngine.checkSpeed(speed,car);
}

@Then("^it should answer with result (true|false)$")
    public void it_should_answer(boolean expected){
    assertEquals(expected,result);
}


}
