import java.util.ArrayList;

class SinglyLinkedList<E> {

    private Node<E> root;
    private int count; 

    public SinglyLinkedList() {
        super();
        root= null;
    }

    public int getCount() {
        return count;
    }

    public boolean add(E info) {
        Node<E> node = new Node<E>(info);
        if (root == null) {
            
            root = node;
            count++;
            return true;
        }
        Node<E> tempNode = root;
        while(tempNode.getNext() != null) {
            tempNode = tempNode.getNext();
        }
        tempNode.setNext(node); 
        count++;
        return true;
    }

    public boolean remove(E info) {
        if (root == null) {
            return false;
        }
       
        
        else if ( count == 1) {
            root = null;
            count--;
            return true;
        } else {
            Node<E> tempNode = root;
            ArrayList<Node<E>> tempArray = new ArrayList<Node<E>>();
            
            while(tempNode.getInfo() != info) {
                
                if (tempArray.isEmpty())
                tempArray.add(0, tempNode);
                else {
                    tempArray.clear();
                    tempArray.add(0, tempNode);
                }
                tempNode = tempNode.getNext();
                
            
            } 
            if (tempNode.getInfo() == info ) {
                
                tempArray.get(0).setNext(tempNode.getNext());
                count--;
                return true;
            } else {
                return false;
            }
        }

        
        
    }

    public void removeAt(int position) {
        position = position > count ? count : position;
        if (root == null)
           return;
        else if (position <= 1) {
           root = root.getNext();
        } else {
           Node<E> tmpref = root;
           for (int i = 0; i < position - 1; i++) {
              tmpref = tmpref.getNext();
           }
           tmpref.setNext(tmpref.getNext().getNext());
        }
        count--;
     }

    public void printAll() {
        Node<E> tmpref = root;
        while (tmpref != null) {
           System.out.print("->" + tmpref.getInfo());
           tmpref = tmpref.getNext();
        }
        System.out.println();
    }
   


    private class Node<E> {

        
        private E info;
        private Node<E> next;

        public Node(E info) {
            super();
            this.info = info;
            this.next = null;
        }

    
        /**
         * @return the info
         */
        public E getInfo() {
            return this.info;
        }

        /**
         * @param next the next to set
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }
    
        /**
         * @return the next
         */
        public Node<E> getNext() {
            return this.next;
        }
    
        
    }
}