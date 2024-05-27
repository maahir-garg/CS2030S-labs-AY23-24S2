/**
 * A generic box storing an item.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */

public class Box<T> {

  private final T content;
  private static final Box<? extends Object> EMPTY_BOX = new Box<>(null);

  private Box(T content) {
    this.content = content;
  }

  public static <T> Box<T> of(T content) {
    if (content == null) {
      return null;
    }
    return new Box<T>(content);
  }

  public static <T> Box<T> ofNullable(T content) {
    if (content == null) {
      return Box.<T>empty();
    }

    return Box.of(content);
  }

  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
    Box<T> temp = (Box<T>) EMPTY_BOX;
    return temp;
  }

  public boolean isPresent() {
    return !(this.content == null);
  }

  public T getContent() {
    return this.content;
  }

  public Box<T> filter(BooleanCondition<? super T> con) {
    if (this == Box.<T>empty()) {
      return this;
    }
    if (con.test(this.getContent())) {
      return this;
    } else {
      return Box.<T>empty();
    }
  }

  public <U> Box<U> map(Transformer<? super T, U> transformer) {
    if (this == Box.<T>empty()) {
      return Box.<U>empty();
    }

    return Box.<U>ofNullable(transformer.transform(this.getContent()));
  }

  @Override
  public String toString() {
    if (this.isPresent()) {
      return "[" + this.content + "]";
    } else {
      return "[]";
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o instanceof Box<?>) {
      Box<?> compareWith = (Box<?>) o;
      if (this.getContent() == compareWith.getContent()) {
        return true;
      }
      if (this.getContent() == null || compareWith.getContent() == null) {
        return false;
      }
      return this.getContent().equals(compareWith.getContent());
    }

    return false;
  }

}



