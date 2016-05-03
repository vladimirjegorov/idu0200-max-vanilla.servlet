package ee.ttu.idu0200.validator;

import ee.ttu.idu0200.form.ClassroomForm;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class ClassroomFormValidator {

  public Map<String, String> validate(ClassroomForm classroomForm) {
    Map<String, String> bindingResult = new HashMap<>();

    validateSeatingCapacity(classroomForm.getSeatingCapacity(), bindingResult);
    validateTitle(classroomForm.getTitle(), bindingResult);
    validateDescription(classroomForm.getDescription(), bindingResult);

    return bindingResult;
  }

  void validateSeatingCapacity(String seatingCapacityString, Map<String, String> bindingResult) {
    if (isBlank(seatingCapacityString)) {
      bindingResult.put("classroom.seatingCapacity", "classroom.seatingCapacity.empty");
    } else {
      try {
        int seatingCapacity = parseInt(seatingCapacityString);
        if (seatingCapacity < 0) {
          bindingResult.put("classroom.seatingCapacity",  "classroom.seatingCapacity.tooLow");
        }
        if (seatingCapacity > 20) {
          bindingResult.put("classroom.seatingCapacity",  "classroom.seatingCapacity.tooMuch");
        }
      } catch (NumberFormatException e) {
        bindingResult.put("classroom.seatingCapacity", "classroom.seatingCapacity.invalidFormat");
      }
    }
  }

  void validateTitle(String title, Map<String, String> bindingResult) {
    if (isEmpty(title)) {
      bindingResult.put("classroom.title", "classroom.title.empty");
    } else {
      if (title.length() > 100) {
        bindingResult.put("classroom.title", "classroom.title.tooLong");
      }
    }
  }

  void validateDescription(String description, Map<String, String> bindingResult) {
    if (isEmpty(description)) {
      bindingResult.put("classroom.description", "classroom.description.empty");
    } else {
      if (description.length() > 500) {
        bindingResult.put("classroom.description", "classroom.description.tooLong");
      }
    }
  }
}
