class trial<E> {
    Node<E> head;
    Node<E> last;
    int count;

    public void insertBeginning(E data) {
        Node node = new Node(data);
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        if (head == null) {

            node.setPrev(node);
            node.setNext(node);
            head = node;
            last = node;
            count++;
        }
        else {
            
            node.setNext(head);
            node.setPrev(last);
            head.setPrev(node);
            last.setNext(node);
            head = node;
            count++;
        }

    }

    public void printAll() {
        Node<E> temp = head;
        while (temp != last) {
            System.out.println("->"+temp.getPoint());
            temp = temp.getNext();
        }
        System.out.println();
    }

    private class Node<E> {
        E data;
        Node prev, next;
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        public Node(E data) {
            this.data = data;
            prev = null;
            next = null;
        }

        public E getPoint() {
            return data;
        }

        public void setPoint(E data) {
            this.data = data;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


}