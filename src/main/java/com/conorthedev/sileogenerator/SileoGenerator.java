package com.conorthedev.sileogenerator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ConorTheDev
 * @version 1.0
 *
 * Generate your Sileo Depictions with ease
 */
public class SileoGenerator {
    /*
       The directory the program is being run from
    */
    private static final String runDirectory = System.getProperty("user.dir");
    /*
        The package name gathered from the user at runtime
     */
    private static String name = "";
    /*
        The image header URL gathered from the user at runtime
    */
    private static String headerUrl = "";
    /*
        The tint colour gathered from the user at runtime
    */
    private static String tintColour = "";
    /*
        The package description gathered from the user at runtime
    */
    private static String description = "";
    /*
        The package screenshots gathered from the user at runtime
    */
    private static ArrayList<String> screenshots = new ArrayList<>();

    /*
       Main method
    */
    public static void main(String[] args) {
        /*
            Display 'help' message if no arguments specified
         */
        if (args.length == 0) {
            System.out.println("=== Sileo Depiction Generator v1.0 ===");
            System.out.println("Please specify arguments!\n");
            System.out.println("Valid Arguments:\ncreate");
        } else {
            /*
                If the first argument is equal to 'create' start the JSON creation, first gathering info from the user
             */
            if (args[0].equalsIgnoreCase("create")) {
                // Start a new reader instance
                Scanner reader = new Scanner(System.in);

                // Get the package name
                System.out.println("Package Name: ");
                name = reader.nextLine();

                // Get the header URL
                System.out.println("Header Image URL (type none for none): ");
                headerUrl = reader.nextLine();

                // Get the tint colour
                System.out.println("Tint Colour (hexadecimal, e.g: #f75454): ");
                tintColour = reader.nextLine();

                // Get the package description
                System.out.println("Short description of your package: ");
                description = reader.nextLine();

                // Get the amount of screenshots
                System.out.println("Do you want your depiction to contain " + "screenshots? (y/n)");
                String containScreenshots = reader.nextLine();

                if (containScreenshots.equalsIgnoreCase("y")) {
                    System.out.println("How many screenshots do you have? (Integer)");
                    int numberOfScreenshots = reader.nextInt();

                    // Ask the user for a screenshot for how many times they provided
                    for(int x = 0; x < numberOfScreenshots; x++) {
                        // Ask the user for a URL
                        System.out.println("Screenshot " + (x + 1) + " url: ");
                        String url = reader.next();

                        // Add the screenshot to an array
                        screenshots.add(url);
                    }

                    // Display the gathered information to the user for confirmation
                    System.out.println("\nPackage Information:\nName: " + name + "\nHeader Image URL: " + headerUrl + "\nTint Colour: " + tintColour + "\nDescription: " + description + "\nScreenshots: " + Arrays.toString(screenshots.toArray()));
                } else {
                    // Display the gathered information to the user for confirmation
                    System.out.println("\nPackage Information:\nName: " + name + "\nHeader Image URL: " + headerUrl + "\nTint Colour: " + tintColour + "\nDescription: " + description);
                }

                // Ask the user if this information is correct
                System.out.println("Is this information correct? (y/n)");
                String isOk = reader.next();

                if (isOk.equalsIgnoreCase("y")) {
                    // The information is correct so we start generating a depiction
                    System.out.println("\nGenerating Sileo Depiction... This may take a few moments...");

                    // Create a json object
                    JSONObject generatedJson = new JSONObject();

                    // Put the user's package info into the JSONObject (generatedJson)
                    generatedJson.put("minVersion", "0.1");
                    // If the headerURL isn't blank put it in, otherwise skip it
                    if (!headerUrl.equalsIgnoreCase("none")) {
                        generatedJson.put("headerImage", headerUrl);
                    }
                    generatedJson.put("tintColor", tintColour);

                    // Create an array of tabs
                    JSONArray tabs = new JSONArray();
                    // Create details tab
                    JSONObject detailsTab = new JSONObject();

                    // Set the name of the tab
                    detailsTab.put("tabname", "Details");

                    // Create an array of views
                    JSONArray views = new JSONArray();

                    // Create an object of the 1st view, this will contain the package name and the short description
                    JSONObject firstView = new JSONObject();

                    // Put the markdown into the first view
                    firstView.put("class", "DepictionMarkdownView");
                    firstView.put("markdown", "## " + name + "\n" + description);

                    // Put the object into the array
                    views.put(0, firstView);

                    if(containScreenshots.equalsIgnoreCase("y")) {
                        // Create an object of the screenshots view, this will contain screenshots
                        JSONObject screenshotsView = new JSONObject();

                        screenshotsView.put("itemCornerRadius", 9);
                        screenshotsView.put("itemSize", "{187.5, 406}");

                        // Create an array of screenshots
                        JSONArray theScreenshots = new JSONArray();
                        int i = 0;
                        for(String url : screenshots) {
                            JSONObject screen = new JSONObject();
                            screen.put("accessibilityText", "Screenshot");
                            screen.put("url", url);
                            screen.put("fullSizeURL", url);
                            theScreenshots.put(i, screen);
                            i++;
                        }

                        // Put the screenshots into the screenshotsView
                        screenshotsView.put("screenshots", theScreenshots);

                        views.put(1, screenshotsView);
                    }

                    // Put the views into the details tab
                    detailsTab.put("views", views);

                    // Put the details tab into the array of tabs
                    tabs.put(0, detailsTab);
                    // Put the array of tabs into the JSON
                    generatedJson.put("tabs", tabs);

                    // Convert the JSONObject to a string, format it and print it to the user
                    String generatedJsonOutput = generatedJson.toString(4);
                    System.out.println("JSON Output:\n" + generatedJsonOutput);

                    // Write the json to a file with the package name at the root of the directory
                    File json = new File(runDirectory, name + ".json");
                    if (!json.exists()) {
                        try {
                            if (json.createNewFile()) {
                                // The file was successfully created, now write to it

                                Writer output = new BufferedWriter(new FileWriter(json));
                                output.write(generatedJsonOutput);
                                output.close();

                                System.out.println("Wrote JSON file to: " + json.getAbsolutePath());
                            } else {
                                // The file was unsuccessfully created
                                System.out.println("Error writing JSON file to: " + json.getAbsolutePath());
                            }
                        } catch (IOException e) {
                            // An error occured, oh noes!
                            e.printStackTrace();
                        }
                    } else {
                        // The file already exists, ask the user if we should overwrite it
                        System.out.println("The file: + " + json.getAbsolutePath() + " already exists, overwrite? (y/n)");
                        String overwrite = reader.next();

                        if (overwrite.equalsIgnoreCase("y")) {
                            // We have permission to overwrite the file
                            Writer output = null;
                            try {
                                output = new BufferedWriter(new FileWriter(json));
                                output.write(generatedJsonOutput);
                                output.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            // Close the program
                            System.out.println("Exiting program...");
                            System.exit(0);
                        }

                        System.out.println("Wrote JSON file to: " + json.getAbsolutePath());
                    }
                } else {
                    // The information is not correct so we exit the program
                    System.out.println("Exiting program since this information is not correct...");
                    System.exit(0);
                }

                // Close the reader
                reader.close();

            } else {
                // Tell the user that their argument is not valid
                System.err.println("ERROR: Argument '" + args[0] + "' is not a valid argument ");
            }
        }
    }
}
