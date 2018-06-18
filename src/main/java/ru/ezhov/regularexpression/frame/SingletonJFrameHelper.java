/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

/**
 * @author RRNDeonisiusEZH
 */
public class SingletonJFrameHelper {
    private static JFrameHelper frameHelper;

    private SingletonJFrameHelper() {
    }

    public static JFrameHelper getInstance() {
        if (frameHelper == null) {
            frameHelper = new JFrameHelper();
        }
        return frameHelper;
    }
}
