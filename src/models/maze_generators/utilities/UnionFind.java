package models.maze_generators.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UnionFind<T> {
    private final Map<T, T> components = new HashMap<>();
    private final Map<T, Integer> treeSizes = new HashMap<>();

    public UnionFind(T[] elements) {
        for (T component : elements) {
            components.put(component, component);
            treeSizes.put(component, 1);
        }
    }

    public void connect(T leftComponent, T rightComponent) {
        Objects.requireNonNull(leftComponent);
        Objects.requireNonNull(rightComponent);

        if (areConnected(leftComponent, rightComponent)) {
            return;
        }

        T leftComponentTree = find(leftComponent);
        T rightComponentTree = find(rightComponent);

        int leftTreeSize = getTreeSize(leftComponentTree);
        int rightTreeSize = getTreeSize(rightComponentTree);
        if (leftTreeSize < rightTreeSize) {
            components.put(leftComponentTree, rightComponentTree);
            treeSizes.put(rightComponentTree, leftTreeSize + rightTreeSize);
        } else {
            components.put(rightComponentTree, leftComponentTree);
            treeSizes.put(leftComponentTree, leftTreeSize + rightTreeSize);
        }
    }

    private T find(T component) {
        while (!component.equals(components.get(component))) {
            components.put(component, components.get(components.get(component))); // Path compression
            component = components.get(component);
        }
        return component;
    }

    private int getTreeSize(T component) {
        return treeSizes.get(component);
    }

    public boolean areConnected(T leftComponent, T rightComponent) {
        Objects.requireNonNull(leftComponent);
        Objects.requireNonNull(rightComponent);
        return find(leftComponent).equals(find(rightComponent));
    }
}