package common;

/**
 * Created by shahbaaz on 1/3/17.
 */
public class Queue<T> {

    private T[] array;

    //Points to the enqueueing end of the queue
    private int front = -1;

    //Points to the dequeueing end of the queue
    private int back = 0;

    private int size = 0;
    private int maxSize = 5;

    public Queue () {
        array = (T []) new Object[maxSize];
    }

    public void enqueue(T object) {
        if (size == maxSize) {
            maxSize *= 2;
            T[] newArray = (T []) new Object[maxSize];

            int j = 0;
            for (int i = back; i < array.length; i++) {
                newArray[i - back] = array[back];
                j++;
            }
            for (int i = 0; i <= front; i++) {
                newArray[j] = array[i];
                j++;
            }
            front = array.length-1;
            array = newArray;
            back = 0;
            front++;
            array[front] = object;
        } else {
            if (front == array.length - 1) {
                front = 0;
            } else {
                front++;
            }
            size++;
            array[front] = object;
        }

    }

    public T dequeue() {
        size--;
        T element = array[back];
        if (back == array.length-1) {
            back = 0;
        } else {
            back++;
        }
        return element;
    }

    public T peekFront() {
        return array[front];
    }

    public T peekBack() {
        return array[back];
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Queue<Integer> Q = new Queue<Integer>();

        Q.enqueue(1);
        Q.enqueue(2);
        Q.enqueue(3);
        Q.enqueue(4);
        Q.enqueue(5);

        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());

        Q.enqueue(1);
        Q.enqueue(2);
        Q.enqueue(3);
        Q.enqueue(4);
        Q.enqueue(5);

        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());
        System.out.println(Q.dequeue());
    }
}

