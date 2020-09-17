import 'package:flutter/material.dart';
import 'package:flutter_demoapp/config/jd_api.dart';
import 'package:flutter_demoapp/model/home_page_model.dart';
import 'package:flutter_demoapp/net/net_request.dart';


class HomePageProivder with ChangeNotifier{

  HomePageModel model;
  bool isLoading = false;
  bool isError = false;
  String errorMsg = "";

  loagHomePageData(){
     isLoading = true;
     isError = false;
     errorMsg ="";
    NetRequest().requestData(JdApi.HOME_PAGE).then((value){
      isLoading = false;
      if(value.code == 200){
        print(value.data);
        model = HomePageModel.fromJson(value.data);
      }
      notifyListeners();
    }).catchError((error){
      print(error);
      errorMsg = error;
      isLoading =error;
      isError = true;
      notifyListeners();
    });
  }

}