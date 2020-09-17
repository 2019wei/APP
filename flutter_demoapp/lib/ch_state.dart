import 'package:flutter/material.dart';

class chState with ChangeNotifier{
  int currentIndex = 0;

  void changeCurrentIndex(int index){
    if(this.currentIndex != index){
      this.currentIndex = index;
      notifyListeners();
    }
  }

}