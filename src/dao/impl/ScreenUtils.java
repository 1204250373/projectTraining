package dao.impl;

import java.awt.*;

public class ScreenUtils {

    //��ȡ��ǰ������Ļ�Ŀ��
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    //��õ�ǰ������Ļ�ĸ߶�

    public static int getScreenHeight(){

        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
