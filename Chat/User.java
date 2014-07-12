public class User implements Observer{
    private String name;
    private int id; 
    private int logged;
    private Subject topic;
    public User(String name, int id, int logged){
        this.name = name; 
        this.id = id;
        this.logged = logged;
    }
    public String getName(){
    	return this.name;
    }
    public int getID(){
    	return this.id;
    }
    public int getLogged(){
    	return this.logged;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: No new message");
        }else
        System.out.println(name+":: Consuming message::"+msg);
    }
 
    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }
 

}