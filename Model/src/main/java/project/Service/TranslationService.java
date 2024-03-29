package project.Service;

import project.DTO.TranslationDTO;
import project.Entity.Translation;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TranslationService extends CrudImplementation {

    public Translation update(Integer id, Translation translation){
        try {
            Translation oldTranslation = findOne(Translation.class, id);
            oldTranslation.setText(translation.getText());
            oldTranslation.setTimestamp(translation.getTimestamp());
            oldTranslation.setSpeech(translation.getSpeech());
            return em.merge(oldTranslation);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<TranslationDTO> findBySpeech(Integer speechId){
        try {
            return em.createQuery("SELECT e.id, e.text, e.timestamp  FROM Translation e WHERE e.speech.id=:speechId ")
                    .setParameter("speechId", speechId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TranslationDTO> findUpdates(Integer speechId, Integer lastId){
        try {
            return em.createQuery("SELECT e.id, e.text, e.timestamp FROM Translation e WHERE "+
                    "e.speech.id=:speechId AND e.id>:lastId")
                    .setParameter("speechId", speechId)
                    .setParameter("lastId", lastId)
                    .getResultList();
        } catch (Exception e) {
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
