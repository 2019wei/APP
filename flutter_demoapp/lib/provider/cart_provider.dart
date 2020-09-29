import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/model/product_detail_model.dart';
import 'package:shared_preferences/shared_preferences.dart';

class CartProvider with ChangeNotifier{
  List<PartData> models = [];

  Future<void> addToCart(PartData data) async{
    // print(data.toJson());

    SharedPreferences prefs = await SharedPreferences.getInstance();
    //存入緩存
    //  List<String> list = [];
    // list.add(json.encode(data.toJson()));
    // prefs.setStringList("cartInfo", list);

    //取出緩存
    //  list = prefs.getStringList("cartInfo");
    //  print(list);

    //先把緩存裡的數據取出來
    List<String> list = [];
    list = prefs.getStringList("cartInfo");

    //判斷取出的list有沒有東西
    if(list == null){
      print("緩存裡沒有任何商品數據");
      // 講傳遞過來的數據存到緩存和數組中
      list.add(json.encode(data.toJson()));
      //存到緩存
      prefs.setStringList("cartInfo", list);
      //更新本地數據
      models.add(data);
      //通知聽眾
      notifyListeners();
    }else{
      print("緩存裡有商品數據");
      //定義臨時數組
      List<String> tmpList = [];
      //判斷緩存中是否有對象的商品
      bool isUpdated = false;

      //遍歷緩存數組
      for(var i = 0 ;i<list.length;i++){
        PartData tmpData = PartData.fromJson(json.decode(list[i]));
        //判斷商品id
        if(tmpData.id == data.id){
          tmpData.count = data.count;
          isUpdated = true;
        }

        //放到數組中
        String tmpDataStr = json.encode(tmpData.toJson());
       tmpList.add(tmpDataStr);
       models.add(tmpData);
      }


      // 如果緩存裡的數組,沒有現在添加的商品,那麼直接添加
      if(isUpdated == false){
        String str = json.encode(data.toJson());
        tmpList.add(str);
        models.add(data);
      }
      //存入緩存
      prefs.setStringList("cartInfo", tmpList);

      //通知聽眾
      notifyListeners();

    }
  }

  //獲取購物車商品數量
int getAllCount(){
    int count = 0;
    for(PartData data in this.models){
      count += data.count;
    }
    return count;
}

//獲取購物車的商品
void getCartList() async{
  SharedPreferences prefs = await SharedPreferences.getInstance();
   List<String> list = [];
  //取出緩存
   list = prefs.getStringList("cartInfo");
   if(list != null){
     for(var i=0;i<list.length;i++){
       PartData tmpData = PartData.fromJson(json.decode(list[i]));
       models.add(tmpData);
     }
     notifyListeners();
   }

}
//刪除商品
void removeFromCart(String id) async {
    //從緩存中刪除
  SharedPreferences prefs = await SharedPreferences.getInstance();
  List<String> list = [];
  //取出緩存
  list = prefs.getStringList("cartInfo");
  for(var i=0;i<list.length;i++){
    PartData tmpData = PartData.fromJson(json.decode(list[i]));
    models.add(tmpData);
  }
}


}