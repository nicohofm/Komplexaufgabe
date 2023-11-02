package Interfaces;
import Classes.*;

public interface ICommunicator {
    SpeedCamera speedCamera = null;
    Simulator simulator = null;

    void startup();
    void executeSimulation();
    void report();
    void export();
    void shutdown();
    void exit();
}
