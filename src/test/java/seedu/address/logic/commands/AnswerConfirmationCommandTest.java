package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains unit tests for {@code AnswerConfirmationCommand}.
 */
public class AnswerConfirmationCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_yesAnswer_noPendingCommandThrowsCommandException() {
        AnswerConfirmationCommand command =
                new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "");
        assertCommandFailure(command, model, AnswerConfirmationCommand.MESSAGE_NO_PENDING_COMMAND);

        command = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, null);
        assertCommandFailure(command, model, AnswerConfirmationCommand.MESSAGE_NO_PENDING_COMMAND);
    }

    @Test
    public void execute_noAnswer_noPendingCommandThrowsCommandException() {
        AnswerConfirmationCommand command =
                new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        assertCommandFailure(command, model, AnswerConfirmationCommand.MESSAGE_NO_PENDING_COMMAND);

        command = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, null);
        assertCommandFailure(command, model, AnswerConfirmationCommand.MESSAGE_NO_PENDING_COMMAND);
    }

    @Test
    public void execute_noAnswer_returnsCancelledMessage() {
        model.setPendingCommand(new ClearCommand());
        AnswerConfirmationCommand cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(cmd, model, AnswerConfirmationCommand.MESSAGE_COMMAND_CANCELLED, expectedModel);

        model.setPendingCommand(new ClearCommand());
        cmd = new AnswerConfirmationCommand(
                AnswerConfirmationCommand.AnswerType.NO, null);
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(cmd, model, AnswerConfirmationCommand.MESSAGE_COMMAND_CANCELLED, expectedModel);
    }

    @Test
    public void execute_noAnswer_modelUnchanged() {
        model.setPendingCommand(new ClearCommand());
        AnswerConfirmationCommand cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(cmd, model, AnswerConfirmationCommand.MESSAGE_COMMAND_CANCELLED, expectedModel);
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());

        model.setPendingCommand(new ClearCommand());
        cmd = new AnswerConfirmationCommand(
                AnswerConfirmationCommand.AnswerType.NO, null);
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(cmd, model, AnswerConfirmationCommand.MESSAGE_COMMAND_CANCELLED, expectedModel);
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void execute_yesAnswer_clearsModel() {
        model.setPendingCommand(new ClearCommand());
        AnswerConfirmationCommand cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "");

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(cmd, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);

        model.setPendingCommand(new ClearCommand());
        cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, null);

        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(cmd, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_yesAnswer_deletesPersonNullArg() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setPendingCommand(new DeleteCommand(INDEX_FIRST_PERSON));
        AnswerConfirmationCommand cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "");

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                seedu.address.logic.Messages.format(personToDelete));

        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_yesAnswer_deletesPersonEmptyArg() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setPendingCommand(new DeleteCommand(INDEX_FIRST_PERSON));
        AnswerConfirmationCommand cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, null);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                seedu.address.logic.Messages.format(personToDelete));

        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        model.setPendingCommand(new ClearCommand());
        AnswerConfirmationCommand cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "");
        assertTrue(cmd.equals(cmd));

        model.setPendingCommand(new ClearCommand());
        cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, null);
        assertTrue(cmd.equals(cmd));
    }

    @Test
    public void equals_sameAnswerType_returnsTrue() {
        AnswerConfirmationCommand cmd1 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        AnswerConfirmationCommand cmd2 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        assertTrue(cmd1.equals(cmd2));

        cmd1 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, null);
        cmd2 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, null);
        assertTrue(cmd1.equals(cmd2));

        cmd1 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        cmd2 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, null);
        assertTrue(cmd1.equals(cmd2));
    }

    @Test
    public void equals_sameAnswerTypeWithArgs_returnsTrue() {
        AnswerConfirmationCommand cmd1 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "aaa");
        AnswerConfirmationCommand cmd2 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "aaa");
        assertTrue(cmd1.equals(cmd2));

        cmd1 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "aaa");
        cmd2 = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "bbb");
        assertFalse(cmd1.equals(cmd2));
    }

    @Test
    public void equals_differentAnswerType_returnsFalse() {
        AnswerConfirmationCommand yesCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "");
        AnswerConfirmationCommand noCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        assertFalse(yesCmd.equals(noCmd));

        yesCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, null);
        noCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, null);
        assertFalse(yesCmd.equals(noCmd));

        yesCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "");
        noCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, null);
        assertFalse(yesCmd.equals(noCmd));

        yesCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, null);
        noCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        assertFalse(yesCmd.equals(noCmd));
    }

    @Test
    public void equals_differentAnswerTypeWithArgs_returnsTrue() {
        AnswerConfirmationCommand yesCmd =
                new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "aaa");
        AnswerConfirmationCommand noCmd =
                new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "aaa");
        assertTrue(yesCmd.equals(noCmd));

        yesCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.YES, "aaa");
        noCmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "bbb");
        assertFalse(yesCmd.equals(noCmd));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        AnswerConfirmationCommand cmd = new AnswerConfirmationCommand(AnswerConfirmationCommand.AnswerType.NO, "");
        assertFalse(cmd.equals("N"));
    }
}
