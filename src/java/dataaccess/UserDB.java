/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;
import models.Role;
import models.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author David Doan <your.name at your.org>
 */
public class UserDB {
    //get all user?
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }

        
    }
//get one particular user for specific email

    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
           User user = em.find(User.class, email);
           return user;
        } finally {
           em.close();
        }
        
    }

    
//insert  one user
//pass the whole user object
    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

      public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}