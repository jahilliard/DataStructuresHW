public class DNode<E>
{
  private DNode<E> prev;
  private E data;
  private DNode<E> next;
  
  public DNode(E data)
  {
    this.data = data;
  }
  
  public DNode<E> getPrev()
  {
    return prev;
  }

  public E getData()
  {
    return data;
  }

  public DNode<E> getNext()
  {
    return next;
  }
  
  public void setPrev(DNode<E> prev)
  {
    this.prev = prev;
  }

  public void setNext(DNode<E> next)
  {
    this.next = next;
  }
}