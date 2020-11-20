package org.windowsprint.component;

public abstract class Component implements IComponent {

    private  String name;

    protected Component(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println(this.name);
    }
}
