import 'package:flutter/material.dart';
import 'package:flutter_demoapp/ch_state.dart';
import 'package:flutter_demoapp/page/cart_page.dart';
import 'package:flutter_demoapp/page/category_page.dart';
import 'package:flutter_demoapp/page/home_page.dart';
import 'package:flutter_demoapp/page/user_page.dart';
import 'package:provider/provider.dart';

class IndexPage extends StatefulWidget {
  @override
  _IndexPageState createState() => _IndexPageState();
}

class _IndexPageState extends State<IndexPage> {
  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: Consumer<chState>(
        builder: (context, currentIndex,__) {
          return BottomNavigationBar(
            type: BottomNavigationBarType.fixed,
            currentIndex: currentIndex.currentIndex,
            onTap: (index) {
              currentIndex.changeCurrentIndex(index);
            },
            items: [
              BottomNavigationBarItem(
                  icon: Icon(Icons.home), title: Text('首頁')),
              BottomNavigationBarItem(
                  icon: Icon(Icons.category), title: Text('分類')),
              BottomNavigationBarItem(
                  icon: Icon(Icons.shopping_cart), title: Text('購物車')),
              BottomNavigationBarItem(
                  icon: Icon(Icons.account_circle), title: Text('我的')),
            ],
          );
        },
      ),
      body: Consumer<chState>(builder: (_,currentIndex,__){
        return IndexedStack(
          index: currentIndex.currentIndex,
          children: [
            HomePage(),
            CategoryPage(),
            CartPage(),
            UserPage(),
          ],
        );
      },)
    );
  }
}
