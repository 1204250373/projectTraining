package dao.impl;

import com.sun.jndi.ldap.Connection;
import com.sun.org.apache.xml.internal.utils.BoolStack;
import dao.Compare;
import dbutils.DBHelper;
import javafx.collections.FXCollections;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SGBook implements Compare {


    //根据书籍价格进行排序
    public static void SortBook_Price_UP(Vector<Vector<String>> books){
        SGBook.quickSort(books,0,books.size()-1,up_compare);
    }
    public static void SortBook_Price_DOWN(Vector<Vector<String>> books){
        SGBook.quickSort(books,0,books.size()-1,down_compare);
    }
    //卖家上架书籍
    public static void SetBook( String Vendor,String BookName
            , String BookStack , String NowRepertory , String BookPrice ){
        String sql = "insert into books (Vendor , BookName , BookState , NowRepertory , BookPrice )" +
                " values('"+Vendor+"','"+BookName+"','"+BookStack+"','"+NowRepertory+"','"+BookPrice+"')";
        DBHelper.update(sql);

    }
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
        Vector<Vector<String>> getbook2 = SGBook.GetBookAll();
//        SGBook.SetBook("12","12","12","12","12");
//        Vector<Vector<String>> getbook3 = SGBook.SeekBooks_Vendor("12");
//        System.out.println(getbook);
//        System.out.println(getbook2);
//        System.out.println(getbook3);
//        System.out.println( getbook.get(0).get(BOOKNAME) );
//        SGBook.quickSort(getbook2,0,getbook2.size());
        SortBook_Price_UP(getbook2);
        System.out.println(getbook2);
        SortBook_Price_DOWN(getbook2);
        System.out.println(getbook2);


    }

    private static void quickSort(Vector<Vector<String>> books, int low, int high,Compare compare) {
        if (low < high) {
            // 找到基准元素的位置
            int pivotIndex = partition(books, low, high,compare);

            // 递归地对基准元素左右两边的子数组进行排序
            quickSort(books, low, pivotIndex - 1,compare);
            quickSort(books, pivotIndex + 1, high,compare);
        }
    }
    private static int partition(Vector<Vector<String>> books, int low, int high ,Compare pare) {
        String pivot = books.get(high).get(BOOKPRICE);  // 选择最右边的元素作为基准元素
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (pare.compare(Float.parseFloat(books.get(j).get(BOOKPRICE)),Float.parseFloat(pivot))) {
                i++;
                swap(books, i, j);
            }
        }

        swap(books, i + 1, high);
        return i + 1;
    }
    private static void swap(Vector<Vector<String>> books, int i, int j) {
        Vector<String> temp = books.get(i);
        books.set(i,books.get(j))  ;
        books.set(j,temp)  ;
    }

    final static int MAXBOOSNUM = 50;
    final static int BOOKATTRUVYTE = 6;
    final static int BOOKID = 0;
    final static int BOOKNAME = 1;
    final static int BOOKSTATE = 2;
    final static int NOWREPERTORY = 3;
    final static int BOOKPRICE = 4;
    final static int VENDOR = 5;

   static Compare up_compare = (f1, f2) -> f1<f2;
   static Compare down_compare = (f1, f2) -> f1>f2;


    @Override
    public boolean compare(Float f1, Float f2) {
        return false;
    }
}
