class CircularDoublyLinkedList<E> {
    
    private Node<E> root;
    private int count;

    public CircularDoublyLinkedList() {
        count = 0;
        root = null;
    }

    public void printAllClockWise() {
        Node<E> tempNode = root;
        for (int i = 0; i < count; i++ ) {
            System.out.print("->"+tempNode.getInfo());
            tempNode = tempNode.getNext();
        }
        System.out.println();
    }

    public boolean addNode(E info) {
        Node<E> node = new Node<E>(info);
        if (root == null) {
            node.setPrevious(node); //try root here
            node.setNext(node);
            root = node;
            count++;
            return true;
        }  /*else if (count == 1) {
            Node<E> tempNode = root;
            tempNode.setNext(node);
            tempNode.setPrevious(node);
            node.setNext(tempNode);
            node.setPrevious(tempNode);
            count++;
            return true;

        }*/ else {
            Node<E> tempNode = root;
            int counter = 0;
            for (counter = 0;counter < getCount()-1 ; counter++) {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(node);
            root.setPrevious(node);
            node.setNext(root);
            node.setPrevious(tempNode);
            count++;
            return true;
        }
        
    }


    public boolean addNodeAtFirst(E info) {
        Node<E> node = new Node<E>(info);
        if (root == null) {
            node.setPrevious(node); //try root here
            node.setNext(node);
            root = node;
            count++;
            return true;
        } else {
            Node<E> tmpref = root;
            tmpref.getPrevious().setNext(node);
            
            node.setNext(root);
            node.setPrevious(tmpref.getPrevious());
            tmpref.setPrevious(node);
            root = node;
            /*tempref.getPrevious().setNext(node);
            tempref.setPrevious(node);
            node.setNext(tempref);
            while (tempref.getNext() != root)
                tempref = tempref.getNext();
            node.setPrevious(root.getPrevious());
            root = node;
            while (tmpref.getNext() != root)
                tmpref = tmpref.getNext();
            tmpref.setNext(node);
            node.setNext(root);
            node.setPrevious(tmpref);
            root.setPrevious(node);
            root = node;*/
            count++;
            
            return true;
        }
        
    }

    public boolean remove(E info) {
        Node<E> tNode = root;

        if (root == null) {
            return false;
        } else if (count == 1 ) {
            if (tNode.getInfo() == info) {
                root = null;
                count --;
                return true;
            }
            
        } else {
            int tmp=0;
            while (tNode.getInfo() != info && tmp <= getCount()) {
                tmp++;
                tNode = tNode.getNext();
            }

            if (tNode.getInfo() == info) {
                
                
                tNode.getPrevious().setNext(tNode.getNext()); 
                tNode.getNext().setPrevious(tNode.getPrevious());
        
                count--;
                if(tNode == root)
                {
                    root = tNode.getNext();
                }
                System.out.println(tNode.getInfo());
                return true;
            } else {
                System.out.println("no data found");
            }
        }

        return false;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }


    
    
    class Node <E> {
        private Node<E> previous;
        private Node<E> next;
        private E info;

        public Node(E info) {
            super();
            previous = null;
            next = null;
            this.info = info;
        }

        /**
         * @return the previous
         */
        public Node<E> getPrevious() {
            return previous;
        }

        /**
         * @param previous the previous to set
         */
        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        /**
         * @return the info
         */
        public E getInfo() {
            return info;
        }

        /**
         * @param info the info to set
         */
        public void setInfo(E info) {
            this.info = info;
        }

        /**
         * @return the next
         */
        public Node<E> getNext() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    
}