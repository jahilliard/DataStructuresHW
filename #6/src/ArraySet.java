public class ArraySet
{
  //leave these fields public so that we can test your code
  public Comparable[] data;
  public int numElements;
  
  public ArraySet()
  {
    data = new Comparable[2];
    numElements = 0;
  }
  
  public int size()
  {
    return numElements;
  }
  
  public boolean add(Comparable obj)
  {
    if (contains(obj) == true)
    {
      return false;
    }
    if (numElements == data.length)
    {
      Comparable[] temp = new Comparable[data.length * 2];
      for (int i = 0; i < numElements; i++)
      {
        temp[i] = data[i];
      }
      data = temp;
    }
    if (numElements == 0)
    {
      data[0] = obj;
      numElements ++;
      return true;
    }
    else
    {
      int x = numElements - 1;
      while ((x >= 0) && (obj.compareTo(data[x]) < 0))
      {
        data[x+1] = data[x];
        x--;
      }
      data[x+1] = obj;
      numElements ++;
      return true;
    }
  }
  
  public boolean contains(Comparable obj)
  {
    int low = 0;
    int high = numElements-1;
    int mid = (low + high)/2;
    while (low <= high)
    {
      if (obj.compareTo(data[mid]) == 0)
      {
        return true;
      }
      else if (obj.compareTo(data[mid]) > 0)
      {
        low = mid + 1;
        mid = (high + low)/2;
      }
      else 
      {
        high = mid - 1;
        mid = (high + low)/2;
      }
    }
    return false;
  }
  
  public boolean remove(Comparable obj)
  {
    if (contains(obj) == false)
    {
      return false;
    }
    int x = 0;
    while ((obj.compareTo(data[x]) != 0))
    {
      x++;
    }
    while (x < numElements)
    {
      data[x] = data[x+1];
      x++;
    }
    numElements --;
    return true;
  }
  
  public String toString()
  {
    String s = "[";
    for (int i = 0; i < numElements - 1; i++)
      s += data[i] + ", ";
    if (numElements > 0)
      s += data[numElements - 1];
    return s + "]";
  }
}