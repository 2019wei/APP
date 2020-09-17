import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/config/jd_api.dart';
import 'package:flutter_demoapp/net/net_request.dart';
import 'package:flutter_demoapp/provider/home_page_provider.dart';
import 'package:provider/provider.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    // NetRequest().requestData(JdApi.HOME_PAGE).then((value) => print(value.data));

    return ChangeNotifierProvider<HomePageProivder>(
      create: (context) {
        var provider = HomePageProivder();
        provider.loadHomePageData();
        return provider;
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text('首頁'),
        ),
        body: Consumer<HomePageProivder>(
          builder: (_, provider, __) {
            return Container();
          },
        ),
      ),
    );
  }
}

// NetRequest() async {
//   var dio = Dio();
//   Response response = await dio.get(JdApi.HOME_PAGE);
//   print(response.data);
// }
