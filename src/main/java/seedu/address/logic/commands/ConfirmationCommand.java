package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ConfirmationCommand extends Command {
    private final Command command;

    public ConfirmationCommand(Command command) {
        this.command = command;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    public Command getCommand() {
        return command;
    }
}
