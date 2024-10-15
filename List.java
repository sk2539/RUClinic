import java.util.Iterator;

public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;
    int NOT_FOUND = -1;

    public List() { //default constructor with an initial capacity of 4.
        objects = (E[]) new Object[4];
        size = 0;
    }

    private int find(E e) {
        for (int i = 0; i<size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        E[] resizedArray= (E[]) new Object[objects.length + 4];
        for (int i = 0; i < size; i++) {
            resizedArray[i] = objects[i];
        }
        objects = resizedArray;
    }

    public boolean contains(E e) {
        if (objects == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (objects[i] != null && objects[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Null elements are not allowed.");
        }
        if (size == objects.length) {
            grow();
        }
        if (!contains(e)) {
            objects[size] = e;
            size++;
        }
    }

    public void remove(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                for (int j = i; j < size - 1; j++) {
                    objects[j] = objects[j + 1];
                }
                objects[size - 1] = null;
                size--;
                return;
            }
        }
    }

    public boolean isEmpty() {
        if(objects == null || size == 0)
        {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    //figure this out
    public Iterator<E> iterator() {
        return new ListIterator<>();
    }

    public E get(int index) { //return the object at the index
        return objects[index];
    }

    public void set(int index, E e) { //put object e at the index
        if(size > index)
        {
            objects[index] = e;
        }
    }

    public int indexOf(E e) { //return the index of the object or return -1
        return find(e);
    }

    //figure this out
    private class ListIterator<E> implements Iterator<E> {
        int currIndex = 0;

        public boolean hasNext() { //return false if it’s empty or end of list
            if(currIndex == 0 || currIndex == objects.length)
            {
                return false;
            }
            return true;
        }

        public E next() { //return the next object in the list
            E returnNext = (E) objects[currIndex];
            currIndex++;
            return returnNext;
        }
    }
}