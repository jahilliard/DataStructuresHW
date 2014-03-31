public class Movie implements Comparable
{
  private String title;
  private int year;
  
  public Movie(String title, int year)
  {
    this.title = title;
    this.year = year;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public int getYear()
  {
    return year;
  }
  
  public String toString()
  {
    return title + " (" + year + ")";
  }

  public boolean equals(Object other)
  {
    Movie movie = (Movie)other;
    return title.equals(movie.getTitle()) && year == movie.getYear();
  }
  
  public int compareTo(Object other)
  {
    Movie movie = (Movie)other;
    if ((title.equals(movie.getTitle()) && (year == movie.getYear())))
    {
      return 0;
    }
    else if (year != movie.getYear())
    {
      return (movie.getYear() - year);
    }
    else
    {
      return title.compareTo(movie.getTitle());
    }
  }
}