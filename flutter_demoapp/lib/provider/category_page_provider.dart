import 'package:flutter/material.dart';
import 'package:flutter_demoapp/config/jd_api.dart';
import 'package:flutter_demoapp/model/category_content_model.dart';
import 'package:flutter_demoapp/net/net_request.dart';


class CategoryPageProivder with ChangeNotifier{


  bool isLoading = false;
  bool isError = false;
  String errorMsg = "";
  List<String> categoryNavList = [];
  List<CatagoryContentModel> catagoryContentList = [];
  int tabIndex = 0;

  loadCategoryPageData(){
    isLoading = true;
    isError = false;
    errorMsg ="";
    NetRequest().requestData(JdApi.CATEGORY_NAV).then((value){
      isLoading = false;
      // print(value.data);
      if(value.data is List){
        for (var i =0 ; i< value.data.length;i++){
          categoryNavList.add(value.data[i]);
        }
        loadCategoryContentData(tabIndex);
      }
      notifyListeners();
    }).catchError((error){
      print(error);
      errorMsg = error;
      isLoading =false;
      isError = true;
      notifyListeners();
    });
  }
//分類右側
  loadCategoryContentData(int index){
    this.tabIndex = index;
   isLoading = true;
  catagoryContentList.clear();

  //請求數據
   var data = {'title':categoryNavList[index]};
   NetRequest().requestData(JdApi.CATEGORY_CONTENT,data: data,method: "post").then((value){
     isLoading = false;
     print(value.data);
     if(value.data is List){
       for(var item in value.data){
         CatagoryContentModel tempModel = CatagoryContentModel.fromJson(item);
         catagoryContentList.add(tempModel);
       }
     }
     notifyListeners();
   }).catchError((error){
     print(error);
     errorMsg = error;
     isLoading =false;
     isError = true;
     notifyListeners();
   });


  notifyListeners();
  }


}