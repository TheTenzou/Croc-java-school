package ru.croc.java.school.lecture2.task2;

import java.util.*;

/**
 * Поиск списока компонент связаности
 */
public class ConnectedComponents {
    /**
     * Список вершин первый параметр вершина второй номер компоненты связаности
     * если компоненту еще не обошли то он равен 0
     */
    private HashMap<Node, Integer> nodes = new HashMap<>();
    /**
     * компонента звязаности
     */
    private Set<Node> components;

    /**
     * хеш мапа с компонентами связаности где ключь ее номер
     */
    private HashMap<Integer, Set<Node>> connectedComponents = new HashMap<>();

    /**
     * список компонент которые нужно обединить
     */
    private ArrayList<ArrayList<Integer>> setsOfComponentsToGroup = new ArrayList<>();

    /**
     * Коструктор
     *
     * @param nodes список вершин
     */
    public ConnectedComponents(Collection<Node> nodes) {
        for (Node node : nodes) {
            this.nodes.put(node, 0);
        }
    }

    /**
     * обход в ширену компонент связаности
     *
     * @param node           вершина графа
     * @param componentIndex индекс компоненты
     */
    private void dfs(Node node, int componentIndex) {
        this.nodes.put(node, componentIndex);
        components.add(node);
        Set<Node> links = node.getLinks();
        for (Node link : links) {
            int groupNumber = nodes.get(link);
            if (groupNumber == 0) {
                // найдена вершина которую еще не обошли
                dfs(link, componentIndex);
            } else if (groupNumber != componentIndex) {
                // надена связ с вершиной из другой группы
                boolean componentWasAdd = false;
                // проверка есть ли эта группа в списке на объединение
                for (ArrayList<Integer> componentsToGroup : setsOfComponentsToGroup) {
                    if (componentsToGroup.contains(groupNumber)) {
                        componentsToGroup.add(componentIndex);
                        componentWasAdd = true;
                    }
                }
                // если нет то добовляем новый список на объединение
                if (!componentWasAdd) {
                    ArrayList<Integer> newSetOfComponentsToGroup = new ArrayList<>();
                    newSetOfComponentsToGroup.add(componentIndex);
                    newSetOfComponentsToGroup.add(groupNumber);
                    setsOfComponentsToGroup.add(newSetOfComponentsToGroup);
                }
            }
        }
    }

    /**
     * объединяут связаные компонеты
     */
    private void groupComponents() {
        for (ArrayList<Integer> componentsToGroup : setsOfComponentsToGroup) {
            Integer firstComponentIndex = componentsToGroup.get(0);
            componentsToGroup.remove(0);
            Set<Node> firstSet = connectedComponents.get(firstComponentIndex);
            for (Integer componentIndex : componentsToGroup) {
                firstSet.addAll(connectedComponents.get(componentIndex));
                connectedComponents.remove(componentIndex);
            }
            connectedComponents.put(firstComponentIndex, firstSet);
        }
    }

    /**
     * Возврашает список компонент связаности
     *
     * @return список компонент связаности
     */
    public Set<Set<Node>> getConnectedComponents() {
        if (nodes.isEmpty()) {
            return new HashSet<>();
        }

        int componentsNumber = 1;
        for (Node node : nodes.keySet()) {
            if (nodes.get(node) == 0) {
                components = new HashSet<>();
                dfs(node, componentsNumber);
                connectedComponents.put(componentsNumber, components);
                componentsNumber++;
            }
        }
        groupComponents();
        return new HashSet<>(connectedComponents.values());
    }
}
