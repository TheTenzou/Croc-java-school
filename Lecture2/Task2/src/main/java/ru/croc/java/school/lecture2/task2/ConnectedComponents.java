package ru.croc.java.school.lecture2.task2;

import java.util.*;

/**
 * Поиск списока компонент связаности
 */
public class ConnectedComponents {
    /**
     * Список вершин первый параметр вершина второй былали вершина использована
     * True - вершину еще не посешали
     * False - вершину уэе посешали
     */
    private HashMap<Node, Boolean> nodes = new HashMap<>();
    /**
     * компонента звязаности
     */
    private Set<Node> components = new HashSet<>();

    /**
     * Коструктор
     *
     * @param nodes список вершин
     */
    public ConnectedComponents(Collection<Node> nodes) {
        for (Node node : nodes) {
            this.nodes.put(node, Boolean.TRUE);
        }
    }

    /**
     * обход в ширену компоненты связаности
     * @param node вершина графа
     */
    private void dfs(Node node) {
        this.nodes.put(node, Boolean.FALSE);
        components.add(node);
        Set<Node> links = node.getLinks();
        for (Node link : links) {
            if (nodes.get(link)) {
                dfs(link);
            }
        }
    }

    /**
     * Возврашает список компонент связаности
     * @return список компонент связаности
     */
    public Set<Set<Node>> getConnectedComponents() {
        if (nodes.isEmpty()) {
            return new HashSet<>();
        }

        Set<Set<Node>> result = new HashSet<>();
        for (Node node : nodes.keySet()) {
            if(nodes.get(node)) {
                components.clear();
                dfs(node);
                result.add(new HashSet<>(components));
            }
        }

        return result;
    }
}
