class CircularDoublyLinkedListTestDrive {
    public static void main(String...args) {
        CircularDoublyLinkedList<String> linkedList = new CircularDoublyLinkedList<String>();
        linkedList.addNode("hello");
        
        linkedList.printAllClockWise();
        linkedList.addNode("bye");
        linkedList.printAllClockWise();
        linkedList.addNode("tata");
        linkedList.printAllClockWise();
        linkedList.addNodeAtFirst("sayionara");
        linkedList.printAllClockWise();
        linkedList.addNodeAtFirst("ritvik");
        linkedList.printAllClockWise();
        linkedList.remove("dcsdc");
        linkedList.printAllClockWise();
        /*linkedList.remove("sayionara");
        linkedList.printAllClockWise();*/
    }
}