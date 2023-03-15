package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UnmarkTest {
    @Test
    public void isSameUnmark() {
        Mark doneMark = new Mark();
        doneMark.setDone();
        Mark undoneMarkOne = new Mark();
        Mark undoneMarkTwo = new Mark();
        undoneMarkTwo.setUndone();
        // same object -> returns true
        assertTrue(doneMark.equals(doneMark));

        // null -> returns false
        assertFalse(doneMark.equals(null));

        // different mark -> returns false
        assertFalse(doneMark.equals(undoneMarkOne));

        // marks that have the same status -> returns true
        doneMark.setUndone();
        assertTrue(doneMark.equals(new Mark()));

        // marks that have the same status -> returns true
        assertTrue(undoneMarkOne.equals(undoneMarkTwo));
    }

    @Test
    public void equals() {
        Mark undoneMark = new Mark();
        Mark doneMark = new Mark();
        doneMark.setDone();
        undoneMark.setDone();
        undoneMark.setUndone();

        // same values -> returns true
        assertTrue(undoneMark.toString().equals("[ ]"));

        // same values -> returns true
        assertTrue(doneMark.toString().equals("[X]"));
    }
}
