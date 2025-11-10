import java.util.Scanner;

// create main class to handle the menu for the pet clinic
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetClinic clinic = new PetClinic();
        clinic.loadClinicData();

        // while true to continue asking for input until user chooses to exit
        while (true) {
            // display the menu options to the user
            System.out.println("\n--- Pet Clinic Menu ---");
            System.out.println("1. Add Pet");
            System.out.println("2. Delete Pet");
            System.out.println("3. Modify Pet");
            System.out.println("4. Display All Pets");
            System.out.println("5. Report");
            System.out.println("6. Search Pet");
            System.out.println("7. Exit and Save");
            System.out.println("To return to menu at any point enter 'menu'.");
            System.out.print("Enter option: ");

            // set choice variable to store user input
            int choice;
            // use try block to handle errors whenever turning user input to an int
            try {
                choice = Integer.parseInt(scanner.nextLine());
                // catch errors if user doesn't enter a number from 1 to 7 and print a message
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number between 1 and 7.");
                // continue allows the loop to start again, letting user input another number
                continue;
            }

            // use a switch to handle multiple options for user input
            switch (choice) {
                // case one which allows user to add a pet
                case 1:
                    // prompt user to enter the type of pet to add
                    System.out.print("Type (cat/dog): ");
                    String type = scanner.nextLine().trim().toLowerCase();

                    // if the user input is not cat or dog return to the menu
                    if (!type.equals("cat") && !type.equals("dog")) {
                        System.out.println("Invalid type. Returning to menu.");
                        break;
                    }

                    // prompt user to enter pet name
                    System.out.print("Name: ");
                    String name = scanner.nextLine().trim();

                    // prompt user to enter age
                    System.out.print("Age: ");
                    int age = 0;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age. Returning to menu.");
                        break;
                    }

                    // prompt user to enter colour
                    System.out.print("Colour: ");
                    String colour = scanner.nextLine().trim();

                    // prompt user to enter weight
                    System.out.print("Weight (kg): ");
                    double weight = 0.0;
                    try {
                        weight = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid weight. Returning to menu.");
                        break;
                    }

                    System.out.print("Breed: ");
                    String breed = scanner.nextLine().trim();

                    // Create the pet object
                    Pet newPet;
                    if (type.equals("dog")) {
                        newPet = new Dog(name, age, colour, weight, breed);
                    } else {
                        newPet = new Cat(name, age, colour, weight, breed);
                    }

                    // if the pet is invalid don't add it to the clinic
                    if (!newPet.isValid()) {
                        System.out.println("Pet details invalid. Pet not saved.");
                        break;
                    }

                    // otherwise save it
                    clinic.addPet(newPet);
                    System.out.println("Pet added successfully!");
                    break;


                // case two which allows user to delete a pet
                case 2: {
                    // set default petName and validPetName to false
                    String petName = "";
                    boolean validPetName = false;

                    // while user input is not a valid input
                    while (!validPetName) {
                        // prompt user to enter the name of pet they wish to delete
                        System.out.print("Enter name of pet to delete: ");
                        petName = scanner.nextLine().trim();

                        // if user enters menu, return to menu
                        if(petName.equalsIgnoreCase("menu")) {
                            System.out.println("Returning to menu.");
                            break;
                        }

                        // if pet name is empty, print message
                        if (petName.isEmpty()) {
                            System.out.println("Pet name cannot be blank.");
                            // else if pet name is not a valid word, print message
                        } else if (!petName.matches("[a-zA-Z]+")) {
                            System.out.println("Pet name must be a valid word, example John.");
                        } else {
                            validPetName = true;
                        }
                        // allow user to try again if pet name is not valid
                        if (!validPetName) {
                            System.out.print("Would you like to try again? (y/n): ");
                            String again = scanner.nextLine().trim().toLowerCase();
                            if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                System.out.println("Returning to menu.");
                                break; // Exit the loop
                            }
                        }
                    }

                    // return back to menu if pet name is not valid
                    if (!validPetName) {
                        continue;
                    }

                    // Check if the pet exists before asking for confirmation
                    boolean petExists = false;
                    // Loop through all the pets in the list
                    for (Pet pet : clinic.getPets()) {
                        // If we find a pet with the same name entered by user
                        if (pet.getName().equalsIgnoreCase(petName)) {
                            petExists = true;
                            break;
                        }
                    }

                    // if the pet exists
                    if (petExists) {
                        // allow user to confirm if they want to delete the pet or not
                        System.out.println("Are you sure you want to delete " + petName + "? (y/n): ");
                        String confirmDelete = scanner.nextLine().trim();

                        // if user entered y, delete the pet
                        if (confirmDelete.equalsIgnoreCase("y") || confirmDelete.equalsIgnoreCase("yes")) {
                            clinic.deletePet(petName);
                            // else if they did not enter y, do not delete pet
                        } else {
                            System.out.println(petName + " was not deleted.");
                        }
                    } else {
                        System.out.println(petName + " was not found.");
                    }
                    // exit case two and stop deleting pet
                    break;
                }


                // case 3 which allows the user to modify details of a pet
                case 3: {
                    // set default oldName and validOldName to false
                    String oldName = "";
                    boolean validOldName = false;

                    // while user input is not a valid input
                    while (!validOldName) {
                        // prompt user to enter the name of pet they wish to modify
                        System.out.print("Enter name of pet to modify: ");
                        oldName = scanner.nextLine().trim();

                        // if user enters menu, return to menu
                        if(oldName.equalsIgnoreCase("menu")) {
                            System.out.println("Returning to menu.");
                            break;
                        }

                        // if old name is empty,print message
                        if (oldName.isEmpty()) {
                            System.out.println("Name cannot be blank.");
                            // else if old name is not a valid word, print message
                        } else if (!oldName.matches("[a-zA-Z]+")) {
                            System.out.println("Name must be a valid word, example John.");
                            // else valid old name is true
                        } else {
                            validOldName = true;
                        }
                        if (!validOldName) {
                            System.out.print("Would you like to try again? (y/n): ");
                            String again = scanner.nextLine().trim().toLowerCase();
                            if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                System.out.println("Returning to menu.");
                                break; // Stop modifying the pet
                            }
                        }
                    }

                    // Check if pet exists before asking user to modify details
                    boolean petExists = false;
                    // Loop through all the pets in the list
                    for (Pet pet : clinic.getPets()) {
                        // If we find a pet with the same name entered by user
                        if (pet.getName().equalsIgnoreCase(oldName)) {
                            petExists = true;
                            break;
                        }
                    }

                    // if pet was not found print message and go back to menu
                    if (!petExists) {
                        System.out.println(oldName + " was not found.");
                        continue;
                    }

                    // set default newColour and validNewColour to false
                    String newColour = "";
                    boolean validNewColour = false;

                    // while user input is not a valid input
                    while (!validNewColour) {
                        // prompt user to enter the new colour
                        System.out.print("New Colour: ");
                        newColour = scanner.nextLine().trim();

                        // if user enters menu, return to menu
                        if(newColour.equalsIgnoreCase("menu")) {
                            System.out.println("Returning to menu.");
                            break;
                        }

                        // if newColour is empty, print message
                        if (newColour.isEmpty()) {
                            System.out.println("Colour cannot be blank.");
                            // else if new colour is not a valid word, enter message
                        } else if (!newColour.matches("[a-zA-Z]+")) {
                            System.out.println("Colour must be a valid word, example white.");
                        } else {
                            validNewColour = true;
                        }
                        // prompt user to enter again if new colour is not valid
                        if (!validNewColour) {
                            System.out.print("Would you like to try again? (y/n): ");
                            String again = scanner.nextLine().trim().toLowerCase();
                            if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                System.out.println("Returning to menu.");
                                break; // Stop modifying the pet
                            }
                        }
                    }

                    // return back to menu if new colour is not valid
                    if (!validNewColour) {
                        continue;
                    }

                    // set default newAge and validNewAge to false
                    int newAge = 0;
                    boolean validNewAge = false;

                    // while user input is not a valid input
                    while (!validNewAge) {
                        // surround in a try block in case of errors
                        try {
                            // prompt user to enter new age
                            System.out.print("New Age: ");
                            newAge = Integer.parseInt(scanner.nextLine());
                            // if newAge is positive
                            if (newAge > 0) {
                                // set validNewAge to true if user entered valid input
                                validNewAge = true;
                                // else print messages and prompt user to try again
                            } else {
                                System.out.println("Age must be a positive number.");
                                System.out.print("Would you like to try again? (y/n): ");
                                String again = scanner.nextLine().trim().toLowerCase();
                                if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                    System.out.println("Returning to menu.");
                                    break; // Stop modifying the pet
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a whole number for the pet's age. Example 1 (not one).");
                            System.out.print("Would you like to try again? (y/n): ");
                            String again = scanner.nextLine().trim().toLowerCase();
                            if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                System.out.println("Returning to menu.");
                                break; // Stop modifying the pet
                            }
                        }
                    }

                    // return back to menu if new age is not valid
                    if (!validNewAge) {
                        continue;
                    }

                    // set default newWeight and validNewWeight to false
                    double newWeight = 0.0;
                    boolean validNewWeight = false;

                    // while user input is not a valid input
                    while (!validNewWeight) {
                        // surround in a try block in case of errors
                        try {
                            // prompt user to enter new weight
                            System.out.print("New Weight (kilograms): ");
                            newWeight = Double.parseDouble(scanner.nextLine());
                            // check if newWeight is positive
                            if (newWeight > 0) {
                                // set validNewWeight to true if user entered valid input
                                validNewWeight = true;
                            } else {
                                System.out.println("Weight must be a positive number.");
                                System.out.print("Would you like to try again? (y/n): ");
                                String again = scanner.nextLine().trim().toLowerCase();
                                if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                    System.out.println("Returning to menu.");
                                    break; // Stop modifying the pet
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number value for the pet's weight. Example 1 (not one).");
                            System.out.print("Would you like to try again? (y/n): ");
                            String again = scanner.nextLine().trim().toLowerCase();
                            if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                System.out.println("Returning to menu.");
                                break; // Stop modifying the pet
                            }
                        }
                    }

                    // return back to menu if new weight is not valid
                    if (!validNewWeight) {
                        continue;
                    }

                    clinic.modifyPet(oldName, newAge, newColour, newWeight);
                    break;
                }


                // case four which allows user to view all pets
                case 4:
                    // if the list is empty print a message to user
                    if (clinic.getPets().isEmpty()) {
                        System.out.println("There are no pets to view.");
                        // else let user view all pets
                    } else {
                        clinic.viewAllPets();
                    }
                    break; // exit case four and stop viewing pets


                // case five which reports details to user
                case 5:
                    // if list is empty print a message to user
                    if (clinic.getPets().isEmpty()) {
                        System.out.println("There are no pets to generate a report.");
                        // else report details to user
                    } else {
                        clinic.reportDetails();
                    }
                    break; // exit case five and stop displaying report


                // case six which allows user to search for a pet
                case 6: {
                    // set empty String key and boolean validKey to false
                    String key = "";
                    boolean validKey = false;

                    // while user input is not a validKey
                    while (!validKey) {
                        // prompt user to search by name or colour
                        System.out.print("Search by name or colour: ");
                        key = scanner.nextLine().trim();

                        // if user enters menu, return to menu
                        if(key.equalsIgnoreCase("menu")) {
                            System.out.println("Returning to menu.");
                            break;
                        }

                        // if the input is empty, print message
                        if (key.isEmpty()) {
                            System.out.println("Search term cannot be blank.");
                            // else if input is not a valid word, print message
                        } else if (!key.matches(".*[a-zA-Z].*")) {
                            System.out.println("Search term must be a valid word. Example, John or white.");
                        } else {
                            validKey = true;
                        }
                        if (!validKey) {
                            // allow user to try again if they wish
                            System.out.print("Would you like to try again? (y/n): ");
                            String again = scanner.nextLine().trim().toLowerCase();
                            if (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("yes")) {
                                System.out.println("Returning to menu.");
                                break; // exit the loop
                            }
                        }
                    }

                    if (validKey) {
                        // Use the PetClinic's searchPets method!
                        clinic.searchPets(key);
                    }
                    break;
                }

                // case seven which allows user to save data and exit the program
                case 7:
                    // save clinic data
                    clinic.saveClinicData();
                    System.out.println("Data saved. Exiting menu.");
                    return;
                    // default case of switch to print a message to user
                // ensuring they enter a valid option
                default:
                    System.out.println("Invalid option. Please enter a whole number between 1 and 7.");
            }
        }
    }
}
