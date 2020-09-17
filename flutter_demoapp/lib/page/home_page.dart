import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/config/jd_api.dart';
import 'package:flutter_demoapp/net/net_request.dart';
import 'package:flutter_demoapp/page/chage_page.dart';
import 'package:flutter_demoapp/provider/home_page_provider.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'package:provider/provider.dart';

import '../model/home_page_model.dart';

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
        body: Container(
          color: Color(0xFFf4f4f4),
          child: Consumer<HomePageProivder>(
            builder: (_, provider, __) {
              // print(provider.isLoading);
              //加載動畫
              if (provider.isLoading) {
                return Center(child: CupertinoActivityIndicator());
              }

              //捕獲異常
              if (provider.isError) {
                return Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(provider.errorMsg),
                      OutlineButton(
                        child: Text('刷新'),
                        onPressed: () {
                          provider.loadHomePageData();
                        },
                      )
                    ],
                  ),
                );
              }

              HomePageModel model = provider.model;

              return ListView(
                children: [
                  buildAspercRatio(model),
                  //圖標分類
                  buildLogs(model),
                ],
              );
              //return Container();
            },
          ),
        ),
      ),
    );
  }

  //圖標分類
  Widget buildLogs(HomePageModel model) {
    //定義list
    List<Widget> list = [];

    //遍例model中的logos
    for (var i = 0; i < model.logos.length; i++) {
      list.add(GestureDetector(
        child: Container(
          width: 60,
          child: Column(
            children: [
              Image.asset(
                "assets${model.logos[i].image}",
                width: 50,
                height: 50,
              ),
              Text('${model.logos[i].title}')
            ],
          ),
        ),
        onTap:()=> chagePage('${model.logos[i].title}',context),
      ));
    }

    return Container(
      color: Colors.white,
      height: 170,
      padding: const EdgeInsets.all(10.0),
      child: Wrap(
        children: list,
        spacing: 7.0,
        runSpacing: 10.0,
        alignment: WrapAlignment.spaceBetween,
      ),
    );
  }

//輪播圖
  AspectRatio buildAspercRatio(HomePageModel model) {
    return AspectRatio(
      aspectRatio: 72 / 35,
      child: Swiper(
        itemCount: model.swipers.length,
        pagination: SwiperPagination(),
        autoplay: true,
        itemBuilder: (BuildContext context, int index) {
          return Image.asset("assets${model.swipers[index].image}");
        },
      ),
    );
  }

  chagePage(String title,BuildContext context) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => ChagePage(
                  title: title,
                )));
  }
}

// NetRequest() async {
//   var dio = Dio();
//   Response response = await dio.get(JdApi.HOME_PAGE);
//   print(response.data);
// }
