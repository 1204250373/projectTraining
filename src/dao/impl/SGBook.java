package dao.impl;

import dbutils.DBHelper;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SGBook {

    //卖家上架书籍

    //通过书名找书
    public static Vector<Vector<String>> SeekBooks_BookName(String BookName) throws SQLException {
        Vector<Vector<String>> BOOKS = new Vector<>(MAXBOOSNUM);
        String sql = "SELECT * from books where BookName ="+ BookName +";";
        ResultSet rs = DBHelper.query(sql);
        while(rs.next()) {
            Vector<String> book = new Vector<>(BOOKATTRUVYTE);
            book.add(rs.getString("BookID"));
            book.add(rs.getString("BookName"));
            book.add(rs.getString("BookState"));
            book.add(rs.getString("NowRepertory"));
            book.add(rs.getString("BookPrice"));
            book.add(rs.getString("Vendor"));
            BOOKS.add(book);
        }
        return BOOKS;
    }
    //通过卖家查找书
    public static Vector<Vector<String>> SeekBooks_Vendor(String Vendor) throws SQLException {
        Vector<Vector<String>> BOOKS = new Vector<>(MAXBOOSNUM);
        String sql = "SELECT * from books where Vendor = "+Vendor+";";
        ResultSet rs = DBHelper.query(sql);
        while(rs.next()) {
            Vector<String> book = new Vector<>(BOOKATTRUVYTE);
            book.add(rs.getString("BookID"));
            book.add(rs.getString("BookName"));
            book.add(rs.getString("BookState"));
            book.add(rs.getString("NowRepertory"));
            book.add(rs.getString("BookPrice"));
            book.add(rs.getString("Vendor"));
            BOOKS.add(book);
        }
        return BOOKS;
    }
    //获取所有售卖的书籍
    public static Vector<Vector<String>> GetBookAll() throws SQLException {

        Vector<Vector<String>> BOOKS = new Vector<>(MAXBOOSNUM);
        String sql = "SELECT * from books;";
        ResultSet rs = DBHelper.query(sql);
        while(rs.next()) {
            Vector<String> book = new Vector<>(BOOKATTRUVYTE);
            book.add(rs.getString("BookID"));
            book.add(rs.getString("BookName"));
            book.add(rs.getString("BookState"));
            book.add(rs.getString("NowRepertory"));
            book.add(rs.getString("BookPrice"));
            book.add(rs.getString("Vendor"));
            BOOKS.add(book);
        }
        return BOOKS;
    }

    public static void main(String[] args) throws SQLException {
        Vector<Vector<String>> getbook = SGBook.SeekBooks_Vendor("5");
        System.out.println(getbook);
    }


    final static int MAXBOOSNUM = 50;
    final static int BOOKATTRUVYTE = 6;
    final static int BookID = 0;
    final static int BookName = 1;
    final static int BookState = 2;
    final static int NowRepertory = 3;
    final static int BookPrice = 4;
    final static int Vendor = 5;
}
