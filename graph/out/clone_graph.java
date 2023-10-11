
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}



Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

 

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:

Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:

Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

*/

class Solution {
    public Node cloneGraph(Node node) {
        // original : copy
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // use a stack so it will be dfs
        queue.offer(node);
        map.put(node, new Node(node.val));

        // clone val
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (Node next : map.get(now)) {
                if (!map.containsKey(next)) {
                    map.put(next, new Node(next.val));
                    queue.offer(next);
                }
            }
        }

        // clone the edges
        for (Map.Entry<Node, Node> entry : map.entrySet()) {
            Node original = entry.getKey();
            Node copy = entry.getValue();

            for (Node nei : original.neighbors) { // 2 4
                Node copyNei = map.get(nei); // 2' 4'
                copy.neighbors.add(copyNei); // copy = 1' : [2', 4']
            }
        }

        return map.get(node);
    }
}

    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node root) {
        return helper(root);
    }

    // to create a copy a node
    Node helper(Node node) {
        // base conditions
        if (node == null) {
            return null;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node copy = new Node(node.val); // 1'
        map.put(node, copy); // 1:1'

        for (Node nei : node.neighbors) { // 2 4
            // for each neighbor, copy val and edge
            Node copyNei = helper(nei);
            copy.neighbors.add(copyNei);
        }

        return copy;
    }