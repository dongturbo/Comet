package com.comet;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

import javax.servlet.AsyncContext;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class CometListener  {
    private static LinkedList<Message> messages = new LinkedList<Message>();

    public void onListener(String text) throws InterruptedException {




        WebContext webContext = WebContextFactory.get();

        //获取当前页面URL，比如/ext3/test_tag.jsp
        String currentPage = webContext.getCurrentPage();
        //当前脚本sessin
        ScriptSession scriptSession = webContext.getScriptSession();
        String uuid= (String)webContext.getSession().getAttribute("uuid");
        Message message=new Message();
        new CometServlet().getMap().put(uuid,message);
        synchronized (message){
            while(message.getText()==null){
                try {
                    message.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(message.getText());
        //return message.getText();
        //设置页面控件的值
        Util util = new Util(scriptSession);
        util.setValue("text", message.getText()); //这里是清空页面输入框的值

        //设置脚本sessin的属性值
        scriptSession.setAttribute("uuid", message.getId());
        //获取脚本session的属性值
        for(Iterator it = scriptSession.getAttributeNames(); it.hasNext();){
            String attrName = (String)it.next();
            System.out.println(attrName + "=" + scriptSession.getAttribute(attrName));
        }
        //获取所有浏览当前页面的脚本session
        Collection<ScriptSession> sessions = webContext.getScriptSessionsByPage(currentPage);

        System.out.println("UUID="+webContext.getSession().getAttribute("uuid"));
        Util utilAll = new Util(sessions);
        //执行客户端脚本
        ScriptBuffer script = new ScriptBuffer();
        script.appendScript("clientFunction(")
                .appendData(scriptSession.getAttribute("uuid"))
                .appendScript(");");


        for(ScriptSession session: sessions){
            session.addScript(script);
        }


        //更新这些脚本session的一些元素
        //utilAll.removeAllOptions("messages");
        //utilAll.addOptions("messages", messages, "id", "text");
    }
}