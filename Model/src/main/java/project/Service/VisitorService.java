package project.Service;

import project.Entity.Conference;
import project.Entity.Question;
import project.Entity.Speech;
import project.Entity.Visitor;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VisitorService extends CrudImplementation {
    public Visitor update(String id, Visitor visitor){
        try {
            Visitor oldVisitor = findOne(Visitor.class, id);
            oldVisitor.setEmail(visitor.getEmail());
            oldVisitor.setName(visitor.getName());
            oldVisitor.setPhone(visitor.getPhone());
            return em.merge(oldVisitor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Visitor> findAll(){
        return super.findAll(Visitor.class);
    }

    public Visitor findOne(String id){
        return super.findOne(Visitor.class, id);
    }

    public void remove(String id) { super.remove(Visitor.class, id);}

    public Boolean registerToSpeech(String visitorId, Integer speechId) {
        Visitor visitor = findOne(visitorId);
        Speech speech = findOne(Speech.class, speechId);

        List<Visitor> visitors = speech.getVisitors();
        if (visitors.contains(visitor))
            return false;
        else {
            try {
                visitors.add(visitor);
                em.merge(speech);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    public void addQuestion(String text, String visitorId, Integer speechId ) {
        Visitor visitor = findOne(visitorId);
        Question question = new Question();
        question.setText(text);
        question.setSpeech(findOne(Speech.class, speechId));
        question.setRating(1);
        question.setModerated(false);
        question.setAnswered(false);

        question = em.merge(question);
        System.err.println("visitor " + visitor.getQuestions());
        System.err.println("questionID " + question.getId());
        visitor.getQuestions().add(question);
        System.err.println("visitor: " + visitor.getQuestions());
        em.merge(visitor);
        em.flush();
        System.err.println("visitor: " + findOne(visitorId).getQuestions());

    }


    public Boolean registerToConference(String visitorId, Integer conferenceId) {
        Visitor visitor = findOne(visitorId);
        Conference conference = findOne(Conference.class, conferenceId);
        List<Visitor> visitors = conference.getVisitors();
        if (visitors.contains(visitor))
            return false;
        else {
            try {
                visitors.add(visitor);
                em.merge(conference);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }
        }
    }


    public List<Conference> getConferencesSubscribedTo(String visitorLogin) {
        Visitor visitor = findOne(visitorLogin);
        return visitor.getConferences();
    }


    public List<Speech> getSpeechesSubscribedTo(String visitorLogin) {
        Visitor visitor = findOne(visitorLogin);
        return visitor.getSpeeches();
    }

    public boolean hasVisitorSubscribedToConference(String visitorLogin, Integer speechId) {
        Visitor visitor = findOne(visitorLogin);
        Integer conferenceId = findOne(Speech.class, speechId).getConference().getId();
        return visitor.getConferences().contains(findOne(Conference.class, conferenceId));
    }



}
