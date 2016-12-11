package cc.fs.design_pattern.design.create.singleton;

public class SingletonRun {
    public void run(){
        Singleton singleton = Singleton.getInstance();
        singleton.call();
    }
}
