public interface Subject{


    public void register(Observer obj);
    public void unregister(Observer obj);

    //notify observers of change
    public void notifyObservers();

    public Object getUpdate(Observer obj);
}