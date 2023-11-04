package ExecuteProgram;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Scanner;

public class Application {
    public static void main(String... args) {
        Execute execute = new Execute();
        while(true)
        {
            System.out.println("1: Startup");
            System.out.println("2: Import");
            System.out.println("3: Execute Simulation");
            System.out.println("4: Report");
            System.out.println("5: Export");
            System.out.println("6: Shutdown");
            System.out.println("7: Exit");
            Scanner input = new Scanner(System.in);
            int id = input.nextInt();
            switch (id)
            {
                case 1:
                    execute.StartUp();
                    break;
                case 2:
                    execute.Import();
                    break;
                case 3:
                    execute.ExecuteSimulation();
                    break;
                case 4:
                    execute.Report();
                    break;
                case 5:
                    execute.Export();
                    break;
                case 6:
                    execute.Shutdown();
                    break;
                case 7:
                    execute.Exit();
                    break;
            }
        }
    }
}