package ru.croc.java.school.lecture2.task2;

import java.util.HashSet;
import java.util.Set;

public class Node {
    Set<Node> links = new HashSet<>();

    public Node(Set<Node> links) {
        this.links = links;
    }

    public Node() {}

    public Set<Node> getLinks() {
        return links;
    }

    public void addNode(Node node) {
        this.links.add(node);
    }
}
