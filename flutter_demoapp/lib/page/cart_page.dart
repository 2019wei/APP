import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_demoapp/provider/cart_provider.dart';
import 'package:flutter_slidable/flutter_slidable.dart';
import 'package:provider/provider.dart';

class CartPage extends StatefulWidget {
  @override
  _CartPageState createState() => _CartPageState();
}

class _CartPageState extends State<CartPage> {
  // 保留一個sliable打開
  final SlidableController _slidableController = SlidableController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("購物車"),
      ),
      body: Consumer<CartProvider>(builder: (_, provider, __) {
        if (provider.models.length == 0) {
          return Center(
            child: Column(
              children: [
                Padding(
                  padding: EdgeInsets.only(top: 20, bottom: 20),
                  child: Image.asset(
                    "assets/image/shop_cart.png",
                    width: 90,
                    height: 90,
                  ),
                ),
                Text(
                  "購物車空空如也,去逛逛吧~",
                  style: TextStyle(fontSize: 16, color: Colors.white60),
                )
              ],
            ),
          );
        } else {
          //有商品
          return Stack(
            children: [
              //商品列表
              ListView.builder(
                  itemCount: provider.models.length,
                  itemBuilder: (context, index) {
                    return buildProductitem(provider, index);
                  }),

              //底部菜單欄
              Positioned(
                bottom: 0,
                left: 0,
                right: 0,
                child: Container(
                  height: 50,
                  decoration: BoxDecoration(
                      color: Colors.white,
                      border: Border(
                          top: BorderSide(width: 1, color: Colors.white60),
                          bottom: BorderSide(width: 1, color: Colors.white60))),
                  child: Row(
                    children: [
                      InkWell(
                        child: Padding(
                          padding: EdgeInsets.only(left: 5, right: 5),
                          child: Image.asset(
                            provider.isSelectAll
                                ? "assets/image/selected.png"
                                : "assets/image/unselect.png",
                            width: 20,
                            height: 20,
                          ),
                        ),
                        onTap: () {
                          //選中全選
                          provider.changeSelectAll();
                        },
                      ),
                      Text(
                        "全選",
                        style: TextStyle(color: Colors.blue),
                      ),
                      SizedBox(
                        width: 10,
                      ),
                      Text(
                        "合計",
                        style: TextStyle(fontSize: 16),
                      ),
                      Text(
                        "\$",
                        style: TextStyle(
                            fontSize: 16,
                            color: Colors.black38,
                            fontWeight: FontWeight.w500),
                      ),
                      Spacer(),
                      Container(
                        width: 120,
                        height: double.infinity,
                        color: Colors.redAccent,
                        child: Center(
                          child: Text(
                            "去結算()",
                            style: TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.w500,
                                fontSize: 16),
                          ),
                        ),
                      )
                    ],
                  ),
                ),
              )
            ],
          );
        }
      }),
    );
  }

  Widget buildProductitem(CartProvider provider, int index) {
    return Slidable(
      controller: _slidableController,
      actionPane: SlidableDrawerActionPane(),
      actionExtentRatio: 0.2,
      // 右側action
      secondaryActions: [
        SlideAction(
          child: Center(
            child: Text(
              '刪除',
              style: TextStyle(color: Colors.white, fontSize: 16),
            ),
          ),
          color: Colors.redAccent,
          onTap: () {
            //刪除
            print('執行刪除');
            provider.removeFromCart(provider.models[index].id);
          },
        )
      ],
      child: Row(
        children: [
          InkWell(
            child: Padding(
              padding: EdgeInsets.only(left: 8),
              child: Image.asset(
                provider.models[index].isSelected
                    ? "assets/image/selected.png"
                    : "assets/image/unselect.png",
                width: 20,
                height: 20,
              ),
            ),
            onTap: () {
              //選中事件
              provider.changeSelectId(provider.models[index].id);
            },
          ),
          Expanded(
            child: Card(
              margin: EdgeInsets.all(8.0),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    padding: EdgeInsets.only(left: 5),
                    child: Image.asset(
                      "assets/${provider.models[index].loopImgUrl[0]}",
                      width: 90,
                      height: 90,
                    ),
                  ),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Container(
                          padding: EdgeInsets.only(top: 5),
                          child: Text(
                            provider.models[index].title,
                            style: TextStyle(
                                fontSize: 16, fontWeight: FontWeight.w400),
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                          ),
                        ),
                        SizedBox(
                          height: 10,
                        ),
                        Row(
                          children: [
                            Text(
                              "\$${provider.models[index].price}",
                              style: TextStyle(
                                  fontSize: 16,
                                  fontWeight: FontWeight.w400,
                                  color: Colors.redAccent),
                            ),
                            Spacer(),
                            InkWell(
                              child: Container(
                                width: 35,
                                height: 35,
                                color: Colors.white60,
                                child: Center(
                                    child: Text(
                                  "－",
                                  style: TextStyle(
                                      fontSize: 18,
                                      color: provider.models[index].count == 1
                                          ? Color(0xFFB0B0B0)
                                          : Colors.black),
                                )),
                              ),
                              onTap: () {
                                //減號
                                provider.models[index].count -= 1;
                                provider.addToCart(provider.models[index]);
                              },
                            ),
                            SizedBox(
                              width: 2,
                            ),
                            Container(
                              width: 35,
                              height: 35,
                              child: Center(
                                child: Text("${provider.models[index].count}"),
                              ),
                            ),
                            SizedBox(
                              width: 2,
                            ),
                            InkWell(
                              child: Container(
                                width: 35,
                                height: 35,
                                color: Color(0xFFF7F7F7),
                                child: Center(
                                  child: Text(
                                    "+",
                                    style: TextStyle(fontSize: 18),
                                  ),
                                ),
                              ),
                              onTap: () {
                                //加號
                                provider.models[index].count += 1;
                                provider.addToCart(provider.models[index]);
                              },
                            )
                          ],
                        )
                      ],
                    ),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
