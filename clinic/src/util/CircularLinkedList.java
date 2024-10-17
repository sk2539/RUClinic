package clinic.src.util;
import clinic.src.*;

public class CircularLinkedList {
    private Node head;
    private Node tail;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void setHead(Node head){
        this.head = head;
        if(tail != null){
            this.tail.next = this.head;
        }
    }

    public void setTail(Node tail){
        this.tail = tail;
    }

    public Node getHead(){
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public void addTechnician(clinic.src.Technician technician) {
        Node newNode = new Node(technician);

        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

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

        return removedTechnician;
    }

    // MAKE SURE YOU CAN PRINT HERE - if not, put this method in clinic.src.ClinicManager
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

}