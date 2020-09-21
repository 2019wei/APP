import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/model/product_info_model.dart';
import 'package:flutter_demoapp/provider/product_list_provider.dart';
import 'package:provider/provider.dart';

class ProductListPage extends StatefulWidget {
  final String title;

  ProductListPage({Key key, this.title}) : super(key: key);

  @override
  _ProductListPageState createState() => _ProductListPageState();
}

class _ProductListPageState extends State<ProductListPage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Scaffold(
        appBar: AppBar(
          title: Text("${widget.title}"),
        ),
        body: Container(
          color: Colors.white,
          child: Consumer<ProductListProvider>(
            builder: (_, provider, __) {
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
                          provider.loadProductList();
                        },
                      )
                    ],
                  ),
                );
              }
              return ListView.builder(
                  itemCount: provider.list.length,
                  itemBuilder: (context, index) {
                    ProductinfoModel model = provider.list[index];
                    //print(model.toJson());
                    //展示
                    return InkWell(child: buildProductItem(model),onTap: (){
                      //前往商品
                      print(model.title);
                    },);
                  });
            },
          ),
        ),
      ),
    );
  }

  Row buildProductItem(ProductinfoModel model) {
    return Row(
                    children: [
                      Image.asset(
                        "assets${model.cover}",
                        width: 95,
                        height: 120,
                      ),
                      Expanded(
                        child: Padding(
                          padding: const EdgeInsets.all(5),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.start,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                model.title,
                                maxLines: 2,
                                overflow: TextOverflow.ellipsis,
                              ),
                              SizedBox(
                                height: 5,
                              ),
                              Text(
                                "${model.price}",
                                style: TextStyle(
                                    fontSize: 16, color: Colors.red),
                              ),
                              SizedBox(
                                height: 5,
                              ),
                              Row(
                                children: [
                                  Text(
                                    "${model.comment}條評價",
                                    style: TextStyle(
                                        fontSize: 13, color: Colors.grey),
                                  ),SizedBox(
                                    height: 5,
                                  ),
                                  Text(
                                    " 好評率${model.rate}",
                                    style: TextStyle(
                                        fontSize: 13, color: Colors.grey),
                                  ),
                                ],
                              )
                            ],
                          ),
                        ),
                      )
                    ],
                  );
  }
}
