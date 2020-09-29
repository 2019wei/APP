import 'package:flare_splash_screen/flare_splash_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/ch_state.dart';
import 'package:flutter_demoapp/page/index_page.dart';
import 'package:flutter_demoapp/provider/cart_provider.dart';
import 'package:provider/provider.dart';

main() {
  runApp(MultiProvider(
    providers: [
      ChangeNotifierProvider.value(
        value: chState(),
      ),
      ChangeNotifierProvider<CartProvider>(
        create: (context){
          CartProvider provider = new CartProvider();
          provider.getCartList();
          return provider;
        },
      ),
    ],
    child: MyApp(),
  ));
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(primarySwatch: Colors.blue),
        // IndexPage()
        // loadPage(context)
        home: IndexPage());
  }
}

Widget loadPage(BuildContext context) {
  return SplashScreen.navigate(
    name: 'assets/intro.flr',
    next: (context) => IndexPage(),
    until: () => Future.delayed(Duration(seconds: 3)),
    startAnimation: '1',
  );
}
