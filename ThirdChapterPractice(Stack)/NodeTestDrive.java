class NodeTestDrive {
    public static void main (String ... args) {
        Stack<String> stack = new Stack<>();
        System.out.println(stack.push("hello"));
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        
        stack.push("data");
        stack.peek();
    }
}