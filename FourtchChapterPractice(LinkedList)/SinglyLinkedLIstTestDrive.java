public class SinglyLinkedLIstTestDrive {
    public static void main (String ... args) {
        SinglyLinkedList<String> sList = new SinglyLinkedList<String>();
        sList.add("Hello");
        sList.printAll();
        sList.add("bye1");
        sList.printAll();
        sList.add("bye2");
        sList.printAll();
        sList.remove("Hello");
        sList.printAll();
        sList.remove("bye1");
        sList.printAll();
        System.out.print(sList.getCount());
    }
}