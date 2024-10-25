package clinic.src.util;
import clinic.src.*;

/**
 * The clinic.src.util.CircularLinkedList class represents a circular linked list of technicians.
 * It allows for adding, removing, and displaying technicians in a circular manner.
 * The head and tail nodes are linked to each other to form a circular structure.
 */
public class CircularLinkedList {
    private Node head;
    private Node tail;
    private int size = 0;

    /**
     * Default constructor that initializes an empty circular linked list.
     */
    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Sets the head node of the circular linked list and ensures the circular structure is maintained.
     *
     * @param head The node to be set as the head of the list.
     */
    public void setHead(Node head) {
        this.head = head;
        if (tail != null) {
            this.tail.next = this.head;
        }
    }

    /**
     * Sets the tail node of the circular linked list.
     *
     * @param tail The node to be set as the tail of the list.
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * Retrieves the head node of the circular linked list.
     *
     * @return The head node of the list.
     */
    public Node getHead() {
        return this.head;
    }

    /**
     * Retrieves the tail node of the circular linked list.
     *
     * @return The tail node of the list.
     */
    public Node getTail() {
        return this.tail;
    }

    /**
     * Adds a new technician to the circular linked list. If the list is empty, the new technician becomes
     * both the head and tail. Otherwise, it inserts the technician at the beginning and maintains the circular structure.
     *
     * @param technician The technician to be added to the list.
     */
    public void addTechnician(clinic.src.Technician technician) {
        Node newNode = new Node(technician);

        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
        size++;
    }

    /**
     * Removes and returns the technician at the head of the circular linked list. If the list is empty,
     * it returns null. After removing the technician, the circular structure is maintained.
     *
     * @return The technician that was removed, or null if the list is empty.
     */
    public clinic.src.Technician removeTechnician() {
        if (head == null) {
            System.out.println("clinic.src.util.List is empty. No technician to remove.");
            return null;
        }

        Technician removedTechnician = head.technician;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }
        size--;
        return removedTechnician;
    }

    /**
     * Displays the list of technicians in the circular linked list, showing their names and locations.
     * If the list is empty, it prints a message indicating so.
     */
    public void display() {
        System.out.println();
        System.out.println("Rotation list for the technicians.");
        if (head == null) {
            System.out.println("clinic.src.util.List is empty.");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.technician.getProfile().getFirstName().toUpperCase() + " "
                    + current.technician.getProfile().getLastName().toUpperCase() + " ("
                    + current.technician.getProvider().getLocation().toString().toUpperCase() + ")");

            current = current.next;

            if (current != head) {
                System.out.print(" --> ");
            }
        } while (current != head);

        System.out.println();
    }

    public int getSize()
    {
        return size;
    }
}