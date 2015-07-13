package project;


import project.Entity.TestEntity;
import project.Util.CrudImplementation;


/**
 * Created by andrey on 13.07.15.
 */

public class Launcher {
    public static void main(String[] args) {
        CrudImplementation crudImplementation = new CrudImplementation();
        crudImplementation.save(new TestEntity("test"));

    }
}
