import 'package:flutter/material.dart';
import 'package:flutter_demoapp/provider/cart_provider.dart';
import 'package:provider/provider.dart';

class CartPage extends StatefulWidget {
  @override
  _CartPageState createState() => _CartPageState();
}

class _CartPageState extends State<CartPage> {
  @override
  Widget build(BuildContext context) {
    return Consumer<CartProvider>(builder: (_, provider, __) {
      return Scaffold(
        appBar: AppBar(
          title: Text("購物車"),
        ),
        body: Container(),
      );
    });
  }
}
