package reactive;

import java.util.HashMap;

public class Store {
    
    private HashMap<String, Object> defaultValues;
    private HashMap<String, Observable> store = new HashMap<>();

    public Store(HashMap<String, Object> store) {
        for (String key : store.keySet()) {
            this.store.put(key, new Observable(store.get(key)));
        }
        this.defaultValues = store;
    }

    public void clear() {
        for (String key : store.keySet()) {
            this.set(key, defaultValues.get(key));
        }
    }
    
    public boolean has(String key) {
        return store.containsKey(key);
    }
    
    private void checkIfExists(String key) {
        if (!this.has(key))
            System.out.println(String.format("Reactive value \"%s\" doesn't exists", key));
    }

    public Object get(String key) {
        this.checkIfExists(key);
        return store.get(key).getValue();
    }

    public void set(String key, Object value) {
        if (store.containsKey(key)) {
            store.get(key).setValue(value);
        } else {
            store.put(key, new Observable(value));
        }
    }

    public void subscribe(String key, ObservableCallback observer) {
        this.checkIfExists(key);
        store.get(key).subscribe(observer);
    }

    public void unsubscribe(String key, ObservableCallback observer) {
        this.checkIfExists(key);
        store.get(key).unsubscribe(observer);
    }
}
