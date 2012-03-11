package com.xml;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;

public class TestXml {
	 public   void   BuildXMLDoc()   throws   IOException,   JDOMException {
	        Element   eeeRoot,   eee1,   eee2;
	        Document   Doc;
	        eeeRoot   =   new   Element("employees_information");
	        Doc   =   new   Document(eeeRoot);
	        eeeRoot   =   Doc.getRootElement();
	        eee1   =   new   Element("name");
	        eee2   =   eee1.setText("C.Y.   Shen");
	        //eee2   =   eee1.addAttribute("emp_id",   "001");
	        eee1   =   eeeRoot.addContent(eee2);
	        eee1   =   new   Element("age");
	        eee2   =   eee1.setText("43");
	        eee1   =   eeeRoot.addContent(eee2);
	        eee1   =   new   Element("sex");
	        eee2   =   eee1.setText("Male");
	        eee1   =   eeeRoot.addContent(eee2);
	        //   XMLOut.setEncoding("gb2312");
	        XMLOutputter   XMLOut   =   new   XMLOutputter();
	        XMLOut.output(Doc,   new   FileOutputStream("test1.xml"));
	    }
	    public   static   void   main(String[]   args) {
	        try   {
	        	TestXml   s1   =   new   TestXml();
	            System.out.println("Now   we   build   an   XML   document   .....");
	            s1.BuildXMLDoc();
	        } catch   (Exception   e) {
	            System.out.println(e.getMessage());
	        }
	    }

}
