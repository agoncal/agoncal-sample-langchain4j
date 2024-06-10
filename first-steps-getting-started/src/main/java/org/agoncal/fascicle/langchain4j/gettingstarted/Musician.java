package org.agoncal.fascicle.langchain4j.gettingstarted;

// tag::adocSnippet[]
public class Musician {

  private String firstName;
  private String lastName;
  private String bio;

  // Constructors, getters, setters, toString

  // tag::adocSkip[]

  public Musician(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  @Override
  public String toString() {
    return "Musician{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", bio='" + bio + '\'' +
      '}';
  }

  // end::adocSkip[]
}
// end::adocSnippet[]
