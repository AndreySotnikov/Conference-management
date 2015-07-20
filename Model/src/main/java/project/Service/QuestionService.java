package project.Service;

import project.Entity.Question;
import project.Entity.Speech;
import project.Entity.Visitor;
import project.Util.CrudImplementation;
import project.Util.CrudRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.ArrayList;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class QuestionService extends CrudImplementation{
//    @EJB(beanInterface = VisitorService.class)
//    private VisitorService visitorService;

    public List<Question> findAllBySpeech(Integer speechId){
        return em.createQuery("select e from Question e where e.speech.id=:id")
                .setParameter("id",speechId).getResultList();
    }

    public List<Question> findAllFiltered(Integer speechId, Boolean moderated){
        return em.createQuery("select e from Question e where e.speech.id=:id and e.moderated=:moderated")
                .setParameter("id", speechId)
                .setParameter("moderated",moderated).getResultList();
    }

    public Question findOne(Integer id){
        return findOne(Question.class, id);
    }

    public void addQuestion(String text, Visitor visitor){
        Question question = new Question();
        question.setText(text);
        List<Visitor> visitors = new ArrayList<>();
        visitors.add(visitor);
        question.setVisitorsUpVoteQuestion(visitors);
        question.setRating(1);
        save(question);
    }

    public void addQuestion(String text, String visitorLogin, Integer speechId) throws Exception{
        Question question = new Question();
        question.setText(text);
        question.setSpeech(findOne(Speech.class,speechId));
        List<Visitor> visitors = new ArrayList<>();
        Visitor visitor = findOne(Visitor.class,visitorLogin);
        visitors.add(visitor);
        question.setVisitorsUpVoteQuestion(visitors);
        question.setRating(1);
        save(question);
    }

    public void moderate(Integer id){
        Question question = findOne(id);
        question.setModerated(true);
        em.merge(question);
    }

    public void addAnswer(Integer id, String text){
        Question question = findOne(id);
        question.setAnswer(text);
        em.merge(question);
    }

    public boolean upvote(Integer id, Visitor visitor){
        Question question = findOne(id);
        if (question.getVisitorsUpVoteQuestion().contains(visitor))
            return false;
        else{
            List<Visitor> visitors = question.getVisitorsUpVoteQuestion();
            visitors.add(visitor);
            question.setVisitorsUpVoteQuestion(visitors);
            question.setRating(question.getRating()+1);
            em.merge(question);
            return true;
        }
    }

    public boolean upvote(Integer id, String visitorLogin){
        Question question = findOne(id);
        Visitor visitor = findOne(Visitor.class, visitorLogin);
        if (question.getVisitorsUpVoteQuestion().contains(visitor))
            return false;
        else{
            List<Visitor> visitors = question.getVisitorsUpVoteQuestion();
            visitors.add(visitor);
            question.setVisitorsUpVoteQuestion(visitors);
            question.setRating(question.getRating()+1);
            em.merge(question);
            return true;
        }
    }

    public void remove(int id){
        remove(Question.class,id);
    }
}
