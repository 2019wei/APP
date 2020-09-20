import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/model/category_content_model.dart';
import 'package:flutter_demoapp/provider/category_page_provider.dart';
import 'package:provider/provider.dart';

class CategoryPage extends StatefulWidget {
  @override
  _CategoryPageState createState() => _CategoryPageState();
}

class _CategoryPageState extends State<CategoryPage> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<CategoryPageProivder>(
      create: (context) {
        var provider = CategoryPageProivder();
        provider.loadCategoryPageData();
        return provider;
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text('分類'),
        ),
        body: Container(
          color: Color(0xFFf4f4f4),
          child: Consumer<CategoryPageProivder>(
            builder: (_, provider, __) {
              // print(provider.isLoading);
              //加載動畫
              if (provider.isLoading && provider.categoryNavList.length ==0) {
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
                          provider.loadCategoryPageData();
                        },
                      )
                    ],
                  ),
                );
              }
              print(provider.categoryNavList);
              return Row(
                children: [
                  //分類左側
                  buildNavLeftContainer(provider),
                  //分類右側
                  Expanded(child:Stack(
                    children: [buildCategoryContent(provider.catagoryContentList),
                    provider.isLoading ?Center(child:CupertinoActivityIndicator()):Container()
                    ],
                  ) ,),
                ],
              );
            },
          ),
        ),
      ),
    );
  }

  //左側
  Container buildNavLeftContainer(CategoryPageProivder provider) {
    return Container(
      width: 90,
      child: ListView.builder(
          itemCount: provider.categoryNavList.length,
          itemBuilder: (context, index) {
            return InkWell(
              child: Container(
                  height: 50,
                  color:
                      provider.tabIndex == index ? Colors.amber : Colors.blue,
                  padding: const EdgeInsets.only(top: 15),
                  child: Text(
                    provider.categoryNavList[index],
                    textAlign: TextAlign.center,
                    style: TextStyle(
                        color: Colors.red, fontWeight: FontWeight.w500),
                  )),
              onTap: () {
                //print(provider.categoryNavList[index]);
                provider.loadCategoryContentData(index);
              },
            );
          }),
    );
  }

  //右側
  buildCategoryContent(List<CatagoryContentModel> contentList) {
    List<Widget> list = [];
    //處理數據
    for (var i = 0; i < contentList.length; i++) {
      list.add(Container(
        height: 30,
        margin: const EdgeInsets.only(left: 10, top: 10),
        child: Text(
          "${contentList[i].title}",
          style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
        ),
      ));
      //商品數據
      List<Widget> descList = [];
      for (var j = 0; j < contentList[i].desc.length; j++) {
        descList.add(InkWell(
          child: Container(
            width: 60,
            color: Colors.white,
            child: Column(
              children: [
                Image.asset(
                  "assets${contentList[i].desc[j].img}",
                  width: 50,
                  height: 50,
                ),
                Text("${contentList[i].desc[j].text}")
              ],
            ),
          ),
          onTap: () {
            //前往商品
          },
        ));
      }
      //將desclist追加到list中
      list.add(
        Padding(
          padding: const EdgeInsets.all(8),
          child: Wrap(
            spacing: 7.0,
            runSpacing: 10,
            alignment: WrapAlignment.start,
            children: descList,
          ),
        ),
      );
    }

    return Container(
      width: double.infinity,
      color: Colors.green,
      child: ListView(
        children: list,
      ),
    );
  }
}
