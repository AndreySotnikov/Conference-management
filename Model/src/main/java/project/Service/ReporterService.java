package project.Service;

import project.Entity.Reporter;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReporterService extends CrudImplementation{

    public Reporter update(String id, Reporter reporter) {
        try {
            Reporter oldReporter = findOne(Reporter.class, id);
            oldReporter.setName(reporter.getName());
            oldReporter.setEmail(reporter.getEmail());
            oldReporter.setBusy(reporter.isBusy());
            return em.merge(oldReporter);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Reporter> findAll(){
        return super.findAll(Reporter.class);
    }

    public Reporter findOne(String id){
        return super.findOne(Reporter.class, id);
    }

    public void remove(String id){
        super.remove(Reporter.class, id);
    }

}
