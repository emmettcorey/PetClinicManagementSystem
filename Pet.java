// create abstract Pet class. Set access modifier to abstract
// so that it allows subclasses to have common variables in one place
// and allows for easy adding of pets
// public so that other classes like Dog and Cat can use it as their parent class

public abstract class Pet {
    // protected instance variables to be used by subclasses
    // protected means that they can be accessed by subclasses but not everywhere
    // this prevents them from being modified in the Main class
    protected String name;
    protected int age;
    protected String colour;
    protected double weight;

    // constructor for pet class
    public Pet(String name, int age, String colour, double weight) {
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
    }

    // abstract method ensures that it must be defined in subclasses
    public abstract String speak();

    // method to check for errors in the user input
    boolean isValid() {

        // set variable valid to true
        boolean valid = true;

        // check if any of the fields are left blank, and inform the user
        if (name.isEmpty()) {
            System.out.println("You left the name field blank, the pet details wont be saved");
        }

        if (colour.isEmpty()) {
            System.out.println("You left the colour field blank, the pet details wont be saved");
        }

        // check for errors in the user input value for age
        try {
            if (age <= 0) {
                System.out.println("The pet's age cannot be less than zero");
                valid = false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number value for the pet's age");
            valid = false;
        }

        // check for errors in the user input value for weight
        try {
            if (weight <= 0) {
                System.out.println("The pet's weight cannot be less than zero");
                valid = false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number value for the pet's weight");
            valid = false;
        }
        // if values entered cause no errors, return true
        return valid;
    }

    // method to get the name of the pet
    public String getName() {
        return name;
    }

    // method to get the colour of the pet
    public String getColour() {

        return colour;
    }

    // method to return the pet details
    @Override
    public String toString() {
        return name + ", Age: " + age + ", Colour: " + colour + ", Weight: " + weight +"kg";
    }

    // abstract method ensures that it must be defined in subclasses
    public abstract String toFileString();
}