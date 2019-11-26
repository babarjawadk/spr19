package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;

/** Tests the clorus class
 *  @authr Jawad
 */
public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(100);
        assertEquals(100, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(99.97, c.energy(), 0.01);
        c.move();
        assertEquals(99.94, c.energy(), 0.01);
        c.stay();
        assertEquals(99.93, c.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Plip somePlip = new Plip(2);
        Clorus someClorus = new Clorus (157);
        someClorus.attack(somePlip);
        assertEquals(159, someClorus.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus firstClorus = new Clorus(100);
        Clorus secondClorus = firstClorus.replicate();
        firstClorus.move();
        assertEquals(49.97, firstClorus.energy(), 0.01);
        assertEquals(50, secondClorus.energy(), 0.01);
    }
}
