package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.Address;
import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rate;
import seedu.address.model.person.Time;
import seedu.address.model.tag.Tag;

/**
 * Unmarks an event identified using it's displayed index from the address book.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Umarks the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_EVENT_SUCCESS = "Unmarked event: %1$s";

    private final Index targetIndex;

    public UnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToUnmark = lastShownList.get(targetIndex.getZeroBased());
        Event unmarkedEvent = createUnmarkedEvent(eventToUnmark);
        model.unmarkEvent(eventToUnmark, unmarkedEvent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNMARK_EVENT_SUCCESS, eventToUnmark));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToUnmark}
     * edited with {@code editPersonDescriptor}.
     */
    private static Event createUnmarkedEvent(Event eventToUnmark) {
        assert eventToUnmark != null;

        Name updatedName = eventToUnmark.getName();
        Rate updatedRate = eventToUnmark.getRate();
        Address updatedAddress = eventToUnmark.getAddress();
        Set<Tag> updatedTags = eventToUnmark.getTags();
        Time updatedStartTime = eventToUnmark.getStartTime();
        Time updatedEndTime = eventToUnmark.getEndTime();
        Contact updatedContact = eventToUnmark.getContact();

        Event updatedEvent = new Event(
                updatedName, updatedRate, updatedAddress, updatedStartTime, updatedEndTime, updatedTags);
        updatedEvent.unmark();
        updatedEvent.linkContact(updatedContact);
        return updatedEvent;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkCommand) other).targetIndex)); // state check
    }
}
