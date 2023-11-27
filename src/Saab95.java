import java.awt.*;
import java.util.LinkedList;

public class Saab95 extends Car {
    private boolean turboOn = false;


    public Saab95() {
        super(2,2,"Saab95",125,Color.red);
    }

    public void setTurboOn() {
        turboOn = true;


    }

    public boolean getTurboState() {
        return turboOn;
    }
    public void setTurboOff() {
        turboOn = false;
    }



    public double speedFactor() {
        double turbo = 1;
        if (turboOn) {turbo = 100;}
        return getEnginePower() * 0.1 * turbo;
    }



    }




