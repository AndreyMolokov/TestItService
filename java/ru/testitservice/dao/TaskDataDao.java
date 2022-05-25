package ru.testitservice.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testitservice.entity.TaskData;
import ru.testitservice.model.TaskModel;
import java.util.List;

@Repository
public class TaskDataDao {
    SessionFactory localSessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.localSessionFactory = sessionFactory;
    }

    public void create(TaskModel taskModel){
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);
        TaskData taskData = new TaskData();
        taskData.setTaskType(taskModel.getTaskType());
        taskData.setTaskText(taskModel.getTaskText());
        taskData.setDate(currentTime);

        Session session = localSessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.save(taskData);
        tr.commit();
        session.close();
    }
    public TaskData read(Long id) {
        Session session = localSessionFactory.openSession();
        TaskData taskData =session.get(TaskData.class,id);
        session.close();
        return taskData;
    }
    public List<TaskData> findAll( ) {
        Session session = localSessionFactory.openSession();
        List<TaskData> taskDataList = session.createQuery("from  TaskData").list();
        session.close();
        return taskDataList;
    }

    public void update(TaskData taskData) {
        Session session = localSessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(taskData);
        tx1.commit();
        session.close();
    }
    public void delete(TaskData taskData) {
        Session session = localSessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(taskData);
        tx1.commit();
        session.close();
    }
}
