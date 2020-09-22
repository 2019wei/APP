import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/model/product_detail_model.dart';
import 'package:flutter_demoapp/provider/product_detail_provider.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'package:provider/provider.dart';

class ProductDetailPage extends StatefulWidget {
  final String id;

  ProductDetailPage({Key key, this.id}) : super(key: key);

  @override
  _ProductDetailPageState createState() => _ProductDetailPageState();
}

class _ProductDetailPageState extends State<ProductDetailPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("經東"),
      ),
      body: Container(
        child: Consumer<ProductDetailProvider>(
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
                        provider.loadProduct(id: widget.id);
                      },
                    )
                  ],
                ),
              );
            }

            //獲取model
            ProductDetailModel model = provider.model;
            String baitiaoTitle = "【白條支付】首單想立減優惠";

            for (var item in model.baitiao) {
              if (item.select == true) {
                baitiaoTitle = item.desc;
              }
            }

            return Stack(
              children: [
                //主體內容
                ListView(
                  children: [
                    //輪播
                    buildSwiperContainer(model),
                    //標題
                    buildTitleContainer(model),
                    //價格
                    buildPriceContainer(model),
                    //白條支付
                    buildPayContainer(baitiaoTitle, model,provider),

                    //商品件數
                    buildCountContainer(model),
                  ],
                ),

                //底部菜單
                buildBottomPositioned()
              ],
            );
          },
        ),
      ),
    );
  }

  Positioned buildBottomPositioned() {
    return Positioned(
      left: 0,
      right: 0,
      bottom: 0,
      child: Container(
        decoration: BoxDecoration(
            border:
                Border(top: BorderSide(width: 3, color: Color(0xffe8e8ed)))),
        child: Row(
          children: [
            Expanded(
              child: InkWell(
                child: Container(
                  height: 60,
                  color: Colors.amber,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Icon(Icons.shopping_cart),
                      Text(
                        "購物車",
                        style: TextStyle(fontSize: 13),
                      )
                    ],
                  ),
                ),
                onTap: () {
                  //購物車
                },
              ),
            ),
            Expanded(
              child: InkWell(
                child: Container(
                  height: 60,
                  color: Colors.blueAccent,
                  alignment: Alignment.center,
                  child: Text(
                    '加入購物車',
                    style: TextStyle(
                        fontSize: 15,
                        color: Colors.white,
                        fontWeight: FontWeight.bold),
                  ),
                ),
                onTap: () {
                  //加入購物車
                },
              ),
            )
          ],
        ),
      ),
    );
  }

  Container buildCountContainer(ProductDetailModel model) {
    return Container(
      padding: EdgeInsets.all(10),
      decoration: BoxDecoration(
          color: Colors.white,
          border: Border(
              top: BorderSide(width: 1, color: Colors.white60),
              bottom: BorderSide(width: 1, color: Colors.white60))),
      child: InkWell(
        child: Row(
          children: [
            Text(
              "已選",
              style: TextStyle(color: Colors.black38),
            ),
            Expanded(
              child: Padding(
                padding: EdgeInsets.only(left: 8, right: 8),
                child: Text('${model.partData.count}件'),
              ),
            ),
            Icon(Icons.more_horiz)
          ],
        ),
        onTap: () {
          //選擇商品個數
        },
      ),
    );
  }

  Container buildPayContainer(String baitiaoTitle, ProductDetailModel model, ProductDetailProvider provider) {
    return Container(
      padding: EdgeInsets.all(10),
      decoration: BoxDecoration(
          color: Colors.white,
          border: Border(
              top: BorderSide(width: 1, color: Colors.white60),
              bottom: BorderSide(width: 1, color: Colors.white60))),
      child: InkWell(
        child: Row(
          children: [
            Text(
              "支付",
              style: TextStyle(color: Colors.black38),
            ),
            Expanded(
              child: Padding(
                padding: EdgeInsets.only(left: 8, right: 8),
                child: Text('$baitiaoTitle'),
              ),
            ),
            Icon(Icons.more_horiz)
          ],
        ),
        onTap: () {
          //選擇支付方式
          // print(baitiaoTitle);
          return showBaitiao(model,provider);
        },
      ),
    );
  }

  Future showBaitiao(ProductDetailModel model, ProductDetailProvider provider) {
    return showModalBottomSheet(
        context: context,
        builder: (BuildContext context) {
          return ChangeNotifierProvider<ProductDetailProvider>.value(
            value: provider,
            child: Stack(
              children: [
                //頂部標題藍
                Stack(
                  children: [
                    Container(
                      width: double.infinity,
                      height: 40,
                      color: Colors.white60,
                      child: Center(
                        child: Text(
                          "打白條購買",
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                    Positioned(
                      top: 0,
                      right: 0,
                      width: 40,
                      height: 40,
                      child: Center(
                        child: IconButton(
                          icon: Icon(Icons.close),
                          iconSize: 20,
                          onPressed: () {
                            //關閉
                            Navigator.pop(context);
                          },
                        ),
                      ),
                    )
                  ],
                ),
                //主體列表
                Container(
                  margin: EdgeInsets.only(top: 40, bottom: 50),
                  child: ListView.builder(
                      itemCount: model.baitiao.length,
                      itemBuilder: (context, index) {
                        return InkWell(
                          child: Row(
                            children: [
                              Padding(
                                padding: EdgeInsets.only(left: 8, right: 8),
                                child: Consumer<ProductDetailProvider>(builder: (_,tmpProvider,__){
                                  return Image.asset(
                                    model.baitiao[index].select ?
                                    "assets/image/selected.png" : "assets/image/unselect.png" ,
                                    width: 20,
                                    height: 20,
                                  );
                                },),
                              ),
                              Padding(
                                padding: const EdgeInsets.only(top: 8, bottom: 8),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text("${model.baitiao[index].desc}"),
                                    Text("${model.baitiao[index].tip}"),
                                  ],
                                ),
                              )
                            ],
                          ),
                          onTap: () {
                            //選擇分期類型
                      provider.changeBaitiaoSelected(index);
                          },
                        );
                      }),
                )

                //底部按鈕
                ,
                Positioned(
                  left: 0,
                  right: 0,
                  bottom: 0,
                  child: InkWell(
                    child: Container(
                      alignment: Alignment.center,
                      child: Text(
                        '立即打白條',
                        style: TextStyle(
                            color: Colors.white, fontWeight: FontWeight.bold),
                      ),
                      width: double.infinity,
                      height: 50,
                      color: Colors.deepOrangeAccent,
                    ),
                    onTap: () {
                      //確定分期返回
                      Navigator.pop(context);
                    },
                  ),
                )
              ],
            ),
          );
        });
  }

  Container buildPriceContainer(ProductDetailModel model) {
    return Container(
      width: double.infinity,
      color: Colors.white,
      padding: EdgeInsets.all(10),
      child: Text(
        "\$${model.partData.price}",
        style: TextStyle(
            fontSize: 16, fontWeight: FontWeight.bold, color: Colors.redAccent),
      ),
    );
  }

  Container buildTitleContainer(ProductDetailModel model) {
    return Container(
      color: Colors.white,
      padding: EdgeInsets.all(10),
      child: Text(
        model.partData.title,
        style: TextStyle(fontSize: 16.0, fontWeight: FontWeight.bold),
      ),
    );
  }

  Container buildSwiperContainer(ProductDetailModel model) {
    return Container(
      color: Colors.white,
      height: 300,
      child: Swiper(
        itemCount: model.partData.loopImgUrl.length,
        pagination: SwiperPagination(),
        autoplay: true,
        itemBuilder: (BuildContext context, int index) {
          return Image.asset(
            "assets${model.partData.loopImgUrl[index]}",
            width: double.infinity,
            height: 400,
            fit: BoxFit.fill,
          );
        },
      ),
    );
  }
}
