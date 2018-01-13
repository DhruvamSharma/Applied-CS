import java.util.*;
class Stack<T> {

    private T mdata;
    private LinkedList<Node> linkedList = new LinkedList<>(); 
    public Stack( ) {
        
    }

    public T push(T data) {
        Node node = new Node();
        node.setData(data);
        if ( linkedList.size() == 0) {
            node.next = null;
        }
        else {
            Node nodeTemp = linkedList.getLast();
            nodeTemp.next = node;
            node.next = null;
        }
        linkedList.addLast(node);
        return (T)(linkedList.getLast().getData());
    }

    public T pop() {
        Node tempNode;
        if (linkedList.isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            tempNode = linkedList.remove(linkedList.size()-1);
        }
        return (T)tempNode.getData();
    }

    public int size() {
        return linkedList.size();
    }

    public T peek() {
        Node tempNode;
        if (linkedList.isEmpty()) {
            return null;
        }
        else {
            tempNode = linkedList.peekLast();
        }
        return (T) tempNode.getData();
    }



    class Node<T> {
        private T data;
        private Node<T> next;
        
        /**
         * @param data the data to set
         */
        public void setData(T data) {
            this.data = data;
        }
    
        /**
         * @return the data
         */
        public T getData() {
            return data;
        }
    
        /**
         * @param next the next to set
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }
    
        /**
         * @return the next
         */
        public Node<T> getNext() {
            return next;
        }

        
    }
}

