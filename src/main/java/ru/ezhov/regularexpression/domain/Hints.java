/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.domain;

import ru.ezhov.regularexpression.infrastructure.connection.ApplicationConnection;
import ru.ezhov.regularexpression.infrastructure.connection.HintsDao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author RRNDeonisiusEZH
 */
public class Hints {
    private static final Logger logger = Logger.getLogger(Hints.class.getName());

    public void add(NewHint newHint) throws AddHintException {
        try {
            HintsDao hintsDao = new HintsDao(ApplicationConnection.getInstance());
            hintsDao.insert(newHint.getText(), newHint.getDescription());
        } catch (Exception ex) {
            throw new AddHintException(ex);
        }
    }

    public void remove(int id) throws DeleteHintException {
        try {
            HintsDao hintsDao = new HintsDao(ApplicationConnection.getInstance());
            hintsDao.delete(id);
        } catch (Exception ex) {
            throw new DeleteHintException(ex);
        }
    }

    public void update(int id, String idKey, String text, String description) {
        try {
            HintsDao hintsDao = new HintsDao(ApplicationConnection.getInstance());
            hintsDao.update(id, idKey, text, description);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public List<Hint> all() throws AllHintsException {
        try {
            HintsDao hintsDao = new HintsDao(ApplicationConnection.getInstance());
            return hintsDao.select();
        } catch (Exception ex) {
            throw new AllHintsException(ex);
        }
    }

    public List<Hint> all(String condition) throws AllHintsException {
        try {
            HintsDao hintsDao = new HintsDao(ApplicationConnection.getInstance());
            if (condition == null || "".equals(condition)) {
                return hintsDao.select();
            } else {
                return hintsDao.selectCondition(condition);
            }
        } catch (Exception ex) {
            throw new AllHintsException(ex);
        }
    }

    public String selectKey(int idKey) {
        String text = null;
        try {
            HintsDao hintsDao = new HintsDao(ApplicationConnection.getInstance());
            text = hintsDao.selectKey(idKey);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return text;
    }
}
