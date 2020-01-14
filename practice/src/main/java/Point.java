import java.util.Objects;

public class Point {
  private String name;

  public Point(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point)) return false;
    Point point = (Point) o;
    return Objects.equals(getName(), point.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public String toString() {
    return "Point{" +
      "name='" + name + '\'' +
      '}';
  }
}
