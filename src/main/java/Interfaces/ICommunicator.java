package Interfaces;
import ;

public interface ICommunicator {
    Speedcamera speedCamera;
    Simulator simulator;

    void startup();
    void executeSimulation();
    void report();
    void export();
    void shutdown();
    void exit();
}
