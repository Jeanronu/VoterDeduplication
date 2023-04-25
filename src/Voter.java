/**
 * The Voter class represents a registered voter with first name, middle name, last name, and birthday.
 */
public class Voter implements Comparable<Voter> {
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthday;

    /**
     * Constructs a Voter object with the given first name, middle name, last name, and birthday.
     * @param firstName the first name of the voter
     * @param middleName the middle name of the voter
     * @param lastName the last name of the voter
     * @param birthday the birthday of the voter in format "yyyy-mm-dd"
     */
    public Voter(String firstName, String middleName, String lastName, String birthday) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    /**
     * Returns the first name of the voter.
     * @return the first name of the voter
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the middle name of the voter.
     * @return the middle name of the voter
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Returns the last name of the voter.
     * @return the last name of the voter
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the birthday of the voter in format "yyyy-mm-dd".
     * @return the birthday of the voter
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Compares this voter with the specified voter for order.
     * @param other the voter to be compared
     * @return a negative integer, zero, or a positive integer as this voter is less than, equal to, or greater than the specified voter
     */
    @Override
    public int compareTo(Voter other) {
        int lastNameComparison = this.lastName.compareTo(other.getLastName());
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        int firstNameComparison = this.firstName.compareTo(other.getFirstName());
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }
        return 0;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Voter)) {
            return false;
        }

        Voter other = (Voter) obj;
        return this.firstName.equals(other.getFirstName()) &&
                this.middleName.equals(other.getMiddleName()) &&
                this.lastName.equals(other.getLastName()) &&
                this.birthday.equals(other.getBirthday());
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return firstName.hashCode() + middleName.hashCode() + lastName.hashCode() + birthday.hashCode();
    }

    /**
     * Returns the string representation of this voter in the format "lastName, firstName".
     * @return the string representation of this voter
     */
    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
}