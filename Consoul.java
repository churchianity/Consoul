
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

/*
 *
 *
 */
public class Consoul implements Runnable {

    // Text Colors
    private static final String BLACK  = "\u001B[30m";
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE   = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN   = "\u001B[36m";
    private static final String WHITE  = "\u001B[37m";

    // Background Colors
    private static final String BLACK_BACKGROUND  = "\u001B[40m";
    private static final String RED_BACKGROUND    = "\u001B[41m";
    private static final String GREEN_BACKGROUND  = "\u001B[42m";
    private static final String YELLOW_BACKGROUND = "\u001B[43m";
    private static final String BLUE_BACKGROUND   = "\u001B[44m";
    private static final String PURPLE_BACKGROUND = "\u001B[45m";
    private static final String CYAN_BACKGROUND   = "\u001B[46m";
    private static final String WHITE_BACKGROUND  = "\u001B[47m";

    // Wrap Text
    private static final String WRAP = "\u001B[7m";

    // Make text blink
    private static final String BLINK = "\u001B[5m";

    // Clear Console & Reset Cursor Position
    private static final String CLEAR = "\u001B[2J\u001B[;H";

    // Reset Colors
    private static final String RESET = "\u001B[0m";

    // Object Model for Course Content
    private static class Course {
        private String content;
        private Map<String, String> keywords;
        private String delimiter;
        private String[] pages;

        private Course() { this("JavaTutorial.md", new HashMap<String, String>(), "##? "); }

        private Course(String path, Map<String, String> keywords, String delimiter) {
            this.content = readAllBytesFromFile(path);
            this.keywords = keywords;
            this.delimiter = delimiter;
            this.pages = this.content.split(this.delimiter);
        }

        private String readAllBytesFromFile(String path) {
            try {
                return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
                /*
                // Convert tabs to 4 spaces
                this.content = this.content.replaceAll("\\t", TAB_WIDTH);
                // Get rid of lines consisting of just spaces (NOT NEWLINES)
                this.content = this.content.replaceAll("^[ ]+$", "");
                */
            } catch (IOException e) {
                e.printStackTrace(System.err);
                return "IOException splat!";
            }
        }
    }

    // Handles input
    private static final Scanner INPUT = new Scanner(System.in);

    // Handles Writing to Screen
    private static final BufferedWriter WRITER = new BufferedWriter(
                                                 new OutputStreamWriter(System.out));
    // Instance Vars
    private Course course;
    private volatile int bookmark;
    private Thread narrator;
    private volatile String listener;
    private boolean running;

    // Default
    public Consoul() { this(new Course()); }

    // Initial
    public Consoul(Course course) {
        this.course = course;
        this.bookmark = 0;
        this.running = true;
    }

    public void stop() {
        this.running = false;
        System.out.print(RESET + CLEAR + "Bye!\n");
    }

    // Thread
    @Override
    public synchronized void run() {
        try {
            // Clear screen
            WRITER.write(CLEAR); WRITER.flush();

            // Retrieve page
            String page = this.course.pages[bookmark];

            // Print page header
            WRITER.write(RED + "[" + this.bookmark*1.0 + "]   ");

            // Break page into lines
            String[] lines = page.split("\\r?\\n");
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    if (!Character.isLetterOrDigit(line.charAt(i))) {
                        WRITER.write(YELLOW);

                    } else if (Character.isWhitespace(line.charAt(i))) {
                        //

                    } else {
                        WRITER.write(WHITE); WRITER.flush();
                        this.narrator.sleep(20);
                    }

                    WRITER.write(line.charAt(i));
                }
                WRITER.write("\n"); WRITER.flush();
            }

            // Wait for user confirmation to continue
            WRITER.newLine(); WRITER.newLine();
            WRITER.write(BLINK + WHITE_BACKGROUND + BLACK + "Press Enter to Continue!" + RESET);
            WRITER.flush();

            Thread.sleep(1000000);
            System.exit(1);

        } catch (InterruptedException e) { // thread.sleep() throws
            // was interrupted

        } catch (IOException e){ // bufferedwriter throws
            e.printStackTrace(System.err);
        }
    }

    public void start() {
        while (this.running) {
            this.narrator = new Thread(this);
            this.narrator.start();

            // User Input Section
            try {
                this.listener = INPUT.nextLine();

                if (this.listener.matches("[+-]\\d")) { // Go To Inputted Page Index Offset
                    this.bookmark += Integer.parseInt(this.listener);

                } else { // Go To Inputted Page Index
                    this.bookmark = Integer.parseInt(this.listener);
                }

            // Non-numeric input check
            } catch (NumberFormatException e) {

                if (this.listener.matches("[pbzja]")) { // Go Back One Page
                    this.bookmark--;

                } else if (this.listener.matches("\\$")) { // Go To Last Page - "0" always goes to first page
                    this.bookmark = this.course.pages.length - 1;

                } else if (this.listener.matches("\u001B")) { // Escape to help menu
                    this.stop();

                } else { // Go Forward One Page - this is treated like a default
                    this.bookmark++;
                }

            } finally { // Stop Running Thread
                this.bookmark = Math.max(0, Math.min(this.course.pages.length - 1, this.bookmark)); // clamp
                this.narrator.interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Consoul consoul = new Consoul();
        consoul.start();
    }
}

