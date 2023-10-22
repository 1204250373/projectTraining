package dao.impl;

import beans.User;
import dao.Compare;
import dbutils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DoOrder {


    public static Vector<Vector<String>> SellerSeekOrder(User user) throws SQLException {
        String sql = "select * from orderFrom where seller_id="+user.getSid()+";";
        ResultSet rs =DBHelper.query(sql);
        Vector<Vector<String>> orders = ResultSerToVector(rs);
        return orders;
    }
    public static Vector<Vector<String>> BuyerSeekOrder(User user) throws SQLException {
        String sql = "select * from orderFrom where buyer_id="+user.getSid()+";";
        ResultSet rs =DBHelper.query(sql);
        Vector<Vector<String>> orders = ResultSerToVector(rs);
        return orders;
    }

    public static void NtoY(String orderId){
        String sql = "update orderFrom set YorN = '已发货' where oid ="+orderId+";";
        DBHelper.update(sql);
    }
    public static void ChangeReceive(String orderId){
        String sql = "update orderFrom set receive_goods = '已收货' where oid ="+orderId+";";
        DBHelper.update(sql);
    }

    public static void SetDeliverGoods(String orderId){
        String Time = GetNewTime.GetTime();
        String sql = "update orderFrom set deliver_goods='"+Time+"' where oid ="+orderId+";";
        DBHelper.update(sql);
    }

    public static void SortOrderState_UP(Vector<Vector<String>> orders){
        SSGBook.quickSort(orders,0,orders.size()-1,YORN,State_up_compare);
    }
    public static void SortOrderrece_UP(Vector<Vector<String>> orders){
        SSGBook.quickSort(orders,0,orders.size()-1,RECEIVEGOODS,State_up_compare);
    }
    public static void SortOrderDELIVER_UP(Vector<Vector<String>> orders){
        SSGBook.quickSort(orders,0,orders.size()-1,DELIVERGOODS,DELIVERGOODS_up_compare);
    }
    //订单的生成
    public  static void LogSoldBook(User buyer,String bookid ,String buyNum,String allPrice) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Vector<String> book = SSGBook.SeekBooks_BookID(bookid).get(0);
        String book_name = book.get(SSGBook.BOOKNAME);
        String book_price = book.get(SSGBook.BOOKPRICE);
        String book_vender = book.get(SSGBook.VENDOR);
        String addresss ="*******************";
        User seller = UserDaoImpl.findUserbyID(book_vender);
        String buyTime = GetNewTime.GetTime();
        addOrder(buyer.getPhone(),buyer.getSid(),book_vender,seller.getPhone(),book_price,book_name,buyNum,allPrice,buyTime,addresss);

    }

    public static void Deleteorder(String admin , String user){
        String type = "删除用户";
        String sql = "insert into doUser(admin,type,user) values('"+admin+"','"+type+"','"+user+"')";
        DBHelper.update(sql);
    }

    private static Vector<Vector<String>> ResultSerToVector(ResultSet rs) throws SQLException {
        Vector<Vector<String>> orders = new Vector<>(MAXORDER);
        while(rs.next()) {
            Vector<String> order = new Vector<>(ORDERSTATE);
            order.add(rs.getString("oid"));
            order.add(rs.getString("seller_phone"));
            order.add(rs.getString("seller_id"));
            order.add(rs.getString("buyer_phone"));
            order.add(rs.getString("buyer_id"));
            order.add(rs.getString("book_name"));
            order.add(rs.getString("book_unitPrice"));
            order.add(rs.getString("book_buyNum"));
            order.add(rs.getString("allPrice"));
            order.add(rs.getString("YorN"));
            order.add(rs.getString("buyTime"));
            order.add(rs.getString("buyeraddress"));
            order.add(rs.getString("deliver_goods"));
            order.add(rs.getString("receive_goods"));
            orders.add(order);
        }
        return orders;
    }


    private static void addOrder(String buyer_phone,String buyer_id,String seller_id,String seller_phone,String bookPrice,String bookName ,String bookNum,String allPrice,String buyTime,String address) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String sql ="insert into orderFrom (seller_phone,seller_id,buyer_phone,buyer_id,book_name,book_unitPrice,book_buyNum,allPrice,YorN,buyTime,buyeraddress) " +
                "values('"+seller_phone+"','"+seller_id+"','"+buyer_phone+"','"+buyer_id+"','"+bookName+"',"+bookPrice+","+bookNum+","+allPrice+",'未发货','"+buyTime+"','"+address+"')";
        DBHelper.update(sql);
    }




    final static int MAXORDER = 50;
    final static int ORDERSTATE = 11;
    public final static int OID =0;
    public final static int SELLERPHONE=1;
    public final static int SELLERID=2;
    public final static int BUYERPHONE=3;
    public final static int BUYERID=4;
    public final static int BOOKNAME=5;
    public final static int BOOKUNIPRICE=6;
    public final static int BOOKBUYNUM=7;
    public final static int ALLPRICE=8;
    public final static int YORN=9;
    public final static int BUYTIME=10;
    public final static int ADDRESS=11;
    public final static int DELIVERGOODS=12;
    public final static int RECEIVEGOODS=13;
    static Compare DELIVERGOODS_up_compare = new Compare() {
        @Override
        public boolean compare(String f1, String f2) {
            if(f1==null||f2==null) return false ;
            return f1.compareTo(f2)>0;
        }
    };
    static Compare State_up_compare = (s1 ,s2) -> s1.compareTo(s2)>0;

//    public static void main(String[] args) {
////        Deleteorder("2240129516","110");
//    }




}
