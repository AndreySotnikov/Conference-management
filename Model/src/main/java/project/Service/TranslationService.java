package project.Service;

import project.Entity.Translation;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Green-L on 18.07.2015.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TranslationService extends CrudImplementation {

    public Translation update(Integer id, Translation translation){
        try {
            Translation oldTranslation = findOne(Translation.class, id);
            oldTranslation.setText(translation.getText());
            oldTranslation.setTime(translation.getTime());
            oldTranslation.setSpeech(translation.getSpeech());
            return em.merge(oldTranslation);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Translation> findAll(){
        return super.findAll(Translation.class);
    }

    public Translation findOne(Integer id){
        return super.findOne(Translation.class, id);
    }

    public void remove(Integer id){
        super.remove(Translation.class, id);
    }
}
