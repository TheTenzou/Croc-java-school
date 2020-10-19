package ru.croc.java.school.lecture2.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ConnectedComponentsTest {

    @Test
    public void getConnectedComponentsTest() {
        // пустой список
        List<Node> nodes = new ArrayList<>();
        ConnectedComponents connectedComponents = new ConnectedComponents(nodes);
        Assertions.assertEquals(new HashSet<>(), connectedComponents.getConnectedComponents());
//        Assertions.assertEquals(new ArrayList<>(), connectedComponents.getConnectedComponents());

        // одна  вершина
        nodes = new ArrayList<>();
        // создаем и добаляем вершины с список
        Node node1 = new Node();
        //добаляем вершины с список
        nodes.add(node1);
        // собираем правильные ответ
        connectedComponents = new ConnectedComponents(nodes);
        HashSet<Node> component1 = new HashSet<>();
        component1.add(node1);
        Collection<Set<Node>> answer = new HashSet<>();
        answer.add(component1);
        //проверка
        Assertions.assertEquals(answer, connectedComponents.getConnectedComponents());

        // две не связаные вершины
        nodes = new ArrayList<>();
        // создаем и добаляем вершины с список
        node1 = new Node();
        Node node2 = new Node();
        //добаляем вершины с список
        nodes.add(node1);
        nodes.add(node2);
        // собираем правильные ответ
        connectedComponents = new ConnectedComponents(nodes);
        component1 = new HashSet<>();
        HashSet<Node> component2 = new HashSet<>();
        component1.add(node1);
        component2.add(node2);
        answer = new HashSet<>();
        answer.add(component1);
        answer.add(component2);
        //проверка
        Assertions.assertEquals(answer, connectedComponents.getConnectedComponents());

        // три вершины две из низ связаны
        nodes = new ArrayList<>();
        // создаем и добаляем вершины с список
        node1 = new Node();
        node2 = new Node();
        Node node3 = new Node();
        // связываем вершины
        node2.addNode(node3);
        node3.addNode(node2);
        //добаляем вершины с список
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        // собираем правильные ответ
        connectedComponents = new ConnectedComponents(nodes);
        component1 = new HashSet<>();
        component2 = new HashSet<>();
        component1.add(node1);
        component2.add(node2);
        component2.add(node3);
        answer = new HashSet<>();
        answer.add(component1);
        answer.add(component2);
        //проверка
        Assertions.assertEquals(answer, connectedComponents.getConnectedComponents());

        // четыре вершины ири из низ связаны
        nodes = new ArrayList<>();
        // создаем  вершины
        node1 = new Node();
        node2 = new Node();
        node3 = new Node();
        Node node4 = new Node();
        // связываем вершины
        node2.addNode(node3);
        node3.addNode(node2);
        node4.addNode(node2);
        node4.addNode(node3);
        node2.addNode(node4);
        node3.addNode(node4);
        //добаляем вершины с список
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        // собираем правильные ответ
        connectedComponents = new ConnectedComponents(nodes);
        component1 = new HashSet<>();
        component2 = new HashSet<>();
        component1.add(node1);
        component2.add(node2);
        component2.add(node3);
        component2.add(node4);
        answer = new HashSet<>();
        answer.add(component1);
        answer.add(component2);
        //проверка
        Assertions.assertEquals(answer, connectedComponents.getConnectedComponents());

        // звездочка. все краевые вершины связаны с одной центральной
        nodes = new ArrayList<>();
        // создаем  вершины
        node1 = new Node();
        node2 = new Node();
        node3 = new Node();
        node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        // связываем вершины
        node2.addNode(node1);
        node3.addNode(node1);
        node4.addNode(node1);
        node5.addNode(node1);
        node6.addNode(node1);
        //добаляем вершины с список
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        // собираем правильные ответ
        connectedComponents = new ConnectedComponents(nodes);
        component1 = new HashSet<>();
        component1.add(node1);
        component1.add(node2);
        component1.add(node3);
        component1.add(node4);
        component1.add(node5);
        component1.add(node6);
        answer = new HashSet<>();
        answer.add(component1);
        //проверка
        Assertions.assertEquals(answer, connectedComponents.getConnectedComponents());

        // две звездочки. все краевые вершины связаны с одной центральной
        nodes = new ArrayList<>();
        // создаем  вершины
        node1 = new Node();
        node2 = new Node();
        node3 = new Node();
        node4 = new Node();
        node5 = new Node();
        node6 = new Node();
        Node node7 = new Node();
        Node node8 = new Node();
        Node node9 = new Node();
        Node node10 = new Node();
        Node node11 = new Node();
        Node node12 = new Node();
        // связываем вершины
        node2.addNode(node1);
        node3.addNode(node1);
        node4.addNode(node1);
        node5.addNode(node1);
        node6.addNode(node1);

        node7.addNode(node12);
        node8.addNode(node12);
        node9.addNode(node12);
        node10.addNode(node12);
        node11.addNode(node12);
        //добаляем вершины с список
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);

        nodes.add(node7);
        nodes.add(node8);
        nodes.add(node9);
        nodes.add(node10);
        nodes.add(node11);
        nodes.add(node12);
        // собираем правильные ответ
        connectedComponents = new ConnectedComponents(nodes);
        component1 = new HashSet<>();
        component1.add(node1);
        component1.add(node2);
        component1.add(node3);
        component1.add(node4);
        component1.add(node5);
        component1.add(node6);

        component2 = new HashSet<>();
        component2.add(node7);
        component2.add(node8);
        component2.add(node9);
        component2.add(node10);
        component2.add(node11);
        component2.add(node12);
        answer = new HashSet<>();
        answer.add(component1);
        answer.add(component2);
        //проверка
        Assertions.assertEquals(answer, connectedComponents.getConnectedComponents());
    }
}
