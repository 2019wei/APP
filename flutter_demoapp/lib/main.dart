import 'package:flutter/material.dart';
import 'package:flutter_demoapp/ch_state.dart';
import 'package:flutter_demoapp/page/index_page.dart';
import 'package:provider/provider.dart';

main() {
  runApp(ChangeNotifierProvider.value(
    value: chState(),
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
      home: IndexPage(),
    );
  }
}
