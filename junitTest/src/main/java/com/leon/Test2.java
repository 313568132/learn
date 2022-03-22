package com.leon;

import com.leon.api.vo.Book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Test2 {
    public static void main(String[] args) throws Exception{
        Book book = new Book("a2",32);
        ObjectOutput outputStream =
                new ObjectOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\a.txt",true));
        outputStream.writeObject(book);
        outputStream.close();


        ObjectInput objectInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\a.txt"));
        Book book2 = (Book)objectInputStream.readObject();
        System.out.println("book2.getName(): "+ book2.getName());
        System.out.println("book2.getPage(): "+ book2.getPage());
    }
}
