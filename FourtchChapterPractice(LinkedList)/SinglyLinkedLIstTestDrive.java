public class SinglyLinkedLIstTestDrive {
    public static void main (String ... args) {
        SinglyLinkedList<String> sList = new SinglyLinkedList<String>();
        sList.add("Hello");
        sList.printAll();
        sList.add("bye");
        sList.printAll();
        sList.remove("Hello");
        sList.printAll();
        sList.remove("bye");
        sList.printAll();
        System.out.print(sList.getCount());
    }
}