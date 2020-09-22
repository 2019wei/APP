import 'package:flutter/material.dart';
import 'package:flutter_demoapp/config/jd_api.dart';
import 'package:flutter_demoapp/model/product_detail_model.dart';
import 'package:flutter_demoapp/net/net_request.dart';


class ProductDetailProvider with ChangeNotifier{

  ProductDetailModel model;
  bool isLoading = false;
  bool isError = false;
  String errorMsg = "";


  loadProduct({String id}){
    isLoading = true;
    isError = false;
    errorMsg ="";
    NetRequest().requestData(JdApi.PRODUCTIONS_DETAIL).then((value){
      isLoading = false;
       print(value.data);
      if(value.code == 200 && value.data is List){
        for(var item in value.data){
          ProductDetailModel tmpModel = ProductDetailModel.fromJson(item);
          if(tmpModel.partData.id == id){
              model = tmpModel;
          }
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
  //分期切換
  changeBaitiaoSelected(int index){
    if(this.model.baitiao[index].select == false){
      for(int i =0;i<this.model.baitiao.length;i++){
        if(i==index){
          this.model.baitiao[i].select = true;
        }else{
          this.model.baitiao[i].select = false;
        }
      }

      notifyListeners();
    }
  }

}