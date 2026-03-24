package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ConfirmationCommand extends Command {

    public static final String MESSAGE_REQUIRE_CONFIRMATION = """
            Are you sure you want to execute the following command?
            "%s"
            Please type %s to confirm or %s to cancel.
            """;

    private final String userInput;
    private final Command command;

    public ConfirmationCommand(String userInput, Command command) {
        this.userInput = userInput;
        this.command = command;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(
                MESSAGE_REQUIRE_CONFIRMATION,
                userInput,
                AnswerConfirmationCommand.COMMAND_WORD_YES,
                AnswerConfirmationCommand.COMMAND_WORD_NO
        ));
    }

    public Command getCommand() {
        return command;
    }
}
