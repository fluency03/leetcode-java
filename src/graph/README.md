# Graph

Reference: https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/

## Representations

Assume the labels are integers from 0 to n.


### Adjacency Matrix

```java
public class GraphInAdjacencyMatrix {
    int V;
    int[][] graph;
    GraphInAdjacencyMatrix(int v) {
        this.V = v;
        this.graph = new int[v][v];
    }
}
```


### Adjacency List

```java
public class GraphInAdjacencyList {
    int V;
    List<Integer>[] graph;
    GraphInAdjacencyList(int v) {
        this.V = v;
        this.graph = new LinkedList[v];
        for (int i=0; i<v; i++) {
            this.graph.add(new LinkedList<>());
        }
    }
}
```


### Adjacency Set

```java
public class GraphInAdjacencySet {
    int V;
    Set<Integer>[] graph;
    GraphInAdjacencySet(int v) {
        this.V = v;
        this.graph = new HashSet[v];
        for (int i=0; i<v; i++) {
            this.graph.add(new HashSet<>());
        }
    }
}
```



