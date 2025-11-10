import java.io.*;
import java.util.*;

// create PetClinic class to keep track of all the pets
// and allow the user to add pets etc.
// public so that the main program can create a PetClinic object and use its methods

public class PetClinic {
    // create an array list to store the pets
    private ArrayList<Pet> pets;
    // create instance variable to store name of clinic
    private String clinicName;

    public PetClinic() {
        this.pets = new ArrayList<>();
        this.clinicName ="Pet Clinic";
    }

    // getter method to access the list of pets
    public ArrayList<Pet> getPets() {
        return pets;
    }


    // method to add pet
    public void addPet(Pet pet) {
        // if pet is valid add to the array list and print confirmation
        if (pet.isValid()) {
            pets.add(pet);
            System.out.println(pet.getName() + " added to the clinic!");
            saveClinicData();
            // else print a message informing user pet wasn't added
        } else {
            System.out.println("Pet data is invalid. Pet not added");
        }
    }


    // method to delete the pet
    public void deletePet(String petName) {
        // set boolean found to false
        boolean found = false;
        // Loop through all the pets in the list
        for (Pet pet : pets) {
            // If we find a pet with the same name entered by user
            // remove the pet
            if (pet.getName().equalsIgnoreCase(petName)) {
                pets.remove(pet);
                System.out.println(petName + " deleted from the clinic!");
                saveClinicData();
                found = true;
                // stop looking for pet to delete
                break;
            }
        }
        // If pet wasn't found print a message to user
        if (!found) {
            System.out.println(petName + " was not found.");
        }
    }


    // method to modify Pet Details
    // modifyPet constructor
    public void modifyPet(String petName, int newAge, String newColour, double newWeight) {
        // for the pets in array list
        for (Pet pet : pets) {
            // if name matches the name entered by user,
            // allow the user to modify the pet details and print confirmation
            if (pet.getName().equalsIgnoreCase(petName)) {
                pet.age = newAge;
                pet.colour = newColour;
                pet.weight = newWeight;
                System.out.println(petName + "'s details modified!");
                saveClinicData();
                return;
            }
        }
        // if pet name entered by user was not found print a message
        System.out.println(petName + " not found.");
    }


    // method to view all pets
    public void viewAllPets() {
        // for each pet in the pets array list, print pet
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }


    // method to get report details
    public void reportDetails() {
        // set dog and cat count to 0
        int dogCount = 0;
        int catCount = 0;
        // create HashMap to count how many pets have each colour
        // hash map is used to store data in key : value pairs
        Map<String, Integer> colourCounts = new HashMap<>();

        // for each pet in pets array list
        for (Pet pet : pets) {
            // if pet is a dog increment dog count
            if (pet instanceof Dog) {
                dogCount++;
                // else if pet is a cat increment cat count
            } else if (pet instanceof Cat) {
                catCount++;
            }
            // record the pet colour in the HashMap and increment the count
            // checks the colour count, and if the colour does not yet exist, it starts at 0, then adds 1 each time colour is found
            colourCounts.put(pet.getColour(), colourCounts.getOrDefault(pet.getColour(), 0) + 1);
        }

        // set dominant colour to a default value and set maxCount to 0
        String dominantColour = "No colours";
        int maxCount = 0;

        // for each colour and its count in HashMap,
        // get the colour and the number of pets with that colour
        for (Map.Entry<String, Integer> entry : colourCounts.entrySet()) {
            String colour = entry.getKey();
            int count = entry.getValue();

            // if the count of that colour is bigger than the maxCount
            if (count > maxCount) {
                // maxCount is the current count
                maxCount = count;
                // dominantColour is the colour with the highest count
                dominantColour = colour;
            }
        }

        // print report details
        System.out.println("Clinic Name: " + clinicName);
        System.out.println("Cats: " + catCount);
        System.out.println("Dogs: " + dogCount);
        System.out.println("Dominant Pet Colour: " + dominantColour);
    }


    // method to search Pets
    public void searchPets(String searchString) {
        // set counter to keep track of found pets
        int foundCount = 0;

        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(searchString) || pet.getColour().equalsIgnoreCase(searchString)) {
                System.out.println(pet);
                System.out.println(pet.speak());
                // increase foundCount if a pet is found.
                foundCount++;
            }
        }

        // if no pets were found, print message
        if (foundCount == 0) {
            System.out.println("No pets found.");
        }
    }


    // method to save clinic data to file
    public void saveClinicData() {
        try (PrintWriter clinicWriter = new PrintWriter("ClinicDetails.txt");
             PrintWriter petWriter = new PrintWriter("PetDetails.txt")) {

            // Calculate pet counts and dominant color
            int dogCount = 0;
            int catCount = 0;
            Map<String, Integer> colourCounts = new HashMap<>();

            // for each pet in Pets list
            // if it is an instance of Dog class, increment count
            for (Pet pet : pets) {
                if (pet instanceof Dog) {
                    dogCount++;
                    // same for catCount
                } else if (pet instanceof Cat) {
                    catCount++;
                }
                // checks the colour count, and if the colour does not yet exist, it starts at 0, then adds 1 each time colour is found
                colourCounts.put(pet.getColour(), colourCounts.getOrDefault(pet.getColour(), 0) + 1);
            }

            // set dominant colour to default value and max count to 0
            String dominantColour = "No colours";
            int maxCount = 0;

            // for every key pair in the hash map, get the colour and count
            for (Map.Entry<String, Integer> entry : colourCounts.entrySet()) {
                String colour = entry.getKey();
                int count = entry.getValue();

                // if the count for that colour is greater than the max, it is the new max
                if (count > maxCount) {
                    maxCount = count;
                    dominantColour = colour;
                }
            }

            // Write clinic details
            clinicWriter.println("Clinic Name: " + clinicName +
                    ", Cats: " + catCount +
                    ", Dogs: " + dogCount +
                    ", Dominant Pet Colour: " + dominantColour);

            // for each pet in pets array list
            for (Pet pet : pets) {
                // if pet is valid then add to file
                if (pet.isValid()) {
                    petWriter.println(pet.toFileString());
                }
            }

            // catch exceptions when saving data to file
        } catch (IOException e) {
            // print an error message
            System.out.println("Error saving data: " + e.getMessage());
        }
    }


    // method to load clinic data from file
    public void loadClinicData() {
        // try in case of exception
        // scanner to read the clinic and pet detail files
        try (Scanner clinicScanner = new Scanner(new File("ClinicDetails.txt"));
             Scanner petScanner = new Scanner(new File("PetDetails.txt"))) {

            // if there is more lines in Clinic Details file, read it
            if (clinicScanner.hasNextLine()) {
                String clinicData = clinicScanner.nextLine();
                //Split the line at the commas.
                String [] parts = clinicData.split(",");
                //set the clinic name to the first part of the line
                // substring is used to remove label 'clinic name'
                clinicName = parts[0].substring(12);
            }

            // while there are more lines in Pet Details file, read it
            while (petScanner.hasNextLine()) {
                String petData = petScanner.nextLine();
                // Split the lines into parts
                String[] petParts = petData.split(",");

                // if the first part of the line is Dog
                if (petParts[0].equalsIgnoreCase("Dog")) {
                    // create a new Dog object and add it to pets list
                    // split it into type, name, age, colour, weight, type
                    pets.add(new Dog(petParts[1], Integer.parseInt(petParts[2]), petParts[3], Double.parseDouble(petParts[4]), petParts[5]));
                    // else if the first part of the line is Cat
                } else if (petParts[0].equalsIgnoreCase("Cat")) {
                    // create a new Cat object and add it to pets list
                    pets.add(new Cat(petParts[1], Integer.parseInt(petParts[2]), petParts[3], Double.parseDouble(petParts[4]), petParts[5]));
                }
            }

            // catch exceptions when loading data from file
        } catch (IOException e) {
            // print an error message
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}