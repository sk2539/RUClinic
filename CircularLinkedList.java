public class CircularLinkedList {
    private Node head;
    private Node tail;

    private class Node {
        Technician technician;
        Node next;

        Node(Technician technician) {
            this.technician = technician;
            this.next = null;
        }
    }

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addTechnician(Technician technician) {
        Node newNode = new Node(technician);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
    }

    public Technician removeTechnician() {
        if (head == null) {
            System.out.println("List is empty. No technician to remove.");
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

    // MAKE SURE YOU CAN PRINT HERE - if not, put this method in ClinicManager
    public void display() {
        System.out.println();
        System.out.println("Rotation list for the technicians.");
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.technician.profile.getFirstName().toUpperCase() + " "
                    + current.technician.profile.getLastName().toUpperCase() + " ("
                    + current.technician.getProvider().getLocation().toString().toUpperCase() + ")");

            current = current.next;

            if (current != head) {
                System.out.print(" --> ");
            }
        } while (current != head);

        System.out.println();
    }

}
