
import 'dart:convert';

import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/model/product_detail_model.dart';
import 'package:shared_preferences/shared_preferences.dart';

class CartProvider with ChangeNotifier{
  List<PartData> models = [];
  bool isSelectAll = false;

  Future<void> addToCart(PartData data) async{
    // print(data.toJson());

    SharedPreferences prefs = await SharedPreferences.getInstance();
    //存入緩存
    //   List<String> list = [];
    // list.add(json.encode(data.toJson()));
    // prefs.setStringList("cartInfo", list);

    //取出緩存
    //  list = prefs.getStringList("cartInfo");
    //  print(list);

    //先把緩存裡的數據取出來
    List<String> list = [];
    list = prefs.getStringList("cartInfo");
    models.clear();
    //判斷取出的list有沒有東西
    if(list == null){
      print("緩存裡沒有任何商品數據");
      list = [];
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
  // print(models[0].id);
  // print(id);
  SharedPreferences prefs = await SharedPreferences.getInstance();
  List<String> list = [];
  //取出緩存
  list = prefs.getStringList("cartInfo");
  //遍歷緩存數據
  for(var i=0;i<list.length;i++){
    PartData tmpData = PartData.fromJson(json.decode(list[i]));
    if(tmpData.id == id){
      list.remove(list[i]);
      // print('找到1');
      break;
    }
  }

  //遍歷本地數據
  for(var i=0;i<models.length;i++){
    if(this.models[i].id == id){
      this.models.remove(this.models[i]);
      // print('找到2');
      break;
    }
  }

  //緩存重新賦值
  prefs.setStringList("cartInfo", list);
  notifyListeners();
}
//選中狀態
void changeSelectId(String id){
    int tmpCount = 0;
    // print(id);
  for(var i =0; i <this.models.length; i++){
    if(id == this.models[i].id){
      this.models[i].isSelected = !this.models[i].isSelected;
    }
    if(this.models[i].isSelected){
      tmpCount++;
    }
  }
  //如果tmpCount的個數 和 models.lenth 一致 那就是全選狀態
    if(tmpCount == this.models.length){
      this.isSelectAll = true;
    }else{
      this.isSelectAll = false;
    }
  notifyListeners();
}

//全選
void changeSelectAll(){
  isSelectAll = !isSelectAll;
  for(var i = 0 ; i< this.models.length; i++){
    this.models[i].isSelected = isSelectAll;
  }
  notifyListeners();
}


//統計合計金額
String getAmount(){
    String amountStr = "0.00";
    for(var i = 0 ; i <this.models.length ; i++){
      if(this.models[i].isSelected == true){
        num price = this.models[i].count * NumUtil.getNumByValueStr(this.models[i].price,fractionDigits: 2);
        num amount = NumUtil.getNumByValueStr(amountStr,fractionDigits: 2);
        amountStr = NumUtil.add(amount, price).toString();
      }
    }

    return amountStr;
}

//統計選中商品個數
int getSelectedCount(){
    int selectedCount = 0;
    for(var i = 0 ; i<this.models.length; i++){
      if(this.models[i].isSelected == true){
        selectedCount++;
      }
    }

    return selectedCount ;
}




}