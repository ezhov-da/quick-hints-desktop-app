/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.ezhov.regularexpression.connection.ApplicationConnection;
import ru.ezhov.regularexpression.connection.Querys;
import ru.ezhov.regularexpression.connection.TreatmentsQuerys;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class TreatmentData {
    private static final Logger logger = Logger.getLogger(TreatmentData.class.getName());

        public void insert(String text,String description){
                            try {
                                                TreatmentsQuerys treatmentsQuerys = new TreatmentsQuerys(ApplicationConnection.getInstance());
                                                treatmentsQuerys.insert(Querys.INSERT, text, description);
                                                select(Querys.SELECT);
                            } catch (ClassNotFoundException ex) {
                                        logger.log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                        logger.log(Level.SEVERE, null, ex);
                            } catch (UnsupportedEncodingException ex) {
                                        logger.log(Level.SEVERE, null, ex);
                            } catch (IllegalArgumentException ex){
                                        logger.log(Level.SEVERE, null, ex);
                            }
            }
            
            public void delete(int id){
                            try {
                                                
                                                TreatmentsQuerys treatmentsQuerys = new TreatmentsQuerys(ApplicationConnection.getInstance());
                                                treatmentsQuerys.delete(Querys.DELETE, id);
                                                select(Querys.SELECT);
                            } catch (ClassNotFoundException ex) {
                                    logger.log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                    logger.log(Level.SEVERE, null, ex);
                            } catch (UnsupportedEncodingException ex) {
                                    logger.log(Level.SEVERE, null, ex);
                            } catch (IllegalArgumentException ex){
                                    logger.log(Level.SEVERE, null, ex);
                            }
            }
            
            
            
            public void update( int id, String idKey, String text, String description){
                            try {
                                                
                                                TreatmentsQuerys treatmentsQuerys = new TreatmentsQuerys(ApplicationConnection.getInstance());
                                                treatmentsQuerys.update(Querys.UPDATE, id, idKey,text,description);
                                                select(Querys.SELECT);
                            } catch (ClassNotFoundException ex) {
                                    logger.log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                    logger.log(Level.SEVERE, null, ex);
                            } catch (UnsupportedEncodingException ex) {
                                    logger.log(Level.SEVERE, null, ex);
                            } catch (IllegalArgumentException ex){
                                    logger.log(Level.SEVERE, null, ex);
                            }
            }
            
            public void select(String query){
                        try {
                                        /*fill table*/
                                        TreatmentsQuerys treatmentsQuerys = new TreatmentsQuerys(ApplicationConnection.getInstance());
                                       Vector<ApplicationObject> vector =    treatmentsQuerys.select(query);
                                       
                                       TreatmentList.clearList();
                                       TreatmentList.setList(vector);
      
                        } catch (ClassNotFoundException ex) {
                                logger.log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                                logger.log(Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                                logger.log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex){
                                logger.log(Level.SEVERE, null, ex);
                        }
            }
                      
            
            
            public String  selectKey(int idKey){
            String text = null;    
                        try {    /*fget text*/
                                        TreatmentsQuerys treatmentsQuerys = new TreatmentsQuerys(ApplicationConnection.getInstance());
                                                text =  treatmentsQuerys.selectKey(Querys.SELECT_KEY, idKey);
                        } catch (ClassNotFoundException ex) {
                                logger.log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                                logger.log(Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                                logger.log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex){
                                logger.log(Level.SEVERE, null, ex);
                        }
                    return text;
            }
}
