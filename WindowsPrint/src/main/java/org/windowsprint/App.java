package org.windowsprint;

import org.windowsprint.component.*;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //用户信息Frame
        String userInfoFrame = "用户信息Frame";
        ArrayList<IComponent> userInfoFrameChildren = new ArrayList<IComponent>();
        userInfoFrameChildren.add(new Label("用户名Label"));
        userInfoFrameChildren.add(new Text("用户名Text"));
        userInfoFrameChildren.add(new Label("密码Label"));
        userInfoFrameChildren.add(new PasswordBox("密码PasswordBox"));
        userInfoFrameChildren.add(new CheckBox("复选框CheckBox"));
        userInfoFrameChildren.add(new Label("记住用户名Label"));
        userInfoFrameChildren.add(new LinkLabel("忘记密码LinkLabel"));

        //登录页面窗体
        String myWindowsName = "登录页面窗体";
        ArrayList<IComponent> myWindowsChildren = new ArrayList<IComponent>();
        myWindowsChildren.add(new PictureBox("Logo图标"));
        myWindowsChildren.add(new Frame(userInfoFrame,userInfoFrameChildren));
        myWindowsChildren.add(new Button("登录Button"));
        myWindowsChildren.add(new Button("注册Button"));
        Windows myWindows = new Windows(myWindowsName, myWindowsChildren);
        myWindows.print();
    }
}
