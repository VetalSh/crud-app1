package ua.vetal.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.vetal.springcourse.dao.PersonDAO;
import ua.vetal.springcourse.models.Person;

@Component
public class PersonValidator implements Validator {
  private final PersonDAO personDAO;

  public PersonValidator(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return Person.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    Person person = (Person) o;

    if(personDAO.show(person.getEmail()).isPresent())
      errors.rejectValue("email", "", "This email is already taken");
  }
}
