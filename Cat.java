// create subclass Cat which extends from the parent class Pet
// public so that other parts of the program can create and use Cat objects

public class Cat extends Pet {
    // additional instance variable for the Cat subclass
    // final because breed cannot be modified
    private final String breed;

    // Constructor for Cat class
    public Cat(String name, int age, String colour, double weight, String breed) {
        // call to the Pet class constructor and set breed for cat object
        super(name, age, colour, weight);
        this.breed = breed;
    }

    // Override the speak method from Pet class
    // Return a string that represents the cat
    @Override
    public String speak() {
        return "Miaow! I am " + name + ", a " + age + " year old " + breed;
    }

    // Override the toString method from Pet class
    // Return a string representation of the Cat object
    @Override
    public String toString() {
        return "Cat- " + super.toString() + ", Breed: " + breed;
    }

    // Override the toFileString method from Pet class
    // Return a string representation of the Cat object
    // Used for returning a string when saving data to a file
    @Override
    public String toFileString() {
        return "Cat," + name + "," + age + "," + colour + "," + weight + "," + breed;
    }
}