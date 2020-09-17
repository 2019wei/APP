import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/config/jd_api.dart';
import 'package:flutter_demoapp/net/net_request.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
      NetRequest().requestData(JdApi.HOME_PAGE).then((value) => print(value.data));

    return Scaffold(
      appBar: AppBar(
        title: Text("首頁"),
      ),
      body: Container(),
    );
  }
}


// NetRequest() async {
//   var dio = Dio();
//   Response response = await dio.get(JdApi.HOME_PAGE);
//   print(response.data);
// }