import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {
    Saab95 saab;
    Volvo240 volvo;
    Scania scania;
    Verkstad<Saab95> saabVerkstad;
    Verkstad<Volvo240> volvoVerkstad;
    Verkstad<Car> mixadVerkstad;


    @BeforeEach
    void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();

        saabVerkstad = new Verkstad<>();
        saabVerkstad.setMaxAntalBilar(4);

        volvoVerkstad = new Verkstad<>();
        volvoVerkstad.setMaxAntalBilar(6);

        mixadVerkstad = new Verkstad<>();
        mixadVerkstad.setMaxAntalBilar(10);


    }

    @Test
    void colorCar() {
        Color saabColor = Color.red;
        assertEquals(saabColor, saab.getColor());

        Color volvoColor = Color.black;
        assertEquals(volvoColor, volvo.getColor());
    }

    @Test
    void nrDoors() {
        int volvoNrDoors = 4;
        assertEquals(volvoNrDoors, volvo.getNrDoors());

        int saabNrDoors = 2;
        assertEquals(saabNrDoors, saab.getNrDoors());
    }

    @Test
    void turboState() {
        saab.setTurboOn();
        assertTrue(saab.getTurboState());

        saab.setTurboOff();
        assertFalse(saab.getTurboState());
    }

    @Test
    void enginePowerCar() {
        double expectedPowerSaab = 125;
        assertEquals(expectedPowerSaab, saab.getEnginePower());

        double expectedPowerVolvo = 100;
        assertEquals(expectedPowerVolvo, volvo.getEnginePower());
    }

    @Test
    void testGas() {
        saab.startEngine();
        saab.gas(0.5);
        assertTrue(saab.getCurrentSpeed() > 0);

        volvo.startEngine();
        volvo.gas(0.5);
        assertTrue(volvo.getCurrentSpeed() > 0);

        scania.startEngine();
        scania.gas(0.5);
        assertTrue(scania.getCurrentSpeed() > 0);


    }

    @Test
    void testBrake() {
        saab.startEngine();
        saab.gas(1);
        double initialSpeedSaab = saab.getCurrentSpeed();
        saab.brake(0.5);
        double expectedSpeedSaab = initialSpeedSaab - (0.5 * saab.speedFactor());
        assertEquals(expectedSpeedSaab, saab.getCurrentSpeed());

        volvo.startEngine();
        volvo.gas(1);
        double initialSpeedVolvo = volvo.getCurrentSpeed();
        volvo.brake(0.5);
        double expectedSpeedVolvo = initialSpeedVolvo - (0.5 * volvo.speedFactor());
        assertEquals(expectedSpeedVolvo, volvo.getCurrentSpeed());

    }

    @Test
    void testStartEngine() {
        saab.startEngine();
        assertTrue(saab.getCurrentSpeed() > 0);

        volvo.startEngine();
        assertTrue(volvo.getCurrentSpeed() > 0);
    }

    @Test
    void testStopEngine() {
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());

        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    void testGetModelName() {
        String saabModel = saab.getModelName();
        assertEquals("Saab95", saabModel);

        String volvoModel = volvo.getModelName();
        assertEquals("Volvo240", volvoModel);
    }

    @Test
    void testGetPosition() {
        Point saabPosition = saab.getPosition();
        assertEquals(new Point(0, 0), saabPosition);

        Point volvoPosition = volvo.getPosition();
        assertEquals(new Point(0, 0), volvoPosition);
    }

    @Test
    void testGetDirection() {
        double saabDirection = saab.getDirection();
        assertEquals(0.0, saabDirection);

        double volvoDirection = volvo.getDirection();
        assertEquals(0.0, volvoDirection);
    }

    @Test
    void carMove() {
        Point sStartingPosition = saab.getPosition();
        saab.startEngine();
        saab.gas(0.5);
        saab.move();
        Point sUpdatedPosition = saab.getPosition();
        assertNotEquals(sStartingPosition, sUpdatedPosition);

        Point vStartingPosition = volvo.getPosition();
        volvo.startEngine();
        volvo.gas(0.5);
        volvo.move();

        Point vUpdatedPosition = volvo.getPosition();
        assertNotEquals(vStartingPosition, vUpdatedPosition);

        Point scaniaStartingPosition = scania.getPosition();
        scania.startEngine();
        scania.gas(0.5);
        scania.move();
        Point scaniaUpdatedPosition = scania.getPosition();
        assertNotEquals(scaniaStartingPosition, scaniaUpdatedPosition);
    }


    @Test
    void testTurnLeft() {
        double initialDirectionS = saab.getDirection();

        saab.turnLeft();
        assertEquals((initialDirectionS - 90) % 360, saab.getDirection());

        double initialDirectionV = volvo.getDirection();
        volvo.turnLeft();
        assertEquals((initialDirectionV - 90) % 360, volvo.getDirection());
    }

    @Test
    void testTurnRight() {
        double initialDirectionS = saab.getDirection();

        saab.turnRight();
        assertEquals((initialDirectionS + 90) % 360, saab.getDirection());

        double initialDirectionV = volvo.getDirection();
        volvo.turnRight();
        assertEquals((initialDirectionV + 90) % 360, volvo.getDirection());
    }

    @Test
    void testchangeflakVinkel() {
        scania.changeFlak(50);
        assertEquals(50, scania.getflakVinkel());

        scania.changeFlak(70);
        assertEquals(70, scania.getflakVinkel());
    }

    @Test
    void testTaUtOchInBilar() {
        saabVerkstad.taInBil(saab);
        saabVerkstad.taInBil(saab);
        saabVerkstad.taUtBil(saab);
        assertEquals(1, saabVerkstad.getBilarIVerkstad().size());
    }

    @Test
    void testGetBilarIVerkstad() {
        volvoVerkstad.taInBil(volvo);
        assertTrue(volvoVerkstad.getBilarIVerkstad().contains(volvo));

        mixadVerkstad.taInBil(saab);
        mixadVerkstad.taInBil(volvo);
        assertEquals(2, mixadVerkstad.getBilarIVerkstad().size());
    }

    @Test
    void testGetMaxAntalBilar() {
        int expectedMaxAntalBilar = 4;
        assertEquals(expectedMaxAntalBilar, saabVerkstad.getMaxAntalBilar());

    }
}
