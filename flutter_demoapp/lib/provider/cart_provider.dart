import 'package:flutter/material.dart';
import 'package:flutter_demoapp/model/product_detail_model.dart';

class CartProvider with ChangeNotifier{
  Future<void> addToCart(PartData data) async{
    print(data.toJson());
  }
}