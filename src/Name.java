/**
 * A class that stores a name (first, last).
 * 
 * @author Mustafa Faqiryar
 */
public class Name {
    /** The first name. */
    private final String firstName;
    /** The last name. */
    private final String lastName;

    /**
     * Creates a new name with the given first and last name.
     * 
     * @param firstName The first name.
     * @param lastName The last name.
     * @throws IllegalArgumentException If either the first or last name is null or empty.
     */
    public Name(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("The first and last name must not be null.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the first name.
     * 
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name.
     * 
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the full name.
     * 
     * @return The full name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Name otherName = (Name)obj;
        return otherName.getFullName().equals(this.getFullName());
    }

    @Override
    public int hashCode() {
        return (firstName.hashCode() * 31) + lastName.hashCode();
    }
}