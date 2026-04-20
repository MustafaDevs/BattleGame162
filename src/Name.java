/**
 * A class that stores a name (first, last).
 * 
 * @author Mustafa Faqiryar
 */
public class Name {
    // Class Invariants:
    // - firstName cannot be null or empty
    // - lastName cannot be null or empty

    /** The first name. */
    private final String firstName;
    /** The last name. */
    private final String lastName;

    /**
     * Creates a new name with the given first and last name.
     * 
     * @param firstName The first name.
     * @param lastName  The last name.
     * @throws IllegalArgumentException If either the first or last name is null or
     *                                  empty.
     */
    public Name(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("The first and last name must not be null.");
        }
        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("The first and last name must not be empty.");
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
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Name otherName = (Name) obj;
        if (!this.firstName.equals(otherName.firstName))
            return false;

        return this.lastName.equals(otherName.lastName);
    }

    @Override
    public int hashCode() {
        return (firstName.hashCode() * 31) + lastName.hashCode();
    }
}
