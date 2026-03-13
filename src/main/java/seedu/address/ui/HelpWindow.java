package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String HELP_MESSAGE = "=== Doritus User Guide ===\n\n"
            + "Doritus is a desktop app for managing contacts, optimized for use via a "
            + "Command Line Interface (CLI) while still having the benefits of a Graphical "
            + "User Interface (GUI).\n\n"
            + "--- COMMAND FORMAT NOTES ---\n"
            + "- Words in UPPER_CASE are parameters to be supplied by the user.\n"
            + "- Items in square brackets are optional.\n"
            + "- Items with ... after them can be used multiple times including zero times.\n"
            + "- Parameters can be in any order.\n\n"
            + "========================================\n"
            + "COMMANDS\n"
            + "========================================\n\n"
            + "1. ADD A CONTACT\n"
            + "   Format: add n/NAME p/PHONE_NUMBER e/EMAIL u/USERNAME r/ROLE [t/TAG]...\n"
            + "   Constraints:\n"
            + "     - NAME: Alphanumeric characters and single spaces only. No consecutive spaces.\n"
            + "     - PHONE_NUMBER: Exactly 8 digits. Must be unique.\n"
            + "     - EMAIL: Valid email format. Must be unique.\n"
            + "     - USERNAME: Alphanumeric characters only (no spaces or special characters). Must be unique.\n"
            + "     - ROLE: Alphanumeric characters and spaces only.\n"
            + "   Examples:\n"
            + "     add n/John Doe p/98765432 e/johnd@example.com u/johndoe123 r/Teaching Assistant\n"
            + "     add n/Betsy Crowe p/12345678 e/betsycrowe@example.com u/betsycrowe r/Student t/friend\n\n"
            + "2. LIST ALL CONTACTS\n"
            + "   Format: list\n\n"
            + "3. EDIT A CONTACT\n"
            + "   Format: edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [u/USERNAME] [r/ROLE] [t/TAG]...\n"
            + "   - At least one optional field must be provided.\n"
            + "   - Existing values will be updated to the input values.\n"
            + "   - When editing tags, existing tags are replaced (not cumulative).\n"
            + "   - Use t/ without any value to clear all tags.\n"
            + "   Examples:\n"
            + "     edit 1 p/91234567 e/johndoe@example.com\n"
            + "     edit 2 n/Betsy Crower t/\n\n"
            + "4. FIND CONTACTS BY NAME\n"
            + "   Format: find KEYWORD [MORE_KEYWORDS]\n"
            + "   - Search is case-insensitive.\n"
            + "   - Only full words are matched.\n"
            + "   - Persons matching at least one keyword are returned.\n"
            + "   Examples:\n"
            + "     find John\n"
            + "     find alex david\n\n"
            + "5. DELETE A CONTACT\n"
            + "   Format: delete INDEX\n"
            + "   Examples:\n"
            + "     delete 3\n\n"
            + "6. CLEAR ALL CONTACTS\n"
            + "   Format: clear\n\n"
            + "7. HELP\n"
            + "   Format: help\n\n"
            + "8. EXIT\n"
            + "   Format: exit\n\n"
            + "========================================\n"
            + "COMMAND SUMMARY\n"
            + "========================================\n"
            + "Add    : add n/NAME p/PHONE e/EMAIL u/USERNAME r/ROLE [t/TAG]...\n"
            + "List   : list\n"
            + "Edit   : edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [u/USERNAME] [r/ROLE] [t/TAG]...\n"
            + "Find   : find KEYWORD [MORE_KEYWORDS]\n"
            + "Delete : delete INDEX\n"
            + "Clear  : clear\n"
            + "Help   : help\n"
            + "Exit   : exit\n";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private TextArea helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
