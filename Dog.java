// create subclass Dog which extends from the parent class Pet
// public so that other parts of the program can create and use Dog objects

public class Dog extends Pet {
    // additional instance variable for the Dog subclass
    // final because breed cannot be modified
    private final String breed;

    // Constructor for dog class
    public Dog(String name, int age, String colour, double weight, String breed) {
        // call to the Pet class constructor and set breed for dog object
        super(name, age, colour, weight);
        this.breed = breed;
    }

    // Override the speak method from Pet class
    // Return a string that represents the dog
    @Override
    public String speak() {
        return "Woof! I am " + name + ", a " + age + " year old " + breed;
    }

    // Override the toString method from Pet class
    // Return a string representation of the Dog object
    @Override
    public String toString() {
        return "Dog- " + super.toString() + ", Breed: " + breed;
    }

    // Override the toFileString method from Pet class
    // Return a string representation of the Dog object
    // Used for returning a string when saving data to a file
    @Override
    public String toFileString() {
        return "Dog," + name + "," + age + "," + colour + "," + weight + "," + breed;
    }
}