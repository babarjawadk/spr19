package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;


/**
 * An implementation the Clorus, a fierce blue-colored predator
 * that enjoys nothing more than snacking on hapless Plips.
 * @author Jawad
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 34, green = 0 and blue = 231.
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature,
     * it should gain that creature’s energy.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Clorus should lose 0.03 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.03;
    }


    /**
     * Clorus should loose 0.01 energy when staying.
     */
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        energy *= 0.5;
        Clorus that = new Clorus(energy);
        return that;
    }

    /**
     * Cloruses should obey exactly the following behavioral rules:
     * If there are no empty squares, the Clorus will STAY (even if there are Plips
     * nearby they could attack since plip squares do not count as empty squares).
     * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        boolean anyEmpty = false;

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            Direction dir = entry.getKey();
            Occupant occ = entry.getValue();
            if (occ.name().equals("empty")) {
                anyEmpty = true;
                emptyNeighbors.addLast(dir);
            } else if (occ.name().equals("plip")) {
                anyPlip = true;
                plipNeighbors.addLast(dir);
            }
        }

        if (!anyEmpty) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (anyPlip) {
            Direction plipDirection = randomEntry(plipNeighbors);
            Plip plipAttacked = (Plip) neighbors.get(plipDirection);
            this.attack(plipAttacked);
            return new Action(Action.ActionType.ATTACK, plipDirection);
        }

        // Rule 3
        if (energy >= 1.00) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
