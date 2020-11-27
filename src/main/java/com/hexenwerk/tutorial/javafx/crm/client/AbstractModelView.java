package com.hexenwerk.tutorial.javafx.crm.client;

public abstract class AbstractModelView<T, V> implements ModelView<T> {

    protected T model;

    @Override
    public T getModel() {
        merge();
        return model;
    }

    @Override
    public void setModel(T model) {
        this.model = model;
        load();
    }

    public abstract void merge();

    public abstract void load();

    public abstract void bind(V view);

    public abstract void unbind(V view);
}
