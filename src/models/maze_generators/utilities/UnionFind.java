package models.maze_generators.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UnionFind<T> {
    private final Map<T, T> components = new HashMap<>();
    private final Map<T, Integer> treeSizes = new HashMap<>();

    public UnionFind(T[] components) {
        for (T component : components) {
            this.components.put(component, component);
            this.treeSizes.put(component, 1);
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
            this.components.put(leftComponentTree, rightComponentTree);
            this.treeSizes.put(rightComponentTree, leftTreeSize + rightTreeSize);
        } else {
            this.components.put(rightComponentTree, leftComponentTree);
            this.treeSizes.put(leftComponentTree, leftTreeSize + rightTreeSize);
        }
    }

    private T find(T component) {
        while (!component.equals(this.components.get(component))) {
            this.components.put(component, this.components.get(this.components.get(component))); // Path compression
            component = this.components.get(component);
        }
        return component;
    }

    private int getTreeSize(T component) {
        return this.treeSizes.get(component);
    }

    public boolean areConnected(T leftComponent, T rightComponent) {
        Objects.requireNonNull(leftComponent);
        Objects.requireNonNull(rightComponent);
        return find(leftComponent).equals(find(rightComponent));
    }
}