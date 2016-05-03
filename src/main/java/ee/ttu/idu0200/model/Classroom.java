package ee.ttu.idu0200.model;

public class Classroom {

  private long id;
  private String title;
  private int seatingCapacity;
  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSeatingCapacity() {
    return seatingCapacity;
  }

  public void setSeatingCapacity(int seatingCapacity) {
    this.seatingCapacity = seatingCapacity;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
