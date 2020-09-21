import 'package:flutter/material.dart';
import 'package:flutter_demoapp/config/jd_api.dart';
import 'package:flutter_demoapp/model/product_info_model.dart';
import 'package:flutter_demoapp/net/net_request.dart';


class ProductListProvider with ChangeNotifier{


  bool isLoading = false;
  bool isError = false;
  String errorMsg = "";
  List<ProductinfoModel> list = [];

  loadProductList(){
    isLoading = true;
    isError = false;
    errorMsg ="";
    NetRequest().requestData(JdApi.PRODUCTIONS_LIST).then((value){
      isLoading = false;
      print(value.data);
      if(value.code == 200 && value.data is List){
        for(var item in value.data){
          ProductinfoModel tmpModel = ProductinfoModel.fromJson(item);
          list.add(tmpModel);
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
  }

}