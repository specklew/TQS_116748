/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(2);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());
    }

    @Test
    void testAddElementToFullSet() {
        assertThrows(IllegalArgumentException.class, () -> setB.add(11));
    }

    @Test
    void testAddElementThatAlreadyExists() {
        setA.add(99);
        assertThrows(IllegalArgumentException.class, () -> setA.add(99));
    }

    @Test
    void testSetIntersection() {
        assertTrue(setB.intersects(setC));
        assertFalse(setA.intersects(setC));
    }

    @Test
    void testHashCode() {
        assertNotEquals(setB.hashCode(), setC.hashCode());
    }

    @SuppressWarnings({"SimplifiableAssertion", "ConstantValue"})
    @Test
    void testEqualsWhenNull() {
        assertFalse(setA.equals(null));
    }

    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "SimplifiableAssertion"})
    @Test
    void testEqualsWhenDifferentClass() {
        int number = 0;
        assertFalse(setA.equals(number));
    }

    @SuppressWarnings({"ConstantValue", "SimplifiableAssertion"})
    @Test
    void testEquals() {
        setB = setA;
        assertTrue(setA.equals(setB));
    }

    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


}
