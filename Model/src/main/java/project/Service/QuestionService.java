package project.Service;

import project.Entity.Question;
import project.Entity.Visitor;
import project.Util.CrudImplementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 18.07.15.
 */
public class QuestionService extends CrudImplementation{
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
        question.setVisitor(visitors);
        question.setRating(1);
        save(question);
    }

    public void moderate(Integer id){
        Question question = findOne(id);
        question.setModerated(true);
        em.merge(question);
    }

    public boolean upvote(Integer id, Visitor visitor){
        Question question = findOne(id);
        if (question.getVisitor().contains(visitor))
            return false;
        else{
            List<Visitor> visitors = question.getVisitor();
            visitors.add(visitor);
            question.setVisitor(visitors);
            question.setRating(question.getRating()+1);
            em.merge(question);
            return true;
        }
    }
}
