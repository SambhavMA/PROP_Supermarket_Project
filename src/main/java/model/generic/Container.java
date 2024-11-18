package model.generic;

import java.util.HashMap;


public class Container<E, T> {
    private HashMap<E, T> objects;

    public Container() {
        objects = new HashMap<E, T>();
    }

    public HashMap<E, T> getObjects() {
        return objects;
    }

    public void addObjects(E id, T object) throws Exception{
        objects.put(id, object);
    }

    public void modifyObject(E id, T object) throws Exception{
        objects.put(id, object);
    }

    public void deleteObject(E id) throws Exception{
        objects.remove(id);
    }

    public T getProductById(E id) {
        if (objects.containsKey(id)) {
            return objects.get(id);
        }
        return null;
    }


}

