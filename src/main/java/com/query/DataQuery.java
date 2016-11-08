
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.query;

import com.entity.Category;
import com.entity.Forum;
import com.entity.Message;
import com.entity.Subject;
import com.entity.User;
import com.utils.DataConnect;
import com.utils.Hasher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matti
 */
public class DataQuery {

    private EntityManagerFactory emf;
    private EntityManager em;

    public DataQuery() {
        emf = Persistence.createEntityManagerFactory("com.mycompany_Project_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public boolean SignupControl(String name, String email, String password) {

        String encrypted = Hasher.generateHash(password);

        //This is temporary. It should be deleted once database has been changed!!!
        Random random = new Random();
        int randomId = random.nextInt(1000);

        try {
            User user = new User();
            user.setId(randomId);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(encrypted);
            user.setPermissions("1");

            em.persist(user);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean MessageControl(String name, String message) {

        //This is temporary. It should be deleted once database has been changed!!!
        Random random = new Random();
        int randomId = random.nextInt(1000);

        try {
            Message m = new Message();
            m.setId(randomId);
            m.setMessageDesc(message);
            m.setName(name);
            m.setSubjectId(randomId);

            em.persist(m);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public User getUser(int userId) {
        try {
            User userPersist = em.find(User.class, userId);
            System.out.println(userPersist.toString());
            return userPersist;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateUser(int userId, String name, String email, String password) {

        String encrypted = Hasher.generateHash(password);

        try {

            User user = new User();
            em.getTransaction().begin();
            em.find(User.class, user.getId());

            user.setName(name);
            user.setEmail(email);
            if (password != "") {
                user.setPassword(encrypted);
            }

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteUser(User user) {
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean createForum(String name, String desc) {

        //This is temporary. It should be deleted once database has been changed!!!
        Random random = new Random();
        int randomId = random.nextInt(1000);

        try {
            Forum forum = new Forum();
            forum.setId(randomId);
            forum.setName(name);
            forum.setForumDesc(desc);
            forum.setForumOwner("");
            em.persist(forum);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Forum getForum(int forumId) {
        try {
            Forum forumPersist = em.find(Forum.class, forumId);
            System.out.println(forumPersist.toString());
            return forumPersist;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateForum(int forumId, String name, String desc) {

        try {

            Forum forum = new Forum();
            em.getTransaction().begin();
            em.find(Forum.class, forumId);

            forum.setName(name);
            forum.setForumDesc(desc);

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    //connect to DB and get message list
    public List<Forum> getForumList(int forumId) throws SQLException {

        //get database connection
        Connection con = DataConnect.getConnection();

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Forum");

        //get messages data from database
        ResultSet result = ps.executeQuery();

        List<Forum> list = new ArrayList<>();

        while (result.next()) {
            Forum f = new Forum();

            f.setId(result.getInt("id"));
            f.setName(result.getString("name"));
            f.setForumDesc(result.getString("forumDesc"));
            f.setForumOwner(result.getString("forumOwner"));

            //store all data into a List
            list.add(f);
        }

        return list;
    }

    public boolean deleteForum(Forum forum) {
        try {
            em.getTransaction().begin();
            em.remove(forum);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean createCategory(String name, String desc, int forumId) {

        //This is temporary. It should be deleted once database has been changed!!!
        Random random = new Random();
        int randomId = random.nextInt(1000);

        try {
            Category cat = new Category();
            cat.setId(randomId);
            cat.setName(name);
            cat.setForumId(forumId);
            em.persist(cat);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Category getCategory(int catId) {
        try {
            Category catPersist = em.find(Category.class, catId);
            System.out.println(catPersist.toString());
            return catPersist;
        } catch (Exception e) {
            return null;
        }
    }

    //connect to DB and get message list
    public List<Category> getCategoryList(int forumId) throws SQLException {

        //get database connection
        Connection con = DataConnect.getConnection();

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Category WHERE forumId = ? ");
        ps.setInt(1, forumId);
        //get messages data from database
        ResultSet result = ps.executeQuery();

        List<Category> list = new ArrayList<>();

        while (result.next()) {
            Category c = new Category();

            c.setId(result.getInt("id"));
            c.setName(result.getString("name"));
            c.setForumId(forumId);

            //store all data into a List
            list.add(c);
        }

        return list;
    }

    public boolean updateCategory(int catId, int forumId, String name, String desc) {

        try {

            Category cat = new Category();
            em.getTransaction().begin();
            em.find(Category.class, catId);

            cat.setName(name);
            cat.setForumId(catId);

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteCategory(Category cat) {
        try {
            em.getTransaction().begin();
            em.remove(cat);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean createSubject(String name, String desc, int catId) {

        //This is temporary. It should be deleted once database has been changed!!!
        Random random = new Random();
        int randomId = random.nextInt(1000);

        try {
            Subject sub = new Subject();
            sub.setId(randomId);
            sub.setName(name);
            sub.setSubjectDesc(desc);
            sub.setCategorieId(catId);
            em.persist(sub);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Subject getSubject(int subId) {
        try {
            Subject subPersist = em.find(Subject.class, subId);
            System.out.println(subPersist.toString());
            return subPersist;
        } catch (Exception e) {
            return null;
        }
    }

    //connect to DB and get message list
    public List<Subject> getSubjectList(int catId) throws SQLException {

        //get database connection
        Connection con = DataConnect.getConnection();

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Subject WHERE categoryId = ?");
        ps.setInt(1, catId);

        //get messages data from database
        ResultSet result = ps.executeQuery();

        List<Subject> list = new ArrayList<>();

        while (result.next()) {
            Subject s = new Subject();

            s.setId(result.getInt("id"));
            s.setName(result.getString("name"));
            s.setSubjectDesc(result.getString("subjectDesc"));
            s.setCategoryId(result.getInt("CategoryId"));

            //store all data into a List
            list.add(s);
        }

        return list;
    }

    public boolean updateSubject(int subId, int catId, String name, String desc) {

        try {

            Subject sub = new Subject();
            em.getTransaction().begin();
            em.find(Subject.class, subId);

            sub.setName(name);
            sub.setCategoryId(catId);
            sub.setSubjectDesc(desc);

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteSubject(Subject sub) {
        try {
            em.getTransaction().begin();
            em.remove(sub);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean createMessage(String name, String desc, int subjectId) {

        //This is temporary. It should be deleted once database has been changed!!!
        Random random = new Random();
        int randomId = random.nextInt(1000);

        try{
            Message mes = new Message();
            mes.setId(randomId);
            mes.setName(name);
            mes.setMessageDesc(desc);
            mes.setSubjectId(subjectId);
            em.persist(mes);
            em.getTransaction().commit();

            return true;
        }catch(Exception e){
            return false;
        }

    }

    public Message getMessage(int mesId) {
        try {
            Message mesPersist = em.find(Message.class, mesId);
            System.out.println(mesPersist.toString());
            return mesPersist;
        } catch (Exception e) {
            return null;
        }
    }

    //connect to DB and get message list
    public List<Message> getMessageList(int subId) throws SQLException {

        //get database connection
        Connection con = DataConnect.getConnection();

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Message WHERE subjectId = ?");
        ps.setInt(1, subId);

        //get messages data from database
        ResultSet result = ps.executeQuery();

        List<Message> list = new ArrayList<>();

        while (result.next()) {
            Message m = new Message();

            m.setId(result.getInt("id"));
            m.setName(result.getString("name"));
            m.setMessageDesc(result.getString("messageDesc"));
            m.setSubjectId(result.getInt("SubjectId"));

            //store all data into a List
            list.add(m);
        }

        return list;
    }

    public boolean updateMessage(int mesId, int subId, String name, String desc) {

        try {

            Message mes = new Message();
            em.getTransaction().begin();
            em.find(Message.class, mesId);

            mes.setName(name);
            mes.setSubjectId(subId);
            mes.setMessageDesc(desc);

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteMessage(Message mes) {
        try {
            em.getTransaction().begin();
            em.remove(mes);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
