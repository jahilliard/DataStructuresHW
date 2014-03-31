import java.util.*;

public class LinkedCycle<E>
{
  //leave these fields public so that we can test your code
  public DNode<E> currNode;
  public int numElements;
  
  public LinkedCycle()
  {
    currNode = null;
    numElements = 0;
  }
  
  public int size()
  {
    return numElements;
  }
  
  public E get()
  {
    if (numElements == 0)
    {
      throw new NoSuchElementException();
    }
    return currNode.getData();
  }
  
  public void add(E obj)
  {
    if (numElements == 0)
    {
      DNode<E> temp = new DNode<E>(obj);
      currNode = temp;
      currNode.setNext(currNode);
      currNode.setPrev(currNode);
      numElements++;
    }
    else
    {
      DNode<E> temp = new DNode<E>(obj);
      temp.setNext(currNode.getNext());
      currNode.getNext().setPrev(temp);
      currNode.setNext(temp);
      temp.setPrev(currNode);
      currNode = temp;
      numElements ++;
    }
  }
  
  public List<E> toList()
  {
    List<E> temp = new ArrayList<E>();
    DNode<E> loop = currNode;
    for (int i=0; i < numElements; i++)
    {
      temp.add(loop.getNext().getData());
      loop = loop.getNext();
    }
    return temp;
  }
  
  public boolean contains(E obj)
  {
    DNode<E> loop = currNode;
    for (int i = 0; i < numElements; i++)
    {
      if (obj == loop.getData())
      {
        return true;
      }
      loop = loop.getNext();
    }
    return false;
  }
  
  public void scroll(int n)
  {
    if (numElements == 0)
    {
      throw new NoSuchElementException();
    }
    else if ((numElements == 1) || (n ==0))
    {
    }
    else if (n > 0)
    {
      for (int i = 0; i < n; i++)
      {
        currNode = currNode.getNext();
      }
    }
    else if (n < 0)
    {
      for (int i = 0; i > n; i--)
      {
        currNode = currNode.getPrev();
      }
    }
  }

  public E remove()
  {
    if (numElements == 0)
    {
      throw new NoSuchElementException();
    }
    else if (numElements == 1)
    {
      E temp = currNode.getData();
      currNode.setNext(null);
      currNode.setPrev(null);
      numElements--;
      return temp;
    }
    E temp = currNode.getData();
    DNode<E> remove = currNode;
    currNode = currNode.getPrev();
    currNode.setNext(currNode.getNext().getNext());
    currNode.getNext().setPrev(currNode);
    numElements --;
    return temp;
  }
}