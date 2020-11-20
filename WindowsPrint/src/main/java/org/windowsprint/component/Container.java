package org.windowsprint.component;

import java.util.Iterator;
import java.util.List;

public abstract class Container implements IComponent {

    private String name;
    private List<IComponent> children;

    protected Container(String name, List<IComponent> children) {
        this.name = name;
        this.children = children;
    }

    protected void add(IComponent component) {
        children.add(component);
    }

    @Override
    public void print() {
        System.out.println(name);
        for (Iterator<IComponent> iterator = children.iterator(); iterator.hasNext();) {
            IComponent next =  iterator.next();
            next.print();
        }
    }
}
