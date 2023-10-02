package dao.impl;

import dao.Compare;
import dbutils.DBHelper;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

public class SSGBook {

    //卖家上架书籍
    public static void SetBook( String Vendor,String BookName
            , String BookStack , String NowRepertory , String BookPrice ){
        String sql = "insert into books (Vendor , BookName , BookState , NowRepertory , BookPrice )" +
                " values('"+Vendor+"','"+BookName+"','"+BookStack+"','"+NowRepertory+"','"+BookPrice+"')";
        DBHelper.update(sql);

    }
    //通过书名找书
    public static Vector<Vector<String>> SeekBooks_BookName(String BookName) throws SQLException {
        String sql = "SELECT * from books where BookName ="+ BookName +";";
        ResultSet rs = DBHelper.query(sql);
        return ResultSerToVector(rs);
    }
    //通过卖家查找书
    public static Vector<Vector<String>> SeekBooks_Vendor(String Vendor) throws SQLException {
        String sql = "SELECT * from books where Vendor = "+Vendor+";";
        ResultSet rs = DBHelper.query(sql);
        return ResultSerToVector(rs);
    }
    //获取所有售卖的书籍
    public static Vector<Vector<String>> GetBookAll() throws SQLException {
        String sql = "SELECT * from books;";
        ResultSet rs = DBHelper.query(sql);
        return ResultSerToVector(rs);
    }

    //根据信息删除（下架/卖出）书籍
    public static void SoldBook(String BookID) throws SQLException {

        String soldTime = GetNewTime.GetTime();//卖出时间
        int bookRep = Integer.parseInt(GetBookRepertory(BookID)) ;//库存

        if(bookRep>=1){
            String sql ="UPDATE books SET NowRepertory='"+(bookRep-1)+"' WHERE BookID = '"+ BookID +"';";
            DBHelper.update(sql);
        }else if(false){
            //String sql ="DELETE * from books where BookID="+BookID+";";
        }else{

        }

    }
    public static void DeleteBook(String BookID) throws SQLException {
        String sql ="DELETE  from books where BookID='"+BookID+"';";
        DBHelper.update(sql);
    }

        //根据书籍价格进行排序
    public static void SortBook_Price_UP(Vector<Vector<String>> books){
        SSGBook.quickSort(books,0,books.size()-1,BOOKPRICE,up_compare);
    }
    public static void SortBook_Price_DOWN(Vector<Vector<String>> books){
        SSGBook.quickSort(books,0,books.size()-1,BOOKPRICE,down_compare);
    }
    public static void SortBook_Name_UP(Vector<Vector<String>> books){
        SSGBook.quickSort(books,0,books.size()-1,BOOKNAME,Name_up_compare);
    }
    public static void SortBook_Name_DOWN(Vector<Vector<String>> books){
        SSGBook.quickSort(books,0,books.size()-1,BOOKNAME,Name_down_compare);
    }
    public static void SortBook_Vendor_UP(Vector<Vector<String>> books){
        SSGBook.quickSort(books,0,books.size()-1,VENDOR,Vendor_up_compare);
    }
    public static void SortBook_Vendor_DOWN(Vector<Vector<String>> books){
        SSGBook.quickSort(books,0,books.size()-1,VENDOR,Vendor_down_compare);
    }
    public static void main(String[] args) throws SQLException {
        //Vector<Vector<String>> getbook = SGBook.SeekBooks_Vendor("5");
        Vector<Vector<String>> getbook2 = SSGBook.GetBookAll();
        Vector<Vector<String>> getbook3 = SSGBook.GetBookAll();
//        SGBook.SetBook("12","12","12","12","12");
//        Vector<Vector<String>> getbook3 = SGBook.SeekBooks_Vendor("12");
//        System.out.println(getbook);
//        System.out.println(getbook2);
//        System.out.println(getbook3);
//        System.out.println( getbook.get(0).get(BOOKNAME) );
//        SGBook.quickSort(getbook2,0,getbook2.size());
        SSGBook.SoldBook(getbook2.get(0).get(BOOKID));
        //SSGBook.DeleteBook(getbook2.get(1).get(BOOKID));


    }

    private static Vector<Vector<String>> ResultSerToVector(ResultSet rs) throws SQLException {
        Vector<Vector<String>> BOOKS = new Vector<>(MAXBOOSNUM);
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

    private static void quickSort(Vector<Vector<String>> books, int low, int high, int CompareVALUE,Compare compare) {
        if (low < high) {
            // 找到基准元素的位置
            int pivotIndex = partition(books, low, high,CompareVALUE,compare);

            // 递归地对基准元素左右两边的子数组进行排序
            quickSort(books, low, pivotIndex - 1,CompareVALUE,compare);
            quickSort(books, pivotIndex + 1, high,CompareVALUE,compare);
        }
    }
    private static int partition(Vector<Vector<String>> books, int low, int high , int CompareVALUE,Compare pare) {
        String pivot = books.get(high).get(CompareVALUE);  // 选择最右边的元素作为基准元素
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (pare.compare(books.get(j).get(CompareVALUE),pivot)) {
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

    //获取书的库存
    private static String GetBookRepertory(String BookId) throws SQLException {
        String sql ="SELECT * from books where BookID ="+BookId+";";
        ResultSet resultSet =  DBHelper.query(sql);
        if(resultSet.next()){
            return resultSet.getString("NowRepertory");
        }else{
            return "-1";
        }

    }
    //根据ID获取书的全部信息
    private static Vector<String> GetBook(String BookId) throws SQLException {
        String sql ="SELECT * from books where BookID ="+BookId+";";
        ResultSet rs =  DBHelper.query(sql);
        if(rs.next()) {
            Vector<String> book = new Vector<>(BOOKATTRUVYTE);
            book.add(rs.getString("BookID"));
            book.add(rs.getString("BookName"));
            book.add(rs.getString("BookState"));
            book.add(rs.getString("NowRepertory"));
            book.add(rs.getString("BookPrice"));
            book.add(rs.getString("Vendor"));
            return book;
        }
        return null;
    }

    final static int MAXBOOSNUM = 50;
    final static int BOOKATTRUVYTE = 6;
    final static int BOOKID = 0;
    final static int BOOKNAME = 1;
    final static int BOOKSTATE = 2;
    final static int NOWREPERTORY = 3;
    final static int BOOKPRICE = 4;
    final static int VENDOR = 5;

   static Compare up_compare = (s1, s2) -> Float.parseFloat(s1)<Float.parseFloat(s2);
   static Compare down_compare =  (s1, s2)->Float.parseFloat(s1)>Float.parseFloat(s2);
   static Compare Name_up_compare = (s1, s2) -> s1.compareTo(s2)>0 ;
   static Compare Name_down_compare = (s1 ,s2) -> s2.compareTo(s1)>0;
   static Compare Vendor_up_compare = (s1, s2) -> s1.compareTo(s2)>0 ;
   static Compare Vendor_down_compare = (s1 ,s2) -> s2.compareTo(s1)>0;



}
