package reactive;

import java.util.ArrayList;

class Observable {
    private Object value;
    private ArrayList<ObservableCallback> observers = new ArrayList<>();

    public Observable(Object value) {
        this.value = value;
    }

    public void subscribe(ObservableCallback callback) {
        observers.add(callback);
    }

    public void unsubscribe(ObservableCallback callback) {
        observers.remove(callback);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (value == this.value)
            return;

        this.value = value;
        this.observers.forEach(observer -> observer.callback(this.value));
    }
}
